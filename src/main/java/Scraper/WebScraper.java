package Scraper;
//Imports needed for scraping
import Data.Product;
import Utils.DataWriter;
import Utils.ProductUtils;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WebScraper {
    private static final int THREAD_POOL_SIZE = 10;
    private static Document page;



    public static List<Product> ParallelScraping(String startURL) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        //Thread safe variant of arrayList, add set... are implemented by making a fresh copy of the underlying array
        List<Product> products = new CopyOnWriteArrayList<>();

        //Future: interface which represents the result of an async computation
        //Blocks until the computation is complete

        List<Future<String>> futures = new ArrayList<>();
        //to make sure a visited page isn't scraped more than once
        ConcurrentSkipListSet<String> visitedPages = new ConcurrentSkipListSet<>();

        //add the first page to the task queue
        visitedPages.add(startURL);

        futures.add((Future<String>) executor.submit(()-> Scrape(startURL,products,visitedPages)));

        while (!futures.isEmpty())
        {
            List<Future<String>> newFutures = new ArrayList<>();
            for (Future<String> future : futures)
            {
                try{
                    //Waits for page scrape to complete
                    String nextUrl = future.get();
                    if (nextUrl != null && visitedPages.add(nextUrl)){
                        newFutures.add((Future<String>) executor.submit(()-> Scrape(nextUrl,products,visitedPages)));
                    }
                }
                catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            futures = newFutures;
        }

        executor.shutdown();
        try{
            executor.awaitTermination(60,TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.out.println("Execution interrupted:" + e.getMessage());
        }

        return products;
    }

    public static void Scrape(String startURL, List<Product> products, ConcurrentSkipListSet<String> visitedPages) {
        long Start = System.nanoTime();
        try{
            //Connecting to the site
            page = Jsoup.connect(startURL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                    .header("Accept-Language", "*").get();
        }
        catch (IOException e) {
            System.out.println("Can't scrape " + startURL);
        }

        GatherData(page);
        Element nextButton = page.selectFirst("a.next");
        if (nextButton != null) {
            String nextURL = nextButton.attr("href");
            if (!nextURL.startsWith("http")) {
                nextURL = startURL + nextURL.replace("^/","");
            }
            startURL = nextURL;
        }
        else {
            startURL = null;
        }
        long End = System.nanoTime();
        System.out.println("Took: " + (End - Start) / 1000000 + "ms");

    }

    private static void GatherData(Document doc) {
        //Selects elements in css style
        Elements productElements = doc.select("li.product");
        //Stores the selected elements
        for (Element productElement : productElements) {
            String url = productElement.selectFirst("a").attr("href");
            String productName = productElement.selectFirst("h2").text();
            String productPrice;
            if (productElement.selectFirst("span.onsale")!= null) {
                productPrice = productElement.selectFirst("span.price ins").text();
            }
            else{
                productPrice = productElement.selectFirst("span.price").text();
            }

            String img = productElement.selectFirst("img").attr("src");

            ProductUtils.AddProduct(
                    new Product(
                            productName,
                            Float.parseFloat(productPrice.substring(1)),
                            img,
                            url));
        }
    }

}

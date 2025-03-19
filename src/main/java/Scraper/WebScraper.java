package Scraper;
//Imports needed for scraping
import Data.Product;
import Utils.DataWriter;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;

public class WebScraper {
    public static ArrayList<Product> products = new ArrayList<>();

    private static Document page;

    private static String url = "https://www.scrapingcourse.com/ecommerce";

    public static void Scrape() {


        while (url != null) {
            try{
                //Connecting to the site
                page = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                        .header("Accept-Language", "*").get();
            }
            catch (IOException e) {
                System.out.println("Can't scrape " + url);
            }

            GatherData(page);
            Element nextButton = page.selectFirst("a.next");
            if (nextButton != null) {
                String nextURL = nextButton.attr("href");
                if (!nextURL.startsWith("http")) {
                    nextURL = url + nextURL.replace("^/","");
                }
                url = nextURL;
            }
            else {
                url = null;
            }
        }


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

            products.add(
                    new Product(
                    productName,
                    Float.parseFloat(productPrice.substring(1)),
                    img,
                    url)
            );
        }
    }

}

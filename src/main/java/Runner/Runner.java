package Runner;

import Scraper.WebScraper;
import Utils.DataWriter;
import Utils.MenuCLI;
import Utils.ProductUtils;

public class Runner {

    private static String url = "https://www.scrapingcourse.com/ecommerce";
    public static void main(String[] args) {
        WebScraper.ParallelScraping(url);
        MenuCLI.StartMenu();

    }
}

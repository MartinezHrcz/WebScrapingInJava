package Runner;

import Scraper.WebScraper;
import Utils.MenuCLI;


public class Runner {

    private static String url = "https://www.scrapingcourse.com/ecommerce";
    public static void main(String[] args) {
        WebScraper.ParallelScraping(url);
        MenuCLI.StartMenu();

    }
}

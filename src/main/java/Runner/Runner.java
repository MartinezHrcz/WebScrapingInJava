package Runner;

import Scraper.WebScraper;
import Utils.MenuCLI;

public class Runner {

    public static void main(String[] args) {
        WebScraper.Scrape();
        MenuCLI.StartMenu();
    }
}

package Utils;

import Data.Product;
import Scraper.WebScraper;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ProductUtils {

    public static Float AvgPrice() {
        float sum = 0;
        for (Product product : WebScraper.products) {
            sum += product.getProductPrice();
        }
        return sum / WebScraper.products.size();
    }

    public static Float MinPrice() {
        Optional<Product> max = WebScraper.products.stream().sorted(Product::compareTo).min(Product::compareTo);
        return max.stream().findFirst().get().getProductPrice();
    }
    public static Float MaxPrice() {
        Optional<Product> max = WebScraper.products.stream().sorted(Product::compareTo).max(Product::compareTo);
        return max.stream().findFirst().get().getProductPrice();
    }
}

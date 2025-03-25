package Utils;

import Data.Product;

import java.util.ArrayList;
import java.util.Optional;

public class ProductUtils {

    private static ArrayList<Product> products = new ArrayList<>();

    public static void AddProduct(Product product){
        products.add(product);
    }

    public static boolean RemoveProduct(Product product) {
        return products.remove(product);
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }


    public static Float AvgPrice() {
        float sum = 0;
        for (Product product : products) {
            sum += product.getProductPrice();
        }
        return sum / products.size();
    }

    public static Float MinPrice() {
        Optional<Product> max = products.stream().sorted(Product::compareTo).min(Product::compareTo);
        return max.stream().findFirst().get().getProductPrice();
    }
    public static Float MaxPrice() {
        Optional<Product> max = products.stream().sorted(Product::compareTo).max(Product::compareTo);
        return max.stream().findFirst().get().getProductPrice();
    }
}

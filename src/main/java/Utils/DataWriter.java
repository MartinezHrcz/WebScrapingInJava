package Utils;

import Data.Product;

import java.util.ArrayList;

public class DataWriter {

    public static void writeDataToCLI(ArrayList<Product> products) {

        products.forEach(product -> {
            System.out.println(product.toString());
        });
    }
}

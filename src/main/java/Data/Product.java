package Data;

public class Product implements Comparable<Product>{
    private String productName;
    private float productPrice;
    private String imageUrl;
    private String url;



    public Product(String productName, float productPrice, String imageUrl, String url) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("productName='").append(productName).append('\'');
        sb.append(", productPrice=").append(productPrice);
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", url='").append(url).append('\'');
        return sb.toString();
    }

    @Override
    public int compareTo(Product o) {
        return Float.compare(this.productPrice, o.productPrice);
    }
}

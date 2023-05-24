import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Product {
    private static long currentId;
    private String type;
    private double price;
    private boolean discount;
    private LocalDate date;
    private long id;


    public Product(String type, double price, boolean discount) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.date = LocalDate.now();
        this.id = currentId++;
   }

    public Product(String type, double price, boolean discount, LocalDate date) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.date = date;
        this.id = currentId++;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean getDiscount() {
        return discount;
    }

    public LocalDate getDate() {
        return date;
    }

    public static List<Product> getBooksAboveProduct(List<Product> productList) {
        return productList.stream()
                .filter(product -> product.getType().equals("Book"))
                .filter(product -> product.getPrice() > 250)
                .collect(Collectors.toList());
    }

    public static List<Product> discountBook(List<Product> productList) {
        return productList.stream()
                .filter(product -> product.getType().equals("Book"))
                .filter(Product::getDiscount)
                .map(product -> new Product(product.getType(), product.getPrice() * 0.9, false))
                .collect(Collectors.toList());
    }

    public static Product getBookWithMinPrice(List<Product> productList) throws Exception {
        String type = "Book";
        if (productList == null) {
            throw new Exception("Продукт [категорія: " + type + "] не знайдено");
        }
        return productList.stream()
                .filter(product -> product.getType().equals(type))
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElseThrow(() -> new Exception("Продукт [категорія: " + type + "] не знайдено"));
    }

    public static List<Product> get3LastAddProducts(List<Product> productList) {
        return productList.stream()
                .sorted(Comparator.comparing(Product::getDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
    public static double calculatePrice(List<Product> productList) {
        return productList.stream()
                .filter(product -> product.getDate().getYear() == LocalDate.now().getYear())
                .filter(product -> product.getType().equals("Book"))
                .map(Product::getPrice)
                .filter(productPrice -> productPrice < 75)
                .reduce(0D, Double::sum);
    }
    public static Map<String,List<Product>> groupingByType(List<Product> productList) {
        return productList.stream()
                .collect(Collectors.groupingBy(Product::getType,Collectors.mapping(Function.identity(),Collectors.toList())));
    }


    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}

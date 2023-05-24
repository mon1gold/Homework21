import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Book", 43, true, LocalDate.of(2022, 12, 2));
        Product product2 = new Product("Book", 6, true, LocalDate.of(2023, 11, 7));
        Product product3 = new Product("Book", 187, true, LocalDate.of(2023, 3, 4));
        Product product4 = new Product("Car", 12, true, LocalDate.of(2023, 9, 2));
        Product product5 = new Product("Car", 10, true, LocalDate.of(2023, 5, 17));
        System.out.println(Product.groupingByType(List.of(product1, product2, product3, product4, product5)));
    }
}
import java.util.List;

public class ProductController {
    private List<DB.Product> products;

    public ProductController(List<DB.Product> products) {
        this.products = products;
    }

    public void displayProductList() {
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            DB.Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());

        }
        System.out.println();
    }
    public DB.Product getProductByIndex(int index) {
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        } else {
            System.out.println("Invalid product index.");
            return null;
        }
    }
}

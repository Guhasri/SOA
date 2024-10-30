import java.util.Scanner;

public class EComFrontend {

    public static void main(String[] args) {
        // Initialize database and controllers
        DB db = new DB();
        DB.Cart cart = db.new Cart();
        ProductController productController = new ProductController(db.getDefaultProducts());
        CartController cartController = new CartController(cart);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nE-Commerce System");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    productController.displayProductList();
                    break;
                case 2:
                    System.out.print("Enter the product number to add to cart: ");
                    int productNumber = scanner.nextInt() - 1;
                    DB.Product product = productController.getProductByIndex(productNumber);
                    if (product != null) {
                        cart.addProduct(product);
                    }
                    break;
                case 3:
                    cartController.displayCartSummary();
                    break;
                case 4:
                    cartController.removeProductFromCart();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

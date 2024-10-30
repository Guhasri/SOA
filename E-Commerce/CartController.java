import java.util.Scanner;

public class CartController {
    private DB.Cart cart;
    private Scanner scanner;

    public CartController(DB.Cart cart) {
        this.cart = cart;
        this.scanner = new Scanner(System.in);
    }

    // Display cart summary
    public void displayCartSummary() {
        cart.showCart();
        System.out.println("Total price: $" + cart.getTotalPrice());
    }

    // Remove a product from the cart by index
    public void removeProductFromCart() {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. Nothing to remove.");
            return;
        }

        System.out.print("Enter the product number to remove: ");
        int productIndex = scanner.nextInt() - 1;

        if (productIndex >= 0 && productIndex < cart.getProducts().size()) {
            DB.Product productToRemove = cart.getProducts().get(productIndex);
            cart.removeProduct(productToRemove);
        } else {
            System.out.println("Invalid product number.");
        }
    }
}

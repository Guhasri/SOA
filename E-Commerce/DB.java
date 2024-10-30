import java.util.ArrayList;
import java.util.List;

public class DB {
    public static class Product {
        String name;
        double price;
        public Product(String name, double price) {
            this.name = name;
            this.price = price;

        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String toString() {
            return STR."Product : name= \{name}, price=\{price}";
        }
    }

    // Cart class to manage products added to the cart
    public class Cart {
       List<Product> products;

        public Cart() {
            this.products = new ArrayList<>();
        }

        public void addProduct(Product product) {
            products.add(product);
            System.out.println(product.getName() + " added to the cart.");
        }

        public void removeProduct(Product product) {
            products.remove(product);
            System.out.println(product.getName() + " removed from the cart.");
        }

        public double getTotalPrice() {
            double total = 0;
            for (Product product : products) {
                total += product.getPrice();
            }
            return total;
        }

        public void showCart() {
            if (products.isEmpty()) {
                System.out.println("Your cart is empty.");
            } else {
                System.out.println("Products in your cart:");
                for (Product product : products) {
                    System.out.println(product);
                }
            }
        }

        public List<Product> getProducts() {
            return products;
        }
    }

    // Method to create some default products
    public List<Product> getDefaultProducts() {
        List<Product> defaultProducts = new ArrayList<>();
        defaultProducts.add(new Product("Laptop", 999));
        defaultProducts.add(new Product("Smartphone", 499));
        defaultProducts.add(new Product("Headphones", 199));
        defaultProducts.add(new Product("Smartwatch", 149));
        defaultProducts.add(new Product("Tablet", 238));
        return defaultProducts;
    }
}

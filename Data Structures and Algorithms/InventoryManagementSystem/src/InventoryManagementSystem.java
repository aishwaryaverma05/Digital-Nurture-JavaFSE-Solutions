import java.util.HashMap;

public class InventoryManagementSystem {

    private static HashMap<Integer, Product> inventory = new HashMap<>();

    public static void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Product added successfully.");
    }

    public static void updateProduct(int productId, int quantity, double price) {
        Product product = inventory.get(productId);

        if (product != null) {
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void deleteProduct(int productId) {
        if (inventory.remove(productId) != null) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void displayInventory() {
        for (Product product : inventory.values()) {
            product.displayProduct();
        }
    }

    public static void main(String[] args) {

        Product p1 = new Product(101, "Laptop", 10, 55000);
        Product p2 = new Product(102, "Mouse", 50, 500);

        addProduct(p1);
        addProduct(p2);

        System.out.println("\nInventory:");
        displayInventory();

        updateProduct(101, 8, 53000);

        System.out.println("\nUpdated Inventory:");
        displayInventory();

        deleteProduct(102);

        System.out.println("\nFinal Inventory:");
        displayInventory();
    }
}
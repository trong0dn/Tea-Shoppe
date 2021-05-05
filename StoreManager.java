// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

public class StoreManager {
    // Create a new Inventory object upon object creation,
    private Inventory inventory = new Inventory();

    // To check how much stock of a given Product is in the Inventory.
    public int checkStock(Product product) {
        return inventory.getQuantity(product.getId());
    }

    // To process a transaction given an Array of Product information.
    public double processOrder(int[][] cartProduct) {
        double totalPrice = 0.0;
        boolean productExist = false;
        for (int[] i : cartProduct) {
            int id = i[0];
            int quantity = i[1];
            if (quantity > inventory.getQuantity(id)) {
                return -1;
            } else {
                totalPrice += inventory.getProduct(id).getPrice() * quantity;
                inventory.removeStock(id, quantity);
                productExist = true;
            }
        }
        if (!productExist) {
            return -1;
        }
        return totalPrice;
    }
}
// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import java.util.ArrayList;

/**
 * This class contains the functionality for managing the Inventory, ShoppingCart, and providing information to the
 * StoreView class.
 *
 * @author  Trong Nguyen
 * @version 2.0
 */
public class StoreManager {
    /**
     * Create a new Inventory object upon StoreManager object creation.
     */
    private final Inventory inventory = new Inventory();

    /**
     * Create a new ShoppingCart object upon StoreManager object creation.
     */
    private final ShoppingCart shoppingCart = new ShoppingCart();

    /**
     * ArrayList to track the ShoppingCart objects.
     */
    private final ArrayList<ShoppingCart> cartArrayList = new ArrayList<>();

    /**
     * Initialized int cartID used to identify the user of the system.
     */
    private int CART_ID = -1;

    /**
     * Check stock of a given Product is in the Inventory.
     *
     * @param product   Product object to be checked
     * @return          int value of amount of stock
     */
    public int checkStock(Product product) {
        return inventory.getQuantity(product.getId());
    }

    /**
     * Process transaction given shoppingCart.
     *
     * @param shoppingCart  ShoppingCart object containing the products and quantity.
     * @return              double value for the total price of all the products in the ShoppingCart.
     */
    public double processOrder(ShoppingCart shoppingCart) {
        double totalPrice = 0.0;
        for (Integer[] p : shoppingCart.getCart()) {
            int id = p[0];
            int quantity = p[1];
            totalPrice += inventory.getProductInfo(id).getPrice() * quantity;
            inventory.removeStock(id, quantity);
        }
        return totalPrice;
    }

    /**
     * Unique cartID used to identify the user of the system assigned to per StoreView for a new, unique ShoppingCart
     * object.
     *
     * @return  int value for new, unique cartID.
     */
    public int assignNewCartID() {
        CART_ID++;
        // Each increment of cartID adds a new instance of a ShoppingCart to the cartArrayList.
        cartArrayList.add(new ShoppingCart());
        return CART_ID;
    }

    /**
     * Get StoreManager inventory.
     *
     * @return  Inventory object, the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Get StoreManager cartArrayList.
     *
     * @return  ArrayList<String>, list of ShoppingCart
     */
    public ArrayList<ShoppingCart> getCartArrayList() {
        return cartArrayList;
    }

    /**
     * Get StoreManager shoppingCart.
     *
     * @return  ShoppingCart object, the shopping cart
     */
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
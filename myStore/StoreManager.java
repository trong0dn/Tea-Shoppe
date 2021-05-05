// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;

/**
 * This class contains the functionality for managing the store.Inventory, store.ShoppingCart,
 * and providing information to the store.StoreView class.
 *
 * @author  Trong Nguyen
 * @version 3.0
 */
public class StoreManager {
    /**
     * Create a new store.Inventory object upon store.StoreManager object creation.
     */
    private final Inventory inventory = new Inventory();

    /**
     * Create a new store.ShoppingCart object upon store.StoreManager object creation.
     */
    private final ShoppingCart shoppingCart = new ShoppingCart();

    /**
     * ArrayList to track the store.ShoppingCart objects.
     */
    private final ArrayList<ShoppingCart> cartArrayList = new ArrayList<>();

    /**
     * Initialized int cartID used to identify the user of the system.
     */
    private int CART_ID = -1;

    /**
     * Check stock of a given store.Product is in store.Inventory.
     *
     * @param product   store.Product object to be checked
     * @return          int value of amount of stock
     */
    public int checkStock(Product product) {
        return this.inventory.getQuantity(product.getId());
    }

    /**
     * Process transaction given shoppingCart.
     *
     * @param shoppingCart  store.ShoppingCart object containing the products and quantity.
     * @return              double value for the total price of all the products in the store.ShoppingCart.
     */
    public double processOrder(ShoppingCart shoppingCart) {
        double totalPrice = 0.0;
        for (Integer[] p : shoppingCart.getCart()) {
            int id = p[0];
            int quantity = p[1];
            totalPrice += this.inventory.getProduct(id).getPrice() * quantity;
            this.inventory.removeStock(id, quantity);
        }
        return totalPrice;
    }

    /**
     * Unique cartID used to identify the user of the system assigned to per store.StoreView for a new,
     * unique store.ShoppingCart object.
     *
     * @return  int value for new, unique cartID.
     */
    public int assignNewCartID() {
        CART_ID++;
        // Each increment of cartID adds a new instance of a store.ShoppingCart to the cartArrayList.
        cartArrayList.add(new ShoppingCart());
        return CART_ID;
    }

    /**
     * Get store.StoreManager inventory.
     *
     * @return  store.Inventory object, the inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Get store.StoreManager cartArrayList.
     *
     * @return  ArrayList<String>, list of store.ShoppingCart
     */
    public ArrayList<ShoppingCart> getCartArrayList() {
        return this.cartArrayList;
    }

    /**
     * Get store.StoreManager shoppingCart.
     *
     * @return  store.ShoppingCart object, the shopping cart
     */
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }
}
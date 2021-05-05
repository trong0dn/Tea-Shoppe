// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;

/**
 * This class contains the functionality for managing the Inventory, ShoppingCart,
 * and providing information to the StoreView class.
 *
 * @author  Trong Nguyen
 * @version 4.0
 */
public class StoreManager {
    /**
     * Create a new Inventory object upon StoreManager object creation.
     */
    private final Inventory storeInventory = new Inventory();

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
     * Check stock of a given Product is in Inventory.
     *
     * @param product   Product : object to be checked
     * @return          int  : value of amount of stock
     */
    public int checkStock(Product product) {
        return this.storeInventory.getStock(product.getId());
    }

    /**
     * Process transaction given shoppingCart.
     *
     * @return double : the total price of the transaction
     */
    public double processOrder() {
        double totalPrice = 0.0;
        for (Integer[] p : this.shoppingCart.getCart()) {
            int id = p[0];
            int quantity = p[1];
            totalPrice += this.storeInventory.getProductInfo(id).getPrice() * quantity;
        }
        if (totalPrice < 0) {
            totalPrice = 0;
        }
        return totalPrice;
    }

    /**
     * Unique cartID used to identify the user of the system assigned to per StoreView for a new,
     * unique ShoppingCart object.
     *
     * @return  int : value for new, unique cartID
     */
    public int assignNewCartID() {
        CART_ID++;
        // Each increment of cartID adds a new instance of a ShoppingCart to the cartArrayList.
        cartArrayList.add(new ShoppingCart());
        return CART_ID;
    }

    /**
     * Add product to the shopping cart and remove product from the inventory.
     *
     * @param id        int : product ID
     * @param quantity  int : the quantity to be transferred between shopping cart and inventory
     */
    public void addToCart(int id, int quantity) {
        for (Integer[] p : this.shoppingCart.getCart()) {
            if (p[0] == id) {
                this.shoppingCart.addToCart(id, quantity);
                this.storeInventory.removeStock(id, quantity);
                return;
            }
        }
        // Adding to the shopping cart for the first time.
        this.shoppingCart.addToCart(id, quantity);
        this.storeInventory.removeStock(id, quantity);
    }

    /**
     * Remove product from shopping cart and add product back to inventory.
     *
     * @param id        int : product ID
     * @param quantity  int : the quantity to be transferred between shopping cart and inventory
     */
    public void removeFromCart(int id, int quantity) {
        try {
            for (Integer[] p : this.shoppingCart.getCart()) {
                if (p[0] == id) {
                    this.shoppingCart.removeFromCart(id, quantity);
                    this.storeInventory.addStock(this.storeInventory.getProductInfo(id), quantity);
                    return;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // Do nothing.
        }
    }

    /**
     * Return all products in the shopping cart back to the inventory upon terminating without checkout.
     */
    public void clear() {
        for (Integer[] p : shoppingCart.getCart()) {
            int id = p[0];
            int stock = p[1];
            if (id == storeInventory.getProductInfo(id).getId()) {
                this.shoppingCart.removeFromCart(id, stock);
                this.storeInventory.addStock(this.storeInventory.getProductInfo(id), stock);
            }
        }
    }

    /**
     * Get StoreManager inventory.
     *
     * @return  Inventory : the inventory object
     */
    public Inventory getInventory() {
        return this.storeInventory;
    }

    /**
     * Get StoreManager cartArrayList.
     *
     * @return  ArrayList<String> : list of ShoppingCart
     */
    public ArrayList<ShoppingCart> getCartArrayList() {
        return this.cartArrayList;
    }

    /**
     * Get StoreManager shoppingCart.
     *
     * @return  ShoppingCart : the shopping cart object
     */
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }
}
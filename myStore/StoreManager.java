// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;

/**
 * This class contains the functionality for managing the Inventory, ShoppingCart,
 * and providing information to the StoreView class.
 *
 * @author  Trong Nguyen
 * @version 5.0
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
                this.shoppingCart.addProductQuantity(this.storeInventory.getProductInfo(id), quantity);
                this.storeInventory.removeProductQuantity(this.storeInventory.getProductInfo(id), quantity);
                return;
            }
        }
        // Adding to the shopping cart for the first time.
        this.shoppingCart.addProductQuantity(this.storeInventory.getProductInfo(id), quantity);
        this.storeInventory.removeProductQuantity(this.storeInventory.getProductInfo(id), quantity);
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
                    this.shoppingCart.removeProductQuantity(this.storeInventory.getProductInfo(id), quantity);
                    this.storeInventory.addProductQuantity(this.storeInventory.getProductInfo(id), quantity);
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
                this.shoppingCart.removeProductQuantity(this.storeInventory.getProductInfo(id), stock);
                this.storeInventory.addProductQuantity(this.storeInventory.getProductInfo(id), stock);
            }
        }
    }

    /**
     * Return stock of a given Product ID in Inventory.
     *
     * @param id       int : product ID
     * @return          int : value of amount of stock
     */
    public int getStock(int id) {
        return this.storeInventory.getProductQuantity(this.storeInventory.getProductInfo(id));
    }

    /**
     * Return price of a given Product ID in Inventory.
     *
     * @param id       int : product ID
     * @return          double : price value of the product
     */
    public double getPrice(int id) {
        return this.storeInventory.getProductInfo(id).getPrice();
    }

    /**
     * Return name of a given Product ID in Inventory.
     *
     * @param id       int : product ID
     * @return          String : name of the product
     */
    public String getName(int id) {
        return this.storeInventory.getProductInfo(id).getName();
    }

    /**
     * Get StoreManager shoppingCart.
     *
     * @return      ArrayList<Product> : list of integer array representing a product item
     */
    public ArrayList<Integer[]> getShoppingCart() {
        return this.shoppingCart.getCart();
    }

    /**
     * Get list of Product in the Inventory.
     *
     * @return      ArrayList<Product> : list of products
     */
    public ArrayList<Product> getProductCatalog() {
        return this.storeInventory.getProductCatalog();
    }
}
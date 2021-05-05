// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;

/**
 * This class keeps track of the state of the user's shopping cart.
 *
 * @author  Trong Nguyen
 * @version 5.0
 */
public class ShoppingCart implements ProductStockContainer {
    /**
     * Initialize ArrayList to store the array of product ID and amount.
     */
    private final ArrayList<Integer[]> shoppingCart = new ArrayList<>();

    /**
     * Get ArrayList which tracks the information for the shopping carts.
     *
     * @return  ArrayList<Integer[]> : object that tracks the shopping cart
     */
    public ArrayList<Integer[]> getCart() {
        return this.shoppingCart;
    }

    /**
     * Get the quantity of a specific product.
     *
     * @param product   Product : the product
     * @return          int : number of quantity
     */
    @Override
    public int getProductQuantity(Product product) {
        int quantity = 0;
        for (Integer[] integers : this.shoppingCart) {
            if (integers[0] == product.getId()) {
                quantity = integers[1];
                break;
            }
        }
        return quantity;
    }

    /**
     * Add a specified amount of product to the shopping cart.
     *
     * @param product   Product : the product
     * @param quantity  int : value for a specified amount of product to be added to shopping cart
     */
    @Override
    public void addProductQuantity(Product product, int quantity) {
        boolean productInCart = false;
        int newQuantity;
        int index = 0;
        int id = product.getId();
        Integer[] newProduct = {id, quantity};
        if (quantity > 0) {
            if (this.shoppingCart.size() != 0) {
                for (Integer[] p : this.shoppingCart) {
                    if (p[0] == id) {
                        // Products existing in the shopping cart are updated.
                        newQuantity = p[1] + quantity;
                        Integer[] Product = {id, newQuantity};
                        this.shoppingCart.set(index, Product);
                        productInCart = true;
                        break;
                    }
                    index++;
                }
            }
            if (!productInCart) {
                // New products are added to the shopping cart.
                this.shoppingCart.add(newProduct);
            }
        }
    }

    /**
     * Remove a specified amount of product from the shopping cart.
     *
     * @param product   Product : the product
     * @param quantity  int : value for a specified amount of product to be removed to shopping cart
     */
    @Override
    public void removeProductQuantity(Product product, int quantity) {
        int newQuantity;
        int index = 0;
        int id = product.getId();
        if (quantity > 0) {
            for (Integer[] p : this.shoppingCart) {
                if (p[0] == id) {
                    if (quantity > p[1]) {
                        // If quantity of product in shopping cart is 0, remove the product from cart.
                        this.shoppingCart.remove(p);
                    }
                    newQuantity = p[1] - quantity;
                    Integer[] Product = {id, newQuantity};
                    this.shoppingCart.set(index, Product);
                }
                index++;
            }
        }
    }

    /**
     * Get number of product item in the shopping cart.
     *
     * @return  int : number of products
     */
    @Override
    public int getNumOfProducts() {
        return getCart().size();
    }
}

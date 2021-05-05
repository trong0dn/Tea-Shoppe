// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;

/**
 * This class keeps track of the state of the user's shopping cart.
 *
 * @author  Trong Nguyen
 * @version 3.0
 */
public class ShoppingCart {
    /**
     * Initialize ArrayList to store the array of product ID and amount.
     */
    private final ArrayList<Integer[]> shoppingCart = new ArrayList<>();

    /**
     * Add a specified amount of product to the shopping cart.
     *
     * @param id        int value for the ID number of the product.
     * @param quantity  int value for a specified amount of product to be added to shopping cart.
     */
    public void addToCart(int id, int quantity) {
        boolean productInCart = false;
        int newQuantity;
        int index = 0;
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
     * @param id        int value for the ID number of the product.
     * @param quantity  int value for a specified amount of product to be removed to shopping cart.
     */
    public void removeFromCart(int id, int quantity) {
        if (quantity > 0) {
            for (Integer[] p : this.shoppingCart) {
                if (p[0] == id) {
                    p[1] -= quantity;
                    if (p[1] <= 0) {
                        // If quantity of product in shopping cart is 0, remove the product from cart.
                        this.shoppingCart.remove(p);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Get ArrayList which tracks the information for the shopping carts.
     *
     * @return  ArrayList<Integer[]> object that tracks the shopping cart.
     */
    public ArrayList<Integer[]> getCart() {
        return this.shoppingCart;
    }
}

// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;

/**
 * This class tracks the state of the product in inventory of the store.
 *
 * @author  Trong Nguyen
 * @version 3.0
 */
public class Inventory {
    /**
     * ArrayList to track the store.Product objects.
     */
    private final ArrayList<Product> products = new ArrayList<>();

    /**
     * ArrayList to track the amount of stocks.
     */
    private final ArrayList<Integer> stocks = new ArrayList<>();

    /**
     * Initialize the store.Inventory.
     */
    public Inventory() {
        init();
    }

    /**
     * Set the contents of the store.Inventory to some default values upon object creation.
     */
    private void init() {
        Product p1 = new Product("jasmine", 0, 1.00);
        Product p2 = new Product("matcha", 1, 2.50);
        Product p3 = new Product("oolong", 2, 3.25);
        Product p4 = new Product("chamomile", 3, 4.50);
        Product p5 = new Product("pu'erh", 4, 5.65);
        products.add(p1); stocks.add(5);
        products.add(p2); stocks.add(10);
        products.add(p3); stocks.add(15);
        products.add(p4); stocks.add(20);
        products.add(p5); stocks.add(25);
    }

    /**
     * Get amount of stock for a given store.Product ID number.
     *
     * @param id    int value for the ID number of the product.
     * @return      int value for the quantity of the product in stock.
     */
    public int getQuantity(int id) {
        int quantity = 0;
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId() == id) {
                quantity = this.stocks.get(i);
                break;
            }
        }
        return quantity;
    }

    /**
     * Add specified amount of stock for a given store.Product object to the inventory.
     *
     * @param product  store.Product object to be added to stock.
     * @param quantity int value for a specified amount of stock to be added.
     */
    public void addStock(Product product, Integer quantity) {
        Integer newQuantity;
        boolean productExist = false;
        if (quantity >= 0) {
            if (this.products.size() != 0) {
                for (int i = 0; i < this.products.size(); i++) {
                    if (this.products.get(i).getId() == (product.getId())) {
                        newQuantity = this.stocks.get(i);
                        newQuantity += quantity;
                        this.stocks.set(i, newQuantity);
                        productExist = true;
                        break;
                    }
                }
            }
            if (!productExist) {
                this.products.add(product);
                this.stocks.add(quantity);
            }
        }
    }

    /**
     * Remove specified amount of stock for a given store.Product ID from the inventory.
     *
     * @param id       int value for the ID number of the product.
     * @param quantity int value for a specified amount of stock to be removed.
     */
    public void removeStock(int id, Integer quantity) {
        Integer newQuantity = 0;
        int index = 0;
        boolean productExist = false;
        if (quantity >= 0) {
            for (int i = 0; i < this.products.size(); i++) {
                if (this.products.get(i).getId() == id) {
                    newQuantity = this.stocks.get(i);
                    newQuantity -= quantity;
                    index = i;
                    if (newQuantity < 0) {
                        newQuantity = 0;
                    }
                    productExist = true;
                    break;
                }
            }
            if (productExist) {
                this.stocks.set(index, newQuantity);
            }
        }
    }

    /**
     * Get information on a store.Product given a store.Product ID.
     *
     * @param id    int value for the ID number of the product.
     * @return      store.Product object
     */
    public Product getProduct(int id) {
        int index = 0;
        boolean productExist = false;
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getId() == id) {
                index = i;
                productExist = true;
            }
        }
        if (productExist) {
            return this.products.get(index);
        } else {
            //INVENTORY > ERROR > store.Product does not exist in inventory.
            return null;
        }
    }

    /**
     * Get ArrayList which tracks the information for the store.Product objects.
     * @return  ArrayList<store.Product> object that track the store.Product objects.
     */
    public ArrayList<Product> getProductList() {
        return this.products;
    }

}
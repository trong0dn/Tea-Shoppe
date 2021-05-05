// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import java.util.ArrayList;

/**
 * This class tracks the state of the product in inventory of the store.
 *
 * @author  Trong Nguyen
 * @version 2.0
 */
public class Inventory {
    /**
     * ArrayList to track the Product objects.
     */
    private final ArrayList<Product> products = new ArrayList<>();

    /**
     * ArrayList to track the amount of stocks.
     */
    private final ArrayList<Integer> stocks = new ArrayList<>();

    /**
     * Initialize the Inventory.
     */
    public Inventory() {
        init();
    }

    /**
     * Set the contents of the Inventory to some default values upon object creation.
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
     * Get amount of stock for a given Product ID number.
     *
     * @param id    int value for the ID number of the product.
     * @return      int value for the quantity of the product in stock.
     */
    public int getQuantity(int id) {
        int quantity = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                quantity = stocks.get(i);
                break;
            }
        }
        return quantity;
    }

    /**
     * Add specified amount of stock for a given Product object to the inventory.
     *
     * @param product  Product object to be added to stock.
     * @param quantity int value for a specified amount of stock to be added.
     */
    public void addStock(Product product, Integer quantity) {
        Integer newQuantity;
        boolean productExist = false;
        if (products.size() != 0) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == (product.getId())) {
                    newQuantity = stocks.get(i);
                    newQuantity += quantity;
                    stocks.set(i, newQuantity);
                    productExist = true;
                    break;
                }
            }
        }
        if (!productExist) {
            products.add(product);
            stocks.add(quantity);
        }
    }

    /**
     * Remove specified amount of stock for a given Product ID from the inventory.
     *
     * @param id       int value for the ID number of the product.
     * @param quantity int value for a specified amount of stock to be removed.
     */
    public void removeStock(int id, Integer quantity) {
        Integer newQuantity = 0;
        int index = 0;
        boolean productExist = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                newQuantity = stocks.get(i);
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
            stocks.set(index, newQuantity);
        }
    }

    /**
     * Get information on a Product given a Product ID.
     *
     * @param id    int value for the ID number of the product.
     * @return      Product object
     */
    public Product getProductInfo(int id) {
        int index = 0;
        boolean productExist = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                index = i;
                productExist = true;
            }
        }
        if (productExist) {
            return products.get(index);
        } else {
            //INVENTORY > ERROR > Product does not exist in inventory.
            return null;
        }
    }

    /**
     * Get ArrayList which tracks the information for the Product objects.
     * @return  ArrayList<Product> object that track the Product objects.
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

}
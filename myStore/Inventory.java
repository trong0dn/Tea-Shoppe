// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;

/**
 * This class tracks the state of the product in inventory of the store.
 *
 * @author  Trong Nguyen
 * @version 4.0
 */
public class Inventory {
    /**
     * ArrayList to track the products.
     */
    private final ArrayList<Product> productCatalog = new ArrayList<>();

    /**
     * ArrayList to track the amount of stocks.
     */
    private final ArrayList<Integer> stocks = new ArrayList<>();

    /**
     * Initialize the inventory.
     */
    public Inventory() {
        init();
    }

    /**
     * Set the contents of the inventory to some default values upon object creation.
     */
    private void init() {
        Product p1 = new Product("jasmine", 0, 1.00);
        Product p2 = new Product("matcha", 1, 2.50);
        Product p3 = new Product("oolong", 2, 3.25);
        Product p4 = new Product("chamomile", 3, 4.50);
        Product p5 = new Product("puerh", 4, 5);
        Product p6 = new Product("black", 5, 5);
        Product p7 = new Product("pekoe", 6, 5);
        Product p8 = new Product("red", 7, 5);
        Product p9 = new Product("yellow", 8, 5);
        Product p10 = new Product("herbal", 9, 5);
        productCatalog.add(p1); stocks.add(100);
        productCatalog.add(p2); stocks.add(100);
        productCatalog.add(p3); stocks.add(100);
        productCatalog.add(p4); stocks.add(100);
        productCatalog.add(p5); stocks.add(100);
        productCatalog.add(p6); stocks.add(100);
        productCatalog.add(p7); stocks.add(100);
        productCatalog.add(p8); stocks.add(100);
        productCatalog.add(p9); stocks.add(100);
        productCatalog.add(p10); stocks.add(100);
    }

    /**
     * Get amount of stock for a given Product ID number.
     *
     * @param id    int : value for the ID number of the product.
     * @return      int : value for the quantity of the product in stock.
     */
    public int getStock(int id) {
        int quantity = 0;
        for (int i = 0; i < this.productCatalog.size(); i++) {
            if (this.productCatalog.get(i).getId() == id) {
                quantity = this.stocks.get(i);
                break;
            }
        }
        return quantity;
    }

    /**
     * Add specified amount of stock for a given Product object to the inventory.
     *
     * @param product  Product :  object to be added to stock.
     * @param quantity int : value for a specified amount of stock to be added.
     */
    public void addStock(Product product, int quantity) {
        int newQuantity;
        boolean productExist = false;
        if (quantity >= 0) {
            if (this.productCatalog.size() != 0) {
                for (int i = 0; i < this.productCatalog.size(); i++) {
                    if (this.productCatalog.get(i).getId() == (product.getId())) {
                        newQuantity = this.stocks.get(i);
                        newQuantity += quantity;
                        this.stocks.set(i, newQuantity);
                        productExist = true;
                        break;
                    }
                }
            }
            if (!productExist) {
                this.productCatalog.add(product);
                this.stocks.add(quantity);
            }
        }
    }

    /**
     * Remove specified amount of stock for a given Product ID from the inventory.
     *
     * @param id       int : value for the ID number of the product.
     * @param quantity int : value for a specified amount of stock to be removed.
     */
    public void removeStock(int id, int quantity) {
        int newQuantity = 0;
        int index = 0;
        boolean productExist = false;
        if (quantity >= 0) {
            for (int i = 0; i < this.productCatalog.size(); i++) {
                if (this.productCatalog.get(i).getId() == id) {
                    newQuantity = this.stocks.get(i);
                    if (quantity >= newQuantity) {
                        newQuantity = 0;
                    }
                    newQuantity -= quantity;
                    index = i;
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
     * Get information on a product given a Product ID.
     *
     * @param id    int : value for the ID number of the product
     * @return      Product : the product object
     */
    public Product getProductInfo(int id) {
        int index = 0;
        boolean productExist = false;
        for (int i = 0; i < this.productCatalog.size(); i++) {
            if (this.productCatalog.get(i).getId() == id) {
                index = i;
                productExist = true;
            }
        }
        if (productExist) {
            return this.productCatalog.get(index);
        } else {
            //INVENTORY > ERROR > store.Product does not exist in inventory.
            return null;
        }
    }

    /**
     * Get ArrayList which tracks the information for the Product objects.
     *
     * @return  ArrayList<Product> : object that track the Product objects
     */
    public ArrayList<Product> getProductCatalog() {
        return this.productCatalog;
    }
}
// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Integer> stocks = new ArrayList<>();

    // Initialize the Inventory
    public Inventory() {
        init();
    }

    // Set the contents of the Inventory to some default values upon object creation.
    private void init() {
        Product p1 = new Product("white", 1, 1.00);
        Product p2 = new Product("green", 2, 2.50);
        Product p3 = new Product("oolong", 3, 3.25);
        Product p4 = new Product("black", 4, 4.50);
        Product p5 = new Product("pu'erh", 5, 5.65);
        products.add(p1); stocks.add(5);
        products.add(p2); stocks.add(11);
        products.add(p3); stocks.add(16);
        products.add(p4); stocks.add(9);
        products.add(p5); stocks.add(10);
    }

    // Get the amount of stock for a given Product ID.
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

    // Add a specified amount of stock for a given Product to the inventory.
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

    // Remove a specified amount of stock for a given Product ID from the inventory.
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
        if (!productExist) {
            // ValueError: Product ID does not exist in Stock. Do nothing.
        } else {
            stocks.set(index, newQuantity);
        }
    }

    // Get information on a Product given a Product ID.
    public Product getProduct(int id) {
        int index = 0;
        boolean productExist = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                index = i;
                productExist = true;
            }
        }
        if (!productExist) {
            return null;
        } else {
            return products.get(index);
        }
    }
}
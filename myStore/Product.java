// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

/**
 * This class stores information about items being sold by the store.
 *
 * @author  Trong Nguyen
 * @version 5.0
 */
public class Product {
    /**
     * The name of the product.
     */
    private final String name;

    /**
     * The ID number of product.
     */
    private final int id;

    /**
     * The price of the product.
     */
    private final double price;

    /**
     * Constructor for product.
     *
     * @param name  String : the name of the product
     * @param id    int : the ID number of the product
     * @param price double : the price of the product
     */
    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    /**
     * Get the name of the product.
     *
     * @return  String : the name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the ID number of the product.
     *
     * @return  int : value of the ID number of the product
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the price of the product.
     *
     * @return  double : value of the price of the product
     */
    public double getPrice() {
        return this.price;
    }
}

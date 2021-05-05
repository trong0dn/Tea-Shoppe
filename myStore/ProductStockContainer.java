// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

/**
 * Interface defining basic behaviour that are a requirement of any class that manage a Product-Stock collection.
 *
 * @author Trong Nguyen
 * @version 5.0
 */
public interface ProductStockContainer {

    /**
     * Get the product quantity from a collection.
     *
     * @param product   Product : the product
     * @return          int : the quantity value of a specific product available in the container
     */
    public int getProductQuantity(Product product);

    /**
     * Add product item to a container.
     *
     * @param product   Product : the product
     * @param quantity  int : the quantity value to be added
     */
    public void addProductQuantity(Product product, int quantity);

    /**
     * Remove product item from a container.
     *
     * @param product   Product : the product
     * @param quantity  int : the quantity value to be removed
     */
    public void removeProductQuantity(Product product, int quantity);

    /**
     * Get number of product items in a container.
     *
     * @return      int : the number of products in the container
     */
    public int getNumOfProducts();
}

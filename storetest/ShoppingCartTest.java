// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package storetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myStore.ShoppingCart;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ShoppingCart class.
 * @author  Trong Nguyen
 * @version 3.0
 */
public class ShoppingCartTest {

    private static ShoppingCart sc;

    /**
     * Initialize objects for testing StoreManager.
     */
    @BeforeEach
    public void init() {
        sc = new ShoppingCart();
        sc.addToCart(0,5);
        sc.addToCart(1,10);
        sc.addToCart(2,15);
        sc.addToCart(3,20);
        sc.addToCart(4,25);
    }

    /**
     * Testing ShoppingCart addToCart method functionalities.
     */
    @Test
    public void testAddToCart() {
        ShoppingCart newCart = new ShoppingCart();
        Integer[][] arr = {};
        assertEquals(arr.length, newCart.getCart().size(),
                "Size of an empty shopping cart is 0");

        Integer[][] a1 = {{0,5},{1,10},{2,15},{3,20},{4,25}};
        assertEquals(a1.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a1[i], sc.getCart().get(i),
                    "Checking initialized array of arrays structure in ShoppingCart object");
        }

        sc.addToCart(7,20);
        assertEquals(6,sc.getCart().size(),
                "Adding a product with a new ID increases Shopping Cart size");

        Integer[][] a2 = {{0,5},{1,10},{2,15},{3,20},{4,25},{7,20}};
        assertEquals(a2.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a2[i], sc.getCart().get(i),
                    "New product items with new ID gets added to the end of array");
        }

        sc.addToCart(0,15);
        Integer[][] a3 = {{0,20},{1,10},{2,15},{3,20},{4,25},{7,20}};
        assertEquals(a3.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a3[i], sc.getCart().get(i),
                    "Adding the item with the same product ID updates the quantity");
        }

        sc.addToCart(5,-15);
        assertEquals(a3.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a3[i], sc.getCart().get(i),
                    "addToCart method does not add negative values");
        }

        sc.addToCart(1,0);
        sc.addToCart(2,0);
        assertEquals(a3.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a3[i], sc.getCart().get(i),
                    "Adding 0 quantity does not change the ShoppingCart");
        }
    }

    /**
     * Testing ShoppingCart removeFromCart method functionalities.
     */
    @Test
    public void testRemoveFromCart() {
        ShoppingCart newCart = new ShoppingCart();
        Integer[][] arr = {};
        assertEquals(arr.length, newCart.getCart().size(),
                "Size of an empty shopping cart is 0");

        newCart.removeFromCart(1,5);
        assertEquals(arr.length, newCart.getCart().size(),
                "Removing from empty shopping cart does not change size of shopping cart");

        Integer[][] a1 = {{0,5},{1,10},{2,15},{3,20},{4,25}};
        assertEquals(a1.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a1[i], sc.getCart().get(i),
                    "Checking initialized array of arrays structure in ShoppingCart object");
        }

        sc.removeFromCart(1,5);
        Integer[][] a2 = {{0,5},{1,5},{2,15},{3,20},{4,25}};
        assertEquals(a2.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a2[i], sc.getCart().get(i),
                    "Removing items with a product ID decreases quantity in the ShoppingCart");
        }

        sc.removeFromCart(1,5);
        Integer[][] a3 = {{0,5},{2,15},{3,20},{4,25}};
        assertEquals(a3.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a3[i], sc.getCart().get(i),
                    "Removing items in the cart, when quantity reaches zero the item is remove completely");
        }

        sc.removeFromCart(4,-10);
        assertEquals(a3.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a3[i], sc.getCart().get(i),
                    "Removing negative quantity does not change the ShoppingCart");
        }

        sc.removeFromCart(7,10);
        assertEquals(a3.length, sc.getCart().size());

        for (int i = 0; i < sc.getCart().size(); i++) {
            assertArrayEquals(a3[i], sc.getCart().get(i),
                    "Removing an item that does not exist in cart does not change the ShoppingCart");
        }
    }

    /**
     * Testing ShoppingCart getCart accessor method functionalities.
     */
    @Test
    public void testGetCart() {
        assertEquals(5, sc.getCart().size(),
                "ShoppingCart size");

        sc.addToCart(0,5);
        assertEquals(5, sc.getCart().size(),
                "Adding an item ID already in the Shopping Cart does not increase the size");

        sc.addToCart(7,5);
        assertEquals(6, sc.getCart().size(),
                "Adding a new item ID to the Shopping Cart does increase the size");

        ShoppingCart newCart = new ShoppingCart();
        assertEquals(0,newCart.getCart().size(),
                "Size of an new empty cart is 0");
    }
}
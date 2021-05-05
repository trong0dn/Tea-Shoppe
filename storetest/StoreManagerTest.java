// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package storetest;

import myStore.Inventory;
import myStore.Product;
import myStore.ShoppingCart;
import myStore.StoreManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StoreManager class.
 * @author  Trong Nguyen
 * @version 3.0
 */
public class StoreManagerTest {

    private static StoreManager sm;
    private static ShoppingCart sc;
    private static Product p1;
    private static Product p2;
    private static Product p3;
    private static Product p4;
    private static Product p5;
    private static Product p6;

    /**
     * Initialize objects for testing StoreManager.
     */
    @BeforeEach
    public void init() {
        sm = new StoreManager();
        sc = new ShoppingCart();
        p1 = new Product("jasmine", 0, 1.00);
        p2 = new Product("matcha", 1, 2.50);
        p3 = new Product("oolong", 2, 3.25);
        p4 = new Product("chamomile", 3, 4.50);
        p5 = new Product("pu'erh", 4, 5.65);
        p6 = new Product("herbal", 5, 2.00);
    }

    /**
     * Testing StoreManager checkStock method.
     */
    @Test
    public void testCheckStock() {
        assertAll("Checking the initialize stock inventory owned by StoreManager",
                () -> assertEquals(5, sm.checkStock(p1)),
                () -> assertEquals(10, sm.checkStock(p2)),
                () -> assertEquals(15, sm.checkStock(p3)),
                () -> assertEquals(20, sm.checkStock(p4)),
                () -> assertEquals(25, sm.checkStock(p5)),
                () -> assertEquals(0, sm.checkStock(p6))
        );
    }

    /**
     * Testing StoreManager processOrder method.
     */
    @Test
    public void testProcessOrder() {
        ShoppingCart emptyCart = new ShoppingCart();
        sc.addToCart(0,1);
        sc.addToCart(1,1);
        sc.addToCart(2,1);
        sc.addToCart(3,1);
        sc.addToCart(4,1);
        assertAll("Comparing processOrder with expect value of order",
                () -> assertEquals(16.9,sm.processOrder(sc),0.001),
                () -> assertEquals(0,sm.processOrder(emptyCart),0.001)
        );
    }

    /**
     * Testing StoreManager assignNewCartID method for unique cartID used to identify the user of the system
     * assigned to per store.StoreView for a new, unique store.ShoppingCart object.
     */
    @Test
    public void testAssignNewCartID() {
        assertAll("Checking for unique cartID via counter incrementation strategy starting at 0",
                () -> assertEquals(sm.getCartArrayList().size(), sm.assignNewCartID()),
                () -> assertEquals(sm.getCartArrayList().size(), sm.assignNewCartID()),
                () -> assertEquals(sm.getCartArrayList().size(), sm.assignNewCartID()),
                () -> assertEquals(3, sm.assignNewCartID()),
                () -> assertEquals(4, sm.assignNewCartID()),
                () -> assertEquals(5, sm.assignNewCartID())
        );
    }

    /**
     * Testing StoreManager getInventory accessor method.
     */
    @Test
    public void testGetInventory() {
        Inventory i = new Inventory();

        for (int j = 0; j < sm.getInventory().getProductList().size(); j++) {
            assertSame(i.getProductList().get(j).getId(), sm.getInventory().getProductList().get(j).getId());
        }

        assertNotSame(i, sm.getInventory(),"Different instances of inventory are not the same");
    }

    /**
     * Testing StoreManager getCartArrayList accessor method.
     */
    @Test
    public void testGetCartArrayList() {
        assertAll("No shoppingCarts in empty CartArrayList",
                () -> assertEquals(0, sm.getCartArrayList().size())
        );

        sm.assignNewCartID();
        assertAll("Assignment of one shoppingCart in CartArrayList",
                () -> assertEquals(1, sm.getCartArrayList().size())
        );

        sm.assignNewCartID();
        assertAll("Assignment of second shoppingCart in CartArrayList",
                () -> assertEquals(2, sm.getCartArrayList().size())
        );
    }

    /**
     * Testing StoreManager getShoppingCart accessor method.
     */
    @Test
    public void testGetShoppingCart() {
        ArrayList<Integer[]> emptyArray = new ArrayList<>();

        assertAll("Testing getShoppingCart",
                () -> assertEquals(0, sm.getShoppingCart().getCart().size(),
                        "Checking the size of the empty ShoppingCart"),
                () -> assertIterableEquals(emptyArray, sm.getShoppingCart().getCart(),
                        "Two different empty ShoppingCarts"),
                () -> assertNotSame(sc.getCart(), sm.getShoppingCart().getCart(),
                        "Different instances of an empty Shopping Cart are not the same")
        );

        sm.getShoppingCart().addToCart(0,5);
        Integer[][] arr = {{0,5}};

        assertEquals(arr.length, sm.getShoppingCart().getCart().size(),
                "Adding an item to shopping cart increases size of shopping cart owned by StoreManager");

        for (int i = 0; i < sm.getShoppingCart().getCart().size(); i++) {
            assertArrayEquals(arr[i], sm.getShoppingCart().getCart().get(i));
        }

        sm.getShoppingCart().addToCart(1,10);
        sm.getShoppingCart().addToCart(2,15);
        Integer[][] newArr = {{0,5},{1,10},{2,15}};

        assertEquals(newArr.length, sm.getShoppingCart().getCart().size(),
                "Add new items to shopping cart increases shopping cart owned by StoreManager");
        for (int i = 0; i < sm.getShoppingCart().getCart().size(); i++) {
            assertArrayEquals(newArr[i], sm.getShoppingCart().getCart().get(i));
        }
    }
}
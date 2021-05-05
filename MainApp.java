// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

public class MainApp {
    public static void main(String[] args) {

        StoreManager m1 = new StoreManager();

        int[][] cart = {
                {1,5},
                {2,10},
                {3,15}
        };

        System.out.println(m1.processOrder(cart));

        // Initialize new Shopping cart.
        /*
        int[][] cart = {
                {1,5},
                {2,10},
                {3,15}
        };
        */
    }
}

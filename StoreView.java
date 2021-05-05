// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class manages the GUI for the store.
 *
 * @author  Trong Nguyen
 * @version 2.0
 */
public class StoreView {
    /**
     * Create an instance of StoreManager.
     */
    private final StoreManager storeManager;

    /**
     * Initialized int cartID used to identify the user of the system.
     */
    private final int cartId;

    /**
     * Constructor for StoreView.
     *
     * @param storeManager  StoreManager object to manage information to the StoreView class.
     * @param cartId        int value unique cartID for the ShoppingCart instance.
     */
    public StoreView(StoreManager storeManager, int cartId) {
        this.storeManager = storeManager;
        this.cartId = cartId;
    }

    /**
     * Graphic user-interface panel for HELP menu information for the StoreView class.
     */
    private void help() {
        System.out.println("\\-------------------------HELP------------------------/");
        System.out.println("browse   - to browse our inventory selection");
        System.out.println("add      - to add products to shopping cart");
        System.out.println("remove   - to remove products from shopping cart");
        System.out.println("view     - to view products in your shopping cart");
        System.out.println("checkout - to process orders in your shopping cart");
        System.out.println("quit     - to deactivate the storeview");
        System.out.println();
    }

    /**
     * Graphic user-interface panel for BROWSE menu information for the StoreView class to view the Products in stock
     * for the Inventory.
     */
    private void browse() {
        System.out.println("\\------------------------BROWSE-----------------------/");
        System.out.println("Type 'help' for a list of commands.\n");
        System.out.println("Stock | Product Name | Unit Price");
        for (Product p : storeManager.getInventory().getProducts()) {
            int stock = storeManager.checkStock(p);
            String name = p.getName();
            double price = p.getPrice();
            System.out.printf("%5d | %12s | $%.2f \n", stock, name, price);
        }
        System.out.println();
    }

    /**
     * Graphic user-interface panel for VIEWCART information for the StoreView class to view the Products in the
     * ShoppingCart.
     *
     * @param cartId    int value for a unique cartID for the ShoppingCart object.
     */
    private void viewCart(int cartId) {
        System.out.println("\\-----------------------VIEWCART----------------------/");
        System.out.println("Product ID | Product Name | Quantity | Price");
        for (Integer[] p : storeManager.getCartArrayList().get(cartId).getCart()) {
            int id = p[0];
            String name = storeManager.getInventory().getProductInfo(id).getName();
            int quantity = p[1];
            double price = storeManager.getInventory().getProductInfo(id).getPrice();
            System.out.printf("%10d | %12s | %8d | $%.2f \n", id, name, quantity, price);
        }
        System.out.println();
    }

    /**
     * Graphic user-interface panel for ADD menu information for the StoreView class to add Products to the shopping
     * cart.
     *
     * @param cartId    int value for a unique cartID for the ShoppingCart object.
     */
    private void addToCart(int cartId) {
        System.out.println("\\-------------------------ADD-------------------------/");
        System.out.println("Stock | Product Name | Unit Price | Option");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        HashMap<Integer, Integer> optionId = new HashMap<>();
        for (Product p : storeManager.getInventory().getProducts()) {
            int stock = storeManager.checkStock(p);
            String name = p.getName();
            double price = p.getPrice();
            int id = p.getId();

            System.out.printf("%5d | %12s |    $%.2f   | (%d) \n", stock, name, price, option);
            // Store the option key with the id value.
            optionId.put(option, id);
            option++;
        }
        System.out.print("Option: ");
        int optionAdd = sc.nextInt();
        System.out.print("Quantity: ");
        int quantity = sc.nextInt();

        Integer id = null;
        boolean correctInput = false;
        if (optionId.containsKey(optionAdd)) {
            id = optionId.get(optionAdd);
            // Check if the ID matches a Product ID in the inventory.
            if (id == storeManager.getInventory().getProductInfo(id).getId()) {
                // Check if sufficient quantity exists in the inventory.
                if (quantity <= storeManager.getInventory().getQuantity(id)) {
                    correctInput = true;
                } else {
                    System.out.println("STOREVIEW > ERROR > Not enough stock in inventory.");
                }
            }
        } else {
            System.out.println("STOREVIEW > ERROR > Option selection does not exist in inventory.");
        }
        if (correctInput) {
            storeManager.getInventory().removeStock(id, quantity);
            storeManager.getCartArrayList().get(cartId).addToCart(id, quantity);
        }
        System.out.println();
    }

    /**
     * Graphic user-interface panel for REMOVE menu information for the StoreView class to remove Products from the
     * shopping cart.
     *
     * @param cartId    int value for a unique cartID for the ShoppingCart object.
     */
    private void removeFromCart(int cartId) {
        System.out.println("\\------------------------REMOVE-----------------------/");
        System.out.println("Stock | Product Name | Unit Price | Option");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        HashMap<Integer, Integer> optionId = new HashMap<>();
        HashMap<Integer, Integer> cartStock = new HashMap<>();
        for (Integer[] p : storeManager.getCartArrayList().get(cartId).getCart()) {
            int id = p[0];
            int stock = p[1];
            String name = storeManager.getInventory().getProductInfo(id).getName();
            double price = storeManager.getInventory().getProductInfo(id).getPrice();

            System.out.printf("%5d | %12s |    $%.2f   | (%d) \n", stock, name, price, option);
            // Store the option key with the product id value.
            optionId.put(option, id);
            // Store the option key with the product stock value.
            cartStock.put(option, stock);
            option++;
        }
        System.out.print("Option: ");
        int optionRemove = sc.nextInt();
        System.out.print("Quantity: ");
        int quantity = sc.nextInt();

        Integer id = null;
        boolean correctInput = false;
        if (optionId.containsKey(optionRemove)) {
            id = optionId.get(optionRemove);
            // Check if the ID matches a Product ID in the inventory.
            if (id == storeManager.getInventory().getProductInfo(id).getId()) {
                int stock = cartStock.get(optionRemove);
                // Check if quantity removed from shopping cart appropriate.
                if (quantity > stock) {
                    quantity = stock;
                }
                correctInput = true;
            }
        } else {
            System.out.println("STOREVIEW > ERROR > Option selection does not exist in cart.");
        }
        if (correctInput) {
            storeManager.getCartArrayList().get(cartId).removeFromCart(id, quantity);
            storeManager.getInventory().addStock(storeManager.getInventory().getProductInfo(id), quantity);
        }
        System.out.println();
    }

    /**
     * Graphic user-interface panel for CHECKOUT menu information for the StoreView class to process the total price
     * of Products from the shopping cart. The StoreView is deactivated after checkout.
     *
     * @param cartId    int value for a unique cartID for a given ShoppingCart object.
     */
    private void checkout(int cartId) {
        viewCart(cartId);
        System.out.println("\\-----------------------CHECKOUT----------------------/");
        double totalPrice = storeManager.processOrder(storeManager.getCartArrayList().get(cartId));
        System.out.printf(">>> Total Purchase: $%.2f \n", totalPrice);
        System.out.println();
    }

    /**
     * Graphic user-interface panel for QUIT menu information for the StoreView class to deactivate a StoreView.
     * If users quits, then all Products in the ShoppingCart is returned to the Inventory.
     *
     * @param cartId    int value for a unique cartID for a given ShoppingCart object.
     */
    private void quit(int cartId) {
        System.out.println("\\-------------------------QUIT------------------------/");
        for (Integer[] p : storeManager.getCartArrayList().get(cartId).getCart()) {
            int id = p[0];
            int stock = p[1];
            if (id == storeManager.getInventory().getProductInfo(id).getId()) {
                storeManager.getShoppingCart().removeFromCart(id, stock);
                storeManager.getInventory().addStock(storeManager.getInventory().getProductInfo(id), stock);
            }
        }
        System.out.println("QUIT CART >>> " + cartId);
    }

    /**
     * Graphic user-interface for the store system to be textually display in the console.
     *
     * @return  boolean false, for the displayGUI to be terminated.
     */
    public boolean displayGUI() {
        System.out.println("CART >>> " + cartId);
        System.out.println("Enter a command...");

        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();

        System.out.println("|--------------------THE TEA SHOPPE--------------------|");
        switch (choice) {
            case "help":
                help();
                break;
            case "browse":
                browse();
                break;
            case "add":
                addToCart(cartId);
                break;
            case "remove":
                removeFromCart(cartId);
                break;
            case "view":
                viewCart(cartId);
                break;
            case "checkout":
                checkout(cartId);
                return true;
            case "quit":
                quit(cartId);
                return true;
            default:
                System.out.println("STOREVIEW > ERROR > INVALID COMMAND\n");
            }
        return false;
    }

    /**
     * main method for the entry point of the program which the runtime system passes information to the application.
     *
     * @param args  argument for an array of elements of type String.
     */
    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv2 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv3 = new StoreView(sm, sm.assignNewCartID());
        ArrayList<StoreView> users = new ArrayList<>();

        users.add(sv1);
        users.add(sv2);
        users.add(sv3);

        int activeSV = users.size();

        Scanner sc = new Scanner(System.in);
        while (activeSV > 0) {
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = sc.nextInt();
            if (choice < users.size() && choice >= 0) {
                if (users.get(choice) != null) {
                    String chooseAnother = "";
                    while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")) {
                        // this implementation of displayGUI waits for input and displays the page
                        // corresponding to the user's input. it does this once, and then returns
                        // true if the user entered 'checkout' or 'quit'.
                        if (users.get(choice).displayGUI()) {
                            users.set(choice, null);
                            activeSV--;
                            break;
                        }
                        System.out.print("GO TO ANOTHER STOREVIEW? (y) >>> ");
                        chooseAnother = sc.next();
                    }
                } else {
                    System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
                }
            } else {
                System.out.printf("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]%n",
                        0, users.size() - 1);
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }
}

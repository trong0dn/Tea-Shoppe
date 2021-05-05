// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class manages the GUI for the store.
 *
 * @author  Trong Nguyen
 * @version 3.0
 */
public class StoreView {
    /**
     * Create an instance of store.StoreManager.
     */
    private final StoreManager storeManager;

    /**
     * Initialized int cartID used to identify the user of the system.
     */
    private final int cartId;

    /**
     * Constructor for store.StoreView.
     *
     * @param storeManager  store.StoreManager object to manage information to the store.StoreView class.
     * @param cartId        int value unique cartID for the store.ShoppingCart instance.
     */
    public StoreView(StoreManager storeManager, int cartId) {
        this.storeManager = storeManager;
        this.cartId = cartId;
    }

    /**
     * Graphic user-interface panel for HELP menu information for the store.StoreView class.
     */
    private void help() {
        System.out.println("\\-------------------------HELP------------------------/");
        System.out.println("browse   - to browse our inventory selection");
        System.out.println("add      - to add products to shopping cart");
        System.out.println("remove   - to remove products from shopping cart");
        System.out.println("view     - to view products in your shopping cart");
        System.out.println("checkout - to process orders in your shopping cart");
        System.out.println("quit     - to deactivate the storeview");
        System.out.println("exit     - to exit the program");
        System.out.println();
    }

    /**
     * Graphic user-interface panel for BROWSE menu information for the store.StoreView class
     * to view the Products in stock for the store.Inventory.
     */
    private void browse() {
        System.out.println("\\------------------------BROWSE-----------------------/");
        System.out.println("Type 'help' for a list of commands.\n");
        System.out.println("Stock | store.Product Name | Unit Price");
        for (Product p : this.storeManager.getInventory().getProductList()) {
            int stock = this.storeManager.checkStock(p);
            String name = p.getName();
            double price = p.getPrice();
            System.out.printf("%5d | %12s | $%.2f \n", stock, name, price);
        }
        System.out.println();
    }

    /**
     * Graphic user-interface panel for VIEWCART information for the store.StoreView class
     * to view the Products in the store.ShoppingCart.
     *
     * @param cartId    int value for a unique cartID for the store.ShoppingCart object.
     */
    private void viewCart(int cartId) {
        System.out.println("\\-----------------------VIEWCART----------------------/");
        System.out.println("store.Product ID | store.Product Name | Quantity | Price");
        for (Integer[] p : this.storeManager.getCartArrayList().get(cartId).getCart()) {
            int id = p[0];
            String name = this.storeManager.getInventory().getProduct(id).getName();
            int quantity = p[1];
            double price = this.storeManager.getInventory().getProduct(id).getPrice();
            System.out.printf("%10d | %12s | %8d | $%.2f \n", id, name, quantity, price);
        }
        System.out.println();
    }

    /**
     * Graphic user-interface panel for ADD menu information for the store.StoreView class
     * to add Products to the shopping cart.
     *
     * @param cartId    int value for a unique cartID for the store.ShoppingCart object.
     */
    private void addToCart(int cartId) {
        System.out.println("\\-------------------------ADD-------------------------/");
        System.out.println("Stock | store.Product Name | Unit Price | Option");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        HashMap<Integer, Integer> optionId = new HashMap<>();
        for (Product p : this.storeManager.getInventory().getProductList()) {
            int stock = this.storeManager.checkStock(p);
            String name = p.getName();
            double price = p.getPrice();
            int id = p.getId();

            System.out.printf("%5d | %12s |    $%.2f   | (%d) \n", stock, name, price, option);
            // Store the option key with the id value.
            optionId.put(option, id);
            option++;
        }

        Integer id = null;
        Integer quantity = null;

        boolean validOption = false;
        while (!validOption) {
            try {
                System.out.print("Option: ");
                int optionAdd = sc.nextInt();
                // Check for existing key-value pair, then retrieve value.
                if (optionId.containsKey(optionAdd)) {
                    id = optionId.get(optionAdd);
                    validOption = true;
                } else {
                    System.out.printf("STOREVIEW > ERROR > Option selection does not exist in inventory.\n" +
                            "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, option - 1);
                }
            } catch (InputMismatchException e) {
                System.out.printf("STOREVIEW > InputMismatchException > Option selection does not exist in cart.\n" +
                        "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, option - 1);
            } finally {
                sc.nextLine();
            }
        }

        boolean validQuantity = false;
        while (!validQuantity) {
            try {
                System.out.print("Quantity: ");
                quantity = sc.nextInt();
                // Check if the ID matches a store.Product ID in the inventory.
                if (id == this.storeManager.getInventory().getProduct(id).getId()) {
                    // Check if sufficient quantity exists in the inventory.
                    if (quantity <= this.storeManager.getInventory().getQuantity(id) && quantity >= 0) {
                        validQuantity = true;
                    } else {
                        System.out.printf("STOREVIEW > ERROR > INVALID STOCK AMOUNT.\n" +
                                "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, this.storeManager.getInventory().getQuantity(id));
                    }
                }
            } catch (InputMismatchException e) {
                System.out.printf("STOREVIEW > InputMismatchException > INVALID STOCK AMOUNT.\n" +
                        "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, this.storeManager.getInventory().getQuantity(id));
            } finally {
                sc.nextLine();
            }
        }

        try {
            this.storeManager.getInventory().removeStock(id, quantity);
            this.storeManager.getCartArrayList().get(cartId).addToCart(id, quantity);
        } catch (Exception e) {
            System.out.println("STOREVIEW > Exception > addToCart");
            e.printStackTrace();
        } finally {
            System.out.println();
        }
    }

    /**
     * Graphic user-interface panel for REMOVE menu information for the store.StoreView class
     * to remove Products from the shopping cart.
     *
     * @param cartId    int value for a unique cartID for the store.ShoppingCart object.
     */
    private void removeFromCart(int cartId) {
        System.out.println("\\------------------------REMOVE-----------------------/");
        System.out.println("Stock | store.Product Name | Unit Price | Option");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        HashMap<Integer, Integer> optionId = new HashMap<>();
        HashMap<Integer, Integer> cartStock = new HashMap<>();
        for (Integer[] p : this.storeManager.getCartArrayList().get(cartId).getCart()) {
            int id = p[0];
            int stock = p[1];
            String name = this.storeManager.getInventory().getProduct(id).getName();
            double price = this.storeManager.getInventory().getProduct(id).getPrice();

            System.out.printf("%5d | %12s |    $%.2f   | (%d) \n", stock, name, price, option);
            // Store the option key with the product id value.
            optionId.put(option, id);
            // Store the option key with the product stock value.
            cartStock.put(option, stock);
            option++;
        }

        Integer id = null;
        Integer quantity = null;
        Integer stock = null;

        boolean validOption = false;
        while (!validOption) {
            try {
                System.out.print("Option: ");
                int optionRemove = sc.nextInt();
                // Check for existing key-value pair, then retrieve value.
                if (optionId.containsKey(optionRemove)) {
                    id = optionId.get(optionRemove);
                    // Check if the ID matches a store.Product ID in the inventory.
                    if (id == this.storeManager.getInventory().getProduct(id).getId()) {
                        stock = cartStock.get(optionRemove);
                        validOption = true;
                    }
                } else {
                    System.out.printf("STOREVIEW > ERROR > Option selection does not exist in cart.\n" +
                            "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, option - 1);
                }
            } catch (InputMismatchException e) {
                System.out.printf("STOREVIEW > InputMismatchException > Option selection does not exist in cart.\n" +
                        "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, option - 1);
            } finally {
                sc.nextLine();
            }
        }

        boolean validQuantity = false;
        while (!validQuantity) {
            try {
                System.out.print("Quantity: ");
                quantity = sc.nextInt();
                // Check if sufficient quantity exists in shopping cart.
                if (quantity >= 0 && quantity <= stock) {
                    validQuantity = true;
                } else {
                    System.out.printf("STOREVIEW > ERROR > INVALID QUANTITY\n" +
                                    "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, stock);
                }
            } catch (InputMismatchException e) {
                System.out.printf("STOREVIEW > InputMismatchException > INVALID QUANTITY\n" +
                        "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, stock);
            } finally {
                sc.nextLine();
            }
        }

        try {
            this.storeManager.getCartArrayList().get(cartId).removeFromCart(id, quantity);
            this.storeManager.getInventory().addStock(this.storeManager.getInventory().getProduct(id), quantity);
        } catch (Exception e) {
            System.out.println("STOREVIEW > Exception > removeFromCart");
            e.printStackTrace();
        } finally {
            System.out.println();
        }
    }

    /**
     * Graphic user-interface panel for CHECKOUT menu information for the store.StoreView class
     * to process the total price of Products from the shopping cart. The store.StoreView is
     * deactivated after checkout.
     *
     * @param cartId    int value for a unique cartID for a given store.ShoppingCart object.
     */
    private void checkout(int cartId) {
        viewCart(cartId);
        System.out.println("\\-----------------------CHECKOUT----------------------/");
        double totalPrice = this.storeManager.processOrder(this.storeManager.getCartArrayList().get(cartId));
        System.out.printf(">>> Total Purchase: $%.2f \n", totalPrice);
        System.out.println();
    }

    /**
     * Graphic user-interface panel for QUIT menu information for the store.StoreView class
     * to deactivate a store.StoreView. If users quits, then all Products in the store.ShoppingCart
     * is returned to the store.Inventory.
     *
     * @param cartId    int value for a unique cartID for a given store.ShoppingCart object.
     */
    private void quit(int cartId) {
        System.out.println("\\-------------------------QUIT------------------------/");
        for (Integer[] p : this.storeManager.getCartArrayList().get(cartId).getCart()) {
            int id = p[0];
            int stock = p[1];
            if (id == this.storeManager.getInventory().getProduct(id).getId()) {
                this.storeManager.getShoppingCart().removeFromCart(id, stock);
                this.storeManager.getInventory().addStock(this.storeManager.getInventory().getProduct(id), stock);
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
        System.out.println("CART >>> " + this.cartId);
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
                addToCart(this.cartId);
                break;
            case "remove":
                removeFromCart(this.cartId);
                break;
            case "view":
                viewCart(this.cartId);
                break;
            case "checkout":
                checkout(this.cartId);
                return true;
            case "quit":
                quit(this.cartId);
                return true;
            case "exit":
                System.exit(0);
            default:
                System.out.println("STOREVIEW > ERROR > INVALID COMMAND");
                System.out.println("Enter command \"help\" for list of commands\n");
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

            int choice = -1;
            boolean inputValid = false;
            while (!inputValid) {
                try {
                    System.out.print("CHOOSE YOUR STOREVIEW >>> ");
                    choice = sc.nextInt();
                    inputValid = true;
                } catch (InputMismatchException e) {
                    System.out.printf("MAIN > InputMismatchException > BAD CHOICE\n" +
                                    "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, users.size() - 1);
                } finally {
                    sc.nextLine();
                }
            }

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
                System.out.printf("MAIN > ERROR > BAD CHOICE\n" +
                                "PLEASE CHOOSE IN RANGE [%d, %d]\n", 0, users.size() - 1);
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");
    }
}

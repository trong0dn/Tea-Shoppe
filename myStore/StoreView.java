// STUDENT NAME: Trong Nguyen
// STUDENT NUMBER: 100848232

package myStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class manages the GUI for the store.
 *
 * @author  Trong Nguyen
 * @version 5.0
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
     * Main JFrame Swing component.
     */
    private final JFrame frame;

    /**
     * ArrayList of JLabel that stores the updated stock labels display on each product panel.
     */
    private final ArrayList<JLabel> stockLabels = new ArrayList<>();

    /**
     * Constructor for StoreView.
     *
     * @param storeManager  StoreManager : object to manage information to the StoreView class
     * @param cartId        int : value unique cartID for the ShoppingCart instance
     */
    public StoreView(StoreManager storeManager, int cartId) {
        this.storeManager = storeManager;
        this.cartId = cartId;
        this.frame = new JFrame("Client StoreView");
    }

    /**
     * ArrayList to store the JLabels that holds the images for each product panel.
     *
     * @return  ArrayList<JLabel> : list of JLabels for images
     */
    private ArrayList<JLabel> grabImages() {
        ArrayList<JLabel> imageList = new ArrayList<>();
        ArrayList<String> productNameList = new ArrayList<>();
        String productName;

        // Image filename has to be the name of the product in the inventory.
        for (Product p : this.storeManager.getProductCatalog()) {
            productName = p.getName();
            productNameList.add(productName);
        }

        for (String s : productNameList) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResource("/Images/" + s + ".jpg"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert image != null;
            Image scaledImage = image.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            imageList.add(imageLabel);
        }
        return imageList;
    }

    /**
     * Update the labels displaying the stock for each product.
     *
     * @param pid   int : product ID value
     */
    private void updateStockLabel(int pid) {
        int stock = this.storeManager.getStock(pid);
        double price = this.storeManager.getPrice(pid);
        stockLabels.get(pid).setText(String.format("($%.2f) - Stock: %d", price, stock));
    }

    /**
     * Generate a JButton that models adding a product to the shopping cart.
     *
     * @param pid   int : product ID value
     * @return      JButton : a button
     */
    private JButton addButton(int pid) {
        JButton addButton = new JButton("+");

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                storeManager.addToCart(pid, 1);
                updateStockLabel(pid);
            }
        });
        return addButton;
    }

    /**
     * Generate a JButton that models removing a product to the shopping cart.
     *
     * @param pid   int : product ID value
     * @return      JButton : a button
     */
    private JButton removeButton(int pid) {
        JButton removeButton = new JButton("-");

        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                storeManager.removeFromCart(pid,1);
                updateStockLabel(pid);
            }
        });
        return removeButton;
    }

    /**
     * Generate a JButton that models quitting the store view.
     *
     * @return   JButton : a button
     */
    private JButton quitButton() {
        JButton quitButton = new JButton("Quit");

        quitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Quit",
                        JOptionPane.DEFAULT_OPTION) == JOptionPane.OK_OPTION) {
                    storeManager.clear();
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        return quitButton;
    }

    /**
     * Generate the String representation to be displayed by the view cart functionality.
     *
     * @return  String : the String to be displayed
     */
    private String viewCartString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\\-------------------------VIEW CART-----------------------/\n");
        sb.append("Product ID | Product Name | Quantity | Price\n");
        for (Integer[] p : this.storeManager.getShoppingCart()) {
            int id = p[0];
            String name = this.storeManager.getName(id);
            int quantity = p[1];
            double price = this.storeManager.getPrice(id);
            sb.append(String.format("%-18d | %-18s | %-14d | $%.2f%n", id, name, quantity, price));
        }
        sb.append("\\----------------------------------------------------------------/\n");
        return sb.toString();
    }

    /**
     * Generate JButton that models viewing the shopping cart details.
     *
     * @return  JButton : a button
     */
    private JButton viewCartButton() {
        ImageIcon cartIcon = new ImageIcon(getClass().getResource("/Images/cartIcon.jpg"));
        Image cartImage = cartIcon.getImage();
        Image newCartImage = cartImage.getScaledInstance(20,20, Image.SCALE_DEFAULT);
        cartIcon = new ImageIcon(newCartImage);
        JButton viewCartButton = new JButton("View Cart", cartIcon);

        viewCartButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String viewCartString = viewCartString();
                JOptionPane.showMessageDialog(frame, viewCartString, "My Cart", JOptionPane.PLAIN_MESSAGE);
            }

        });
        return viewCartButton;
    }


    /**
     * Generate the String representation to be displayed by the checkout functionality.
     *
     * @return  String : the String to be displayed
     */
    private String checkoutString() {
        StringBuilder sb = new StringBuilder();
        sb.append(viewCartString());
        double totalPrice = this.storeManager.processOrder();
        sb.append(String.format(">>> Total Purchase: $%.2f \n", totalPrice));
        return sb.toString();
    }

    /**
     * Generate JButton that models viewing the checkout action functionality.
     *
     * @return  JButton : a button
     */
    private JButton checkoutButton() {
        JButton checkoutButton = new JButton("Checkout");

        checkoutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String checkoutString = checkoutString();
                if (JOptionPane.showConfirmDialog(frame, checkoutString, "Checkout", JOptionPane.DEFAULT_OPTION)
                        == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        return checkoutButton;
    }

    /**
     * Graphic user-interface for the store system to be display using Java SWING interface.
     */
    public boolean displayGUI() {
        this.frame.setPreferredSize(new Dimension(1000,800));
        ImageIcon teaIcon = new ImageIcon(getClass().getResource("/Images/teaIcon.jpg"));
        this.frame.setIconImage(teaIcon.getImage());

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel();
        JPanel bodyPanel = new JPanel(new GridLayout());
        JPanel eastPanel = new JPanel(new BorderLayout());
        JPanel footerPanel = new JPanel();

        JLabel headerLabel = new JLabel(String.format("The Tea Shoppe! (ID: %d)", cartId));
        headerLabel.setFont(new Font("MV Boli", Font.BOLD, 20));

        headerPanel.setBackground(new Color(150, 155, 120));
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,true));
        headerPanel.add(headerLabel);

        bodyPanel.setLayout(new GridLayout(0,2));
        bodyPanel.setBackground(new Color(100, 100, 150));
        eastPanel.setBackground(new Color(60, 80, 135));
        footerPanel.setBackground(new Color(195, 100, 100));

        // Create ArrayList to holds some components
        ArrayList<JPanel> productPanels = new ArrayList<>();
        ArrayList<JButton> addButtons = new ArrayList<>();
        ArrayList<JButton> subButtons = new ArrayList<>();
        ArrayList<JLabel> imageGrabbed = grabImages();
        ArrayList<JPanel> buttonForPanels = new ArrayList<>();

        for (int i = 0; i < storeManager.getProductCatalog().size(); i++) {
            // Create label for each of the product panels to display stock and price
            JLabel stockLabel = new JLabel();
            stockLabels.add(stockLabel);

            // Create panels for each product
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(100, 100, 150));
            productPanels.add(panel);

            // Create add and remove buttons
            addButtons.add(addButton(i));
            subButtons.add(removeButton(i));

            // Add both buttons to panel
            JPanel buttonPanel = new JPanel();
            buttonForPanels.add(buttonPanel);
            buttonForPanels.get(i).add(addButtons.get(i));
            buttonForPanels.get(i).add(subButtons.get(i));
        }


        GridBagConstraints c = new GridBagConstraints();
        for (int i = 0; i < this.storeManager.getProductCatalog().size(); i++) {
            // Add the label for the stock amount and price on top
            c.gridx = 0;
            c.gridy = 0;
            updateStockLabel(i);
            productPanels.get(i).add(this.stockLabels.get(i),c);

            // Add the image for the product in the middle area
            c.insets = new Insets(2,2,2,2);
            c.gridx = 0;
            c.gridy = 1;
            productPanels.get(i).add(imageGrabbed.get(i), c);

            // Add the remove and add product buttons below
            c.gridx = 0;
            c.gridy = 2;
            productPanels.get(i).add(buttonForPanels.get(i),c);

            // Add a titled line border with the product name
            String name = this.storeManager.getName(i);
            Border border = BorderFactory.createLineBorder(Color.BLACK);
            TitledBorder title;
            title = BorderFactory.createTitledBorder(border, name.toUpperCase(Locale.ROOT));
            productPanels.get(i).setBorder(title);

            // Add each of the product panels to the body panel
            bodyPanel.add(productPanels.get(i));
        }

        // Add ScrollPane for the bodyPanel
        JScrollPane bodyScrollPane = new JScrollPane(bodyPanel);
        mainPanel.add(bodyScrollPane, BorderLayout.CENTER);

        JPanel upperEastPanel = new JPanel(new GridLayout(0,1));
        upperEastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5,false));

        // Add buttons for view cart, checkout, and quit to the east panel
        upperEastPanel.add(viewCartButton());
        upperEastPanel.add(checkoutButton());
        upperEastPanel.add(quitButton());

        // Display some text for advertisement
        JPanel lowerEastPanel = new JPanel(new BorderLayout());
        JEditorPane salesText = new JEditorPane();
        salesText.setText("New TEA Products in stock!\n\nAll TEA items marked down at least 25%");
        salesText.setFont(new Font("MV Boli", Font.PLAIN, 16));
        salesText.setSize(new Dimension(250,150));

        // Display an image for advertisement
        ImageIcon saleIcon = new ImageIcon(getClass().getResource("/Images/saleIcon.jpg"));
        Image saleImage = saleIcon.getImage();
        Image newSaleImage = saleImage.getScaledInstance(250,250, Image.SCALE_DEFAULT);
        saleIcon = new ImageIcon(newSaleImage);
        JLabel salesImage = new JLabel(saleIcon);

        lowerEastPanel.add(salesText, BorderLayout.NORTH);
        lowerEastPanel.add(salesImage, BorderLayout.SOUTH);

        eastPanel.add(upperEastPanel, BorderLayout.NORTH);
        eastPanel.add(lowerEastPanel, BorderLayout.SOUTH);

        headerPanel.setPreferredSize(new Dimension(250, 50));
        eastPanel.setPreferredSize(new Dimension(250, 100));
        footerPanel.setPreferredSize(new Dimension(250, 10));

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyScrollPane, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);

        this.frame.add(mainPanel);
        this.frame.pack();

        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?")
                        == JOptionPane.OK_OPTION) {
                    storeManager.clear();
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        this.frame.setVisible(true);
        return true;
    }

    /**
     * main method for the entry point of the program which the runtime system passes information to the application.
     *
     * @param args  argument for an array of elements of type String.
     */
    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv = new StoreView(sm, sm.assignNewCartID());
        sv.displayGUI();
    }
}

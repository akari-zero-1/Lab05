package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

public class StoreManagerScreen extends JFrame {
    private Store store;

    // Constructor
    public StoreManagerScreen(Store store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorthPanel(), BorderLayout.NORTH);
        cp.add(createCenterPanel(), BorderLayout.CENTER);

        setTitle("Store Manager");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to create the North Panel containing the header and menu bar
    private JPanel createNorthPanel() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    // Create menu bar
    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        menu.add(createMenuItem("View store", e -> showStore()));
        menu.add(createUpdateStoreMenu());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    // Helper method to create a menu item with an action listener
    private JMenuItem createMenuItem(String name, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(action);
        return menuItem;
    }

    // Create submenu for updating store (Add Book, Add CD, Add DVD)
    private JMenu createUpdateStoreMenu() {
        JMenu smUpdateStore = new JMenu("Update store");
        smUpdateStore.add(createMenuItem("Add Book", e -> addBook()));
        smUpdateStore.add(createMenuItem("Add CD", e -> addCD()));
        smUpdateStore.add(createMenuItem("Add DVD", e -> addDVD()));
        return smUpdateStore;
    }

    // Create header panel with title
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    // Create the center panel with media items
    private JScrollPane createCenterPanel() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));  // 3x3 grid for media items

        // Get the first 9 items in the store and add them to the center panel
        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < Math.min(9, mediaInStore.size()); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        JScrollPane scrollPane = new JScrollPane(center);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        return scrollPane;
    }

    // Actions for menu items

    private void showStore() {
        JOptionPane.showMessageDialog(this, "View store clicked");
        // Implement store viewing logic here
    }

    private void addBook() {
        new AddBookToStoreScreen(store);
        dispose();  // Close current screen
    }

    private void addCD() {
        new AddCompactDiscToStoreScreen(store);
        dispose();  // Close current screen
    }

    private void addDVD() {
        new AddDigitalVideoDiscToStoreScreen(store);
        dispose();  // Close current screen
    }

    // Main method to start the application
    public static void main(String[] args) {
        Store store = new Store();
        new StoreManagerScreen(store);
    }
}

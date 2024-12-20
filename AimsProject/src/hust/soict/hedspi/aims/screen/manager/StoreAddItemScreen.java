package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import javax.swing.*;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

public class StoreAddItemScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private Store store;

    // Create north panel (menu bar and header)
    private JPanel createNorthPanel() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    // Create menu bar with options
    private JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenuItem viewStoreItem = new JMenuItem("View store");
        viewStoreItem.addActionListener(e -> {
            // Add functionality to view store
            JOptionPane.showMessageDialog(this, "View store clicked");
        });

        JMenu smUpdateStore = new JMenu("Update store");
        JMenuItem addBookItem = new JMenuItem("Add a Book");
        addBookItem.addActionListener(e -> {
            // Add functionality to add a book
            JOptionPane.showMessageDialog(this, "Add a Book clicked");
        });

        JMenuItem addCDItem = new JMenuItem("Add a CD");
        addCDItem.addActionListener(e -> {
            // Add functionality to add a CD
            JOptionPane.showMessageDialog(this, "Add a CD clicked");
        });

        JMenuItem addDVDItem = new JMenuItem("Add a DVD");
        addDVDItem.addActionListener(e -> {
            // Add functionality to add a DVD
            JOptionPane.showMessageDialog(this, "Add a DVD clicked");
        });

        smUpdateStore.add(addBookItem);
        smUpdateStore.add(addCDItem);
        smUpdateStore.add(addDVDItem);
        menu.add(viewStoreItem);
        menu.add(smUpdateStore);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    // Create header with title
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

    // Create center panel with media items in store
    private JScrollPane createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));  // 3x3 grid for media items

        // Get the first 9 items in the store and add them to the center panel
        java.util.List<Media> mediaInStore = store.getItemsInStore();
        for (int i = 0; i < Math.min(9, mediaInStore.size()); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i));
            center.add(cell);
        }

        JScrollPane scrollPane = new JScrollPane(center);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        return scrollPane;
    }

    // Constructor
    public StoreAddItemScreen(Store store) {
        this.store = store;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorthPanel(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Main method for testing
    public static void main(String[] args) {
        Store store = new Store();
        StoreAddItemScreen screen = new StoreAddItemScreen(store);
    }
}

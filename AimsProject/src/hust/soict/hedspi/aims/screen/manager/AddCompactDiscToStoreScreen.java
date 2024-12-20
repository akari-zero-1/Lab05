package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddCompactDiscToStoreScreen extends JFrame {
    private Store store;
    private JTextField title, category, director, length, cost, artist;
    private JButton btnSubmit, btnBack;

    public AddCompactDiscToStoreScreen(Store store) {
        this.store = store;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add Compact Disc to Store");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container cp = getContentPane();
        cp.setLayout(new GridLayout(7, 2, 10, 10));  // 7 rows, 2 columns

        // Adding labels and text fields
        cp.add(new JLabel("Enter title"));
        title = new JTextField(20);
        cp.add(title);

        cp.add(new JLabel("Enter category"));
        category = new JTextField(20);
        cp.add(category);

        cp.add(new JLabel("Enter director"));
        director = new JTextField(20);
        cp.add(director);

        cp.add(new JLabel("Enter length"));
        length = new JTextField(20);
        cp.add(length);

        cp.add(new JLabel("Enter cost"));
        cost = new JTextField(20);
        cp.add(cost);

        cp.add(new JLabel("Enter artist"));
        artist = new JTextField(20);
        cp.add(artist);

        // Adding buttons
        btnBack = new JButton("Back");
        btnBack.addActionListener(new BackButtonListener());
        cp.add(btnBack);

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new SubmitButtonListener());
        cp.add(btnSubmit);

        setVisible(true);
    }

    // Back button listener
    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StoreManagerScreen(store);
            dispose();
        }
    }

    // Submit button listener
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Parsing input fields
                String titleString = title.getText().trim();
                String categoryString = category.getText().trim();
                String directorString = director.getText().trim();
                String artistString = artist.getText().trim();
                float costFloat = Float.parseFloat(cost.getText().trim());

                // Creating the CompactDisc and adding it to the store
                CompactDisc cd = new CompactDisc(1, titleString, artistString, categoryString, directorString, costFloat);
                store.addMedia(cd);

                // Navigate back to the Store Manager screen
                new StoreManagerScreen(store);
                dispose();
            } catch (NumberFormatException ex) {
                showErrorDialog("Cost must be a valid float value.");
            } catch (IllegalArgumentException ex) {
                showErrorDialog("Please make sure all fields are filled correctly.");
            }
        }
    }

    // Error dialog function
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

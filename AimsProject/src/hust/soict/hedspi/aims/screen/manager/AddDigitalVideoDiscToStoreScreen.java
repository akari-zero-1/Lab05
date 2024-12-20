package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends JFrame {
    private Store store;
    private JTextField title, category, director, length, cost;
    private JButton btnSubmit, btnBack;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        this.store = store;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add Digital Video Disc to Store");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Setting layout with two columns, one for label and one for text fields
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(6, 2, 10, 10));

        // Adding labels and text fields for input
        cp.add(new JLabel("Enter title"));
        title = new JTextField(20);
        cp.add(title);

        cp.add(new JLabel("Enter category"));
        category = new JTextField(20);
        cp.add(category);

        cp.add(new JLabel("Enter director"));
        director = new JTextField(20);
        cp.add(director);

        cp.add(new JLabel("Enter length (integer)"));
        length = new JTextField(20);
        cp.add(length);

        cp.add(new JLabel("Enter cost (float)"));
        cost = new JTextField(20);
        cp.add(cost);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.addActionListener(new BackButtonListener());
        cp.add(btnBack);

        // Submit Button
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new SubmitButtonListener());
        cp.add(btnSubmit);

        setVisible(true);
    }

    // Action listener for Back button
    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StoreManagerScreen(store);
            dispose();
        }
    }

    // Action listener for Submit button
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Parsing input fields
                String titleString = title.getText().trim();
                String categoryString = category.getText().trim();
                String directorString = director.getText().trim();
                int lengthInt = Integer.parseInt(length.getText().trim());
                float costFloat = Float.parseFloat(cost.getText().trim());

                // Creating the DigitalVideoDisc and adding it to the store
                DigitalVideoDisc dvd = new DigitalVideoDisc(1, titleString, categoryString, directorString, lengthInt, costFloat);
                store.addMedia(dvd);

                // Navigate back to the Store Manager screen
                new StoreManagerScreen(store);
                dispose();
            } catch (NumberFormatException ex) {
                showErrorDialog("Length must be an integer and Cost must be a float.");
            } catch (IllegalArgumentException ex) {
                showErrorDialog("Please ensure all fields are filled out correctly.");
            }
        }
    }

    // Error dialog function
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

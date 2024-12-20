package hust.soict.hedspi.aims.screen.manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends JFrame {
    private Store store;
    private JTextField title, category, content, cost, authors;
    private JButton btnSubmit, btnBack;

    public AddBookToStoreScreen(Store store) {
        this.store = store;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add Book to Store");
        setSize(600, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(6, 2, 10, 10));

        // Adding labels and text fields
        cp.add(new JLabel("Enter title"));
        title = new JTextField(20);
        cp.add(title);

        cp.add(new JLabel("Enter authors (separated by space)"));
        authors = new JTextField(20);
        cp.add(authors);

        cp.add(new JLabel("Enter category"));
        category = new JTextField(20);
        cp.add(category);

        cp.add(new JLabel("Enter cost (float)"));
        cost = new JTextField(20);
        cp.add(cost);

        // Adding buttons
        btnBack = new JButton("Back");
        btnBack.addActionListener(new BackButtonListener());
        cp.add(btnBack);

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new SubmitButtonListener());
        cp.add(btnSubmit);

        setVisible(true);
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new StoreManagerScreen(store);
            dispose();
        }
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Parsing input fields
                String titleString = title.getText().trim();
                String categoryString = category.getText().trim();
                float costFloat = Float.parseFloat(cost.getText().trim());
                List<String> authorsList = parseAuthors(authors.getText().trim());

                // Creating and adding the book
                Book book = new Book(1, titleString, categoryString, costFloat);
                authorsList.forEach(book::addAuthor);
                store.addMedia(book);

                // Navigate back to the Store Manager screen
                new StoreManagerScreen(store);
                dispose();
            } catch (IllegalArgumentException ex) {
                showErrorDialog("Cost must be a valid float value.");
            }
        }
    }

    private List<String> parseAuthors(String authorsInput) {
        List<String> authorsList = new ArrayList<>();
        String[] authorsArray = authorsInput.split("\\s+");
        for (String author : authorsArray) {
            authorsList.add(author.trim());
        }
        return authorsList;
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

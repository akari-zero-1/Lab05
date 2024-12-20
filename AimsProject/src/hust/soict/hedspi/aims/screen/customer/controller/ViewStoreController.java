package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewStoreController {
    private Store store;
    private Cart cart;

    // Constant for the number of columns in the grid
    private static final int NUM_COLUMNS = 3;

    // Constructor to initialize store and cart
    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    // FXML annotation for the GridPane
    @FXML
    private GridPane gridPane;

    // Method to initialize the view with items from the store
    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;

        // Loop through items in the store and add them to the GridPane
        for (int i = 0; i < store.getItemsInStore().size(); i++) {
            try {
                // Load the item FXML
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
                ItemController itemController = new ItemController();
                fxmlLoader.setController(itemController);

                // Load the AnchorPane for each item
                AnchorPane anchorPane = fxmlLoader.load();
                itemController.setData(store.getItemsInStore().get(i), cart);

                // Add item to grid, manage column/row layout
                if (column == NUM_COLUMNS) {
                    column = 0;
                    row++;
                }

                // Add the item to the grid with margins
                gridPane.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
            } catch (IOException e) {
                // Log the error and show a user-friendly alert
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while loading an item.");
                alert.showAndWait();
            }
        }
    }

    // Button action method to view the cart
    @FXML
    void btnViewCartPressed(ActionEvent event) {
        navigateToCartView(event);
    }

    // Method to navigate to the Cart view
    private void navigateToCartView(ActionEvent event) {
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
            fxmlLoader.setController(new CartController(store, cart));
            Parent root = fxmlLoader.load();
            
            // Switch to the Cart view scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cart");
            stage.show();
        } catch (IOException e) {
            // Log the error and show an alert
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while navigating to the cart.");
            alert.showAndWait();
        }
    }
}

package hust.soict.hedspi.aims.screen.customer.controller;

import java.util.Optional;
import javax.naming.LimitExceededException;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {
    private Media media;
    private Cart cart;

    @FXML
    private Label lblCost;

    @FXML
    private Label lblTitle;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    public void setData(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");
        updateButtonVisibility(media);
    }

    private void updateButtonVisibility(Media media) {
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 0));  // Reset margin if needed
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60)); // Set margin for non-playable media
        }
    }

    private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        // Kiểm tra nếu media đã có trong giỏ hàng
        if (cart.getItemsOrdered().contains(media)) {
            showAlert(AlertType.WARNING, "Add media", "Warning:", "This media is already in the cart.");
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Add media");
        alert.setHeaderText("Do you want to add this media to cart?");
        alert.setContentText(media.toString());
        
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    cart.addMedia(media);
                    showAlert(AlertType.INFORMATION, "Add media", "Status:", "Success");
                } catch (LimitExceededException | IllegalArgumentException e) {
                    showAlert(AlertType.ERROR, "Error", "Error:", e.getMessage());
                }
            }
        });
    }

    @FXML
    void btnPlayClicked(ActionEvent event) {
        try {
            showAlert(AlertType.INFORMATION, "Play media", "Playing " + media.getTitle(), ((Playable) media).playMedia());
        } catch (PlayerException e) {
            showAlert(AlertType.ERROR, "Error", e.getMessage(), null);
        }
    }
}

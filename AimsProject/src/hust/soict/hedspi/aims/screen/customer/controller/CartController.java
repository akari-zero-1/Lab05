package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;
import java.util.Optional;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
	private Cart cart;
	private Store store;

	@FXML private TableView<Media> tblMedia;
	@FXML private TableColumn<Media, String> colMediaTitle;
	@FXML private Label costLabel;
	@FXML private TableColumn<Media, Integer> colMediaId;
	@FXML private TableColumn<Media, String> colMediaCategory;
	@FXML private TableColumn<Media, Float> colMediaCost;
	@FXML private Button btnPlay;
	@FXML private Button btnRemove;
	@FXML private Button btnPlaceOrder;
	@FXML private RadioButton radioBtnFilterId;
	@FXML private RadioButton radioBtnFilterTitle;
	@FXML private TextField tfFilter;
	@FXML private ToggleGroup filterCategory;

	public CartController(Store store, Cart cart) {
		this.cart = cart;
		this.store = store;
	}
	
	@FXML
	private void initialize() {
		colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

		tblMedia.setItems(cart.getItemsOrdered());
		updateButtonVisibility();
		tblMedia.getSelectionModel().selectedItemProperty().addListener(this::updateButtonBar);
		updateCostLabel();
		updateBtnPlaceOrder();
		initializeFilter();
	}

	private void initializeFilter() {
		tfFilter.textProperty().addListener((observable, oldValue, newValue) -> showFilteredMedia(newValue));
		filterCategory.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> showFilteredMedia(tfFilter.getText()));
	}

	private void updateBtnPlaceOrder() {
		btnPlaceOrder.setDisable(cart.getItemsOrdered().isEmpty());
	}

	private void showFilteredMedia(String newValue) {
		FilteredList<Media> filteredData = new FilteredList<>(cart.getItemsOrdered());
		RadioButton selectedFilter = (RadioButton) filterCategory.getSelectedToggle();
		filteredData.setPredicate(media -> {
			if (newValue == null || newValue.isEmpty()) return true;
			String filterText = newValue.toLowerCase().trim();
			if (selectedFilter.equals(radioBtnFilterTitle)) {
				return media.getTitle().toLowerCase().contains(filterText);
			} else {
				return String.valueOf(media.getId()).contains(filterText);
			}
		});
		SortedList<Media> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tblMedia.comparatorProperty());
		tblMedia.setItems(sortedData);
	}

	private void updateCostLabel() {
		float cost = (float) cart.getItemsOrdered().stream().mapToDouble(Media::getCost).sum();

		costLabel.setText(String.format("%.2f$", cost));
	}

	private void updateButtonBar(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
		updateButtonVisibility();
	}

	private void updateButtonVisibility() {
		Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
		boolean hasSelection = selectedMedia != null;
		btnPlay.setVisible(hasSelection && selectedMedia instanceof Playable);
		btnRemove.setVisible(hasSelection);
	}

	@FXML
	void btnPlayPressed(ActionEvent event) {
		try {
			Media media = tblMedia.getSelectionModel().getSelectedItem();
			if (media instanceof Playable) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Play media");
				alert.setHeaderText("Playing " + media.getTitle());
				alert.setContentText(((Playable) media).playMedia());
				alert.showAndWait();
			} else {
				throw new ClassCastException("This media is not playable!");
			}
		} catch (PlayerException | ClassCastException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(e.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		cart.removeMedia(media);
		updateCostLabel();
	}

	@FXML
	void btnViewStorePressed(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/hust/soict/hedspi/aims/screen/customer/view/Store.fxml"));
			fxmlLoader.setController(new ViewStoreController(store, cart));
			Parent root = fxmlLoader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Store");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnPlaceOrderPressed(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Place order");
		alert.setHeaderText("Do you want to place order?");
		alert.setContentText(cart.printCart());
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Alert successAlert = new Alert(AlertType.INFORMATION);
			successAlert.setTitle("Place Order");
			successAlert.setHeaderText("Status:");
			successAlert.setContentText("Success");
			successAlert.showAndWait();
			cart.emptyCart();
			updateBtnPlaceOrder();
		}
	}
}

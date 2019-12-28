package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.application.Main;
import co.crisi.filefinder.model.Imputed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class PenalIndexPaneController {

	private MainPaneController mainController;
	private ObservableList<Imputed> filterImputerData = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField filterField;

	@FXML
	private TableView<Imputed> imputedTableView;

	@FXML
	private TableColumn<Imputed, String> lastNamesImputedTableColumn;

	@FXML
	private TableColumn<Imputed, String> namesImputedTableColumn;

	@FXML
	void handleFilterField(KeyEvent event) {
		if (filterField.getText().isEmpty()) {
			imputedTableView.setItems(Main.getImputedData());
			imputedTableView.refresh();
		} else {
			filterImputerData.clear();
			for (Imputed imputed : Main.getImputedData()) {
				if (imputed.getLastNames().toLowerCase().contains(filterField.getText().toLowerCase()))
					filterImputerData.add(imputed);
			}
			imputedTableView.setItems(filterImputerData);
			imputedTableView.refresh();
		}
	}

	@FXML
	void handleRegisteredImputedButton(ActionEvent event) {

		if (imputedTableView.getSelectionModel().getSelectedIndex() != -1) {
			Imputed imputed = imputedTableView.getSelectionModel().getSelectedItem();
			mainController.loadPenalLocationsPaneController(imputed);
		} else {
			mainController.showAlert("Debe seleccionar algún imputado", "", "ADVERTENCIA", AlertType.WARNING);
		}
	}

	@FXML
	void initialize() {
		assert filterField != null : "fx:id=\"filterField\" was not injected: check your FXML file 'PenalIndexPane.fxml'.";
		assert imputedTableView != null : "fx:id=\"imputedTableView\" was not injected: check your FXML file 'PenalIndexPane.fxml'.";
		assert lastNamesImputedTableColumn != null : "fx:id=\"lastNamesImputedTableColumn\" was not injected: check your FXML file 'PenalIndexPane.fxml'.";
		assert namesImputedTableColumn != null : "fx:id=\"namesImputedTableColumn\" was not injected: check your FXML file 'PenalIndexPane.fxml'.";
	}

	public void initTableView() {
		namesImputedTableColumn.setCellValueFactory(cellData -> cellData.getValue().namesProperty());
		lastNamesImputedTableColumn.setCellValueFactory(cellData -> cellData.getValue().lastNamesProperty());
		imputedTableView.setItems(Main.getImputedData());
		imputedTableView.refresh();
	}

	public MainPaneController getMainController() {
		return mainController;
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
		initTableView();
	}
}

package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.exceptions.PenalLocationNullException;
import co.crisi.filefinder.model.Imputed;
import co.crisi.filefinder.model.PenalLocation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda cellphone: 321-937-3570 g-mail:
 *         harmaharcri.cs@gmail.com
 *
 */
public class PenalLocationsPaneController {
	private MainPaneController mainController;
	private Imputed imputed;
	private ObservableList<PenalLocation> penalLocationData = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lastNamesImputedLabel;

	@FXML
	private Label namesImputedLabel;

	@FXML
	private TableView<PenalLocation> penalLocationsTableView;

	@FXML
	private TableColumn<PenalLocation, String> crimeTableColumn;

	@FXML
	private TableColumn<PenalLocation, String> functionTableColumn;

	@FXML
	private TableColumn<PenalLocation, String> radTableColumn;

	@FXML
	private TableColumn<PenalLocation, String> folioTableColumn;

	@FXML
	private TableColumn<PenalLocation, String> volumeTableColumn;

	@FXML
	void handleBackButton(ActionEvent event) {
		mainController.loadPenalIndexPane();
	}

	@FXML
	void handleRemoveButton(ActionEvent event) {
		if (mainController.chooseLeave()) {
			if (!penalLocationsTableView.getSelectionModel().isEmpty()) {
				PenalLocation penalLocation = penalLocationsTableView.getSelectionModel().getSelectedItem();
				try {
					imputed.removePenalLocation(penalLocation.getRadication(), penalLocation.getFolio(),
							penalLocation.getVolume(), penalLocation.getFunction(), penalLocation.getCrime());
					penalLocationData.remove(penalLocation);
					penalLocationsTableView.refresh();
					mainController.showAlert("Se ha borrado el registro!", "", "ADVERTENCIA", AlertType.WARNING);
				} catch (PenalLocationNullException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@FXML
	void handleAddNewRegisterButton(ActionEvent event) {
		mainController.loadAddPenalLocationsPane(imputed);
	}

	@FXML
	void initialize() {
		assert lastNamesImputedLabel != null : "fx:id=\"lastNamesImputedLabel\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
		assert namesImputedLabel != null : "fx:id=\"namesImputedLabel\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
		assert penalLocationsTableView != null : "fx:id=\"penalLocationsTableView\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
		assert crimeTableColumn != null : "fx:id=\"crimeTableColumn\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
		assert functionTableColumn != null : "fx:id=\"functionTableColumn\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
		assert radTableColumn != null : "fx:id=\"radTableColumn\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
		assert folioTableColumn != null : "fx:id=\"folioTableColumn\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
		assert volumeTableColumn != null : "fx:id=\"volumeTableColumn\" was not injected: check your FXML file 'PenalLocationsPane.fxml'.";
	}

	public MainPaneController getMainController() {
		return mainController;
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
	}

	public void initTableView() {
		penalLocationData.clear();
		crimeTableColumn.setCellValueFactory(cellData -> cellData.getValue().crimeProperty());
		functionTableColumn.setCellValueFactory(cellData -> cellData.getValue().functionProperty());
		radTableColumn.setCellValueFactory(cellData -> cellData.getValue().radicationProperty());
		folioTableColumn.setCellValueFactory(cellData -> cellData.getValue().folioProperty());
		volumeTableColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
		penalLocationData.addAll(imputed.getPenalLocations());
		penalLocationsTableView.setItems(penalLocationData);
		penalLocationsTableView.refresh();
	}

	public Imputed getImputed() {
		return imputed;
	}

	public void setImputed(Imputed imputed) {
		this.imputed = imputed;
		lastNamesImputedLabel.setText(imputed.getLastNames());
		namesImputedLabel.setText(imputed.getNames());
		initTableView();
	}
}

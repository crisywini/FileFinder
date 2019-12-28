package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.exceptions.CivilLocationNullException;
import co.crisi.filefinder.model.CivilLocation;
import co.crisi.filefinder.model.Defendant;
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
public class CivilLocationsPaneController {
	private MainPaneController mainController;
	private Defendant defendant;
	private ObservableList<CivilLocation> civilLocationData = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label defendantLastNamesLabel;

	@FXML
	private Label defendantNamesLabel;

	@FXML
	private TableView<CivilLocation> civilLocationTableView;

	@FXML
	private TableColumn<CivilLocation, String> processTableColumn;

	@FXML
	private TableColumn<CivilLocation, String> applicantTableColumn;

	@FXML
	private TableColumn<CivilLocation, String> radTableColumn;

	@FXML
	private TableColumn<CivilLocation, String> folioTableColumn;

	@FXML
	private TableColumn<CivilLocation, String> volumeTableColumn;

	@FXML
	void handleBackButton(ActionEvent event) {
		mainController.loadCivilIndexPane();
	}

	@FXML
	void handleRemoveButton(ActionEvent event) {
		if (mainController.chooseLeave()) {
			if (!civilLocationTableView.getSelectionModel().isEmpty()) {
				CivilLocation civilLocation = civilLocationTableView.getSelectionModel().getSelectedItem();
				try {
					defendant.removeCivilLocation(civilLocation.getRadication(), civilLocation.getFolio(),
							civilLocation.getVolume(), civilLocation.getProcess());
					civilLocationData.remove(civilLocation);
					civilLocationTableView.refresh();
					mainController.showAlert("Registro borrado!", "", "ADVERTENCIA", AlertType.WARNING);
				} catch (CivilLocationNullException e) {
					e.printStackTrace();
				}
			} else
				mainController.showAlert("Debe seleccionar un registro", "", "ADVERTENCIA", AlertType.WARNING);
		}

	}

	@FXML
	void handleAddNewRegisterButton(ActionEvent event) {
		mainController.loadAddCivilLocationsPane(defendant);
	}

	@FXML
	void initialize() {
		assert defendantLastNamesLabel != null : "fx:id=\"defendantLastNamesLabel\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";
		assert defendantNamesLabel != null : "fx:id=\"defendantNamesLabel\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";
		assert civilLocationTableView != null : "fx:id=\"civilLocationTableView\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";
		assert processTableColumn != null : "fx:id=\"processTableColumn\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";
		assert applicantTableColumn != null : "fx:id=\"applicantTableColumn\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";
		assert radTableColumn != null : "fx:id=\"radTableColumn\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";
		assert folioTableColumn != null : "fx:id=\"folioTableColumn\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";
		assert volumeTableColumn != null : "fx:id=\"volumeTableColumn\" was not injected: check your FXML file 'CivilLocationsPane.fxml'.";

	}

	public MainPaneController getMainController() {
		return mainController;
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
	}

	public Defendant getDefendant() {
		return defendant;
	}

	public void setDefendant(Defendant defendant) {
		this.defendant = defendant;
		defendantLastNamesLabel.setText(defendant.getLastNames());
		defendantNamesLabel.setText(defendant.getNames());
		initTableView();
	}

	public void initTableView() {
		civilLocationData.clear();
		processTableColumn.setCellValueFactory(cellData -> cellData.getValue().processProperty());
		radTableColumn.setCellValueFactory(cellData -> cellData.getValue().radicationProperty());
		folioTableColumn.setCellValueFactory(cellData -> cellData.getValue().folioProperty());
		volumeTableColumn.setCellValueFactory(cellData -> cellData.getValue().volumeProperty());
		applicantTableColumn.setCellValueFactory(cellData -> cellData.getValue().applicantProperty());
		civilLocationData.addAll(defendant.getCivilLocations());
		civilLocationTableView.setItems(civilLocationData);
		civilLocationTableView.refresh();
	}
}

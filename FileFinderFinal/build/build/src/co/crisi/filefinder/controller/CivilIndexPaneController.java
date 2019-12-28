package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.application.Main;
import co.crisi.filefinder.model.Defendant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class CivilIndexPaneController {
	private MainPaneController mainController;
	private ObservableList<Defendant> filterDefentandData = FXCollections.observableArrayList();
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField searchField;

	@FXML
	private TableView<Defendant> defendantsTableView;

	@FXML
	private TableColumn<Defendant, String> lastNamesTableColumn;

	@FXML
	private TableColumn<Defendant, String> namesTableColumn;

	@FXML
	void handleCivilLocationsButton(ActionEvent event) {
		if (defendantsTableView.getSelectionModel().getSelectedIndex() != -1) {
			Defendant defendant = defendantsTableView.getSelectionModel().getSelectedItem();
			mainController.loadCivilLocationsPane(defendant);
		}
	}

	@FXML
	void handleFilterField(KeyEvent event) {
		if (searchField.getText().length() == 0 || searchField.getText().isEmpty()) {
			defendantsTableView.setItems(Main.getDefendantData());
			defendantsTableView.refresh();
		} else {
			filterDefentandData.clear();
			for (Defendant defendant : Main.getDefendantData()) {
				if (defendant.getLastNames().toLowerCase().contains(searchField.getText().toLowerCase()))
					filterDefentandData.add(defendant);
			}
			defendantsTableView.setItems(filterDefentandData);
			defendantsTableView.refresh();
		}
	}

	@FXML
	void initialize() {
		assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'CivilIndexPane.fxml'.";
		assert defendantsTableView != null : "fx:id=\"defendantsTableView\" was not injected: check your FXML file 'CivilIndexPane.fxml'.";
		assert lastNamesTableColumn != null : "fx:id=\"lastNamesTableColumn\" was not injected: check your FXML file 'CivilIndexPane.fxml'.";
		assert namesTableColumn != null : "fx:id=\"namesTableColumn\" was not injected: check your FXML file 'CivilIndexPane.fxml'.";
	}

	public MainPaneController getMainController() {
		return mainController;
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
		initTableView();
	}

	public void initTableView() {
		namesTableColumn.setCellValueFactory(cellData -> cellData.getValue().namesProperty());
		lastNamesTableColumn.setCellValueFactory(cellData -> cellData.getValue().lastNamesProperty());
		defendantsTableView.setItems(Main.getDefendantData());
		defendantsTableView.refresh();
	}
}

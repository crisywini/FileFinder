package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.exceptions.PenalLocationRepeatedException;
import co.crisi.filefinder.model.Imputed;
import co.crisi.filefinder.model.TypeFunction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
/**
 * 
 * @author Cristian Giovanny Sánchez Pineda 
 *  cellphone: 321-937-3570 
 *  g-mail: harmaharcri.cs@gmail.com
 *
 */
public class AddNewRegisterPenalPaneController {
	private MainPaneController mainController;
	private Imputed imputed;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private Label lastNamesImputed;

	@FXML
	private Label namesImputed;

	@FXML
	private TextField crimeField;

	@FXML
	private ComboBox<TypeFunction> typeFunctionComboBox;

	@FXML
	private TextField radField;

	@FXML
	private TextField folioField;

	@FXML
	private TextField volumeField;

	@FXML
	void handleAddButton(ActionEvent event) {
		if (isInputValid()) {
			long rad = Long.parseLong(radField.getText());
			long folio = Long.parseLong(folioField.getText());
			long volume = Long.parseLong(volumeField.getText());
			TypeFunction function = typeFunctionComboBox.getValue();
			String crime = crimeField.getText();
			try {
				imputed.addPenalLocation(rad, folio, volume, function, crime);
				volumeField.setText("");
				radField.setText("");
				folioField.setText("");
				crimeField.setText("");
				typeFunctionComboBox.getSelectionModel().clearSelection();
				mainController.showAlert(
						"Imputado: " + imputed.getLastNames() + " " + imputed.getNames() + " agregado.", "",
						"INFORMACIÓN", AlertType.INFORMATION);
			} catch (PenalLocationRepeatedException e) {
				mainController.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			}
		}
	}

	@FXML
	void handleBackButton(ActionEvent event) {
		mainController.loadPenalLocationsPaneController(imputed);

	}

	public void initComboBox() {
		ObservableList<TypeFunction> data = FXCollections.observableArrayList(TypeFunction.CONOCIMIENTO,
				TypeFunction.GARANTIAS);
		typeFunctionComboBox.setItems(data);
	}

	@FXML
	void initialize() {
		assert crimeField != null : "fx:id=\"crimeField\" was not injected: check your FXML file 'AddNewRegisterPenalPane.fxml'.";
		assert typeFunctionComboBox != null : "fx:id=\"typeFunctionComboBox\" was not injected: check your FXML file 'AddNewRegisterPenalPane.fxml'.";
		assert radField != null : "fx:id=\"radField\" was not injected: check your FXML file 'AddNewRegisterPenalPane.fxml'.";
		assert folioField != null : "fx:id=\"folioField\" was not injected: check your FXML file 'AddNewRegisterPenalPane.fxml'.";
		assert volumeField != null : "fx:id=\"volumeField\" was not injected: check your FXML file 'AddNewRegisterPenalPane.fxml'.";
	}

	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (radField.getText().isEmpty())
			errorMessage += "Debe ingresar la radicación\n";
		else
			try {
				Long.parseLong(radField.getText());
			} catch (Exception e) {
				errorMessage += "La radicación debe ser un número\n";
			}
		if (folioField.getText().isEmpty())
			errorMessage += "Debe ingresar el folio\n";
		else
			try {
				Long.parseLong(folioField.getText());
			} catch (Exception e) {
				errorMessage += "El folio debe ser un numero\n";
			}
		if (volumeField.getText().isEmpty())
			errorMessage += "Debe ingresar el tomo\n";
		else
			try {
				Long.parseLong(volumeField.getText());
			} catch (Exception e) {
				errorMessage += "El tomo debe ser un numero\n";
			}
		if (crimeField.getText().isEmpty())
			errorMessage += "Debe ingresar el crimen\n";
		if (typeFunctionComboBox.getSelectionModel().getSelectedIndex() == -1)
			errorMessage += "Debe ingresar un tipo de proceso\n";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			mainController.showAlert(errorMessage, "", "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	public MainPaneController getMainController() {
		return mainController;
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
	}

	public Imputed getImputed() {
		return imputed;
	}

	public void setImputed(Imputed imputed) {
		this.imputed = imputed;
		lastNamesImputed.setText(imputed.getLastNames());
		namesImputed.setText(imputed.getNames());
		initComboBox();
	}
}

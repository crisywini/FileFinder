package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.application.Main;
import co.crisi.filefinder.exceptions.ImputedRepeatedException;
import co.crisi.filefinder.exceptions.PenalLocationRepeatedException;
import co.crisi.filefinder.model.FileFinder;
import co.crisi.filefinder.model.Imputed;
import co.crisi.filefinder.model.PenalIndex;
import co.crisi.filefinder.model.TypeFunction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda cellphone: 321-937-3570 g-mail:
 *         harmaharcri.cs@gmail.com
 *
 */
public class AddImputedPaneController {
	private MainPaneController mainController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private TextField lastNamesImputed;

	@FXML
	private TextField namesImputed;
	@FXML
	private RadioButton personRadioButton;
	@FXML
	private RadioButton entityRadioButton;

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
			Main main = mainController.getMain();
			FileFinder fileFinder = main.getFileFinder();
			PenalIndex penalIndex = fileFinder.getPenalIndex();
			Imputed imputed;
			if (personRadioButton.isSelected()) {
				long rad = Long.parseLong(radField.getText());
				long folio = Long.parseLong(folioField.getText());
				long volume = Long.parseLong(volumeField.getText());
				TypeFunction function = typeFunctionComboBox.getValue();
				String crime = crimeField.getText();
				try {
					penalIndex.addImputed(lastNamesImputed.getText().toUpperCase(),
							namesImputed.getText().toUpperCase());
					int pos = penalIndex.getPosImputed(lastNamesImputed.getText().toUpperCase(),
							namesImputed.getText().toUpperCase());
					imputed = penalIndex.getImputedPeople().get(pos);
					imputed.addPenalLocation(rad, folio, volume, function, crime);
					Main.getImputedData().add(imputed);
					volumeField.setText("");
					radField.setText("");
					folioField.setText("");
					crimeField.setText("");
					lastNamesImputed.setText("");
					namesImputed.setText("");
					typeFunctionComboBox.getSelectionModel().clearSelection();
					personRadioButton.setSelected(false);
					entityRadioButton.setSelected(false);
					mainController.showAlert(
							"Imputado: " + imputed.getLastNames() + " " + imputed.getNames() + " agregado.", "",
							"INFORMACIÓN", AlertType.INFORMATION);

				} catch (ImputedRepeatedException e) {
					mainController.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
				} catch (PenalLocationRepeatedException e) {
					mainController.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
				}

			} else {
				long rad = Long.parseLong(radField.getText());
				long folio = Long.parseLong(folioField.getText());
				long volume = Long.parseLong(volumeField.getText());
				TypeFunction function = typeFunctionComboBox.getValue();
				String crime = crimeField.getText();
				try {
					penalIndex.addImputed(lastNamesImputed.getText().toUpperCase(), "ENTIDAD");
					int pos = penalIndex.getPosImputed(lastNamesImputed.getText().toUpperCase(), "ENTIDAD");
					imputed = penalIndex.getImputedPeople().get(pos);
					imputed.addPenalLocation(rad, folio, volume, function, crime);
					Main.getImputedData().add(imputed);
					volumeField.setText("");
					radField.setText("");
					folioField.setText("");
					crimeField.setText("");
					lastNamesImputed.setText("");
					namesImputed.setText("");
					typeFunctionComboBox.getSelectionModel().clearSelection();
					personRadioButton.setSelected(false);
					entityRadioButton.setSelected(false);
					mainController.showAlert(
							"Imputado: " + imputed.getLastNames() + " " + imputed.getNames() + " agregado.", "",
							"INFORMACIÓN", AlertType.INFORMATION);
				} catch (ImputedRepeatedException e) {
					mainController.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
				} catch (PenalLocationRepeatedException e) {
					mainController.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
				}
			}
		}
	}

	@FXML
	void initialize() {
		assert toggleGroup != null : "fx:id=\"toggleGroup\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
		assert lastNamesImputed != null : "fx:id=\"lastNamesImputed\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
		assert namesImputed != null : "fx:id=\"namesImputed\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
		assert crimeField != null : "fx:id=\"crimeField\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
		assert typeFunctionComboBox != null : "fx:id=\"typeFunctionComboBox\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
		assert radField != null : "fx:id=\"radField\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
		assert folioField != null : "fx:id=\"folioField\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
		assert volumeField != null : "fx:id=\"volumeField\" was not injected: check your FXML file 'AddImputedPane.fxml'.";
	}

	public void initComboBox() {
		ObservableList<TypeFunction> data = FXCollections.observableArrayList(TypeFunction.CONOCIMIENTO,
				TypeFunction.GARANTIAS);
		typeFunctionComboBox.setItems(data);
	}

	public MainPaneController getMainController() {
		return mainController;
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
		initComboBox();
	}

	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (lastNamesImputed.getText().isEmpty())
			errorMessage += "Debe ingresar los apellidos del imputado\n";
		if (namesImputed.getText().isEmpty())
			if (personRadioButton.isSelected())
				errorMessage += "Debe ingresar los nombres de la persona\n";
		if (!personRadioButton.isSelected() && !entityRadioButton.isSelected())
			errorMessage += "Debe seleccionar si el demandado es una persona o una entidad\n";
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
}

package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.exceptions.CivilLocationRepeatedException;
import co.crisi.filefinder.model.Applicant;
import co.crisi.filefinder.model.Defendant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class AddNewRegisterCivilController {
	private MainPaneController mainController;
	private Defendant defendant;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lastNamesDefendantLabel;

	@FXML
	private Label namesDefendantLabel;

	@FXML
	private TextField radField;

	@FXML
	private TextField folioField;

	@FXML
	private TextField volumeField;

	@FXML
	private TextField processField;

	@FXML
	private TextField lastNamesApplicantField;

	@FXML
	private TextField namesApplicantField;

	@FXML
	void handleAddButton(ActionEvent event) {
		if (isInputValid()) {
			long rad = Long.parseLong(radField.getText());
			long folio = Long.parseLong(folioField.getText());
			long volume = Long.parseLong(volumeField.getText());
			Applicant applicant = new Applicant(namesApplicantField.getText(), lastNamesApplicantField.getText());
			String process = processField.getText();
			try {
				defendant.addCivilLocation(rad, folio, volume, applicant, process);
				mainController.showAlert("Registro con exito", "", "Información", AlertType.INFORMATION);
				volumeField.setText("");
				radField.setText("");
				processField.setText("");
				namesApplicantField.setText("");
				lastNamesApplicantField.setText("");
				folioField.setText("");
			} catch (CivilLocationRepeatedException e) {
				mainController.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			}
		}
	}

	@FXML
	void handleBackButton(ActionEvent event) {
		mainController.loadCivilLocationsPane(defendant);
	}

	@FXML
	void initialize() {
		assert lastNamesDefendantLabel != null : "fx:id=\"lastNamesDefendantLabel\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
		assert namesDefendantLabel != null : "fx:id=\"namesDefendantLabel\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
		assert radField != null : "fx:id=\"radField\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
		assert folioField != null : "fx:id=\"folioField\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
		assert volumeField != null : "fx:id=\"volumeField\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
		assert processField != null : "fx:id=\"processField\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
		assert lastNamesApplicantField != null : "fx:id=\"lastNamesApplicantField\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
		assert namesApplicantField != null : "fx:id=\"namesApplicantField\" was not injected: check your FXML file 'AddNewRegister.fxml'.";
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
		if (processField.getText().isEmpty())
			errorMessage += "Debe ingresar el proceso\n";
		if (lastNamesApplicantField.getText().isEmpty())
			errorMessage += "Debe ingresar los apellidos del demandante\n";
		if (namesApplicantField.getText().isEmpty())
			errorMessage += "Debe ingresar los nombres del demandante\n";
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

	public Defendant getDefendant() {
		return defendant;
	}

	public void setDefendant(Defendant defendant) {
		this.defendant = defendant;
		lastNamesDefendantLabel.setText(defendant.getLastNames());
		namesDefendantLabel.setText(defendant.getNames());
	}
}

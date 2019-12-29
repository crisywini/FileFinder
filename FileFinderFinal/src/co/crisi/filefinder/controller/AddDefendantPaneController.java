package co.crisi.filefinder.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.filefinder.application.Main;
import co.crisi.filefinder.exceptions.ApplicantRepeatedException;
import co.crisi.filefinder.exceptions.CivilLocationRepeatedException;
import co.crisi.filefinder.exceptions.DefendantRepeatedException;
import co.crisi.filefinder.model.Applicant;
import co.crisi.filefinder.model.CivilIndex;
import co.crisi.filefinder.model.Defendant;
import co.crisi.filefinder.model.FileFinder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda cellphone: 321-937-3570 g-mail:
 *         harmaharcri.cs@gmail.com
 *
 */
public class AddDefendantPaneController {
	private MainPaneController mainController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField lastNamesDefendantField;

	@FXML
	private TextField namesDefendantField;

	@FXML
	private RadioButton personRadioButton;

	@FXML
	private ToggleGroup toggleGroup;
	@FXML
	private ToggleGroup toggleGroupApplicant;
	@FXML
	private RadioButton applicantEntityRadioButton;
	@FXML
	private RadioButton applicantPersonRadioButton;

	@FXML
	private RadioButton entityRadioButton;

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
			Main main = mainController.getMain();
			FileFinder fileFinder = main.getFileFinder();
			CivilIndex civilIndex = fileFinder.getCivilIndex();
			Defendant defendant;
			Applicant applicant;
			if (personRadioButton.isSelected()) {
				try {
					civilIndex.addDefendant(namesDefendantField.getText().toUpperCase(),
							lastNamesDefendantField.getText().toUpperCase());
					if (applicantEntityRadioButton.isSelected()) {
						civilIndex.addApplicant("ENTIDAD", lastNamesApplicantField.getText().toUpperCase());
					} else {
						civilIndex.addApplicant(namesApplicantField.getText().toUpperCase(),
								lastNamesApplicantField.getText().toUpperCase());
					}
					int pos = civilIndex.getPosDefendants(lastNamesDefendantField.getText().toUpperCase(),
							namesDefendantField.getText().toUpperCase());
					defendant = civilIndex.getDefendants().get(pos);
					String nameApplicant = "";
					if (applicantEntityRadioButton.isSelected())
						nameApplicant = "ENTIDAD";
					else
						nameApplicant = namesApplicantField.getText().toUpperCase();

					int posApplicant = civilIndex.getPosApplicant(lastNamesApplicantField.getText().toUpperCase(),
							nameApplicant);
					applicant = civilIndex.getApplicants().get(posApplicant);
					defendant.addCivilLocation(Long.parseLong(radField.getText()), Long.parseLong(folioField.getText()),
							Long.parseLong(volumeField.getText()), applicant, processField.getText().toUpperCase());
					applicant.addDefendant(defendant);
					Main.getDefendantData().add(defendant);
					mainController.showAlert("Registro con exito", "", "Información", AlertType.INFORMATION);
					volumeField.setText("");
					radField.setText("");
					processField.setText("");
					namesDefendantField.setText("");
					namesApplicantField.setText("");
					lastNamesDefendantField.setText("");
					lastNamesApplicantField.setText("");
					folioField.setText("");
					personRadioButton.setSelected(false);
					entityRadioButton.setSelected(false);

				} catch (DefendantRepeatedException e) {
					mainController.showAlert(e.getMessage() + "\nDebe buscar el demandado y anexar un nuevo registro",
							"Error", "ERROR", AlertType.ERROR);
				} catch (ApplicantRepeatedException e) {
					mainController.showAlert(e.getMessage(), "Error", "ERROR", AlertType.ERROR);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (CivilLocationRepeatedException e) {
					mainController.showAlert(e.getMessage(), "Error", "ERROR", AlertType.ERROR);
				}
			} else {
				try {
					civilIndex.addDefendant("ENTIDAD", lastNamesDefendantField.getText().toUpperCase());
					if (applicantEntityRadioButton.isSelected()) {
						civilIndex.addApplicant("ENTIDAD", lastNamesApplicantField.getText().toUpperCase());
					} else {
						civilIndex.addApplicant(namesApplicantField.getText().toUpperCase(),
								lastNamesApplicantField.getText().toUpperCase());
					}
					int pos = civilIndex.getPosDefendants(lastNamesDefendantField.getText().toUpperCase(), "ENTIDAD");
					defendant = civilIndex.getDefendants().get(pos);
					String nameApplicant = "";
					if (applicantEntityRadioButton.isSelected())
						nameApplicant = "ENTIDAD";
					else
						nameApplicant = namesApplicantField.getText().toUpperCase();

					int posApplicant = civilIndex.getPosApplicant(lastNamesApplicantField.getText().toUpperCase(),
							nameApplicant);
					applicant = civilIndex.getApplicants().get(posApplicant);
					defendant.addCivilLocation(Long.parseLong(radField.getText()), Long.parseLong(folioField.getText()),
							Long.parseLong(volumeField.getText()), applicant, processField.getText().toUpperCase());
					applicant.addDefendant(defendant);
					Main.getDefendantData().add(defendant);
					mainController.showAlert("Registro con exito", "", "Información", AlertType.INFORMATION);
					volumeField.setText("");
					radField.setText("");
					processField.setText("");
					namesDefendantField.setText("");
					namesApplicantField.setText("");
					lastNamesDefendantField.setText("");
					lastNamesApplicantField.setText("");
					folioField.setText("");
					personRadioButton.setSelected(false);
					entityRadioButton.setSelected(false);

				} catch (DefendantRepeatedException e) {
					mainController.showAlert(e.getMessage() + "\nDebe buscar el demandado y anexar un nuevo registro",
							"Error", "ERROR", AlertType.ERROR);
				} catch (ApplicantRepeatedException e) {
					mainController.showAlert(e.getMessage(), "Error", "ERROR", AlertType.ERROR);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (CivilLocationRepeatedException e) {
					mainController.showAlert(e.getMessage(), "Error", "ERROR", AlertType.ERROR);
				}
			}
		}
	}

	@FXML
	void initialize() {
		assert lastNamesDefendantField != null : "fx:id=\"lastNamesDefendantField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert namesDefendantField != null : "fx:id=\"namesDefendantField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert personRadioButton != null : "fx:id=\"personRadioButton\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert toggleGroup != null : "fx:id=\"toggleGroup\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert entityRadioButton != null : "fx:id=\"entityRadioButton\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert radField != null : "fx:id=\"radField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert folioField != null : "fx:id=\"folioField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert volumeField != null : "fx:id=\"volumeField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert processField != null : "fx:id=\"processField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert lastNamesApplicantField != null : "fx:id=\"lastNamesApplicantField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";
		assert namesApplicantField != null : "fx:id=\"namesApplicantField\" was not injected: check your FXML file 'AddDefendantPane.fxml'.";

	}

	public MainPaneController getMainController() {
		return mainController;
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
	}

	public boolean isInputValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (lastNamesDefendantField.getText().isEmpty())
			errorMessage += "Debe ingresar los apellidos del demandado\n";
		if (namesDefendantField.getText().isEmpty()) {
			if (personRadioButton.isSelected())
				errorMessage += "Debe ingresar el nombre del demandado(Solo para personas)\n";
		}
		if (!personRadioButton.isSelected() && !entityRadioButton.isSelected())
			errorMessage += "Debe seleccionar si el demandado es una persona o una entidad\n";
		if (!applicantEntityRadioButton.isSelected() && !applicantPersonRadioButton.isSelected())
			errorMessage += "Debe seleccionar si el demandante es una persona o una entidad\n";
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
			if (applicantPersonRadioButton.isSelected())
				errorMessage += "Debe ingresar los nombres del demandante\n";
		if (errorMessage.isEmpty())
			isValid = true;
		else
			mainController.showAlert(errorMessage, "", "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}
}

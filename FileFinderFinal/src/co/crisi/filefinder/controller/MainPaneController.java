package co.crisi.filefinder.controller;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.crisi.filefinder.application.Main;
import co.crisi.filefinder.gui.Finder;
import co.crisi.filefinder.model.Defendant;
import co.crisi.filefinder.model.Imputed;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda cellphone: 321-937-3570 g-mail:
 *         harmaharcri.cs@gmail.com
 *
 */
public class MainPaneController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane mainPane;
	private Main main;
	private Stage primaryStage;
	VBox civilIndexPane;
	VBox addDefendantPane;
	VBox civilLocationsPane;
	VBox addImputedPane;
	VBox penalIndexPane;
	VBox penalLocationsPane;
	VBox addCivilLocationPane;
	VBox addPenalLocationPane;
	CivilIndexPaneController civilPaneController;
	AddDefendantPaneController addDefendantPaneController;
	CivilLocationsPaneController civilLocationsPaneController;
	AddImputedPaneController addImputedPaneController;
	PenalIndexPaneController penalIndexPaneController;
	PenalLocationsPaneController penalLocationsPaneController;
	AddNewRegisterCivilController addCivilLocationsController;
	AddNewRegisterPenalPaneController addPenalLocationsController;

	@FXML
	void handleAboutMenuItem(ActionEvent event) {
		showAlert(
				"FileFinder fue creado por Cristian Giovanny Sánchez Pineda\nComo regalo para su tía Luz Evila\nFue creado con animo de ayudarla a encontrar de una forma más fácil archivos que de otra forma se volvería tedioso",
				"", "INFORMACION FILE FINDER", AlertType.INFORMATION);
	}

	@FXML
	void handleAddCivilIndexMenuItem(ActionEvent event) {
		loadAddDefendantPane();
	}

	@FXML
	void handleAddPenalIndexMenuItem(ActionEvent event) {
		loadAddImputedPane();
	}

	@FXML
	void handleCivilIndexMenuItem(ActionEvent event) {
		loadCivilIndexPane();
	}

	@FXML
	void handlePenalIndexMenuItem(ActionEvent event) {
		loadPenalIndexPane();
	}

	@FXML
	void handleSaveDataMenuItem(ActionEvent event) {
		try {
			main.serialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'MainPane.fxml'.";
		loadCivilIndexPane();
	}

	public void loadCivilIndexPane() {
		if (civilIndexPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("CivilIndexPane.fxml"));
				civilIndexPane = (VBox) loader.load();
				civilPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		civilPaneController.setMainController(this);
		mainPane.setCenter(civilIndexPane);
	}

	public void loadAddDefendantPane() {
		if (addDefendantPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("AddDefendantPane.fxml"));
				addDefendantPane = (VBox) loader.load();
				addDefendantPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addDefendantPaneController.setMainController(this);
		mainPane.setCenter(addDefendantPane);
	}

	public void loadCivilLocationsPane(Defendant defendant) {
		if (civilLocationsPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("CivilLocationsPane.fxml"));
				civilLocationsPane = (VBox) loader.load();
				civilLocationsPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		civilLocationsPaneController.setDefendant(defendant);
		civilLocationsPaneController.setMainController(this);
		mainPane.setCenter(civilLocationsPane);
	}

	public void loadAddImputedPane() {
		if (addImputedPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("AddImputedPane.fxml"));
				addImputedPane = (VBox) loader.load();
				addImputedPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addImputedPaneController.setMainController(this);
		mainPane.setCenter(addImputedPane);
	}

	public void loadPenalIndexPane() {
		if (penalIndexPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("PenalIndexPane.fxml"));
				penalIndexPane = (VBox) loader.load();
				penalIndexPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		penalIndexPaneController.setMainController(this);
		mainPane.setCenter(penalIndexPane);
	}

	public void loadPenalLocationsPaneController(Imputed imputed) {
		if (penalLocationsPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("PenalLocationsPane.fxml"));
				penalLocationsPane = (VBox) loader.load();
				penalLocationsPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		penalLocationsPaneController.setImputed(imputed);
		penalLocationsPaneController.setMainController(this);
		mainPane.setCenter(penalLocationsPane);
	}

	public void loadAddCivilLocationsPane(Defendant defendant) {
		if (addCivilLocationPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("AddNewRegisterCivilPane.fxml"));
				addCivilLocationPane = (VBox) loader.load();
				addCivilLocationsController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addCivilLocationsController.setDefendant(defendant);
		addCivilLocationsController.setMainController(this);
		mainPane.setCenter(addCivilLocationPane);
	}

	public void loadAddPenalLocationsPane(Imputed imputed) {
		if (addPenalLocationPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Finder.class.getResource("AddNewRegisterPenalPane.fxml"));
				addPenalLocationPane = (VBox) loader.load();
				addPenalLocationsController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		addPenalLocationsController.setImputed(imputed);
		addPenalLocationsController.setMainController(this);
		mainPane.setCenter(addPenalLocationPane);
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void showAlert(String contentText, String headerText, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setContentText(contentText);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.initOwner(primaryStage);
		alert.showAndWait();
	}

	public boolean chooseLeave() {
		boolean centinela;
		Alert miAlert = new Alert(AlertType.WARNING);
		miAlert.setTitle("ADVERTENCIA");
		miAlert.setContentText("¿Desea eliminar el registro?");
		miAlert.initOwner(primaryStage);
		ButtonType buttonTypeOne = new ButtonType("Si");
		ButtonType buttonTypeTwo = new ButtonType("No");
		miAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> resultado = miAlert.showAndWait();
		centinela = resultado.get() == buttonTypeOne;
		return centinela;
	}
}

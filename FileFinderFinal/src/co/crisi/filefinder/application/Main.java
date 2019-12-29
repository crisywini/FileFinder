package co.crisi.filefinder.application;

import java.io.File;
import java.io.IOException;

import co.crisi.filefinder.controller.MainPaneController;
import co.crisi.filefinder.exceptions.DefendantRepeatedException;
import co.crisi.filefinder.exceptions.ImputedRepeatedException;
import co.crisi.filefinder.gui.Finder;
import co.crisi.filefinder.model.CivilIndex;
import co.crisi.filefinder.model.Defendant;
import co.crisi.filefinder.model.FileFinder;
import co.crisi.filefinder.model.Imputed;
import co.crisi.filefinder.model.PenalIndex;
import co.crisi.filefinder.persistence.Persistence;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author Cristian Giovanny Sánchez Pineda cellphone: 321-937-3570 g-mail:
 *         harmaharcri.cs@gmail.com
 *
 */
public class Main extends Application implements IFileFinderControl {

	private static FileFinder fileFinder;
	private static ObservableList<Defendant> defendantData = FXCollections.observableArrayList();
	private static ObservableList<Imputed> imputedData = FXCollections.observableArrayList();
	static final EventHandler<WindowEvent> closer = new EventHandler<WindowEvent>() {

		@Override
		public void handle(WindowEvent event) {
			try {
				serializeStatic();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	@Override
	public void start(Stage primaryStage) {
		loadData();
		loadMainPane(primaryStage);
	}

	public void addMillion() {
		for (int i = 0; i < 10000; i++) {
			try {
				fileFinder.getCivilIndex().addDefendant("Defendant" + i, i + "defendant");
			} catch (DefendantRepeatedException e) {
				e.printStackTrace();
			}
			try {
				fileFinder.getPenalIndex().addImputed(i + "imputed", "imputed" + i);
			} catch (ImputedRepeatedException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadMainPane(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Finder.class.getResource("MainPane.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			MainPaneController mainController = loader.getController();
			mainController.setMain(this);
			mainController.setPrimaryStage(primaryStage);
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(800);
			primaryStage.setMinHeight(700);
			primaryStage.setTitle("File Finder");
			primaryStage.setOnCloseRequest(closer);
			primaryStage.getIcons().add(new Image("file:resources\\images\\searcher2.png"));
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public FileFinder getFileFinder() {
		return fileFinder;
	}

	public static void setFileFinder(FileFinder fileFinder) {
		Main.fileFinder = fileFinder;
	}

	public static ObservableList<Defendant> getDefendantData() {
		return defendantData;
	}

	public static void setDefendantData(ObservableList<Defendant> defendantData) {
		Main.defendantData = defendantData;
	}

	public static ObservableList<Imputed> getImputedData() {
		return imputedData;
	}

	public static void setImputedData(ObservableList<Imputed> imputedData) {
		Main.imputedData = imputedData;
	}

	public void serialize() throws IOException {
		Persistence.serialize(Persistence.FILE_FINDER_PATH, fileFinder);
	}

	public Object readObject() throws ClassNotFoundException, IOException {
		return Persistence.readSerializeFile(Persistence.FILE_FINDER_PATH);
	}

	public static void serializeStatic() throws IOException {
		Persistence.serialize(Persistence.FILE_FINDER_PATH, fileFinder);
	}

	public void loadData() {
		FileFinder fileFinderAuxiliar = null;
		File root = new File(Persistence.FILE_FINDER_PATH);
		if (root.exists()) {
			try {
				fileFinderAuxiliar = (FileFinder) readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fileFinderAuxiliar == null)
			fileFinder = new FileFinder();
		else
			setFileFinder(fileFinderAuxiliar);
		loadCollections();
	}

	public void loadCollections() {
		defendantData.addAll(fileFinder.getCivilIndex().getDefendants());
		imputedData.addAll(fileFinder.getPenalIndex().getImputedPeople());
	}

	@Override
	public PenalIndex getPenalIndex() {
		return fileFinder.getPenalIndex();
	}

	@Override
	public void setPenalIndex(PenalIndex penalIndex) {
		fileFinder.setPenalIndex(penalIndex);
	}

	@Override
	public CivilIndex getCivilIndex() {
		return fileFinder.getCivilIndex();
	}

	@Override
	public void setCivilIndex(CivilIndex civilIndex) {
		fileFinder.setCivilIndex(civilIndex);
	}
}

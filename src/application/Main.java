package application;

import java.io.IOException;
import java.util.ArrayList;

import com.rkw.IniFile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Pane;

public class Main extends Application {

	private Pane mainPane = null;
	private YadGlobal yg = YadGlobal.getInstance();

	@Override
	public void start(Stage primaryStage) {
		try {
//			primaryStage.getIcons().add(new Image("/images/bloodhound.png"));
			primaryStage.setTitle("YadPb-FX");
			primaryStage.setScene(createScene(loadMainPane()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() {
//		System.out.println("*** Yad Project Builder is Ending. ***");
		// Save file

		SceneInfo si = yg.sceneNav.fxmls.get(yg.scenePeek());
		if (si != null && si.controller instanceof RefreshScene) {
			RefreshScene c = (RefreshScene) si.controller;
			c.leaveScene();
		}

		Object[] objs = yg.prjList.keySet().toArray();

		java.util.List<String> saveList = new ArrayList<String>();

		for (Object o : objs) {
			String s = (String) o;
			IniFile ini = yg.prjList.get(s);
			if (ini.getChangedFlag() == true)
				saveList.add(s);
		}

		if (saveList.size() > 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
			ButtonType noButton = new ButtonType("No", ButtonData.NO);
			ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			messageBox.getButtonTypes().setAll(yesButton, noButton, cancelButton);

			messageBox.setContentText("Some projects have changed, save the projects?");

			messageBox.showAndWait().ifPresent(type -> {
				if (type.getButtonData() == ButtonData.YES) {

					try {
						Stage stage = new Stage();
						stage.setTitle("Save Project(s)");

						FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/ProjectsToSave.fxml"));

						stage.initModality(Modality.APPLICATION_MODAL);

						stage.setScene(new Scene(loader.load()));
						stage.showAndWait();

						if (yg.openPrjName != null) {
							if (yg.itemProjects.contains(yg.openPrjName) == false)
								yg.itemProjects.add(yg.openPrjName);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else if (type == ButtonType.NO) {
				} else {
					return;
				}
			});
		}
	}

	/**
	 * Loads the main fxml layout. Sets up the vista switching VistaNavigator. Loads
	 * the first vista into the fxml layout.
	 *
	 * @return the loaded pane.
	 * @throws IOException if the pane could not be loaded.
	 */
//    @SuppressWarnings("resource")
	private Pane loadMainPane() throws IOException {
//		yg.Msg("*** Yad Project Builder is Starting. ***");

		FXMLLoader loader = new FXMLLoader();

		mainPane = (Pane) loader.load(getClass().getResourceAsStream(SceneNav.MAIN)); // SceneNav

		SceneNavController mainController = loader.getController();

		yg.sceneNav.setMainController(mainController);
		yg.sceneNav.loadScene(SceneNav.YADPB);

		return mainPane;
	}

	/**
	 * Creates the main application scene.
	 *
	 * @param mainPane the main application layout.
	 *
	 * @return the created scene.
	 */
	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);

		scene.getStylesheets().setAll(getClass().getResource("application.css").toExternalForm());

		return scene;
	}
}

package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.rkw.IniFile;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tbl.Projectx;

public class YadPbController implements Initializable, RefreshScene {

//	private VBox dialogPane = null;
	private YadGlobal yg = YadGlobal.getInstance();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private VBox calendar;

	@FXML
	private HBox hbox;

	@FXML
	private AnchorPane aPane;

	@FXML
	private ListView<String> lstProjects;

	@FXML
	private ListView<String> lstDialogs;

	@FXML
	private Label lblVersion;

	@FXML
	private StackPane stackPane;

	@FXML
    void onMouseDrag(MouseEvent event) {

    }

	public void setDialogNav(Node node) {
		stackPane.getChildren().setAll(node);
	}
	
	@FXML
    void doAbout(ActionEvent event) {
		
		FXMLLoader loader = yg.loadScene(aPane, "screens/About.fxml", "About Screen", null);
		AboutController ac = (AboutController)loader.getController();
		Stage stage = (Stage)ac.getStage();
    	
    	stage.showAndWait();
    }
	
	@FXML
    void doQuit(ActionEvent event) {
		Stage stage = (Stage)aPane.getScene().getWindow();
		stage.close();
    }
	
	@FXML
    void doSaveProject(ActionEvent event) {
		Stage stage = (Stage)aPane.getScene().getWindow();
		stage.close();
    }
	
	@FXML
    void doSaveAllProjects(ActionEvent event) {
		Stage stage = (Stage)aPane.getScene().getWindow();
		stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lblVersion.setText(yg.yadPbVersion);
		
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());

		yg.itemProjects = FXCollections.observableArrayList();
		lstProjects.setItems(yg.itemProjects);
		yg.itemDialogs = FXCollections.observableArrayList();
		lstDialogs.setItems(yg.itemDialogs);

		lstDialogs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
//				System.out.println("new " + newVal + " old " + oldVal);
				if (newVal != null) {
					String[] a = newVal.split(",");
					yg.currDialog = a[0];
					yg.loadDialog(newVal);
				} else {
					yg.clearStack();
				}
			}
		});

		lstProjects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
//				System.out.println("new " + newVal + " old " + oldVal);
				if (newVal != null && newVal.equals(yg.currProject) == false) {
					yg.currIni = yg.prjList.get(newVal);
					yg.currBackupIni = yg.prjBackupList.get(newVal);
					yg.currProject = newVal;

					loadProject(newVal);

					yg.clearStack();
				} else {
					lstDialogs.getItems().clear();
				}
			}
		});
		
		ContextMenu cm = new ContextMenu();
		cm.setStyle("-fx-font-size: 14px;");
		
		MenuItem mOpenProject = new MenuItem("Open Project");
		mOpenProject.setOnAction((ActionEvent e) -> {
//			FXMLLoader loader = new FXMLLoader();

			try {
			    Stage stage = new Stage();
			    stage.setTitle("Open a Project");

			    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/ProjectOpen.fxml"));

			    stage.initModality(Modality.APPLICATION_MODAL);

			    stage.setScene(new Scene(loader.load()));
			    stage.showAndWait();

			    if (yg.openPrjName != null) {
			    	if (!yg.itemProjects.contains(yg.openPrjName))
			    		yg.itemProjects.add(yg.openPrjName);
			    	lstProjects.getSelectionModel().select(yg.itemProjects.size() - 1);
			    	loadProject(yg.openPrjName);
			    }
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		MenuItem mNewProject = new MenuItem("New Project");
		
		mNewProject.setOnAction((ActionEvent e) -> {
			
			try {
			    Stage stage = new Stage();
			    stage.setTitle("Project Script for " + yg.currProject);

			    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/ProjectNew.fxml"));

			    stage.initModality(Modality.APPLICATION_MODAL);

			    stage.setScene(new Scene(loader.load()));
			    ProjectNewController pnc = loader.getController();
			    
			    stage.showAndWait();
			    
			    Projectx prj = pnc.getProject();
			    if (prj != null) {
			    	File f = new File(yg.workDir.getAbsolutePath() + File.separator + prj.getProject() + ".yadpb");
					
					Calendar c = Calendar.getInstance();
					String createDate = String.format("%d/%02d/%02d %02d:%02d:%02d",
							c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
							c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
					
					try {
						f.createNewFile();
						FileWriter myWriter = new FileWriter(f.getAbsolutePath());
						myWriter.write("# Created by the YadPb program.\n# For project '" + prj.getProject() + "'\n# Description: " + prj.getDescription() + "\n# Created: " + createDate);
					    myWriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					File b = new File(yg.workDir + File.separator + "backups" + File.separator + f.getName());
					try {
						b.createNewFile();
						FileWriter myWriter = new FileWriter(b.getAbsolutePath());
						myWriter.write("# Created by the YadPb program.\n# For project '" + prj.getProject() + "'\n# Description: " + prj.getDescription() + "\n# Created: " + createDate);
					    myWriter.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					
					yg.sysIni.addValuePair("Projects", prj.getProject(), prj.getDescription());
					
					yg.sysIni.writeFile(true);
					
					yg.prjList.put(prj.getProject(), new IniFile(f.getAbsolutePath()));
					yg.prjBackupList.put(prj.getProject(), new IniFile(b.getAbsolutePath()));
					
					yg.currIni = yg.prjList.get(prj.getProject());
					yg.currBackupIni = yg.prjBackupList.get(prj.getProject());
					
					yg.currProject = prj.getProject();
					
					lstProjects.getItems().add(prj.getProject());
					
					ObservableList<String> prjs = lstProjects.getItems();
					
				
					for (String s : prjs) {
						if (s.equals(prj.getProject()) == true) {
							lstProjects.getSelectionModel().select(s);
							break;
						}
					}
					
					processProject();
			    }

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});

		MenuItem mCreateScript = new MenuItem("Create Script");
		mCreateScript.setOnAction((ActionEvent e) -> {
			String dlg = ProjectScript.createDialog(yg.currIni);

			if (dlg != null && dlg.isEmpty() == false) {
				try {
				    Stage stage = new Stage();
				    stage.setTitle("Project Script for " + yg.currProject);

				    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/OpenScript.fxml"));

				    stage.initModality(Modality.APPLICATION_MODAL);

				    stage.setScene(new Scene(loader.load()));
				    OpenScriptController osc = loader.getController();
				    osc.setScript(dlg);
				    stage.showAndWait();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		MenuItem mDeleteProject = new MenuItem("Delete Project");
		mDeleteProject.setOnAction((ActionEvent e) -> {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.getDialogPane().setStyle("-fx-font-size: 16px;");
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
			ButtonType noButton = new ButtonType("No", ButtonData.NO);
			messageBox.getButtonTypes().setAll(yesButton, noButton);
			
			String prj = lstProjects.getSelectionModel().getSelectedItem();

			messageBox.setContentText("Delete '" + prj + "' project?\n*** This cannot be recovered. ***");

			messageBox.showAndWait().ifPresent(type -> {
				if (type.getButtonData() == ButtonData.YES) {
					File f = new File(yg.workDir + File.separator + prj + ".yadpb");
					if (f.exists() == true) {
						f.delete();
					}
					
					lstProjects.getItems().remove(prj);
					yg.prjList.remove(prj);
					yg.prjBackupList.remove(prj);
					
					yg.sysIni.removeValuePair("Projects", prj);
					yg.sysIni.writeFile(true);
					
					yg.clearStack();
					
				} else {
					return;
				}
			});
		});

		cm.getItems().addAll(mOpenProject, mNewProject, mCreateScript, mDeleteProject);
		
		cm.setOnShowing(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				int idx = lstProjects.getSelectionModel().getSelectedIndex();
				if (idx < 0) {
					mCreateScript.setDisable(true);
					mDeleteProject.setDisable(true);
				} else {
					mCreateScript.setDisable(false);
					mDeleteProject.setDisable(false);
				}
			}
		});

		lstProjects.setContextMenu(cm);

		ContextMenu cm2 = new ContextMenu();
		cm2.setStyle("-fx-font-size: 14px;");
		MenuItem mNewDialog = new MenuItem("New Dialog");
		MenuItem mDeleteDialog = new MenuItem("Delete Dialog");

		cm2.getItems().addAll(mNewDialog, mDeleteDialog);
		
		cm2.setOnShowing(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				if (lstProjects.getSelectionModel().getSelectedIndex() >= 0) {
					if (lstDialogs.getSelectionModel().getSelectedIndex() >= 0)
						mDeleteDialog.setDisable(false);
					else
						mDeleteDialog.setDisable(true);
					mNewDialog.setDisable(false);
				} else {
					mNewDialog.setDisable(true);
					mDeleteDialog.setDisable(true);
				}
			}
		});
		
		mNewDialog.setOnAction((e) -> {
			FXMLLoader loader = yg.loadScene(aPane, "screens/NewDialog.fxml", "New Dailog", null);
			NewDialogController ndc = (NewDialogController)loader.getController();
			Stage stage = (Stage)ndc.getStage();
	    	
	    	stage.showAndWait();
	    	
	    	if (yg.newDialogName != null && yg.newDialogType != null) {
	    		yg.currIni.addSection(yg.newDialogName);
	    		yg.currIni.addValuePair(yg.newDialogName, "type", yg.newDialogType);
	    		yg.currIni.addSection(yg.newDialogName + "-General");
//	    		yg.currIni.addValuePair(yg.newDialogName + "-General", "--title", yg.newDialogName);
	    		
	    		yg.itemDialogs.add(yg.newDialogName + "," + yg.newDialogType);
	    	}
		});
		
		mDeleteDialog.setOnAction((e) -> {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.getDialogPane().setStyle("-fx-font-size: 16px;");
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
			ButtonType noButton = new ButtonType("No", ButtonData.NO);
			messageBox.getButtonTypes().setAll(yesButton, noButton);
			
			String dlg = lstDialogs.getSelectionModel().getSelectedItem();

			messageBox.setContentText("Delete '" + dlg + "' dialog?\n*** This cannot be recovered. ***");

			messageBox.showAndWait().ifPresent(type -> {
				if (type.getButtonData() == ButtonData.YES) {
					
					lstDialogs.getItems().remove(dlg);
					yg.itemDialogs.remove(dlg);
					
					String[] a = dlg.split(",");
					
					yg.currIni.removeSection(a[0]);
					yg.currIni.removeSection(a[0] + "-General");
					yg.sysIni.writeFile(true);
					
				} else {
					return;
				}
			});
		});

		lstDialogs.setContextMenu(cm2);

		yg.dialogStackPane = stackPane;
		stackPane.prefWidthProperty().bind(aPane.widthProperty());

	}

	private int loadProject(String prjName) {

		File f = new File(yg.workDir + File.separator + prjName + ".yadpb");
		if (!f.exists()) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.getDialogPane().setStyle("-fx-font-size: 16px;");
			messageBox.setTitle("Warning");
			ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
			messageBox.getDialogPane().getButtonTypes().add(type);

			messageBox.setContentText("The project '" + prjName + "' cannot be opened.");
			messageBox.showAndWait();
			return 1;
		}
		if (yg.sysIni.getBoolean("System", "backup")) {
			File b = new File(yg.backupDir.getAbsolutePath() + File.separator + f.getName());
			if (!yg.prjList.containsKey(prjName)) {
				yg.copyFile(f, b);
			}
		}

		if (yg.prjList.containsKey(prjName)) {
			yg.currIni = yg.prjList.get(prjName);
		} else {
			yg.prjList.put(prjName, new IniFile(f.getAbsolutePath()));
			yg.prjBackupList.put(prjName, new IniFile(f.getAbsolutePath()));

			yg.currIni = yg.prjList.get(prjName);
		}

		if (yg.currIni == null) {
			return 1;
		}

		processProject();

		return 0;
	}

	private int processProject() {

		yg.itemDialogs.clear();

		Object[] obj = yg.currIni.getSectionNames();

		for (Object o : obj) {
			String s = (String)o;

			if (s.endsWith("-General"))
				continue;

			String type = yg.currIni.getString(s, "type");
			yg.itemDialogs.add(s + "," + type);
		}

		return 0;
	}

	@Override
	public void refreshScene() {

	}

	@Override
	public void leaveScene() {

	}

	@Override
	public void clickIt(String text, WidgetType widgetType) {

	}

}

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.FileFilter;
import tbl.MimeFilter;

public class FileController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private AnchorPane aPane;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    @FXML
    private Button btnFilterAdd;

    @FXML
    private Button btnFilterDelete;
    
    @FXML
    private Button btnMimeAdd;

    @FXML
    private Button btnMimeDelete;
    
    @FXML
    private TableColumn<FileFilter, String> colFilterName;

    @FXML
    private TableColumn<FileFilter, String> colFilterFilter;

    @FXML
    private TableColumn<MimeFilter, String> colMimeFilter;

    @FXML
    private TableColumn<MimeFilter, String> colMimeName;

    @FXML
    private TableView<MimeFilter> tblMimeFilter;

    @FXML
    private TableView<FileFilter> tblFileFilter;

    @FXML
    private TextField txtFilterFilter;

    @FXML
    private TextField txtFilterName;
    
    @FXML
    private ToggleButton btnDirectory;

    @FXML
    private ToggleButton btnMultiple;

    @FXML
    private ToggleButton btnOverwrite;

    @FXML
    private ToggleButton btnQuoted;

    @FXML
    private ToggleButton btnSaveMode;

    @FXML
    private TextField txtFileName;

    @FXML
    private TextField txtOverwriteText;

    @FXML
    private TextField txtSeparator;
    
    @FXML
    private TextField txtMimeFilter;

    @FXML
    private TextField txtMimeName;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> filterNames = new ArrayList<String>();
    private java.util.List<String> mimeNames = new ArrayList<String>();
    
    @FXML
    void doMimeAdd(ActionEvent event) {
    	String name = txtMimeName.getText();
		if (name == null || name.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No mime name given.");
			
			messageBox.showAndWait();
			return;
		}
		
		String mime = txtMimeFilter.getText();
		if (mime == null || mime.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No mime given.");
			
			messageBox.showAndWait();
			return;
		}
		
//		System.out.println(name);
		
		if (mimeNames.contains(name) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The field name '" + name + "' is already exists.");
			
			messageBox.showAndWait();
			return;
		}
		
		tblMimeFilter.getItems().add(new MimeFilter(name, mime));
		
		txtMimeName.setText("");
		txtMimeFilter.setText("");
		mimeNames.add(name);
    }
    
    @FXML
    void doMimeDelete(ActionEvent event) {
    	String name = txtMimeName.getText();
		
		if (name == null || name.length() == 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No mime name given.");
			
			messageBox.showAndWait();
			return;
		}
		
		int n = 0;
		for (String f : mimeNames) {
			if (f.equals(name) == true) {
				break;
			}
			n++;
		}
		
		if (n >= mimeNames.size())
			return;
		
		MimeFilter mf = tblMimeFilter.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + name + "'?");
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("mf " + mf);
				tblMimeFilter.getItems().remove(mf);
				mimeNames.remove(mf.getName());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtMimeName.setText("");
		txtMimeFilter.setText("");
    }
    
    @FXML
    void doFilterAdd(ActionEvent event) {
    	String name = txtFilterName.getText();
		if (name == null || name.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			messageBox.showAndWait();
			return;
		}
		
		String filter = txtFilterFilter.getText();
		if (filter == null || filter.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No filter given.");
			
			messageBox.showAndWait();
			return;
		}
		
//		System.out.println(name);
		
		if (filterNames.contains(name) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The field name '" + name + "' is already exists.");
			
			messageBox.showAndWait();
			return;
		}
		
		tblFileFilter.getItems().add(new FileFilter(name, filter));
		
		txtFilterName.setText("");
		txtFilterFilter.setText("");
		filterNames.add(name);
    }

    @FXML
    void doFilterDelete(ActionEvent event) {
    	String name = txtFilterName.getText();
		
		if (name == null || name.length() == 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			messageBox.showAndWait();
			return;
		}
		
		int n = 0;
		for (String f : filterNames) {
			if (f.equals(name) == true) {
				break;
			}
			n++;
		}
		
		if (n >= filterNames.size())
			return;
		
		FileFilter ff = tblFileFilter.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + name + "'?");
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("ff " + ff);
				tblFileFilter.getItems().remove(ff);
				filterNames.remove(ff.getName());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtFilterName.setText("");
		txtFilterFilter.setText("");
    }
    
    @FXML
    void doMimeTypes(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Common Mime Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/sorted_mime_types.txt", 0.0, 0.0, false);
		    stage.showAndWait();
		    
		    if (yg.currMime != null) {
		    	txtMimeFilter.setText(yg.currMime);
		    }
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void doFileFilter(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Common File Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/sorted_file_types.txt", 0.0, 0.0, false);
		    stage.showAndWait();
		    
		    if (yg.currFilter != null) {
		    	txtFilterFilter.setText(yg.currFilter);
		    }
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	tblFileFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtFilterName.setText(newVal.getName());
		        txtFilterFilter.setText(newVal.getFilter());
		    }
		});
    	
    	colFilterName.setCellValueFactory(new PropertyValueFactory<FileFilter, String>("name"));
		colFilterFilter.setCellValueFactory(new PropertyValueFactory<FileFilter, String>("filter"));
		
		colFilterName.setCellFactory(TextFieldTableCell.forTableColumn());
		colFilterName.setOnEditCommit((TableColumn.CellEditEvent<FileFilter, String> t) -> {
	        ((FileFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
	        filterNames.remove(t.getOldValue());
	        filterNames.add(t.getNewValue());
	    });
		
		colFilterFilter.setCellFactory(TextFieldTableCell.forTableColumn());
//		colFilterFilter.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setFilter(e.getNewValue()));
		colFilterFilter.setOnEditCommit((TableColumn.CellEditEvent<FileFilter, String> t) -> {
	        ((FileFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFilter(t.getNewValue());
	    });
		
		tblMimeFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtMimeName.setText(newVal.getName());
		        txtMimeFilter.setText(newVal.getMime());
		    }
		});
    	
    	colMimeName.setCellValueFactory(new PropertyValueFactory<MimeFilter, String>("name"));
		colMimeFilter.setCellValueFactory(new PropertyValueFactory<MimeFilter, String>("mime"));
		
		colMimeName.setCellFactory(TextFieldTableCell.forTableColumn());
		colMimeName.setOnEditCommit((TableColumn.CellEditEvent<MimeFilter, String> t) -> {
	        ((MimeFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
	        mimeNames.remove(t.getOldValue());
	        mimeNames.add(t.getNewValue());
	    });
		
		colMimeFilter.setCellFactory(TextFieldTableCell.forTableColumn());
//		colMimeFilter.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setMime(e.getNewValue()));
		colMimeFilter.setOnEditCommit((TableColumn.CellEditEvent<MimeFilter, String> t) -> {
	        ((MimeFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
	    });
	}
    
//    private void saveFilters() {
//    	ObservableList<FileFilter> obs = tblFileFilter.getItems();
//    
//    	String filters = null;
//    	for (FileFilter ff : obs) {
//    		if (filters == null)
//    			filters = ff.getName() + "|" + ff.getFilter();
//    		else
//    			filters += "," + ff.getName() + "|" + ff.getFilter();
//    	}
//    	
//    	if (filters != null)
//    		yg.iniUpdate("filefilters", filters);
//    }
    
//    private void saveMimes() {
//    	ObservableList<MimeFilter> obs = tblMimeFilter.getItems();
//        
//    	String mimes = null;
//    	for (MimeFilter mf : obs) {
//    		if (mimes == null)
//    			mimes = mf.getName() + "|" + mf.getMime();
//    		else
//    			mimes += "," + mf.getName() + "|" + mf.getMime();
//    	}
//    	
//    	if (mimes != null)
//    		yg.iniUpdate("mimefilters", mimes);
//    }

	@Override
	public void updateDialog() {
		loadHelp("txts/file.txt");
		updateDialogs(aPane);
	}

}


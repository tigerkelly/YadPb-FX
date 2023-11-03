package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class IconsController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    @FXML
    private AnchorPane aPane;

    @FXML
    private ToggleButton btnCompact;

    @FXML
    private ToggleButton btnDescend;

    @FXML
    private ToggleButton btnGeneric;

    @FXML
    private ToggleButton btnListen;

    @FXML
    private ToggleButton btnMonitor;

    @FXML
    private Button btnReadDir;

    @FXML
    private ToggleButton btnSortByName;

    @FXML
    private ToggleButton btnSingleClick;

    @FXML
    private TextField txtItemWidth;

    @FXML
    private TextField txtReadDir;

    @FXML
    private TextField txtTerm;
    
//    private YadGlobal yg = YadGlobal.getInstance();
    
    @FXML
    void doReadDir(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog((Stage)aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtReadDir.setText(selectedFile.getAbsolutePath());
        }
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/icon.txt");
		updateDialogs(aPane);
	}

}


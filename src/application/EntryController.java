package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class EntryController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private AnchorPane aPane;
    
    @FXML
    private ToggleButton btnCompletion;

    @FXML
    private ToggleButton btnEditable;

    @FXML
    private Button btnLeftIcon;

    @FXML
    private ToggleButton btnNumOutput;

    @FXML
    private ToggleButton btnNumeric;

    @FXML
    private Button btnRightIcon;

    @FXML
    private ComboBox<String> cbPrecision;

    @FXML
    private TextField txtEntryLabel;

    @FXML
    private TextField txtEntryText;

    @FXML
    private TextField txtLeftAction;

    @FXML
    private TextField txtLeftIcon;

    @FXML
    private TextField txtRightIcon;

    @FXML
    private TextField txtRightAction;
    
    @FXML
    private Button btnGeneral;
    
//    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doLeftIcon(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Icon File");
    	File f = fileChooser.showOpenDialog(btnLeftIcon.getScene().getWindow());
    	
    	if (f != null) {
    		txtLeftIcon.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doRightIcon(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Icon File");
    	File f = fileChooser.showOpenDialog(btnRightIcon.getScene().getWindow());
    	
    	if (f != null) {
    		txtRightIcon.setText(f.getAbsolutePath());
    	}
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbPrecision.getItems().addAll("1", "2", "3", "4", "5", "6");
		cbPrecision.setValue("3");
		
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/entry.txt");
		updateDialogs(aPane);
	}

}


package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TextController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
//    private YadGlobal yg = YadGlobal.getInstance();
    
    @FXML
    private AnchorPane aPane;

    @FXML
    private ToggleButton btnEditable;

    @FXML
    private Button btnFileName;
    
    @FXML
    private ToggleButton btnShowCursor;

    @FXML
    private ToggleButton btnListen;

    @FXML
    private ToggleButton btnShowUri;

    @FXML
    private ToggleButton btnTail;

    @FXML
    private ToggleButton btnWrapText;

    @FXML
    private ComboBox<String> cbTheme;
    
    @FXML
    private ComboBox<String> cbLang;
    
    @FXML
    private TextField txtMargins;
    
    @FXML
    private ComboBox<String> cbJustify;

    @FXML
    private ColorPicker cpBgColor;

    @FXML
    private ColorPicker cpFgColor;

    @FXML
    private ColorPicker cpUriColor;

    @FXML
    private TextField txtFontName;
    
    @FXML
    private TextField txtFileName;
    
    @FXML
    void doFileName(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog((Stage)aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtFileName.setText(selectedFile.getAbsolutePath());
        }
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbJustify.getItems().addAll("Left", "Right", "Center", "Fill");
		cbJustify.getSelectionModel().select(0);
		
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/text.txt");
		updateDialogs(aPane);
	}

}


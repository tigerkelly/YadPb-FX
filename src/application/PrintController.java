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
import javafx.stage.Stage;

public class PrintController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private AnchorPane aPane;
    
    @FXML
    private ComboBox<String> cbPrintType;

    @FXML
    private ToggleButton btnAddPreview;

    @FXML
    private Button btnFileName;

    @FXML
    private Button btnGeneral;

    @FXML
    private ToggleButton btnHeaders;

    @FXML
    private TextField txtFileName;

    @FXML
    private TextField txtFontName;

    @FXML
    void doFileName(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog((Stage)aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtFileName.setText(selectedFile.getAbsolutePath());
        }
    }

    
//    private YadGlobal yg = YadGlobal.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbPrintType.getItems().addAll("Text", "Image", "Raw");
		cbPrintType.getSelectionModel().select("Text");
		
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/print.txt");
		updateDialogs(aPane);
	}

}


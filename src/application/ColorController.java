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

public class ColorController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane aPane;

    @FXML
    private Label lblDialog;

    @FXML
    private ToggleButton btnAlpha;

    @FXML
    private ToggleButton btnExpand;

    @FXML
    private ToggleButton btnExtra;

    @FXML
    private Button btnGeneral;

    @FXML
    private ToggleButton btnGtkPalette;

    @FXML
    private Button btnPalette;

    @FXML
    private ComboBox<String> cbMode;

    @FXML
    private ColorPicker cpInitColor;

    @FXML
    private TextField txtPalette;

//    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doPalette(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtPalette.setText(selectedFile.getAbsolutePath());
        }
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbMode.getItems().addAll("Hex", "RGB");
		cbMode.getSelectionModel().select(0);

		javafx.scene.paint.Color c = javafx.scene.paint.Color.web("#00000000");
		cpInitColor.setValue(c);

		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/color.txt");
		
		updateDialogs(aPane);
	}

}


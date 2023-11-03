package application;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class CalendarController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnArrow;

    @FXML
    private Button btnGeneral;

    @FXML
    private ToggleButton btnShowWeeks;

    @FXML
    private DatePicker dpDefaultDate;

    @FXML
    private Label lblDialog;

    @FXML
    private TextField txtDateFormat;

    @FXML
    private TextField txtDetailFile;

    @FXML
    private AnchorPane aPane;

//     private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doFileDialog(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtDetailFile.setText(selectedFile.getAbsolutePath());
        }
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	dpDefaultDate.setValue(LocalDate.now());

    	aPane.getStylesheets().add(getClass().getResource("application.css").toString());

	}

	@Override
	public void updateDialog() {
		loadHelp("txts/calendar.txt");
		updateDialogs(aPane);

	}

}


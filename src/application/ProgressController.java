package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class ProgressController extends HandleActions implements Initializable, DialogInterface {

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
    private TextField txtLogHeight;

    @FXML
    private TextField txtLogText;

    @FXML
    private TextField txtPercentage;

    @FXML
    private TextField txtProgressText;
    
    @FXML
    private ToggleButton btnAutoClose;

    @FXML
    private ToggleButton btnAutoKill;

    @FXML
    private ToggleButton btnLogExpand;

    @FXML
    private ToggleButton btnLogOnTop;

    @FXML
    private ToggleButton btnPulsate;

    @FXML
    private ToggleButton btnRightToLeft;
    
    @FXML
    private ToggleButton btnEnableLog;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/progress.txt");
		updateDialogs(aPane);
	}

}


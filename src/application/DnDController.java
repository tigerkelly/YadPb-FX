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

public class DnDController extends HandleActions implements Initializable, DialogInterface {

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
    private ToggleButton btnTooltip;

    @FXML
    private TextField txtCommand;

    @FXML
    private TextField txtExitOnDrop;
    
//    private YadGlobal yg = YadGlobal.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/dnd.txt");
		updateDialogs(aPane);
	}

}


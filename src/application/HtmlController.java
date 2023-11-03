package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HtmlController extends HandleActions implements Initializable, DialogInterface {

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
    private ToggleButton btnBrowser;

    @FXML
    private ToggleButton btnPrintUri;

    @FXML
    private Button btnUrl;

    @FXML
    private ComboBox<String> cbEncoding;

    @FXML
    private TextField txtUriHandler;

    @FXML
    private TextField txtUrl;
    
    @FXML
    private TextField txtMime;

    @FXML
    private TextField txtUserAgent;

    @FXML
    private TextField txtUserStyle;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("General Settings for dialog " + yg.currDialog);
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/GeneralSettings.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));  
		    stage.showAndWait();
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbEncoding.getItems().add("UTF-8");
		cbEncoding.getSelectionModel().select(0);
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/html.txt");
		updateDialogs(aPane);
	}

}


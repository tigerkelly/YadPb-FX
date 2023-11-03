package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class FontController extends HandleActions implements Initializable, DialogInterface {

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
    private TextField txtFontName;

    @FXML
    private TextField txtSeparator;
    
    @FXML
    private ToggleButton btnPreview;

    @FXML
    private ToggleButton btnQuoted;

    @FXML
    private ToggleButton btnSeparateOutput;

    
//    private YadGlobal yg = YadGlobal.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	ChangeListener limitCount = new ChangeListener(txtSeparator, 1);
    	txtSeparator.textProperty().addListener(limitCount);
	}
    
    public class ChangeListener implements javafx.beans.value.ChangeListener<String> {


        private int maxLength;
        private TextField textField;


        public ChangeListener(TextField textField, int maxLength) {
            this.textField= textField;
            this.maxLength = maxLength;
        }


        public int getMaxLength() {
            return maxLength;
        }


        @Override
        public void changed(ObservableValue<? extends String> ov, String oldValue,
                String newValue) {


            if (newValue == null) {
                return;
            }


            if (newValue.length() > maxLength) {
                textField.setText(oldValue);
            } else {
                textField.setText(newValue);
            }
        }


    }

	@Override
	public void updateDialog() {
		loadHelp("txts/FONT.txt");
		updateDialogs(aPane);
	}

}


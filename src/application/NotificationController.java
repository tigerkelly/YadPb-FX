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

public class NotificationController extends HandleActions implements Initializable, DialogInterface{

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
    private ToggleButton btnHidden;

    @FXML
    private ToggleButton btnListen;

    @FXML
    private ToggleButton btnNoMiddle;

    @FXML
    private TextField txtCommand;

    @FXML
    private TextField txtItemSep;

    @FXML
    private TextField txtMenu;

    @FXML
    private TextField txtSep;
    
    @FXML
    private TextField txtIconSize;
    
//    private YadGlobal yg = YadGlobal.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	ChangeListener limitCount = new ChangeListener(txtSep, 1);
    	txtSep.textProperty().addListener(limitCount);
    	
    	ChangeListener limitCount2 = new ChangeListener(txtItemSep, 1);
    	txtItemSep.textProperty().addListener(limitCount2);
	}
    
	public class ChangeListener implements javafx.beans.value.ChangeListener<String> {

		private int maxLength;
		private TextField textField;

		public ChangeListener(TextField textField, int maxLength) {
			this.textField = textField;
			this.maxLength = maxLength;
		}

		public int getMaxLength() {
			return maxLength;
		}

		@Override
		public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {

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
		loadHelp("txts/notification.txt");
		updateDialogs(aPane);
	}

}


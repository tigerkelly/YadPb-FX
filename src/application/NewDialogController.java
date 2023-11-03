package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewDialogController implements Initializable {
	
	private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cbDialogType;

    @FXML
    private TextField tfDialogName;

    @FXML
    void doBtnCancel(ActionEvent event) {
    	yg.newDialogName = null;
    	yg.newDialogType = null;
    	
    	Stage stage = (Stage)aPane.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void doBtnSave(ActionEvent event) {
    	String dialogName = tfDialogName.getText();
    	String dialogType = cbDialogType.getValue();
    	
    	if (dialogName == null || dialogName.isBlank() == true)
    		return;
    	if (dialogType == null || dialogType.isBlank() == true)
    		return;
    	
    	yg.newDialogName = dialogName;
    	yg.newDialogType = dialogType;
    	
    	Stage stage = (Stage)aPane.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> types = cbDialogType.getItems();
		
		List<String> keys = new ArrayList<>(yg.dialogNames.keySet());
		Collections.sort(keys);
		
		for (String key : keys) {
			types.add(key);
		}
	}
	
	public Stage getStage() {
		return (Stage)aPane.getScene().getWindow();
	}

}

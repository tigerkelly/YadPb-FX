package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AboutController implements Initializable {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnClose;

    @FXML
    void doBtnClose(ActionEvent event) {
    	Stage stage = (Stage)aPane.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public Stage getStage() {
		return (Stage)aPane.getScene().getWindow();
	}

}
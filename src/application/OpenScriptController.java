package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class OpenScriptController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnSave;

    @FXML
    private TextArea taScript;
    
    @FXML
    private AnchorPane aPane;

    @FXML
    void doClose(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void doSave(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
    	File selectedFile = fileChooser.showSaveDialog((Stage)aPane.getScene().getWindow());
        if (selectedFile != null) {
        	String script = taScript.getText();
        	
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
				writer.write(script);
        	    
        	    writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
    	Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    public void setScript(String script) {
    	String info = "#\n";
    	info += "# NOTE:\n# The dialogs in this file are meant to be run within ";
    	info += "a BASH or SH script file.\n# The '|' and '!' seperators cause issues when ";
    	info += "run at the command prompt.\n\n";
    	taScript.setText(info + script);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}
}
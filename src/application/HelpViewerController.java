package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelpViewerController {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea taHelpText;

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void setFileName(String fileName, double width, double height, Boolean flag) {
    	
    	if (width != 0.0 && height != 0.0) {
	    	aPane.setPrefWidth(width);
			aPane.setPrefHeight(height);
    	}
    	
    	try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line = null;
			String txt = null;
			while ((line = br.readLine()) != null) {
				if (txt == null)
					txt = line + "\n";
				else
					txt += line + "\n";
			}
			br.close();
			if (txt != null)
				taHelpText.setText(txt);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }
}
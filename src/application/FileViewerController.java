package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FileViewerController extends HandleActions implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane aPane;
    
    @FXML
    private Button btnSelect;

    @FXML
    private Button btnCancel;

    @FXML
    private ListView<String> lstText;
    
    @FXML
	private TextField urlTextField;
    
    @FXML
    private Tooltip ttListView;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private String fileName = null;

    @FXML
    void doSelect(ActionEvent event) {
    	Stage stage = (Stage) btnSelect.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void doCancel(ActionEvent event) {
    	yg.currMime = null;
    	yg.currFilter = null;
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	lstText.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
//				System.out.println("new " + newVal + " old " + oldVal);
				if (newVal != null) {
					String[] a = newVal.split("\\s");
					if (fileName.endsWith("sorted_mime_types.txt")) {
						yg.currMime = a[a.length - 1];
					} else if (fileName.endsWith("sorted_file_types.txt")) {
						yg.currFilter = null;
						for (int i = 1; i < a.length; i++) {
							if (yg.currFilter == null)
								yg.currFilter = a[i];
							else
								yg.currFilter += " " + a[i];
						}
					} else if (fileName.endsWith("type_help.txt")) {
						
					} else if (fileName.endsWith("icon_info.txt")) {
						
					} else if (fileName.endsWith("list_type.txt")) {
						
					}
				}
			}
		});
    	
		lstText.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					String item = lstText.getSelectionModel().getSelectedItem();
					String[] a = item.split("\\s");
					if (fileName.endsWith("sorted_mime_types.txt")) {
						yg.currMime = a[a.length - 1];
					} else if (fileName.endsWith("sorted_file_types.txt")) {
						yg.currFilter = null;
						for (int i = 1; i < a.length; i++) {
							if (yg.currFilter == null)
								yg.currFilter = a[i];
							else
								yg.currFilter += " " + a[i];
						}
					} else if (fileName.endsWith("type_help.txt")) {

					} else if (fileName.endsWith("icon_info.txt")) {
						
					} else if (fileName.endsWith("list_type.txt")) {
						
					}
					Stage stage = (Stage) btnCancel.getScene().getWindow();
					stage.close();
				}
			}
		});
    }
    
    public void setFileName(String fileName, double width, double height, Boolean flag) {
    	this.fileName = fileName;
    	
    	if (width != 0.0 && height != 0.0) {
	    	aPane.setPrefWidth(width);
			aPane.setPrefHeight(height);
			btnSelect.setVisible(flag);
			lstText.setTooltip(null);
    	}
		
//    	if (fileName.endsWith("sorted_file_types.txt")) {
//    		aPane.setPrefWidth(300.0);
//    		aPane.setPrefHeight(300.0);
//    	} else if (fileName.endsWith("type_help.txt")) {
//    		aPane.setPrefWidth(900.0);
//    		aPane.setPrefHeight(600.0);
//    		btnSelect.setVisible(false);
//    		lstText.setTooltip(null);
//    	} else if (fileName.endsWith("icon_info.txt")) {
//    		aPane.setPrefWidth(900.0);
//    		aPane.setPrefHeight(600.0);
//    		btnSelect.setVisible(false);
//    		lstText.setTooltip(null);
//		} else if (fileName.endsWith("list_type.txt")) {
//    		aPane.setPrefWidth(900.0);
//    		aPane.setPrefHeight(600.0);
//    		btnSelect.setVisible(false);
//    		lstText.setTooltip(null);
//    	}
    	
    	try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line = null;
			while ((line = br.readLine()) != null) {
				lstText.getItems().add(line);
			}
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }

}

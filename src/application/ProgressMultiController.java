package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import tbl.ColumnType;

public class ProgressMultiController extends HandleActions implements Initializable, DialogInterface {

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
    private ToggleButton btnAutoClose;

    @FXML
    private ToggleButton btnAutoKill;

    @FXML
    private Button btnProgressAdd;

    @FXML
    private Button btnProgressDelete;

    @FXML
    private ToggleButton btnVertical;

    @FXML
    private ComboBox<String> cbAlign;

    @FXML
    private ComboBox<String> cbProgressType;

    @FXML
    private TableColumn<ColumnType, String> colName;

    @FXML
    private TableColumn<ColumnType, String> colType;

    @FXML
    private TableView<ColumnType> tblProgressBars;

    @FXML
    private TextField txtProgressName;

    @FXML
    private TextField txtWatchBar;

    @FXML
    void doProgressAdd(ActionEvent event) {
    	String txt = txtProgressName.getText();
		if (txt == null || txt.length() <= 0)
			return;
		
//		System.out.println(txt);
		
		if (barNames.contains(txt) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The progress bar name '" + txt + "' is already exists.");
			
			messageBox.showAndWait();
			return;
		}
		
		tblProgressBars.getItems().add(new ColumnType(txt, cbProgressType.getSelectionModel().getSelectedItem()));
		
		txtProgressName.setText("");
		cbProgressType.getSelectionModel().select(0);
		barNames.add(txt);
    }

    @FXML
    void doProgressDelete(ActionEvent event) {
    	String txt = txtProgressName.getText();
		
		if (txt == null || txt.length() == 0)
			return;
		
		int n = 0;
		for (String f : barNames) {
			if (f.equals(txt) == true) {
				break;
			}
			n++;
		}
		
		if (n >= barNames.size())
			return;
		
		ColumnType ct = tblProgressBars.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + txt + "'?");
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("ct " + ct);
				tblProgressBars.getItems().remove(ct);
				barNames.remove(ct.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtProgressName.setText("");
		cbProgressType.getSelectionModel().select(0);
    }
    
//    private YadGlobal yg = YadGlobal.getInstance();
    
    private java.util.List<String> barNames = new ArrayList<String>();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbProgressType.getItems().addAll("NORM", "RTL", "Pulse");
		cbProgressType.getSelectionModel().select(0);
		
		cbAlign.getItems().addAll("Left", "Center", "Right");
		cbAlign.getSelectionModel().select(0);
		
		tblProgressBars.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
//		        System.out.println(newVal);
		        txtProgressName.setText(newVal.getText());
		        cbProgressType.getSelectionModel().select(newVal.getType());
		    }
		});
		
		colName.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("type"));
		
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        ((ColumnType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setText(t.getNewValue());
	        barNames.remove(t.getOldValue());
	        barNames.add(t.getNewValue());
	    });
		
		colType.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList("NORM", "RTL", "PULSE")));
		
		colType.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        ((ColumnType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setType(t.getNewValue());
	    });
		
		tblProgressBars.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtProgressName.setText(newVal.getText());
		        cbProgressType.getSelectionModel().select(newVal.getType());
		    }
		});
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/multiprogress.txt");
		updateDialogs(aPane);
	}

}


package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.ColumnType;

public class ListController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> columnNames = new ArrayList<String>();
  	private String[] types = {
  			"Checkbox-CHK",
  			"Float-FLT",
  			"Hidden-HD",
  			"Image-IMG",
  			"Numeric-NUM",
  			"Progress Bar-BAR",
  			"Radio Button-RD",
  			"Size Column-SZ",
  			"Text-TXT",
  			"Tooltip-TIP"
  			};
  	
  	 @FXML
     private AnchorPane aPane;

     @FXML
     private ToggleButton btnAddOnTop;

     @FXML
     private ToggleButton btnCheckList;

     @FXML
     private Button btnColumnHelp;

     @FXML
     private ToggleButton btnEditable;

     @FXML
     private Button btnGeneral;
     
     @FXML
     private ToggleButton btnListen;

     @FXML
     private ToggleButton btnIecFormat;

     @FXML
     private ToggleButton btnMultiple;

     @FXML
     private ToggleButton btnNoClick;

     @FXML
     private ToggleButton btnNoHeaders;

     @FXML
     private ToggleButton btnNoRulesHint;

     @FXML
     private ToggleButton btnNoSelection;

     @FXML
     private ToggleButton btnPrintAll;

     @FXML
     private ToggleButton btnQuotedOutput;

     @FXML
     private ToggleButton btnRadioList;

     @FXML
     private ToggleButton btnRegexSearch;

     @FXML
     private ToggleButton btnTail;

     @FXML
     private ComboBox<String> cbColumnType;
     
     @FXML
     private ComboBox<String> cbGridLines;
     
     @FXML
     private ComboBox<String> cbEllipsize;

     @FXML
     private TableColumn<ColumnType, String> colName;

     @FXML
     private TableColumn<ColumnType, String> colType;

     @FXML
     private TableView<ColumnType> tblColumns;
     
     @FXML
     private TextField txtAddAction;

     @FXML
     private TextField txtColumnName;

     @FXML
     private TextField txtDClickAction;

     @FXML
     private TextField txtEditableCols;

     @FXML
     private TextField txtEllipsizeCols;

     @FXML
     private TextField txtExpand;

     @FXML
     private TextField txtHide;

     @FXML
     private TextField txtLimit;

     @FXML
     private ComboBox<String> cbPrecision;

     @FXML
     private TextField txtPrintCol;

     @FXML
     private TextField txtSearchCol;

     @FXML
     private TextField txtSelectAction;

     @FXML
     private TextField txtSepCol;

     @FXML
     private TextField txtSepValue;

     @FXML
     private TextField txtSeparator;

     @FXML
     private TextField txtTooltip;

     @FXML
     private TextField txtWrapCols;

     @FXML
     private TextField txtWrapWidth;
     
     @FXML
     void doColumnAdd(ActionEvent event) {
    	String txt = txtColumnName.getText();
 		if (txt == null || txt.length() <= 0) {
 			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			messageBox.showAndWait();
 			return;
 		}
 		
// 		System.out.println(txt);
 		
 		if (columnNames.contains(txt) == true) {
 			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
 			messageBox.setTitle("Warning");
 			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
 			messageBox.getButtonTypes().setAll(yesButton);

 			messageBox.setContentText("The column name '" + txt + "' is already exists.");
 			
 			messageBox.showAndWait();
 			return;
 		}
 		
 		tblColumns.getItems().add(new ColumnType(txt, cbColumnType.getSelectionModel().getSelectedItem()));
 		
 		txtColumnName.setText("");
 		cbColumnType.getSelectionModel().select(8);
 		columnNames.add(txt);
     }
     
     @FXML
     void doColumnDelete(ActionEvent event) {
    	 String txt = txtColumnName.getText();
 		
 		if (txt == null || txt.length() == 0) {
 			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			messageBox.showAndWait();
 			return;
 		}
 		
 		int n = 0;
 		for (String f : columnNames) {
 			if (f.equals(txt) == true) {
 				break;
 			}
 			n++;
 		}
 		
 		if (n >= columnNames.size())
 			return;
 		
 		ColumnType ct = tblColumns.getItems().get(n);
 		
 		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
 		messageBox.setTitle("Warning");
 		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
 		ButtonType noButton = new ButtonType("No", ButtonData.NO);
 		messageBox.getButtonTypes().setAll(yesButton, noButton);
 		
 		messageBox.setContentText("Delete '" + txt + "'?");
 		
 		messageBox.showAndWait().ifPresent(type -> {
 			if (type.getButtonData() == ButtonData.YES) {
// 				System.out.println("ct " + ct);
 				tblColumns.getItems().remove(ct);
 				columnNames.remove(ct.getText());
 			} else if (type == ButtonType.NO) {
 				return;
 			}
 		});
 		
 		txtColumnName.setText("");
 		cbColumnType.getSelectionModel().select(8);
     }

     @FXML
     void doColumnType(ActionEvent event) {
//    	 System.out.println(lblDialog.getText());
        	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Column Types Help");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/list_type.txt", 900.0, 600.0, false);
		    stage.showAndWait();
		    
		    if (yg.currColumnType != null) {
		    	cbColumnType.getSelectionModel().select(yg.currColumnType);
		    }
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
     }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbColumnType.getItems().addAll(types);
		cbColumnType.getSelectionModel().select(8);
		
		cbPrecision.getItems().addAll("1", "2", "3", "4", "5", "6");
		cbPrecision.setValue("3");
		
		colName.setCellValueFactory(new PropertyValueFactory<>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		colName.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("type"));
		
		colName.setCellFactory(TextFieldTableCell.<ColumnType>forTableColumn());
//		colName.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setText(e.getNewValue()));
		
		colName.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        ((ColumnType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setText(t.getNewValue());
	        columnNames.remove(t.getOldValue());
	        columnNames.add(t.getNewValue());
	    });
		
		colType.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(types)));
//		colType.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue()));
		
		colType.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        ((ColumnType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setType(t.getNewValue());
	    });
		
		tblColumns.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtColumnName.setText(newVal.getText());
		        cbColumnType.getSelectionModel().select(newVal.getType());
		    }
		});
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/list.txt");
		updateDialogs(aPane);
	}

}


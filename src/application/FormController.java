package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.TextType;

public class FormController extends HandleActions implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    @FXML
    private TitledPane titledPane;
    
    @FXML
    private AnchorPane aPane;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> fieldNames = new ArrayList<String>();
	
	private final int defaultType = 20;
    
    @FXML
    private Button btnAdd;

    @FXML
    private ToggleButton btnCycle;

    @FXML
    private Button btnDelete;

    @FXML
    private ToggleButton btnOutputByRow;

    @FXML
    private ToggleButton btnQuotedOutput;

    @FXML
    private ToggleButton btnScroll;

    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnTypeHelp;

    @FXML
    private ComboBox<String> cbAlign;

    @FXML
    private ComboBox<String> cbComplete;

    @FXML
    private ComboBox<String> cbPrecision;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private TableColumn<TextType, String> colText;

    @FXML
    private TableColumn<TextType, String> colType;
    
    @FXML
    private TableColumn<TextType, String> colValues;

    @FXML
    private TableView<TextType> tblFields;

    @FXML
    private TextField txtColumns;

    @FXML
    private TextField txtFocus;

    @FXML
    private TextField txtFormat;

    @FXML
    private TextField txtItemSeparator;

    @FXML
    private TextField txtNum;

    @FXML
    private TextField txtSeparator;

    @FXML
    private TextField txtText;
    
    @FXML
    private TextField txtValues;
    
    @FXML
    void doBtnLabelHelp(ActionEvent event) {
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Field Label Syntax");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/HelpViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    HelpViewerController controller = loader.<HelpViewerController>getController();
		    controller.setFileName("txts/field_label_syntax.txt", 0.0, 0.0, false);
		    stage.showAndWait();
		    
//		    if (yg.currFilter != null) {
//		    	txtFilterFilter.setText(yg.currFilter);
//		    }
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void doTypeHelp(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Field Types Help");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/HelpViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    HelpViewerController controller = loader.<HelpViewerController>getController();
		    controller.setFileName("txts/type_help.txt", 900.0, 600.0, false);
		    stage.showAndWait();
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void doButtonAdd(ActionEvent event) {
    	String txt = txtText.getText();
		if (txt == null || txt.length() <= 0)
			return;
		
//		System.out.println(txt);
		
		if (fieldNames.contains(txt) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The field name '" + txt + "' is already exists.");
			
			messageBox.showAndWait();
			return;
		}
		
		tblFields.getItems().add(new TextType(txt, cbType.getSelectionModel().getSelectedItem(), txtValues.getText()));
		
		txtText.setText("");
		cbType.getSelectionModel().select(defaultType);
		fieldNames.add(txt);
    }

    @FXML
    void doButtonDelete(ActionEvent event) {
    	String txt = txtText.getText();
		
		if (txt == null || txt.length() == 0)
			return;
		
		int n = 0;
		for (String f : fieldNames) {
			if (f.equals(txt) == true) {
				break;
			}
			n++;
		}
		
		if (n >= fieldNames.size())
			return;
		
		TextType tt = tblFields.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + txt + "'?");
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("tt " + tt);
				tblFields.getItems().remove(tt);
				fieldNames.remove(tt.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtText.setText("");
		cbType.getSelectionModel().select(defaultType);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbType.getItems().addAll(yg.fldTypes);
		cbType.getSelectionModel().select(defaultType);
		
		cbAlign.getItems().addAll("Left", "Center", "Right");
		cbAlign.getSelectionModel().select(0);
		
		cbPrecision.getItems().addAll("1","2","3","4","5","6");
		cbPrecision.getSelectionModel().select(2);
		
		cbComplete.getItems().addAll("Any", "All", "Regex");
		cbComplete.getSelectionModel().select(0);
		
		tblFields.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
//		        System.out.println(newVal.getValues());
		        txtText.setText(newVal.getText());
		        cbType.getSelectionModel().select(newVal.getType());
		        txtValues.setText(newVal.getValues());
		    }
		});
		
		colText.setCellValueFactory(new PropertyValueFactory<TextType, String>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<TextType, String>("type"));
		colValues.setCellValueFactory(new PropertyValueFactory<TextType, String>("values"));
		
		colText.setCellFactory(TextFieldTableCell.forTableColumn());
		colText.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setText(e.getNewValue()));
		
		colType.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(yg.fldTypes)));
		colType.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue()));
		
		colValues.setCellFactory(TextFieldTableCell.forTableColumn());
		colValues.setOnEditCommit((e) -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setValues(e.getNewValue());
		});
		ChangeListener limitCount = new ChangeListener(txtSeparator, 1);
    	txtSeparator.textProperty().addListener(limitCount);
    	
    	ChangeListener limitCount2 = new ChangeListener(txtItemSeparator, 1);
    	txtItemSeparator.textProperty().addListener(limitCount2);
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
    
//    private void saveButtons() {
//    	ObservableList<TextType> obs = tblFields.getItems();
//    
//    	String fields = null;
//    	for (TextType tt : obs) {
//    		if (fields == null)
//    			fields = tt.getText() + ":" + tt.getType() + ":" + tt.getValues();
//    		else
//    			fields += "," + tt.getText() + ":" + tt.getType() + ":" + tt.getValues();
//    	}
//    	
//    	if (fields != null)
//    		yg.iniUpdate("fields", fields);
//    }

	@Override
	public void updateDialog() {
		loadHelp("txts/FORM.txt");
		updateDialogs(aPane);
	}

}


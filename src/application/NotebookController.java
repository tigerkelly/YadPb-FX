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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import tbl.TabInfo;

public class NotebookController extends HandleActions implements Initializable, DialogInterface {

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
    private Button btnTabAdd;

    @FXML
    private Button btnTabDelete;

    @FXML
    private ComboBox<String> cbTabIcon;

    @FXML
    private ComboBox<String> cbTabPosition;

    @FXML
    private TableColumn<TabInfo, String> colIcon;

    @FXML
    private TableColumn<TabInfo, String> colText;

    @FXML
    private TableColumn<TabInfo, String> colTooltip;

    @FXML
    private TableView<TabInfo> tblTabs;

    @FXML
    private TextField txtActiveTab;

    @FXML
    private TextField txtKey;

    @FXML
    private TextField txtTabBorders;

    @FXML
    private TextField txtTabText;

    @FXML
    private TextField txtTabTooltip;

    @FXML
    void doTabAdd(ActionEvent event) {
    	String name = txtTabText.getText();
		if (name == null || name.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			messageBox.showAndWait();
			return;
		}
		
		String icon = cbTabIcon.getSelectionModel().getSelectedItem();
		String tooltip = txtTabTooltip.getText();
//		System.out.println(name);
		
		if (tabNames.contains(name) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The tab name '" + name + "' is already exists.");
			
			messageBox.showAndWait();
			return;
		}
		
		tblTabs.getItems().add(new TabInfo(name, icon, tooltip));
		
		txtTabText.setText("");
		cbTabIcon.getSelectionModel().select(0);
		txtTabTooltip.setText("");
		tabNames.add(name);
    }

    @FXML
    void doTabDelete(ActionEvent event) {
    	String name = txtTabText.getText();
		
		if (name == null || name.length() == 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			messageBox.showAndWait();
			return;
		}
		
		int n = 0;
		for (String f : tabNames) {
			if (f.equals(name) == true) {
				break;
			}
			n++;
		}
		
		if (n >= tabNames.size())
			return;
		
		TabInfo ti = tblTabs.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + name + "'?");
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("ti " + ti);
				tblTabs.getItems().remove(ti);
				tabNames.remove(ti.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtTabText.setText("");
		cbTabIcon.getSelectionModel().select(0);
		txtTabTooltip.setText("");
    }
    
//    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> tabNames = new ArrayList<String>();
	private String[] iconTypes = {"None", "help-about", "list-add", "gtk-apply", "gtk-cancel", "gtk-close", "document-clear", "window-close", "gtk-edit", "system-run", "gtk-no", "gtk-ok", "document-open", "document-print", "application-exit", "view-refresh", "list-remove", "document-save", "system-search", "gtk-preferences", "gtk-yes"};

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbTabPosition.getItems().addAll("Top", "Bottom", "Left", "Right");
		cbTabPosition.getSelectionModel().select(0);
		
    	cbTabIcon.getItems().addAll(iconTypes);
		cbTabIcon.getSelectionModel().select(0);
		
		tblTabs.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
//		        System.out.println(newVal);
		        txtTabText.setText(newVal.getText());
		        cbTabIcon.getSelectionModel().select(newVal.getIcon());
		        txtTabTooltip.setText(newVal.getTooltip());
		    }
		});
		
		colText.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("text"));
		colIcon.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("icon"));
		colTooltip.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("tooltip"));
		
		colText.setCellFactory(TextFieldTableCell.forTableColumn());
		colText.setOnEditCommit((TableColumn.CellEditEvent<TabInfo, String> t) -> {
	        ((TabInfo) t.getTableView().getItems().get(t.getTablePosition().getRow())).setText(t.getNewValue());
	        tabNames.remove(t.getOldValue());
	        tabNames.add(t.getNewValue());
	    });
		
		colIcon.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(iconTypes)));
		colIcon.setOnEditCommit((TableColumn.CellEditEvent<TabInfo, String> t) -> {
	        ((TabInfo) t.getTableView().getItems().get(t.getTablePosition().getRow())).setIcon(t.getNewValue());
	    });
		
		colTooltip.setCellFactory(TextFieldTableCell.forTableColumn());
		colTooltip.setOnEditCommit((TableColumn.CellEditEvent<TabInfo, String> t) -> {
	        ((TabInfo) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTooltip(t.getNewValue());
	    });
	}

	@Override
	public void updateDialog() {
		loadHelp("txts/notebook.txt");
		updateDialogs(aPane);
	}

}


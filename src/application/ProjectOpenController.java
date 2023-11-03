package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tbl.Projectx;

public class ProjectOpenController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOpen;

    @FXML
    private TableView<Projectx> tblProjects;

    @FXML
    private TextField txtPath;

    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doCancel(ActionEvent event) {
    	yg.openPrjName = null;
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doOpenProject(ActionEvent event) {
    	TableViewSelectionModel<Projectx> selectionModel = tblProjects.getSelectionModel();
    	ObservableList<Projectx> selectedItems = selectionModel.getSelectedItems();
    	yg.openPrjName = ((Projectx)selectedItems.get(0)).getProject();
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	TableColumn<Projectx, String> colProject = new TableColumn<>("Project");
    	colProject.setPrefWidth(200.0);
    	TableColumn<Projectx, String> colDesc = new TableColumn<>("Description");
    	colDesc.setPrefWidth(300.0);
    	
    	colProject.setCellValueFactory(new PropertyValueFactory<>("project"));
    	colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
    	
    	tblProjects.getColumns().add(colProject);
    	tblProjects.getColumns().add(colDesc);
    	
    	tblProjects.setRowFactory(tv -> {
            TableRow<Projectx> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (row.isEmpty() == false) ) {
                    Projectx rowData = row.getItem();
//                    System.out.println("Double click on: " + rowData.getProject());
                    yg.openPrjName = rowData.getProject();
                	Stage stage = (Stage) btnCancel.getScene().getWindow();
                    stage.close();
                }
            });
            return row ;
        });

    	
    	Object[] keys = yg.sysIni.getSectionKeys("Projects");
		
		for (Object o : keys) {
			String k = (String)o;
			
			tblProjects.getItems().add(new Projectx(k, yg.sysIni.getString("Projects", k)));
		}
		
	}

}


package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.DialogButton;

public class GeneralSettingsController extends HandleActions implements Initializable {

	@FXML
	private AnchorPane aPane;
	
    @FXML
    private Button btnAdd;

    @FXML
    private ToggleButton btnAlwaysPrint;

    @FXML
    private Button btnCancel;

    @FXML
    private ToggleButton btnCenterWindow;

    @FXML
    private ToggleButton btnCloseOnFocus;

    @FXML
    private Button btnDelete;
    
    @FXML
    private ToggleButton tbPrintXid;
    
    @FXML
    private ToggleButton tbSpellCheck;

    @FXML
    private ToggleButton btnEnableSpell;

    @FXML
    private ToggleButton btnEscapeOk;

    @FXML
    private ToggleButton btnExpander;

    @FXML
    private ToggleButton btnFixed;

    @FXML
    private ToggleButton btnFullScreen;
    
    @FXML
    private Button btnPrintXid;

    @FXML
    private Button btnGtkrc;

    @FXML
    private Button btnIamgePath;

    @FXML
    private Button btnIcon;

    @FXML
    private Button btnImage;

    @FXML
    private ToggleButton btnKillParent;

    @FXML
    private ToggleButton btnMaximized;

    @FXML
    private ToggleButton btnMouse;

    @FXML
    private ToggleButton btnNoEscape;

    @FXML
    private ToggleButton btnNoFocus;

    @FXML
    private ToggleButton btnNoMarkup;

    @FXML
    private ToggleButton btnNoButtons;

    @FXML
    private ToggleButton btnOnTop;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnRest;
    
    @FXML
    private Button btnIconHelp;

    @FXML
    private Button btnSave;

    @FXML
    private ToggleButton btnSelectable;

    @FXML
    private ToggleButton btnSkipTaskbar;

    @FXML
    private ToggleButton btnSplash;

    @FXML
    private ToggleButton btnSticky;

    @FXML
    private ToggleButton btnTimeout;

    @FXML
    private ToggleButton btnUndecorated;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cbButtonIcon;

    @FXML
    private ComboBox<String> cbButtonLayout;

    @FXML
    private ComboBox<String> cbHScrollPolicy;

    @FXML
    private ComboBox<String> cbKillSignal;

    @FXML
    private ComboBox<String> cbTextAlign;

    @FXML
    private ComboBox<String> cbTimeoutPosition;

    @FXML
    private ComboBox<String> cbVScrollPolicy;

    @FXML
    private Label lblDialog;

    @FXML
    private TableView<DialogButton> tblButtons;
    
    @FXML
    private TextField txtPrintXid;
    
    @FXML
    private TextField txtXDisplay;
    
    @FXML
    private TextField txtPosX;

    @FXML
    private TextField txtPosY;

    @FXML
    private TextField txtBorders;

    @FXML
    private TextField txtButtonId;

    @FXML
    private TextField txtButtonText;

    @FXML
    private TextField txtButtonTooltip;

    @FXML
    private TextField txtDialogText;

    @FXML
    private TextField txtExpanderText;

    @FXML
    private TextField txtGtkrc;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtIcon;

    @FXML
    private TextField txtImage;

    @FXML
    private TextField txtImagePath;

    @FXML
    private TextField txtPlug;

    @FXML
    private TextField txtResponse;

    @FXML
    private TextField txtRest;

    @FXML
    private TextField txtSpellLang;

    @FXML
    private TextField txtTabNum;

    @FXML
    private TextField txtTimeoutSecs;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtWidth;
    
    @FXML
    private TableColumn<DialogButton, String> colButtonTooltip;

    @FXML
    private TableColumn<DialogButton, String> colButtonIcon;

    @FXML
    private TableColumn<DialogButton, String> colButtonId;

    @FXML
    private TableColumn<DialogButton, String> colButtonText;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> btnNames = new ArrayList<String>();
    
    private String[] icons = {"help-about", "list-add", "gtk-apply", "gtk-cancel", "gtk-close", "document-clear", "window-close", "gtk-edit", "system-run", "gtk-no", "gtk-ok", "document-open", "document-print", "application-exit", "view-refresh", "list-remove", "document-save", "system-search", "gtk-preferences", "gtk-yes"};

    @FXML
    void doIconHelp(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Common Mime Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/icon_info.txt", 900.0, 600.0, false);
		    stage.showAndWait();
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void doAdd(ActionEvent event) {
    	String txt = txtButtonText.getText();
		if (txt == null || txt.length() <= 0)
			return;
		
		if (btnNames.contains(txt) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("A button with the name '" + txt + "' already exists.");
			
			messageBox.showAndWait();		
		} else {
			String id = txtButtonId.getText();
			String icon = cbButtonIcon.getSelectionModel().getSelectedItem();
			String tooltip = txtButtonTooltip.getText();
			
			tblButtons.getItems().add(new DialogButton(id, txt, icon, tooltip));
			
			txtButtonText.setText("");
			txtButtonTooltip.setText("");
			cbButtonIcon.getSelectionModel().select(0);
			txtButtonId.setText("0");
			btnNames.add(txt);
			
//			saveButtons();
		}
    }

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doDelete(ActionEvent event) {
    	String txt = txtButtonText.getText();
		if (txt == null || txt.length() <= 0)
			return;
		
		int n = 0;
		for (String f : btnNames) {
			if (f.equals(txt) == true) {
				break;
			}
			n++;
		}
		
		if (n >= btnNames.size())
			return;
		
		DialogButton db = tblButtons.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + txt + "'?");
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
				System.out.println("db " + db);
				tblButtons.getItems().remove(db);
				btnNames.remove(db.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
//		ObservableList<DialogButton> btns = tblButtons.getItems();
//		btns.remove(n);
		
		txtButtonText.setText("");
		txtButtonTooltip.setText("");
		cbButtonIcon.getSelectionModel().select(0);
		txtButtonId.setText("0");
		
//		saveButtons();
    }
    
    @FXML
    void doBtnPrintXid(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Resource File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtPrintXid.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doGtkrc(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Resource File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtGtkrc.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doIcon(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Icon File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtIcon.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doImage(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Image File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtImage.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doImagePath(ActionEvent event) {
    	DirectoryChooser dirChooser = new DirectoryChooser();
    	dirChooser.setTitle("Select Image Path");
    	
    	File f = dirChooser.showDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtImagePath.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    }

    @FXML
    void doRest(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Rest File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtRest.setText(f.getAbsolutePath());
    	}
    }
    
    @FXML
    void onMouseClicked(MouseEvent event) {
    	DialogButton db = tblButtons.getSelectionModel().getSelectedItem();
    	
    	if (db != null) {
	    	txtButtonId.setText(db.getId());
	    	txtButtonText.setText(db.getText());
	    	cbButtonIcon.getSelectionModel().select(db.getIcon());
	    	txtButtonTooltip.setText(db.getTooltip());
    	}
    }

    @FXML
    void doSave(ActionEvent event) {
    	
		VBox vb1 = (VBox)aPane.lookup("#mvbox");
		
		if (vb1 == null)
			return;
		
		ObservableList<Node> childern = vb1.getChildren();
		
		processGeneral(childern);
		
//		System.out.println(yg.currIni.toString());
		
		Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colButtonId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colButtonText.setCellValueFactory(new PropertyValueFactory<>("text"));
		colButtonIcon.setCellValueFactory(new PropertyValueFactory<>("icon"));
		colButtonTooltip.setCellValueFactory(new PropertyValueFactory<>("tooltip"));
		
		colButtonText.setCellValueFactory(new PropertyValueFactory<DialogButton, String>("text"));
		colButtonIcon.setCellValueFactory(new PropertyValueFactory<DialogButton, String>("icon"));
		colButtonTooltip.setCellValueFactory(new PropertyValueFactory<DialogButton, String>("tooltip"));
		
		colButtonText.setCellFactory(TextFieldTableCell.forTableColumn());
		colButtonText.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setText(e.getNewValue()));
		
		colButtonIcon.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(icons)));
		colButtonIcon.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setIcon(e.getNewValue()));
		
		colButtonTooltip.setCellFactory(TextFieldTableCell.forTableColumn());
		colButtonTooltip.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setText(e.getNewValue()));
		
		cbTimeoutPosition.getItems().addAll("Top", "Bottom", "Left", "Right");
		cbTimeoutPosition.setValue("Top");
		cbTextAlign.getItems().addAll("Left", "Right", "Center", "Fill");
		cbTextAlign.setValue("Left");
		cbKillSignal.getItems().addAll("SIGTERM", "SIGINT", "SIGUSR1", "SIGUSR2", "SIGKILL", "SIGCHLD");
		cbKillSignal.setValue("SIGTERM");
		cbVScrollPolicy.getItems().addAll("Auto", "Always", "Never");
		cbVScrollPolicy.setValue("Auto");
		cbHScrollPolicy.getItems().addAll("Auto", "Always", "Never");
		cbHScrollPolicy.setValue("Auto");
		cbButtonLayout.getItems().addAll("Spread", "Edge", "Start", "End", "Center");
		cbButtonLayout.setValue("Start");
		cbButtonIcon.getItems().addAll(icons);
		cbButtonIcon.setValue("help-about");
		
		loadSettings();
	}
	
	private void loadSettings() {
		loadHelp("txts/general.txt");
		
		String title = yg.currIni.getString(yg.currDialog + "-General", "--title");
		String icon = yg.currIni.getString(yg.currDialog + "-General", "--icon-theme");
		String timeoutsecs = yg.currIni.getString(yg.currDialog + "-General", "--timeout");
		String button = yg.currIni.getString(yg.currDialog + "-General", "--button");
		String text = yg.currIni.getString(yg.currDialog + "-General", "--text");
		String image = yg.currIni.getString(yg.currDialog + "-General", "--image");
		String width = yg.currIni.getString(yg.currDialog + "-General", "--width");
		String height = yg.currIni.getString(yg.currDialog + "-General", "--height");
		String posx = yg.currIni.getString(yg.currDialog + "-General", "--posx");
		String posy = yg.currIni.getString(yg.currDialog + "-General", "--posy");
		String plug = yg.currIni.getString(yg.currDialog + "-General", "--plug");
		String tabnum = yg.currIni.getString(yg.currDialog + "-General", "--tab-num");
		String expandertext = yg.currIni.getString(yg.currDialog + "-General", "--expander-text");
		String borders = yg.currIni.getString(yg.currDialog + "-General", "--borders");
		String imagepath = yg.currIni.getString(yg.currDialog + "-General", "--imagepath");
		String rest = yg.currIni.getString(yg.currDialog + "-General", "--rest");
		String response = yg.currIni.getString(yg.currDialog + "-General", "--response");
		String gtkc = yg.currIni.getString(yg.currDialog + "-General", "--gtkc");
		String spelllang = yg.currIni.getString(yg.currDialog + "-General", "--spell-lang");
		String signal = yg.currIni.getString(yg.currDialog + "-General", "--signal");
		String printxidfile = yg.currIni.getString(yg.currDialog + "-General", "--print-xid-file");
		String xdisplay = yg.currIni.getString(yg.currDialog + "-General", "--xdisplay");
		
		String timeoutposition = yg.currIni.getString(yg.currDialog + "-General", "--timeout-indicator");
		String btnlayout = yg.currIni.getString(yg.currDialog + "-General", "--buttons-layout");
		String textalign = yg.currIni.getString(yg.currDialog + "-General", "--text-align");
		String hscroll = yg.currIni.getString(yg.currDialog + "-General", "--hscroll-policy");
		String vscroll = yg.currIni.getString(yg.currDialog + "-General", "--vscroll-policy");
		
		Boolean killparent = yg.currIni.getBoolean(yg.currDialog + "-General", "--kill-parent");
		Boolean undecorated = yg.currIni.getBoolean(yg.currDialog + "-General", "--undecorated");
		Boolean fullscreen = yg.currIni.getBoolean(yg.currDialog + "-General", "--fullscreen");
		Boolean noescape = yg.currIni.getBoolean(yg.currDialog + "-General", "--no-escape");
		Boolean nobuttons = yg.currIni.getBoolean(yg.currDialog + "-General", "--no-buttons");
		Boolean center = yg.currIni.getBoolean(yg.currDialog + "-General", "--center");
		Boolean maximized = yg.currIni.getBoolean(yg.currDialog + "-General", "--maximized");
		Boolean expander = yg.currIni.getBoolean(yg.currDialog + "-General", "--expander");
		Boolean nomarkup = yg.currIni.getBoolean(yg.currDialog + "-General", "--no-markup");
		Boolean escapeok = yg.currIni.getBoolean(yg.currDialog + "-General", "--escape-ok");
		Boolean alwaysprint = yg.currIni.getBoolean(yg.currDialog + "-General", "--always-print");
		Boolean sticky = yg.currIni.getBoolean(yg.currDialog + "-General", "--sticky");
		Boolean fixed = yg.currIni.getBoolean(yg.currDialog + "-General", "--fixed");
		Boolean mouse = yg.currIni.getBoolean(yg.currDialog + "-General", "--mouse");
		Boolean ontop = yg.currIni.getBoolean(yg.currDialog + "-General", "--on-top");
		Boolean skiptaskbar = yg.currIni.getBoolean(yg.currDialog + "-General", "--skip-taskbar");
		Boolean splash = yg.currIni.getBoolean(yg.currDialog + "-General", "--splash");
		Boolean nofocus = yg.currIni.getBoolean(yg.currDialog + "-General", "--no-focus");
		Boolean closeonfocus = yg.currIni.getBoolean(yg.currDialog + "-General", "--close-on-unfocus");
		Boolean selectable = yg.currIni.getBoolean(yg.currDialog + "-General", "--selectable");
		Boolean enablespell = yg.currIni.getBoolean(yg.currDialog + "-General", "--enable-spell");
		Boolean printxid = yg.currIni.getBoolean(yg.currDialog + "-General", "--print-xid");
		
		if (button != null) {
			btnNames.clear();
			
			String[] a = button.split(",");

			for (String s : a) {
				String[] b = s.split(":");
				String[] c = b[0].split("!");
				
				tblButtons.getItems().add(new DialogButton(b[1], c[0], c[1], c[2]));
				
				btnNames.add(c[0]);
			}
			
			txtButtonText.setText("");
			cbButtonIcon.getSelectionModel().select(0);
			txtButtonTooltip.setText("");
		}
		
		if (title != null)
			txtTitle.setText(title);
		buildHelp(txtTitle, txtTitle.getTooltip());
		
		if (icon != null)
			txtIcon.setText(icon);
		buildHelp(txtIcon, txtIcon.getTooltip());
		
		if (timeoutsecs != null)
			txtTimeoutSecs.setText(timeoutsecs);
		buildHelp(txtTimeoutSecs, txtTimeoutSecs.getTooltip());
		
		if (text != null)
			txtDialogText.setText(text);
		buildHelp(txtDialogText, txtDialogText.getTooltip());
		if (image != null)
			txtImage.setText(image);
		buildHelp(txtImage, txtImage.getTooltip());
		if (width != null)
			txtWidth.setText(width);
		buildHelp(txtWidth, txtWidth.getTooltip());
		if (height != null)
			txtHeight.setText(height);
		buildHelp(txtHeight, txtHeight.getTooltip());
		if (posx != null)
			txtPosX.setText(posx);
		buildHelp(txtPosX, txtPosX.getTooltip());
		if (posy != null)
			txtPosY.setText(posy);
		buildHelp(txtPosY, txtPosY.getTooltip());
		if (plug != null)
			txtPlug.setText(plug);
		buildHelp(txtPlug, txtPlug.getTooltip());
		if (tabnum != null)
			txtTabNum.setText(tabnum);
		buildHelp(txtTabNum, txtTabNum.getTooltip());
		if (expandertext != null)
			txtExpanderText.setText(expandertext);
		buildHelp(txtExpanderText, txtExpanderText.getTooltip());
		if (borders != null)
			txtBorders.setText(borders);
		buildHelp(txtBorders, txtBorders.getTooltip());
		if (imagepath != null)
			txtImagePath.setText(imagepath);
		buildHelp(txtImagePath, txtImagePath.getTooltip());
		if (rest != null)
			txtRest.setText(rest);
		buildHelp(txtRest, txtRest.getTooltip());
		if (response != null)
			txtResponse.setText(response);
		buildHelp(txtResponse, txtResponse.getTooltip());
		if (gtkc != null)
			txtGtkrc.setText(gtkc);
		buildHelp(txtGtkrc, txtGtkrc.getTooltip());
		if (spelllang != null)
			txtSpellLang.setText(spelllang);
		buildHelp(txtSpellLang, txtSpellLang.getTooltip());
		
		if (timeoutposition != null)
			cbTimeoutPosition.setValue(timeoutposition);
		buildHelp(cbTimeoutPosition, cbTimeoutPosition.getTooltip());
		if (btnlayout != null)
			cbButtonLayout.setValue(btnlayout);
		buildHelp(cbButtonLayout, cbButtonLayout.getTooltip());
		if (textalign != null)
			cbTextAlign.setValue(textalign);
		buildHelp(cbTextAlign, cbTextAlign.getTooltip());
		if (hscroll != null)
			cbHScrollPolicy.setValue(hscroll);
		buildHelp(cbHScrollPolicy, cbHScrollPolicy.getTooltip());
		if (vscroll != null)
			cbVScrollPolicy.setValue(vscroll);
		buildHelp(cbVScrollPolicy, cbVScrollPolicy.getTooltip());
		if (signal != null)
			cbKillSignal.setValue(signal);
		buildHelp(cbKillSignal, cbKillSignal.getTooltip());
		if (printxidfile != null)
			txtPrintXid.setText(printxidfile);
		buildHelp(txtPrintXid, txtPrintXid.getTooltip());
		if (xdisplay != null)
			txtXDisplay.setText(printxidfile);
		buildHelp(txtXDisplay, txtXDisplay.getTooltip());
		
		setToggleButton(btnKillParent, killparent);
		buildHelp(btnKillParent, btnKillParent.getTooltip());
		setToggleButton(btnUndecorated, undecorated);
		buildHelp(btnUndecorated, btnUndecorated.getTooltip());
		setToggleButton(btnFullScreen, fullscreen);
		buildHelp(btnFullScreen, btnFullScreen.getTooltip());
		setToggleButton(btnNoEscape, noescape);
		buildHelp(btnNoEscape, btnNoEscape.getTooltip());
		setToggleButton(btnNoButtons, nobuttons);
		buildHelp(btnNoButtons, btnNoButtons.getTooltip());
		setToggleButton(btnCenterWindow, center);
		buildHelp(btnCenterWindow, btnCenterWindow.getTooltip());
		setToggleButton(btnMaximized, maximized);
		buildHelp(btnMaximized, btnMaximized.getTooltip());
		setToggleButton(btnExpander, expander);
		buildHelp(btnExpander, btnExpander.getTooltip());
		setToggleButton(btnNoMarkup, nomarkup);
		buildHelp(btnNoMarkup, btnNoMarkup.getTooltip());
		setToggleButton(btnEscapeOk, escapeok);
		buildHelp(btnEscapeOk, btnEscapeOk.getTooltip());
		setToggleButton(btnAlwaysPrint, alwaysprint);
		buildHelp(btnAlwaysPrint, btnAlwaysPrint.getTooltip());
		setToggleButton(btnSticky, sticky);
		buildHelp(btnSticky, btnSticky.getTooltip());
		setToggleButton(btnFixed, fixed);
		buildHelp(btnFixed, btnFixed.getTooltip());
		setToggleButton(btnMouse, mouse);
		buildHelp(btnMouse, btnMouse.getTooltip());
		setToggleButton(btnOnTop, ontop);
		buildHelp(btnOnTop, btnOnTop.getTooltip());
		setToggleButton(btnSkipTaskbar, skiptaskbar);
		buildHelp(btnSkipTaskbar, btnSkipTaskbar.getTooltip());
		setToggleButton(btnSplash, splash);
		buildHelp(btnSplash, btnSplash.getTooltip());
		setToggleButton(btnNoFocus, nofocus);
		buildHelp(btnNoFocus, btnNoFocus.getTooltip());
		setToggleButton(btnCloseOnFocus, closeonfocus);
		buildHelp(btnCloseOnFocus, btnCloseOnFocus.getTooltip());
		setToggleButton(btnSelectable, selectable);
		buildHelp(btnSelectable, btnSelectable.getTooltip());
		setToggleButton(btnEnableSpell, enablespell);
		buildHelp(btnEnableSpell, btnEnableSpell.getTooltip());
		setToggleButton(tbPrintXid, printxid);
		buildHelp(tbPrintXid, tbPrintXid.getTooltip());
	}
	
	@SuppressWarnings("unchecked")
	private void processGeneral(ObservableList<Node> childern) {
		if (childern != null) {
			for (Node n : childern) {
				String key = n.getId();
				if (key != null && key.startsWith("--") == false)
					continue;
				
//				System.out.println("key " + key);
				
				if (n instanceof TextField) {
//					System.out.println("TextField:");
					TextField tf = (TextField)n;
					String v = tf.getText();
					if (v.isBlank() == false) {
						yg.currIni.addValuePair(yg.currDialog + "-General", key, v);
					} else {
					 	yg.currIni.removeValuePair(yg.currDialog + "-General", key);
					}
				} else if (n instanceof DatePicker) {
//					System.out.println("DatePicker:");
					DatePicker dp = (DatePicker)n;
					String v = dp.getValue().toString();
					if (v.isBlank() == false) {
						yg.currIni.addValuePair(yg.currDialog + "-General", key, v);
					} else {
					 	yg.currIni.removeValuePair(yg.currDialog + "-General", key);
					}
				} else if (n instanceof ColorPicker) {
//					System.out.println("ColorPicker:");
					ColorPicker cp = (ColorPicker)n;
					String v = cp.getValue().toString();
					if (v.isBlank() == false) {
						yg.currIni.addValuePair(yg.currDialog + "-General", key, v);
					} else {
					 	yg.currIni.removeValuePair(yg.currDialog + "-General", key);
					}
				} else if (n instanceof ComboBox) {
//					System.out.println("ComboBox:");
					ComboBox<String> cb = (ComboBox<String>)n;
					String v = cb.getValue();
					if (v.isBlank() == false) {
						yg.currIni.addValuePair(yg.currDialog + "-General", key, v);
					} else {
					 	yg.currIni.removeValuePair(yg.currDialog + "-General", key);
					}
				} else if (n instanceof ToggleButton) {
//					System.out.println("ToggleButton:");
					ToggleButton tb = (ToggleButton)n;
					String v = Boolean.toString(tb.isSelected());
					if (v.equalsIgnoreCase("true")) {
						yg.currIni.addValuePair(yg.currDialog + "-General", key, v);
					} else {
					 	yg.currIni.removeValuePair(yg.currDialog + "-General", key);
					}
				} else if (n instanceof TitledPane) {
//					System.out.println("TitlePane:");
					TitledPane tp = (TitledPane)n;
					AnchorPane ap = (AnchorPane)tp.getContent();
					processGeneral(ap.getChildren());
				} else if (n instanceof TableView) {
//					System.out.println("TableView:");
					processButtons(n, key);
				} else if (n instanceof FlowPane) {
//					System.out.println("FlowPane:");
					FlowPane fp = (FlowPane)n;
					processGeneral(fp.getChildren());
				} else if (n instanceof VBox) {
//					System.out.println("VBox:");
					VBox vb = (VBox)n;
					processGeneral(vb.getChildren());
				} else if (n instanceof HBox) {
//					System.out.println("HBox:");
					HBox hb = (HBox)n;
					processGeneral(hb.getChildren());
				} else if (n instanceof AnchorPane) {
//					System.out.println("AnchorPane:");
					AnchorPane ap = (AnchorPane)n;
					processGeneral(ap.getChildren());
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void processButtons(Node n, String id) {
		if (id.equalsIgnoreCase("button") == true) {
			TableView<DialogButton> tv = (TableView<DialogButton>)n;
			ObservableList<DialogButton> buttons = tv.getItems();
			String m = null;
			for (DialogButton dp : buttons) {
				
				if (m == null) {
					if (dp.getText() != null)
						m = dp.getText() + "!" + dp.getIcon() + "!" + dp.getTooltip() + ":" + dp.getId();
					else
						m = dp.getIcon() + ":" + dp.getId();
				} else {
					if (dp.getText() != null)
						m += "," + dp.getText() + "!" + dp.getIcon() + "!" + dp.getTooltip() + ":" + dp.getId();
					else
						m += "," + dp.getIcon() + ":" + dp.getId();
				}
			}
			if (m != null && m.isBlank() == false)
				yg.currIni.addValuePair(yg.currDialog + "-General", id, m);
			else
				yg.currIni.removeValuePair(yg.currDialog + "-General", id);
		}
	}
}


package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import tbl.ColumnType;
import tbl.FileFilter;
import tbl.MarkValue;
import tbl.MimeFilter;
import tbl.TabInfo;
import tbl.TextType;

public class HandleActions {
	
	private YadGlobal yg = YadGlobal.getInstance();
	private Map<String, String> fldHelp = new HashMap<String, String>();

	public HandleActions() {
		
	}
	
	@FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	try {
		    Stage stage = new Stage();
		    stage.setTitle("General Settings for dialog " + yg.currDialog);

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("screens/GeneralSettings.fxml"));

		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    stage.showAndWait();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
	
	@FXML
	void doToggleButton(ActionEvent event) {
		if (event.getSource() instanceof ToggleButton) {
    		ToggleButton tb = (ToggleButton) event.getSource();
    		if (tb.isSelected() == true) {
				tb.setGraphic(new ImageView(yg.checkImage));
			} else {
				tb.setGraphic(null);
			}
    	}
	}
	
	public void setToggleButton(ToggleButton tb, boolean flag) {
		if (tb == null)
			return;
		tb.setSelected(flag);
		if (flag == true) {
			tb.setGraphic(new ImageView(yg.checkImage));
		} else {
			tb.setGraphic(null);
		}
	}
	
	@FXML
	void doBtnSaveSettings(ActionEvent event) {
		
		ObservableList<Window> ws = Window.getWindows();
		
		Scene scene = (Scene)ws.get(0).getScene();
		VBox vb1 = (VBox)scene.lookup("#mvbox");
		
		if (vb1 == null) {
			return;
		}
		
		ObservableList<Node> childern = vb1.getChildren();
		
		processChildern(childern);
	}
	
	public void updateDialogs(AnchorPane ap) {
		
		VBox vb1 = (VBox)ap.lookup("#mvbox");
		
		if (vb1 == null)
			return;
		
		ObservableList<Node> childern = vb1.getChildren();
		
		processFields(childern);
	}
	
	@SuppressWarnings("unchecked")
	private void processFields(ObservableList<Node> childern) {
		
		if (childern != null) {
			for (Node n : childern) {
				String key = n.getId();
				
				if (n instanceof TextField) {
					TextField tf = (TextField)n;
					String s = yg.currIni.getString(yg.currDialog, key);
					if (s == null || s.isBlank() == true) {
						s = yg.defValues.get(key);
						if (s != null)
							tf.setText(s);
					} else {
						tf.setText(s);
					}
					buildHelp(tf, tf.getTooltip());
				} else if (n instanceof DatePicker) {
//					System.out.println("DatePicker:");
					DatePicker dp = (DatePicker)n;
					String s = yg.currIni.getString(yg.currDialog, key);
					if (s == null || s.isBlank() == true) {
						s = yg.defValues.get(key);
						if (s != null)
							dp.setValue(LocalDate.parse(s));
					} else {
						dp.setValue(LocalDate.parse(s));
					}
					buildHelp(dp, dp.getTooltip());
				} else if (n instanceof Label) {
//					Label lbl = (Label)n;
//					System.out.println("Label: " + lbl.getText());
				} else if (n instanceof ColorPicker) {
//					System.out.println("ColorPicker: " + key);
					ColorPicker cp = (ColorPicker)n;
					String s = yg.currIni.getString(yg.currDialog, key);
					if (s == null || s.isBlank() == true) {
						s = yg.defValues.get(key);
						if (s == null)
							s = "#ffffffff";
						javafx.scene.paint.Color c = javafx.scene.paint.Color.web(s);
						if (s != null)
							cp.setValue(c);
					} else {
						javafx.scene.paint.Color c = javafx.scene.paint.Color.web(s);
						cp.setValue(c);
					}
					buildHelp(cp, cp.getTooltip());
				} else if (n instanceof ComboBox) {
//					System.out.println("ComboBox:");
					ComboBox<String> cb = (ComboBox<String>)n;
					String s = yg.currIni.getString(yg.currDialog, key);
					if (s == null || s.isBlank() == true) {
						s = yg.defValues.get(key);
						if (s != null)
							cb.setValue(s);
					} else {
						cb.setValue(s);
					}
					buildHelp(cb, cb.getTooltip());
				} else if (n instanceof ToggleButton) {
//					System.out.println("ToggleButton:");
					ToggleButton tb = (ToggleButton)n;
					boolean f = yg.currIni.getBoolean(yg.currDialog, key);
					tb.setSelected(f);
					buildHelp(tb, tb.getTooltip());
				} else if (n instanceof TitledPane) {
//					System.out.println("TitlePane:");
					TitledPane tp = (TitledPane)n;
					Node nn = tp.getContent();
					if (nn instanceof HBox) {
						HBox hb = (HBox)tp.getContent();
						processFields(hb.getChildren());
					} else if (nn instanceof AnchorPane) {
						AnchorPane ap = (AnchorPane)tp.getContent();
						processFields(ap.getChildren());
					}
				} else if (n instanceof TableView) {
//					System.out.println("TableView: " + key);
					fillTable(n, key);
				} else if (n instanceof VBox) {
//					System.out.println("VBox:");
					VBox vb = (VBox)n;
					processFields(vb.getChildren());
				} else if (n instanceof HBox) {
					HBox hb = (HBox)n;
					processFields(hb.getChildren());
				} else if (n instanceof AnchorPane) {
//					System.out.println("AnchorPane:");
					AnchorPane ap = (AnchorPane)n;
					processFields(ap.getChildren());
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void fillTable(Node n, String key) {
		if (key.equalsIgnoreCase("--mark") == true) {
			TableView<MarkValue> tv = (TableView<MarkValue>)n;
			ObservableList<MarkValue> marks = tv.getItems();
			buildHelp(tv, tv.getTooltip());
			marks.clear();
			
			String ss = yg.currIni.getString(yg.currDialog, key);
			if (ss == null)
				return;
			
			String[] cols = ss.split(",");
			
			for (String c : cols) {
				String[] a = c.split(":");
				
				marks.add(new MarkValue(a[0], a[1]));
			}
		} else if (key.equalsIgnoreCase("--tab") == true) {
			TableView<TabInfo> tv = (TableView<TabInfo>)n;
			ObservableList<TabInfo> tabs = tv.getItems();
			buildHelp(tv, tv.getTooltip());
			
			tabs.clear();
			
			String[] a = yg.currIni.getString(yg.currDialog, key).split(",");

			for (String s : a) {
				String[] b = s.split("!");

				if (b.length == 3)
					tabs.add(new TabInfo(b[0], b[1], b[2]));
				else
					tabs.add(new TabInfo(b[0], b[1], ""));
			}
		} else if (key.equalsIgnoreCase("--file-filter") == true) {
			TableView<FileFilter> tv = (TableView<FileFilter>)n;
			buildHelp(tv, tv.getTooltip());
			ObservableList<FileFilter> ffs = tv.getItems();
			ffs.clear();
		
			String ss = yg.currIni.getString(yg.currDialog, key);
			if (ss != null) {
				String[] a = ss.split(",");
				
				for (String s : a) {
					String[] b = s.split(":");
					
					ffs.add(new FileFilter(b[0], b[1]));
				}
			}
		} else if (key.equalsIgnoreCase("--mime-filter") == true) {
			TableView<MimeFilter> tv = (TableView<MimeFilter>)n;
			ObservableList<MimeFilter> mfs = tv.getItems();
			buildHelp(tv, tv.getTooltip());
			mfs.clear();
		
			String[] a = yg.currIni.getString(yg.currDialog, key).split(",");
			
			for (String s : a) {
				String[] b = s.split(":");
				
				mfs.add(new MimeFilter(b[0], b[1]));
			}
		} else if (key.equalsIgnoreCase("--column") == true) {
			TableView<ColumnType> tv = (TableView<ColumnType>)n;
			ObservableList<ColumnType> mfs = tv.getItems();
			buildHelp(tv, tv.getTooltip());
			mfs.clear();
		
			String[] a = yg.currIni.getString(yg.currDialog, key).split(",");
			
			for (String s : a) {
				String[] b = s.split(":");
				
				mfs.add(new ColumnType(b[0], b[1]));
			}
		} else if (key.equalsIgnoreCase("--field") == true) {
			TableView<TextType> tv = (TableView<TextType>)n;
			ObservableList<TextType> fs = tv.getItems();
			buildHelp(tv, tv.getTooltip());
			
			fs.clear();
			
			String field = yg.currIni.getString(yg.currDialog, key);
			if (field != null && field.isBlank() == false) {
				String[] a = yg.currIni.getString(yg.currDialog, key).split(",");
	
				for (String s : a) {
					String[] b = s.split(":");
					
					if (b.length < 3)
						fs.add(new TextType(b[0], b[1], ""));
					else
						fs.add(new TextType(b[0], b[1], b[2]));
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void processChildern(ObservableList<Node> childern) {
		
		if (childern != null) {
			for (Node n : childern) {
				String key = n.getId();
				if (key != null && key.startsWith("--") == false)
					continue;
				
				if (n instanceof TextField) {
//					System.out.println("TextField: " + key);
					TextField tf = (TextField)n;
					String v = tf.getText();
					if (key != null && v.isBlank() == false)
						yg.currIni.addValuePair(yg.currDialog, key, v);
					else
					 	yg.currIni.removeValuePair(yg.currDialog, key);
				} else if (n instanceof DatePicker) {
//					System.out.println("DatePicker:");
					DatePicker dp = (DatePicker)n;
					String v = dp.getValue().toString();
					if (key != null && v.isBlank() == false)
						yg.currIni.addValuePair(yg.currDialog, key, v);
					else
					 	yg.currIni.removeValuePair(yg.currDialog, key);
				} else if (n instanceof ColorPicker) {
//					System.out.println("ColorPicker:");
					ColorPicker cp = (ColorPicker)n;
					String v = cp.getValue().toString();
					if (key != null && v.isBlank() == false)
						yg.currIni.addValuePair(yg.currDialog, key, v);
					else
					 	yg.currIni.removeValuePair(yg.currDialog, key);
				} else if (n instanceof ComboBox) {
//					System.out.println("ComboBox:");
					ComboBox<String> cb = (ComboBox<String>)n;
					String v = cb.getValue();
					if (key != null && v != null && v.isBlank() == false)
						yg.currIni.addValuePair(yg.currDialog, key, v);
					else
					 	yg.currIni.removeValuePair(yg.currDialog, key);
				} else if (n instanceof ToggleButton) {
//					System.out.println("ToggleButton:");
					ToggleButton tb = (ToggleButton)n;
					String v = Boolean.toString(tb.isSelected());
					if (key != null && v.equalsIgnoreCase("true"))
						yg.currIni.addValuePair(yg.currDialog, key, v);
					else
					 	yg.currIni.removeValuePair(yg.currDialog, key);
				} else if (n instanceof TitledPane) {
//					System.out.println("TitlePane:");
					TitledPane tp = (TitledPane)n;
					Node nn = tp.getContent();
					if (nn instanceof HBox) {
						HBox hb = (HBox)tp.getContent();
						processChildern(hb.getChildren());
					} else if (nn instanceof AnchorPane) {
						AnchorPane ap = (AnchorPane)tp.getContent();
						processChildern(ap.getChildren());
					}
				} else if (n instanceof TableView) {
//					System.out.println("TableView:");
					processTable(n, key);
				} else if (n instanceof VBox) {
//					System.out.println("VBox:");
					VBox vb = (VBox)n;
					processChildern(vb.getChildren());
				} else if (n instanceof HBox) {
//					System.out.println("HBox:");
					HBox hb = (HBox)n;
					processChildern(hb.getChildren());
				} else if (n instanceof AnchorPane) {
//					System.out.println("AnchorPane:");
					AnchorPane ap = (AnchorPane)n;
					processChildern(ap.getChildren());
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void processTable(Node n, String id) {
		if (id.equalsIgnoreCase("--mark") == true) {
			TableView<MarkValue> tv = (TableView<MarkValue>)n;
			ObservableList<MarkValue> marks = tv.getItems();
			String m = null;
			for (MarkValue mark : marks) {
				if (m == null)
					m = mark.getText() + ":" + mark.getValue();
				else
					m += "," + mark.getText() + ":" + mark.getValue();
			}
			if (m != null)
				yg.currIni.addValuePair(yg.currDialog, id, m);
			else
				yg.currIni.removeValuePair(yg.currDialog, id);
		} else if (id.equalsIgnoreCase("--tab") == true) {
			TableView<TabInfo> tv = (TableView<TabInfo>)n;
			ObservableList<TabInfo> tabs = tv.getItems();
			String m = null;
			for (TabInfo tab : tabs) {
				if (m == null)
					m = tab.getText() + "!" + tab.getIcon() + "!" + tab.getTooltip();
				else
					m += "," + tab.getText() + "!" + tab.getIcon() + "!" + tab.getTooltip();
			}
			if (m != null)
				yg.currIni.addValuePair(yg.currDialog, id, m);
			else
				yg.currIni.removeValuePair(yg.currDialog, id);
		} else if (id.equalsIgnoreCase("--file-filter") == true) {
			TableView<FileFilter> tv = (TableView<FileFilter>)n;
			ObservableList<FileFilter> ffs = tv.getItems();
			String m = null;
			for (FileFilter ff : ffs) {
				if (m == null)
					m = ff.getName() + ":" + ff.getFilter();
				else
					m += "," + ff.getName() + ":" + ff.getFilter();
			}
			if (m != null)
				yg.currIni.addValuePair(yg.currDialog, id, m);
			else
				yg.currIni.removeValuePair(yg.currDialog, id);
		} else if (id.equalsIgnoreCase("--mime-filter") == true) {
			TableView<MimeFilter> tv = (TableView<MimeFilter>)n;
			ObservableList<MimeFilter> mfs = tv.getItems();
			String m = null;
			for (MimeFilter mf : mfs) {
				if (m == null)
					m = mf.getName() + ":" + mf.getMime();
				else
					m += "," + mf.getName() + ":" + mf.getMime();
			}
			if (m != null)
				yg.currIni.addValuePair(yg.currDialog, id, m);
			else
				yg.currIni.removeValuePair(yg.currDialog, id);
		} else if (id.equalsIgnoreCase("--column") == true) {
			TableView<ColumnType> tv = (TableView<ColumnType>)n;
			ObservableList<ColumnType> cts = tv.getItems();
			String m = null;
			for (ColumnType ct : cts) {
				if (m == null)
					m = ct.getText() + ":" + ct.getType();
				else
					m += "," + ct.getText() + ":" + ct.getType();
			}
			if (m != null)
				yg.currIni.addValuePair(yg.currDialog, id, m);
			else
				yg.currIni.removeValuePair(yg.currDialog, id);
		} else if (id.equalsIgnoreCase("--field") == true) {
			TableView<TextType> tv = (TableView<TextType>)n;
			ObservableList<TextType> fs = tv.getItems();
			String m = null;
			for (TextType f : fs) {
				if (m == null) {
					if (f.getValues().isBlank() == false)
						m = f.getText() + ":" + f.getType() + ":" + f.getValues();
					else
						m = f.getText() + ":" + f.getType();
				} else {
					if (f.getValues().isBlank() == false)
						m += "," + f.getText() + ":" + f.getType() + ":" + f.getValues();
					else
						m += "," + f.getText() + ":" + f.getType();
				}
			}
			if (m != null)
				yg.currIni.addValuePair(yg.currDialog, id, m);
			else
				yg.currIni.removeValuePair(yg.currDialog, id);
		}
	}
	
	public void loadHelp(String fileName) {
		fldHelp.clear();
		
		InputStream in = getClass().getResourceAsStream(fileName);
		if (in != null) {		// Found jar file.
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					line = line.trim();
					int n = line.indexOf(' ');
					String s = line.substring(0, n);
					String[] a = s.split("=");
					fldHelp.put(a[0], line);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// No jar file.
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
	
				String line = null;
				while ((line = br.readLine()) != null) {
					line = line.trim();
					int n = line.indexOf(' ');
					String s = line.substring(0, n);
					String[] a = s.split("=");
					fldHelp.put(a[0], line);
				}
				br.close();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(fileName));
//
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				line = line.trim();
//				int n = line.indexOf(' ');
//				String s = line.substring(0, n);
//				String[] a = s.split("=");
//				fldHelp.put(a[0], line);
//			}
//			br.close();
//			
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
		
//		for (String k : fldHelp.keySet())
//			System.out.println(k + ", " + fldHelp.get(k));
	}
	
	public void buildHelp(Node node, Tooltip tt) {
		if (tt == null)
			return;
//		tt.setText("");
		String key = node.getId();
		if (key == null || key.startsWith("--") == false) {
//			System.out.println("key '" + key + "'");
			return;
		}
		String help = fldHelp.get(key);
		if (help == null)
			return;
		int n = help.indexOf(' ');
		String opt = help.substring(0, n);
		String h = null;
		for (; n < help.length(); n++) {
			if (help.charAt(n) != ' ')
				break;
		}
		h = help.substring(n);
		
		tt.setText(opt + "\n" + h);
	}
}

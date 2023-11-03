package tbl;

import application.YadGlobal;

public class TextType {

	
	private String text = null;
	private String type = null;
	private String shortType = null;
	private String values = null;
	
	private YadGlobal yg = YadGlobal.getInstance();
	
	public TextType() {
		super();
	}
	
	public TextType(String text, String type, String values) {
		super();
		this.text = text;
		this.type = type;
		this.values = values;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getShortType() {
		String[] a = type.split("-");
		return a[1];
	}

	public void setShortType(String shortType) {
		for (String s : yg.fldTypes) {
			if (s.endsWith("-" + shortType) == true) {
				this.type = s;
				break;
			}
		}
	}

	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "TextType [text=" + text + ", type=" + type + ", shortType=" + shortType + ", values=" + values + "]";
	}
}

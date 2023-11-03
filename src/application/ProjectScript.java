package application;

import java.util.ArrayList;
import java.util.List;

import com.rkw.IniFile;

public class ProjectScript {
	
	static String yad = "yad ";

	static public String createDialog(IniFile ini) {
		StringBuilder txt = new StringBuilder();
		
		Object[] secs = ini.getSectionNames();
		
		for (Object sec : secs) {
			String s = (String)sec;
			
			if (s.endsWith("-General") == true)
				continue;
			
			String type = ini.getString(s, "type");
			
//			System.out.println("sec " + s + " type " + type);
				
			switch (type) {
			case "Calendar":
				txt.append( buildCalendar(ini, s));
				break;
			case "Color":
				txt.append( buildColor(ini, s));
				break;
			case "DnD":
				txt.append( buildDnD(ini, s));
				break;
			case "Entry":
				txt.append( buildEntry(ini, s));
				break;
			case "File":
				txt.append( buildFile(ini, s));
				break;
			case "Font":
				txt.append( buildFont(ini, s));
				break;
			case "Form":
				txt.append( buildForm(ini, s));
				break;
			case "HTML":
				txt.append( buildHtml(ini, s));
				break;
			case "Icons":
				txt.append( buildIcons(ini, s));
				break;
			case "Info":
				txt.append( buildInfo(ini, s));
				break;
			case "List":
				txt.append( buildList(ini, s));
				break;
			case "Notebook":
				txt.append( buildNotebook(ini, s));
				break;
			case "Notifaction":
				txt.append( buildNotification(ini, s));
				break;
			case "Print":
				txt.append( buildPrint(ini, s));
				break;
			case "Progress":
				txt.append( buildProgress(ini, s));
				break;
			case "Progress Multi":
				txt.append( buildProgressMulti(ini, s));
				break;
			case "Scale":
				txt.append( buildScale(ini, s));
				break;
			}
			
			txt.append("\n");
		}
		
		return txt.toString();
	}
	
	static private String buildCalendar(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In calendar");
		
		lst.add("--calendar");
		
		String detailfile = ini.getString(sec, "--details");
		String dateformat = ini.getString(sec, "--date-format");
		String defaultdate = ini.getString(sec, "--default-date");

		boolean showweeks = ini.getBoolean(sec, "show-weeks");
		
		if (detailfile != null && detailfile.isEmpty() == false)
			lst.add("--details=\"" + detailfile + "\"" );
		if (dateformat != null && dateformat.isEmpty() == false)
			lst.add("--date-format=\"" + dateformat + "\"");
		if (defaultdate != null && defaultdate.isEmpty() == false) {
			String[] a = defaultdate.split("-");
			lst.add("--month=" + a[1]);
			lst.add("--day=" + a[2]);
			lst.add("--year=" + a[0]);
		}
		if (showweeks == true)
			lst.add("--show-weeks");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildColor(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Color");
		
		lst.add("--color");
		
		String palette = ini.getString(sec, "--palette");
		String mode = ini.getString(sec, "--mode");
		String initcolor = ini.getString(sec, "--init-color");

		boolean gtkpalette = ini.getBoolean(sec, "--gtk-palette");
		boolean extra = ini.getBoolean(sec, "--extra");
		boolean alpha = ini.getBoolean(sec, "--alpha");
		boolean expand = ini.getBoolean(sec, "--expand");
		
		if (palette != null && palette.isEmpty() == false)
			lst.add("--details=\"" + palette + "\"" );
		if (mode != null && mode.isEmpty() == false)
			lst.add("--details=\"" + mode + "\"" );
		if (initcolor != null && initcolor.isEmpty() == false) {
			lst.add("--init-color=\"" + initcolor.substring(0, 7) + "\"");
		}
		
		if (gtkpalette == true)
			lst.add("--gtk-palette");
		if (extra == true)
			lst.add("--extra");
		if (alpha == true)
			lst.add("--alpha");
		if (expand == true)
			lst.add("--expand");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildDnD(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In DnD");
		
		lst.add("--dnd");
		
		
		String command = ini.getString(sec, "--command");
		String exitondrop = ini.getString(sec, "--exit-on-drop");
		
		boolean tooltip = ini.getBoolean(sec, "--tooltip");
		
		if (command != null && command.isEmpty() == false)
			lst.add("--command=\"" + command + "\"");
		if (exitondrop != null && exitondrop.isEmpty() == false)
			lst.add("--exit-on-drop=\"" + exitondrop + "\"");
		
		if (tooltip == true)
			lst.add("--tooltip");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildEntry(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Entry");
		
		lst.add("--entry");
		
		String label = ini.getString(sec, "--entry-label");
		String text = ini.getString(sec, "--entry-text");
		String lefticon = ini.getString(sec, "--licon");
		String leftaction = ini.getString(sec, "--licon-action");
		String righticon = ini.getString(sec, "--ricon");
		String rightaction = ini.getString(sec, "--ricon-action");
		String precision = ini.getString(sec, "--float-precision");
		
		boolean completion = ini.getBoolean(sec, "--completion");
		boolean numeric = ini.getBoolean(sec, "--numeric");
		boolean numoutput = ini.getBoolean(sec, "--num-output");
		
		if (label != null && label.isEmpty() == false)
			lst.add("--entry-label=\"" + label + "\"");
		if (text != null && text.isEmpty() == false)
			lst.add("--entry-text=\"" + text + "\"");
		if (lefticon != null && lefticon.isEmpty() == false)
			lst.add("--licon=\"" + lefticon + "\"");
		if (leftaction != null && leftaction.isEmpty() == false)
			lst.add("--licon-cmd=\"" + leftaction + "\"");
		if (righticon != null && righticon.isEmpty() == false)
			lst.add("--ricon=\"" + righticon + "\"");
		if (rightaction != null && rightaction.isEmpty() == false)
			lst.add("--ricon-cmd=\"" + rightaction + "\"");
		if (precision != null && precision.isEmpty() == false)
			lst.add("--float-precision=\"" + precision + "\"");
		
		if (completion == true)
			lst.add("--completion");
		if (numeric == true)
			lst.add("--numeric");
		if (numoutput == true)
			lst.add("--num-output");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildFile(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In File");
		
		lst.add("--file");
		
		String filename = ini.getString(sec, "--filename");
		String sep = ini.getString(sec, "--separator");
		String owtext = ini.getString(sec, "--overwrite-text");
		
		boolean multiple = ini.getBoolean(sec, "--multiple");
		boolean directory = ini.getBoolean(sec, "--directory");
		boolean savemode = ini.getBoolean(sec, "--save");
		boolean quoted = ini.getBoolean(sec, "--quoted-output");
		boolean overwrite = ini.getBoolean(sec, "--confirm-overwrite");
		
		if (filename != null && filename.isEmpty() == false)
			lst.add("--filename=\"" + filename + "\"");
		if (sep != null && sep.isEmpty() == false)
			lst.add("--separator=\"" + sep + "\"");
//		if (owtext != null && owtext.isEmpty() == false)
//			lst.add("--confirm-overwrite=\"" + owtext + "\"");
		
		if (multiple == true)
			lst.add("--multiple");
		if (directory == true)
			lst.add("--directory");
		if (savemode == true)
			lst.add("--save");
		if (quoted == true)
			lst.add("--quoted-output");
		if (overwrite == true) {
			if (owtext != null)
				lst.add("--confirm-overwrite=\"" + owtext + "\"");
			else
				lst.add("--confirm-overwrite");
		}
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildFont(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Font");
		
		lst.add("--font");
		
		String fontname = ini.getString(sec, "--fontname");
		String sep = ini.getString(sec, "--separator");
		
		boolean preview = ini.getBoolean(sec, "--preview");
		boolean output = ini.getBoolean(sec, "--separate-output");
		boolean quoted = ini.getBoolean(sec, "--quoted-output");
		
		if (fontname != null && fontname.isEmpty() == false)
			lst.add("--fontname=\"" + fontname + "\"");
		if (sep != null && sep.isEmpty() == false)
			lst.add("--separator=\"" + sep + "\"");
		
		if (preview == true)
			lst.add("--preview");
		if (output == true)
			lst.add("--separate-output");
		if (quoted == true)
			lst.add("--quoted-output");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildForm(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Form");
		
		lst.add("--form");
		
		String field = ini.getString(sec, "--field");
		String columns = ini.getString(sec, "--columns");
		String sep = ini.getString(sec, "--separator");
		String items = ini.getString(sec, "--item-separator");
		String format = ini.getString(sec, "--date-format");
		String outputnum = ini.getString(sec, "--num-output");
		String precision = ini.getString(sec, "--float-precision");
		String align = ini.getString(sec, "--align");
		String complete = ini.getString(sec, "--complete");
		String focus = ini.getString(sec, "--focus-field");
		
		boolean cycle = ini.getBoolean(sec, "--cycle-read");
		boolean scroll = ini.getBoolean(sec, "--scroll");
		boolean outputrow = ini.getBoolean(sec, "--output-by-row");
		boolean quoted = ini.getBoolean(sec, "--quoted-output");
		
		if (field != null) {
			String[] flds = field.split(",");
			
			for (String f : flds) {
				String[] a = f.split(":");
				String[] b = a[1].split("-");
				
				if (a.length > 2)
					lst.add("--field=\"" + a[0] + ":" + b[1] + "\"" + " \"" + a[2] + "\"");
				else
					lst.add("--field=\"" + a[0] + ":" + b[1] + "\" \" \"" );
			}
		}
		
		if (columns != null && columns.isEmpty() == false)
			lst.add("--columns=\"" + columns + "\"");
		if (sep != null && sep.isEmpty() == false)
			lst.add("--separator=\"" + sep + "\"");
		if (items != null && items.isEmpty() == false)
			lst.add("--items-separator=\"" + items + "\"");
		if (format != null && format.isEmpty() == false)
			lst.add("--date-foramt=\"" + format + "\"");
		if (outputnum != null && outputnum.isEmpty() == false)
			lst.add("--focus-field=\"" + outputnum + "\"");
		if (precision != null && precision.isEmpty() == false)
			lst.add("--float-precision=\"" + precision + "\"");
		if (align != null && align.isEmpty() == false)
			lst.add("--align=\"" + align + "\"");
		if (complete != null && complete.isEmpty() == false)
			lst.add("--complete=\"" + complete + "\"");
		if (focus != null && focus.isEmpty() == false)
			lst.add("--focus-field=\"" + focus + "\"");
		
		if (cycle == true)
			lst.add("--read-cycle");
		if (scroll == true)
			lst.add("--scroll");
		if (outputrow == true)
			lst.add("--output-by-row");
		if (quoted == true)
			lst.add("--quoted-output");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildHtml(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In HTML");
		
		lst.add("--html");
		
		String uri = ini.getString(sec, "--uri");
		String mime = ini.getString(sec, "--mime");
		String encoding = ini.getString(sec, "--encoding");
		String useragent = ini.getString(sec, "--user-agent");
		String userstyle = ini.getString(sec, "--user-style");
		
		boolean browser = ini.getBoolean(sec, "--browser");
		boolean printuri = ini.getBoolean(sec, "--print-uri");
		
		if (uri != null && uri.isEmpty() == false)
			lst.add("--uri=\"" + uri + "\"");
		if (mime != null && mime.isEmpty() == false)
			lst.add("--mime=\"" + mime + "\"");
		if (encoding != null && encoding.isEmpty() == false)
			lst.add("--encoding=\"" + encoding + "\"");
		if (useragent != null && useragent.isEmpty() == false)
			lst.add("--user-agent=\"" + useragent + "\"");
		if (userstyle != null && userstyle.isEmpty() == false)
			lst.add("--user-style=\"" + userstyle + "\"");
		
		
		if (browser == true)
			lst.add("--browser");
		if (printuri == true)
			lst.add("--print-uri");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildIcons(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Icons");
		
		lst.add("--icons");
		
		
		String readdir = ini.getString(sec, "--read-dir");
		String itemwidth = ini.getString(sec, "--item-width");
		String term = ini.getString(sec, "--term");
		
		boolean monitor = ini.getBoolean(sec, "--monitor");
		boolean generic = ini.getBoolean(sec, "--generic");
		boolean sortbyname = ini.getBoolean(sec, "--sort-by-name");
		boolean descend = ini.getBoolean(sec, "--descend");
		boolean listen = ini.getBoolean(sec, "--listen");
		boolean compact = ini.getBoolean(sec, "--compact");
		boolean singleclick = ini.getBoolean(sec, "--single-click");
		
		if (readdir != null && readdir.isEmpty() == false)
			lst.add("--read-dir=\"" + readdir + "\"");
		if (itemwidth != null && itemwidth.isEmpty() == false)
			lst.add("--item-width=\"" + itemwidth + "\"");
		if (term != null && term.isEmpty() == false)
			lst.add("--term=" + term);
		
		if (monitor == true)
			lst.add("--monitor");
		if (generic == true)
			lst.add("--generic");
		if (sortbyname == true)
			lst.add("--sort-by-name");
		if (descend == true)
			lst.add("--descend");
		if (listen == true)
			lst.add("--listen");
		if (compact == true)
			lst.add("--compact");
		if (singleclick == true)
			lst.add("--single-click");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildInfo(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Info");
		
		lst.add("--info");
		
		
		String filename = ini.getString(sec, "--filename");
		String fontname = ini.getString(sec, "--fontname");
		String justify = ini.getString(sec, "--justify");
		String margins = ini.getString(sec, "--margins");
		String language = ini.getString(sec, "--language");
		String theme = ini.getString(sec, "--theme");
		String fgcolor = ini.getString(sec, "--fore");
		String bgcolor = ini.getString(sec, "--back");
		String uricolor = ini.getString(sec, "--uri-color");
		
		boolean editable = ini.getBoolean(sec, "--editable");
		boolean tail = ini.getBoolean(sec, "--tail");
		boolean showcursor = ini.getBoolean(sec, "--show-cursor");
		boolean showuri = ini.getBoolean(sec, "--show-uri");
		boolean listen = ini.getBoolean(sec, "--listen");
		
		if (filename != null && filename.isEmpty() == false)
			lst.add("--filename=\"" + filename + "\"");
		if (fontname != null && fontname.isEmpty() == false)
			lst.add("--fontname=\"" + fontname + "\"");
		if (justify != null && justify.isEmpty() == false)
			lst.add("--justify=\"" + justify + "\"");
		if (margins != null && margins.isEmpty() == false)
			lst.add("--margins=\"" + margins + "\"");
		if (language != null && language.isEmpty() == false)
			lst.add("--lang=\"" + language + "\"");
		if (theme != null && theme.isEmpty() == false)
			lst.add("--theme=\"" + theme + "\"");
		if (fgcolor != null && fgcolor.isEmpty() == false)
			lst.add("--fore=\"" + fgcolor + "\"");
		if (bgcolor != null && bgcolor.isEmpty() == false)
			lst.add("--back=\"" + bgcolor + "\"");
		if (uricolor != null && uricolor.isEmpty() == false)
			lst.add("--uri-color=\"" + uricolor + "\"");
		
		if (editable == true)
			lst.add("--editable");
		if (tail == true)
			lst.add("--tail");
		if (showcursor == true)
			lst.add("--show-cursor");
		if (showuri == true)
			lst.add("--show-uri");
		if (listen == true)
			lst.add("--listen");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildList(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In List");
		
		lst.add("--list");
		
		
		String columns = ini.getString(sec, "--column");
		String sep = ini.getString(sec, "--separator");
		String editablecols = ini.getString(sec, "--editable-cols");
		String gridlines = ini.getString(sec, "--grid-lines");
		String printcolumn = ini.getString(sec, "--print-column");
		String hidecolumn = ini.getString(sec, "--hide-column");
		String expandcolumn = ini.getString(sec, "--expand-column");
		String searchcolumn = ini.getString(sec, "--search-column");
		String tooltipcolumn = ini.getString(sec, "--tooltip-column");
		String sepcolumn = ini.getString(sec, "--sep-column");
		String sepvalue = ini.getString(sec, "--sep-value");
		String limit = ini.getString(sec, "--limit");
		String wrapwidth = ini.getString(sec, "--wrap-width");
		String wrapcols = ini.getString(sec, "--wrap-cols");
		String ellipsize = ini.getString(sec, "--ellipsize");
		String ellipsizecols = ini.getString(sec, "--ellipsize-cols");
		String dclickaction = ini.getString(sec, "--dclick-action");
		String selectaction = ini.getString(sec, "--select-action");
		String addaction = ini.getString(sec, "--add-action");
		String precision = ini.getString(sec, "--float-precision");
		
		boolean checklist = ini.getBoolean(sec, "--checklist");
		boolean radiolist = ini.getBoolean(sec, "--radiolist");
		boolean multiple = ini.getBoolean(sec, "--multiple");
		boolean editable = ini.getBoolean(sec, "--editable");
		boolean noheaders = ini.getBoolean(sec, "--no-headers");
		boolean noclick = ini.getBoolean(sec, "--no-click");
		boolean noruleshint = ini.getBoolean(sec, "--no-rules-hint");
		boolean noselection = ini.getBoolean(sec, "--no-selection");
		boolean printall = ini.getBoolean(sec, "--print-all");
		boolean regexsearch = ini.getBoolean(sec, "--regex-search");
		boolean listen = ini.getBoolean(sec, "--listen");
		boolean addontop = ini.getBoolean(sec, "--add-on-top");
		boolean tail = ini.getBoolean(sec, "--tail");
		boolean iecformat = ini.getBoolean(sec, "--iec-format");
		
		if (columns != null) {
			String[] cols = columns.split(",");
			for (String c : cols) {
				String[] a = c.split(":");
				String[] b = a[1].split("-");
				lst.add("--column=\"" + a[0] + ":" + b[1] + "\"");
			}
		}
		if (sep != null && sep.isEmpty() == false)
			lst.add("--separator=\"" + sep + "\"");
		if (editablecols != null && editablecols.isEmpty() == false)
			lst.add("--editable-cols=\"" + editablecols + "\"");
		if (gridlines != null && gridlines.isEmpty() == false)
			lst.add("--grid-lines=\"" + gridlines + "\"");
		if (printcolumn != null && printcolumn.isEmpty() == false)
			lst.add("--print-column=\"" + printcolumn + "\"");
		if (hidecolumn != null && hidecolumn.isEmpty() == false)
			lst.add("--hide-column=\"" + hidecolumn + "\"");
		if (expandcolumn != null && expandcolumn.isEmpty() == false)
			lst.add("--expand-column=\"" + expandcolumn + "\"");
		if (searchcolumn != null && searchcolumn.isEmpty() == false)
			lst.add("--search-column=\"" + searchcolumn + "\"");
		if (tooltipcolumn != null && tooltipcolumn.isEmpty() == false)
			lst.add("--tooltip-column=\"" + tooltipcolumn + "\"");
		if (sepcolumn != null && sepcolumn.isEmpty() == false)
			lst.add("--sep-column=\"" + sepcolumn + "\"");
		if (sepvalue != null && sepvalue.isEmpty() == false)
			lst.add("--sep-vlaue=\"" + sepvalue + "\"");
		if (limit != null && limit.isEmpty() == false)
			lst.add("--limit=\"" + limit + "\"");
		if (wrapcols != null && wrapcols.isEmpty() == false)
			lst.add("--wrap-cols=\"" + wrapcols + "\"");
		if (wrapwidth != null && wrapwidth.isEmpty() == false)
			lst.add("--wrap-width=\"" + wrapwidth + "\"");
		if (ellipsize != null && ellipsize.isEmpty() == false)
			lst.add("--ellipsize=\"" + ellipsize + "\"");
		if (ellipsizecols != null && ellipsizecols.isEmpty() == false)
			lst.add("--ellipsize-cols=\"" + ellipsizecols + "\"");
		if (dclickaction != null && dclickaction.isEmpty() == false)
			lst.add("--dclick-action=\"" + dclickaction + "\"");
		if (selectaction != null && selectaction.isEmpty() == false)
			lst.add("--select-action=\"" + selectaction + "\"");
		if (addaction != null && addaction.isEmpty() == false)
			lst.add("--add-action=\"" + addaction + "\"");
		if (precision != null && precision.isEmpty() == false)
			lst.add("--float-precision=\"" + precision + "\"");
		
		if (checklist == true)
			lst.add("--checklist");
		if (radiolist == true)
			lst.add("--radiolist");
		if (editable == true)
			lst.add("--editable");
		if (multiple == true)
			lst.add("--multiple");
		if (noheaders == true)
			lst.add("--no-headers");
		if (noclick == true)
			lst.add("--no-click");
		if (noruleshint == true)
			lst.add("--no-rules-hint");
		if (noselection == true)
			lst.add("--no-selection");
		if (printall == true)
			lst.add("--print-all");
		if (regexsearch == true)
			lst.add("--regex-search");
		if (listen == true)
			lst.add("--listen");
		if (addontop == true)
			lst.add("--add-on-top");
		if (tail == true)
			lst.add("--tail");
		if (iecformat == true)
			lst.add("--iec-format");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildNotebook(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Notebook");
		
		lst.add("--notebook");
		
		
		String key = ini.getString(sec, "--key");
		String tabs = ini.getString(sec, "--tab");
		String tabpos = ini.getString(sec, "tab-pos");
		String tabborders = ini.getString(sec, "--tab-borders");
		String activetab = ini.getString(sec, "--active-tab");
		
		if (key != null)
			lst.add("--key=" + key);
		if (tabs != null) {
			String[] ts = tabs.split(",");
			
			for (String t : ts) {
				lst.add("--tab=\"" + t + "\"");
			}
		}
		if (tabpos != null && tabpos.isEmpty() == false)
			lst.add("--tab-pos=" + tabpos);
		if (tabborders != null && tabborders.isEmpty() == false)
			lst.add("--tab-borders=" + tabborders);
		if (activetab != null && activetab.isEmpty() == false)
			lst.add("--active-tab=" + activetab);
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildNotification(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Notification");
		
		lst.add("--notification");
		
		
		String command = ini.getString(sec, "--command");
		String sep = ini.getString(sec, "--separator");
		String itemsep = ini.getString(sec, "--item-separator");
		String menu = ini.getString(sec, "--menu");
		String iconsize = ini.getString(sec, "--icon-size");
		
		boolean listen = ini.getBoolean(sec, "--listen");
		boolean nomiddle = ini.getBoolean(sec, "--no-middle");
		boolean hidden = ini.getBoolean(sec, "--hidden");
		
		if (command != null && command.isEmpty() == false)
			lst.add("--command=\"" + command + "\"");
		if (sep != null && sep.isEmpty() == false)
			lst.add("--separator=\"" + sep + "\"");
		if (itemsep != null && itemsep.isEmpty() == false)
			lst.add("--item-separator=\"" + itemsep + "\"");
		if (menu != null && menu.isEmpty() == false)
			lst.add("--menu=\"" + menu + "\"");
		if (iconsize != null && iconsize.isEmpty() == false)
			lst.add("--icon-size=\"" + iconsize + "\"");
		
		if (listen == true)
			lst.add("--listen");
		if (nomiddle == true)
			lst.add("--no-middle");
		if (hidden == true)
			lst.add("--hidden");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildPrint(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Print");
		
		lst.add("--print");
		
		
		String type = ini.getString(sec, "--type");
		String filename = ini.getString(sec, "--filename");
		String fontname = ini.getString(sec, "--fontname");
		
		boolean headers = ini.getBoolean(sec, "--headers");
		boolean addpreview = ini.getBoolean(sec, "--add-preview");
		
		if (type != null && type.isEmpty() == false)
			lst.add("--type=\"" + type + "\"");
		if (filename != null && filename.isEmpty() == false)
			lst.add("--filename=\"" + filename + "\"");
		if (fontname != null && fontname.isEmpty() == false)
			lst.add("--fontname=\"" + fontname + "\"");
		
		if (headers == true)
			lst.add("--headers");
		if (addpreview == true)
			lst.add("--add-preview");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}

		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildProgress(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Progress");
		
		lst.add("--progress");
		
		
		String progresstext = ini.getString(sec, "--progress-text");
		String percentage = ini.getString(sec, "--percentage");
		String enablelogtext = ini.getString(sec, "--enable-logtext");
		
		boolean rtl = ini.getBoolean(sec, "--rtl");
		boolean enablelog = ini.getBoolean(sec, "--enable-log");
		boolean autoclose = ini.getBoolean(sec, "--auto-close");
		boolean autokill = ini.getBoolean(sec, "--auto-kill");
		boolean pulsate = ini.getBoolean(sec, "--pulsate");
		boolean logontop = ini.getBoolean(sec, "--logon-top");
		boolean logexpand = ini.getBoolean(sec, "--log-expand");
		boolean logheight = ini.getBoolean(sec, "--log-height");
		
		if (progresstext != null && progresstext.isEmpty() == false)
			lst.add("--progress-text=\"" + progresstext + "\"");
		if (percentage != null && percentage.isEmpty() == false)
			lst.add("--percentage=\"" + percentage + "\"");
		if (enablelog == true) {
			if (enablelogtext != null && enablelogtext.isEmpty() == false)
				lst.add("--enable-log=\"" + enablelogtext + "\"");
			else
				lst.add("--enable-log");
		}
		
		
		if (rtl == true)
			lst.add("--rtl");
		if (autoclose == true)
			lst.add("--auto-close");
		if (autokill == true)
			lst.add("--auto-kill");
		if (pulsate == true)
			lst.add("--pulsate");
		if (logontop == true)
			lst.add("--log-on-top");
		if (logexpand == true)
			lst.add("--log-expand");
		if (logheight == true)
			lst.add("--log-height");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildProgressMulti(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Progress Multi");
		
		lst.add("--multi-progress");
		
		
		String bars = ini.getString(sec, "--bar");
		String watchbar = ini.getString(sec, "--watch-bar");
		String align = ini.getString(sec, "--align");
		
		boolean autoclose = ini.getBoolean(sec, "--auto-close");
		boolean autokill = ini.getBoolean(sec, "--auto-kill");
		
		if (bars != null) {
			String[] s = bars.split(",");
			
			for (String b : s) {
				String[] d = b.split(":");
				lst.add("--bar=\"" + d[0] + ":" + d[1] + "\"");
			}
		}
			
		if (watchbar != null && watchbar.isEmpty() == false)
			lst.add("--watch-bar=\"" + watchbar + "\"");
		if (align != null && align.isEmpty() == false)
			lst.add("--align=\"" + align + "\"");
		
		if (autoclose == true)
			lst.add("--auto-close");
		if (autokill == true)
			lst.add("--auto-kill");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildScale(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Scale");
		
		lst.add("--scale");
		
		String marks = ini.getString(sec, "--mark");
		int initvalue = 0;
		int minvalue = 0;
		int maxvalue = 0;
		int stepsize = 0;
		int pagevalue = 0;
		if (ini.keyExists(sec, "--init-value"))
			initvalue = ini.getInt(sec, "--init-value");
		if (ini.keyExists(sec, "--min-value"))
			minvalue = ini.getInt(sec, "--min-value");
		if (ini.keyExists(sec, "--max-value"))
			maxvalue = ini.getInt(sec, "--max-value");
		if (ini.keyExists(sec, "--step"))
			stepsize = ini.getInt(sec, "--step");
		if (ini.keyExists(sec, "--page"))
			pagevalue = ini.getInt(sec, "--page");
		
		boolean printpartial = ini.getBoolean(sec, "--print-partial");
		boolean hidevalue = ini.getBoolean(sec, "--hide-value");
		boolean vertical = ini.getBoolean(sec, "--vertical");
		boolean invert = ini.getBoolean(sec, "--invert");
		boolean incbuttons = ini.getBoolean(sec, "--inc-buttons");
		
		if (marks != null) {
			String[] mks = marks.split(",");
			
			for (String mark : mks) {
				String[] a = mark.split(":");
				if (a.length == 2) {
					lst.add("--mark=\"" + a[0] + ":" + a[1] + "\"");
				} else {
					lst.add("--mark=\"" + a[0] + "\"");		// NAME is optional
				}
			}
		}
		
		lst.add("--init-value=" + initvalue);
		lst.add("--min-value=" + minvalue);
		lst.add("--max-value=" + maxvalue);
		lst.add("--step=" + stepsize);
		lst.add("--page=" + pagevalue);
		
		if (printpartial == true)
			lst.add("--print-partial");
		if (hidevalue == true)
			lst.add("--hide-value");
		if (vertical == true)
			lst.add("--vertical");
		if (invert == true)
			lst.add("--invert");
		if (incbuttons == true)
		lst.add("--inc-buttons");
		
		txt.append("# Dialog '" + sec + "'\nresults=$(" + yad);
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		txt.append(")\n# -----\n");
		
		return txt.toString();
	}
	
	static private String buildGeneral(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
		String title = ini.getString(sec + "-General", "--title");
		String icon = ini.getString(sec + "-General", "--window-icon");
//		String geometry = ini.getString(sec + "-General", "geometry");
		String timeoutsecs = ini.getString(sec + "-General", "--timeout");
		String buttons = ini.getString(sec + "-General", "--button");
		String text = ini.getString(sec + "-General", "--text");
		String image = ini.getString(sec + "-General", "--image");
		String width = ini.getString(sec + "-General", "--width");
		String height = ini.getString(sec + "-General", "--height");
		String posx = ini.getString(sec + "-General", "--posx");
		String posy = ini.getString(sec + "-General", "--posy");
		String plug = ini.getString(sec + "-General", "--plug");
		String tabnum = ini.getString(sec + "-General", "--tab-num");
		String expandertext = ini.getString(sec + "-General", "--expandertext");
		String borders = ini.getString(sec + "-General", "--borders");
		String imagepath = ini.getString(sec + "-General", "--imagepath");
		String rest = ini.getString(sec + "-General", "--rest");
		String response = ini.getString(sec + "-General", "--response");
		String gtkrc = ini.getString(sec + "-General", "--gtkrc");
		String spelllang = ini.getString(sec + "-General", "--spelllang");
		String signal = ini.getString(sec + "-General", "--signal");
		
		String timeoutposition = ini.getString(sec + "-General", "--timeout-indicator");
		String btnlayout = ini.getString(sec + "-General", "--buttons-layout");
		String textalign = ini.getString(sec + "-General", "--text-align");
		String hscroll = ini.getString(sec + "-General", "--hscroll-policy");
		String vscroll = ini.getString(sec + "-General", "--vscroll-policy");
		
		Boolean killparent = ini.getBoolean(sec + "-General", "--kill-parent");
		Boolean undecorated = ini.getBoolean(sec + "-General", "--undecorated");
		Boolean fullscreen = ini.getBoolean(sec + "-General", "--fullscreen");
		Boolean noescape = ini.getBoolean(sec + "-General", "--no-escape");
		Boolean nobuttons = ini.getBoolean(sec + "-General", "--no-buttons");
		Boolean center = ini.getBoolean(sec + "-General", "--center");
		Boolean maximized = ini.getBoolean(sec + "-General", "--maximized");
		Boolean expander = ini.getBoolean(sec + "-General", "--expander");
		Boolean nomarkup = ini.getBoolean(sec + "-General", "--no-markup");
		Boolean escapeok = ini.getBoolean(sec + "-General", "--escape-ok");
		Boolean alwaysprint = ini.getBoolean(sec + "-General", "--always-print");
		Boolean sticky = ini.getBoolean(sec + "-General", "--sticky");
		Boolean fixed = ini.getBoolean(sec + "-General", "--fixed");
		Boolean mouse = ini.getBoolean(sec + "-General", "--mouse");
		Boolean ontop = ini.getBoolean(sec + "-General", "--on-top");
		Boolean skiptaskbar = ini.getBoolean(sec + "-General", "--skip-taskbar");
		Boolean splash = ini.getBoolean(sec + "-General", "--splash");
		Boolean nofocus = ini.getBoolean(sec + "-General", "--no-focus");
		Boolean closeonfocus = ini.getBoolean(sec + "-General", "--close-on-unfocus");
		Boolean selectable = ini.getBoolean(sec + "-General", "--selectable");
		Boolean enablespell = ini.getBoolean(sec + "-General", "--enable-spell");
		
		if (title != null && title.isEmpty() == false)
			lst.add("--title=\"" + title + "\"");
		if (icon != null && icon.isEmpty() == false)
			lst.add("--icon=\"" + icon + "\"");
		if (timeoutsecs != null && timeoutsecs.isEmpty() == false)
			lst.add("--timeout=\"" + timeoutsecs + "\"");
		if (text != null && text.isEmpty() == false)
			lst.add("--text=\"" + text + "\"");
		if (image != null && image.isEmpty() == false)
			lst.add("--image=\"" + image + "\"");
		if (width != null && width.isEmpty() == false)
			lst.add("--width=\"" + width + "\"");
		if (height != null && height.isEmpty() == false)
			lst.add("--height=\"" + height + "\"");
		if (posx != null && posx.isEmpty() == false)
			lst.add("--posx=\"" + posx + "\"");
		if (posy != null && posy.isEmpty() == false)
			lst.add("--posy=\"" + posy + "\"");
		if (plug != null && plug.isEmpty() == false)
			lst.add("--plug=\"" + plug + "\"");
		if (tabnum != null && tabnum.isEmpty() == false)
			lst.add("--tabnum=\"" + tabnum + "\"");
		if (borders != null && borders.isEmpty() == false)
			lst.add("--borders=\"" + borders + "\"");
		if (imagepath != null && imagepath.isEmpty() == false)
			lst.add("--image-path=\"" + imagepath + "\"");
		if (rest != null && rest.isEmpty() == false)
			lst.add("--rest=\"" + rest + "\"");
		if (response != null && response.isEmpty() == false)
			lst.add("--response=\"" + response + "\"");
		if (gtkrc != null && gtkrc.isEmpty() == false)
			lst.add("--gtkrc=\"" + gtkrc + "\"");
		if (spelllang != null && spelllang.isEmpty() == false)
			lst.add("--spell-lang=\"" + spelllang + "\"");
		if (btnlayout != null && btnlayout.isEmpty() == false)
			lst.add("--buttons-layout=\"" + btnlayout + "\"");
		if (textalign != null && textalign.isEmpty() == false)
			lst.add("--text-align=\"" + textalign + "\"");
		if (hscroll != null && hscroll.isEmpty() == false)
			lst.add("--hscroll-policy=\"" + hscroll.toLowerCase() + "\"");
		if (vscroll != null && vscroll.isEmpty() == false)
			lst.add("--vscroll-policy=\"" + vscroll.toLowerCase() + "\"");
		if (timeoutposition != null && timeoutposition.isEmpty() == false && timeoutsecs != null && timeoutsecs.isEmpty() == false)
			lst.add("--timeout-indicator=" + timeoutposition);
		
		
		if (buttons != null) {
			
			String[] a = buttons.split(",");

			for (String s : a) {
				String[] b = s.split("!");
				
				lst.add("--button=\"" + b[0] + "!" + b[1] + "!" + b[2] + "\"");
//				System.out.println("--button=\"" + b[0] + "\\!" + b[1] + "\\!" + b[2] + "\"");
			}
		}
		
		if (killparent == true) {
			if (signal == null)
				lst.add("--kill-parent");
			else
				lst.add("--kill-parent=" + signal);
		}
		if (undecorated == true)
			lst.add("--undecorated");
		if (fullscreen == true)
			lst.add("--full-screen");
		if (noescape == true)
			lst.add("--no-escape");
		if (nobuttons == true)
			lst.add("--no-buttons");
		if (center == true)
			lst.add("--center");
		if (maximized == true)
			lst.add("--maximized");
		if (expander == true) {
			if (expandertext == null)
				lst.add("--expander");
			else
				lst.add("--expander=" + expandertext);
		}
		
		if (nomarkup == true)
			lst.add("--no-markup");
		if (escapeok == true)
			lst.add("--escape-ok");
		if (alwaysprint == true)
			lst.add("--always-print-result");
		if (sticky == true)
			lst.add("--sticky");
		if (fixed == true)
			lst.add("--fixed");
		if (mouse == true)
			lst.add("--mouse");
		if (ontop == true)
			lst.add("--on-top");
		if (skiptaskbar == true)
			lst.add("--skip-taskbar");
		if (splash == true)
			lst.add("--splash");
		if (nofocus == true)
			lst.add("--no-focus");
		if (closeonfocus == true)
			lst.add("--close-on-focus");
		if (selectable == true)
			lst.add("--selectable");
		if (enablespell == true)
			lst.add("--enable-spell");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		return txt.toString();
	}
}

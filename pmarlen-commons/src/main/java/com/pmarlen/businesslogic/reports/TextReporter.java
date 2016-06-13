
package com.pmarlen.businesslogic.reports;

import com.pmarlen.model.Constants;
import com.pmarlen.model.JarpeReportsInfoDTO;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.pmarlen.businesslogic.reports.TextReporter
 * @author alfredo
 */
public class TextReporter {

	public static int columns = 32;

	public static boolean DEBUG = false;
	
	static class TextToken {

		String e;

		int i;
		int l;
		boolean o;
		
		public TextToken(String e, int max) {
			this.e = e;
			o      = false; // optional
		}

	}

	static class StaticTextToken extends TextToken {

		StaticTextToken(String e, int max) {
			super(e, max);
		}

		@Override
		public String toString() {
			l = e.length();
			return e;
		}

	}

	static abstract class VariableToken extends TextToken {

		private static boolean is1ValidSymbol(char c) {
			return String.valueOf(c).matches("[^\\$|\\#|\\?|\\{|\\}|\\,|\\s]");
		}

		String a;
		int n;
		String A;

		VariableToken(String e, int max) {
			super(e, max);
		}

		static VariableToken build(String ess, int max) {
			VariableToken vt = null;
			String e;

			int i;
			int l;
			String a;
			int n;
			String A;
			boolean isReg = ess.startsWith("#{");
			boolean isOpt = ess.startsWith("?{");
			String es = ess.substring(2, ess.length() - 1);

			String ca[] = es.split(",");

			if (DEBUG) {
				System.out.println("\t\tVariableToken.build:ca.length: spplited ->" + es + "<-, ca array->" + Arrays.asList(ca) + "<-: first:ca[0]->" + ca[0] + "<- is letterordigit?" + (Character.isLetterOrDigit(ca[0].charAt(0))) + ", is1Validsymbol?" + is1ValidSymbol(ca[0].charAt(0)));
			}

			if (ca != null && ca.length == 4) {
				if (ca[0].length() == 1 && is1ValidSymbol(ca[0].charAt(0))) {
					vt = new SymbolToken(es, max);
				} else if (isReg) {
					vt = new RecordFieldToken(es, max);
				} else {
					vt = new TextFieldToken(es, max);
				}
				vt.e = ca[0];
				vt.a = ca[1];
				int xn = ca[2].contains("%") ? (Integer.parseInt(ca[2].replace("%", "").trim()) * max) / 100
						: ca[2].startsWith("-") ? max - (Integer.parseInt(ca[2].replace("-", "")))
						: Integer.parseInt(ca[2]);
				vt.n = xn;
				vt.A = ca[3];
			} else {
				vt = new TextFieldToken(es, max);
				vt.e = ca[0];
				vt.a = "r";
				vt.n = 0;
				vt.A = "F";
			}
			if (DEBUG) {
				System.out.println("\t\tVariableToken.build: return " + vt.getClass().getSimpleName() + ", vt.e=" + vt.e);
			}
			vt.o = isOpt;
			return vt;
		}

		public String expand(String value) {
			StringBuilder sbr = new StringBuilder();
			int cl = value.length();
			if (DEBUG) {
				System.out.println("\t\tVariableToken.expand: cl=" + cl + ", value=" + value + ", this.n=" + this.n + ", this.a=" + this.a);
			}
			if (this.n > 0) {
				if (cl >= n) {
					if (this.a.equals("r")) {
						final int is1 = cl - n;
						final int is2 = cl;
						if (DEBUG) System.out.println("\t\t\tVariableToken.expand: r > substring(" + value + "," + is1 + ", " + is2+")");
						sbr.append(value.substring(is1, is2));
					} else if (this.a.equals("l")) {
						if (DEBUG) System.out.println("\t\t\tVariableToken.expand: l < substring(" + value + "," + 0 + ", " + n+")");
						sbr.append(value.substring(0, n));
					} else if (this.a.equals("c")) {
						final int is1 = ((cl - n) / 2);
						final int is2 = is1 + n;
						if (DEBUG) System.out.println("\t\t\tVariableToken.expand: c - substring(" + value + "," + is1 + ", " + is2+")");
						sbr.append(value.substring(is1, is2));
					} else {
						throw new IllegalArgumentException("for:" + this.a);
					}
				} else {
					int s = n - cl;
					int sl = (s / 2) + s % 2;
					int sr = (s / 2);
					if (this.a.equals("r")) {
						for (int i = 0; i < s; i++) {
							sbr.append(' ');
						}
						sbr.append(value.substring(0, cl));
					} else if (this.a.equals("l")) {
						sbr.append(value.substring(0, cl));
						for (int i = 0; i < s; i++) {
							sbr.append(' ');
						}
					} else if (this.a.equals("c")) {
						for (int i = 0; i < sl; i++) {
							sbr.append(' ');
						}
						sbr.append(value);
						for (int i = 0; i < sr; i++) {
							sbr.append(' ');
						}
					} else {
						throw new IllegalArgumentException("for:" + this.a);
					}
				}
				String xcontent = sbr.toString();
				l = xcontent.length();
				return xcontent;
			} else {
				l = value.length();
				return e;
			}
		}

	}

	static class TextFieldToken extends VariableToken {

		protected TextFieldToken(String e, int max) {
			super(e, max);
		}
	}

	static class SymbolToken extends VariableToken {

		protected SymbolToken(String e, int max) {
			super(e, max);
		}
	}

	static class RecordFieldToken extends VariableToken {

		protected RecordFieldToken(String e, int max) {
			super(e, max);
		}
	}

	private static String mergeInsert(String s, String i, int p) {
		if (DEBUG) {
			System.err.println("processLine: mergeInsert s:->" + s + "<-, i:->" + i + "<-, p" + p);
		}
		//return "??????????????????????????????????????????????????";
		return s.substring(0, p) + i + s.substring(p + i.length());
	}

	private static String processLine(String srcline, HashMap<String, Object> parameters, Map<String, ?> rfield, int maxLineWidth) {
		List<TextToken> tokens = new ArrayList<TextToken>();

		int fromIndex = 0;
		int beginIndex = 0;
		int endIndex = 0;
		if (DEBUG) {
			System.err.println("============================================================================================>>>>>");
		}
		if (DEBUG) {
			System.err.println("processLine: ->" + srcline + "<-, maxLineWidth=" + maxLineWidth);
		}
		String inThemiddle = null;
		int cont = 0;
		String sub = null;

		//String regExp = "(\\$|\\#)\\{((\\p{Alpha})(\\p{Alnum}|\\.)+|([^\\$|\\{|\\}|\\,|\\s]))\\,(r|c|l)\\,(((-)?)\\p{Digit}+|(\\p{Digit}+(%)?))\\,(L|C|R|F)\\}";
		String regExp = "(\\$|\\?|\\#)\\{((\\p{Alpha})(\\p{Alnum}|\\.)+|([^\\$|\\{|\\}|\\,|\\s]))\\,(r|c|l)\\,(((-)?)\\p{Digit}+|(\\p{Digit}+(%)?))\\,(L|C|R|F)\\}";
		
		Pattern pattern = Pattern.compile(regExp);

		Matcher matcher = pattern.matcher(srcline);
		boolean found = false;
		cont = 0;
		beginIndex = 0;
		endIndex = 0;
		int prevBeginIndex = 0;
		int prevEndIndex = 0;
		while (matcher.find()) {
			beginIndex = matcher.start();

			endIndex = matcher.end();
			if (beginIndex > prevEndIndex) {
				inThemiddle = srcline.substring(prevEndIndex, beginIndex);
				tokens.add(new StaticTextToken(inThemiddle, maxLineWidth));
				if (DEBUG) {
					System.err.println("\tprocessLine:inThemiddle->" + inThemiddle + "<-");
				}
			}

			prevBeginIndex = beginIndex;
			prevEndIndex = endIndex;
			String txt = matcher.group();
			tokens.add(VariableToken.build(txt, maxLineWidth));
			if (DEBUG) {
				System.err.println("\tprocessLine:contentToken->" + txt + "<-");
			}

			if (matcher.hitEnd() && endIndex < srcline.length()) {
				inThemiddle = srcline.substring(endIndex + 1);
				tokens.add(new StaticTextToken(inThemiddle, maxLineWidth));
				if (DEBUG) {
					System.err.println("\tprocessLine:inThemiddle last->" + inThemiddle + "<-");
				}
			}

			cont++;
			found = true;
		}

		//======================================================================
		StringBuilder sbProcessed = new StringBuilder();
		int cc = 0;
		int indexStart = 0;
		int indexEnd = 0;
		String ti = null;
		boolean guia = false;
		for (int i = 1; i <= maxLineWidth; i++) {
			if (guia) {
				if (i % 10 == 0) {
					sbProcessed.append('_');
				} else {
					sbProcessed.append('.');
				}
			} else {
				sbProcessed.append(' ');
			}
		}
		String fp = null;
		TextToken tb = null;
		fp = sbProcessed.toString();
		String rp = "";
		int tc = 0;
		int p = 0;
		int iter = 0;
		boolean deleteLine=false;
		for (TextToken tt : tokens) {
			ti="";
			if (DEBUG) {
				System.err.println("#---[" + iter + "]---");
			}
			if (DEBUG) {
				System.err.println("tt         :->" + tt.e + "<-(0):" + tt.getClass().getSimpleName() + ", params keys=" + parameters.keySet() + ", is TextFieldToken ?" + (tt instanceof TextFieldToken));
			}
			if (tt instanceof StaticTextToken) {
				ti = tt.toString();
				tc = ti.length();
				cc += tc;
				p = tb != null ? tb.i + tb.l : 0;
				if (DEBUG) {
					System.err.println("p          :->" + p + "<- (1)");
				}
			} else if (tt instanceof TextFieldToken ){
				if(parameters.containsKey(tt.e)) {
					ti = ((VariableToken) tt).expand(parameters.get(tt.e).toString());
					tc = ti.length();
					cc += tc;
					VariableToken vt = (VariableToken) tt;
					p = vt.A.equals("L") ? 0
							: vt.A.equals("R") ? maxLineWidth - tc
							: vt.A.equals("C") ? (maxLineWidth - tc) / 2
							: vt.A.equals("F") ? (tb != null ? tb.i + tb.l : 0)
							: 0;
					if (DEBUG) {
						System.err.println("p          :->" + p + "<- (2)");
					}
				} else if(tt.o){ // is Optional token line
					deleteLine = true;
				}
			} else if (tt instanceof SymbolToken) {
				StringBuilder sbst = new StringBuilder();
				for (int i = 1; i <= ((SymbolToken) tt).n; i++) {
					sbst.append(tt.e.charAt(0));
				}
				ti = sbst.toString();
				tc = ti.length();
				cc += tc;
				VariableToken vt = (VariableToken) tt;
				p = vt.A.equals("L") ? 0
						: vt.A.equals("R") ? maxLineWidth - tc
						: vt.A.equals("C") ? (maxLineWidth - tc) / 2
						: vt.A.equals("F") ? (tb != null ? tb.i + tb.l : 0)
						: 0;
				if (DEBUG) {
					System.err.println("p          :->" + p + "<- (3)");
				}
			} else if (tt instanceof RecordFieldToken && rfield != null && rfield.containsKey(tt.e)) {
				/*
				 ti = ((VariableToken) tt).expand(rfield.get(tt.e));
				 tc = ti.length();
				 cc += tc;
				 p = tb != null ? tb.i + tb.l : 0;
				 if(DEBUG) System.err.println("p          :->"+p+"<- (4)");
				 */
				ti = ((RecordFieldToken) tt).expand(rfield.get(tt.e).toString());
				tc = ti.length();
				cc += tc;
				VariableToken vt = (VariableToken) tt;
				p = vt.A.equals("L") ? 0
						: vt.A.equals("R") ? maxLineWidth - tc
						: vt.A.equals("C") ? (maxLineWidth - tc) / 2
						: vt.A.equals("F") ? (tb != null ? tb.i + tb.l : 0)
						: 0;
				if (DEBUG) {
					System.err.println("p          :->" + p + "<- (4)");
				}
			}
			tt.i = p;

			if (DEBUG) {
				System.err.println("mergeInsert:->" + fp + "<- <= ->" + ti + "<-," + p);
			}
			fp = mergeInsert(fp, ti, p);

			tb = tt;

			iter++;
		}
		if(deleteLine){
			return null;
		}else{
			return fp;
		}
	}

	private static void processReport(InputStream isReport, OutputStream os, HashMap<String, Object> parameters, Collection<Map<String,?>> records, int maxLineWidth) {
		processReport(isReport, os, parameters, records, maxLineWidth, "\n", "UTF-8");
	}

	private static void processReport(InputStream isReport, OutputStream os, HashMap<String, Object> parameters, Collection<Map<String,?>> records, int maxLineWidth, String endLine, String encoding) {
		BufferedReader br = null;
		PrintStream ps = null;
		br = new BufferedReader(new InputStreamReader(isReport));
		try {
			ps = new PrintStream(os, true, encoding);
		} catch (UnsupportedEncodingException ue) {
			throw new IllegalArgumentException(encoding + " NOT SUPORTED");
		}
		String sourceLine = null;

		List<String> preLines = new ArrayList<String>();
		List<String> postLines = new ArrayList<String>();
		List<String> detailLines = new ArrayList<String>();

		try {
			int zone = 0;
			while ((sourceLine = br.readLine()) != null) {
				if (DEBUG) {
					System.err.print("READLINE:->" + sourceLine + "<-");
				}
				if (sourceLine.contains("#{")) {
					zone = 2;
				} else {
					if (zone == 2 || zone == 3) {
						zone = 3;
					} else {
						zone = 1;
					}
				}
				if (DEBUG) System.err.println("\tZONA_->" + zone);
				if (zone == 1) {
					preLines.add(sourceLine);
				}
				if (zone == 2) {
					detailLines.add(sourceLine);
				}
				if (zone == 3) {
					postLines.add(sourceLine);
				}
			}

			//==================================================================
			for (String l : preLines) {
				final String pl1 = processLine(l, parameters, null, maxLineWidth);
				if(pl1!=null){
					ps.println(pl1);
				}
			}
			for (Map<String, ?> r : records) {
				for (String l : detailLines) {
					final String pl2 = processLine(l, parameters, r, maxLineWidth);
					if(pl2!=null){
						ps.println(pl2);
					}
				}
			}
			for (String l : postLines) {
				final String pl3 = processLine(l, parameters, null, maxLineWidth);
				if(pl3!=null){
					ps.println(pl3);
				}
			}

		} catch (IOException ioe) {

		}

	}

	public static void setColumns(int columns) {
		TextReporter.columns = columns;
	}
	
	public static byte[] generateTicketTXTBytes(JarpeReportsInfoDTO jrInfo) {
		return generateTicketTXTBytes(jrInfo, "\n", "UTF-8");
	}
	
	public static byte[] generateTicketTXTBytes(JarpeReportsInfoDTO jrInfo, String endLine, String encoding) {
		ByteArrayOutputStream baos = null;
		byte content[]=null;
		try {
			baos = new ByteArrayOutputStream();
			processReport(TextReporter.class.getResourceAsStream("/textreports/TEXT_TICKET_V4.txt"), baos, jrInfo.getParameters(), jrInfo.getRecords(), columns, endLine,  encoding);
			content = baos.toByteArray();
		} catch (Exception ioe) {
			ioe.printStackTrace(System.err);			
		}
		return content;
	}
	

	public static List<String> justifyText(String text, int maxPerColumn) {
		List<String> result = new ArrayList<String>();

		String[] words = text.split("\\s");
		String currentLine = "";

		for (String w : words) {
			if (w.trim().length() == 0) {
				continue;
			}
			if (currentLine.length() + 1 + w.length() <= maxPerColumn) {
				if (currentLine.length() == 0) {
					currentLine = w;
				} else {
					currentLine = currentLine + " " + w;
				}

			} else {
				result.add(currentLine);
				currentLine = w;
			}
		}

		if (currentLine.length() > 0) {
			result.add(currentLine);
			currentLine = "";
		}

		return result;
	}
	
	public static List<String> splitInLinesText(String text, int maxPerColumn,Character fill) {
		List<String> result = new ArrayList<String>();
		int lt=text.length(),i1=0,i2=maxPerColumn;
		String lx= null;
		StringBuilder fillBuff=null;
		while(i2<lt){
			lx= text.substring(i1, i2).trim();
			if(lx.length() < maxPerColumn && fill != null){
				fillBuff = new StringBuilder();
				final int fitTot = maxPerColumn - lx.length();
				for(int j=0;j< (fitTot);j++){
					fillBuff.append(fill);
				}
				result.add(lx+fillBuff.toString());
			} else {
				result.add(lx);
			}
			i1=i2;
			i2+=maxPerColumn;
		}
		i2=lt;
		lx= text.substring(i1, i2).trim();
		
		if(lx.length() < maxPerColumn && fill != null){
			fillBuff = new StringBuilder();
			final int fitTot = maxPerColumn - lx.length();
			for(int j=0;j<fitTot;j++){
				fillBuff.append(fill);
			}
			result.add(lx+fillBuff.toString());
		} else {
			result.add(lx);
		}
		
		return result;
	}
}

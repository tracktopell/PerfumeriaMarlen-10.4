/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools,Templates
 * and open the template in the editor.
 */
package com.pmarlen.migration;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alfredo
 */
public class PrepareDataForTest_PM104 {

	private static Connection newDBConnection = null;

	private static boolean debug = false;

	private static String usrNewDB = null;
	private static String pwdNewDB = null;
	private static String urlNewDB = null;

	public static void main(String[] args) {
		System.out.println("---------------------- PrepareDataForTest_PM104 -----------------------");

		usrNewDB = args[0];
		pwdNewDB = args[1];
		urlNewDB = args[2];
		
		connectToNewDB();
				
		try {
			long t0=System.currentTimeMillis();
			long t1,t2,t3,T;
			System.out.println("---------------------- SELECT ALL PRODUCTOS -----------------------");
			
			List<Object[]> resultAllProductos = executeQuery(newDBConnection, "SELECT CODIGO_BARRAS,COSTO_VENTA FROM PRODUCTO");
			
			List<Object[]> resultAllAlmacenes = executeQuery(newDBConnection, "SELECT ID,TIPO_ALMACEN,SUCURSAL_ID FROM ALMACEN WHERE SUCURSAL_ID IN (2,3,4,5,6)");
			
			debug = false;
			int tot=resultAllProductos.size();
			int i=0;
			int adv=0;
			System.out.println("EXECUTING UPDATE OR INSERT:");
			for(Object[] prodArr: resultAllProductos){
				String codigoBarras = prodArr[0].toString();
				Double costoVenta   = ((Double)prodArr[1]);
				Random r=new Random(System.currentTimeMillis());
				
				for(Object[] almaArr: resultAllAlmacenes){
					
					int cantidadNueva = 200+r.nextInt(1000);
					
					Integer almacenId=(Integer)almaArr[0];
					
					if(executeDirectUpdate(newDBConnection,"UPDATE ALMACEN_PRODUCTO SET CANTIDAD = "+cantidadNueva+" WHERE ALMACEN_ID="+almacenId+" AND PRODUCTO_CODIGO_BARRAS='"+codigoBarras+"'")==0){
						executeDirectUpdate(newDBConnection,"INSERT INTO ALMACEN_PRODUCTO VALUES("+almacenId+",'"+codigoBarras+"',"+cantidadNueva+","+costoVenta+",NULL)");
					}
				}
				i++;
				adv= (i *100) / tot;
				t1 = System.currentTimeMillis();
				System.out.print("ADVANCE: \t"+i+"/\t"+tot+"\t:"+adv+" % [ "+enalapsed(t0, t1)+" ] \r");
			}
			t2 = System.currentTimeMillis();
			System.out.println("ADVANCE: \t"+i+"/\t"+tot+"\t:"+adv+" % [ "+enalapsed(t0, t2)+" ] \r");;
			
			System.out.println("=================================== END IMPORT OK =================================");	
			System.exit(0);	
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(3);
		}
	}
	
	private static final DecimalFormat df = new DecimalFormat("00");
	
	private static String enalapsed(long t0,long t1){
		StringBuffer sb=new StringBuffer();
		long milliseconds = t1 -t0;
		
		int seconds = (int) (milliseconds / 1000) % 60 ;
		int minutes = (int) ((milliseconds / (1000*60)) % 60);
		int hours   = (int) ((milliseconds / (1000*60*60)) % 24);
		
		if(hours>0){
			sb.append(df.format(hours)).append(":");			
		}
		if(minutes>0){
			sb.append(df.format(minutes)).append(":");			
		}
		if(seconds>0){
			sb.append(df.format(seconds));			
		}
		
		return sb.toString();
	}

	private static void executeMultipleUpdates(String sql, List<Object[]> resultLineas) throws SQLException {
		executeMultipleUpdates(newDBConnection, sql, resultLineas);
	}
	
	private static void executeMultipleUpdates(Connection conn,String sql, List<Object[]> resultLineas) throws SQLException {
		System.out.println("-> executeMultipleUpdates: SQL:"+sql);
		final PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		int cm = 0;
        int tm = resultLineas.size();
		for (Object[] row : resultLineas) {
			if (debug) {
				System.out.println("SQL:" + sql + " <= " + Arrays.asList(row));
			}
			executeUpdate(ps, row);
			cm++;
			System.out.print("\rAv:\t"+((cm*100)/tm)+"%");
		}
		System.out.println("\t Total Affected: " + cm);
		
		ps.close();
	}

	private static void connectToNewDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(1);
		}

		try {
			newDBConnection = DriverManager.getConnection(urlNewDB, usrNewDB, pwdNewDB);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(2);
		}
	}

	private static List<Object[]> executeQuery(Connection conn, String sql) throws SQLException {		
		System.out.println("-> executeQuery: SQL:"+sql);
		List<Object[]> result = new ArrayList<Object[]>();
		final Statement st = conn.createStatement();

		final ResultSet rs = st.executeQuery(sql);
		final ResultSetMetaData rsMD = rs.getMetaData();
		if (debug) {
			for (int i = 1; i <= rsMD.getColumnCount(); i++) {
				if (i > 1) {
					System.out.print(",");
				}
				System.out.print(rsMD.getColumnName(i));
			}
			System.out.println("");
		}
		int c = 0;
		for (c = 0; rs.next(); c++) {
			Object[] row = new Object[rsMD.getColumnCount()];
			for (int i = 1; i <= rsMD.getColumnCount(); i++) {
				//row[i - 1] = rs.getObject(rsMD.getColumnName(i));
				row[i - 1] = rs.getObject(i);

				if (debug) {
					if (i > 1) {
						System.out.print(",");
					}
					System.out.print(row[i - 1]);
				}

			}
			result.add(row);
			if (debug) {
				System.out.println("");
			}
		}
		System.out.println("Count: " + c);
		
		rs.close();
		return result;
	}
	/*
	private static void executeUpdate(Connection conn, PreparedStatement st, Object[] row) throws SQLException {
		st.clearParameters();
		st.clearWarnings();

		for (int j = 1; j <= row.length; j++) {
			st.setObject(j, row[j - 1]);
		}

		int r = st.executeUpdate();
		if (debug) {
			System.out.println("Affected: " + r);
		}
	}
	*/
	
	private static Object executeUpdate(PreparedStatement st, Object[] row) throws SQLException {
		Object generatedKey=null;
		
		if(row != null) {
			st.clearParameters();
			st.clearWarnings();	
			for (int j = 1; j <= row.length; j++) {
				st.setObject(j, row[j - 1]);
			}
		}

		int r = st.executeUpdate();
		if (debug) {
			System.out.print("Affected: " + r);
		}
		
		ResultSet gksRS = st.getGeneratedKeys();
		if(gksRS != null) {
			if(gksRS.next()){
				generatedKey = gksRS.getObject(1);
			}
			if (debug) {
				System.out.print(", KEY=" + generatedKey);
			}
		}
		if (debug) {
			System.out.println(".");
		}
		
		return generatedKey;
	}
	
	private static int executeDirectUpdate(Connection conn, String q) throws SQLException {		
		if (debug) {
			System.out.println("-> executeDirectUpdate: SQL:"+q);
		}
		int r = conn.createStatement().executeUpdate(q);
		if (debug) {
			System.out.println("Affected: " + r);
		}
		return r;
	}

	private static String extractXMLValue(String label, String xml) {
		String bl = "<"+label+">";
		String el = "</"+label+">";
		int    bli = xml.indexOf(bl);
		int    bti = bli+bl.length();
		int    eli = xml.indexOf(el);
		
		if(bli >=  0 && eli > bti) {
			return xml.substring(bti, eli);
		} else {
			return null;
		}		
	}
	
	private static String extractXMLAtribute(String atribute, String xml) {
		
		int    bli = xml.indexOf(atribute);
		int    bti = xml.indexOf("\"",bli);
		int    eti = xml.indexOf("\"",bti+1);
		
		if(bli >=  0 && eti > bti) {
			return xml.substring(bti+1, eti);
		} else {
			return null;
		}
	}
}

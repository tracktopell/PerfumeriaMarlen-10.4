/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools,Templates
 * and open the template in the editor.
 */
package com.pmarlen.migration;

import com.lowagie.text.pdf.PdfName;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.businesslogic.GeneradorNumTicket;
import com.pmarlen.model.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alfredo
 */
public class HomogenizarVentas_PM104 {

	private static Connection newDBConnection = null;

	private static boolean debug = false;

	private static String usrNewDB = null;
	private static String pwdNewDB = null;
	private static String urlNewDB = null;

	private static int sucursalId = 0;
	private static DecimalFormat sdf = new DecimalFormat("###############0.000000");
	
	private class  RegVenta {
	
	}
	
	
	
	public static void main(String[] args) {
		
		usrNewDB = args[0];
		pwdNewDB = args[1];
		urlNewDB = args[2];

		System.out.println("---------------------- HomogenizarVentas_PM104 -----------------------");
		if(debug){
			System.out.println("-->>> DEBUG MODE");
		}
		
		
		connectToNewDBTransactional();

		try {
			long t1, t2, t3, T;
			String queryVentasPM103 =	"SELECT  ES.ID,\n" +													// 0
										"        ESD.ALMACEN_ID,\n" +
										"        ES.AUTORIZA_DESCUENTO,\n" +
										"        ES.TOTAL,\n" +
										"        ES.TOTAL_COBRADO,\n" +
										"        ES.IMPORTE_RECIBIDO,\n"+										// 5
										"        ES.CAMBIO,\n" +
										"        ES.DESCUENTO_APLIC,\n" +
										"        ES.PORCENTAJE_DESCUENTO_CALCULADO,\n" +
										"        ES.PORCENTAJE_DESCUENTO_EXTRA,\n" +
										"        ES.ELEM_DET,\n" +												// 10
										"        ES.TOT_PRODS,\n" +
										"        ES.SUB_TOTAL_1RA,\n" +
										"        ES.SUB_TOTAL_OPO,\n" +
										"        ES.SUB_TOTAL_REG,\n" +
										"        SUM(ESD.CANTIDAD * ESD.PRECIO_VENTA) AS IMPORTE_BRUTO_X,\n" +	// 15
										"        SUM(ESD.CANTIDAD) T1,\n"+
										"        SUM(1) T2\n" +													// 17
										"FROM    ENTRADA_SALIDA ES,ENTRADA_SALIDA_DETALLE ESD\n" +
										"WHERE   1=1\n" +
										"AND     ES.ID >= 5500\n"+
										"AND     ES.PEDIDO_SUCURSAL IS NULL\n" +
										"AND     ES.ID = ESD.ENTRADA_SALIDA_ID\n" +
										"GROUP   BY ES.ID, ESD.ALMACEN_ID";
			
			System.out.println("--->> HOMOGENIZANDO VENTAS ACTUALES:");
			
			final List<Object[]> ventas = executeQuery(newDBConnection,queryVentasPM103);
			int esID     = -1;
			int esIDLast = -1;
			Integer esAlmacenId = null;
			Integer autorizaDescunto = null;
			Double  total= 0.0;
			Double  totalCobrado = 0.0;
			Integer elemDet = 0;
			Integer totProds = 0;
			Double  importeRecib = null;
			Double  cambio = null;
			Double  descAplic = null;
			Integer porcentajeDescCalc  = null;
			Integer porcentajeDescExtra = null;
			Double  importeBruto = null;
			Integer  tot1 = null;
			Integer  tot2 = null;
			
			Double  st1ra = 0.0;
			Double  stOpo = 0.0;			
			Double  stReg = 0.0;
			boolean first=true;
			int numReg = 0;
			System.out.println("->ventas.size="+ventas.size());
			for(Object[] ventasArr:ventas){
				numReg++;
				
				//System.out.println("READ["+numReg+"]:"+Arrays.asList(ventasArr));
				
				
				esID				= (Integer)ventasArr[0];
				if(first){
					esIDLast        = esID;
					first			= false;
				}
				
				if(esID == esIDLast && numReg != ventas.size()){
					esAlmacenId			= (Integer)ventasArr[1];
					autorizaDescunto	= (Integer)ventasArr[2];
					if(autorizaDescunto==null) autorizaDescunto=0;
					total				= (Double) ventasArr[3];
					if(total==null)		total = 0.0;
					totalCobrado		= (Double) ventasArr[4];
					if(totalCobrado==null)totalCobrado=0.0;
					importeRecib		= (Double) ventasArr[5];
					if(importeRecib ==null)importeRecib=0.0;
					cambio				= (Double) ventasArr[6];
					if(cambio==null) cambio=0.0;
					descAplic			= (Double)ventasArr[7];
					if(descAplic==null)descAplic = 0.0;
					porcentajeDescCalc	= (Integer)ventasArr[8];
					if(porcentajeDescCalc == null) porcentajeDescCalc = 0;					
					porcentajeDescExtra	= (Integer)ventasArr[9];
					if(porcentajeDescExtra==null) porcentajeDescExtra = 0;
					importeBruto		= (Double) ventasArr[15];
					tot1				= ((java.math.BigDecimal) ventasArr[16]).intValue();
					tot2				= ((java.math.BigDecimal) ventasArr[17]).intValue();

					elemDet			+= tot1;
					totProds		+= tot2;

					if(esAlmacenId == 1){
						st1ra = importeBruto;
					} else if(esAlmacenId == 2){
						stOpo = importeBruto;
					} else if(esAlmacenId == 3){
						stReg = importeBruto;
					}				
					total			= st1ra + stOpo + stReg;
					totalCobrado	= total;
					
				} else if(esID != esIDLast || numReg == ventas.size()){
					if(numReg != ventas.size()) {
						//System.out.println("\t>>["+((numReg == ventas.size())?"*":" ")+""+numReg+"] ID: ["+esIDLast+"]\t"+sdf.format(total)+"\t"+sdf.format(totalCobrado)+"\t"+sdf.format(cambio)+"\t"+elemDet+"\t"+totProds+"\t"+sdf.format(st1ra)+"\t"+sdf.format(stOpo)+"\t"+sdf.format(stReg)+"\t-"+porcentajeDescCalc+"%"+"\t-"+porcentajeDescExtra+"%");
						updatePV(esIDLast,porcentajeDescCalc,porcentajeDescExtra,elemDet,totProds,st1ra,stOpo,stReg);
					}
					
					if(esID != esIDLast ){
						st1ra			= 0.0;
						stOpo			= 0.0;
						stReg			= 0.0;
						elemDet			= 0;
						totProds		= 0;
					}
					
					esAlmacenId			= (Integer)ventasArr[1];
					autorizaDescunto	= (Integer)ventasArr[2];
					if(autorizaDescunto==null) autorizaDescunto=0;
					total				= (Double) ventasArr[3];
					if(total==null)		total = 0.0;
					totalCobrado		= (Double) ventasArr[4];
					if(totalCobrado==null)totalCobrado=0.0;
					importeRecib		= (Double) ventasArr[5];
					if(importeRecib ==null)importeRecib=0.0;
					cambio				= (Double) ventasArr[6];
					if(cambio==null) cambio=0.0;
					descAplic			= (Double)ventasArr[7];
					if(descAplic==null)descAplic = 0.0;
					porcentajeDescCalc	= (Integer)ventasArr[8];
					if(porcentajeDescCalc == null) porcentajeDescCalc = 0;					
					porcentajeDescExtra	= (Integer)ventasArr[9];
					if(porcentajeDescExtra==null) porcentajeDescExtra = 0;
					importeBruto		= (Double) ventasArr[15];
					tot1				= ((java.math.BigDecimal) ventasArr[16]).intValue();
					tot2				= ((java.math.BigDecimal) ventasArr[17]).intValue();

					elemDet			+= tot1;
					totProds		+= tot2;

					if(esAlmacenId == 1){
						st1ra = importeBruto;
					} else if(esAlmacenId == 2){
						stOpo = importeBruto;
					} else if(esAlmacenId == 3){
						stReg = importeBruto;
					}
					
					total			= st1ra + stOpo + stReg;
					totalCobrado	= total;
					
					if(numReg == ventas.size()){
						//System.out.println("\t>>["+((numReg == ventas.size())?"*":" ")+""+numReg+"] ID: ["+esID+"]\t"+sdf.format(total)+"\t"+sdf.format(totalCobrado)+"\t"+sdf.format(cambio)+"\t"+elemDet+"\t"+totProds+"\t"+sdf.format(st1ra)+"\t"+sdf.format(stOpo)+"\t"+sdf.format(stReg)+"\t-"+porcentajeDescCalc+"%"+"\t-"+porcentajeDescExtra+"%");
						updatePV(esID,porcentajeDescCalc,porcentajeDescExtra,elemDet,totProds,st1ra,stOpo,stReg);
					}
					
					cambio			= 0.0;
					
				}
				esIDLast = esID;
			}
						
			System.out.println("=================================== END IMPORT OK =================================");
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(3);
		}
	}
	
	private static void updatePV(int id,int descCalc,int descExtra,int elemDEt,int numProds,Double  st1ra,Double  stOpo,Double  stReg){
		Double total;
		Double subTotal;
		Double subTotalNG;
		Double descAplic;
		Double iva;
		Double totalCobrado;
		Double cambio;
		Double impDesc = 0.0;
		
		descAplic = (descCalc/100.0) + (descExtra/100.0);
		 
		subTotal     = st1ra + stOpo + stReg;
		subTotalNG   = (subTotal/1.16);
		impDesc      = descAplic*subTotalNG;
		iva          = (subTotalNG - impDesc)* 0.16;
		total        = subTotalNG - impDesc + iva;
		totalCobrado = total;
		cambio       = 0.0;
		
		String updatePV =	"UPDATE  ENTRADA_SALIDA SET TOTAL="+sdf.format(total)+",DESCUENTO_APLIC="+sdf.format(impDesc)+",SUB_TOTAL_1RA="+sdf.format(st1ra)+",SUB_TOTAL_OPO="+sdf.format(stOpo)+",SUB_TOTAL_REG="+sdf.format(stReg)+" WHERE ID="+id+"; IVA="+sdf.format(iva);
		//String updatePV =	"UPDATE  ENTRADA_SALIDA SET IB="+sdf.format(subTotal)+",ING="+sdf.format(subTotalNG)+" WHERE ID="+id+";";
		
		System.out.println("\t"+updatePV);
		
	}
	
	

	private static final DecimalFormat df = new DecimalFormat("00");

	private static String enalapsed(long t0, long t1) {
		StringBuffer sb = new StringBuffer();
		long milliseconds = t1 - t0;

		int seconds = (int) (milliseconds / 1000) % 60;
		int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
		int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

		if (hours > 0) {
			sb.append(df.format(hours)).append(":");
		}
		if (minutes > 0) {
			sb.append(df.format(minutes)).append(":");
		}
		if (seconds > 0) {
			sb.append(df.format(seconds));
		}

		return sb.toString();
	}

	private static void executeMultipleUpdates(String sql, List<Object[]> resultLineas) throws SQLException {
		executeMultipleUpdates(newDBConnection, sql, resultLineas);
	}

	private static void executeMultipleUpdates(Connection conn, String sql, List<Object[]> resultLineas) throws SQLException {
		System.out.println("-> executeMultipleUpdates: SQL:" + sql);
		final PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int cm = 0;
		int tm = resultLineas.size();
		for (Object[] row : resultLineas) {
			if (debug) {
				System.out.println("SQL:" + sql + " <= " + Arrays.asList(row));
			}
			executeUpdate(ps, row);
			cm++;
			System.out.print("\rAv:\t" + ((cm * 100) / tm) + "%");
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

	private static void connectToNewDBTransactional() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(1);
		}

		try {
			newDBConnection = DriverManager.getConnection(urlNewDB, usrNewDB, pwdNewDB);
			newDBConnection.setAutoCommit(false);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(2);
		}
	}

	private static List<Object[]> executeQuery(Connection conn, String sql) throws SQLException {
		if (debug) {
			System.out.println("-> executeQuery: SQL:" + sql);
		}
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
		if (debug) {
			System.out.println("Count: " + c);
		}

		rs.close();
		return result;
	}

	private static Object executeUpdate(PreparedStatement st, Object[] row) throws SQLException {
		Object generatedKey = null;

		if (row != null) {
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
		if (gksRS != null) {
			if (gksRS.next()) {
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
			System.out.println("-> executeDirectUpdate: SQL:" + q);
		}
		int r = conn.createStatement().executeUpdate(q);
		if (debug) {
			System.out.println("Affected: " + r);
		}
		return r;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools,Templates
 * and open the template in the editor.
 */
package com.pmarlen.migration;

import com.pmarlen.businesslogic.GeneradorNumTicket;
import static com.pmarlen.businesslogic.LogicaFinaciera.CALCULO_DESC_ALMACEN;
import static com.pmarlen.businesslogic.LogicaFinaciera.calculaTotales;
import static com.pmarlen.businesslogic.LogicaFinaciera.getImpuestoIVA;
import com.pmarlen.businesslogic.TotalesCalculados;
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
import java.util.Date;
import java.util.List;

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
		
		//connectToNewDBTransactional();
		connectToNewDB();

		try {
			long t1, t2, t3, T;
			
			String queryVentasPM103    ="SELECT    ES.ID,ES.FECHA_CREO,ES.SUCURSAL_ID,ES.CAJA\n" +
										"FROM      ENTRADA_SALIDA ES\n" +
										"WHERE     1=1\n" +
										"AND       ES.PEDIDO_SUCURSAL IS NULL;";
			
			String queryDetVentasPM103 ="SELECT ES.ID,\n" +								// 0							
										"       A.TIPO_ALMACEN,\n" +					// 1
										"       ESD.CANTIDAD,\n" +						// 2
										"       ESD.PRECIO_VENTA,\n" +					// 3
										"       ES.AUTORIZA_DESCUENTO,\n" +				// 4
										"       ES.PORCENTAJE_DESCUENTO_CALCULADO,\n" +	// 5
										"       ES.PORCENTAJE_DESCUENTO_EXTRA\n" +		// 6
										"FROM   ENTRADA_SALIDA ES,ENTRADA_SALIDA_DETALLE ESD,ALMACEN A\n" +
										"WHERE  1=1\n" +
										"AND    ES.ID = ? \n" +
										"AND    ES.ID = ESD.ENTRADA_SALIDA_ID\n" +
										"AND    ESD.ALMACEN_ID = A.ID ;";
			
			System.out.println("--->> HOMOGENIZANDO VENTAS ACTUALES:");
			
			final List<Object[]> ventas = executeQuery(newDBConnection,queryVentasPM103);
			System.out.println("\t->ID_VTA");
			int i=0;
			int adv = 0;
			Date fecha = null;
			int  idSuc = 0;
			Integer  caja=null;
			Integer idVenta=null;
			for(Object[] vtaArr: ventas){
				i++;
				idVenta = (Integer)vtaArr[0];
				fecha   = (Date)   vtaArr[1];
				idSuc   = (Integer)vtaArr[2];
				caja    = (Integer)vtaArr[3];
				
				if(caja==null){
					caja = 1;
				}
				
				//System.out.println("\t"+Arrays.asList(vtaArr));
				
				final List<Object[]> ventasDet = executeQuery(newDBConnection,queryDetVentasPM103.replaceAll("\\?", String.valueOf(idVenta)));
				double factorIva = getImpuestoIVA();
				List<Object[]> esdList = new ArrayList<Object[]>();
				Integer autorizaDescuento = null;
				boolean descunetoHabilitado = true;
				double factorDescExtra = 0.0;
				boolean redondear = false;
				double recibidoEfectivo = 0.0;
				double recibidoTarjeta = 0.0;

				for(Object[] regDetVent: ventasDet){
					//System.out.println("\t"+Arrays.asList(regDetVent));
					autorizaDescuento   = regDetVent[4]!=null?(Integer)regDetVent[4]:0;
					descunetoHabilitado = (autorizaDescuento!=null && autorizaDescuento==1)?true:false;
					factorDescExtra     = regDetVent[6]!=null?((Integer)regDetVent[6]) / 100.0:0;
					
					esdList.add(new Object[]{(Integer)regDetVent[1],(Integer)regDetVent[2],(Double)regDetVent[3]});		
				}

				TotalesCalculados tc1 = calculaTotales(factorIva, esdList, descunetoHabilitado, factorDescExtra, false, recibidoEfectivo, recibidoTarjeta, CALCULO_DESC_ALMACEN);

				//System.out.println(tc1.toString(true));
				//System.out.println(tc1.toString(false));

				//TotalesCalculados tc2 = calculaTotales(factorIva, esdList, descunetoHabilitado, factorDescExtra, true, recibidoEfectivo, recibidoTarjeta, CALCULO_DESC_SUCUSALES);

				//System.out.println(tc2.toString(true));
				//System.out.println(tc2.toString(false));

				updatePV(idVenta,tc1,fecha,idSuc,caja);
				adv = (i*100) / ventas.size();
				System.out.print("\t==>UpdateVEntas\t"+adv+"% ("+i+"/"+ventas.size()+")\r");
			}
			System.out.println();
					
			System.out.println("=================================== END IMPORT OK =================================");
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(3);
		}
	}
	
	private static void updatePV(int id,TotalesCalculados tc,Date fecha,int sucId,int caja){
		
		String numTicket = GeneradorNumTicket.getNumTicket(fecha, sucId, caja);
		
		String updatePV =	"UPDATE  ENTRADA_SALIDA SET "+
							"TOTAL="+sdf.format(tc.getTotalFinal())+","+
							"TOTAL_COBRADO="+sdf.format(tc.getTotalFinal())+","+
							"IMPORTE_RECIBIDO="+sdf.format(tc.getTotalFinal())+","+
							"CAMBIO="+sdf.format(tc.getCambio())+","+
							"NUMERO_TICKET='"+numTicket+"',"+
							"SUB_TOTAL_1RA ="+sdf.format(tc.getSubTotal1ra())+","+
							"SUB_TOTAL_OPO ="+sdf.format(tc.getSubTotalOpo())+","+
							"SUB_TOTAL_1RA ="+sdf.format(tc.getSubTotalReg())+","+
							"ELEM_DET  ="+tc.getElemDet()+","+
							"TOT_PRODS ="+tc.getTotProds()+", "+
							"PEDIDO_SUCURSAL=0 "+
							"WHERE ID="+id+";";
		
		//System.out.println("\t"+updatePV);
		try {
			executeDirectUpdate(newDBConnection,updatePV);
		}catch(SQLException se){
			System.err.println(se.toString());
		}
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

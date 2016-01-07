/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools,Templates
 * and open the template in the editor.
 */
package com.pmarlen.migration;

import com.pmarlen.businesslogic.GeneradorNumTicket;
import com.pmarlen.businesslogic.LogicaFinaciera;
import com.pmarlen.businesslogic.TotalesCalculados;
import com.pmarlen.model.Constants;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.eclipse.jdt.internal.compiler.impl.Constant;

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
			long t0 = System.currentTimeMillis();
			long t1, t2, t3, T;
			System.out.println("---------------------- SELECT ALL PRODUCTOS -----------------------");

			List<Object[]> resultAllProductos = executeQuery(newDBConnection, "SELECT CODIGO_BARRAS,COSTO_VENTA FROM PRODUCTO");

			List<Object[]> resultAllAlmacenes = executeQuery(newDBConnection, "SELECT ID,TIPO_ALMACEN,SUCURSAL_ID FROM ALMACEN WHERE SUCURSAL_ID IN (2,3,4,5,6)");

			debug = false;
			int tot = resultAllProductos.size();
			int i = 0;
			int adv = 0;
			System.out.println("EXECUTING UPDATE OR INSERT ALMACEN_PRODUCTO:");
			for (Object[] prodArr : resultAllProductos) {
				String codigoBarras = prodArr[0].toString();
				Double precio = ((Double) prodArr[1]);
				Random r = new Random(System.currentTimeMillis());
				List<Object[]> resultPrecioAP = executeQuery(newDBConnection, "SELECT MAX(PRECIO) FROM ALMACEN_PRODUCTO WHERE PRODUCTO_CODIGO_BARRAS='" + codigoBarras + "'");
				for (Object[] acv : resultPrecioAP) {
					precio = (Double) acv[0];
				}
				for (Object[] almaArr : resultAllAlmacenes) {

					int cantidadNueva = (int)(r.nextDouble()*500);
					Integer almacenId = (Integer) almaArr[0];
					Integer tipoAlmacen = (Integer) almaArr[1];
					if(precio == null){
						precio = r.nextDouble()*200;
					}
					Double precioAct = precio;
					if (tipoAlmacen == Constants.ALMACEN_OPORTUNIDAD) {
						precioAct = precio * 0.6;
					}

					if (executeDirectUpdate(newDBConnection, "UPDATE ALMACEN_PRODUCTO SET CANTIDAD=" + cantidadNueva + ",PRECIO=" + precioAct + " WHERE ALMACEN_ID=" + almacenId + " AND PRODUCTO_CODIGO_BARRAS='" + codigoBarras + "'") == 0) {
						executeDirectUpdate(newDBConnection, "INSERT INTO ALMACEN_PRODUCTO (ALMACEN_ID,PRODUCTO_CODIGO_BARRAS,CANTIDAD,PRECIO) VALUES(" + almacenId + ",'" + codigoBarras + "'," + cantidadNueva + "," + precioAct + ")");
					}
				}

				i++;
				adv = (i * 100) / tot;
				t1 = System.currentTimeMillis();
				System.out.print("ADVANCE: \t" + i + "/\t" + tot + "\t:" + adv + " % [ " + enalapsed(t0, t1) + " ] \r");
			}
			t2 = System.currentTimeMillis();
			System.out.println("ADVANCE: \t" + i + "/\t" + tot + "\t:" + adv + " % [ " + enalapsed(t0, t2) + " ] \r");;

			resultAllProductos = null;
			resultAllAlmacenes = null;
			
			System.out.println("EXECUTING UPDATE ENTRADA_SALIDA FOR CALCULO_TOTALES.");
			t0 = System.currentTimeMillis();
			List<Object[]> resultAllES = executeQuery(newDBConnection,
					"SELECT    ES.ID,"
					+ "ES.CLIENTE_ID,"
					+ "ES.FECHA_CREO,"
					+ "SUM(ESD.CANTIDAD*ESD.PRECIO_VENTA) AS SUBTOTAL,"
					+ "SUM(1) AS NUM_DET, "
					+ "SUM(CANTIDAD) TOT_PRODS, "
					+ "ES.NUMERO_TICKET,"
					+ "ES.FACTOR_IVA,"
					+ "ES.PORCENTAJE_DESCUENTO_EXTRA,"
					+ "ES.PORCENTAJE_DESCUENTO_CALCULADO,"
					+ "ES.IMPORTE_RECIBIDO\n"
					+ "FROM ENTRADA_SALIDA ES,ENTRADA_SALIDA_DETALLE ESD\n"
					+ "WHERE 1=1\n"
					+ "AND ES.ID=ESD.ENTRADA_SALIDA_ID\n"
					+ "GROUP BY ES.ID");
			tot = resultAllES.size();
			i = 0;
			adv = 0;

			boolean autorizaDesc=true;
			boolean redondea=false;
			for (Object[] esArr : resultAllES) {
				int id = 0;
				int clienteId = 0;
				Date fecha = null;
				double subTotal = 0.0;
				int numDet = 0;
				int numProds = 0;
				String numeroTicket = null;
				double factorIVA = 0.0;
				double factorDescExtra = 0.0;
				double factorDescCalculador = 0.0;
				double importeRecibido = 0.0;

				id = ((Integer) esArr[0]).intValue();
				if (esArr[1] != null) {
					clienteId = ((Integer) esArr[1]).intValue();
				} else {
					clienteId = 1;
				}
				fecha = (Timestamp) esArr[2];
				if (esArr[3] != null) {
					subTotal = ((Double) esArr[3]);
				} else {
					subTotal = 0.0;
				}
				numDet = ((BigDecimal) esArr[4]).intValue();
				numProds = ((BigDecimal) esArr[5]).intValue();
				numeroTicket = ((String) esArr[6]);
				if (esArr[7] != null) {
					factorIVA = ((Double) esArr[7]) / 100.0;
				} else {
					factorIVA = 0.0;
				}
				if (esArr[8] != null) {
					factorDescExtra = ((Integer) esArr[8]) / 100.0;
				} else {
					factorDescExtra = 0.0;
				}
				if (esArr[9] != null) {
					factorDescCalculador = ((Integer) esArr[9]) / 100.0;
				} else {
					factorDescCalculador = 0.0;
				}
				if (esArr[10] != null) {
					importeRecibido = (Double) esArr[10];
				} else {
					importeRecibido = 0.0;
				}

				String queryUpdate = "";
				int sucId = 1;
				int caja = 0;

				if (esArr[1] != null) {
					clienteId = ((Integer) esArr[1]).intValue();
				}
				double subTotal1ra = ((Double) esArr[3]).intValue();

				TotalesCalculados ct = LogicaFinaciera.calculaTotales(factorIVA, numProds, subTotal1ra, 0.0, 0.0, autorizaDesc, factorDescExtra, redondea, importeRecibido, 0.0, LogicaFinaciera.CALCULO_DESC_ALMACEN);
				//calculaTotales(double factorIva,int numProd1ra,double st1ra,double stOpo,double stReg,boolean descunetoHabilitado, double factorDescExtra,boolean redondear, double recibidoEfectivo,double recibidoTarjeta,int estrategiaDesc)
				numeroTicket = GeneradorNumTicket.getNumTicket(fecha, 1, 1);
				
				queryUpdate = "UPDATE ENTRADA_SALIDA SET TOTAL=" + Constants.df2Decimal.format(ct.getTotalFinal()) + ",TOTAL_COBRADO=" + Constants.df2Decimal.format(ct.getTotalCobrar()) + 
						",ELEM_DET=" + numDet + ",TOT_PRODS=" + numProds + ",SUB_TOTAL_1RA=" + Constants.df2Decimal.format(subTotal) +
						" WHERE ID=" + id;

				executeDirectUpdate(newDBConnection, queryUpdate);
				i++;
				adv = (i * 100) / tot;
				t1 = System.currentTimeMillis();
				System.out.println("ADVANCE: \t" + i + "/\t" + tot + "\t:" + adv + " % [ " + enalapsed(t0, t1) + " ]: totalFinal="+ct.getTotalFinal()+", total="+ct.getTotal()+", TotalCobrar="+ct.getTotalCobrar());
				//System.out.print("ADVANCE: \t" + i + "/\t" + tot + "\t:" + adv + " % [ " + enalapsed(t0, t1) + " ] \r");
			}
			t2 = System.currentTimeMillis();
			System.out.println("ADVANCE: \t" + i + "/\t" + tot + "\t:" + adv + " % [ " + enalapsed(t0, t2) + " ]");

			System.out.println("=================================== END IMPORT OK =================================");
			resultAllES=null;
			
			
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(3);
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

	private static String extractXMLValue(String label, String xml) {
		String bl = "<" + label + ">";
		String el = "</" + label + ">";
		int bli = xml.indexOf(bl);
		int bti = bli + bl.length();
		int eli = xml.indexOf(el);

		if (bli >= 0 && eli > bti) {
			return xml.substring(bti, eli);
		} else {
			return null;
		}
	}

	private static String extractXMLAtribute(String atribute, String xml) {

		int bli = xml.indexOf(atribute);
		int bti = xml.indexOf("\"", bli);
		int eti = xml.indexOf("\"", bti + 1);

		if (bli >= 0 && eti > bti) {
			return xml.substring(bti + 1, eti);
		} else {
			return null;
		}
	}
}

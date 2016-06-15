package com.pmarlen.migration;

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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * com.pmarlen.migration.HomogenizarInventario_PM1041
 * @author alfredo
 */
public class HomogenizarInventario_PM1041 {

	private static Connection newDBConnection = null;

	private static boolean debug = false;

	private static String usrNewDB = null;
	private static String pwdNewDB = null;
	private static String urlNewDB = null;

	private static int sucursalId = 0;

	private static HashMap<Integer, Integer> almacenByTipo = null;

	private static class AlmsProd {

		String cb;
		Integer cantidad1ra;
		Double precioVenta1ra;
		Integer cantidadOpo;
		Double precioVentaOpo;
		Integer cantidadReg;
		Double precioVentaReg;

		public AlmsProd(String cbx) {
			cb = cbx;
			cantidad1ra = 0;
			precioVenta1ra = 0.0;
			cantidadOpo = 0;
			precioVentaOpo = 0.0;
			cantidadReg = 0;
			precioVentaReg = 0.0;
		}
	}

	public static void main(String[] args) {

		usrNewDB = args[0];
		pwdNewDB = args[1];
		urlNewDB = args[2];
		sucursalId = Integer.parseInt(args[3]);

		System.out.println("---------------------- HomogenizarInventario_PM1041 (Suc:" + sucursalId + ") -----------------------");
		if (debug) {
			System.out.println("-->>> DEBUG MODE");
		}

		connectToNewDB();

		try {
			long t1, t2, t3, T;

			System.out.println("---------------------- HOMOGENIZANDO PRODUCTOS -----------------------");
			List<Object[]> resultAlmacenesSuc = executeQuery(newDBConnection, "SELECT TIPO_ALMACEN,ID FROM ALMACEN WHERE SUCURSAL_ID=" + sucursalId);

			List<Object[]> resultAlmacenesPrincipales = executeQuery(newDBConnection, "SELECT TIPO_ALMACEN,ID FROM ALMACEN WHERE SUCURSAL_ID=1");

			almacenByTipo = new HashMap<Integer, Integer>();
			for (Object[] arr : resultAlmacenesSuc) {
				System.out.println("\t-->> almacenByTipo.put(" + arr[0] + ", " + arr[1] + ")");
				almacenByTipo.put((Integer) arr[0], (Integer) arr[1]);
			}

			LinkedHashMap<String, String> productosActuales = null;

			List<Object[]> resultAllProductos = null;
			resultAllProductos = executeQuery(newDBConnection, "SELECT CODIGO_BARRAS,NOMBRE,PRESENTACION,INDUSTRIA,LINEA,MARCA FROM PRODUCTO");
			productosActuales = new LinkedHashMap<String, String>();
			for (Object[] ap : resultAllProductos) {
				String cb = (String) ap[0];
				String prod2String = Arrays.asList(ap).toString();
				productosActuales.put(cb, prod2String);
			}

			System.out.println("->Productos Actuales: #" + productosActuales.size());

			PreparedStatement psInsertProd = newDBConnection.prepareStatement(
					"INSERT INTO PRODUCTO(CODIGO_BARRAS,INDUSTRIA,LINEA,MARCA,NOMBRE,PRESENTACION,ABREBIATURA,UNIDADES_X_CAJA,CONTENIDO,UNIDAD_MEDIDA,UNIDAD_EMPAQUE,COSTO,COSTO_VENTA)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			PreparedStatement psExistAlmProd = newDBConnection.prepareStatement(
					"SELECT * FROM ALMACEN_PRODUCTO WHERE ALMACEN_ID=? AND PRODUCTO_CODIGO_BARRAS=?", Statement.RETURN_GENERATED_KEYS);

			PreparedStatement psUpdateAlmProd = newDBConnection.prepareStatement(
					"UPDATE ALMACEN_PRODUCTO SET CANTIDAD=?,PRECIO=? "
					+ "WHERE ALMACEN_ID=? AND PRODUCTO_CODIGO_BARRAS=?", Statement.RETURN_GENERATED_KEYS);

			PreparedStatement psInsertAlmProd = newDBConnection.prepareStatement(
					"INSERT INTO ALMACEN_PRODUCTO(CANTIDAD,PRECIO,ALMACEN_ID,PRODUCTO_CODIGO_BARRAS)"
					+ " VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int migrated = 0;

			int carx = 0;
			int affAP = 0;
			
			System.out.println("==>> TERMINADO, AHORA PARA IMPORTAR INVETARIOS:");

			resultAllProductos = executeQuery(newDBConnection, "SELECT CODIGO_BARRAS,NOMBRE,PRESENTACION,INDUSTRIA,LINEA,MARCA FROM PRODUCTO");
			productosActuales = new LinkedHashMap<String, String>();
			for (Object[] ap : resultAllProductos) {
				String cb = (String) ap[0];
				String prod2String = Arrays.asList(ap).toString();
				productosActuales.put(cb, prod2String);
			}
			int numReg = 0;
			int totRI = resultAllProductos.size();
			int almProdUp = 0;
			int almProdIn = 0;
			int almProdPR = 0;
			System.out.println("==>> DESPUES, #resultAllProductos.size=" + resultAllProductos.size());
			for (Object[] ap : resultAllProductos) {
				numReg++;
				String codigoBarrasIntegrado = (String) ap[0];
				AlmsProd ir = new AlmsProd(codigoBarrasIntegrado);

				for (Object[] arrAlm : resultAlmacenesSuc) {
					Integer almacenIdActual = (Integer) arrAlm[1];
					Integer tipoAlmacen = (Integer) arrAlm[0];
					Integer cantidad = 0;
					Double precioVenta = 0.0;
					if (tipoAlmacen == Constants.ALMACEN_PRINCIPAL) {
						cantidad = ir.cantidad1ra;
						precioVenta = ir.precioVenta1ra;
					} else if (tipoAlmacen == Constants.ALMACEN_OPORTUNIDAD) {
						cantidad = ir.cantidadOpo;
						precioVenta = ir.precioVentaOpo;
					} else if (tipoAlmacen == Constants.ALMACEN_REGALIAS) {
						cantidad = ir.cantidadReg;
						precioVenta = ir.precioVentaReg;
					}

					psUpdateAlmProd.clearParameters();
					// CANT,PREC, ALM, CB
					psUpdateAlmProd.setObject(1, cantidad);
					psUpdateAlmProd.setObject(2, precioVenta);
					psUpdateAlmProd.setObject(3, almacenIdActual);
					psUpdateAlmProd.setObject(4, codigoBarrasIntegrado);

					affAP = psUpdateAlmProd.executeUpdate();
					if (affAP == 1) {
						almProdUp++;
					} else {
						psInsertAlmProd.clearParameters();

						psInsertAlmProd.setObject(1, cantidad);
						psInsertAlmProd.setObject(2, precioVenta);
						psInsertAlmProd.setObject(3, almacenIdActual);
						psInsertAlmProd.setObject(4, codigoBarrasIntegrado);

						affAP = psInsertAlmProd.executeUpdate();
						if (affAP == 1) {
							almProdIn++;
						}
					}
				}
				for (Object[] arrAlm : resultAlmacenesPrincipales) {
					Integer almacenIdActual = (Integer) arrAlm[1];
					Integer tipoAlmacen = (Integer) arrAlm[0];
					Integer cantidad = 0;
					Double precioVenta = 0.0;

					psExistAlmProd.clearParameters();
					// CANT,PREC, ALM, CB
					psExistAlmProd.setObject(1, almacenIdActual);
					psExistAlmProd.setObject(2, codigoBarrasIntegrado);
					ResultSet rseia = psExistAlmProd.executeQuery();
					boolean existInAlm = rseia.next();
					rseia.close();

					if (!existInAlm) {
						psInsertAlmProd.clearParameters();

						psInsertAlmProd.setObject(1, cantidad);
						psInsertAlmProd.setObject(2, precioVenta);
						psInsertAlmProd.setObject(3, almacenIdActual);
						psInsertAlmProd.setObject(4, codigoBarrasIntegrado);

						affAP = psInsertAlmProd.executeUpdate();
						if (affAP == 1) {
							almProdPR++;
						}
					}
				}
				//newDBConnection.commit();
				System.out.print("--->> HOMOGENIZANDO INVENTARIO (" + numReg + " / " + totRI + ") [\t" + almProdUp + ", \t" + almProdIn + ",\t" + almProdPR + "] \r");
			}
			System.out.println();
			System.out.println("->OK, Homogenizado el Inventario ");

			System.out.println("=================================== END IMPORT OK =================================");

			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(3);
		}
	}

	private static int insertEntradaSalidaSucursal(Connection conn, EntradaSalida x, Double descuentoAplic, List<EntradaSalidaDetalle> pvdList, int tipoAlmacen) throws SQLException {
		Collections.reverse(pvdList);
		Double subTotal1ra = 0.0;
		Double subTotalOpo = 0.0;
		Double subTotalReg = 0.0;

		if (debug) {
			System.out.println("insertEntradaSalidaSucursal: EntradaSalida: INSERT FECHA:" + x.getFechaCreo() + ", SUCURSAL:" + x.getSucursalId() + ", CAJA:" + x.getCaja() + ", TICKET:" + x.getNumeroTicket());
			for (EntradaSalidaDetalle esd : pvdList) {
				System.out.println("insertEntradaSalidaSucursal:\t INSERT & COMMIT, DISCOUNT=" + esd.getCantidad() + " x " + esd.getProductoCodigoBarras() + "[" + esd.getAlmacenId() + "]");
			}
		}

		int r = -1;
		int tipoMov = x.getTipoMov();
		PreparedStatement ps = null;
		PreparedStatement psESE = null;
		PreparedStatement psESD = null;
		PreparedStatement psMHP = null;

		double importeBruto = 0.0;
		Integer totProds = 0;

		for (EntradaSalidaDetalle esd : pvdList) {
			final double imp = esd.getCantidad() * esd.getPrecioVenta();
			importeBruto += (imp);
			if (tipoAlmacen == Constants.ALMACEN_PRINCIPAL) {
				subTotal1ra += imp;
			} else if (tipoAlmacen == Constants.ALMACEN_OPORTUNIDAD) {
				subTotalOpo += imp;
			} else if (tipoAlmacen == Constants.ALMACEN_REGALIAS) {
				subTotalReg += imp;
			}
			totProds += esd.getCantidad();
		}

		x.setElemDet(pvdList.size());
		x.setTotProds(totProds);

		if (descuentoAplic != null) {
			double descRaz = descuentoAplic / importeBruto;
			int descCalc = (int) (descRaz * 100);
			x.setPorcentajeDescuentoCalculado(descCalc);
			x.setPorcentajeDescuentoExtra(0);
		} else {
			descuentoAplic = 0.0;
		}
		x.setSubTotal1ra(subTotal1ra);
		x.setSubTotalOpo(subTotalOpo);
		x.setSubTotalReg(subTotalReg);

		Double subTotal = (subTotal1ra + subTotalOpo);
		Double total = subTotal - descuentoAplic;
		Double cambio = 0.0;
		if (x.getImporteRecibido() != null) {
			x.setCambio(x.getImporteRecibido() - total);
		} else {
			x.setCambio(0.0);
		}
		x.setTotalCobrado(total);
		x.setTotal(total);

		try {
			//Timestamp now = new Timestamp(System.currentTimeMillis());
			ps = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA(TIPO_MOV,SUCURSAL_ID,ESTADO_ID,FECHA_CREO,USUARIO_EMAIL_CREO,CLIENTE_ID,FORMA_DE_PAGO_ID,"
					+ "METODO_DE_PAGO_ID,FACTOR_IVA,COMENTARIOS,CFD_ID,NUMERO_TICKET,CAJA,IMPORTE_RECIBIDO,APROBACION_VISA_MASTERCARD,PORCENTAJE_DESCUENTO_CALCULADO,"
					+ "PORCENTAJE_DESCUENTO_EXTRA,CONDICIONES_DE_PAGO,NUM_DE_CUENTA,AUTORIZA_DESCUENTO,PEDIDO_SUCURSAL,SUB_TOTAL_1RA,SUB_TOTAL_OPO,"
					+ "SUB_TOTAL_REG,TOTAL,TOTAL_COBRADO,CAMBIO,ELEM_DET,TOT_PRODS) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			int ci = 1;
			ps.setObject(ci++, tipoMov);
			ps.setObject(ci++, x.getSucursalId());
			ps.setObject(ci++, Constants.ESTADO_VENDIDO_SUCURSAL);
			ps.setObject(ci++, x.getFechaCreo());
			ps.setObject(ci++, x.getUsuarioEmailCreo());
			ps.setObject(ci++, x.getClienteId());
			ps.setObject(ci++, x.getFormaDePagoId());
			ps.setObject(ci++, x.getMetodoDePagoId());
			ps.setObject(ci++, x.getFactorIva());
			ps.setObject(ci++, x.getComentarios());
			ps.setObject(ci++, x.getCfdId());
			ps.setObject(ci++, x.getNumeroTicket());
			ps.setObject(ci++, x.getCaja());
			ps.setObject(ci++, x.getImporteRecibido());
			ps.setObject(ci++, x.getAprobacionVisaMastercard());
			ps.setObject(ci++, x.getPorcentajeDescuentoCalculado());
			ps.setObject(ci++, x.getPorcentajeDescuentoExtra());
			ps.setObject(ci++, x.getCondicionesDePago());
			ps.setObject(ci++, x.getNumDeCuenta());
			ps.setObject(ci++, x.getAutorizaDescuento());
			ps.setInt(ci++, 1);
			ps.setObject(ci++, x.getSubTotal1ra());
			ps.setObject(ci++, x.getSubTotalOpo());
			ps.setObject(ci++, x.getSubTotalReg());
			ps.setObject(ci++, x.getTotal());
			ps.setObject(ci++, x.getTotalCobrado());
			ps.setObject(ci++, x.getCambio());
			ps.setObject(ci++, x.getElemDet());
			ps.setObject(ci++, x.getTotProds());

			if (debug) {
				System.out.println("->EntradaSalida before Insert:" + x.getId());
			}
			r = ps.executeUpdate();

			ResultSet rsk = ps.getGeneratedKeys();
			if (rsk != null) {
				while (rsk.next()) {
					x.setId(rsk.getInt(1));
				}
			}
			ps.close();
			if (debug) {
				System.out.println("->EntradaSalida after Insert:" + x.getId());
			}
			psESD = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_DETALLE(ENTRADA_SALIDA_ID,PRODUCTO_CODIGO_BARRAS,ALMACEN_ID,CANTIDAD,PRECIO_VENTA) "
					+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			int rESD = 0;
			for (EntradaSalidaDetalle esd1 : pvdList) {
				int ciESD = 1;

				psESD.clearParameters();
				psESD.clearWarnings();

				psESD.setInt(ciESD++, x.getId());
				if (esd1.getProductoCodigoBarras() == null) {
					throw new IllegalStateException("CODIGO_BARRAS CAN NOT BE NULL !");
				} else {
					psESD.setString(ciESD++, esd1.getProductoCodigoBarras());
				}
				psESD.setInt(ciESD++, esd1.getAlmacenId());
				psESD.setInt(ciESD++, esd1.getCantidad());
				psESD.setDouble(ciESD++, esd1.getPrecioVenta());

				rESD += psESD.executeUpdate();
				if (debug) {
					System.out.println("\tINSERT ENTRADA_SALIDA_DETALLE:" + esd1.getCantidad() + " X " + esd1.getProductoCodigoBarras() + " >> [" + rESD + "]++");
				}
			}
			psESD.close();

			if (debug) {
				System.out.println("x.getEsIdDev():" + x.getEsIdDev());
			}
			if (tipoMov == Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION && x.getEsIdDev() != null) {
				psESD = conn.prepareStatement("UPDATE ENTRADA_SALIDA_DETALLE SET DEV='S' WHERE ENTRADA_SALIDA_ID=? AND CODIGO_BARRAS=?");
				int rESDd = 0;
				for (EntradaSalidaDetalle esd2 : pvdList) {
					int iESDd = 1;

					psESD.clearParameters();
					psESD.clearWarnings();

					psESD.setInt(iESDd++, x.getEsIdDev());
					psESD.setString(iESDd++, esd2.getProductoCodigoBarras());

					rESDd += psESD.executeUpdate();
				}
				psESD.close();
				if (debug) {
					System.out.println("rESDd=" + rESDd);
				}
			}

			psESE = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_ESTADO(ENTRADA_SALIDA_ID,ESTADO_ID,FECHA,USUARIO_EMAIL,COMENTARIOS) "
					+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			int[] estados = new int[]{Constants.ESTADO_CAPTURADO, Constants.ESTADO_VENDIDO_SUCURSAL};
			int ciESE = 1;
			int rESE = -1;
			for (int j = 0; j < 2; j++) {
				psESE.clearParameters();
				ciESE = 1;

				psESE.setInt(ciESE++, x.getId());
				psESE.setInt(ciESE++, estados[j]);
				psESE.setTimestamp(ciESE++, x.getFechaCreo());
				psESE.setString(ciESE++, x.getUsuarioEmailCreo());
				psESE.setString(ciESE++, "--NORMAL--");

				rESE += psESE.executeUpdate();
			}
			psESE.close();

			psESD = conn.prepareStatement("UPDATE ALMACEN_PRODUCTO SET CANTIDAD = CANTIDAD + ? "
					+ " WHERE PRODUCTO_CODIGO_BARRAS=? AND ALMACEN_ID=?");

			psMHP = conn.prepareStatement("INSERT INTO MOVIMIENTO_HISTORICO_PRODUCTO(ALMACEN_ID,FECHA,TIPO_MOVIMIENTO,CANTIDAD,COSTO,PRECIO,USUARIO_EMAIL,PRODUCTO_CODIGO_BARRAS,ENTRADA_SALIDA_ID) "
					+ " VALUES(?,?,?,?,?,?,?,?,?)");

			int cant = 0;
			for (EntradaSalidaDetalle pvd : pvdList) {
				psESD.clearParameters();
				cant = 0;
				if (x.getTipoMov() >= Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA && x.getTipoMov() < Constants.TIPO_MOV_OTRO) {
					cant = -1 * pvd.getCantidad();
					psESD.setInt(1, cant);
				} else if (x.getTipoMov() >= Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA && x.getTipoMov() < Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA) {
					cant = pvd.getCantidad();
					psESD.setInt(1, cant);
				}
				if (debug) {
					System.out.println("->tipomov=" + x.getTipoMov() + ", producto=" + pvd.getProductoCodigoBarras() + ", almacen=" + pvd.getAlmacenId() + ", cant=" + cant);
				}
				psESD.setString(2, pvd.getProductoCodigoBarras());
				psESD.setInt(3, pvd.getAlmacenId());

				psESD.executeUpdate();

				psMHP.clearParameters();
				ci = 1;
				psMHP.setInt(ci++, pvd.getAlmacenId());
				psMHP.setTimestamp(ci++, x.getFechaCreo());
				psMHP.setInt(ci++, x.getTipoMov());
				psMHP.setInt(ci++, pvd.getCantidad());
				psMHP.setObject(ci++, null);
				psMHP.setObject(ci++, pvd.getPrecioVenta());
				psMHP.setString(ci++, x.getUsuarioEmailCreo());
				psMHP.setString(ci++, pvd.getProductoCodigoBarras());
				psMHP.setInt(ci++, x.getId());

				r = psMHP.executeUpdate();
			}
			psESD.close();
			psMHP.close();
			//conn.commit();
		} catch (SQLException ex) {
			System.err.println("En lo mas dificil de sucursales:" + ex);
			//conn.rollback();
			//System.exit(5);
		}

		return r;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools,Templates
 * and open the template in the editor.
 */
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alfredo
 */
public class MigrarInventarioPM901_PM1041 {

	private static Connection newDBConnection = null;

	private static Connection derbyDBConnection = null;

	private static boolean debug = false;

	private static String usrNewDB = null;
	private static String pwdNewDB = null;
	private static String urlNewDB = null;

	private static String usrDerbyDB = null;
	private static String pwdDerbyDB = null;
	private static String urlDerbyDB = null;

	private static int sucursalId = 0;

	public static void main(String[] args) {

		usrNewDB = args[0];
		pwdNewDB = args[1];
		urlNewDB = args[2];
		sucursalId = Integer.parseInt(args[3]);
		usrDerbyDB = args[4];
		pwdDerbyDB = args[5];
		urlDerbyDB = args[6];

		System.out.println("---------------------- MigrarVentasPM901_PM1041 (Suc:" + sucursalId + ") -----------------------");
		if(debug){
			System.out.println("-->>> DEBUG MODE");
		}
		
		connectToDerbyDB();

		connectToNewDBTransactional();

		try {
			long t1, t2, t3, T;

			System.out.println("---------------------- HOMOGENIZANDO PRODUCTOS -----------------------");
			List<Object[]> resultAllAlmacenes = executeQuery(newDBConnection, "SELECT TIPO_ALMACEN,ID,SUCURSAL_ID FROM ALMACEN");

			HashMap<Integer, Integer> almacen = new HashMap<Integer, Integer>();
			for (Object[] arr : resultAllAlmacenes) {
				Integer sucId = (Integer) arr[2];
				if (sucId.intValue() == sucursalId) {
					almacen.put((Integer) arr[0], (Integer) arr[1]);
				}
			}

			List<Object[]> resultPM901Productos = executeQuery(derbyDBConnection,
					"SELECT P.CODIGO_BARRAS,I.NOMBRE AS INDUSTRIA,L.NOMBRE AS LINEA,M.NOMBRE AS MARCA,UPPER(P.NOMBRE) AS P_NOMBRE,UPPER(P.PRESENTACION),P.CODIGO_BARRAS AS ABREBIATURA,P.UNIDADES_POR_CAJA,P.CONTENIDO,P.UNIDAD_MEDIDA,'PZ' AS UNIDAD_EMPAQUE,P.COSTO,P.COSTO_VENTA\n"
					+ "FROM   PRODUCTO P,ALMACEN_PRODUCTO AP,MARCA M,LINEA L,INDUSTRIA I\n"
					+ "WHERE  1=1\n"
					+ "AND    P.MARCA_ID = M.ID\n"
					+ "AND    M.LINEA_ID=L.ID\n"
					+ "AND    M.INDUSTRIA_ID=I.ID\n"
					+ "AND    P.ID = AP.PRODUCTO_ID\n"
					+ "AND    AP.ALMACEN_ID=3\n"
					+ "ORDER BY INDUSTRIA,LINEA,MARCA,P_NOMBRE,PRESENTACION,CODIGO_BARRAS");

			LinkedHashMap<String, String> productos = new LinkedHashMap<String, String>();

			List<Object[]> resultAllProductos = executeQuery(newDBConnection, "SELECT CODIGO_BARRAS,NOMBRE,PRESENTACION,INDUSTRIA,LINEA,MARCA FROM PRODUCTO");
			for (Object[] ap : resultAllProductos) {
				String cb = (String) ap[0];
				String prod2String = Arrays.asList(ap).toString();
				productos.put(cb, prod2String);
				//System.out.println("->Producto[" + cb + "]:" + prod2String);
			}

			System.out.println("->Productos:" + productos.size());

			PreparedStatement psInsertProd = newDBConnection.prepareStatement(
					"INSERT INTO PRODUCTO(CODIGO_BARRAS,INDUSTRIA,LINEA,MARCA,NOMBRE,PRESENTACION,ABREBIATURA,UNIDADES_X_CAJA,CONTENIDO,UNIDAD_MEDIDA,UNIDAD_EMPAQUE,COSTO,COSTO_VENTA)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			PreparedStatement psUpdateAlmProd = newDBConnection.prepareStatement(
					"UPDATE ALMACEN_PRODUCTO SET CANTIDAD=?,PRECIO=? "
							+ "WHERE ALMACEN_ID=? AND PRODUCTO_CODIGO_BARRAS=?", Statement.RETURN_GENERATED_KEYS);

			PreparedStatement psInsertAlmProd = newDBConnection.prepareStatement(
					"INSERT INTO ALMACEN_PRODUCTO(ALMACEN_ID,PRODUCTO_CODIGO_BARRAS,CANTIDAD,PRECIO)"
					+ " VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int migrated = 0;
			int carx=0;
			for (Object[] aaDerby : resultPM901Productos) {
				String oldCB = (String) aaDerby[0];

				if (!productos.containsKey(oldCB)) {
					//System.out.println("IMPORT=>" + Arrays.asList(aaDerby));
					psInsertProd.clearParameters();
					executeUpdate(psInsertProd, aaDerby);
					migrated++;
				}

				for(Object[] arrAlm : resultAllAlmacenes) {
					
					psUpdateAlmProd.clearParameters();
					Object[] arrUpdateAlmProd = new Object[]{0, aaDerby[12],arrAlm[1], aaDerby[0]};
					psUpdateAlmProd.setObject(1, 0);
					psUpdateAlmProd.setObject(2, 0);
					psUpdateAlmProd.setObject(3, arrAlm[1]);
					psUpdateAlmProd.setObject(4, aaDerby[0]);
					
					int a=psUpdateAlmProd.executeUpdate();
					if(a!=0){
						//System.out.println("AP a.1=" +a); 
					} else {
						psInsertAlmProd.clearParameters();
						psInsertAlmProd.setObject(1, arrAlm[1]);
						psInsertAlmProd.setObject(2, aaDerby[0]);
						psInsertAlmProd.setObject(3, 0);
						psInsertAlmProd.setObject(4, aaDerby[12]);
						
						a=psInsertAlmProd.executeUpdate();
						
						//System.out.println("AP a.2=" +a); 
					} 
				}
				System.out.print("--->> REVISANDO PRODUCTOS DERBY: ("+(migrated)+"/"+(carx++)+")\r");
				newDBConnection.commit();
			}
			System.out.println("");
			System.out.println("==>> TERMINADO");
			resultAllProductos = executeQuery(newDBConnection, "SELECT CODIGO_BARRAS,NOMBRE,PRESENTACION,INDUSTRIA,LINEA,MARCA FROM PRODUCTO");
			for (Object[] ap : resultAllProductos) {
				String cb = (String) ap[0];
				String prod2String = Arrays.asList(ap).toString();
				productos.put(cb, prod2String);
				//System.out.println("->AHORA Producto[" + cb + "]:" + prod2String);
			}

			System.out.println("->Productos AHORA :" + productos.size());

			String queryVentasPM901 = 
					"SELECT PV.ID,PVE.ESTADO_ID,PVE.FECHA,PV.FORMA_DE_PAGO_ID,PV.USUARIO_ID,A.TIPO_ALMACEN,PV.FACTORIVA,PV.COMENTARIOS,PV.DESCUENTO_APLICADO,PVD.CANTIDAD,P.CODIGO_BARRAS,PVD.PRECIO_VENTA\n" +
					"FROM   PEDIDO_VENTA PV,PEDIDO_VENTA_DETALLE PVD,PEDIDO_VENTA_ESTADO PVE,PRODUCTO P,ALMACEN A\n" +
					"WHERE  1=1\n" +
					"AND    PV.ID=PVD.PEDIDO_VENTA_ID\n" +
					"AND    PV.ID=PVE.PEDIDO_VENTA_ID\n" +
					"AND    PVD.PRODUCTO_ID=P.ID\n" +
					"AND    PV.ALMACEN_ID=A.ID\n" +
					"ORDER BY PVE.FECHA";
			
			//System.out.println("--->> FINAL SOLP PARA PRUEBAS<<---");
			//System.exit(1);
			
			
			System.out.println("--->> EXECUTING QUERY VENTAS:");
			ResultSet ventasRS = derbyDBConnection.createStatement().executeQuery(queryVentasPM901);
			
			
			LinkedHashMap<String, List<Integer>> productosNoEncotrados = new LinkedHashMap<String, List<Integer>>();

			String line = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			EntradaSalida pv = null;
			List<EntradaSalidaDetalle> pvdList = null;
			EntradaSalidaDetalle esd = null;
			Integer pvId = null;
			Timestamp fecha = null;
			Integer formaDePagoId = null;
			Integer clienteId = null;
			String usuario = null;
			Integer tipoAlmacen = null;
			Double factorIVA = null;
			String comentariosX = null;
			String ticket = null;
			Integer numCaja = null;
			Double recibido = null;
			Double descAplicado = null;
			Integer cantidad;
			String codigoBarras = null;
			Double precio = null;
			
			//debug = true;

			int i = 0;
			int adv = 0;

			boolean insertES = false;
			long t0 = System.currentTimeMillis();
			int contCBNoExiste = 0;
			boolean x = false;
			int tot = 1;
			System.out.println("--->> READ RESULT SET:");
			int numProds=0;
			
			while (ventasRS.next()) {
				insertES = false;

				//PV.ID,PVE.ESTADO_ID,PVE.FECHA,PV.FORMA_DE_PAGO_ID,PV.USUARIO_ID,A.TIPO_ALMACEN,PV.FACTORIVA,PV.COMENTARIOS,PV.DESCUENTO_APLICADO,PVD.CANTIDAD,P.CODIGO_BARRAS,PVD.PRECIO_VENTA
				//    1              2        3             4              5            6              7                     8            9              10              11
				pvId  = ventasRS.getInt(1);  
				fecha = ventasRS.getTimestamp(3); 
				formaDePagoId = ventasRS.getInt(4);
				usuario   = ventasRS.getString(5); 
				clienteId = Constants.ID_CLIENTE_MOSTRADOR;
				tipoAlmacen = ventasRS.getInt(6);
				factorIVA = ventasRS.getDouble(7);
				comentariosX = ventasRS.getString(8);
				
				if (ventasRS.getObject(9) == null) {
					descAplicado = null;
				} else {
					descAplicado = ventasRS.getDouble(9);
				}
				cantidad = ventasRS.getInt(10);
				codigoBarras = ventasRS.getString(11);
				precio = ventasRS.getDouble(12);
				if (comentariosX != null) {
					int idxCb = comentariosX.indexOf("<caja>");
					int idxCe = comentariosX.indexOf("</caja>");
					int idxRb = comentariosX.indexOf("<recibido>");
					int idxRe = comentariosX.indexOf("</recibido>");

					if (idxCb > 0 && idxCe > idxCb) {
						numCaja = Integer.parseInt(comentariosX.substring(idxCb + 6, idxCe));
					} else {
						numCaja = 1;
					}
					if (idxRb > 0 && idxRe > idxRb) {
						recibido = Double.parseDouble(comentariosX.substring(idxRb + 10, idxRe));
					} else {
						recibido = null;
					}
				} else {
					numCaja = 1;
					recibido = null;
				}

				if (!productos.containsKey(codigoBarras)) {
					contCBNoExiste++;
					List<Integer> pedidosQueTienenProdX = null;
					pedidosQueTienenProdX = productosNoEncotrados.get(codigoBarras);
					if (pedidosQueTienenProdX == null) {
						pedidosQueTienenProdX = new ArrayList<Integer>();
						productosNoEncotrados.put(codigoBarras, pedidosQueTienenProdX);
					}
					pedidosQueTienenProdX.add(pvId);
				}

				ticket = GeneradorNumTicket.getNumTicket(fecha, sucursalId, numCaja);

				esd = new EntradaSalidaDetalle();

				esd.setAlmacenId(almacen.get(tipoAlmacen));
				esd.setCantidad(cantidad);
				esd.setProductoCodigoBarras(codigoBarras);
				esd.setPrecioVenta(precio);
				esd.setEntradaSalidaId(pvId);

				if (pv == null || (pv != null && pv.getId().intValue() != pvId.intValue())) {
					pv = new EntradaSalida(pvId);
					pvdList = new ArrayList<EntradaSalidaDetalle>();
					insertES = true;
				}

				numProds+=cantidad;
				
				
				pv.setAutorizaDescuento(descAplicado != null ? 1 : 0);
				pv.setCaja(numCaja);
				pv.setSucursalId(sucursalId);
				pv.setFechaCreo(new Timestamp(fecha.getTime()));
				pv.setClienteId(clienteId);
				pv.setComentarios("VENTA SUCURSAL MIGRADO DE PM9.1(USUARIO:" + usuario + ")");
				pv.setFactorIva(factorIVA);
				pv.setFormaDePagoId(Constants.ID_FDP_1SOLA_E);
				pv.setMetodoDePagoId(formaDePagoId == 1 ? Constants.ID_MDP_EFECTIVO : Constants.ID_MDP_TARJETA);
				pv.setImporteRecibido(recibido);
				pv.setNumeroTicket(ticket);
				pv.setUsuarioEmailCreo("sucursalPM901@perfumeriamarlen.com.mx");
				pv.setTipoMov(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA);
				pv.setEstadoId(Constants.ESTADO_VENDIDO_SUCURSAL);

				pvdList.add(esd);
				
				if (insertES) {
					
					insertEntradaSalidaSucursal(newDBConnection, pv, pvdList);
					insertES = false;
					numProds=0;
				}
				i++;
				t2 = System.currentTimeMillis();
				//System.out.println("ADVANCE: \t" + i + " [ TIME: " + enalapsed(t0, t2) + " ] (ERROR CB:" + contCBNoExiste + ")");
				System.out.print("ADVANCE: \t" + i + " [ TIME: " + enalapsed(t0, t2) + " ] (ERROR CB:" + contCBNoExiste + ") \r");
				
			}

			if (pv != null && pvdList.size() > 0) {
				insertEntradaSalidaSucursal(newDBConnection, pv, pvdList);
			}

			t3 = System.currentTimeMillis();
			System.out.print("ADVANCE: \t" + i + "/\t" + tot + "\t:" + adv + " % [ " + enalapsed(t0, t3) + " ] (ERROR CB:" + contCBNoExiste + ") \r");

			ventasRS.close();
			
			System.out.println("MIGRADO VENTAS:");

			if (!productosNoEncotrados.isEmpty()) {
				System.out.println("NO ENCOTRADOS:" + productosNoEncotrados.size());
				Set<String> pcbSet = productosNoEncotrados.keySet();
				for (String cb : pcbSet) {
					//	System.out.println("\t:["+cb+"]"+productosNoEncotrados.get(cb)+" ?? "+productos.get(cb));
					System.out.print(",'" + cb + "'");
				}
			}

			System.out.println("=================================== END IMPORT OK =================================");

			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(3);
		}
	}

	private static int simula_insertEntradaSalidaSucursal(Connection conn, EntradaSalida x, List<EntradaSalidaDetalle> pvdList) throws SQLException {

		if (debug) {
			System.out.println("insertEntradaSalidaSucursal:TESTING: EntradaSalida: INSERT FECHA:" + x.getFechaCreo() + ", SUCURSAL:" + x.getSucursalId() + ", CAJA:" + x.getCaja() + ", TICKET:" + x.getNumeroTicket());
		}
		return 0;
	}

	private static int insertEntradaSalidaSucursal(Connection conn, EntradaSalida x, List<EntradaSalidaDetalle> pvdList) throws SQLException {

		if (debug) {
			System.out.println("insertEntradaSalidaSucursal: EntradaSalida: INSERT FECHA:" + x.getFechaCreo() + ", SUCURSAL:" + x.getSucursalId() + ", CAJA:" + x.getCaja() + ", TICKET:" + x.getNumeroTicket());
		}
		for (EntradaSalidaDetalle esd : pvdList) {
			if (debug) {
				System.out.println("insertEntradaSalidaSucursal:\t INSERT & COMMIT, DISCOUNT=" + esd.getCantidad() + " x " + esd.getProductoCodigoBarras() + "[" + esd.getAlmacenId() + "]");
			}
		}

		int r = -1;
		int tipoMov = x.getTipoMov();
		PreparedStatement ps = null;
		PreparedStatement psESE = null;
		PreparedStatement psESD = null;
		PreparedStatement psMHP = null;

		try {
			//Timestamp now = new Timestamp(System.currentTimeMillis());
			ps = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA(TIPO_MOV,SUCURSAL_ID,ESTADO_ID,FECHA_CREO,USUARIO_EMAIL_CREO,CLIENTE_ID,FORMA_DE_PAGO_ID,METODO_DE_PAGO_ID,FACTOR_IVA,COMENTARIOS,CFD_ID,NUMERO_TICKET,CAJA,IMPORTE_RECIBIDO,APROBACION_VISA_MASTERCARD,PORCENTAJE_DESCUENTO_CALCULADO,PORCENTAJE_DESCUENTO_EXTRA,CONDICIONES_DE_PAGO,NUM_DE_CUENTA,AUTORIZA_DESCUENTO) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
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
					psESD.setObject(ciESD++, null);
				} else {
					psESD.setString(ciESD++, esd1.getProductoCodigoBarras());
				}
				psESD.setInt(ciESD++, esd1.getAlmacenId());
				psESD.setInt(ciESD++, esd1.getCantidad());
				psESD.setDouble(ciESD++, esd1.getPrecioVenta());

				rESD += psESD.executeUpdate();
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
			conn.commit();
		} catch (SQLException ex) {
			System.err.println("En lo mas dificil de sucursales:" + ex);
			conn.rollback();
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

	private static void connectToDerbyDB() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			System.exit(1);
		}

		try {
			derbyDBConnection = DriverManager.getConnection(urlDerbyDB, usrDerbyDB, pwdDerbyDB);
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

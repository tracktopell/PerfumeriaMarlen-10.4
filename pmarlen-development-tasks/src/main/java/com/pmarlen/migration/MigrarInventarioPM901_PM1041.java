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
import java.util.Collections;
import java.util.Date;
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
	private static String fechaInicio = null;
	private static boolean migrarVentas = true;
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

	private static HashMap<String, AlmsProd> inventarioPM901 = null;

	public static void main(String[] args) {

		usrNewDB = args[0];
		pwdNewDB = args[1];
		urlNewDB = args[2];
		sucursalId = Integer.parseInt(args[3]);
		usrDerbyDB = args[4];
		pwdDerbyDB = args[5];
		urlDerbyDB = args[6];

		fechaInicio = args[7];
		migrarVentas = args[8].equalsIgnoreCase("MV");

		System.out.println("---------------------- MigrarInventarioPM901_PM1041 (Suc:" + sucursalId + ") MIGRAR_VENTAS?(" + migrarVentas + ") -----------------------");
		if (debug) {
			System.out.println("-->>> DEBUG MODE");
		}

		connectToDerbyDB();

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

			List<Object[]> resultPM901Inventario = executeQuery(derbyDBConnection,
					"SELECT  P.CODIGO_BARRAS,A.TIPO_ALMACEN,AP.CANTIDAD_ACTUAL,AP.PRECIO_VENTA\n"
					+ "FROM    PRODUCTO P,ALMACEN_PRODUCTO AP,ALMACEN A\n"
					+ "WHERE   1=1\n"
					+ "AND     AP.PRODUCTO_ID=P.ID\n"
					+ "AND     AP.ALMACEN_ID=A.ID");
			inventarioPM901 = new HashMap<String, AlmsProd>();
			for (Object[] rx1 : resultPM901Inventario) {
				String cb = (String) rx1[0];
				Integer ta = (Integer) rx1[1];
				Integer ca = (Integer) rx1[2];
				Double pv = (Double) rx1[3];

				AlmsProd ir = inventarioPM901.get(cb);
				if (ir == null) {
					ir = new AlmsProd(cb);
					inventarioPM901.put(cb, ir);
				}
				if (ta == Constants.ALMACEN_PRINCIPAL) {
					ir.cantidad1ra = ca;
					ir.precioVenta1ra = pv;
				} else if (ta == Constants.ALMACEN_OPORTUNIDAD) {
					ir.cantidadOpo = ca;
					ir.precioVentaOpo = pv;
				} else if (ta == Constants.ALMACEN_REGALIAS) {
					ir.cantidadReg = ca;
					ir.precioVentaReg = pv;
				}
			}

			List<Object[]> resultPM901Productos = executeQuery(derbyDBConnection,
					"SELECT   P.CODIGO_BARRAS,\n" // 0
					+ "       I.NOMBRE AS INDUSTRIA,\n"
					+ "       L.NOMBRE AS LINEA,\n"
					+ "       M.NOMBRE AS MARCA,\n"
					+ "       UPPER(P.NOMBRE) AS P_NOMBRE,\n"
					+ "       UPPER(P.PRESENTACION),\n" // 5
					+ "       P.CODIGO_BARRAS AS ABREBIATURA,\n"
					+ "       P.UNIDADES_POR_CAJA,\n"
					+ "       P.CONTENIDO,\n"
					+ "       P.UNIDAD_MEDIDA,\n"
					+ "       'PZ' AS UNIDAD_EMPAQUE,\n" // 10
					+ "       P.COSTO,\t"
					+ "       P.COSTO_VENTA\n" // 12
					+ "FROM   PRODUCTO P,MARCA M,LINEA L,INDUSTRIA I\n"
					+ "WHERE  1=1\n"
					+ "AND    P.MARCA_ID = M.ID\n"
					+ "AND    M.LINEA_ID=L.ID\n"
					+ "AND    M.INDUSTRIA_ID=I.ID\n"
					+ "ORDER BY INDUSTRIA,LINEA,MARCA,P_NOMBRE,PRESENTACION,CODIGO_BARRAS");

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
			Object[] aaProdParams = null;
			System.out.println("->EMPATANDO CON DERBY: #" + resultPM901Productos.size());
			for (Object[] aaDerby : resultPM901Productos) {

				String oldCB = (String) aaDerby[0];

				if (!productosActuales.containsKey(oldCB)) {

					aaProdParams = new Object[13];
					for (int j = 0; j < aaProdParams.length; j++) {
						aaProdParams[j] = aaDerby[j];
					}

					psInsertProd.clearParameters();
					try {
						executeUpdate(psInsertProd, aaProdParams);
						migrated++;
					} catch (Exception ex) {
						System.out.println("");
						System.out.println("\t----->>>FAYO IMPORT=>" + Arrays.asList(aaProdParams) + ", params # " + aaProdParams.length + ":" + ex.getMessage());
					}
				}

				System.out.print("--->> REVISANDO PRODUCTOS DERBY: (" + migrated + " / " + (carx++) + ") (" + affAP + ")\r");
				//newDBConnection.commit();
			}
			System.out.println("");
			System.out.println("==>> ANTES, #resultAllProductos.size=" + resultAllProductos.size());

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
				AlmsProd ir = inventarioPM901.get(codigoBarrasIntegrado);
				if (ir == null) {
					ir = new AlmsProd(codigoBarrasIntegrado);
				}

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
				System.out.print("--->> IMPORTANDO INVENTARIO (" + numReg + " / " + totRI + ") [\t" + almProdUp + ", \t" + almProdIn + ",\t" + almProdPR + "] \r");
			}
			System.out.println();
			System.out.println("->OK, Importado el Inventario ");
			LinkedHashMap<String, List<Integer>> productosNoEncotrados = new LinkedHashMap<String, List<Integer>>();

			String line = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

			EntradaSalida pv = null;
			List<EntradaSalidaDetalle> pvdListPV = null;
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

			if (migrarVentas) {
				System.out.println("---------------------- Migrando VENTAS (Suc:" + sucursalId + ") DESDE [" + fechaInicio + "] -----------------------");
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				//Date fehcaInicial = sdf.parse(fechaInicio);

				String queryVentasPM901
						= "SELECT PV.ID,PVE.ESTADO_ID,PVE.FECHA,PV.FORMA_DE_PAGO_ID,PV.USUARIO_ID,A.TIPO_ALMACEN,PV.FACTORIVA,PV.COMENTARIOS,PV.DESCUENTO_APLICADO,PVD.CANTIDAD,P.CODIGO_BARRAS,PVD.PRECIO_VENTA\n"
						+ "FROM   PEDIDO_VENTA PV,PEDIDO_VENTA_DETALLE PVD,PEDIDO_VENTA_ESTADO PVE,PRODUCTO P,ALMACEN A\n"
						+ "WHERE  1=1\n"
						+ "AND    PV.ID=PVD.PEDIDO_VENTA_ID\n"
						+ "AND    PV.ID=PVE.PEDIDO_VENTA_ID\n"
						+ "AND    PVD.PRODUCTO_ID=P.ID\n"
						+ "AND    PV.ALMACEN_ID=A.ID\n"
						+ "AND    PVE.FECHA >= TIMESTAMP('" + fechaInicio + "','00.00.00') \n"
						+ "ORDER BY PVE.FECHA";

				System.out.println("--->> EXECUTING QUERY VENTAS:");
				ResultSet ventasRS = derbyDBConnection.createStatement().executeQuery(queryVentasPM901);

				//debug = true;
				int i = 0;
				int adv = 0;

				boolean insertES = false;
				long t0 = System.currentTimeMillis();
				int contCBNoExiste = 0;
				boolean x = false;
				int tot = 1;
				System.out.println("--->> READ RESULT SET:");
				int numProds = 0;
				boolean firstRec = true;
				int lastpvId = -1;
				int contPV = 0;
				while (ventasRS.next()) {
					insertES = false;

					//PV.ID,PVE.ESTADO_ID,PVE.FECHA,PV.FORMA_DE_PAGO_ID,PV.USUARIO_ID,A.TIPO_ALMACEN,PV.FACTORIVA,PV.COMENTARIOS,PV.DESCUENTO_APLICADO,PVD.CANTIDAD,P.CODIGO_BARRAS,PVD.PRECIO_VENTA
					//    1              2        3                    4             5              6            7             8                     9           10              11               12 
					pvId = ventasRS.getInt(1);

					if (firstRec) {
						pv = new EntradaSalida(pvId);
						pvdListPV = new ArrayList<EntradaSalidaDetalle>();
						lastpvId = pvId;
						firstRec = false;
					}
					if (lastpvId != pvId) {
						if (debug) {
							System.out.println("\t\t===============>> " + lastpvId + " != " + pvId + " !! ");
						}

						if (debug) {
							System.out.println("\tpvdList:");
							for (EntradaSalidaDetalle esdXD : pvdListPV) {
								System.out.println("\t\tesdXD:" + esdXD.getCantidad() + " X " + esdXD.getProductoCodigoBarras() + "[" + esdXD.getAlmacenId() + " ] ");
							}
						}

						insertEntradaSalidaSucursal(newDBConnection, pv, descAplicado, pvdListPV, tipoAlmacen);

						numProds = 0;

						pv = new EntradaSalida(pvId);
						pvdListPV = new ArrayList<EntradaSalidaDetalle>();
						insertES = true;
						contPV++;
					}

					fecha = ventasRS.getTimestamp(3);
					formaDePagoId = ventasRS.getInt(4);
					usuario = ventasRS.getString(5);
					clienteId = Constants.ID_CLIENTE_MOSTRADOR;
					tipoAlmacen = ventasRS.getInt(6);
					factorIVA = Constants.IVA;
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

					if (!productosActuales.containsKey(codigoBarras)) {
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

					esd.setAlmacenId(almacenByTipo.get(tipoAlmacen));
					esd.setCantidad(cantidad);
					esd.setProductoCodigoBarras(codigoBarras);
					esd.setPrecioVenta(precio);
					esd.setEntradaSalidaId(pvId);

					numProds += cantidad;

					if (descAplicado != null) {
						pv.setAutorizaDescuento(1);
					} else {
						pv.setAutorizaDescuento(0);
					}
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

					pvdListPV.add(esd);
					i++;
					lastpvId = pvId;
					t2 = System.currentTimeMillis();
					//System.out.println("ADVANCE: \t" + i + " [ TIME: " + enalapsed(t0, t2) + " ] PV=["+lastpvId+" > "+pvId+"] #"+contPV+"["+pvdListPV.size()+"](ERROR CB:" + contCBNoExiste + ")");
					System.out.print("ADVANCE: \t" + i + " [ TIME: " + enalapsed(t0, t2) + " ] PV=[" + lastpvId + " > " + pvId + "] #" + contPV + "[" + pvdListPV.size() + "](ERROR CB:" + contCBNoExiste + ")\r");
				}

				if (pv != null && pvdListPV.size() > 0) {
					insertEntradaSalidaSucursal(newDBConnection, pv, descAplicado, pvdListPV, tipoAlmacen);
				}

				t3 = System.currentTimeMillis();
				System.out.println();
				System.out.print("END: ADVANCE: \t" + i + "/\t" + tot + "\t:" + adv + " % [ " + enalapsed(t0, t3) + " ] (ERROR CB:" + contCBNoExiste + ") \r");

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
			} else {
				System.out.println("==>> No se importa 2da vez el inventario");
				System.exit(0);
			}
			//-------------------------------------------------

			System.out.println("==>> Otra vez el inventario, #resultAllProductos.size=" + resultAllProductos.size());
			for (Object[] ap : resultAllProductos) {
				numReg++;
				String codigoBarrasIntegrado = (String) ap[0];
				AlmsProd ir = inventarioPM901.get(codigoBarrasIntegrado);
				if (ir == null) {
					ir = new AlmsProd(codigoBarrasIntegrado);
				}

				for (Object[] arrAlm : resultAlmacenesSuc) {
					Integer almacenIdActual = (Integer) arrAlm[1];
					tipoAlmacen = (Integer) arrAlm[0];
					cantidad = 0;
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
					tipoAlmacen = (Integer) arrAlm[0];
					cantidad = 0;
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
				System.out.print("--->> IMPORTANDO INVENTARIO (" + numReg + " / " + totRI + ") [\t" + almProdUp + ", \t" + almProdIn + ",\t" + almProdPR + "] \r");
			}
			System.out.println();
			System.out.println("->OK, Importado el Inventario ");

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

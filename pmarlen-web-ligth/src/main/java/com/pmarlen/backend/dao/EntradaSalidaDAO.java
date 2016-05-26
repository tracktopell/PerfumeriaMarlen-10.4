/**
 * EntradaSalidaDAO
 *
 * Created 2015/03/15 12:43
 *
 * @author tracktopell :: DAO Builder
 * http://www.tracktopell.com.mx
 */

package com.pmarlen.backend.dao;

import com.pmarlen.backend.model.Almacen;
import com.pmarlen.backend.model.Cfd;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.EntradaSalidaEstado;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.Usuario;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaEstadoQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.backend.model.quickviews.PagerInfo;
import com.pmarlen.digifactws.production.client.DigifactClient;
import com.pmarlen.jsf.model.EntradaSalidaDTOPageHelper;
import com.pmarlen.model.Constants;
import com.tracktopell.jdbc.DataSourceFacade;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;	
import java.sql.Timestamp;	
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * Class for EntradaSalidaDAO of Table ENTRADA_SALIDA.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2015/03/15 12:43
 */

public class EntradaSalidaDAO {

	private final static Logger logger = Logger.getLogger(EntradaSalidaDAO.class.getName());

	/**
	*	Datasource for table ENTRADA_SALIDA simple CRUD operations.
	*   x common paramenter.
	*/

	private static EntradaSalidaDAO instance;

	private EntradaSalidaDAO(){	
		logger.debug("created EntradaSalidaDAO.");
	}

	public static EntradaSalidaDAO getInstance() {
		if(instance == null){
			instance = new EntradaSalidaDAO();
		}
		return instance;
	}

	private Connection getConnection(){
		return DataSourceFacade.getStrategy().getConnection();
	}

	private Connection getConnectionCommiteable(){
		return DataSourceFacade.getStrategy().getConnectionCommiteable();
	}

	public void actualizaCantidadPendienteParaOtrosES(ArrayList<EntradaSalidaDetalleQuickView> pvdList) throws DAOException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			HashSet<String> codigos = new HashSet<String>();
			int pedidoVentaId = 0;
			for (EntradaSalidaDetalleQuickView pvd : pvdList) {
				pedidoVentaId = pvd.getId();
				codigos.add(pvd.getProductoCodigoBarras());
			}

			int ncb = 0;
			StringBuffer sbCB = new StringBuffer();
			String codigosBuscar = null;
			for (String cb : codigos) {
				if (ncb == 0) {
					sbCB.append("'");
					sbCB.append(cb);
					sbCB.append("'");
			} else {
					sbCB.append(",'");
					sbCB.append(cb);
					sbCB.append("'");
			}
				ncb++;
			}
			codigosBuscar = sbCB.toString();

			logger.info("->actualizaCantidadPendienteParaOtrosES:pedidoVentaId=" + pedidoVentaId + ", codigosBuscar= ->" + codigosBuscar + "<-");

			ps = conn.prepareStatement(
					"SELECT AP.CANTIDAD,ESD.PRODUCTO_CODIGO_BARRAS,ESD.ALMACEN_ID,SUM(ESD.CANTIDAD) TOT_CANTIDAD \n"
					+ "FROM   ENTRADA_SALIDA_DETALLE ESD,ENTRADA_SALIDA ES,ALMACEN_PRODUCTO AP\n"
					+ "WHERE  1 = 1 \n"
					+ "AND    ESD.PRODUCTO_CODIGO_BARRAS = AP.PRODUCTO_CODIGO_BARRAS\n"
					+ "AND    ESD.ALMACEN_ID = AP.ALMACEN_ID\n"
					+ "AND    ESD.ENTRADA_SALIDA_ID = ES.ID \n"
					+ "AND    ES.ESTADO_ID IN (1,2,4)\n"
					+ "AND    ES.ID <> ? \n"
					+ "AND    ESD.PRODUCTO_CODIGO_BARRAS IN ("
					+ codigosBuscar
					+ ")\n"
					+ "GROUP BY ESD.PRODUCTO_CODIGO_BARRAS,ESD.ALMACEN_ID\n"
					+ "ORDER BY ESD.PRODUCTO_CODIGO_BARRAS,ESD.ALMACEN_ID,ESD.ID");

			
			ps.setInt(1, pedidoVentaId);

			rs = ps.executeQuery();
			while (rs.next()) {
				int axi = rs.getInt("ALMACEN_ID");
				String cbxi = rs.getString("PRODUCTO_CODIGO_BARRAS");
				int tcxi = rs.getInt("TOT_CANTIDAD");
				int apcxi = rs.getInt("CANTIDAD");

				logger.debug("->actualizaCantidadPendienteParaOtrosES:Iteration:\t" + axi + "," + cbxi + ", " + tcxi + ", " + apcxi);

				for (EntradaSalidaDetalleQuickView pvd : pvdList) {
					if (pvd.getProductoCodigoBarras().equals(cbxi) && pvd.getAlmacenId() == axi) {
						pvd.setCanPendteEnOtrosPV(tcxi);
						pvd.setApCantidad(apcxi);
						break;
					}
				}
			}
			logger.info("->actualizaCantidadPendienteParaOtrosES:OK, actualizado");

		} catch (SQLException ex) {
			logger.error("->actualizaCantidadPendienteParaOtrosES:SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("->actualizaCantidadPendienteParaOtrosES::clossing:", ex);
				}
			}
		}
	}

	public EntradaSalidaQuickView findBy(EntradaSalida p) throws DAOException, EntityNotFoundException {
		EntradaSalidaQuickView x = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement psESE = null;
		ResultSet rsESE = null;

		Connection conn = null;
		try {
			conn = getConnection();
			final String query = "SELECT	ES.ID,ES.TIPO_MOV,ES.SUCURSAL_ID,ES.ESTADO_ID,ES.FECHA_CREO,ES.USUARIO_EMAIL_CREO,ES.CLIENTE_ID,ES.FORMA_DE_PAGO_ID,"
					+ "ES.METODO_DE_PAGO_ID,ES.FACTOR_IVA,ES.COMENTARIOS,ES.CFD_ID,ES.NUMERO_TICKET,ES.CAJA,ES.IMPORTE_RECIBIDO,ES.APROBACION_VISA_MASTERCARD,"
					+ "ES.PORCENTAJE_DESCUENTO_CALCULADO,ES.PORCENTAJE_DESCUENTO_EXTRA,ES.CONDICIONES_DE_PAGO,ES.NUM_DE_CUENTA,ES.AUTORIZA_DESCUENTO,"
					+ "ES.SUB_TOTAL_1RA,ES.SUB_TOTAL_OPO,ES.SUB_TOTAL_REG,ES.TOTAL,ES.PEDIDO_SUCURSAL,ES.TOT_PRODS,ES.ES_ID_DEV,ES.ES_ID_TRA_ORI,ES.SUCURSAL_ID_TRA_ORI,SUCURSAL_ID_TRA_DES,\n"
					+ "CFD.ID AS CFD_ID,\n"
					+ "S.NOMBRE AS SUCURSAL_NOMBRE,\n"
					+ "E.DESCRIPCION AS E_DESCRIPCION,\n"
					+ "U.NOMBRE_COMPLETO AS U_NOMBRE_COMPLETO,\n"
					+ "C.RFC AS C_RFC,\n"
					+ "C.RAZON_SOCIAL AS C_RAZON_SOCIAL,\n"
					+ "C.NOMBRE_ESTABLECIMIENTO AS C_NOMBRE_ESTABLECIMIENTO,\n"
					+ "FP.DESCRIPCION AS FP_DESCRIPCION,\n"
					+ "MP.DESCRIPCION AS MP_DESCRIPCION,\n"
					+ "CFD.NUM_CFD AS CFD_NUM_CFD,\n"
					+ "COUNT(1) NUM_ELEMENTOS, \n"
					+ "S1.NOMBRE AS S1_NOMBRE,\n"
					+ "S2.NOMBRE AS S2_NOMBRE,\n"
					+ "S1.CLAVE  AS S1_CLAVE,\n"
					+ "S2.CLAVE  AS S2_CLAVE,\n"
					+ "SUM(ESD.CANTIDAD * ESD.PRECIO_VENTA) AS IMPORTE_BRUTO\n"
					+ "FROM      ENTRADA_SALIDA_DETALLE ESD,\n"
					+ "          ENTRADA_SALIDA         ES\n"
					+ "LEFT JOIN CFD            CFD ON ES.CFD_ID            = CFD.ID\n"
					+ "LEFT JOIN SUCURSAL       S   ON ES.SUCURSAL_ID       = S.ID\n"
					+ "LEFT JOIN SUCURSAL       S1  ON ES.SUCURSAL_ID_TRA_ORI = S1.ID\n"
					+ "LEFT JOIN SUCURSAL       S2  ON ES.SUCURSAL_ID_TRA_DES = S2.ID\n"					
					+ "LEFT JOIN ESTADO         E   ON ES.ESTADO_ID         = E.ID\n"
					+ "LEFT JOIN USUARIO        U   ON ES.USUARIO_EMAIL_CREO= U.EMAIL\n"
					+ "LEFT JOIN CLIENTE        C   ON ES.CLIENTE_ID        = C.ID\n"
					+ "LEFT JOIN FORMA_DE_PAGO  FP  ON ES.FORMA_DE_PAGO_ID  = FP.ID\n"
					+ "LEFT JOIN METODO_DE_PAGO MP  ON ES.METODO_DE_PAGO_ID = MP.ID\n"
					+ "WHERE     1=1\n"
					+ "AND       ES.ID = ?\n"					
					+ "AND       ES.ID        = ESD.ENTRADA_SALIDA_ID\n"
					+ "GROUP BY  ESD.ENTRADA_SALIDA_ID\n"
					+ "ORDER BY  ES.ID DESC";
			ps = conn.prepareStatement(query);

			ps.setInt(1, p.getId());
			logger.info("->findBy("+p.getId()+"):query:"+query);
			rs = ps.executeQuery();
			while (rs.next()) {
				x = new EntradaSalidaQuickView();
				x.setId((Integer) rs.getObject("ID"));
				x.setTipoMov((Integer) rs.getObject("TIPO_MOV"));
				x.setSucursalId((Integer) rs.getObject("SUCURSAL_ID"));
				x.setEstadoId((Integer) rs.getObject("ESTADO_ID"));
				x.setFechaCreo((Timestamp) rs.getObject("FECHA_CREO"));
				x.setUsuarioEmailCreo((String) rs.getObject("USUARIO_EMAIL_CREO"));
				x.setClienteId((Integer) rs.getObject("CLIENTE_ID"));
				x.setFormaDePagoId((Integer) rs.getObject("FORMA_DE_PAGO_ID"));
				x.setMetodoDePagoId((Integer) rs.getObject("METODO_DE_PAGO_ID"));
				x.setFactorIva((Double) rs.getObject("FACTOR_IVA"));
				x.setComentarios((String) rs.getObject("COMENTARIOS"));
				x.setCfdId((Integer) rs.getObject("CFD_ID"));
				x.setNumeroTicket((String) rs.getObject("NUMERO_TICKET"));
				x.setCaja((Integer) rs.getObject("CAJA"));
				x.setImporteRecibido((Double) rs.getObject("IMPORTE_RECIBIDO"));
				x.setAprobacionVisaMastercard((String) rs.getObject("APROBACION_VISA_MASTERCARD"));
				x.setPorcentajeDescuentoCalculado((Integer) rs.getObject("PORCENTAJE_DESCUENTO_CALCULADO"));
				x.setPorcentajeDescuentoExtra((Integer) rs.getObject("PORCENTAJE_DESCUENTO_EXTRA"));
				x.setCondicionesDePago((String) rs.getObject("CONDICIONES_DE_PAGO"));
				x.setNumDeCuenta((String) rs.getObject("NUM_DE_CUENTA"));
				x.setAutorizaDescuento((Integer) rs.getObject("AUTORIZA_DESCUENTO"));
				x.setSubTotal1ra((Double) rs.getObject("SUB_TOTAL_1RA"));
				x.setSubTotalOpo((Double) rs.getObject("SUB_TOTAL_OPO"));
				x.setSubTotalReg((Double) rs.getObject("SUB_TOTAL_REG"));
				x.setTotal     ((Double) rs.getObject("TOTAL"));
				x.setPedioSucursal((Integer) rs.getObject("PEDIDO_SUCURSAL"));
				x.setTotProds((Integer) rs.getObject("TOT_PRODS"));
				x.setEsIdDev((Integer) rs.getObject("ES_ID_DEV"));
				x.setEsIdTraOri((Integer) rs.getObject("ES_ID_TRA_ORI"));
				x.setSucursalIdTraOri((Integer) rs.getObject("SUCURSAL_ID_TRA_ORI"));
				x.setSucursalIdTraDes((Integer) rs.getObject("SUCURSAL_ID_TRA_DES"));
				x.setTraspasoSucOriNombre((String) rs.getObject("S1_NOMBRE"));
				x.setTraspasoSucOriClave ((String) rs.getObject("S1_CLAVE"));
				x.setTraspasoSucDesNombre((String) rs.getObject("S2_NOMBRE"));
				x.setTraspasoSucDesClave ((String) rs.getObject("S2_CLAVE"));

				x.setSucursalNombre((String) rs.getObject("SUCURSAL_NOMBRE"));
				x.setEstadoDescripcion((String) rs.getObject("E_DESCRIPCION"));
				x.setUsuarioNombreCompleto((String) rs.getObject("U_NOMBRE_COMPLETO"));
				x.setClienteRFC((String) rs.getObject("C_RFC"));
				x.setClienteRazonSocial((String) rs.getObject("C_RAZON_SOCIAL"));
				x.setClienteNombreEstablecimiento((String) rs.getObject("C_NOMBRE_ESTABLECIMIENTO"));
				x.setMetodoDePagoDescripcion((String) rs.getObject("MP_DESCRIPCION"));
				x.setFormaDePagoDescripcion((String) rs.getObject("FP_DESCRIPCION"));
				x.setCdfNumCFD((String) rs.getObject("CFD_NUM_CFD"));

				x.setNumElementos(rs.getInt("NUM_ELEMENTOS"));
				x.setImporteBruto(rs.getDouble("IMPORTE_BRUTO"));

				x.setImporteNoGravado(x.getImporteBruto() / (1.0 + x.getFactorIva()));
				x.setImporteIVA(x.getImporteBruto() - x.getImporteNoGravado());
				if(x.getImporteBruto() !=null && x.getPorcentajeDescuentoCalculado()!=null && x.getPorcentajeDescuentoExtra()!=null){
					x.setImporteDescuento((x.getImporteBruto() * (x.getPorcentajeDescuentoCalculado()+x.getPorcentajeDescuentoExtra()))/100.0);
					x.setImporteTotal(x.getImporteBruto() - x.getImporteDescuento());
				} else {
					x.setImporteDescuento(0.0);
					x.setImporteTotal(x.getImporteBruto() );
				}
			}
			if(x != null) {
				ArrayList<EntradaSalidaEstadoQuickView> pveList = new ArrayList<EntradaSalidaEstadoQuickView>();
				/*
				 SELECT ESE.ID,ESE.ENTRADA_SALIDA_ID,ESE.ESTADO_ID,E.DESCRIPCION,ESE.FECHA,ESE.USUARIO_EMAIL,U.NOMBRE_COMPLETO,ESE.COMENTARIOS 
				 FROM   ENTRADA_SALIDA_ESTADO ESE,ESTADO E,USUARIO U
				 WHERE  1=1
				 AND    ESE.ESTADO_ID=E.ID
				 AND    ESE.USUARIO_EMAIL = U.EMAIL
				 AND    ESE.ENTRADA_SALIDA_ID=2548
				 ORDER BY ESE.FECHA;
				 */

				psESE = conn.prepareStatement(
						"SELECT ESE.ID,ESE.ENTRADA_SALIDA_ID,ESE.ESTADO_ID,E.DESCRIPCION,ESE.FECHA,ESE.USUARIO_EMAIL,U.NOMBRE_COMPLETO,ESE.COMENTARIOS \n"
						+ "FROM   ENTRADA_SALIDA_ESTADO ESE,ESTADO E,USUARIO U\n"
						+ "WHERE  1=1\n"
						+ "AND    ESE.ESTADO_ID=E.ID\n"
						+ "AND    ESE.USUARIO_EMAIL = U.EMAIL\n"
						+ "AND    ESE.ENTRADA_SALIDA_ID=?\n"
						+ "ORDER BY ESE.FECHA DESC");
				psESE.setInt(1, p.getId());

				rsESE = psESE.executeQuery();
				EntradaSalidaEstadoQuickView z = null;
				while (rsESE.next()) {
					z = new EntradaSalidaEstadoQuickView();

					z.setId(rsESE.getInt("ID"));
					z.setEntradaSalidaId(rsESE.getInt("ENTRADA_SALIDA_ID"));
					z.setEstadoId(rsESE.getInt("ESTADO_ID"));
					z.setEstadoDescripcion(rsESE.getString("DESCRIPCION"));
					z.setFecha(rsESE.getTimestamp("FECHA"));
					z.setUsuarioEmail(rsESE.getString("USUARIO_EMAIL"));
					z.setUsuarioNombreCompleto(rsESE.getString("NOMBRE_COMPLETO"));
					z.setComentarios(rsESE.getString("COMENTARIOS"));

					pveList.add(z);
				}
				x.setPveList(pveList);

				if(z != null){
					x.setEstadoActualFecha(z.getFecha());
					x.setEstadoActualUsuarioEmail(z.getUsuarioEmail());
					x.setEstadoActualUsuarioNombreCompleto(z.getUsuarioNombreCompleto());
				}
			}
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();

					rsESE.close();
					psESE.close();

					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return x;
	}

	@Deprecated
	public ArrayList<EntradaSalidaDetalleQuickView> findAllESDByEntradaSalida(int pedidoVentaId) throws DAOException {
		ArrayList<EntradaSalidaDetalleQuickView> r = new ArrayList<EntradaSalidaDetalleQuickView>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			ps = conn.prepareStatement(
					"SELECT   P.CODIGO_BARRAS,P.NOMBRE,P.PRESENTACION,P.INDUSTRIA,P.MARCA,P.LINEA,P.CONTENIDO,P.UNIDAD_MEDIDA,P.UNIDADES_X_CAJA,P.UNIDAD_EMPAQUE,AP.PRECIO,AP.CANTIDAD,AP.UBICACION,ESD.ID AS ESD_ID,A.ID AS ALMACEN_ID,A.TIPO_ALMACEN,ESD.CANTIDAD AS CANTIDAD_ESD,ESD.PRECIO_VENTA,ESD.DEV\n"
					+ "FROM   ENTRADA_SALIDA ES,\n"
					+ "       ENTRADA_SALIDA_DETALLE ESD,\n"
					+ "       PRODUCTO P,\n"
					+ "       ALMACEN_PRODUCTO AP,\n"
					+ "       ALMACEN A\n"
					+ "WHERE  1=1\n"
					+ "AND    ES.ID                      = ?\n"
					+ "AND    ES.ID                      = ESD.ENTRADA_SALIDA_ID\n"
					+ "AND    ESD.PRODUCTO_CODIGO_BARRAS = P.CODIGO_BARRAS\n"
					+ "AND    ESD.PRODUCTO_CODIGO_BARRAS = AP.PRODUCTO_CODIGO_BARRAS\n"
					+ "AND    AP.ALMACEN_ID=A.ID\n"
					+ "AND    A.ID=ESD.ALMACEN_ID\n"
					+ "ORDER BY ESD.ID");

			ps.setInt(1, pedidoVentaId);

			rs = ps.executeQuery();
			long ct=System.currentTimeMillis();
			while (rs.next()) {
				EntradaSalidaDetalleQuickView x = new EntradaSalidaDetalleQuickView();
				x.setEntradaSalidaId(pedidoVentaId);
				x.setProductoCodigoBarras(rs.getString("CODIGO_BARRAS"));				
				x.setProductoNombre(rs.getString("NOMBRE"));
				x.setProductoPresentacion(rs.getString("PRESENTACION"));
				x.setProductoIndustria(rs.getString("INDUSTRIA"));
				x.setProductoMarca(rs.getString("MARCA"));
				x.setProductoLinea(rs.getString("LINEA"));
				x.setProductoContenido(rs.getString("CONTENIDO"));
				x.setProductoUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
				x.setPrecioVenta(rs.getDouble("PRECIO_VENTA"));
				x.setDev(rs.getString("DEV"));				
				x.setProductoUnidadEmpaque(rs.getString("UNIDAD_EMPAQUE"));
				x.setProductoUnidadesPorCaja(rs.getString("UNIDADES_X_CAJA"));
				x.setCantidad(rs.getInt("CANTIDAD_ESD"));
				x.setId(rs.getInt("ESD_ID"));
				x.setAlmacenId(rs.getInt("ALMACEN_ID"));
				x.setApTipoAlmacen(rs.getInt("TIPO_ALMACEN"));
				x.setApCantidad(rs.getInt("CANTIDAD"));
				x.setApUbicacion(rs.getString("UBICACION"));
				
				x.setRowId(ct++);
				
				logger.info("\t==>>"+x.getCantidad()+" X ["+x.getProductoCodigoBarras()+"] @ "+x.getAlmacenId()+" ("+x.getProductoUnidadEmpaque()+":"+x.getProductoContenido()+" "+x.getProductoUnidadMedida()+")["+x.getProductoUnidadesPorCaja()+"]");
				r.add(x);
			}

		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return r;		
	}
    
	public ArrayList<EntradaSalidaQuickView> findAllActivePendidos() throws DAOException {
		return findAllActive(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,1);
	}
	
	public ArrayList<EntradaSalidaQuickView> findAllActiveDevs() throws DAOException {
		return findAllActive(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION,1);
	}
	
	public ArrayList<EntradaSalidaQuickView> findAllActiveCompras() throws DAOException {
		return findAllActive(Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA,1);
	}
	
	private ArrayList<EntradaSalidaQuickView> findAllActive(int tipoMov,int sucursalId) throws DAOException {
		return findAllActive(tipoMov,sucursalId,true);
	}
	
	@Deprecated
	private ArrayList<EntradaSalidaQuickView> findAllActive(int tipoMov,int sucursalId,boolean active) throws DAOException {
		logger.info("->DEPRECATED: findAllActive(tipoMov="+tipoMov+",sucursalId="+sucursalId+",active="+active+")");
		ArrayList<EntradaSalidaQuickView> r = new ArrayList<EntradaSalidaQuickView>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			String q="SELECT	ES.ID ES_ID,ES.TIPO_MOV,ES.SUCURSAL_ID,ES.ESTADO_ID,ES.FECHA_CREO,ES.USUARIO_EMAIL_CREO,ES.CLIENTE_ID,ES.FORMA_DE_PAGO_ID,ES.METODO_DE_PAGO_ID,ES.FACTOR_IVA,ES.COMENTARIOS,ES.CFD_ID,ES.NUMERO_TICKET,ES.CAJA,ES.IMPORTE_RECIBIDO,ES.APROBACION_VISA_MASTERCARD,ES.PORCENTAJE_DESCUENTO_CALCULADO,ES.PORCENTAJE_DESCUENTO_EXTRA,ES.CONDICIONES_DE_PAGO,ES.NUM_DE_CUENTA,ES.AUTORIZA_DESCUENTO,"
					+ "ES.SUB_TOTAL_1RA,ES.SUB_TOTAL_OPO,ES.SUB_TOTAL_REG,ES.TOTAL,ES.PEDIDO_SUCURSAL,TOT_PRODS,ES.ES_ID_DEV,\n"
					+ "CFD.ID AS CFD_ID,\n"
					+ "S.NOMBRE AS SUCURSAL_NOMBRE,\n"
					+ "E.DESCRIPCION AS E_DESCRIPCION,\n"
					+ "U.NOMBRE_COMPLETO AS U_NOMBRE_COMPLETO,\n"
					+ "C.RFC AS C_RFC,\n"
					+ "C.RAZON_SOCIAL AS C_RAZON_SOCIAL,\n"
					+ "C.NOMBRE_ESTABLECIMIENTO AS C_NOMBRE_ESTABLECIMIENTO,\n"
					+ "FP.DESCRIPCION AS FP_DESCRIPCION,\n"
					+ "MP.DESCRIPCION AS MP_DESCRIPCION,\n"
					+ "CFD.NUM_CFD AS CFD_NUM_CFD,\n"
					+ "COUNT(1) NUM_ELEMENTOS, \n"
					+ "SUM(ESD.CANTIDAD * ESD.PRECIO_VENTA) AS IMPORTE_BRUTO, \n"
					+ "ESE.FECHA AS FECHA_ACTUALIZO, \n"		
					+ "ESE.USUARIO_EMAIL AS USUARIO_ACTUALIZO\n"
					+ "FROM      ENTRADA_SALIDA_ESTADO  ESE,\n"
					+ "          ENTRADA_SALIDA_DETALLE ESD,\n"		
					+ "          ENTRADA_SALIDA         ES\n"
					+ "LEFT JOIN CFD            CFD ON ES.CFD_ID            = CFD.ID\n"
					+ "LEFT JOIN SUCURSAL       S   ON ES.SUCURSAL_ID       = S.ID\n"
					+ "LEFT JOIN ESTADO         E   ON ES.ESTADO_ID         = E.ID\n"
					+ "LEFT JOIN USUARIO        U   ON ES.USUARIO_EMAIL_CREO= U.EMAIL\n"
					+ "LEFT JOIN CLIENTE        C   ON ES.CLIENTE_ID        = C.ID\n"
					+ "LEFT JOIN FORMA_DE_PAGO  FP  ON ES.FORMA_DE_PAGO_ID  = FP.ID\n"
					+ "LEFT JOIN METODO_DE_PAGO MP  ON ES.METODO_DE_PAGO_ID = MP.ID\n"
					+ "WHERE     1=1\n"
					+ (active ? "AND       ES.ESTADO_ID IN (1,2,4)\n":
							    "AND       ES.ESTADO_ID >  4\n" )
					+ "AND       ES.ID        = ESD.ENTRADA_SALIDA_ID\n"
					+ "AND       ES.ID        = ESE.ENTRADA_SALIDA_ID\n"
					+ "AND       ES.ESTADO_ID = ESE.ESTADO_ID\n"		
					+ "AND       ES.TIPO_MOV  = ?\n"
					+ "AND       ES.SUCURSAL_ID= ?\n"
					+ "GROUP BY  ESD.ENTRADA_SALIDA_ID\n"
					+ "ORDER BY  ES.ID DESC";
			
			logger.info("->QUERY :"+q);
			
			ps = conn.prepareStatement(q);
			ps.setInt(1, tipoMov);
			ps.setInt(2, sucursalId);
			
			rs = ps.executeQuery();
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();
			logger.info("->rs.last(): rs.getRow()="+size);
			
			while (rs.next()) {
				EntradaSalidaQuickView x = new EntradaSalidaQuickView();				
				x.setId((Integer) rs.getObject("ES_ID"));
				x.setTipoMov((Integer) rs.getObject("TIPO_MOV"));
				x.setSucursalId((Integer) rs.getObject("SUCURSAL_ID"));
				x.setEstadoId((Integer) rs.getObject("ESTADO_ID"));
				x.setFechaCreo((Timestamp) rs.getObject("FECHA_CREO"));
				x.setUsuarioEmailCreo((String) rs.getObject("USUARIO_EMAIL_CREO"));
				x.setClienteId((Integer) rs.getObject("CLIENTE_ID"));
				x.setFormaDePagoId((Integer) rs.getObject("FORMA_DE_PAGO_ID"));
				x.setMetodoDePagoId((Integer) rs.getObject("METODO_DE_PAGO_ID"));
				x.setFactorIva((Double) rs.getObject("FACTOR_IVA"));
				x.setComentarios((String) rs.getObject("COMENTARIOS"));
				x.setCfdId((Integer) rs.getObject("CFD_ID"));
				x.setNumeroTicket((String) rs.getObject("NUMERO_TICKET"));
				x.setCaja((Integer) rs.getObject("CAJA"));
				x.setImporteRecibido((Double) rs.getObject("IMPORTE_RECIBIDO"));
				x.setAprobacionVisaMastercard((String) rs.getObject("APROBACION_VISA_MASTERCARD"));
				x.setPorcentajeDescuentoCalculado((Integer) rs.getObject("PORCENTAJE_DESCUENTO_CALCULADO"));
				x.setPorcentajeDescuentoExtra((Integer) rs.getObject("PORCENTAJE_DESCUENTO_EXTRA"));
				x.setCondicionesDePago((String) rs.getObject("CONDICIONES_DE_PAGO"));
				x.setNumDeCuenta((String) rs.getObject("NUM_DE_CUENTA"));
				x.setAutorizaDescuento((Integer) rs.getObject("AUTORIZA_DESCUENTO"));
				x.setSubTotal1ra((Double) rs.getObject("SUB_TOTAL_1RA"));
				x.setSubTotalOpo((Double) rs.getObject("SUB_TOTAL_OPO"));
				x.setSubTotalReg((Double) rs.getObject("SUB_TOTAL_REG"));
				x.setTotal     ((Double) rs.getObject("TOTAL"));
				x.setPedioSucursal((Integer) rs.getObject("PEDIDO_SUCURSAL"));
				x.setPedioSucursal((Integer) rs.getObject("TOT_PRODS"));
				x.setEsIdDev((Integer) rs.getObject("ES_ID_DEV"));
				
				x.setSucursalNombre((String) rs.getObject("SUCURSAL_NOMBRE"));
				x.setEstadoDescripcion((String) rs.getObject("E_DESCRIPCION"));
				x.setUsuarioNombreCompleto((String) rs.getObject("U_NOMBRE_COMPLETO"));
				x.setClienteRFC((String) rs.getObject("C_RFC"));
				x.setClienteRazonSocial((String) rs.getObject("C_RAZON_SOCIAL"));
				x.setClienteNombreEstablecimiento((String) rs.getObject("C_NOMBRE_ESTABLECIMIENTO"));
				x.setMetodoDePagoDescripcion((String) rs.getObject("MP_DESCRIPCION"));
				x.setFormaDePagoDescripcion((String) rs.getObject("FP_DESCRIPCION"));
				x.setCdfNumCFD((String) rs.getObject("CFD_NUM_CFD"));

				x.setNumElementos(rs.getInt("NUM_ELEMENTOS"));
				x.setImporteBruto(rs.getDouble("IMPORTE_BRUTO"));

				x.setImporteNoGravado(x.getImporteBruto() / (1.0 + x.getFactorIva()));	
				//logger.info("========================");
				//logger.info("PEDIDO ID:        :\t"+x.getId());
				//logger.info("IMPORTE BRUTO     :\t"+x.getImporteBruto());
				//logger.info("IMPORTE NO GRABADO:\t"+x.getImporteNoGravado());
				if(x.getImporteBruto() !=null && x.getPorcentajeDescuentoCalculado()!=null && x.getPorcentajeDescuentoExtra()!=null){
					x.setImporteDescuento((x.getImporteNoGravado()* (x.getPorcentajeDescuentoCalculado()+x.getPorcentajeDescuentoExtra()))/100.0);															
				} else {
					x.setImporteDescuento(0.0);					
				}
				x.setImporteIVA((x.getImporteNoGravado() - x.getImporteDescuento())*Constants.IVA);
				x.setImporteTotal(x.getImporteNoGravado()- x.getImporteDescuento() + x.getImporteIVA());
				//logger.info("% DESCUENTOS      :\t"+x.getPorcentajeDescuentoCalculado()+"% + "+x.getPorcentajeDescuentoExtra());
				//logger.info("I.V.A.            :\t"+x.getImporteIVA());
				//logger.info("    T O T A L     :\t"+x.getImporteTotal());
				
				x.setEstadoActualFecha((Timestamp) rs.getObject("FECHA_ACTUALIZO"));
				x.setEstadoActualUsuarioEmail((String) rs.getObject("USUARIO_ACTUALIZO"));

				r.add(x);
			}
			logger.info("------------------------------");
			logger.info("->FOUND :"+r.size()+" RECORDS.");
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("findAll:clossing:", ex);
				}
			}
		}
		return r;
	}
	
	public ArrayList<EntradaSalidaQuickView> findAllActiveByPage(int tipoMov,int sucursalId,boolean active,PagerInfo pagerInfo) throws DAOException {
		return findAllActiveByPage(tipoMov,sucursalId,active,pagerInfo,null,null);
	}	
	
	public ArrayList<EntradaSalidaQuickView> findAllActiveByPage(int tipoMov,int sucursalId,boolean active,PagerInfo pagerInfo,Timestamp fechaInicial,Timestamp fechaFinal) throws DAOException {
		return findAllActiveByPage(new EntradaSalidaDTOPageHelper(tipoMov, sucursalId, active, pagerInfo, fechaInicial, fechaFinal, null));
	}
	
	public ArrayList<EntradaSalidaQuickView> findAllActiveByPage(EntradaSalidaDTOPageHelper esdtoH) throws DAOException {
		logger.info("->findAllActiveByPage(tipoMov="+esdtoH.getTipoMov()+",sucursalId="+esdtoH.getSucursalId()+",active="+esdtoH.isActive()+",pagerInfo.filters="+esdtoH.getPagerInfo().getFilters()+",fechaInicial="+esdtoH.getFechaInicial()+",fechaFinal="+esdtoH.getFechaFinal()+",ImporteTotal="+esdtoH.getImporteTotal()+")");
		
		ArrayList<EntradaSalidaQuickView> r = new ArrayList<EntradaSalidaQuickView>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			String fq0 = 
					",ES.ID ES_ID,ES.TIPO_MOV,ES.SUCURSAL_ID,ES.ESTADO_ID,ES.FECHA_CREO,ES.USUARIO_EMAIL_CREO,ES.CLIENTE_ID,ES.FORMA_DE_PAGO_ID,ES.METODO_DE_PAGO_ID,ES.FACTOR_IVA,ES.COMENTARIOS,ES.CFD_ID,ES.NUMERO_TICKET,ES.CAJA,ES.IMPORTE_RECIBIDO,ES.APROBACION_VISA_MASTERCARD,ES.PORCENTAJE_DESCUENTO_CALCULADO,ES.PORCENTAJE_DESCUENTO_EXTRA,ES.CONDICIONES_DE_PAGO,ES.NUM_DE_CUENTA,ES.AUTORIZA_DESCUENTO,"
					+ "ES.SUB_TOTAL_1RA,ES.SUB_TOTAL_OPO,ES.SUB_TOTAL_REG,ES.TOTAL,ES.PEDIDO_SUCURSAL,TOT_PRODS,ES.ES_ID_DEV,ES.ES_ID_TRA_ORI,ES.SUCURSAL_ID_TRA_ORI,SUCURSAL_ID_TRA_DES,\n"
					+ "CFD.ID AS CFD_ID,\n"
					+ "S.NOMBRE AS SUCURSAL_NOMBRE,\n"
					+ "E.DESCRIPCION AS E_DESCRIPCION,\n"
					+ "U.NOMBRE_COMPLETO AS U_NOMBRE_COMPLETO,\n"
					+ "C.RFC AS C_RFC,\n"
					+ "C.RAZON_SOCIAL AS C_RAZON_SOCIAL,\n"
					+ "C.NOMBRE_ESTABLECIMIENTO AS C_NOMBRE_ESTABLECIMIENTO,\n"
					+ "FP.DESCRIPCION AS FP_DESCRIPCION,\n"
					+ "MP.DESCRIPCION AS MP_DESCRIPCION,\n"
					+ "CFD.NUM_CFD AS CFD_NUM_CFD,\n"
					+ "COUNT(1) NUM_ELEMENTOS, \n"
					+ "SUM(ESD.CANTIDAD * ESD.PRECIO_VENTA) AS IMPORTE_BRUTO, \n"
					+ "ESE.FECHA AS FECHA_ACTUALIZO, \n"		
					+ "ESE.USUARIO_EMAIL AS USUARIO_ACTUALIZO,\n"
					+ "S1.NOMBRE AS S1_NOMBRE,\n"
					+ "S2.NOMBRE AS S2_NOMBRE,\n"
					+ "S1.CLAVE  AS S1_CLAVE,\n"
					+ "S2.CLAVE  AS S2_CLAVE\n";

			String fwq0 =
					" FROM       ENTRADA_SALIDA_ESTADO  ESE,\n"
					+ "          ENTRADA_SALIDA_DETALLE ESD,\n"		
					+ "          ENTRADA_SALIDA         ES\n"
					+ "LEFT JOIN CFD            CFD ON ES.CFD_ID      = CFD.ID\n"
					+ "LEFT JOIN SUCURSAL       S   ON ES.SUCURSAL_ID       = S.ID\n"
					+ "LEFT JOIN SUCURSAL       S1  ON ES.SUCURSAL_ID_TRA_ORI = S1.ID\n"
					+ "LEFT JOIN SUCURSAL       S2  ON ES.SUCURSAL_ID_TRA_DES = S2.ID\n"
					+ "LEFT JOIN ESTADO         E   ON ES.ESTADO_ID         = E.ID\n"
					+ "LEFT JOIN USUARIO        U   ON ES.USUARIO_EMAIL_CREO= U.EMAIL\n"
					+ "LEFT JOIN CLIENTE        C   ON ES.CLIENTE_ID        = C.ID\n"
					+ "LEFT JOIN FORMA_DE_PAGO  FP  ON ES.FORMA_DE_PAGO_ID  = FP.ID\n"
					+ "LEFT JOIN METODO_DE_PAGO MP  ON ES.METODO_DE_PAGO_ID = MP.ID\n"
					+ " WHERE    1=1\n"
					+ (esdtoH.isActive()   ?"AND       ES.ESTADO_ID IN (1,2,4,512,1024)\n":
											"AND       ES.ESTADO_ID >  4\n" )
					+ "AND       ES.ID        = ESD.ENTRADA_SALIDA_ID\n"
					+ "AND       ES.ID        = ESE.ENTRADA_SALIDA_ID\n"
					+ "AND       ES.ESTADO_ID = ESE.ESTADO_ID\n"		
					+ "AND       ES.TIPO_MOV  = ?\n"
					+ "AND       ES.SUCURSAL_ID= ?\n"
					+ (esdtoH.getFechaInicial()!=null && esdtoH.getFechaFinal()!=null? "AND  ES.FECHA_CREO >= ? AND ES.FECHA_CREO <= ?\n":"");
			
			String q0="SELECT	ES.TOTAL "
					+ fq0
					+ fwq0;
			
			String qT0="SELECT	ES.TOTAL "
					+ fq0
					+ fwq0;
			
			//+ "ORDER BY  ES.ID DESC";
			Map<String, Object> filters = esdtoH.getPagerInfo().getFilters();
			if(filters != null) {
				for(String k:filters.keySet()){
					q0 += "AND     ES."+k.toUpperCase()+" = ? \n";
					qT0+= "AND     ES."+k.toUpperCase()+" = ? \n";
				}
			}
			q0  += " GROUP BY  ESD.ENTRADA_SALIDA_ID\n";
			qT0 += " GROUP BY  ESD.ENTRADA_SALIDA_ID\n";
			
			if(esdtoH.getPagerInfo().getSortField() != null) {
				q0  += " ORDER BY "+esdtoH.getPagerInfo().getSortField()+" "+(esdtoH.getPagerInfo().getSortOrder()<0?"DESC":"ASC")+" \n";
				qT0 += " ORDER BY "+esdtoH.getPagerInfo().getSortField()+" "+(esdtoH.getPagerInfo().getSortOrder()<0?"DESC":"ASC")+" \n";
			}
			//------------------------------------------------------------------			
			logger.info("\t->QUERY COUNT:");			
			ps = conn.prepareStatement(q0);
			
			int vs=1;
			ps.setInt(vs++, esdtoH.getTipoMov());
			ps.setInt(vs++, esdtoH.getSucursalId());			
			
			if(esdtoH.getFechaInicial()!=null && esdtoH.getFechaFinal()!=null){
				ps.setTimestamp(vs++, esdtoH.getFechaInicial());
				ps.setTimestamp(vs++, esdtoH.getFechaFinal());
			}else{
				
			}
			
			Map<String, Object> filtersValues = esdtoH.getPagerInfo().getFilters();
			if(filters != null) {
				
				for(String k:filtersValues.keySet()){
					ps.setObject(vs++, filtersValues.get(k));
				}
			}
			rs = ps.executeQuery();
			rs.last();
			int size = rs.getRow();
			rs.beforeFirst();
			logger.info("->rs.last(): rs.getRow()=TotalRowCount="+size);
			esdtoH.getPagerInfo().setTotalRowCount(size);
			rs.close();
			ps.close();
			ps = null;
			
			//------------------------------------------------------------------
			if(esdtoH.getImporteTotal() != null) {
				logger.debug("\t->QUERY TOTAL Q:"+qT0);
				ps = conn.prepareStatement(qT0);

				vs=1;
				ps.setInt(vs++, esdtoH.getTipoMov());
				ps.setInt(vs++, esdtoH.getSucursalId());			

				if(esdtoH.getFechaInicial()!=null && esdtoH.getFechaFinal()!=null){
					ps.setTimestamp(vs++, esdtoH.getFechaInicial());
					ps.setTimestamp(vs++, esdtoH.getFechaFinal());
				}else{

				}

				filtersValues = esdtoH.getPagerInfo().getFilters();
				if(filters != null) {	
					for(String k:filtersValues.keySet()){
						ps.setObject(vs++, filtersValues.get(k));
					}
				}
				rs = ps.executeQuery();

				double importeBruto    = 0.0;
				double factorIva       = 0.0;
				double importeNOGra    = 0.0;
				double importeDesc     = 0.0;
				double importeTOTAL    = 0.0;
				double importeIVA      = 0.0;
				int    porcDescCalc = 0;
				int    porcDescExtra   = 0;
				logger.debug("\t\t->IMBR\tFACTOR_IVA\tPDESCCALC\tPOCDESCEX\tIMP_TOTAL");
				Double sumTot = 0.0;
				while(rs.next()){

					importeBruto    = rs.getDouble("IMPORTE_BRUTO");
					factorIva       = rs.getDouble("FACTOR_IVA");
					porcDescCalc    = rs.getInt("PORCENTAJE_DESCUENTO_CALCULADO");
					porcDescExtra   = rs.getInt("PORCENTAJE_DESCUENTO_EXTRA");


					importeNOGra    = importeBruto / (1.0 + factorIva);	
					//logger.info("========================");
					//logger.info("PEDIDO ID:        :\t"+x.getId());
					//logger.info("IMPORTE BRUTO     :\t"+x.getImporteBruto());
					//logger.info("IMPORTE NO GRABADO:\t"+x.getImporteNoGravado());
					int dtot = porcDescCalc + porcDescExtra;

					importeDesc = (importeNOGra * dtot)/100.0;

					importeIVA   = ((importeNOGra - importeDesc)*Constants.IVA);
					importeTOTAL = (importeNOGra- importeDesc + importeIVA);

					sumTot       += importeTOTAL;


					logger.debug("\t\t->"+importeBruto+"\t"+factorIva+"\t"+porcDescCalc+"\t"+porcDescExtra+"\t"+importeTOTAL);				
				}
				logger.debug("T O T A L->"+sumTot);
				esdtoH.setImporteTotal(sumTot);


				rs.close();
				ps.close();
				ps = null;
			}
			//------------------------------------------------------------------
			String qR0 = q0 +   "LIMIT "+esdtoH.getPagerInfo().getFirst()+","+esdtoH.getPagerInfo().getPageSize();
			//logger.info("\t->QUERY BY PAGE (first="+pagerInfo.getFirst()+",pageSize="+pagerInfo.getPageSize()+"):");
			logger.info("\t->QUERY BY PAGE (first="+esdtoH.getPagerInfo().getFirst()+",pageSize="+esdtoH.getPagerInfo().getPageSize()+"):"+q0);
			
			ps = conn.prepareStatement(qR0);
			vs=1;
			ps.setInt(vs++, esdtoH.getTipoMov());
			ps.setInt(vs++, esdtoH.getSucursalId());
			Map<String, Object> filtersValuesT = esdtoH.getPagerInfo().getFilters();
			if(esdtoH.getFechaInicial()!=null && esdtoH.getFechaFinal()!=null){
				ps.setTimestamp(vs++, esdtoH.getFechaInicial());
				ps.setTimestamp(vs++, esdtoH.getFechaFinal());
			}else{
				
			}
			if(filters != null) {
				for(String k:filtersValuesT.keySet()){
					ps.setObject(vs++, filtersValuesT.get(k));
				}
			}
			
			rs = ps.executeQuery();
			
			while (rs.next()) {				
				EntradaSalidaQuickView x = new EntradaSalidaQuickView();				
				x.setId((Integer) rs.getObject("ES_ID"));
				x.setTipoMov((Integer) rs.getObject("TIPO_MOV"));
				x.setSucursalId((Integer) rs.getObject("SUCURSAL_ID"));
				x.setEstadoId((Integer) rs.getObject("ESTADO_ID"));
				x.setFechaCreo((Timestamp) rs.getObject("FECHA_CREO"));
				x.setUsuarioEmailCreo((String) rs.getObject("USUARIO_EMAIL_CREO"));
				x.setClienteId((Integer) rs.getObject("CLIENTE_ID"));
				x.setFormaDePagoId((Integer) rs.getObject("FORMA_DE_PAGO_ID"));
				x.setMetodoDePagoId((Integer) rs.getObject("METODO_DE_PAGO_ID"));
				x.setFactorIva((Double) rs.getObject("FACTOR_IVA"));
				x.setComentarios((String) rs.getObject("COMENTARIOS"));
				x.setCfdId((Integer) rs.getObject("CFD_ID"));
				x.setNumeroTicket((String) rs.getObject("NUMERO_TICKET"));
				x.setCaja((Integer) rs.getObject("CAJA"));
				x.setImporteRecibido((Double) rs.getObject("IMPORTE_RECIBIDO"));
				x.setAprobacionVisaMastercard((String) rs.getObject("APROBACION_VISA_MASTERCARD"));
				x.setPorcentajeDescuentoCalculado((Integer) rs.getObject("PORCENTAJE_DESCUENTO_CALCULADO"));
				x.setPorcentajeDescuentoExtra((Integer) rs.getObject("PORCENTAJE_DESCUENTO_EXTRA"));
				x.setCondicionesDePago((String) rs.getObject("CONDICIONES_DE_PAGO"));
				x.setNumDeCuenta((String) rs.getObject("NUM_DE_CUENTA"));
				x.setAutorizaDescuento((Integer) rs.getObject("AUTORIZA_DESCUENTO"));
				x.setSubTotal1ra((Double) rs.getObject("SUB_TOTAL_1RA"));
				x.setSubTotalOpo((Double) rs.getObject("SUB_TOTAL_OPO"));
				x.setSubTotalReg((Double) rs.getObject("SUB_TOTAL_REG"));
				x.setPedioSucursal((Integer) rs.getObject("PEDIDO_SUCURSAL"));
				x.setTotProds((Integer) rs.getObject("TOT_PRODS"));
				x.setTotal     ((Double) rs.getObject("TOTAL"));
				x.setEsIdDev((Integer) rs.getObject("ES_ID_DEV"));
				x.setEsIdTraOri((Integer) rs.getObject("ES_ID_TRA_ORI"));
				x.setSucursalIdTraOri((Integer) rs.getObject("SUCURSAL_ID_TRA_ORI"));
				x.setSucursalIdTraDes((Integer) rs.getObject("SUCURSAL_ID_TRA_DES"));
				
				x.setTraspasoSucOriNombre((String) rs.getObject("S1_NOMBRE"));
				x.setTraspasoSucOriClave ((String) rs.getObject("S1_CLAVE"));
				x.setTraspasoSucDesNombre((String) rs.getObject("S2_NOMBRE"));
				x.setTraspasoSucDesClave ((String) rs.getObject("S2_CLAVE"));
				
				x.setSucursalNombre((String) rs.getObject("SUCURSAL_NOMBRE"));
				x.setEstadoDescripcion((String) rs.getObject("E_DESCRIPCION"));
				x.setUsuarioNombreCompleto((String) rs.getObject("U_NOMBRE_COMPLETO"));
				x.setClienteRFC((String) rs.getObject("C_RFC"));
				x.setClienteRazonSocial((String) rs.getObject("C_RAZON_SOCIAL"));
				x.setClienteNombreEstablecimiento((String) rs.getObject("C_NOMBRE_ESTABLECIMIENTO"));
				x.setMetodoDePagoDescripcion((String) rs.getObject("MP_DESCRIPCION"));
				x.setFormaDePagoDescripcion((String) rs.getObject("FP_DESCRIPCION"));
				x.setCdfNumCFD((String) rs.getObject("CFD_NUM_CFD"));

				x.setNumElementos(rs.getInt("NUM_ELEMENTOS"));
				x.setImporteBruto(rs.getDouble("IMPORTE_BRUTO"));

				x.setImporteNoGravado(x.getImporteBruto() / (1.0 + x.getFactorIva()));	
				//logger.info("========================");
				//logger.info("PEDIDO ID:        :\t"+x.getId());
				//logger.info("IMPORTE BRUTO     :\t"+x.getImporteBruto());
				//logger.info("IMPORTE NO GRABADO:\t"+x.getImporteNoGravado());
				if(x.getImporteBruto() !=null ){
					int dtot = 0;
					dtot += x.getPorcentajeDescuentoCalculado()!=null? x.getPorcentajeDescuentoCalculado(): 0;
					dtot += x.getPorcentajeDescuentoExtra()    !=null? x.getPorcentajeDescuentoExtra()    : 0;
					x.setImporteDescuento((x.getImporteNoGravado()*dtot)/100.0);
				} else {
					x.setImporteDescuento(0.0);					
				}
				x.setImporteIVA((x.getImporteNoGravado() - x.getImporteDescuento())*Constants.IVA);
				x.setImporteTotal(x.getImporteNoGravado()- x.getImporteDescuento() + x.getImporteIVA());
				//logger.info("% DESCUENTOS      :\t"+x.getPorcentajeDescuentoCalculado()+"% + "+x.getPorcentajeDescuentoExtra());
				//logger.info("I.V.A.            :\t"+x.getImporteIVA());
				//logger.info("    T O T A L     :\t"+x.getImporteTotal());
				
				x.setEstadoActualFecha((Timestamp) rs.getObject("FECHA_ACTUALIZO"));
				x.setEstadoActualUsuarioEmail((String) rs.getObject("USUARIO_ACTUALIZO"));

				r.add(x);
			}
			logger.info("\t------------------------------");
			logger.info("\t->READ :"+r.size()+" RECORDS BY PAGE.");
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("\tfindAll:clossing:", ex);
				}
			}
		}
		return r;
	}
	
	public ArrayList<EntradaSalidaQuickView> findAllHistoricoPedidos() throws DAOException {
		return findAllHistorico(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,1);
	}
	
	public ArrayList<EntradaSalidaQuickView> findAllHistoricoDevs() throws DAOException {
		return findAllHistorico(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION,1);
	}
	
	public ArrayList<EntradaSalidaQuickView> findAllHistoricoCompras() throws DAOException {
		return findAllHistorico(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION,1);
	}
	
	private ArrayList<EntradaSalidaQuickView> findAllHistorico(int tipoMov,int sucursalId) throws DAOException {
		return findAllActive(tipoMov,sucursalId,false);
	}
	
	public Double findSaldoEstimadoSucursalCajaVentas(int sucursalId,int caja,int corteCajaId)throws DAOException{
		Double saldoEstimado=null;
		logger.debug("findSaldoEstimadoSucursalCajaVentas(sucursalId="+sucursalId+",caja="+caja+",corteCajaId="+corteCajaId+")");
		ArrayList<EntradaSalidaQuickView> r = new ArrayList<EntradaSalidaQuickView>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			String q =  "SELECT ES.ID,\n" +
						"       ES.TIPO_MOV,\n" +
						"       ESD.ID,\n" +
						"       CC.ID,\n" +
						"       CC.TIPO_EVENTO,\n" +
						"       CC.SUCURSAL_ID,\n" +
						"       CC.CAJA,\n" +
						"       CC.SALDO_INICIAL,\n" +
						"       ESD.CANTIDAD,\n" +
						"       ESD.ALMACEN_ID,\n" +
						"       ESD.PRODUCTO_CODIGO_BARRAS,\n" +
						"       ESD.PRECIO_VENTA,\n" +
						"       SUM(ESD.CANTIDAD * ESD.PRECIO_VENTA) AS VTA\n" +
						"FROM   ENTRADA_SALIDA ES,\n" +
						"       ENTRADA_SALIDA_DETALLE ESD,\n" +
						"       CORTE_CAJA     CC\n" +
						"WHERE  1=1\n" +
						"AND    ES.ID           = ESD.ENTRADA_SALIDA_ID\n" +
						"AND    ES.TIPO_MOV     = 30\n" +
						"AND    CC.TIPO_EVENTO  = 2\n" +
						"AND    CC.SUCURSAL_ID  = ?\n" +
						"AND    CC.CAJA         = ?\n" +
						"AND    CC.ID           = ?\n" +
						"AND    ES.FECHA_CREO   >= CC.FECHA\n" +
						"ORDER BY ES.ID,ESD.ID";
			logger.debug("findSaldoEstimadoSucursalCajaVentas: query:"+q);
			int ci=1;
			ps = conn.prepareStatement(q);
			
			ps.setInt(ci++, sucursalId);
			ps.setInt(ci++, caja);
			ps.setInt(ci++, corteCajaId);
			
			rs = ps.executeQuery();					
			
			while (rs.next()) {
				saldoEstimado = rs.getDouble("VTA");
			}
			
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("findAll:clossing:", ex);
				}
			}
		}

		return saldoEstimado;
	}

	public Double findSaldoEstimadoSucursalCajaDevol(int sucursalId,int caja,int corteCajaId)throws DAOException{
		Double saldoEstimado=null;
		logger.debug("findSaldoEstimadoSucursalCajaDevol(sucursalId="+sucursalId+",caja="+caja+",corteCajaId="+corteCajaId+")");
		ArrayList<EntradaSalidaQuickView> r = new ArrayList<EntradaSalidaQuickView>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			String q =  "SELECT ES.ID,\n" +
						"       ES.TIPO_MOV,\n" +
						"       ESD.ID,\n" +
						"       CC.ID,\n" +
						"       CC.TIPO_EVENTO,\n" +
						"       CC.SUCURSAL_ID,\n" +
						"       CC.CAJA,\n" +
						"       CC.SALDO_INICIAL,\n" +
						"       ESD.CANTIDAD,\n" +
						"       ESD.ALMACEN_ID,\n" +
						"       ESD.PRODUCTO_CODIGO_BARRAS,\n" +
						"       ESD.PRECIO_VENTA,\n" +
						"       SUM(-1 * ESD.CANTIDAD * ESD.PRECIO_VENTA) AS DEV\n" +
						"FROM   ENTRADA_SALIDA ES,\n" +
						"       ENTRADA_SALIDA_DETALLE ESD,\n" +
						"       CORTE_CAJA     CC\n" +
						"WHERE  1=1\n" +
						"AND    ES.ID           = ESD.ENTRADA_SALIDA_ID\n" +
						"AND    ES.TIPO_MOV     = 21\n" +
						"AND    CC.TIPO_EVENTO  = 2\n" +
						"AND    CC.SUCURSAL_ID  = ?\n" +
						"AND    CC.CAJA         = ?\n" +
						"AND    CC.ID           = ?\n" +
						"AND    ES.FECHA_CREO   >= CC.FECHA\n" +
						"ORDER BY ES.ID,ESD.ID;";
			logger.debug("findSaldoEstimadoSucursalCajaDevol: query:"+q);
			int ci=1;
			ps = conn.prepareStatement(q);
			
			ps.setInt(ci++, sucursalId);
			ps.setInt(ci++, caja);
			ps.setInt(ci++, corteCajaId);
			
			rs = ps.executeQuery();					
			
			while (rs.next()) {
				saldoEstimado = rs.getDouble("DEV");
			}
			
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InQuery:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("findAll:clossing:", ex);
				}
			}
		}

		return saldoEstimado;
	}

	public int getIdForTicket(String ticket) throws DAOException{
		int id=-1;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("SELECT ID,SUCURSAL_ID,NUMERO_TICKET FROM ENTRADA_SALIDA WHERE NUMERO_TICKET=?");
			ps.setString(1, ticket);
			rs = ps.executeQuery();
			while(rs.next()){
				id=rs.getInt("Id");
			}
			rs.close();
		} catch (SQLException ex) {			
			throw new DAOException("getIdForTicket:" + ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("getIdForTicket:clossing:", ex);
				}
			}
		}

		return id;
	}
	
	public int getIdForTicket(Connection conn,String ticket) throws DAOException{
		int id=-1;
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT ID,SUCURSAL_ID,NUMERO_TICKET FROM ENTRADA_SALIDA WHERE NUMERO_TICKET=?");
			ps.setString(1, ticket);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				id=rs.getInt("Id");
			}
			rs.close();
		} catch (SQLException ex) {			
			throw new DAOException("getIdForTicket:" + ex.getMessage());
		}
		return id;
	}
	
	public int insertEntradaSalidaSucursal(Connection conn, EntradaSalida x, List<? extends EntradaSalidaDetalle> pvdList) throws DAOException {
		
		logger.debug("insertEntradaSalidaSucursal:TESTING: EntradaSalida: INSERT FECHA:"+x.getFechaCreo()+", SUCURSAL:"+x.getSucursalId()+", CAJA:"+x.getCaja()+", TICKET:"+x.getNumeroTicket());
		for(EntradaSalidaDetalle esd: pvdList){
			logger.debug("insertEntradaSalidaSucursal:\tTESTING: INSERT & COMMIT, DISCOUNT="+esd.getCantidad()+" x "+esd.getProductoCodigoBarras()+"["+esd.getAlmacenId()+"]");
		}
		
		int r=-1;
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
			logger.info("->EntradaSalida before Insert:"+x.getId());
			r = ps.executeUpdate();					
			
			ResultSet rsk = ps.getGeneratedKeys();
			if (rsk != null) {
				while (rsk.next()) {
					x.setId(rsk.getInt(1));
				}
			}
			ps.close();
			logger.info("->EntradaSalida after Insert:"+x.getId());
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
			
			logger.debug("x.getEsIdDev():"+x.getEsIdDev());
			if(tipoMov == Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION && x.getEsIdDev()!=null){
				psESD = conn.prepareStatement("UPDATE ENTRADA_SALIDA_DETALLE SET DEV='S' WHERE ENTRADA_SALIDA_ID=? AND CODIGO_BARRAS=?");
				int rESDd=0;
				for (EntradaSalidaDetalle esd2 : pvdList) {
					int iESDd = 1;

					psESD.clearParameters();
					psESD.clearWarnings();

					psESD.setInt	(iESDd++, x.getEsIdDev());
					psESD.setString	(iESDd++, esd2.getProductoCodigoBarras());
					
					rESDd += psESD.executeUpdate();
				}
				psESD.close();
				logger.debug("rESDd="+rESDd);
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
			
			int cant=0;
			for (EntradaSalidaDetalle pvd : pvdList) {
				psESD.clearParameters();
				cant=0;
				if(x.getTipoMov() >= Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA && x.getTipoMov() < Constants.TIPO_MOV_OTRO){
					cant = -1 * pvd.getCantidad();
					psESD.setInt(1, cant);
				} else if(x.getTipoMov() >= Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA &&  x.getTipoMov() < Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA){				
					cant = pvd.getCantidad();
					psESD.setInt(1, cant);
				}
				logger.info("->tipomov="+x.getTipoMov()+", producto="+pvd.getProductoCodigoBarras()+", almacen="+pvd.getAlmacenId()+", cant="+cant);
				psESD.setString(2, pvd.getProductoCodigoBarras());
				psESD.setInt(3, pvd.getAlmacenId());

				psESD.executeUpdate();

				psMHP.clearParameters();
				ci=1;
				psMHP.setInt(ci++, pvd.getAlmacenId());
				psMHP.setTimestamp(ci++, x.getFechaCreo());
				psMHP.setInt(ci++, x.getTipoMov());
				psMHP.setInt(ci++, pvd.getCantidad());
				psMHP.setObject(ci++, null);
				psMHP.setObject(ci++, pvd.getPrecioVenta());
				psMHP.setString(ci++, x.getUsuarioEmailCreo());
				psMHP.setString(ci++, pvd.getProductoCodigoBarras());
				psMHP.setInt   (ci++, x.getId());

				r = psMHP.executeUpdate();
			}
			psESD.close();
			psMHP.close();
		} catch (SQLException ex) {
			logger.error("En lo mas dificil de sucursales:", ex);
			throw new DAOException("InUpdate:" + ex.getMessage());
		}
		
		return r;
	}
    
	public int insertPedidoVenta(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList) throws DAOException {
		return insert(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,x,pvdList);
	}
	
	public int insertComra(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList) throws DAOException {
		return insert(Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA,x,pvdList);	
	}
	
	public int insertDevolucionVenta(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList) throws DAOException {
		return insert(Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION,x,pvdList);
	}
	
	public int insertTraspaso(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList) throws DAOException {
		return insert(Constants.TIPO_MOV_SALIDA_TRASPASO,x,pvdList);
	}
	
	private int insert(int tipoMov,EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList) throws DAOException {
		
		Connection conn = null;
		int rs = 0;
		try {
			conn = getConnectionCommiteable();
			rs = insert(tipoMov, x, pvdList, conn);		
			conn.commit();
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			try {
				conn.rollback();
			} catch (SQLException exR) {
				logger.error("RollBack failed:", ex);
			}
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if (conn != null) {
				try {					
					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return rs;
	}
	
	private int insert(int tipoMov,EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList,Connection conn) throws SQLException {	
		PreparedStatement ps = null;
		PreparedStatement psESE = null;
		PreparedStatement psESD = null;
		int r = -1;
		//Connection conn = null;
		try {
//			conn = getConnectionCommiteable();

			Timestamp now = new Timestamp(System.currentTimeMillis());
			ps = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA(TIPO_MOV,SUCURSAL_ID,ESTADO_ID,FECHA_CREO,USUARIO_EMAIL_CREO,CLIENTE_ID,FORMA_DE_PAGO_ID,METODO_DE_PAGO_ID,FACTOR_IVA,COMENTARIOS,CFD_ID,NUMERO_TICKET,CAJA,IMPORTE_RECIBIDO,APROBACION_VISA_MASTERCARD,PORCENTAJE_DESCUENTO_CALCULADO,PORCENTAJE_DESCUENTO_EXTRA,CONDICIONES_DE_PAGO,NUM_DE_CUENTA,AUTORIZA_DESCUENTO,ES_ID_TRA_ORI,SUCURSAL_ID_TRA_ORI,SUCURSAL_ID_TRA_DES) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			int ci = 1;
			ps.setObject(ci++, tipoMov);
			ps.setObject(ci++, x.getSucursalId());
			ps.setObject(ci++, Constants.ESTADO_SINCRONIZADO);
			ps.setObject(ci++, now);
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
			ps.setObject(ci++, x.getEsIdTraOri());
			ps.setObject(ci++, x.getSucursalIdTraOri());
			ps.setObject(ci++, x.getSucursalIdTraDes());
			logger.info("->EntradaSalida before Insert:"+x.getId());
			r = ps.executeUpdate();					
			
			ResultSet rsk = ps.getGeneratedKeys();
			if (rsk != null) {
				while (rsk.next()) {
					x.setId(rsk.getInt(1));
				}
			}
			logger.info("->EntradaSalida after Insert:"+x.getId());
			psESD = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_DETALLE(ENTRADA_SALIDA_ID,PRODUCTO_CODIGO_BARRAS,ALMACEN_ID,CANTIDAD,PRECIO_VENTA) "
					+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			int rESD = 0;
			for (EntradaSalidaDetalle pvd : pvdList) {
				int ciESD = 1;

				psESD.clearParameters();
				psESD.clearWarnings();

				psESD.setInt(ciESD++, x.getId());
				if (pvd.getProductoCodigoBarras() == null) {
					psESD.setObject(ciESD++, null);
				} else {
					psESD.setString(ciESD++, pvd.getProductoCodigoBarras());
				}
				psESD.setInt(ciESD++, pvd.getAlmacenId());
				psESD.setInt(ciESD++, pvd.getCantidad());
				psESD.setDouble(ciESD++, pvd.getPrecioVenta());

				rESD += psESD.executeUpdate();
			}

			psESE = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_ESTADO(ENTRADA_SALIDA_ID,ESTADO_ID,FECHA,USUARIO_EMAIL,COMENTARIOS) "
					+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			int[] estados = new int[]{Constants.ESTADO_CAPTURADO, Constants.ESTADO_SINCRONIZADO};
			int ciESE = 1;

			int rESE = -1;
			for (int j = 0; j < 2; j++) {
				psESE.clearParameters();
				ciESE = 1;

				psESE.setInt(ciESE++, x.getId());
				psESE.setInt(ciESE++, estados[j]);
				psESE.setTimestamp(ciESE++, now);
				psESE.setString(ciESE++, x.getUsuarioEmailCreo());
				psESE.setString(ciESE++, "--NORMAL--");

				rESE += psESE.executeUpdate();
			}
//			conn.commit();
			logger.info("->EntradaSalida after Commit");
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw ex;
			
		} finally {
			if (ps != null) {
				try {
					ps.close();
//					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());					
				}
			}
		}
		return r;
	}

	public int update(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList, Usuario u) throws DAOException {
		PreparedStatement ps = null;
		PreparedStatement psESE = null;
		PreparedStatement psESD = null;

		int r = -1;
		Connection conn = null;
		try {
			conn = getConnectionCommiteable();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			
			ps = conn.prepareStatement("UPDATE ENTRADA_SALIDA SET TIPO_MOV=?,SUCURSAL_ID=?,ESTADO_ID=?,CLIENTE_ID=?,FORMA_DE_PAGO_ID=?,METODO_DE_PAGO_ID=?,FACTOR_IVA=?,COMENTARIOS=?,CFD_ID=?,NUMERO_TICKET=?,CAJA=?,IMPORTE_RECIBIDO=?,APROBACION_VISA_MASTERCARD=?,PORCENTAJE_DESCUENTO_CALCULADO=?,PORCENTAJE_DESCUENTO_EXTRA=?,CONDICIONES_DE_PAGO=?,NUM_DE_CUENTA=?,AUTORIZA_DESCUENTO=? "
					+ " WHERE ID=?");
			
			int ci = 1;
			ps.setObject(ci++, x.getTipoMov());
			ps.setObject(ci++, x.getSucursalId());
			ps.setObject(ci++, x.getEstadoId());
			//ps.setObject(ci++, x.getFechaCreo());
			//ps.setObject(ci++, x.getUsuarioEmailCreo());
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
			
			ps.setObject(ci++, x.getId());
			
			r = ps.executeUpdate();						

			int rESD = conn.createStatement().executeUpdate("DELETE FROM ENTRADA_SALIDA_DETALLE WHERE ENTRADA_SALIDA_ID=" + x.getId());
			logger.info("=>DELETE FROM ENTRADA_SALIDA_DETALLE WHERE ENTRADA_SALIDA_ID=" + x.getId()+"; affected="+rESD);
			
			psESD = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_DETALLE(ENTRADA_SALIDA_ID,PRODUCTO_CODIGO_BARRAS,ALMACEN_ID,CANTIDAD,PRECIO_VENTA) "
					+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			rESD = 0;
			for (EntradaSalidaDetalle pvd : pvdList) {
				int ciESD = 1;

				psESD.clearParameters();
				psESD.clearWarnings();

				psESD.setInt(ciESD++, x.getId());
				if (pvd.getProductoCodigoBarras() == null) {
					psESD.setObject(ciESD++, null);
				} else {
					psESD.setString(ciESD++, pvd.getProductoCodigoBarras());
				}
				psESD.setInt(ciESD++, pvd.getAlmacenId());
				psESD.setInt(ciESD++, pvd.getCantidad());
				psESD.setDouble(ciESD++, pvd.getPrecioVenta());

				rESD += psESD.executeUpdate();
				logger.info("\t=>INSERT INTO ENTRADA_SALIDA_DETALLE .... "+pvd.getCantidad()+" X "+pvd.getProductoCodigoBarras()+" @ "+pvd.getAlmacenId());
			}

			psESE = conn.prepareStatement("UPDATE ENTRADA_SALIDA_ESTADO SET FECHA=?,USUARIO_EMAIL=?,COMENTARIOS=? WHERE ENTRADA_SALIDA_ID=? AND ESTADO_ID=?");
			int ciESE = 1;

			psESE.setTimestamp(ciESE++, now);
			psESE.setString(ciESE++, u.getEmail());
			psESE.setString(ciESE++, "--EDITADO--");

			psESE.setInt(ciESE++, x.getId());
			psESE.setInt(ciESE++, x.getEstadoId());

			psESE.executeUpdate();

			conn.commit();
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			try {
				conn.rollback();
			} catch (SQLException exR) {
				logger.error("RollBack failed:", ex);
			}
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return r;
	}

	public int verificar(EntradaSalida x, Usuario u) throws DAOException {
		Connection conn = null;
		int rs = 0;
		try {
			conn = getConnectionCommiteable();
			rs = verificar(x, u, conn);
		
			conn.commit();
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			try {
				conn.rollback();
			} catch (SQLException exR) {
				logger.error("RollBack failed:", ex);
			}
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if (conn != null) {
				try {					
					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return rs;
	}
	
	private int verificar(EntradaSalida x, Usuario u, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		PreparedStatement psESE = null;
		PreparedStatement psESD = null;

		int r = -1;
		//Connection conn = null;
		try {
			//conn = getConnectionCommiteable();
			ps = conn.prepareStatement("UPDATE ENTRADA_SALIDA SET ESTADO_ID=? WHERE ID=?");
			Timestamp now = new Timestamp(System.currentTimeMillis());

			int ci = 1;
			ps.setInt(ci++, Constants.ESTADO_VERIFICADO);
			ps.setInt(ci++, x.getId());

			r = ps.executeUpdate();
			psESE = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_ESTADO(ENTRADA_SALIDA_ID,ESTADO_ID,FECHA,USUARIO_EMAIL,COMENTARIOS) "
					+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int ciESE = 1;

			int rESE = -1;

			psESE.setInt(ciESE++, x.getId());
			psESE.setInt(ciESE++, Constants.ESTADO_VERIFICADO);
			psESE.setTimestamp(ciESE++, now);
			psESE.setString(ciESE++, u.getEmail());
			psESE.setString(ciESE++, "--NORMAL--");

			rESE += psESE.executeUpdate();

			//conn.commit();
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw ex;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
				}
			}
		}
		return r;
	}

	public int surtir(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList, Usuario u) throws DAOException {
		int rs= 0;
		if(x.getTipoMov() == Constants.TIPO_MOV_SALIDA_TRASPASO){
			
			Connection conn = null;

			try {
				conn = getConnectionCommiteable();
				
				logger.debug("============>>> INICIO SURTIR TRASPASO ["+x.getSucursalIdTraOri()+"] A ["+x.getSucursalIdTraDes()+"]<<<===========");
				rs = surtirTransacc(x, pvdList, u, conn);
				logger.debug("============>>> after surtir:rtx="+rs);
				preparaTraspaso(x, pvdList, u, conn);
				logger.debug("============>>> after preparaTraspaso:x.id="+x.getSucursalId()+", c.tipoMov="+x.getTipoMov());
				rs = insert(Constants.TIPO_MOV_ENTRADA_ALMACEN_TRASPASO, x, pvdList);
				logger.debug("============>>> after insert:rtx="+rs+", x.id="+x.getId());
				rs = verificar(x, u , conn);
				logger.debug("============>>> after verificar:rtx="+rs);			
				rs = surtirTransacc(x, pvdList, u, conn);
				logger.debug("============>>> after surtir destino:rtx="+rs);

				conn.commit();
				
				logger.debug("============>>> commit done, FIN SURTIR TRASPASO <<<===========");

				
			} catch (SQLException ex) {
				logger.error("SQLException:", ex);
				try {
					conn.rollback();
				} catch (SQLException exR) {
					logger.error("RollBack failed:", ex);
				}
				throw new DAOException("InUpdate:" + ex.getMessage());
			} finally {
				if (conn != null) {
					try {					
						conn.close();
					} catch (SQLException ex) {
						logger.error("clossing, SQLException:" + ex.getMessage());
						throw new DAOException("Closing:" + ex.getMessage());
					}
				}
			}
			
			return rs;
		} else {
			return surtirNormal(x, pvdList, u);
		}
		
	}
	
	private int surtirNormal(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList, Usuario u) throws DAOException {
		Connection conn = null;
		int rs = 0;
		try {
			conn = getConnectionCommiteable();
			rs = surtirTransacc(x, pvdList, u, conn);
		
			conn.commit();
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			try {
				conn.rollback();
			} catch (SQLException exR) {
				logger.error("RollBack failed:", ex);
			}
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if (conn != null) {
				try {					
					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return rs;
	}
	
	private int surtirTransacc(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList, Usuario u,Connection conn) throws DAOException {
		PreparedStatement ps = null;
		PreparedStatement psESE = null;
		PreparedStatement psESD = null;
		PreparedStatement psMHP = null;
		int r = -1;
		
		try {
			//conn = getConnectionCommiteable();

			psESD = conn.prepareStatement("UPDATE ALMACEN_PRODUCTO SET CANTIDAD = CANTIDAD + ? "
					+ " WHERE PRODUCTO_CODIGO_BARRAS=? AND ALMACEN_ID=?");

			psMHP = conn.prepareStatement("INSERT INTO MOVIMIENTO_HISTORICO_PRODUCTO(ALMACEN_ID,FECHA,TIPO_MOVIMIENTO,CANTIDAD,COSTO,PRECIO,USUARIO_EMAIL,PRODUCTO_CODIGO_BARRAS,ENTRADA_SALIDA_ID) "
					+ " VALUES(?,?,?,?,?,?,?,?,?)");

			Timestamp now = new Timestamp(System.currentTimeMillis());
			int cant=0;
			for (EntradaSalidaDetalle pvd : pvdList) {
				psESD.clearParameters();
				cant=0;
				if(x.getTipoMov() >= Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA && x.getTipoMov() < Constants.TIPO_MOV_OTRO){
					cant = -1 * pvd.getCantidad();
					psESD.setInt(1, cant);
				} else if(x.getTipoMov() >= Constants.TIPO_MOV_ENTRADA_ALMACEN_COMPRA &&  x.getTipoMov() < Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA){				
					cant = pvd.getCantidad();
					psESD.setInt(1, cant);
				}
				logger.info("->tipomov="+x.getTipoMov()+", cant="+cant);
				psESD.setString(2, pvd.getProductoCodigoBarras());
				psESD.setInt(3, pvd.getAlmacenId());

				psESD.executeUpdate();

				int ci = 1;
				psMHP.clearParameters();

				psMHP.setInt(ci++, pvd.getAlmacenId());
				psMHP.setTimestamp(ci++, now);
				psMHP.setInt(ci++, x.getTipoMov());
				psMHP.setInt(ci++, pvd.getCantidad());
				psMHP.setObject(ci++, null);
				psMHP.setObject(ci++, null);
				psMHP.setString(ci++, u.getEmail());
				psMHP.setString(ci++, pvd.getProductoCodigoBarras());
				psMHP.setInt   (ci++, x.getId());

				r = psMHP.executeUpdate();

			}
			psESD.close();
			psMHP.close();

			ps = conn.prepareStatement("UPDATE ENTRADA_SALIDA SET ESTADO_ID=? WHERE ID=?");

			int ci = 1;
			ps.setInt(ci++, Constants.ESTADO_SURTIDO);
			ps.setInt(ci++, x.getId());

			r = ps.executeUpdate();
			psESE = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_ESTADO(ENTRADA_SALIDA_ID,ESTADO_ID,FECHA,USUARIO_EMAIL,COMENTARIOS) "
					+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int ciESE = 1;

			int rESE = -1;
			psESE.clearParameters();
			ciESE = 1;

			psESE.setInt(ciESE++, x.getId());
			psESE.setInt(ciESE++, Constants.ESTADO_SURTIDO);
			psESE.setTimestamp(ciESE++, now);
			psESE.setString(ciESE++, u.getEmail());
			psESE.setString(ciESE++, "--NORMAL--");

			rESE += psESE.executeUpdate();

			//conn.commit();

		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
//			try {
//				conn.rollback();
//			} catch (SQLException exR) {
//				logger.error("RollBack failed:", ex);
//			}
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
//					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return r;
	}
	
	private int preparaTraspaso(EntradaSalida x, ArrayList<? extends EntradaSalidaDetalle> pvdList, Usuario u,Connection conn) throws DAOException {
		PreparedStatement ps = null;
		ResultSet         rs = null;
		LinkedHashMap<Integer,Almacen> rAL     = new LinkedHashMap<Integer,Almacen>();
		LinkedHashMap<Integer,Almacen> rALDest = new LinkedHashMap<Integer,Almacen>();
		
		Integer sucDest = x.getSucursalIdTraDes();
		if(sucDest == null){
			throw new IllegalStateException("EsIdTraDes=null");
		}
		int r = -1;
//		Connection conn = null;
		try {
//			conn = getConnection();

			ps  = conn.prepareStatement("SELECT * FROM ALMACEN");
			 
			rs = ps.executeQuery();
			
			while(rs.next()){
				Almacen a = new Almacen();
				a.setId((Integer)rs.getObject("ID"));
				a.setTipoAlmacen((Integer)rs.getObject("TIPO_ALMACEN"));
				a.setSucursalId((Integer)rs.getObject("SUCURSAL_ID"));
				rAL.put(a.getId(),a);
				
				if(sucDest == a.getSucursalId()){
					rALDest.put(a.getTipoAlmacen(), a);
				}				
			}
			rs.close();

			for (EntradaSalidaDetalle pvd : pvdList) {
				int almIdOrig  = pvd.getAlmacenId();
				int almTipOrig = rAL.get(almIdOrig).getTipoAlmacen();
				int almIdDest  = rALDest.get(almTipOrig).getId();
				
				pvd.setAlmacenId(almIdDest);				
			}
			x.setSucursalId(sucDest);
			x.setEsIdTraOri(x.getId());
			x.setTipoMov(Constants.TIPO_MOV_ENTRADA_ALMACEN_TRASPASO);
			x.setComentarios("TRASPASO DE ES_ID="+x.getId());
		} catch (SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("preparaTraspaso:" + ex.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
//					conn.close();
				} catch (SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:" + ex.getMessage());
				}
			}
		}
		return r;
	}

	public void invocarInicioWSCFDI(EntradaSalidaQuickView pedidoVenta,ArrayList<EntradaSalidaDetalleQuickView> pvdList,Cliente c, Usuario u,Sucursal s) throws DAOException {
		logger.info("->invocarInicioWSCFDI:");
		PreparedStatement ps = null;
		PreparedStatement psCFD = null;
		PreparedStatement psESE = null;
		PreparedStatement psESD = null;
		
		ResultSet rsCFD = null;
		Cfd cfd = null;
		cfd = new Cfd();
		Connection conn = null;
		String mensajeRefacturado="";
		Timestamp now = new Timestamp(System.currentTimeMillis());
		cfd.setUltimaActualizacion(now);
		try {
			conn = getConnectionCommiteable();
			logger.info("->invocarInicioWSCFDI:BEGIN TRANSACTION.");
			if(pedidoVenta.getCfdId() != null){
				mensajeRefacturado="REFACTRUADO, POR ULTIMO ERROR";
				psCFD = conn.prepareStatement("SELECT ID,ULTIMA_ACTUALIZACION,CONTENIDO_ORIGINAL_XML,CALLING_ERROR_RESULT,NUM_CFD,TIPO FROM CFD "+
						"WHERE ID=?"
				);
				psCFD.setInt(1, pedidoVenta.getCfdId());

				rsCFD = psCFD.executeQuery();
				if(rsCFD.next()) {
					cfd.setId((Integer)rsCFD.getObject("ID"));
					cfd.setUltimaActualizacion((Timestamp)rsCFD.getObject("ULTIMA_ACTUALIZACION"));
					Blob bc=rsCFD.getBlob("CONTENIDO_ORIGINAL_XML");
					if(bc!=null)cfd.setContenidoOriginalXml(bc.getBytes(0, (int) bc.length()));
					cfd.setCallingErrorResult((String)rsCFD.getObject("CALLING_ERROR_RESULT"));
					cfd.setNumCfd((String)rsCFD.getObject("NUM_CFD"));
					cfd.setTipo((String)rsCFD.getObject("TIPO"));
				} 
				psCFD.close();
				rsCFD.close();
			} else {
				mensajeRefacturado="FACTURADO 1RA VEZ";								
			}
			logger.info("->invocarInicioWSCFDI:before WS invoke.");
			DigifactClient.invokeWSFactura(cfd,pedidoVenta, pvdList, c, s.getSerieSicofi(), s.getUsuarioSicofi(), s.getPasswordSicofi());			
			logger.info("->invocarInicioWSCFDI:WS invoked.");
			try {
				logger.info("->invocarInicioWSCFDI:cfd ="+BeanUtils.describe(cfd));
			} catch (Exception ex) {
				logger.error ("->invocarInicioWSCFDI:Describe fails:", ex);
			}
			logger.info("->invocarInicioWSCFDI:INSERTING OR UPDATING");
			if(cfd.getId() == null){
				psCFD = conn.prepareStatement("INSERT INTO CFD(ULTIMA_ACTUALIZACION,CONTENIDO_ORIGINAL_XML,CALLING_ERROR_RESULT,NUM_CFD,TIPO) "+
						" VALUES(?,?,?,?,?)"
						,Statement.RETURN_GENERATED_KEYS);			
				int ci=1;				
				psCFD.setObject(ci++,now);
				if(cfd.getContenidoOriginalXml()!=null)psCFD.setObject(ci++,new ByteArrayInputStream(cfd.getContenidoOriginalXml())); else psCFD.setNull(ci++,java.sql.Types.BLOB);
				psCFD.setObject(ci++,cfd.getCallingErrorResult());
				psCFD.setObject(ci++,cfd.getNumCfd());
				psCFD.setObject(ci++,cfd.getTipo());

				psCFD.executeUpdate();
				logger.info("inserted");
				ResultSet rsk = psCFD.getGeneratedKeys();
				if(rsk != null){
					while(rsk.next()){
						cfd.setId(((Long)rsk.getObject(1)).intValue());
						pedidoVenta.setCfdId(cfd.getId());
						logger.info("inserted, CFD pedidoVenta id="+cfd.getId());
					}
				}		
			} else {
				psCFD = conn.prepareStatement("UPDATE CFD SET ULTIMA_ACTUALIZACION=?,CONTENIDO_ORIGINAL_XML=?,CALLING_ERROR_RESULT=?,NUM_CFD=?,TIPO=? "+
					" WHERE ID=?");
			
				int ci=1;
				psCFD.setObject(ci++,cfd.getId());
				psCFD.setObject(ci++,now);
				if(cfd.getContenidoOriginalXml()!=null)psCFD.setObject(ci++,new ByteArrayInputStream(cfd.getContenidoOriginalXml())); else psCFD.setNull(ci++,java.sql.Types.BLOB);
				psCFD.setObject(ci++,cfd.getCallingErrorResult());
				psCFD.setObject(ci++,cfd.getNumCfd());
				psCFD.setObject(ci++,cfd.getTipo());
				psCFD.setObject(ci++,cfd.getId());

				psCFD.executeUpdate();						
				logger.info("updated");
			}
			psCFD.close();
			logger.info("->invocarInicioWSCFDI:psCDF closed");
			ps = conn.prepareStatement("UPDATE ENTRADA_SALIDA SET ESTADO_ID=?,CFD_ID=? WHERE ID=?");

			int ci = 1;
			ps.setInt(ci++, Constants.ESTADO_FACTURADO);
			ps.setInt(ci++, cfd.getId());
			ps.setInt(ci++, pedidoVenta.getId());
			ps.executeUpdate();			
			ps.close();
			logger.info("->invocarInicioWSCFDI:psCDF closed");
			
			psESE = conn.prepareStatement("SELECT ID,ENTRADA_SALIDA_ID,ESTADO_ID,FECHA,USUARIO_EMAIL,COMENTARIOS FROM ENTRADA_SALIDA_ESTADO "					
					+ "WHERE ENTRADA_SALIDA_ID=? AND ESTADO_ID=?");
			psESE.setInt(1, pedidoVenta.getId());
			psESE.setInt(2, Constants.ESTADO_FACTURADO);
			
			ResultSet rsESE = psESE.executeQuery();
			EntradaSalidaEstado eseX = null;
			if(rsESE.next()) {
				eseX = new EntradaSalidaEstado();
				eseX.setId((Integer)rsESE.getObject("ID"));
				eseX.setEntradaSalidaId((Integer)rsESE.getObject("ENTRADA_SALIDA_ID"));
				eseX.setEstadoId((Integer)rsESE.getObject("ESTADO_ID"));
				eseX.setFecha((Timestamp)rsESE.getObject("FECHA"));
				eseX.setUsuarioEmail((String)rsESE.getObject("USUARIO_EMAIL"));
				eseX.setComentarios((String)rsESE.getObject("COMENTARIOS"));
			} else{
				eseX = new EntradaSalidaEstado();
				eseX.setId(null);
				eseX.setEntradaSalidaId(pedidoVenta.getId());
				eseX.setEstadoId(Constants.ESTADO_FACTURADO);
				eseX.setFecha(now);
				eseX.setUsuarioEmail(u.getEmail());
				eseX.setComentarios(mensajeRefacturado);
			}
			rsESE.close();
			psESE.close();			
			logger.info("->invocarInicioWSCFDI:psESE closed");
			if(eseX.getId() == null){
				psESE = conn.prepareStatement("INSERT INTO ENTRADA_SALIDA_ESTADO(ENTRADA_SALIDA_ID,ESTADO_ID,FECHA,USUARIO_EMAIL,COMENTARIOS) "
						+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

				int ciESE = 1;

				psESE.setInt(ciESE++, pedidoVenta.getId());
				psESE.setInt(ciESE++, eseX.getEstadoId());
				psESE.setTimestamp(ciESE++, eseX.getFecha());
				psESE.setString(ciESE++, eseX.getUsuarioEmail());
				psESE.setString(ciESE++, eseX.getComentarios());

				psESE.executeUpdate();
				logger.info("->invocarInicioWSCFDI:insert psESE executed");
			} else {
				psESE = conn.prepareStatement("UPDATE ENTRADA_SALIDA_ESTADO SET FECHA=?,USUARIO_EMAIL=?,COMENTARIOS=? WHERE ENTRADA_SALIDA_ID=? AND ESTADO_ID=?");
				int ciESE = 1;

				psESE.setTimestamp(ciESE++, eseX.getFecha());
				psESE.setString(ciESE++, eseX.getUsuarioEmail());
				psESE.setString(ciESE++, eseX.getComentarios());

				psESE.setInt(ciESE++, pedidoVenta.getId());
				psESE.setInt(ciESE++, eseX.getEstadoId());

				psESE.executeUpdate();
				logger.info("->invocarInicioWSCFDI:update psESE executed");
			}
			psESE.close();
			logger.info("->invocarInicioWSCFDI:update psESE closed");
			
			
			conn.commit();
			logger.info("->invocarInicioWSCFDI:COMMIT");
		} catch (SQLException ex) {
			logger.error("->invocarInicioWSCFDI:SQLException:", ex);
			try {
				conn.rollback();
				logger.info("->invocarInicioWSCFDI:============== ROLLBACK =================");
			} catch (SQLException exR) {
				logger.error("invocarInicioWSCFDI:RollBack failed:", ex);
			}
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			logger.info("->invocarInicioWSCFDI:END");
		}
	}


    public int delete(EntradaSalida x)throws DAOException {
		PreparedStatement ps = null;
		int r= -1;
		Connection conn = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement("DELETE FROM ENTRADA_SALIDA WHERE ID=?");
			ps.setObject(1, x.getId());
			
			r = ps.executeUpdate();						
		}catch(SQLException ex) {
			logger.error("SQLException:", ex);
			throw new DAOException("InUpdate:" + ex.getMessage());
		} finally {
			if(ps != null) {
				try{
					ps.close();
					conn.close();
				}catch(SQLException ex) {
					logger.error("clossing, SQLException:" + ex.getMessage());
					throw new DAOException("Closing:"+ex.getMessage());
				}
			}
		}
		return r;
	}

}

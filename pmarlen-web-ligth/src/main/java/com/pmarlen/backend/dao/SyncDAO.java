/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.backend.dao;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.rest.dto.DES;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.P;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import com.tracktopell.jdbc.DataSourceFacade;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class SyncDAO {
	
	private final static Logger logger = Logger.getLogger(SyncDAO.class.getName());

	private SyncDAO instance;

	public SyncDAO getInstance() {
		if(instance == null) {
			instance = new SyncDAO();
		}
		return instance;
	}
	
	private Connection getConnection(){
		return DataSourceFacade.getStrategy().getConnection();
	}

	private Connection getConnectionCommiteable(){
		return DataSourceFacade.getStrategy().getConnectionCommiteable();
	}
	
	public SyncDTOPackage syncTransaction(SyncDTORequest syncDTORequest) throws DAOException{
		SyncDTOPackage s= new SyncDTOPackage();
		if(syncDTORequest.isSending()) {
			Connection conn = null;
			
			try {
				conn = getConnectionCommiteable();
			
				List<ES> esList = syncDTORequest.getEsList();
				for(ES es: esList){
					EntradaSalida esEntity = new EntradaSalida();
					esEntity.setCaja(es.getNumC());
					esEntity.setClienteId(es.getIdCte());
					esEntity.setFactorIva(0.16);
					esEntity.setFechaCreo(new Timestamp(es.getT()));
					esEntity.setFormaDePagoId(1);
					esEntity.setFormaDePagoId(1);
					esEntity.setImporteRecibido(es.getIr());
					esEntity.setMetodoDePagoId(1);
					esEntity.setPorcentajeDescuentoCalculado(0);
					esEntity.setPorcentajeDescuentoExtra(0);
					esEntity.setSucursalId(es.getsId());
					esEntity.setTipoMov(es.getTm());
					esEntity.setUsuarioEmailCreo(es.getU());
					
					List<DES> dList = es.getD();
					ArrayList<EntradaSalidaDetalle> pvdList = new ArrayList<EntradaSalidaDetalle>();					
					for(DES d: dList){
						EntradaSalidaDetalle esd=new EntradaSalidaDetalle();
						
						esd.setAlmacenId(d.getaId());
						esd.setCantidad(d.getC());
						esd.setPrecioVenta(d.getP());
						esd.setProductoCodigoBarras(d.getCb());
						
						pvdList.add(esd);
					}
					EntradaSalidaDAO.getInstance().insertPedidoVentaSucursal(conn,esEntity, pvdList);					
				}
				
				conn.commit();
			}catch(Exception e){
				try {
					conn.rollback();
				}catch(Exception et){
			}
			}
		}
		int sucId=new Integer(syncDTORequest.getSucursalId());
		ArrayList<InventarioSucursalQuickView> xa = AlmacenProductoDAO.getInstance().findAllBySucursal(sucId);
		ArrayList<P> xb = new ArrayList<P>();
		for(InventarioSucursalQuickView xia: xa){
			xb.add(xia.generateFaccadeForREST());
		}
		s.setInventarioSucursalQVList(xb);
		s.setUsuarioList(UsuarioDAO.getInstance().findAllForRest());
		s.setClienteList(ClienteDAO.getInstance().findAll());
		s.setMetodoDePagoList(MetodoDePagoDAO.getInstance().findAll());
		s.setFormaDePagoList(FormaDePagoDAO.getInstance().findAll());
		s.setSucursal(SucursalDAO.getInstance().findBy(new Sucursal(sucId)));
		

		return s;
				
	}
}

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
import com.pmarlen.rest.dto.ES_ESD;
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

	private static SyncDAO instance;

	public static SyncDAO getInstance() {
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
		int sucId=syncDTORequest.getiAmAliveDTORequest().getSucursalId();
		List<ES_ESD> escdList = syncDTORequest.getEscdList();
		
		logger.debug("syncTransaction:sucId="+sucId+", escdList.size="+(escdList!=null?escdList.size():null));
		
		if(escdList!=null && escdList.size()>0) {
			Connection conn = null;
			
			try {
				conn = getConnectionCommiteable();
						
				for(ES_ESD escd: escdList){
					EntradaSalida es = escd.getEs();
					List<EntradaSalidaDetalle> esList = escd.getEsdList();
					EntradaSalidaDAO.getInstance().insertPedidoVentaSucursal(conn,es,esList);
				}
				
				conn.commit();
			}catch(Exception e){
				try {
					conn.rollback();
				}catch(Exception et){
				}
			}
		}
		
		ArrayList<InventarioSucursalQuickView> xa = AlmacenProductoDAO.getInstance().findAllBySucursal(sucId);
		
		s.setInventarioSucursalQVList(xa);
		s.setUsuarioList(UsuarioDAO.getInstance().findAllSimple());
		s.setClienteList(ClienteDAO.getInstance().findAll());
		s.setMetodoDePagoList(MetodoDePagoDAO.getInstance().findAll());
		s.setFormaDePagoList(FormaDePagoDAO.getInstance().findAll());
		s.setSucursal(SucursalDAO.getInstance().findBy(new Sucursal(sucId)));
		s.setAlmacenList(AlmacenDAO.getInstance().findBySucursal(sucId));

		return s;
				
	}
}

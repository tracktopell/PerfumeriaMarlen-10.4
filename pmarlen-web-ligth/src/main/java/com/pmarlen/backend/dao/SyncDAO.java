/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.backend.dao;

import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.quickviews.ClienteQuickView;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
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
					EntradaSalida es = escd.getEs().reverse();
					List<EntradaSalidaDetalle> esdList = new ArrayList<EntradaSalidaDetalle>();
					List<ESD> esdl = escd.getEsdList();
					for(ESD esd: esdl){
						esdList.add(esd.reverse());
					}
					EntradaSalidaDAO.getInstance().insertPedidoVentaSucursal(conn,es,esdList);
				}
				
				conn.commit();
				
			}catch(Exception e){
				try {
					conn.rollback();
				}catch(Exception et){
				}
			} finally {
				try{
					conn.close();
				}catch(Exception e){
				
				}
			}
		}
		
		ArrayList<InventarioSucursalQuickView> inventarioBigList = AlmacenProductoDAO.getInstance().findAllBySucursal(sucId);
		List<I> inventarioSucursalList = new ArrayList<I>();
		for(InventarioSucursalQuickView bigI: inventarioBigList){
			inventarioSucursalList.add(new I(bigI));
		}
		s.setInventarioSucursalList(inventarioSucursalList);
		s.setUsuarioList(UsuarioDAO.getInstance().findAllSimple());
		ArrayList<ClienteQuickView> clientesQVList = ClienteDAO.getInstance().findAll();
		ArrayList<Cliente> clientesList = new ArrayList<Cliente>();
		clientesList.addAll(clientesQVList);
		s.setClienteList(clientesList);
		s.setMetodoDePagoList(MetodoDePagoDAO.getInstance().findAll());
		s.setFormaDePagoList(FormaDePagoDAO.getInstance().findAll());
		s.setSucursal(SucursalDAO.getInstance().findBy(new Sucursal(sucId)));
		s.setAlmacenList(AlmacenDAO.getInstance().findBySucursal(sucId));

		return s;
				
	}
}

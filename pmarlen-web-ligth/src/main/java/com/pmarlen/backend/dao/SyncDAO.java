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
import com.pmarlen.backend.model.quickviews.UsuarioQuickView;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.I;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import com.tracktopell.jdbc.DataSourceFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
		
		logger.debug("syncTransaction:sucId="+sucId);
		
		File filePMCajaLastVersion =  new File("/usr/local/pmcajadist/classes/version.properties");
		logger.debug("syncTransaction:filePMCajaLastVersion:"+filePMCajaLastVersion);
		if(filePMCajaLastVersion.exists() && filePMCajaLastVersion.isFile() && filePMCajaLastVersion.canRead()){
			Properties porpVersionPMCaja = new Properties();
			try {
				porpVersionPMCaja.load(new FileInputStream(filePMCajaLastVersion));
				logger.debug("syncTransaction:porpVersionPMCaja:"+porpVersionPMCaja);				
				String pmarlencaja_version = porpVersionPMCaja.getProperty("pmarlencaja.version");
				if(pmarlencaja_version != null){
					s.setCurrentPMCajaVersion(pmarlencaja_version);
				}
			}catch(IOException ioe){
				logger.debug("syncTransaction:filePMCajaLastVersion:"+filePMCajaLastVersion+": "+ioe);
				s.setCurrentPMCajaVersion(null);
			}
		}
		
		
		if(escdList!=null && escdList.size()>0) {
			Connection conn = null;
			logger.debug("syncTransaction:---------------- PROCESSING Sent : List<ES_ESD> escdList.size="+escdList.size());
			try {
				conn = getConnectionCommiteable();
				logger.debug("syncTransaction:BEGIN TRANSACTION");
				int indexProccessing=0;
				for(ES_ESD escd: escdList){
					EntradaSalida es = escd.getEs().reverse();
					List<EntradaSalidaDetalle> esdList = new ArrayList<EntradaSalidaDetalle>();
					List<ESD> esdl = escd.getEsdList();
					logger.debug("syncTransaction:\tprepare esdList:");
					for(ESD esd: esdl){
						esdList.add(esd.reverse());
					}
					logger.debug("syncTransaction:\tprepare for insertPedidoVentaSucursal:");
					s.processingES(indexProccessing++);
					EntradaSalidaDAO.getInstance().insertPedidoVentaSucursal(conn,es,esdList);
					logger.debug("syncTransaction:\tend insertPedidoVentaSucursal:");
				}
				logger.debug("syncTransaction:END");
				logger.debug("syncTransaction:COMMIT TRANSACTION");
				conn.commit();
				s.setSyncDBStatus(SyncDTOPackage.SYNC_OK);
			} catch(SQLException e){
				s.setSyncDBStatus(SyncDTOPackage.SYNC_FAIL | SyncDTOPackage.SYNC_FAIL_JDBC);
				logger.error("syncTransaction: fail ?",e);
				try {
					logger.debug("syncTransaction:ROLLBACK TRANSACTION");
					conn.rollback();
				}catch(Exception et){
					logger.debug("syncTransaction:fail at rollback :(",et);
				}
			} catch(Exception e){
				s.setSyncDBStatus(SyncDTOPackage.SYNC_FAIL | SyncDTOPackage.SYNC_FAIL_INTEGRITY);
				logger.error("syncTransaction: fail ?",e);
				try {
					logger.debug("syncTransaction:ROLLBACK TRANSACTION");
					conn.rollback();
				}catch(Exception et){
					logger.debug("syncTransaction:fail at rollback :(",et);
				}
			} finally {
				try{
					conn.close();
				}catch(Exception e2){
					logger.debug("syncTransaction:fail at close :(",e2);
				}
			}
		} else {
			s.setSyncDBStatus(SyncDTOPackage.SYNC_EMPTY_TRANSACTION);
		}
		
		logger.debug("syncTransaction:----------------REGULAR DataGet -----------------");
		
		ArrayList<InventarioSucursalQuickView> inventarioBigList = AlmacenProductoDAO.getInstance().findAllBySucursal(sucId);
		List<I> inventarioSucursalList = new ArrayList<I>();
		for(InventarioSucursalQuickView bigI: inventarioBigList){
			inventarioSucursalList.add(new I(bigI));
		}
		s.setInventarioSucursalList(inventarioSucursalList);
		final ArrayList<UsuarioQuickView> usuariosList = UsuarioDAO.getInstance().findAll();
		logger.debug("syncTransaction: usuariosList=");		
		for(UsuarioQuickView u: usuariosList){
			logger.debug("\tsyncTransaction: USUARIO="+u);
		}
		s.setUsuarioQVList(usuariosList);
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

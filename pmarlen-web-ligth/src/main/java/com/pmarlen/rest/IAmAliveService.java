package com.pmarlen.rest;

import com.pmarlen.backend.dao.CorteCajaDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.SyncDAO;
import com.pmarlen.backend.model.CorteCaja;
import com.pmarlen.backend.model.InfoCaja;
import com.pmarlen.backend.model.MonitorDeCajas;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.IAmAliveDTOPackage;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.web.servlet.CajaSessionInfo;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
@Path("/iamaliveservice/")
public class IAmAliveService {

	@Context 
	HttpServletRequest httpRequest;

	private static final String encodingUTF8 = "UTF-8";

	private final static Logger logger = Logger.getLogger(IAmAliveService.class.getName());

	@POST
	@Path("/hello")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=" + encodingUTF8)
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + encodingUTF8)
	public IAmAliveDTOPackage hello(IAmAliveDTORequest iamAliveDTOREquest) throws WebApplicationException {
		logger.debug("hello: iamAliveDTOREquest:" + iamAliveDTOREquest);
		String callerIpAddress = "?.?.?.?";
		if(httpRequest != null) {
			callerIpAddress = httpRequest.getRemoteAddr();
			logger.debug("** httpRequest) Caller IP = " + callerIpAddress);
		}
		
		IAmAliveDTOPackage r = registerHello(iamAliveDTOREquest, callerIpAddress);
		
		return r;
	}

	static IAmAliveDTOPackage registerHello(IAmAliveDTORequest syncDTORequest, String callerIpAddress) {
		logger.debug("registerHello: syncDTORequest:CorteCajaDTO=" + syncDTORequest.getCorteCajaDTO());
		CajaSessionInfo cajaSessionInfo = null;
		String pmarlencaja_version =  null;
		if (syncDTORequest.getSessionId() != null) {
			cajaSessionInfo = ContextAndSessionListener.cajaSessionInfoHT.get(syncDTORequest.getSessionId());
			logger.debug("registerHello:cajaSessionInfo=" + cajaSessionInfo);
			if (cajaSessionInfo == null) {
				logger.info("registerHello: -->> add new cajaSessionInfo, loggedIn=" + syncDTORequest.getLoggedIn()+", DevicesInfoUSB="+syncDTORequest.getDevicesInfoUSB());
				cajaSessionInfo = new CajaSessionInfo();
				cajaSessionInfo.setSessionId(syncDTORequest.getSessionId());
				cajaSessionInfo.setCaja(String.valueOf(syncDTORequest.getCajaId()));
				cajaSessionInfo.setSucursal(String.valueOf(syncDTORequest.getSucursalId()));
				cajaSessionInfo.setRemoteAddr(callerIpAddress);
				cajaSessionInfo.setUserAgent(syncDTORequest.getUserAgent());
				if(syncDTORequest.getDevicesInfoUSB()!=null){
					cajaSessionInfo.setDevicesInfoUSB(syncDTORequest.getDevicesInfoUSB());
					if(syncDTORequest.getDevicesInfoUSB().contains("|")){
						final String[] devicesInfoUSBarr = syncDTORequest.getDevicesInfoUSB().split("|");
						InfoCaja infoCaja = MonitorDeCajas.getInfoCaja(syncDTORequest.getUserAgent().getUserInSession());
						if(infoCaja!=null){
							infoCaja.setVersion(syncDTORequest.getUserAgent().getVersion());						
							infoCaja.turnOffAllUSB();
							for(String dia: devicesInfoUSBarr){
								String[] diarr = dia.split("^");
								String usbdevid=diarr[0];
								infoCaja.getUsbDeviceMap().get(usbdevid).setConnected(true);
							}
							cajaSessionInfo.setInfoCaja(infoCaja);
						}
					}
				}
				ContextAndSessionListener.cajaSessionInfoHT.put(syncDTORequest.getSessionId(), cajaSessionInfo);
			}
			if (syncDTORequest.getLoggedIn() != null) {
				cajaSessionInfo.setLoggedIn(syncDTORequest.getLoggedIn());
			}
			pmarlencaja_version = SyncDAO.getCurrentPMCajaVersion();
			cajaSessionInfo.setLastAccesedTime(System.currentTimeMillis());
			registerCorteCaja(syncDTORequest.getCorteCajaDTO());
		}
		IAmAliveDTOPackage r = new IAmAliveDTOPackage(1,pmarlencaja_version);
		return r;
	}

	private static void registerCorteCaja(CorteCajaDTO corteCajaDTO) {		
		CorteCaja cc = corteCajaDTO.reverse();
		boolean insert=false;
		try {
			logger.debug("registerCorteCaja:corteCajaDTO ultimoEstadoPara: (sucursal=" + cc.getSucursalId()+", caja="+cc.getCaja()+")");
			CorteCaja lastBySucursalCaja = CorteCajaDAO.getInstance().findLastBySucursalCaja(cc);
			logger.debug("registerCorteCaja: ultimoEstadoPara: ultimoEstadoPara="+lastBySucursalCaja);
			if(lastBySucursalCaja != null){
				if(lastBySucursalCaja.getTipoEvento() != corteCajaDTO.getTipoEvento()){
					logger.debug("registerCorteCaja: OK, INSERT");
					insert=true;					
				} else {
					logger.debug("registerCorteCaja: ESTADO REPETIDO!");
				}
			} else {
				logger.debug("registerCorteCaja: OK, PRIMER ESTADO ?");
				insert=true;								
			}
			if(insert){
				logger.debug("registerCorteCaja: -->> CorteCajaDAO.getInstance().insert("+cc+");");
				CorteCajaDAO.getInstance().insert(cc);
				logger.debug("registerCorteCaja: NUEVO ESTADO :id="+cc.getId());
				ArrayList<CorteCaja> llBySucCaja = CorteCajaDAO.getInstance().findAllBy(cc.getSucursalId(), cc.getCaja(), null, null);
				logger.debug("registerCorteCaja: FOR REVIEW LIST: ");
				for(CorteCaja ccu: llBySucCaja){
					logger.trace("\tregisterCorteCaja:CorteCaja"+ccu);				
				}
				
			}			
		} catch(DAOException de){
			logger.error("registerCorteCaja:", de);
		}
	}
	

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	

}

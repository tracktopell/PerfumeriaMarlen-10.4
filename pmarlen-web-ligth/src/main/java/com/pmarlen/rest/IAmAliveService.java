package com.pmarlen.rest;

import com.pmarlen.rest.dto.IAmAliveDTOPackage;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.web.servlet.CajaSessionInfo;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
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
		logger.debug("iamAliveDTOREquest: syncDTORequest:CorteCajaDTO=" + syncDTORequest.getCorteCajaDTO());
		CajaSessionInfo cajaSessionInfo = null;
		if (syncDTORequest.getSessionId() != null) {
			cajaSessionInfo = ContextAndSessionListener.cajaSessionInfoHT.get(syncDTORequest.getSessionId());
			logger.debug("cajaSessionInfo=" + cajaSessionInfo);
			if (cajaSessionInfo == null) {
				logger.debug(" add new cajaSessionInfo, loggedIn=" + syncDTORequest.getLoggedIn());
				cajaSessionInfo = new CajaSessionInfo();
				cajaSessionInfo.setSessionId(syncDTORequest.getSessionId());
				cajaSessionInfo.setCaja(String.valueOf(syncDTORequest.getCajaId()));
				cajaSessionInfo.setSucursal(String.valueOf(syncDTORequest.getSucursalId()));
				cajaSessionInfo.setRemoteAddr(callerIpAddress);
				cajaSessionInfo.setUserAgent(syncDTORequest.getUserAgent());
				ContextAndSessionListener.cajaSessionInfoHT.put(syncDTORequest.getSessionId(), cajaSessionInfo);
			}
			if (syncDTORequest.getLoggedIn() != null) {
				cajaSessionInfo.setLoggedIn(syncDTORequest.getLoggedIn());
			}
			cajaSessionInfo.setLastAccesedTime(System.currentTimeMillis());
		}
		IAmAliveDTOPackage r = new IAmAliveDTOPackage(1);
		return r;
	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
	

}

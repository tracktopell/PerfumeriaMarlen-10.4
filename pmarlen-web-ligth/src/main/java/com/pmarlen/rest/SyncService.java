package com.pmarlen.rest;

import com.pmarlen.backend.dao.SyncDAO;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author alfredo
 */
@Path("/syncservice/")
public class SyncService {
	@Context 
	HttpServletRequest httpRequest;

	private static final String encodingUTF8 = "UTF-8";
    
    private final static Logger logger = Logger.getLogger(SyncService.class.getName());
	
	@POST
	@Path("/sync")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=" + encodingUTF8)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] sync(SyncDTORequest syncDTORequest) throws WebApplicationException {
		logger.debug("getSyncDTOPackageZipped: syncDTORequest="+syncDTORequest);
		byte zipData[]=null;
        SyncDTOPackage s=null;
		
		String callerIpAddress = "?.?.?.?";
		if(httpRequest != null) {
			callerIpAddress = httpRequest.getRemoteAddr();
			logger.debug("* httpRequest) Caller IP = " + callerIpAddress);
		}
		
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			s = SyncDAO.getInstance().syncTransaction(syncDTORequest);
			logger.debug("->registerHello"); 
			IAmAliveService.registerHello(syncDTORequest.getiAmAliveDTORequest(), callerIpAddress);
			logger.debug("<- dto ?");
			ObjectMapper mapper = new ObjectMapper();
			ZipOutputStream zos = new ZipOutputStream(baos);
			zos.putNextEntry(new ZipEntry("data.json"));				
			byte[] jsonData=mapper.writeValueAsBytes(s);
			zos.write(jsonData);
			zos.closeEntry();
			zos.finish();				

			baos.close();
			zipData=baos.toByteArray();

			logger.debug("getSyncDTOPackageZipped: zip ContentLength="+zipData.length+" bytes, jsonData.length="+jsonData.length);
			
		} catch (Exception ex) {
			logger.error ("getSyncDTOPackageZipped", ex);
			throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
		}
		return zipData;
	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
}

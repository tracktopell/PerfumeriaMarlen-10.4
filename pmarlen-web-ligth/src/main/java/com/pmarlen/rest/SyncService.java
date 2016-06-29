package com.pmarlen.rest;

import com.pmarlen.backend.dao.CorteCajaDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.dao.SyncDAO;
import com.pmarlen.backend.model.CorteCaja;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.ESD;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import com.pmarlen.web.servlet.CajaSessionInfo;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * /rest/syncservice/sync
 * /rest/syncservice/saldoEstimado/1/2
 * /rest/syncservice/ticket/1234567890
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
		final IAmAliveDTORequest iAmAliveDTORequest = syncDTORequest.getiAmAliveDTORequest();
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
			boolean getAllData = true;
			
			logger.debug("->registerHello:");
			IAmAliveService.registerHello(iAmAliveDTORequest, callerIpAddress);
			
			CajaSessionInfo cajaSessionInfo = null;		
			cajaSessionInfo = ContextAndSessionListener.cajaSessionInfoHT.get(iAmAliveDTORequest.getSessionId());
			
			if(cajaSessionInfo.isRequestedResetAll()){
				cajaSessionInfo.setRequestedResetAll(false);
			}
			
			logger.debug("->syncTransaction:");
			s = SyncDAO.getInstance().syncTransaction(syncDTORequest,getAllData);
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

	@GET
	@Path("/saldoEstimado/{sucursalId:[0-9]+}/{caja:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + encodingUTF8)
	public Double getSaldoEstimado(@PathParam("sucursalId") String sucursalId,@PathParam("caja") String caja) throws WebApplicationException {
		Double saldoEstimado = 0.0;
		logger.debug("getSaldoEstimado: sucursalId="+sucursalId+", caja="+caja);
		CorteCaja busqueda=new CorteCaja();
		busqueda.setSucursalId(Integer.parseInt(sucursalId));
		busqueda.setCaja(Integer.parseInt(caja));
		CorteCaja ultimo=null;
		try {
			ultimo = CorteCajaDAO.getInstance().findLastAperturaBySucursalCaja(busqueda);
			Double saldoVta = null;
			Double saldoDev = null;
			if(ultimo!= null){
				saldoVta = EntradaSalidaDAO.getInstance().findSaldoEstimadoSucursalCajaVentas(ultimo.getSucursalId(), ultimo.getCaja(), ultimo.getId());
				logger.debug("getSaldoEstimado: saldoVta="+saldoVta);
				saldoDev = EntradaSalidaDAO.getInstance().findSaldoEstimadoSucursalCajaDevol (ultimo.getSucursalId(), ultimo.getCaja(), ultimo.getId());
				logger.debug("getSaldoEstimado: saldoDev="+saldoDev);

				if(saldoVta != null){
					saldoEstimado += saldoVta;
				}
				if(saldoDev != null){
					saldoEstimado -= saldoDev;
				}
			} else {
				logger.debug("getSaldoEstimado: NO HAY ULTIMO CORTE.");
			}
			logger.debug("getSaldoEstimado: saldoEstimado="+saldoEstimado);
		}catch(DAOException de){
			logger.error ("getSyncDTOPackageZipped", de);
			throw new WebApplicationException(de, Response.Status.INTERNAL_SERVER_ERROR);
		}
		return saldoEstimado;
	}
	
	@GET
	@Path("/ticket/{ticket:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + encodingUTF8)
	public ES_ESD getTicket(@PathParam("ticket") String ticket) throws WebApplicationException {
		
		ES_ESD esesd = null;
		try {
			esesd = null;
			int esId = EntradaSalidaDAO.getInstance().getIdForTicket(ticket);
			if(esId<0){
				throw new DAOException("NO EXISTRE EL TICKET:"+ticket);
			}
			EntradaSalidaQuickView entradaSalida = EntradaSalidaDAO.getInstance().findBy(new EntradaSalida(esId));
			ArrayList<EntradaSalidaDetalleQuickView> esdqvList = EntradaSalidaDAO.getInstance().findAllESDByEntradaSalida(esId);
			ES es = new ES(entradaSalida);
			List<ESD> esdList=new ArrayList<ESD>();
			int ne=0;
			for(EntradaSalidaDetalleQuickView esdqv: esdqvList){
				ne+=esdqv.getCantidad();
				esdList.add(new ESD(esdqv));
			}
			es.setnElem(ne);
			esesd = new ES_ESD(es, esdList);			
		}catch(DAOException de){
			logger.error("getTicket", de);
			throw new WebApplicationException(de, Response.Status.NOT_FOUND);
		}
		return esesd;
	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}
	
}

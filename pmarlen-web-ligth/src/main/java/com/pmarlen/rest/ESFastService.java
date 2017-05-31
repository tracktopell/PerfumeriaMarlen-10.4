package com.pmarlen.rest;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 * /rest/esfastservice/es/1/1
 * @author alfredo	
 */
@Path("/esfastservice/")
public class ESFastService {
	private static final String encodingUTF8 = "UTF-8";

	private final static Logger logger = Logger.getLogger(ESFastService.class.getName());
	
	@GET
	@Path("/es/{sucursalId:[0-9]+}/{caja:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=" + encodingUTF8)
	public ArrayList<EntradaSalida> getES(@PathParam("sucursalId") int sucursalId, @PathParam("caja") int caja) throws WebApplicationException {
		
		int tipoMov=30; boolean active=true; 
		Timestamp fechaInicial=null; Timestamp fechaFinal=null;		
		ArrayList<EntradaSalida> findFastESByPAge = null;		
		try {
			findFastESByPAge = EntradaSalidaDAO.getInstance().findFastESByPAge(tipoMov, sucursalId, caja, active, fechaInicial, fechaFinal);
		} catch(DAOException de){		
			logger.error ("info", de);
			throw new WebApplicationException(de, Response.Status.NOT_FOUND);
		} catch(Exception de){		
			logger.error ("info", de);
			throw new WebApplicationException(de, Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		return findFastESByPAge;
	}
}

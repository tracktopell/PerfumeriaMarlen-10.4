package com.pmarlen.rest;

import com.pmarlen.backend.dao.CorteCajaDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.model.CorteCaja;
import com.pmarlen.model.GeneradorDeToken;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.IAmAliveDTOPackage;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.web.servlet.CajaSessionInfo;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import javax.annotation.Resource;
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
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Logger;

/**
 * /rest/tokenservice/frase
 * /rest/tokenservice/token/123456
 * @author alfredo	
 */
@Path("/tokenservice/")
public class TokenService {
	private static final String encodingUTF8 = "UTF-8";

	private final static Logger logger = Logger.getLogger(TokenService.class.getName());

	@GET
	@Path("/frase")
	@Produces(MediaType.TEXT_PLAIN + ";charset=" + encodingUTF8)
	public String frase() throws WebApplicationException {
		GeneradorDeToken gt = new GeneradorDeToken();
		final String frase = gt.getFrase();
		logger.debug("token: frase:" + frase);
		return frase;
	}
	
	
	@GET
	@Path("/token/{frase:[0-9]+}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=" + encodingUTF8)
	public String token(@PathParam("frase") String frase) throws WebApplicationException {
		GeneradorDeToken gt = new GeneradorDeToken();
		final String token = gt.getToken(frase);
		logger.debug("token: token("+frase+"):" + token);
		return token;
	}
}

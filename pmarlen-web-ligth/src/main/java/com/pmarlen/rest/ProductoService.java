package com.pmarlen.rest;

import com.pmarlen.backend.dao.AlmacenProductoDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.model.Constants;
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
 * /rest/productoservice/info
 * /rest/productoservice/info/123456
 * @author alfredo	
 */
@Path("/productoservice/")
public class ProductoService {
	private static final String encodingUTF8 = "UTF-8";

	private final static Logger logger = Logger.getLogger(ProductoService.class.getName());
	
	@GET
	@Path("/info/{codigoBarras:[0-9]+}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=" + encodingUTF8)
	public String info(@PathParam("codigoBarras") String codigoBarras) throws WebApplicationException {
		EntradaSalidaDetalleQuickView p = null;
		String info = null;
		try {
			p = ProductoDAO.getInstance().findByCodigo(1, codigoBarras);
			if(p == null){
				throw new DAOException("NOT FOUND:"+codigoBarras);
			}
			info = "NOMBRE:"+p.getProductoNombre()+"\nPRESENTACIÓN:"+p.getProductoPresentacion()+"\nPRECIO:"+Constants.dfCurrency.format(p.getApPrecio());
		} catch(DAOException de){		
			logger.error ("info", de);
			throw new WebApplicationException(de, Response.Status.NOT_FOUND);
		} catch(Exception de){		
			logger.error ("info", de);
			throw new WebApplicationException(de, Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		
		return info;
	}
	
	@GET
	@Path("/fullInfo/{idSuc:[0-9]+}/{codigoBarras:[0-9]+}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=" + encodingUTF8)
	public String info(@PathParam("idSuc") String idSuc,@PathParam("codigoBarras") String codigoBarras) throws WebApplicationException {
		EntradaSalidaDetalleQuickView p = null;
		String info = null;
		try {
			ArrayList<InventarioSucursalQuickView> inv =AlmacenProductoDAO.getInstance().findAllBySucursal(Integer.parseInt(idSuc),codigoBarras);
			
			if(inv.size() == 0){
				throw new DAOException("NOT FOUND:"+codigoBarras);
			}
			InventarioSucursalQuickView pi = inv.get(0);
			info = "CODIGO:"+pi.getCodigoBarras()+
					"\nNOMBRE:"+pi.getNombre()+
					"\nPRESENTACIÓN:"+pi.getPresentacion()+
					"\nLINEA:"+pi.getLinea()+
					"\nMARCA:"+pi.getMarca()+
					"\nINDUSTRIA:"+pi.getIndustria()+
					"\nCONTENIDO:"+pi.getContenido()+" "+pi.getUnidadMedida()+
					"\nPRECIO:"+Constants.dfCurrency.format(pi.getA1p())+
					"\nCANTIDAD:"+pi.getA1c();
		} catch(DAOException de){		
			logger.error ("info", de);
			throw new WebApplicationException(de, Response.Status.NOT_FOUND);
		} catch(Exception de){		
			logger.error ("info", de);
			throw new WebApplicationException(de, Response.Status.INTERNAL_SERVER_ERROR);
		}
		return info;
	}
	
}

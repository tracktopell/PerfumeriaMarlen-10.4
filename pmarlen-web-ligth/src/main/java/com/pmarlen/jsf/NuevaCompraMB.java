package com.pmarlen.jsf;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.model.Constants;
import com.pmarlen.web.security.managedbean.SessionUserMB;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="nuevaCompraMB")
@SessionScoped
public class NuevaCompraMB extends PedidoVentaMB{	
	
	@ManagedProperty(value = "#{sessionUserMB}")
	private SessionUserMB sessionUserMB;
		
	public void setSessionUserMB(SessionUserMB sessionUserMB) {
		this.sessionUserMB = sessionUserMB;
	}	
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		autorizaDescuento = false;
	}

	@Override
	public String reset() {
		logger.trace("EntradaSalidaDetalleMB: rest.");
		init();
		return "/pages/nuevaDevolucion";
	}
	
	public EntradaSalida getNuevaCompra() {
		return entradaSalida;
	}

	public EntradaSalidaFooter getNuevaCompraFooter() {
		return entradaSalidaFooter;
	}

	public boolean isCrearCompra() {
		crearES=false;
		
		if(entityList!=null && entityList.size()>0 ){
			crearES=true;
		}
		
		return crearES;
	}
	
	@Override
	public void guardar() {
		logger.trace("guardar:");
		try{
			entradaSalida.setCaja(1);
			entradaSalida.setFactorIva(Constants.IVA);
			entradaSalida.setNumDeCuenta(null);
			entradaSalida.setNumeroTicket("0");
			entradaSalida.setSucursalId(sessionUserMB.getSucursalId());
			entradaSalida.setUsuarioEmailCreo(sessionUserMB.getUsuarioAuthenticated().getEmail());
			entradaSalida.setAutorizaDescuento(autorizaDescuento?1:0);
						
			EntradaSalidaDAO.getInstance().insertComra(entradaSalida,entityList);
			logger.trace("guardar:entradaSalida.id:"+entradaSalida.getId());
			
			esFinalizado = true;
			cadenaBusqueda = null;
			resultadoBusqueda = null;
			resultadoBusquedaList = null;
			conservarBusqueda = false;
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDAR",  "SE CREO LA COMPRA #"+entradaSalida.getId()) );
		}catch(DAOException de){
			logger.error(de.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"GUARDAR",  "OCURRIÓ UN ERROR AL GUARDAR.") );
		}
	}
	
	public boolean isCompraFinalizada() {
		return esFinalizado;
	}
}

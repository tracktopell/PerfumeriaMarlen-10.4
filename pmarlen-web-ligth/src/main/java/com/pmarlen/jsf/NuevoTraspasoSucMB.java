package com.pmarlen.jsf;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.dao.SucursalDAO;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.model.Constants;
import com.pmarlen.web.security.managedbean.SessionUserMB;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name="nuevoTraspasoSucMB")
@SessionScoped
public class NuevoTraspasoSucMB extends PedidoVentaMB{	
	
	@ManagedProperty(value = "#{sessionUserMB}")
	private SessionUserMB sessionUserMB;
		
	
	private boolean hayCambios;
	
	private boolean verificable;
	
	private boolean surtible;
	
	public void setSessionUserMB(SessionUserMB sessionUserMB) {
		this.sessionUserMB = sessionUserMB;
	}	
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		autorizaDescuento = false;
		hayCambios = false;
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

	public boolean isCrearTraspaso() {
		crearES=false;
		
		if(entityList!=null && entityList.size()>0 && sucursalId!=0){
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
			entradaSalida.setSucursalIdTraDes(sucursalId);
			entradaSalida.setSucursalIdTraOri(1);
			EntradaSalidaDAO.getInstance().insertTraspaso(entradaSalida,entityList);
			logger.debug("guardar:entradaSalida.id:"+entradaSalida.getId()+", ["+entradaSalida.getSucursalIdTraOri()+"] -> ["+entradaSalida.getSucursalIdTraDes()+"]");
			
			esFinalizado = true;
			cadenaBusqueda = null;
			resultadoBusqueda = null;
			resultadoBusquedaList = null;
			conservarBusqueda = false;
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDAR",  "SE CREO EL TRASPASO #"+entradaSalida.getId()) );
		}catch(DAOException de){
			logger.error(de.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"GUARDAR",  "OCURRIÓ UN ERROR AL GUARDAR.") );
		}
	}
	
	public boolean isCompraFinalizada() {
		return esFinalizado;
	}
	
	//--------------------------------------------------------------------------
	private int		sucursalId = 0;	
	
	private ArrayList<SelectItem> sucursalesList;
		public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public int getSucursalId() {
		return sucursalId;
	}

	public ArrayList<SelectItem> getSucursalesList() {
		if(sucursalesList==null){
			sucursalesList = new ArrayList<SelectItem>();
			List<Sucursal> sucursales=null;
			try {
				sucursales=(List<Sucursal>) SucursalDAO.getInstance().findAll();
			}catch(DAOException de){
				logger.error(de.getMessage());			
			}
			if(sucursales != null){
				sucursalesList.add(new SelectItem(0,"--SELECCIONE--"));			
				for(Sucursal s:sucursales){
					if(s.getIdPadre() != null){						
						sucursalesList.add(new SelectItem(s.getId(),"["+s.getClave()+"]"+s.getNombre()));			
					}
				}
			}
		}
		return sucursalesList;
	}
	
	public void onSucursalChange() {
		logger.info("onSucursalChange:sucursalId="+sucursalId);
	}

	public boolean isHayCambios() {
		return hayCambios;
	}

	public boolean isVerificable() {
		return verificable;
	}

	public boolean isSurtible() {
		return surtible;
	}
	
}

package com.pmarlen.jsf;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.model.Constants;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="editarTraspasoSucMB")
@SessionScoped
public class EditarTraspasoSucMB extends EditarPedidoVentaMB{
		
	@PostConstruct
	public void init() {
		super.init();
		logger.trace("OK init");
	}

	
	public boolean isCrearDevolucion() {
		crearEntradaSalida=false;
		
		if(entityList!=null && entityList.size()>0 &&
				entradaSalida.getClienteId()!=null && entradaSalida.getClienteId() > 0 && clienteSeleccionado!=null ){
			crearEntradaSalida=true;
		}
		
		return crearEntradaSalida;
	}
	
	@Override
	public String editar(int devolucionID){
		logger.trace("--------------------------------<<<<<< inicio Editar");
		validarSiEstabaEditandoOtro("PEDIDO");
		logger.trace("devolucionID="+devolucionID);		
		try {
			EntradaSalida es4Edit = new EntradaSalida(devolucionID);
			entradaSalida = EntradaSalidaDAO.getInstance().findBy(es4Edit);
			logger.trace("devolucion="+entradaSalida);
			entityList = EntradaSalidaDAO.getInstance().findAllESDByEntradaSalida(devolucionID);
			EntradaSalidaDAO.getInstance().actualizaCantidadPendienteParaOtrosES(entityList);

			logger.trace("entityList:--------->>>");
			for(EntradaSalidaDetalleQuickView pv:entityList){
				logger.trace("\teditar: rowId="+pv.getRowId()+": "+pv.getCantidad()+" X ["+pv.getProductoCodigoBarras()+"]@"+pv.getAlmacenId());
			}
			logger.trace("entityList:<<<---------");
		}catch(DAOException de){
			logger.error(de.getMessage());
			entradaSalida = new EntradaSalidaQuickView();
			entradaSalida.setId(0);
			entradaSalidaFooter= new EntradaSalidaFooter();
			entityList = new ArrayList<EntradaSalidaDetalleQuickView>();
		}
		tipoAlmacen = Constants.ALMACEN_PRINCIPAL;
		cantidadAgregarBusqueda = 1;
		cantidadAgregarCodigo   = 1;
		
		//getClientesList();
		onClienteListChange();
		
		getFormaDePagoList();
		onFormaDePagoListChange();
		
		getMetodoDePagoList();
		onMetodoDePagoListChange();
		
		getDescuentoEspacialList();
		
		crearEntradaSalida = false;	
		cadenaBusqueda = null;
		resultadoBusqueda = null;
		resultadoBusquedaList = null;
		conservarBusqueda = true;
		
		actualizarTotales();
		hayCambios = false;
		logger.trace("fin Editar");
		logger.trace("-------------------------------->>>>>>> fin Editar");
		return "/pages/editarDevolucion";
	}

	public EntradaSalidaQuickView getTraspaso() {
		return entradaSalida;
	}
	
	public void guardar() {
		try{						
			logger.trace("entradaSalida.id:"+entradaSalida.getId());
			logger.trace("entradaSalida.cfdVentaId:"+entradaSalida.getCfdId());
			EntradaSalidaDAO.getInstance().update(entradaSalida,entityList,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("OK Guardar.");
			
			reset();
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDAR",  "SE ACTUALIZÓ CORRECTAMENTE EL TRASPASO #"+entradaSalida.getId()+".") );
		}catch(Exception e){
			logger.error("guardar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"GUARDAR",  "HUBO UN ERROR AL GUARDAR.") );
		}

	}
	
	public void verificar() {
		try{			
			logger.trace("entradaSalida.id:"+entradaSalida.getId());
			EntradaSalidaDAO.getInstance().verificar(entradaSalida,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("OK Verificar.");
			
			reset();
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"VERIFICAR",  "SE VERIFICÓ CORRECTAMENTE EL TRASPASO #"+entradaSalida.getId()+".") );
		}catch(Exception e){
			logger.error("verificar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"VERFICAR",  "HUBO UN ERROR AL VERIFICAR.") );
		}		
	}
	
	public void surtir() {
		try{			
			logger.trace("entradaSalida.id:"+entradaSalida.getId());
			EntradaSalidaDAO.getInstance().surtir(entradaSalida,entityList,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("OK Surtir.");

			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"SURTIR",  "SE SURTIÓ CORRECTAMENTE EL TRASPASO #"+entradaSalida.getId()+".") );			
			reset();
		}catch(Exception e){
			logger.error("verificar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"SURTIR",  "HUBO UN ERROR AL SURTIR.") );
		}		
	}
	
	public void cancelar() {
		try{			
			logger.trace("entradaSalida.id:"+entradaSalida.getId());
			//EntradaSalidaDAO.getInstance().surtir(entradaSalida,entityList,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("CANCELAR NO IMPLEMENTADO, solo resetea");
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"CANCELAR",  "CANCELAR PENDIENTE DEVOLUCIÓN #"+entradaSalida.getId()+".") );			
			reset();
		}catch(Exception e){
			logger.error("verificar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"SURTIR",  "HUBO UN ERROR AL CANCELAR.") );
		}		
	}
	
	public void cancelarCambios() {
		try{			
			logger.trace("entradaSalida.id:"+entradaSalida.getId());
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"CANCELAR CAMBIOS",  "SE CANCELARON LOS CAMBIOS Y RECARGÓ EL TRASPASO #"+entradaSalida.getId()+".") );
			reset();
		}catch(Exception e){
			logger.error("verificar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"CANCELAR CAMBIOS",  "HUBO UN ERROR AL CANCELAR CAMBIOS.") );
		}		
	}

	public EntradaSalidaFooter getDevolucionFooter() {
		return super.getPedidoVentaFooter(); //To change body of generated methods, choose Tools | Templates.
	}
	
	public boolean isModificable(){
		return entradaSalida!=null && entradaSalida.getEstadoId() < Constants.ESTADO_VERIFICADO;
	}
	
	public int getAlturaExtraTabla() {
		logger.trace("getAlturaExtraTabla: isVerificable()="+isVerificable()+", isSurtible()="+isSurtible());
		if(isModificable()){
			logger.trace("getAlturaExtraTabla: 0");
			return 0;
		} else {
			logger.trace("getAlturaExtraTabla: 180");
			return 180;
		}
	}
		
}
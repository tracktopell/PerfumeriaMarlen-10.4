package com.pmarlen.jsf;

import com.pmarlen.backend.dao.ClienteDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.dao.EntradaSalidaDetalleDAO;
import com.pmarlen.backend.dao.FormaDePagoDAO;
import com.pmarlen.backend.dao.MetodoDePagoDAO;
import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.dao.SucursalDAO;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.FormaDePago;
import com.pmarlen.backend.model.MetodoDePago;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.quickviews.ClienteQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.reports.GeneradorImpresionPedidoVenta;
import com.pmarlen.model.Constants;
import com.pmarlen.web.common.view.messages.Messages;
import com.pmarlen.web.security.managedbean.SessionUserMB;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.print.attribute.standard.Severity;
import javax.servlet.ServletContext;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="editarPedidoVentaMB")
@SessionScoped
public class EditarPedidoVentaMB{	
	protected static transient Logger logger = Logger.getLogger(EditarPedidoVentaMB.class.getName());
	protected List<SelectItem> resultadoBusquedaList;
	protected static final int LONG_MIN_DESC_CTE = 60;
	protected static List<SelectItem> tipoAlmacenList;
	protected ArrayList<EntradaSalidaDetalleQuickView> entityList;
	protected EntradaSalidaDetalleQuickView selectedEntity;
	protected EntradaSalidaQuickView entradaSalida;
	protected EntradaSalidaFooter entradaSalidaFooter;
	protected ArrayList<EntradaSalidaDetalleQuickView> resultadoBusqueda;
	protected EntradaSalidaDetalleQuickView resultadoBusquedaSI;
	protected StreamedContent file;
	
	protected String cadenaBusqueda;
	protected int tipoAlmacen;
	protected String resultadoBusquedaSelected;
	protected int cantidadAgregarBusqueda;
	boolean conservarBusqueda;
	
	protected int cantidadAgregarCodigo;
	protected String codigo;
	protected boolean autorizaDescuento = true;
	protected boolean tablaExpandida = false;
	protected boolean tableDraggableEnabled = false;
	protected boolean crearEntradaSalida;
	protected boolean hayCambios = false;
	@ManagedProperty(value = "#{sessionUserMB}")
	protected SessionUserMB sessionUserMB;
	protected int ultimoESId=0;
	protected int digifactWSState=0;
	
	public void setSessionUserMB(SessionUserMB sessionUserMB) {
		this.sessionUserMB = sessionUserMB;
	}
	
	@PostConstruct
	public void init() {
		entradaSalida = new EntradaSalidaQuickView();
		entradaSalidaFooter= new EntradaSalidaFooter();
		entityList = new ArrayList<EntradaSalidaDetalleQuickView>();
		tipoAlmacen = Constants.ALMACEN_PRINCIPAL;
		cantidadAgregarBusqueda = 1;
		cantidadAgregarCodigo   = 1;
		
		clienteSeleccionado = null;
		crearEntradaSalida = false;	
		cadenaBusqueda = null;
		resultadoBusqueda = null;
		resultadoBusquedaList = null;
		conservarBusqueda = true;
		hayCambios = false;
		autorizaDescuento = true;
		tablaExpandida = false;
		tableDraggableEnabled = false;
		ultimoESId=0;
		digifactWSState=0;
		logger.trace("OK init");
	}

	public String editar(int pedidoVentaID){
		logger.trace("--------------------------------<<<<<< inicio Editar");
		validarSiEstabaEditandoOtro("PEDIDO");
		logger.trace("editar:pedidoVentaID="+pedidoVentaID);		
		try {
			EntradaSalida es4Edit = new EntradaSalida(pedidoVentaID);
			entradaSalida = EntradaSalidaDAO.getInstance().findBy(es4Edit);
			logger.trace("editar:vvpedidoVenta="+entradaSalida);
			entityList = EntradaSalidaDAO.getInstance().findAllESDByEntradaSalida(pedidoVentaID);
			EntradaSalidaDAO.getInstance().actualizaCantidadPendienteParaOtrosES(entityList);

			logger.trace("editar:entityList:--------->>>");
			for(EntradaSalidaDetalleQuickView pv:entityList){
				logger.trace("\t->editar:editar: rowId="+pv.getRowId()+": "+pv.getCantidad()+" X ["+pv.getProductoCodigoBarras()+"]@"+pv.getAlmacenId());
			}
			logger.trace("editar:entityList:<<<---------");
		}catch(DAOException de){
			logger.error("editar:",de);
			entradaSalida = new EntradaSalidaQuickView();
			entradaSalida.setId(0);
			entradaSalidaFooter= new EntradaSalidaFooter();
			entityList = new ArrayList<EntradaSalidaDetalleQuickView>();
		}
		tipoAlmacen = Constants.ALMACEN_PRINCIPAL;
		cantidadAgregarBusqueda = 1;
		cantidadAgregarCodigo   = 1;
		digifactWSState=0;
		
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
		ultimoESId=pedidoVentaID;
		return "/pages/editarPedidoVenta";
	}
	
	protected void validarSiEstabaEditandoOtro(String tipoES){
		if(ultimoESId != 0 && hayCambios){
			logger.warn("Estaba editando #"+entradaSalida.getId());
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"ADVERTENCIA AL EDITAR ",
					"ESTABA EDITANDO "+tipoES+" #"+entradaSalida.getId()+", ¡ Y NO GUARDO !, AL EDITAR ESTE SE PERDIERON LOS CAMBIOS EN LA EDICÓN ANTERIOR") );		
		}
	}
	
	
	public void actualizarCantidadesStockTiempoReal(){
		try {
			EntradaSalidaDAO.getInstance().actualizaCantidadPendienteParaOtrosES(entityList);
		}catch(Exception e){
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ACTUALIZAR INFORMACIÓN",  "ERROR AL ACTUALZIAR LAS CANTIDADES DE ALMACEN EN TIEMPO REAL") );		
		}
	}

	public String reset() {
		logger.trace("EntradaSalidaDetalleMB: rest.");
		hayCambios=false;
		editar(this.entradaSalida.getId());
		prepareDownload();
		return "/pages/cliente";
	}
	
	public EntradaSalidaQuickView getPedidoVenta() {
		return entradaSalida;
	}

	public EntradaSalidaFooter getPedidoVentaFooter() {
		return entradaSalidaFooter;
	}
	
	
	public List<EntradaSalidaDetalleQuickView> getEntityList() {
		return entityList;
	}
	
	public int getSizeList(){
		if(entityList==null){
			return 0;
		}
		return entityList.size();
	}


	public int getTipoAlmacen() {
		return tipoAlmacen;
	}

	public String getCadenaBusqueda() {
		return cadenaBusqueda;
	}

	public void setCadenaBusqueda(String cadenaBusqueda) {
		this.cadenaBusqueda = cadenaBusqueda;
	}
	
	public boolean isPuedeBuscar(){
		if(cadenaBusqueda!=null && cadenaBusqueda.trim().length()>3) {
			return true;
		} else {
			return false;
		}
	}
	

	public void buscarXBoton() {
		buscarXCadena();
	}

	public void cadenaBusquedaChanged(ValueChangeEvent e) {
		logger.trace("cadenaBusquedaChanged: e:"+e.getNewValue());
	}
	public void codigoChanged(ValueChangeEvent e) {
		logger.trace("codigoChanged: e:"+e.getNewValue()+", cantidadAgregarCodigo="+cantidadAgregarCodigo);
	}
	
	public void buscarXCadena() {
		logger.trace("buscarXCadena:tipoAlmacen="+tipoAlmacen+", cadenaBusqueda="+cadenaBusqueda);
		if(cadenaBusqueda.trim().length()>3) {	
			try {
				boolean modoExclusivo = false;
				if(cadenaBusqueda.matches("([ ]+)*\\((.)+\\)([ ]+)*")){
					modoExclusivo = true;
				}
				String cadenaBusquedaQuery = cadenaBusqueda.replace("(", "").replace(")", "");
				
				resultadoBusqueda = ProductoDAO.getInstance().findAllByDesc(this.tipoAlmacen, cadenaBusquedaQuery,modoExclusivo);
				resultadoBusquedaSI = null;
				if(resultadoBusqueda != null && resultadoBusqueda.size()>0){
					FacesContext context = FacesContext.getCurrentInstance();         
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"BUSCAR PRODUCTOS",  "SE ENCONTRARÓN "+resultadoBusqueda.size()+" PRODUCTO"+(resultadoBusqueda.size()>1?"S":"") ));

					logger.trace("buscar:findAllExclusiveByDesc:OK, resultadoBusqueda.size()="+resultadoBusqueda.size());
					resultadoBusquedaSI = resultadoBusqueda.get(0);
				} else {
					FacesContext context = FacesContext.getCurrentInstance();         
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"BUSACAR PRODUCTOS",  "NO SE SE ENCONTRÓ EL NADA CON ESA DESCRIPCIÓN.") );		
				}
			}catch(DAOException de){
				logger.error(de.getMessage());
				FacesContext context = FacesContext.getCurrentInstance();         
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"BUSACAR PRODUCTOS",  "OCURRIÓ UN ERROR AL BUSCAR") );		
			}
			
			resultadoBusquedaList = null;
			cantidadAgregarBusqueda = 1;	
			cantidadAgregarCodigo   = 1;
		}
	}
	
	public void buscarXCodigo() {
		logger.trace("buscarXCodigo:cantidadAgregarCodigo="+cantidadAgregarCodigo+", codigo="+codigo);
		EntradaSalidaDetalleQuickView dvpAdd = null;
		try {
			dvpAdd = ProductoDAO.getInstance().findByCodigo(tipoAlmacen,codigo);

			logger.trace("buscarXCodigo:dvpAdd="+dvpAdd);

			if(dvpAdd != null) {
				dvpAdd.setCantidad(cantidadAgregarCodigo);
				logger.trace("buscarXCodigo:OK +"+cantidadAgregarCodigo+" x "+dvpAdd);
				entityList.add(dvpAdd);

				FacesContext context = FacesContext.getCurrentInstance();         
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"AGREGAR PRODUCTO",  "SE ENCONTRÓ Y SE AGREGÓ "+cantidadAgregarCodigo+" x ["+codigo+"] AL DETALLE.") );

				if(!conservarBusqueda){
					resultadoBusqueda = null;
					resultadoBusquedaList = null;
					cadenaBusqueda = null;
				}
				codigo = "";
				cantidadAgregarBusqueda = 1;
				cantidadAgregarCodigo   = 1;
				actualizarTotales();
			} else {
				FacesContext context = FacesContext.getCurrentInstance();         
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"AGREGAR PRODUCTO",  "NO SE SE ENCONTRÓ EL CODIGO ["+codigo+"].") );

				codigo = "";
				cantidadAgregarBusqueda = 1;
				cantidadAgregarCodigo   = 1;
			}
		}catch(DAOException de){
			logger.error(de.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"AGREGAR PRODUCTO",  "OCURRIÓ UN ERROR AL BUSCAR.") );

			codigo = "";
			cantidadAgregarBusqueda = 1;
			cantidadAgregarCodigo   = 1;
		}
	}
	
	public void cantidadDetalleCambio(long cambioRowId){
		logger.trace("cantidadDetalleCambio:cambioRowId="+cambioRowId);
		actualizarTotales();
	}
	
	public void cantidadDetalleChanged(ValueChangeEvent event) {
		int cantidadChanged = (Integer) event.getNewValue();
		logger.trace("updateCantidad:cantidadChanged="+cantidadChanged);
		actualizarTotales();
	}
	
	public void deleteRow(long deleteRowId){
		logger.trace("deleteRow:deleteRowId="+deleteRowId);
		int i=0;
		int indexDelete=-1;
		int cantidadEliminada=0;
		String codigoEliminado="";
		for(EntradaSalidaDetalleQuickView pv:entityList){
			logger.trace("deleteRow:\tdelete? "+pv.getRowId()+"=="+deleteRowId);
			if(pv.getRowId()==deleteRowId){
				cantidadEliminada= pv.getCantidad();
				codigoEliminado = pv.getProductoCodigoBarras();
				indexDelete = i;
				break;
			}
			i++;
		}
		if(indexDelete >=0) {
			entityList.remove(indexDelete);
			logger.trace("deleteRow:delete index:"+indexDelete);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"ELIMINAR PRODUCTO",  "SE ELIMINÓ CORRECTAMENTE "+cantidadEliminada+" x ["+codigoEliminado+"].") );
		
			if(!conservarBusqueda){
				resultadoBusqueda = null;
				resultadoBusquedaList = null;
				cadenaBusqueda = null;
			}
			codigo = "";
			cantidadAgregarBusqueda = 1;
			cantidadAgregarCodigo   = 1;
			actualizarTotales();
		}
	}
		
	public EntradaSalidaDetalleQuickView getResultadoBusquedaSI(){
		return resultadoBusquedaSI;
	}
		

	public void setResultadoBusquedaSelected(String resultadoBusquedaSelected) {
		logger.trace("setResultadoBusquedaSelected("+resultadoBusquedaSelected+")");
		this.resultadoBusquedaSelected = resultadoBusquedaSelected;
		
		for(EntradaSalidaDetalleQuickView x:resultadoBusqueda){
			if(x.getProductoCodigoBarras().equals(resultadoBusquedaSelected)){
				resultadoBusquedaSI = x;
				break;
			}
		}		
	}

	public String getResultadoBusquedaSelected() {
		return resultadoBusquedaSelected;
	}

	public void setCantidadAgregarBusqueda(int cantidadAgregarBusqueda) {
		this.cantidadAgregarBusqueda = cantidadAgregarBusqueda;
	}
	

	public int getCantidadAgregarBusqueda() {
		return cantidadAgregarBusqueda;
	}

	public boolean isConservarBusqueda() {
		return conservarBusqueda;
	}
	
	public void setConservarBusqueda(boolean conservarBusqueda){
		this.conservarBusqueda = conservarBusqueda;
	}
	
	public void conservarBusquedaChanged(){
		logger.trace("conservarBusquedaChanged:conservarBusqueda="+conservarBusqueda);
	}
	
	public void onRowReorder(ReorderEvent event) {
		logger.trace("onRowReorder:From: " + event.getFromIndex() + ", To:" + event.getToIndex());
		int i=0;
		for(EntradaSalidaDetalleQuickView d:entityList){
			logger.trace("onRowReorder["+(i++)+"]:\t + "+d.getCantidad()+" ["+d.getProductoCodigoBarras()+"]@"+d.getAlmacenId());
		}
		hayCambios = true;
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "RENGLÓN MOVIDO EN DETALLE", "DE " + (event.getFromIndex()+1) + " A " + (event.getToIndex()+1));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public List<SelectItem> getResultadoBusqueda() {
		if (resultadoBusquedaList == null) {
			resultadoBusquedaList = new ArrayList<SelectItem>();
			if(resultadoBusqueda != null){
				for(EntradaSalidaDetalleQuickView pv:resultadoBusqueda){
					resultadoBusquedaList.add(new SelectItem(pv.getProductoCodigoBarras(),pv.toStringShorten()));			
				}
			}			
		}
		return resultadoBusquedaList;
	}
	
	ArrayList<ClienteQuickView> clientes;
//	ArrayList<SelectItem> clientesList;
	Cliente clienteSeleccionado;

	public ArrayList<ClienteQuickView> getClientes() {
		try {
			clientes = ClienteDAO.getInstance().findAll();
		}catch(DAOException de){
			logger.error(de.getMessage());
			clientes = new ArrayList<ClienteQuickView>();
		}
		return clientes;
	}
		
//	public List<SelectItem> getClientesList() {
//		if(clientesList == null){
//			clientesList = new ArrayList<SelectItem>();
//			clientesList.add(new SelectItem(0,"--SELECCIONE--"));
//			
//		}
//		return clientesList;
//	}

	public int getCantidadAgregarCodigo() {
		return cantidadAgregarCodigo;
	}

	public void setCantidadAgregarCodigo(int cantidadAgregarCodigo) {
		logger.trace("setCantidadAgregarCodigo:cantidadAgregarCodigo("+this.cantidadAgregarCodigo+")="+cantidadAgregarCodigo);
		this.cantidadAgregarCodigo = cantidadAgregarCodigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void agregarCodigo() {
		logger.trace("agregarCodigo:cantidadAgregarCodigo="+cantidadAgregarCodigo+", codigo="+codigo);
		
		EntradaSalidaDetalleQuickView dvpAdd = null;
		try {
			dvpAdd = ProductoDAO.getInstance().findByCodigo(tipoAlmacen,codigo);
			if(dvpAdd != null){
				logger.trace("agregarCodigo:dvpAdd="+dvpAdd);

				logger.trace("agregarCodigo:OK +"+cantidadAgregarCodigo+" x "+dvpAdd);

				dvpAdd.setCantidad(cantidadAgregarCodigo);

				entityList.add(dvpAdd);

				FacesContext context = FacesContext.getCurrentInstance();         
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"AGREGAR PRODUCTO",  "SE ENCONTRÓ Y SE AGREGÓ "+cantidadAgregarCodigo+" x ["+codigo+"] AL DETALLE.") );

				if(!conservarBusqueda){
					resultadoBusqueda = null;
					resultadoBusquedaList = null;
					cadenaBusqueda = null;
				}
				codigo = "";
				cantidadAgregarBusqueda = 1;
				cantidadAgregarCodigo   = 1;
				hayCambios = true;
				actualizarTotales();
			} else {
				FacesContext context = FacesContext.getCurrentInstance();         
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"AGREGAR PRODUCTO",  "NO SE ENCONTRÓ ["+codigo+"] ") );

				if(!conservarBusqueda){
					resultadoBusqueda = null;
					resultadoBusquedaList = null;
					cadenaBusqueda = null;
				}
				codigo = "";
				cantidadAgregarBusqueda = 1;
				cantidadAgregarCodigo   = 1;

			}
		}catch(DAOException de){
			logger.error(de.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"AGREGAR PRODUCTO",  "OCURRIÓ UN ERROR AL BUSCAR.") );

			codigo = "";
			cantidadAgregarBusqueda = 1;
			cantidadAgregarCodigo   = 1;
		}
	}
	
	public void actualizarTotales(){
		logger.trace("actualizarTotales, forzar hay cambio");
		hayCambios=true;
		entradaSalidaFooter.calculaParaFacturaTotalesDesde(entradaSalida, entityList);
	}
	
	public void actualizarTabla(){
		logger.trace("actualizarTabla");
		actualizarCantidadesStockTiempoReal();
		hayCambios=true;
		entradaSalidaFooter.calculaParaFacturaTotalesDesde(entradaSalida, entityList);
	}

	public void onClienteListChange() {
		logger.trace("onClienteListChange:clienteId="+entradaSalida.getClienteId());
		clienteSeleccionado = null;
		for(Cliente c:getClientes()){
			if(c.getId().equals(entradaSalida.getClienteId())){
				clienteSeleccionado = c;
				hayCambios = true;
				break;
			}
		}
	}
	
	public void seleccionaCliente(int clienteIdChoiced){
		logger.trace("seleccionaCliente:clienteIdChoiced="+clienteIdChoiced);
		for(Cliente c:getClientes()){
			if(c.getId().equals(clienteIdChoiced)){
				entradaSalida.setClienteId(c.getId());
				entradaSalida.setNumDeCuenta(c.getNumCuenta());
				clienteSeleccionado = c;
				hayCambios = true;
				break;
			}
		}
	}
	
	public List<SelectItem> getFormaDePagoList() {
		ArrayList<SelectItem> formaDePagoList = new ArrayList<SelectItem>();
		ArrayList<FormaDePago> formaDePagos = null;
		try {
			formaDePagos = FormaDePagoDAO.getInstance().findAll();
		}catch(DAOException de){
			logger.error(de.getMessage());			
		}
		if(formaDePagos != null){
			formaDePagoList.add(new SelectItem(0,"--SELECCIONE--"));			
			for(FormaDePago fp:formaDePagos){
				formaDePagoList.add(new SelectItem(fp.getId(),fp.getDescripcion()));			
			}
		}
		return formaDePagoList;
	}

	public void onFormaDePagoListChange() {
		logger.trace("onFormaDePagoListChange:entradaSalida.getFormaDePagoId()="+entradaSalida.getFormaDePagoId());
		hayCambios = true;
	}
	
	public List<SelectItem> getMetodoDePagoList() {
		ArrayList<SelectItem> metodoDePagoList = new ArrayList<SelectItem>();
		ArrayList<MetodoDePago> metodoDePagos = null;
		try {
			metodoDePagos = MetodoDePagoDAO.getInstance().findAll();
		}catch(DAOException de){
			logger.error(de.getMessage());
		}
		if(metodoDePagos != null){
			metodoDePagoList.add(new SelectItem(0,"--SELECCIONE--"));			
			for(MetodoDePago fp:metodoDePagos){
				metodoDePagoList.add(new SelectItem(fp.getId(),fp.getDescripcion()));			
			}
		}
		return metodoDePagoList;
	}

	public void onMetodoDePagoListChange() {
		logger.trace("onMetodoDePagoListChange:entradaSalida.getMetodoDePagoId()="+entradaSalida.getMetodoDePagoId());
		hayCambios = true;
	}
	
	protected ArrayList<SelectItem> descuentoEspecialList;
	public List<SelectItem> getDescuentoEspacialList() {
		if(descuentoEspecialList == null){
			descuentoEspecialList = new ArrayList<SelectItem>();
			Iterator<Integer> descuentosIterator = Constants.descuentos.keySet().iterator();
			while(descuentosIterator.hasNext()){
				Integer dn = descuentosIterator.next();
				descuentoEspecialList.add(new SelectItem(dn,Constants.descuentos.get(dn)));
			}		
		}
		return descuentoEspecialList;
	}

	public void onDescuentoEspecialListChange() {
		logger.trace("onDescuentoEspecialListChange:PorcentajeDescuentoExtra="+entradaSalida.getPorcentajeDescuentoExtra());
		actualizarTotales();
	}

	public void setTablaExpandida(boolean tablaExpandida) {
		this.tablaExpandida = tablaExpandida;
	}

	public boolean isTablaExpandida() {
		return tablaExpandida;
	}

	public void expandirTabla() {
		this.tablaExpandida = true;
	}

	public void contraerTabla() {
		this.tablaExpandida = false;
	}

	public boolean isTableDraggableEnabled() {
		return tableDraggableEnabled;
	}
	
	public void activarMover() {
		this.tableDraggableEnabled = true;
		FacesContext context = FacesContext.getCurrentInstance();         
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"OPCIONES DE TABLA",  "AHORA PUEDE ARRASTRAR LOS RENGLONES PARA MOVER ENTRE ELEMENTOS DEL DETALLE") );			
	}

	public void desactivarMover() {
		this.tableDraggableEnabled = false;
		FacesContext context = FacesContext.getCurrentInstance();         
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"OPCIONES DE TABLA",  "AHORA PUEDE SELECCIONAR EL TEXTO DE LOS RENGLONES DEL DETALLE DEL LOS ELEMENTOS DEL DETALLE") );			
	}

	public void comentariosChanged() {
		logger.trace("comentariosChanged:comentarios="+entradaSalida.getComentarios());		
		hayCambios = true;
	}

	public void onResultadoBusquedaChange() {
		logger.trace("onResultadoBusquedaChange:resultadoBusquedaSelected="+resultadoBusquedaSelected);
	}
	
	public void agregarSeleccionadoDeBusqueda() {
		logger.trace("agregarSeleccionadoDeBusqueda:"+cantidadAgregarBusqueda+" x resultadoBusquedaSelected="+resultadoBusquedaSelected);
		EntradaSalidaDetalleQuickView dvpAdd=null;
		for(EntradaSalidaDetalleQuickView pv:resultadoBusqueda){
			if(pv.getProductoCodigoBarras().equals(resultadoBusquedaSelected)){
				try {
					dvpAdd = (EntradaSalidaDetalleQuickView) BeanUtils.cloneBean(pv);
				} catch (Exception ex) {
					logger.error("UPS, no se pudede clonar", ex);
				}
				dvpAdd.setRowId(System.currentTimeMillis());
				dvpAdd.setAlmacenId(tipoAlmacen);
				dvpAdd.setCantidad(cantidadAgregarBusqueda);
				break;
			}
		}
		if(dvpAdd != null) {
			logger.trace("agregarSeleccionadoDeBusqueda:OK +"+cantidadAgregarBusqueda+" x "+dvpAdd);
			entityList.add(dvpAdd);
			
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"AGREGAR PRODUCTO",  "SE AGREGÓ "+cantidadAgregarBusqueda+" x ["+resultadoBusquedaSelected+"] AL DETALLE.") );
			
			if(!conservarBusqueda){
				resultadoBusqueda = null;
				resultadoBusquedaList = null;
				cadenaBusqueda = null;
			}
			cantidadAgregarBusqueda = 1;
			cantidadAgregarCodigo   = 1;
			actualizarTotales();
			
		}
	}
	
	public void setTipoAlmacen(int tipoAlmacen) {
		logger.trace("setTipoAlmacen:tipoAlmacen="+tipoAlmacen);
		this.tipoAlmacen = tipoAlmacen;
	}

	
	public void onTipoAlmacenChange() {
		logger.trace("onTipoAlmacenChange:tipoAlmacen="+tipoAlmacen);
		cantidadAgregarBusqueda = 1;
		cantidadAgregarCodigo   = 1;
		cadenaBusqueda ="";
		resultadoBusqueda = null;
		resultadoBusquedaList = null;		
	}

	public List<SelectItem> getTipoAlmacenList() {
		if (tipoAlmacenList == null) {

			tipoAlmacenList = new ArrayList<SelectItem>();
			Iterator<Integer> almacentesIt = Constants.tipoAlmacen.keySet().iterator();
			while(almacentesIt.hasNext()){
				Integer almacenId = almacentesIt.next();
				tipoAlmacenList.add(new SelectItem(almacenId, Constants.tipoAlmacen.get(almacenId)));
			}
		}

		return tipoAlmacenList;
	}	

	
	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public boolean isCrearPedido() {
		crearEntradaSalida=false;
		
		if(entityList!=null && entityList.size()>0 &&
				entradaSalida.getClienteId()!=null && entradaSalida.getClienteId() > 0 && clienteSeleccionado!=null &&
				entradaSalida.getFormaDePagoId()!=null && entradaSalida.getFormaDePagoId()> 0 &&
				entradaSalida.getMetodoDePagoId()!=null && entradaSalida.getMetodoDePagoId()> 0 ){
			crearEntradaSalida=true;
		}
		
		return crearEntradaSalida;
	}
	
	protected void validacion(){
		logger.trace("validacion");
		
	}
	
	public void guardar() {
		try{						
			logger.trace("guardar:pedidoVenta.id:"+entradaSalida.getId());
			logger.trace("guardar:pedidoVenta.cfdVentaId:"+entradaSalida.getCfdId());
			EntradaSalidaDAO.getInstance().update(entradaSalida,entityList,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("guardar:OK Guardar.");
			
			reset();
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDAR",  "SE ACTUALIZÓ CORRECTAMENTE EL PEDIDO #"+entradaSalida.getId()+".") );
		}catch(Exception e){
			logger.error("guardar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"GUARDAR",  "HUBO UN ERROR AL GUARDAR.") );
		}

	}
	
	public void verificar() {
		try{			
			logger.trace("verificar:pedidoVenta.id:"+entradaSalida.getId());
			EntradaSalidaDAO.getInstance().verificar(entradaSalida,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("verificar:OK Verificar.");
			
			reset();
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"VERIFICAR",  "SE VERIFICÓ CORRECTAMENTE EL PEDIDO #"+entradaSalida.getId()+".") );
		}catch(Exception e){
			logger.error("verificar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"VERFICAR",  "HUBO UN ERROR AL VERIFICAR.") );
		}		
	}
	
	public void surtir() {
		try{			
			logger.trace("verificar:pedidoVenta.id:"+entradaSalida.getId());
			EntradaSalidaDAO.getInstance().surtir(entradaSalida,entityList,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("verificar:OK Surtir.");

			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"SURTIR",  "SE SURTIÓ CORRECTAMENTE EL PEDIDO #"+entradaSalida.getId()+".") );			
			reset();
		}catch(Exception e){
			logger.error("surtir: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"SURTIR",  "HUBO UN ERROR AL SURTIR.") );
		}		
	}
	
	public void cancelar() {
		try{			
			logger.trace("cancelar:pedidoVenta.id:"+entradaSalida.getId());
			//EntradaSalidaDAO.getInstance().surtir(pedidoVenta,entityList,sessionUserMB.getUsuarioAuthenticated());
			logger.trace("cancelar:CANCELAR NO IMPLEMENTADO, solo resetea");
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"CANCELAR",  "CANCELAR PENDIENTE PEDIDO #"+entradaSalida.getId()+".") );			
			reset();
		}catch(Exception e){
			logger.error("cancelar: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"SURTIR",  "HUBO UN ERROR AL CANCELAR.") );
		}		
	}
	
	public void cancelarCambios() {
		try{			
			logger.trace("cancelarCambios:pedidoVenta.id:"+entradaSalida.getId());
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"CANCELAR CAMBIOS",  "SE CANCELARON LOS CAMBIOS Y RECARGÓ EL PEDIDO #"+entradaSalida.getId()+".") );
			reset();
		}catch(Exception e){
			logger.error("cancelarCambios: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"CANCELAR CAMBIOS",  "HUBO UN ERROR AL CANCELAR CAMBIOS.") );
		}		
	}

	
	public void cerrar() {
		logger.trace("cerrar");
		reset();
	}

	public boolean isHayCambios() {
		return hayCambios;
	}
	
	public void onComentariosChange() {
		logger.trace("onComentariosChange:comentarios="+entradaSalida.getComentarios());
	}
	
	public void onCondicionesChange() {
		logger.trace("onCondicionesChange:CondicionesDePago="+entradaSalida.getCondicionesDePago());
	}
	
	public String getImporteDesglosado(double f){
		return Constants.getImporteDesglosado(f);
	}

	public String getImporteMoneda(double f){
		return Constants.getImporteMoneda(f);
	}
	
	public boolean isAutorizaDescuento(){
		return this.autorizaDescuento;
	}
	
	public void setAutorizaDescuento(boolean autorizaDescuento){
		this.autorizaDescuento = autorizaDescuento;
	}
	
	public void onAutorizaDescuentoChange(){
		logger.trace("autorizaDescuento="+this.autorizaDescuento);
		if(this.autorizaDescuento){
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"DESCUENTO",  " SE AUTORIZÓ LA POLITICA DE DESCUENTO") );
		} else {
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"DESCUENTO",  " SE BLOQUEÓ LA POLITICA DE DESCUENTO") );
		}
		entradaSalida.setAutorizaDescuento(this.autorizaDescuento?1:0);
		if(!this.autorizaDescuento){
			this.entradaSalida.setPorcentajeDescuentoExtra(0);
		}
		actualizarTotales();
	}
	
	public String getPoliticaDescuento(){
		if(this.autorizaDescuento){
			return "POLITICA: De 5,000 a 10,000 = -5%, > 10,000 = -10%";
		}else{
			return "NO HAY DESCUENTO";
		}
	}
	
	public String getCodigoTableWidth(){
		if(this.tablaExpandida)
			return "20%";
		else
			return "40%";
	}
	
	public boolean isVerificable(){		
		return entradaSalida!=null && entradaSalida.getEstadoId() <= Constants.ESTADO_SINCRONIZADO ;
	}
	
	public boolean isSurtible(){
		return entradaSalida!=null && entradaSalida.getEstadoId() == Constants.ESTADO_VERIFICADO;
	}

	public boolean isFacturable(){
		return entradaSalida!=null && (entradaSalida.getEstadoId() == Constants.ESTADO_SURTIDO || (entradaSalida.getEstadoId() == Constants.ESTADO_FACTURADO && entradaSalida.getCfdId()==null));
	}
	
	public boolean isCancelable(){
		return entradaSalida!=null && entradaSalida.getEstadoId() < Constants.ESTADO_SURTIDO ;
	}
	
	public int getAlturaExtraTabla() {
		logger.trace("getAlturaExtraTabla: isVerificable()="+isVerificable()+", isSurtible()="+isSurtible());
		if(isVerificable() || isSurtible()){
			logger.trace("getAlturaExtraTabla: 0");
			return 0;
		} else {
			logger.trace("getAlturaExtraTabla: 180");
			return 180;
		}
	}

	public boolean getRenglonesMoviblesEnTabla() {
		if(isVerificable() || isSurtible()){
			return true;
		} else {
			return false;
		}
	}
	
	public String volverAlListado(){
		return "/pages/pedidosVenta";
	}

	public void prepareDownload() {       
        
    }
 
    public StreamedContent getFile() {
        return file;
    }
	
	protected boolean actualizarEstadoPorResultadoWS = false;
	
	protected int tiempoTrasncurridoInvocarCFD=0;
	protected long tWS=0;

	public boolean isActualizarEstadoPorResultadoWS() {
		logger.trace("actualizarEstadoPorResultadoWS?"+actualizarEstadoPorResultadoWS);
		return actualizarEstadoPorResultadoWS;
	}
	
	public int getTiempoTrasncurridoInvocarCFD(){
		return tiempoTrasncurridoInvocarCFD;
	}
	
	
	public void verifyDigifactWSState(){
		logger.trace("--->> digifactWSState="+digifactWSState);
	}

	public void updateEstadoCFD(){
		tiempoTrasncurridoInvocarCFD = (int)(( System.currentTimeMillis()/1000)-tWS);		
		logger.trace("tiempoTrasncurridoInvocarCFD="+tiempoTrasncurridoInvocarCFD+", actualizarEstadoPorResultadoWS:"+actualizarEstadoPorResultadoWS+", pedidoVenta.getCfdId()="+entradaSalida.getCfdId());
	}
	
	public void generaCFDReal(){
		try{			
			digifactWSState=1;
			logger.trace("generaCFDReal:pedidoVenta.id:"+entradaSalida.getId());
			actualizarEstadoPorResultadoWS = true;
			tiempoTrasncurridoInvocarCFD=0;
			
			Cliente  c = ClienteDAO.getInstance().findBy(new Cliente(entradaSalida.getClienteId()));
			Sucursal s = SucursalDAO.getInstance().findBy(new Sucursal(1));
			tWS = System.currentTimeMillis();
			logger.trace("generaCFDReal:invocando DAO para WS, estaod="+entradaSalida.getEstadoId());
			digifactWSState=2;
			EntradaSalidaDAO.getInstance().invocarInicioWSCFDI(entradaSalida,entityList,c,sessionUserMB.getUsuarioAuthenticated(),s);			
			logger.trace("generaCFDReal:OK DAO y WS Digifact, invodocado: estado="+entradaSalida.getEstadoId());
			
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GENERAR C.F.D.",  "SE GENERÓ EL C.F.D.I.") );
			digifactWSState=3;
			
			reset();
			logger.trace("generaCFDReal:END.");
		} catch(Exception e){
			logger.error("generaCFDReal: Exception", e);
			FacesContext context = FacesContext.getCurrentInstance();         
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"GENERAR C.F.D.",  "HUBO UN ERROR AL INVOCAR WS.") );
			digifactWSState=4;
		}
	}

	public int getDigifactWSState() {
		return digifactWSState;
	}
		
	public String getEstiloInsuficiente(){
		if(entradaSalida.getEstadoId()<Constants.ESTADO_VERIFICADO){
			return "background: yellow;";
		} else {
			return "background: red;";
		}
	}
	
	public boolean isPosibleSurtir(){
		for(EntradaSalidaDetalleQuickView pv:entityList){
			if(pv.getCantidad()>pv.getApCantidad()){
				return false;
			}
		}
		return true;
	}
	
}
	
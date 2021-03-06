package com.pmarlen.jsf;

import com.pmarlen.backend.dao.ClienteDAO;
import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntityNotFoundException;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.quickviews.ProductoQuickView;
import com.pmarlen.model.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name="catalogoProdCtesMB")
@SessionScoped
public class CatalogoProdCtesMB   implements Serializable{
	private transient final Logger logger = Logger.getLogger(CatalogoProdCtesMB.class.getSimpleName());
	List<ProductoQuickView> entityList;
	Integer viewRows;
	ProductoQuickView selectedEntity;
	
	protected static List<SelectItem> tipoAlmacenList;
	protected static List<SelectItem> industriasList;
	protected static List<SelectItem> lineasList;
	protected static List<SelectItem> marcasIndList;
	protected static List<SelectItem> marcasLinList;
	
	private Cliente cliente;
	private String  clienteRFC;
	
	private String selection;
	private String industria;
	private String linea;
	private String marca;
	private String marcaInd;
	private String marcaLin;
	private String buscarCteMsg;
	private int tipoAlmacen;
	private int precioMin;
	private int precioMax;
	
	String dialogTitle;
	int almacenId=1;
	boolean nuevoProducto=false;
	@PostConstruct
    public void init() {
		logger.trace("CatalogoProdCtesMB: init.");
		nuevoProducto=false;        
		//logger.trace("ProductoMB: init:entityList="+entityList);
		viewRows = 10;
		dialogTitle ="CatalogoProdCtesMB";
		tipoAlmacen = Constants.ALMACEN_PRINCIPAL;
		precioMin = 10;
		precioMax = 200;
		cliente = null;
		clienteRFC = "";
    }

	public Cliente getCliente() {
		return cliente;
	}

	public String getBuscarCteMsg() {
		return buscarCteMsg;
	}
	
	public void setClienteRFC(String clienteRFC) {
		this.clienteRFC = clienteRFC;
	}

	public String getClienteRFC() {
		return clienteRFC;
	}
	
	public void searchCliente(){
	
	}
	
	public String reset() {
		logger.trace("ProductoMB: rest.");
        init();
		return "/pages/producto";
    }

	public String getDialogTitle() {
		return dialogTitle;
	}
	
	public void selectEntity(ActionEvent event){
		logger.trace("ProductoMB: selectProducto.");
	}
	
	public void actionX(ActionEvent event){
		logger.trace("ProductoMB: actionX.");
	}
	
	public void prepareForNew() {
		nuevoProducto=true;
		logger.trace("ProductoMB prepareForNew");
		dialogTitle ="DATOS DEL PRODUCTO";
		this.selectedEntity = new ProductoQuickView();
		this.selectedEntity.setUnidadEmpaque("PZ");
		this.selectedEntity.setUnidadesXCaja(12);		
	}
	
	public void setSelectedEntity(ProductoQuickView selectedProducto) {
		nuevoProducto=false;
		logger.trace("ProductoMB setSelectedProducto.id="+selectedProducto.getCodigoBarras());
		dialogTitle ="EDITAR PRODUCTO ID #"+selectedProducto.getCodigoBarras();
		this.selectedEntity = selectedProducto;
	}
	
	public void save(){
		logger.trace("ProductoMB: saveSelectedProducto:codigoBarras:"+selectedEntity.getCodigoBarras());
		
		try{
			int u=-1;			
			if(nuevoProducto){
				u=ProductoDAO.getInstance().insert(selectedEntity);			
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, dialogTitle, "SE CREÓ CORRECTAMENTE NUEVO PRODUCTO"));			
			} else{
				u=ProductoDAO.getInstance().update(selectedEntity);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, dialogTitle, "SE ACTUALIZARÓN CORRECTAMENTE LOS DATOS DEL PRODUCTO ["+selectedEntity.getCodigoBarras()+"]"));			
			}
			reset();
		} catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, dialogTitle, "OCURRIO UN ERROR AL GUARDAR:"+e.getLocalizedMessage()));
			FacesContext.getCurrentInstance().validationFailed();
		}
		
	}

	public Producto getSelectedEntity() {
		return selectedEntity;
	}
	
    public void onEntityChosen(SelectEvent event) {
        Integer entityId = (Integer) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Entity Selected", "Id:" + entityId);
         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public List<ProductoQuickView> getEntityList() {
		
		if(entityList == null){
			logger.info("getEntityList:marca="+marca);
			try{
				entityList = ProductoDAO.getInstance().findAllByMarcaExt(marca);
				logger.info("getEntityList: OK, entityList.size()="+entityList.size());
			}catch(DAOException de){
				entityList = null;
			}
		}

		return entityList;
	}
	
	public int getSizeList(){
		return entityList.size();
	}
	
	public void setViewRows(Integer viewRows) {
		this.viewRows = viewRows;
	}

	public Integer getViewRows() {
		return viewRows;
	}
	
	public String getImporteMoneda(double f){
		return Constants.getImporteMoneda(f);
	}
	
    public void onIndustriaLineaChange(TabChangeEvent event) {
        String tabTitle = event.getTab().getTitle();
        logger.info("->onIndustriaLineaChange:tabTitle="+tabTitle);
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
	
	public List<SelectItem> getIndustriasList() {
		if (industriasList == null) {
			industriasList = new ArrayList<SelectItem>();
			try{
				ArrayList<String> allIndustrias = ProductoDAO.getInstance().findAllIndustrias();
				industriasList.add(new SelectItem(null, "--SELECCIONAR--"));
				for(String i: allIndustrias){
					industriasList.add(new SelectItem(i, getShortDesc(i, MAX_LABEL_MENU_LENGTH)));
				}
			}catch(DAOException de){
			}
		}

		return industriasList;
	}

	public List<SelectItem> getLineasList() {
		if (lineasList == null) {
			lineasList = new ArrayList<SelectItem>();
			try{
				ArrayList<String> allLineas = ProductoDAO.getInstance().findAllLineas();
				lineasList.add(new SelectItem(null, "--SELECCIONAR--"));
				for(String l: allLineas){
					lineasList.add(new SelectItem(l, getShortDesc(l, MAX_LABEL_MENU_LENGTH)));
				}
			}catch(DAOException de){
			}
		}

		return lineasList;
	}

	public List<SelectItem> getMarcasPorIndustriaList() {
		if (marcasIndList == null) {
			marcasIndList = new ArrayList<SelectItem>();
			try{
				logger.info("getMarcasPorIndustriaList:industria="+industria);
				if(industria != null) {
					ArrayList<String> allMarcasI = ProductoDAO.getInstance().findAllMarcasByIndustria(industria);
					marcasIndList.add(new SelectItem(null, "--SELECCIONE--"));
					for(String m: allMarcasI){
						marcasIndList.add(new SelectItem(m, m));
					}
				}
			}catch(DAOException de){
			}
		}

		return marcasIndList;
	}
	
	public void onMasInfo(){
		logger.info("onMasInfo:");
		buscarCteMsg = null;
		cliente      = null;
		clienteRFC   = null;
	}
	
	public List<SelectItem> getMarcasPorLineaList() {
		if (marcasLinList == null) {
			marcasLinList = new ArrayList<SelectItem>();
			try{
				logger.info("getMarcasPorLineaList:linea="+linea);
				if(linea != null) {
					ArrayList<String> allMarcasL = ProductoDAO.getInstance().findAllMarcasByLinea(linea);
					marcasLinList.add(new SelectItem(null, "--SELECCIONE--"));
					for(String m: allMarcasL){
						marcasLinList.add(new SelectItem(m, m));
					}
				}
			}catch(DAOException de){
			}
		}

		return marcasLinList;
	}

	private boolean lineasListR		= false;
	private boolean industriasListR	= false;	
	private boolean marcaLinListR	= false;	
	private boolean marcaIndListR	= false;	
	public void onSelectionChange() {
		logger.info("onSelectionChange:selection="+selection);
		selectedEntity  = null;
		lineasListR		= false;
		industriasListR	= false;	
		marcaLinListR	= false;	
		marcaIndListR	= false;	
		marcaInd		= null;
		linea			= null;
		marca			= null;
		marcasLinList = null;
		marcasIndList = null;
		entityList    = null;
		
		if(selection.equals("LINEA")){
			lineasListR		= true;
		} else if(selection.equals("INDUSTRIA")){
			industriasListR = true;
		} 
	}

	public boolean isLineasListR() {
		return lineasListR;
	}

	public boolean isIndustriasListR() {
		return industriasListR;
	}

	public boolean isMarcaIndListR() {
		return marcaIndListR;
	}

	public boolean isMarcaLinListR() {
		return marcaLinListR;
	}
	
	public void onTipoAlmacenChange() {
		logger.info("onTipoAlmacenChange:tipoAlmacen="+tipoAlmacen);		
	}

	public void onIndustriaChange() {
		logger.info("onIndustriaChange:industria="+industria);		
		marcaInd = null;
		linea    = null;
		marca    = null;
		marcasLinList = null;
		marcasIndList = null;
		entityList    = null;
		selectedEntity = null;
		if(industria != null){
			marcaIndListR = true;
		} else {
			marcaIndListR = false;
		}
	}
	
	public void onLineaChange() {
		logger.info("onLineaChange:linea="+linea);
		marcaLin = null;
		industria = null;
		marca    = null;
		marcasLinList = null;
		marcasIndList = null;
		entityList    = null;
		selectedEntity = null;
		if(linea!=null){
			marcaLinListR = true;
		} else {
			marcaLinListR = false;
		}
	}

	public void onMarcaLinChange() {
		logger.info("onMarcaLinChange:marca="+marcaLin);		
		marca =  marcaLin;
		entityList    = null;
		selectedEntity = null;
	}
	
	public void onMarcaIndChange() {
		logger.info("onMarcaIndChange:marca="+marcaInd);		
		marca =  marcaInd;
		entityList    = null;
		selectedEntity = null;
	}

	
	/**
	 * @return the tipoAlmacen
	 */
	public int getTipoAlmacen() {
		return tipoAlmacen;
	}

	/**
	 * @param tipoAlmacen the tipoAlmacen to set
	 */
	public void setTipoAlmacen(int tipoAlmacen) {
		this.tipoAlmacen = tipoAlmacen;
	}

	/**
	 * @return the industria
	 */
	public String getIndustria() {
		return industria;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getSelection() {
		return selection;
	}
	
	/**
	 * @param industria the industria to set
	 */
	public void setIndustria(String industria) {
		this.industria = industria;
	}

	/**
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}

	/**
	 * @param linea the linea to set
	 */
	public void setLinea(String linea) {
		this.linea = linea;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the marcaInd
	 */
	public String getMarcaInd() {
		return marcaInd;
	}

	/**
	 * @param marcaInd the marcaInd to set
	 */
	public void setMarcaInd(String marcaInd) {
		this.marcaInd = marcaInd;
	}

	/**
	 * @return the marcaLin
	 */
	public String getMarcaLin() {
		return marcaLin;
	}

	/**
	 * @param marcaLin the marcaLin to set
	 */
	public void setMarcaLin(String marcaLin) {
		this.marcaLin = marcaLin;
	}
	
	private MenuModel menuBarModel;

	public MenuModel getMenuBarModel() {
		if(menuBarModel == null){
			menuBarModel = new DefaultMenuModel();
			
			menuBarModel.addElement(getSubmenuCatalogoXLineaMarca());
			menuBarModel.addElement(getSubmenuCatalogoXIndustriaMarca());
		}
		return menuBarModel;
	}

	private DefaultSubMenu getSubmenuCatalogoXLineaMarca(){		
        DefaultSubMenu subMenuLinea = new DefaultSubMenu("Linea");
        LinkedHashMap<String, ArrayList<String>> findAllLineasMarcas = null; 
		try{
			findAllLineasMarcas = ProductoDAO.getInstance().findAllLineasMarcas();
			
			for(String linea: findAllLineasMarcas.keySet()){
				
				DefaultSubMenu itemLinea = new DefaultSubMenu(getShortDesc(linea, MAX_LABEL_MENU_LENGTH));
				itemLinea.setIcon("ui-icon-star");
				subMenuLinea.addElement(itemLinea);
				
				ArrayList<String> marcas = findAllLineasMarcas.get(linea);
				
				for(String marca: marcas){
					DefaultMenuItem subItemMarca = new DefaultMenuItem(getShortDesc(marca, MAX_LABEL_MENU_LENGTH));
					subItemMarca.setIcon("ui-icon-contact");
					itemLinea.addElement(subItemMarca);
				}				
			}
		}catch(DAOException de){
			logger.error("getSubmenuCatalogoXLineaMarca:", de);
		}
        return subMenuLinea;
	}
	
	private DefaultSubMenu getSubmenuCatalogoXIndustriaMarca(){		
		DefaultSubMenu subMenuIndustria = new DefaultSubMenu("Industria");
        LinkedHashMap<String, ArrayList<String>> findAllIndustriasMarcas = null; 
		try{
			findAllIndustriasMarcas = ProductoDAO.getInstance().findAllIndustriasMarcas();
			
			for(String industria: findAllIndustriasMarcas.keySet()){
				
				DefaultSubMenu itemIndustria = new DefaultSubMenu(getShortDesc(industria,MAX_LABEL_MENU_LENGTH));				
				itemIndustria.setIcon("ui-icon-star");
				subMenuIndustria.addElement(itemIndustria);
				
				ArrayList<String> marcas = findAllIndustriasMarcas.get(industria);
				
				for(String marca: marcas){
					DefaultMenuItem subItemMarca = new DefaultMenuItem(getShortDesc(marca, MAX_LABEL_MENU_LENGTH));
					subItemMarca.setIcon("ui-icon-contact");
					itemIndustria.addElement(subItemMarca);
				}				
			}
		}catch(DAOException de){
			logger.error("getSubmenuCatalogoXIndustriaMarca:", de);
		}
        return subMenuIndustria;
	}
	
	private static final int MAX_LABEL_MENU_LENGTH = 30;
	
	private String getShortDesc(String label, int maxLength){
		if(label.length() >= maxLength){
			return label.substring(0, maxLength -3) + "...";
		} else {
			return label;
		}
	}
	private String productoCBSeleccionado;

	public void setProductoCBSeleccionado(String productoCBSeleccionado) {
		this.productoCBSeleccionado = productoCBSeleccionado;
	}

	public String getProductoCBSeleccionado() {
		return productoCBSeleccionado;
	}
	
	
	public void selectProduct(String productoCB){
		logger.info("selectProduct:productoCB="+productoCB);
		productoCBSeleccionado = productoCB;
		selectedEntity = null;
		for(ProductoQuickView e: entityList){
			if(e.getCodigoBarras().equals(productoCB)){
				selectedEntity = e;
				break;
			}
		}
	}

	/**
	 * @return the precioMin
	 */
	public int getPrecioMin() {
		return precioMin;
	}

	/**
	 * @param precioMin the precioMin to set
	 */
	public void setPrecioMin(int precioMin) {
		this.precioMin = precioMin;
	}

	/**
	 * @return the precioMax
	 */
	public int getPrecioMax() {
		return precioMax;
	}

	/**
	 * @param precioMax the precioMax to set
	 */
	public void setPrecioMax(int precioMax) {
		this.precioMax = precioMax;
	}
	
	public void buscarRFC(){
		logger.info("->buscarRFC: clienteRFC="+clienteRFC);
		buscarCteMsg = null;
		try {
			cliente = ClienteDAO.getInstance().findByRFC(clienteRFC);
			clienteRFC = null;
		}catch(EntityNotFoundException nfe){
			logger.error("AL BUSCAR CLIENTE:",nfe);
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, dialogTitle, "NO EXISTE CLIENTE CON R.F.C."));
			//FacesContext.getCurrentInstance().validationFailed();
			buscarCteMsg = "NO EXISTE CLIENTE CON R.F.C.";
			clienteRFC = null;
		}catch(DAOException de){
			logger.error("AL BUSCAR CLIENTE:",de);
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, dialogTitle, "ERROR AL BUSCAR CLIENTE"));
			//FacesContext.getCurrentInstance().validationFailed();
			buscarCteMsg = "ERROR AL BUSCAR CLIENTE";
			clienteRFC = null;
		}
	}
	
	public void cancelarRFC(){
		logger.info("->cancelarRFC");
	}
}

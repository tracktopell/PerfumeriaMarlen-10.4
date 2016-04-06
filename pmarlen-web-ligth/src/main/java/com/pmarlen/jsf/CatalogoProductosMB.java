package com.pmarlen.jsf;

import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.quickviews.ProductoQuickView;
import com.pmarlen.model.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

@ManagedBean(name="catalogoProductosMB")
@SessionScoped
public class CatalogoProductosMB  {
	private transient final Logger logger = Logger.getLogger(CatalogoProductosMB.class.getSimpleName());
	List<ProductoQuickView> entityList;
	Integer viewRows;
	ProductoQuickView selectedEntity;
	protected static List<SelectItem> tipoAlmacenList;
	protected static List<SelectItem> industriasList;
	protected static List<SelectItem> lineasList;
	protected static List<SelectItem> marcasIndList;
	protected static List<SelectItem> marcasLinList;	
	private String industria;
	private String linea;
	private String marca;
	private String marcaInd;
	private String marcaLin;
	
	String dialogTitle;
	int almacenId=1;
	boolean nuevoProducto=false;
	@PostConstruct
    public void init() {
		logger.trace("ProductoMB: init.");
		nuevoProducto=false;        
		//logger.trace("ProductoMB: init:entityList="+entityList);
		viewRows = 10;
		dialogTitle ="PRODUCTO";
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
				entityList = ProductoDAO.getInstance().findAllByMarca(marca);
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
         
	private int tipoAlmacen;
	
	
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
					industriasList.add(new SelectItem(i, i));
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
					lineasList.add(new SelectItem(l, l));
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
	}
	
	public void onLineaChange() {
		logger.info("onLineaChange:linea="+linea);
		marcaLin = null;
		industria = null;
		marca    = null;
		marcasLinList = null;
		marcasIndList = null;
		entityList    = null;
	}

	public void onMarcaLinChange() {
		logger.info("onMarcaLinChange:marca="+marcaLin);		
		marca =  marcaLin;
		entityList    = null;
	}
	
	public void onMarcaIndChange() {
		logger.info("onMarcaIndChange:marca="+marcaInd);		
		marca =  marcaInd;
		entityList    = null;
		
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

}

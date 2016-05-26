package com.pmarlen.jsf;

import com.pmarlen.backend.dao.ProductoDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.quickviews.ProductoQuickView;
import com.pmarlen.model.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean(name="productoMB")
@SessionScoped
public class ProductoMB  implements Serializable {
	private transient final Logger logger = Logger.getLogger(ProductoMB.class.getSimpleName());
	List<ProductoQuickView> entityList;
	Integer viewRows;
	ProductoQuickView selectedEntity;
	String dialogTitle;
	int almacenId=1;
	boolean nuevoProducto=false;
	@PostConstruct
    public void init() {
		logger.debug("ProductoMB: init.");
		nuevoProducto=false;
        try{
			entityList = ProductoDAO.getInstance().findAll();
		}catch(DAOException de){
			logger.error(de.getMessage());
			entityList = new ArrayList<ProductoQuickView>();
		}
		//logger.debug("ProductoMB: init:entityList="+entityList);
		viewRows = 10;
		dialogTitle ="PRODUCTO";
    }
	
	public void recargar(){
		logger.debug("ProductoMB: recagar");
		entityList = null;
	}
	
	public String reset() {
		logger.debug("ProductoMB: reset.");
        init();
		return "/pages/producto";
    }

	public String getDialogTitle() {
		return dialogTitle;
	}
	
	public void selectEntity(ActionEvent event){
		logger.debug("ProductoMB: selectProducto.");
	}
	
	public void actionX(ActionEvent event){
		logger.debug("ProductoMB: actionX.");
	}
	
	public void prepareForNew() {
		nuevoProducto=true;
		logger.debug("ProductoMB prepareForNew");
		dialogTitle ="DATOS DEL PRODUCTO";
		this.selectedEntity = new ProductoQuickView();
		this.selectedEntity.setUnidadEmpaque("PZ");
		this.selectedEntity.setUnidadesXCaja(12);		
		this.selectedEntity.setPoco(5);
	}
	
	public void setSelectedEntity(ProductoQuickView selectedProducto) {
		nuevoProducto=false;
		logger.debug("ProductoMB setSelectedProducto.id="+selectedProducto.getCodigoBarras());
		dialogTitle ="EDITAR PRODUCTO ID #"+selectedProducto.getCodigoBarras();
		this.selectedEntity = selectedProducto;
	}
	
	public void save(){
		logger.debug("ProductoMB: saveSelectedProducto:codigoBarras:"+selectedEntity.getCodigoBarras()+",NOMBRE:"+selectedEntity.getNombre()+", DESCONTONUADO:"+selectedEntity.getDescontinuado());
		
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
			try{
				entityList = ProductoDAO.getInstance().findAll();
				logger.debug("ProductoMB: getEntityList: entityList.size="+entityList.size());
			}catch(DAOException de){
				logger.error(de.getMessage());
				entityList = new ArrayList<ProductoQuickView>();
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
}

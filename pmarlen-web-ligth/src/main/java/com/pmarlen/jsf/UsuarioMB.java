package com.pmarlen.jsf;

import com.pmarlen.backend.dao.UsuarioDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.UsuarioDAO;
import com.pmarlen.backend.model.Usuario;
import com.pmarlen.backend.model.quickviews.UsuarioQuickView;
import com.pmarlen.model.Constants;
import com.pmarlen.web.security.managedbean.EmailChecker;
import com.pmarlen.web.security.managedbean.SessionUserMB;
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

@ManagedBean(name="usuarioMB")
@SessionScoped
public class UsuarioMB  {
	private transient final Logger logger = Logger.getLogger(UsuarioMB.class.getSimpleName());
	private List<UsuarioQuickView> entityList;
	private Integer viewRows;
	private UsuarioQuickView selectedEntity;
	private String dialogTitle;
	private boolean checkSameEmailPassword;
	private boolean todos;
	private Integer checkSameEmailPasswordState;
	private int saveMode=0;
	private String pwd;
	private String pwd2;
	private boolean changePassword;
	
	@PostConstruct
    public void init() {
		logger.trace("UsuarioMB: init.");
		refreshList();
		viewRows = 10;
		dialogTitle ="USUARIO";
		saveMode=0;
		checkSameEmailPasswordState=null;
		changePassword =  true;
		todos=false;
    }

	public void refreshList() {
		
		try{
			entityList = UsuarioDAO.getInstance().findAll(todos);			
		}catch(DAOException de){
			logger.error(de.getMessage());
			entityList = new ArrayList<UsuarioQuickView>();
		}
		logger.trace("UsuarioMB: init:entityList size="+entityList.size());
	}
	
	public String reset() {
		logger.trace("UsuarioMB: rest.");
        init();
		return "/pages/usuario";
    }

	public String getDialogTitle() {
		return dialogTitle;
	}
	
	public void selectEntity(ActionEvent event){
		logger.trace("UsuarioMB: selectUsuario.");
	}
	
	public void actionX(ActionEvent event){
		logger.trace("UsuarioMB: actionX.");
	}
	
	public void prepareForNew() {
		logger.trace("UsuarioMB prepareForNew");
		dialogTitle ="AGREGAR NUEVO USUARIO";
		this.selectedEntity = new UsuarioQuickView();
		checkSameEmailPassword=false;
		checkSameEmailPasswordState=null;
		this.pwd  = null;
		this.pwd2 = null;
		changePassword =  true;
		saveMode=1;
	}
	
	public void refresh() {
		init();
	}
	
	public void setSelectedEntity(UsuarioQuickView selectedUsuario) {
		logger.trace("UsuarioMB setSelectedUsuario.email="+selectedUsuario.getEmail());
		dialogTitle ="EDITAR USUARIO:"+selectedUsuario.getEmail();
		this.selectedEntity = selectedUsuario;
		checkSameEmailPassword=false;
		checkSameEmailPasswordState=null;
		this.pwd  = null;
		this.pwd2 = null;
		changePassword = false;
		saveMode=2;
	}

	public boolean isCheckSameEmailPassword() {
		return checkSameEmailPassword;
	}

	public Integer getCheckSameEmailPasswordState() {
		return checkSameEmailPasswordState;
	}
	
	public void checkPassword(){
		checkSameEmailPasswordState=1;
		logger.info("checkPassword="+this.pwd);
		checkSameEmailPassword = EmailChecker.isSameEmailPassword(this.selectedEntity.getEmail(), this.selectedEntity.getPassword());
		
		if(checkSameEmailPassword){
			checkSameEmailPasswordState=2;
		}
	}
	
	public void checkSamePwd(){
		logger.info("checkPassword: pwd="+pwd+", pwd2="+pwd2);
		
		if(!pwd.equals(pwd2)){
			throw new IllegalStateException("LAS CONTRASEÑAS NO SON LAS MISMAS");
		}
	}
	
	public void save(){
		logger.info("UsuarioMB: save: changePassword="+changePassword+" ,selectedEntity:email:"+selectedEntity.getEmail()+", roles:"+selectedEntity.getRoleList());
		
		try{
			if(changePassword){
				
				checkSamePwd();
				selectedEntity.setPassword(Constants.getMD5Encrypted(pwd));
			}
			int u=-1;			
			if(saveMode == 1){
				logger.trace("UsuarioMB:insert");
				u=UsuarioDAO.getInstance().insert(selectedEntity);			
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AL GURDAR", "SE CREÓ CORRECTAMENTE NUEVO CLIENTE"));			
			} else if(saveMode == 2){
				logger.trace("UsuarioMB:update");
				u=UsuarioDAO.getInstance().update(selectedEntity);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AL GURDAR", "SE ACTUALIZARÓN CORRECTAMENTE LOS DATOS DEL CLIENTE"));			
			}
			reset();
		} catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "AL GURDAR", "OCURRIO UN ERROR AL GUARDAR:"+e.getMessage()));
			FacesContext.getCurrentInstance().validationFailed();
		}
		
	}

	public UsuarioQuickView getSelectedEntity() {
		return selectedEntity;
	}
	
    public void onEntityChosen(SelectEvent event) {
        Integer entityId = (Integer) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Entity Selected", "Id:" + entityId);
         
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public List<UsuarioQuickView> getEntityList() {
//		logger.trace("just return:---------->>");
//		for(UsuarioQuickView uqv: entityList){
//			logger.trace(":"+uqv.getEmail()+","+uqv.getNombreCompleto()+","+uqv.getAbilitado());
//		}
//		logger.trace("<<----------");
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

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public boolean isTodos() {
		return todos;
	}	

	/**
	 * @return the saveMode
	 */
	public int getSaveMode() {
		return saveMode;
	}

	/**
	 * @param saveMode the saveMode to set
	 */
	public void setSaveMode(int saveMode) {
		this.saveMode = saveMode;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the pwd2
	 */
	public String getPwd2() {
		return pwd2;
	}

	/**
	 * @param pwd2 the pwd2 to set
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	/**
	 * @return the changePassword
	 */
	public boolean isChangePassword() {
		return changePassword;
	}

	/**
	 * @param changePassword the changePassword to set
	 */
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}
	
	public void changePasswordX(){		
		String msg = "changePasswordX: values pwd=["+pwd+"], pwd2=["+pwd2+"]";
		logger.info(msg);        
		this.pwd  = null;
		this.pwd2 = null;
	}
}

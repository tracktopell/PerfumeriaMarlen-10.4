package com.pmarlen.jsf;

import com.pmarlen.backend.dao.CorteCajaDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.SucursalDAO;
import com.pmarlen.backend.model.CorteCaja;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.quickviews.UsuarioQuickView;
import com.pmarlen.model.Constants;
import com.pmarlen.web.servlet.CajaSessionInfo;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import com.pmarlen.web.servlet.SessionInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

@ManagedBean(name="sesionesActivasMB")
@SessionScoped
public class SesionesActivasMB implements Serializable{
	private transient static Logger logger = Logger.getLogger("sesionesActivasMB");
	private int noPedidoBuscar;
	
	@PostConstruct
	public void init(){
		logger.trace("init:");	
	}
	
	public List<SessionInfo> getSesionesActivas(){
		List<SessionInfo>  siList = new ArrayList<SessionInfo> ();
	
		Enumeration<SessionInfo> elements = ContextAndSessionListener.sessionInfoHT.elements();
		while(elements.hasMoreElements()){
			SessionInfo si=elements.nextElement();
			siList.add(si);
		}
		
		return siList;
	}

	public List<CajaSessionInfo> getSesionesCajaActivas(){
		List<CajaSessionInfo>  sesionesCajaActivas = new ArrayList<CajaSessionInfo> ();
		List<String> sessionExpired = new ArrayList<String>();
		Enumeration<CajaSessionInfo> elements = ContextAndSessionListener.cajaSessionInfoHT.elements();
		logger.trace("=>Sessions:");
		while(elements.hasMoreElements()){
			CajaSessionInfo csi = elements.nextElement();
			logger.trace("\t=>("+csi.getSessionId()+") creationTime:"+Constants.sdfLogDate.format(new Date(csi.getApplicationContextCreationTime()))+
					", LastAccesedTime:"+Constants.sdfLogDate.format(new Date(csi.getLastAccesedTime()))+", loggedIn="+csi.getLoggedIn());
			if(! csi.isTimeout()) {
				sesionesCajaActivas.add(csi);
			} else {
				sessionExpired.add(csi.getSessionId());
			}
		}
		
		for(String sid: sessionExpired){
			ContextAndSessionListener.cajaSessionInfoHT.remove(sid);
		}
		
		return sesionesCajaActivas;
	}
	
	private Integer sucursalIdVerCorte;	
	private Integer cajaCorte;
	private Date    fechaInicial;
	private Date    fechaFinal;
	private CajaSessionInfo cajaSessionInfoSelected;
	
	public List<CorteCaja> getCorteCajaBySucursalAndCaja(){
		ArrayList<CorteCaja> corteCajaList = null;
		try{
			if(sucursalIdVerCorte !=null && cajaCorte != null){
				corteCajaList = CorteCajaDAO.getInstance().findAllBy(sucursalIdVerCorte, cajaCorte,fechaInicial, fechaFinal);
			}
		}catch(DAOException de){
			logger.error(de.getMessage());
			corteCajaList = new ArrayList<CorteCaja>();
		}
		return corteCajaList;
	}

	
	
	private static Hashtable<Integer,Sucursal> sucursalInfo= null;

	public static Hashtable<Integer, Sucursal> getSucursalInfo() {		
		if(sucursalInfo == null){
			ArrayList<Sucursal> sucursales = null;
			try {
				sucursales = SucursalDAO.getInstance().findAll();
				sucursalInfo=new Hashtable<Integer,Sucursal>();
				for(Sucursal s: sucursales){
					sucursalInfo.put(s.getId(), s);
				}
			}catch(DAOException de){
				logger.error("No se cargaron las Sucursales:"+de.getMessage());
				sucursalInfo=new Hashtable<Integer,Sucursal>();
			}
			
		}
		return sucursalInfo;
	}
	
	public Sucursal getSucursalInfo(int sucId){
		return getSucursalInfo().get(sucId);
	}

	/**
	 * @return the sucursalIdVerCorte
	 */
	public Integer getSucursalIdVerCorte() {
		return sucursalIdVerCorte;
	}

	/**
	 * @param sucursalIdVerCorte the sucursalIdVerCorte to set
	 */
	public void setSucursalIdVerCorte(Integer sucursalIdVerCorte) {
		this.sucursalIdVerCorte = sucursalIdVerCorte;
	}

	/**
	 * @return the cajaCorte
	 */
	public Integer getCajaCorte() {
		return cajaCorte;
	}

	/**
	 * @param cajaCorte the cajaCorte to set
	 */
	public void setCajaCorte(Integer cajaCorte) {
		this.cajaCorte = cajaCorte;
	}

	/**
	 * @return the fechaInicial
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the cajaSessionInfoSelected
	 */
	public CajaSessionInfo getCajaSessionInfoSelected() {
		return cajaSessionInfoSelected;
	}

	/**
	 * @param cajaSessionInfoSelected the cajaSessionInfoSelected to set
	 */
	public void setCajaSessionInfoSelected(CajaSessionInfo cajaSessionInfoSelected) {
		this.cajaSessionInfoSelected = cajaSessionInfoSelected;
		this.sucursalIdVerCorte		 = Integer.parseInt(this.cajaSessionInfoSelected.getSucursal());
		this.cajaCorte				 = Integer.parseInt(this.cajaSessionInfoSelected.getCaja());
		logger.info("setCajaSessionInfoSelected:this.cajaSessionInfoSelected="+this.cajaSessionInfoSelected+", this.sucursalIdVerCorte="+this.sucursalIdVerCorte+", this.cajaCorte="+this.cajaCorte);
	}

}

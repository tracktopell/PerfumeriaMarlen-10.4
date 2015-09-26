package com.pmarlen.jsf;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.SucursalDAO;
import com.pmarlen.backend.model.Sucursal;
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
import org.apache.batik.dom.util.HashTable;
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
	
	private static Hashtable<Integer,String> sucursalInfo= null;

	public static Hashtable<Integer, String> getSucursalInfo() {		
		if(sucursalInfo == null){
			ArrayList<Sucursal> sucursales = null;
			try {
				sucursales = SucursalDAO.getInstance().findAll();
				sucursalInfo=new Hashtable<Integer,String>();
				for(Sucursal s: sucursales){
					sucursalInfo.put(s.getId(), s.getNombre());
				}
			}catch(DAOException de){
				logger.error("No se cargaron las Sucursales:"+de.getMessage());
				sucursalInfo=new Hashtable<Integer,String>();
			}
			
		}
		return sucursalInfo;
	}
	
	public String getSucursalInfo(int sucId){		
		return getSucursalInfo().get(sucId);
	}

}

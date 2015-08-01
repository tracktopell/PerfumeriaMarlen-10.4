package com.pmarlen.jsf;

import com.pmarlen.model.Constants;
import com.pmarlen.web.servlet.CajaSessionInfo;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import com.pmarlen.web.servlet.SessionInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.log4j.Logger;

@ManagedBean(name="sesionesActivasMB")
@SessionScoped
public class SesionesActivasMB {
	private transient static Logger logger = Logger.getLogger("sesionesActivasMB");
	private int noPedidoBuscar;
	
	@PostConstruct
	public void init(){
		logger.info("->init:");	
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
		logger.info("=>Sessions:");
		while(elements.hasMoreElements()){
			CajaSessionInfo csi = elements.nextElement();
			logger.info("\t=>("+csi.getSessionId()+") creationTime:"+Constants.sdfLogDate.format(new Date(csi.getApplicationContextCreationTime()))+
					", LastAccesedTime:"+Constants.sdfLogDate.format(new Date(csi.getLastAccesedTime())));
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

}

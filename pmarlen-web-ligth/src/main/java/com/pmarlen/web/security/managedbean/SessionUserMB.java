package com.pmarlen.web.security.managedbean;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.UsuarioDAO;
import com.pmarlen.backend.model.quickviews.UsuarioQuickView;
import com.pmarlen.jsf.model.InformationMessage;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import com.pmarlen.web.servlet.SessionInfo;
import java.io.Serializable;

import java.text.*;
import java.util.*;

import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name="sessionUserMB")
@SessionScoped
public class SessionUserMB implements Serializable{

	private static final transient Logger logger = Logger.getLogger(SessionUserMB.class.getSimpleName());
	private static final transient SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
	private long timeOutSession;
	private int  realTimeOutSessionInSeconds;
	private UsuarioQuickView usuarioAuthenticated;
	private String uaInfo    = null;
	private String os        = "-";
	private String browser   = "-";
    private Integer xvalue;
    private InformationMessage lastMessage;
    
    private boolean messageRead;
    private String  messageForSession;
    private String  previousMessageForSession;    
	@PostConstruct
    public void init() {
        xvalue = 0;
		usuarioAuthenticated = getUsuarioAuthenticated();
		logger.trace("------------->usuarioAuthenticated="+usuarioAuthenticated);
    }

	public UsuarioQuickView getUsuarioAuthenticated() {
		if (usuarioAuthenticated == null) {
			FacesContext fCtx = FacesContext.getCurrentInstance();
			ExternalContext externalContext = fCtx.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(false);
			String sessionId = session.getId();
			
			String remoteUser      = externalContext.getRemoteUser();
			String userPrincipal   = externalContext.getUserPrincipal().getName();
			
			logger.trace("==>> getUsuarioAuthenticated : Session[" + sessionId + "] first enter: remoteUser=" + remoteUser+", userPrincipal=" + userPrincipal);
				
			if (remoteUser != null) {
				
				Date creationTime = new Date(session.getCreationTime());

				logger.trace("==>> UsuarioAuthenticated : Session[" + sessionId + "] created            :" + sdf.format(creationTime));
				logger.trace("==>> UsuarioAuthenticated : Session[" + sessionId + "] new                ?" + session.isNew());
				
				timeOutSession = session.getMaxInactiveInterval()*1000 - 5000;
				realTimeOutSessionInSeconds = session.getMaxInactiveInterval();
				
				try {
					usuarioAuthenticated = UsuarioDAO.getInstance().findBy(remoteUser);
					if (!session.isNew()) {
						logger.trace("\t==>> usuarioAuthenticated : Session[" + sessionId + "] True enter :" + usuarioAuthenticated);
						SessionInfo si = ContextAndSessionListener.sessionInfoHT.get(sessionId);
						if (si != null) {
							logger.trace("\t==>> found, setUserName");
							si.setUserName(remoteUser);
						} else {
							si = new SessionInfo(session, usuarioAuthenticated.getNombreCompleto());
							logger.trace("\t==>> NOT Fucking found("+sessionId+"), add new ? really ? {"+ContextAndSessionListener.sessionInfoHT.keySet()+"}");
							ContextAndSessionListener.sessionInfoHT.put(sessionId, si);
						}
					}
				} catch(DAOException de){
					logger.error(de.getMessage());
				}
				
			}
		}
		return usuarioAuthenticated;
	}
	
	public void onIdle() {
		logger.trace("onIdle, timeOutSession="+(timeOutSession/1000)+" secs., then Logout !");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "No activity.", "What are you doing over there?"));
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Welcome Back", "Well, that's a long coffee break!"));
    }

	public long getTimeOutSession() {
		return timeOutSession;
	}
	
	public int getTimeOutSessionInMinutes() {
		return realTimeOutSessionInSeconds/60;
	}
	
	public int getTimeOutSessionInSeconds() {
		return realTimeOutSessionInSeconds;
	}
	
	public int getTimeOutSessionInSecondsOfMinutes() {
		return realTimeOutSessionInSeconds%60;
	}
	
	public String getSessionTimeOutMinus3SecsToDisplay() {
		HttpServletRequest r = null;

		r = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		r.getSession().getMaxInactiveInterval();

		return String.valueOf(r.getSession().getMaxInactiveInterval() - 3);
	}

	public void updateLastVisitedPage(String page) {
		FacesContext fCtx = FacesContext.getCurrentInstance();		
		ExternalContext externalContext = fCtx.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		HttpServletRequest request = (HttpServletRequest) (externalContext.getRequest());
		String sessionId = session.getId();
		String userAgent = request.getHeader("User-Agent");
		String remoteAdrrInfo = request.getRemoteAddr() + "(" + request.getRemoteHost() + ")";
		logger.trace("==>> updateLastVisitedPage : page"+page+" from : "+ remoteAdrrInfo +" using:"+userAgent);
		SessionInfo si = ContextAndSessionListener.sessionInfoHT.get(sessionId);
		if(si != null) {
			si.setLastVisitedPage(page);
			si.setRemoteAddr(remoteAdrrInfo);
			if(userAgent != null){
				si.setUserAgent(userAgent);
			}
		}
	}

	public int getSucursalId() {
		return 1;
	}
	
	public String getBienvenido(){
		updateLastVisitedPage("home.jsf");
		return getUsuarioAuthenticated().getNombreCompleto().toUpperCase();
	}
	
	/**
	 * @return the uaInfo
	 */
	public String getUaInfo() {
		if(uaInfo == null){
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String browserDetails = externalContext.getRequestHeaderMap().get("User-Agent");
			String userAgent = browserDetails;
			String user = userAgent.toLowerCase();

			//=================OS=======================
			if (userAgent.toLowerCase().indexOf("windows") >= 0) {
				os = "Windows";
			} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
				os = "Mac";
			} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
				os = "Unix";
			} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
				os = "Android";
			} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
				os = "IPhone";
			} else {
				os = "UnKnown, More-Info: " + userAgent;
			}
			//===============Browser===========================
			if (user.contains("msie")) {
				String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
				browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
			} else if (user.contains("safari") && user.contains("version")) {
				browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
			} else if (user.contains("opr") || user.contains("opera")) {
				if (user.contains("opera")) {
					browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
				} else if (user.contains("opr")) {
					browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
				}
			} else if (user.contains("chrome")) {
				browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
			} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
				//browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
				browser = "Netscape-?";

			} else if (user.contains("firefox")) {
				browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
			} else if (user.contains("rv")) {
				browser = "IE";
			} else {
				browser = "UnKnown, More-Info: " + userAgent;
			}
			
			uaInfo = "["+os+","+browser+"]";
		}
		return uaInfo;
	}

    public void setXvalue(Integer xvalue) {
        this.xvalue = xvalue;
        if(this.xvalue%10==0 && this.xvalue>0){
            RequestContext requestContext = RequestContext.getCurrentInstance();  
            requestContext.execute("alert('OK, it works JS invoked from PrimeFaces !');");
        }
    }
    
    public Integer getXvalue() {
        return xvalue;
    }
    
    public void messageRead() {
        logger.info("->messageRead()");
        messageRead = true;
    }
    
    public String getMessageForSession() {
        if(SystemInfoMB.getSystemWallMessageGlobal() != null ){
            if(previousMessageForSession!=null && 
                    SystemInfoMB.getSystemWallMessageGlobal().equals(previousMessageForSession)){
                messageRead = false;
            }
            
            if(!messageRead){
                messageForSession = SystemInfoMB.getSystemWallMessageGlobal();                                
            }else{
                messageForSession = null;
                previousMessageForSession = messageForSession;
            }
        } 
        return messageForSession;
        
    }

}

package com.pmarlen.web.security.managedbean;

import com.pmarlen.model.Constants;
import com.tracktopell.jdbc.WEBDataSourceFacade;
import java.io.IOException;
import java.io.Serializable;

import java.text.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

@ManagedBean(name="systemInfoMB")
@SessionScoped
public class SystemInfoMB  implements Serializable{
	private static String environment=null;
	private static final transient Logger logger = Logger.getLogger(SystemInfoMB.class.getSimpleName());
	private static final transient SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static String db        = null;
	private static String user      = null;
	private static String password  = null;
	private String broadcastMessage  = null;
    private String broadcastMessage2Push  = null;
    private String broadcastLastMessage   = null;
    private static Queue<String> broadcastMessageQueue = null;
    private static String        systemWallMessage;

	private static Properties prop = new Properties();
	static{
		try {
			prop.load(WEBDataSourceFacade.class.getResourceAsStream("/env_vars.properties"));
		}catch(IOException ioe){
			logger.error("Properties not found:", ioe);
		}
	}

	public static String getDB() {
		if (db == null) {
			db   = prop.getProperty("db");			
		}
		return db;
	}
	
	public static String getUser() {
		if (user == null) {
			user  = prop.getProperty("user");			
		}
		return user;
	}
	
	public static String getPassword() {
		if (password == null) {
			password   = prop.getProperty("password");			
		}
		return password;
	}
	
	public String getSystemVersion() {
		return Constants.getServerVersion();
	}
	
	public String getBuildTimestamp() {
		return Constants.getBuildTimestamp();	
	}
	
	public String getGitSHA1() {
		return Constants.getGitSHA1();
	}
	
	private String sessionDate=sdf.format(new Date());

	public String getSessionDate() {
		updateTime();
		return sessionDate;
	}
	
	public void updateTime(){
		sessionDate= sdf.format(new Date());
        logger.trace("->updateTime():sessionDate="+sessionDate);
        
        broadcastMessage = null;
        
        if(broadcastMessageQueue != null){
            if(! broadcastMessageQueue.isEmpty() ){
                broadcastMessage = broadcastMessageQueue.poll();
                if(broadcastMessage != null){
                    broadcastLastMessage = broadcastMessage;
                    RequestContext requestContext = RequestContext.getCurrentInstance();  
                    requestContext.execute("PF('notificationBar').show();");
                }
            }
        }        
	}
	
	public String getEnvironment(){
		return getEnvironmentStaticlay();
	}
	
	public static String getEnvironmentStaticlay(){
		if(environment == null){
			environment=prop.getProperty("environment");
		}
		return environment;
	}

    public void setBroadcastMessage(String broadcastMessage) {
        this.broadcastMessage = broadcastMessage;
    }

    public String getBroadcastMessage() {
        return this.broadcastMessage;
    }
    
    public void broadcastMessageClosed(ActionEvent actionEvent) {
        logger.info("->broadcastMessageClosed(ActionEvent):broadcastMessage="+broadcastMessage);
        broadcastMessage = null;
    }
    
    public void broadcastMessageClosed() {
        logger.info("->broadcastMessageClosed():broadcastMessage="+broadcastMessage);
        broadcastMessage = null;
    }

    public void setBroadcastMessage2Push(String broadcastMessage2Push) {
        this.broadcastMessage2Push = broadcastMessage2Push;
        if(broadcastMessageQueue == null){
            broadcastMessageQueue = new ConcurrentLinkedQueue<String>();
        }
        broadcastMessageQueue.add(this.broadcastMessage2Push);
        this.broadcastMessage2Push = null;
    }

    public String getBroadcastMessage2Push() {
        return broadcastMessage2Push;
    }

    public String getBroadcastLastMessage() {
        return broadcastLastMessage;
    }

    public void setSystemWallMessage(String systemWallMessage) {
        SystemInfoMB.systemWallMessage = systemWallMessage;
    }

    public String getSystemWallMessage() {
        return systemWallMessage;
    }
    
    public static String getSystemWallMessageGlobal() {
        return systemWallMessage;
    }
}

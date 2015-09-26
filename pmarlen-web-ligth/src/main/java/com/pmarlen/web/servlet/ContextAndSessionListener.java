/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.web.servlet;

import com.pmarlen.model.Constants;
import com.tracktopell.jdbc.WEBDataSourceFacade;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 * @author aestrada
 */
public class ContextAndSessionListener implements ServletContextListener, HttpSessionListener {
    
    Logger logger = Logger.getLogger(ContextAndSessionListener.class.getName());
	
    private static SimpleDateFormat sdfDefault  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
    private static SimpleDateFormat sdfExtended = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS zzzzzz (Z)");
	public static final Hashtable<String , SessionInfo> sessionInfoHT = new Hashtable<String , SessionInfo>();
	public static final Hashtable<String , CajaSessionInfo> cajaSessionInfoHT = new Hashtable<String , CajaSessionInfo>();
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Date dateSystem = new Date();
		logger.trace("================++++++++++++++++++++++++++++++++++++++=============>>contextInitialized");
		logger.trace("contextInitialized: serverVersion="+Constants.getServerVersion());
		TimeZone defaultTZ = TimeZone.getDefault();		
        logger.trace("contextInitialized: TimeZone.getDefault()="+defaultTZ.getDisplayName()+", Time=defaultformat:"+sdfDefault.format(dateSystem)+", ExtendedFormat:"+sdfExtended.format(dateSystem));  
		logger.trace("contextInitialized: WEBDataSourceFacade.registerStrategy:");
        WEBDataSourceFacade.registerStrategy();		
		logger.trace("contextInitialized: ConnectionPoolKeepAliveService.getInstance().start:");
		ConnectionPoolKeepAliveService.getInstance().start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
		logger.trace("contextDestroyed: ConnectionPoolKeepAliveService.getInstance().stopRunning:");
		ConnectionPoolKeepAliveService.getInstance().stopRunning();
        logger.trace("contextDestroyed<<================++++++++++++++++++++++++++++++++++++++=============");		
    }

    @Override
    public void sessionCreated(HttpSessionEvent hse) {
		final HttpSession session = hse.getSession();
		//SessionInfo asi=new SessionInfo(session,"-");
		//sessionInfoHT.put(session.getId(),asi);
		logger.trace("sessionCreated["+session.getId()+"] ==============================================>>");
		logger.trace("sessionCreated["+session.getId()+"] CreationTime          :"+sdfDefault.format(session.getCreationTime()));
		logger.trace("sessionCreated["+session.getId()+"] LastAccessedTime      :"+sdfDefault.format(session.getLastAccessedTime()));
		logger.trace("sessionCreated["+session.getId()+"] MaxInactiveInterval   :"+session.getMaxInactiveInterval()+" secs.");		
		logger.trace("sessionCreated["+session.getId()+"] new                   ?"+session.isNew());
		logger.trace("contextInitialized: =========================================================>>End of initialization");
	}

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
		final HttpSession session = hse.getSession();
        logger.trace ("<<--["+session.getId()+"] sessionDestroyed");
		logger.trace("<<--["+session.getId()+"] was new ? "+session.isNew());
		logger.trace("<<--------------------------------------------------------------------------------");
		sessionInfoHT.remove(session.getId());
    }
}

package com.pmarlen.web.servlet;

import com.pmarlen.model.Constants;
import com.tracktopell.jdbc.WEBDataSourceFacade;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Date;
import java.util.Hashtable;
import java.util.Enumeration;
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
	private static String workingDir = ".";
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Date dateSystem = new Date();
		logger.info("================++++++++++++++++++++++++++++++++++++++=============>>contextInitialized");
		logger.info("contextInitialized: serverVersion="+Constants.getServerVersion());
		TimeZone defaultTZ = TimeZone.getDefault();		
        logger.info("contextInitialized: TimeZone.getDefault()="+defaultTZ.getDisplayName()+", Time=defaultformat:"+sdfDefault.format(dateSystem)+", ExtendedFormat:"+sdfExtended.format(dateSystem));  
        logger.info("contextInitialized: Locale:"+Locale.getDefault());
		logger.info("contextInitialized: SERVER PWD:"+System.getProperty("user.dir"));
		logger.info("contextInitialized: WEBDataSourceFacade.registerStrategy:");
        WEBDataSourceFacade.registerStrategy();		
		logger.info("contextInitialized: ConnectionPoolKeepAliveService.getInstance().start:");

		Enumeration ep=sce.getServletContext().getInitParameterNames();
		while( ep.hasMoreElements() ){
			String initParamX= ep.nextElement().toString();
			String initValueX = sce.getServletContext().getInitParameter(initParamX);
			logger.info("\tcontextInitialized: initParam: "+ initParamX + " = " + initValueX);
		}
	
		workingDir = sce.getServletContext().getInitParameter("pmarlen_work_dir");// pmarlen_work_dir
		logger.info("contextInitialized: workingDir    ="+workingDir +"? allparams="+ sce.getServletContext().getInitParameterNames());
		if(workingDir == null){
			logger.error("contextInitialized: pmarlen_work_dir is null ! ");
			workingDir = "/usr/local/pmarlen_PROD_work/";
		}
		com.pmarlen.businesslogic.reports.GeneradorImpresionPedidoVenta.setWorkingDir(workingDir);

		ConnectionPoolKeepAliveService.getInstance().start();
    }

	public static String getWorkingDir(){
		return workingDir;
	}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
		logger.info("contextDestroyed: ConnectionPoolKeepAliveService.getInstance().stopRunning:");
		ConnectionPoolKeepAliveService.getInstance().stopRunning();
        logger.info("contextDestroyed<<================++++++++++++++++++++++++++++++++++++++=============");		
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
        logger.trace("<<--["+session.getId()+"] sessionDestroyed");
		logger.trace("<<--["+session.getId()+"] was new ? "+session.isNew());
		logger.trace("<<--------------------------------------------------------------------------------");
		sessionInfoHT.remove(session.getId());
    }
}

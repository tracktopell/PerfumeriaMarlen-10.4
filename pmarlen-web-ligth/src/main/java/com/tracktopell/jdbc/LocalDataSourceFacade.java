/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tracktopell.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author alfredo
 */
public class LocalDataSourceFacade extends DataSourceFacade{
	
	private static Logger logger = Logger.getLogger(LocalDataSourceFacade.class.getName());
	private static Properties prop = new Properties();
	public static void registerStrategy(){
		try {
			prop.load(LocalDataSourceFacade.class.getResourceAsStream("/env_vars.properties"));
			logger.debug("prop="+prop);
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			DataSourceFacade.setStrategy(new LocalDataSourceFacade());
		}catch(Exception ioe){
			logger.error("Error:", ioe);
		}
		
	}
	
	public Connection getConnection() {
		
		Connection conn = null;
        try {   
			logger.debug("getConnection: properties="+prop);			
			
			String url = prop.getProperty("url");
			String usr = prop.getProperty("root_user");
			String pwd = prop.getProperty("root_password");
			
			conn = DriverManager.getConnection(url, usr, pwd);
		
        } catch (Exception e) {
            logger.error(e);
        } finally{
			return conn;
		}		
	}

	public Connection getConnectionCommiteable() {
		Connection conn = null;
        try {   
			logger.debug("getConnectionCommiteable: properties="+prop);			
			
			String url = prop.getProperty("url");
			String usr = prop.getProperty("root_user");
			String pwd = prop.getProperty("root_password");
			
			conn = DriverManager.getConnection(url, usr, pwd);
			conn.setAutoCommit(false);
        } catch (Exception e) {
            logger.error(e);
        } finally{
			return conn;
		}
	}
	
}

package com.pmarlen.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo estrada
 */
public class DownloadDBBackup extends HttpServlet {
	private final Logger logger = Logger.getLogger(DownloadDBBackup.class.getSimpleName());

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private static final String URI_DOWNLOAD  = "/PMDB104_PROD_mysqldump_LAST.sql.tgz";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//                    /usr/local/pmarlen_mysqldump/
		String pmbackupdir = getInitParameter("pmbackupdir");
		String uri=request.getRequestURI();
		OutputStream os = null;
		InputStream is = null;
		try {
			logger.debug("DownloadDBBackup: request.getRequestURI():"+request.getRequestURI());
			File fileForDownload=null;
			if( uri.endsWith(URI_DOWNLOAD) ){
				if(uri.endsWith(URI_DOWNLOAD)){
					fileForDownload =  new File(pmbackupdir+"/PMDB104_PROD_mysqldump_LAST.sql.tgz");
					response.setContentType("application/x-gzip");
				}
				
				logger.debug("DownloadDBBackup: fileForDownload="+fileForDownload);
				
				if(!fileForDownload.exists() || !fileForDownload.canRead()) {
					File symLinkForDist =  new File(pmbackupdir);
					if(!symLinkForDist.exists()){
						throw new IllegalStateException("NO SE HA CREADO LA SYM LINK:"+pmbackupdir+" EN ESTE SERVIDOR QUE APUNTE A EL DIR DE /usr/local/pmarlen_mysqldump/ ");
					}
					if(!fileForDownload.exists() || !fileForDownload.canRead()) {
						throw new IllegalStateException("NO SE PUEDE LEER ARCHIVO("+fileForDownload+") PARA:"+uri+", TAL VEZ FALTE EJECUTAR: mvn clean install");
					}
					throw new IllegalStateException("NO SE PUEDE LEER ARCHIVO PARA:"+uri);
				}
				
				logger.debug("OK, found, wrinting: ["+fileForDownload.length()+"] bytes.");
				response.setContentLength((int)fileForDownload.length());
				
				os = response.getOutputStream();
				is = new FileInputStream(fileForDownload);
				
				byte buffer[] = new byte[1024*16];
				int r;
				
				while((r=is.read(buffer, 0, buffer.length))!= -1){
					os.write(buffer, 0, r);
					os.flush();
				}
				buffer=null;
				
				is.close();				
			} else {
				throw new IllegalArgumentException("URI, ERRONEA:"+uri);
			}
		} catch(IllegalStateException ise){
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			logger.error(ise);
		} catch(IllegalArgumentException iae){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			logger.error(iae);
		} catch(Exception e){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			logger.error(e);			
		} finally {
			if(is != null){
				is.close();
			}
			if(os != null){
				os.close();
			}
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}

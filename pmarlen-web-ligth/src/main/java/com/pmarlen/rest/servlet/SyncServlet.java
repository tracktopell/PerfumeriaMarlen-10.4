/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pmarlen.rest.servlet;

import com.pmarlen.backend.dao.AlmacenDAO;
import com.pmarlen.backend.dao.AlmacenProductoDAO;
import com.pmarlen.backend.dao.ClienteDAO;
import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.FormaDePagoDAO;
import com.pmarlen.backend.dao.MetodoDePagoDAO;
import com.pmarlen.backend.dao.SucursalDAO;
import com.pmarlen.backend.dao.UsuarioDAO;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.backend.model.quickviews.SyncDTOPackage;
import com.pmarlen.rest.dto.P;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

import com.pmarlen.web.servlet.CajaSessionInfo;
import com.pmarlen.web.servlet.ContextAndSessionListener;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 *
 * @author alfredo
 */
public class SyncServlet extends HttpServlet {

	Logger logger=Logger.getLogger(SyncServlet.class.getName());
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		logger.trace("processRequest: uri:"+request.getRequestURI()+", queryString:"+request.getQueryString());
		InputStream is = null;		
		OutputStream os=null;
		
		String sucursalId=request.getParameter("sucursalId");
		String cajaId=request.getParameter("caja");
		String sessionId=request.getParameter("sessionId");
		String format=request.getParameter("format");
		String loggedIn=request.getParameter("loggedIn");
		String userAgent = request.getHeader("User-Agent");
		String sending = request.getParameter("sending");
		
		if(request.getQueryString()==null || sucursalId==null|| cajaId==null){					
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			logger.trace("processRequest: :( BAD REQUEST !");
			return;
		}

		if(format==null){
			format="iamalive";
		}
		if(loggedIn==null){
			loggedIn="null";
		}
		
		logger.debug("sucursalId="+sucursalId+", caja="+cajaId+", format="+format+", sessionId="+sessionId+",loggedIn="+loggedIn+", userAgent="+userAgent+", sending="+sending);
		
		CajaSessionInfo cajaSessionInfo = null;
		if(sessionId != null) {
			cajaSessionInfo = ContextAndSessionListener.cajaSessionInfoHT.get(sessionId);
			logger.trace(" cajaSessionInfo="+cajaSessionInfo);
			if(cajaSessionInfo == null) {
				logger.trace(" add new cajaSessionInfo, loggedIn="+loggedIn);
				cajaSessionInfo = new CajaSessionInfo();
				cajaSessionInfo.setSessionId(sessionId);
				cajaSessionInfo.setCaja(cajaId);
				cajaSessionInfo.setSucursal(sucursalId);				
				cajaSessionInfo.setRemoteAddr(request.getRemoteAddr());
				cajaSessionInfo.setUserAgent(userAgent);				
				ContextAndSessionListener.cajaSessionInfoHT.put(sessionId,cajaSessionInfo);
			}
			if(loggedIn != null){
				cajaSessionInfo.setLoggedIn(loggedIn);
			}
			cajaSessionInfo.setLastAccesedTime(System.currentTimeMillis());
		}
		try {
			if(sending != null && sending.equals("true")){
				int    contentLength       = request.getContentLength();
				String contentLengthHeader = request.getHeader("Content-Length");
				logger.debug("contentLength="+contentLength+", contentLengthHeader="+contentLengthHeader);
				
//				is = request.getInputStream();
//				if(contentLength> 0 && contentLength < 100) {
//					byte[] bytesSent = new byte[contentLength];
//					is.read(bytesSent, 0, contentLength);
//					is.close();
//					logger.debug("bytesSent=->"+new String(bytesSent)+"<-");
//				}
				final BufferedReader reader = request.getReader();
				String line=null;
				logger.debug("bytesSent:");
				while((line = reader.readLine()) != null){
					logger.debug("\tline=->"+reader.readLine()+"<-");
				}
			}
			
			if(format.equals("zip") || format.equals("json")){
				int sucId=new Integer(sucursalId);
				SyncDTOPackage s= new SyncDTOPackage();
				ArrayList<InventarioSucursalQuickView> xa = AlmacenProductoDAO.getInstance().findAllBySucursal(sucId);
				ArrayList<P> xb=new ArrayList<P>();
				for(InventarioSucursalQuickView xia: xa){
					xb.add(xia.generateFaccadeForREST());
				}
				s.setInventarioSucursalQVList(xb);
				s.setUsuarioList(UsuarioDAO.getInstance().findAllForRest());
				s.setClienteList(ClienteDAO.getInstance().findAll());
				s.setMetodoDePagoList(MetodoDePagoDAO.getInstance().findAll());
				s.setFormaDePagoList(FormaDePagoDAO.getInstance().findAll());
				s.setSucursal(SucursalDAO.getInstance().findBy(new Sucursal(sucId)));
				s.setAlmacenList(AlmacenDAO.getInstance().findBySucursal(sucId));

				ObjectMapper mapper = new ObjectMapper();
				ByteArrayOutputStream baos=new ByteArrayOutputStream();
				byte[] data=null;
				
				os=response.getOutputStream();
				
				if(format.equals("zip")){
					ZipOutputStream zos = new ZipOutputStream(baos);
					zos.putNextEntry(new ZipEntry("data.json"));				
					byte[] jsonData=mapper.writeValueAsBytes(s);
					zos.write(jsonData);
					zos.closeEntry();
					zos.finish();				

					baos.close();
					data=baos.toByteArray();

					response.setContentType("application/zip");		
					response.addHeader("Content-Disposition", "attachment; filename=data.zip");
					response.setContentLength(data.length);
					logger.trace("zip:ContentLength="+data.length+" bytes, jsonData.length="+jsonData.length);
					os.write(data);
					os.flush();
				} else if(format.equals("json")){

					mapper.writeValue(baos,s);
					data=baos.toByteArray();

					response.setContentType("application/json");		
					response.addHeader("Content-Disposition", "attachment; filename=data.json");
					response.setContentLength(data.length);
					logger.trace("json:ContentLength="+data.length+" bytes");
					os.write(data);
					os.flush();				
				}
			} else if(format.equals("iamalive")){
				response.setStatus(HttpServletResponse.SC_OK);
			}
			logger.trace("OK, finish !");
		} catch (Exception ex) {
			logger.error("error:", ex);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} finally {
			os.close();
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

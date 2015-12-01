<%-- 
    Document   : productos
    Created on : 30-nov-2015, 23:01:35
    Author     : alfredo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.pmarlen.backend.dao.DAOException"%>
<%@page import="com.pmarlen.backend.dao.ProductoDAO"%>
<%@page import="com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PMarlen-Productos</title>
<%
		String codigoBarras = request.getParameter("cb");
		
		EntradaSalidaDetalleQuickView p = null;
		String info = null;
		try {
			p = ProductoDAO.getInstance().findByCodigo(1, codigoBarras);
			if(p == null){
				throw new DAOException("NOT FOUND:"+codigoBarras);
			}
			//info = "NOMBRE:"+p.getProductoNombre()+"\nPRESENTACIÓN:"+p.getProductoPresentacion()+"\nPRECIO:"+Constants.dfCurrency.format(p.getApPrecio());
		} catch(DAOException de){		
			
		} catch(Exception de){		
			
		}


%>
    </head>
    <body>
        <h1></h1>
    </body>
</html>

package com.pmarlen.backend.model.quickviews;

import com.pmarlen.backend.model.*;
import java.io.Serializable;
import java.util.Set;
import java.util.MissingFormatArgumentException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Class for mapping DTO Entity of Table Pedido_Venta.
 * 
 * @author Tracktopell::jpa-builder @see  https://github.com/tracktopell/UtilProjects/tree/master/jpa-builder
 * @date 2014/12/29 17:58
 */

public class EntradaSalidaQuickView extends EntradaSalida {
	
	private String usuarioNombreCompleto;
	
	private Integer usuarioClave;
	
	private String estadoDescripcion;
	
	private String cdfNumCFD;
	
	private String cfdCallingErrorResult;
	
	private String clienteRFC;
	
	private String clienteRazonSocial;
	
	private String clienteNombreEstablecimiento;
	
	private String clienteDirFacturacion;
	
	private String formaDePagoDescripcion;
	
	private String metodoDePagoDescripcion;
	
	private String sucursalNombre;
	
	private String sucursalDireccion;
	
	private int numElementos;
	
	private Double importeBruto;
	
	private Double importeIVA;
	
	private Double importeNoGravado;
	
	private Double importeTotal;
	
	private Double importeDescuento;
	
	private ArrayList<EntradaSalidaEstadoQuickView>  pveList;
	
	private String estadoActualUsuarioEmail;
	
	private String estadoActualUsuarioNombreCompleto;
	
	private java.sql.Timestamp estadoActualFecha;
	
	private String traspasoSucOriNombre;
	
	private String traspasoSucOriClave;	
	
	private String traspasoSucDesNombre;
	
	private String traspasoSucDesClave;

    private String CFDContenidoXML;

    /** 
     * Default Constructor
     */
    public EntradaSalidaQuickView() {
    }

	/**
	 * @return the usuarioNombreCompleto
	 */
	public String getUsuarioNombreCompleto() {
		return usuarioNombreCompleto;
	}

	/**
	 * @param usuarioNombreCompleto the usuarioNombreCompleto to set
	 */
	public void setUsuarioNombreCompleto(String usuarioNombreCompleto) {
		this.usuarioNombreCompleto = usuarioNombreCompleto;
	}

	/**
	 * @return the estadoDescripcion
	 */
	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}

	/**
	 * @param estadoDescripcion the estadoDescripcion to set
	 */
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}

	/**
	 * @return the cdfNumCFD
	 */
	public String getCdfNumCFD() {
		return cdfNumCFD;
	}

	/**
	 * @param cdfNumCFD the cdfNumCFD to set
	 */
	public void setCdfNumCFD(String cdfNumCFD) {
		this.cdfNumCFD = cdfNumCFD;
	}

	/**
	 * 
	 * @return 
	 */
	public String getCfdCallingErrorResult() {
		return cfdCallingErrorResult;
	}

	/**
	 * @param cfdCallingErrorResult 
	 */
	public void setCfdCallingErrorResult(String cfdCallingErrorResult) {
		this.cfdCallingErrorResult = cfdCallingErrorResult;
	}
	
	
	
	/**
	 * @return the clienteRFC
	 */
	public String getClienteRFC() {
		return clienteRFC;
	}

	/**
	 * @param clienteRFC the clienteRFC to set
	 */
	public void setClienteRFC(String clienteRFC) {
		this.clienteRFC = clienteRFC;
	}

	/**
	 * @return the clienteRazonSocial
	 */
	public String getClienteRazonSocial() {
		return clienteRazonSocial;
	}

	/**
	 * @param clienteRazonSocial the clienteRazonSocial to set
	 */
	public void setClienteRazonSocial(String clienteRazonSocial) {
		this.clienteRazonSocial = clienteRazonSocial;
	}

	/**
	 * @return the clienteNombreEstablecimiento
	 */
	public String getClienteNombreEstablecimiento() {
		return clienteNombreEstablecimiento;
	}

	/**
	 * @param clienteNombreEstablecimiento the clienteNombreEstablecimiento to set
	 */
	public void setClienteNombreEstablecimiento(String clienteNombreEstablecimiento) {
		this.clienteNombreEstablecimiento = clienteNombreEstablecimiento;
	}

	/**
	 * @return the formaDePagoDescripcion
	 */
	public String getFormaDePagoDescripcion() {
		return formaDePagoDescripcion;
	}

	/**
	 * @param formaDePagoDescripcion the formaDePagoDescripcion to set
	 */
	public void setFormaDePagoDescripcion(String formaDePagoDescripcion) {
		this.formaDePagoDescripcion = formaDePagoDescripcion;
	}

	/**
	 * @return the metodoDePagoDescripcion
	 */
	public String getMetodoDePagoDescripcion() {
		return metodoDePagoDescripcion;
	}

	/**
	 * @param metodoDePagoDescripcion the metodoDePagoDescripcion to set
	 */
	public void setMetodoDePagoDescripcion(String metodoDePagoDescripcion) {
		this.metodoDePagoDescripcion = metodoDePagoDescripcion;
	}

	/**
	 * @return the sucursalNombre
	 */
	public String getSucursalNombre() {
		return sucursalNombre;
	}

	/**
	 * @param sucursalNombre the sucursalNombre to set
	 */
	public void setSucursalNombre(String sucursalNombre) {
		this.sucursalNombre = sucursalNombre;
	}

	/**
	 * @return the numElementos
	 */
	public int getNumElementos() {
		return numElementos;
	}

	/**
	 * @param numElementos the numElementos to set
	 */
	public void setNumElementos(int numElementos) {
		this.numElementos = numElementos;
	}

	/**
	 * @return the importeBruto
	 */
	public Double getImporteBruto() {
		return importeBruto;
	}

	/**
	 * @param importeBruto the importeBruto to set
	 */
	public void setImporteBruto(Double importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * @return the importeIVA
	 */
	public Double getImporteIVA() {
		return importeIVA;
	}

	/**
	 * @param importeIVA the importeIVA to set
	 */
	public void setImporteIVA(Double importeIVA) {
		this.importeIVA = importeIVA;
	}

	/**
	 * @return the importeNoGravado
	 */
	public Double getImporteNoGravado() {
		return importeNoGravado;
	}

	/**
	 * @param importeNoGravado the importeNoGravado to set
	 */
	public void setImporteNoGravado(Double importeNoGravado) {
		this.importeNoGravado = importeNoGravado;
	}

	/**
	 * @return the importeTotal
	 */
	public Double getImporteTotal() {
		return importeTotal;
	}

	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * @return the importeDescuento
	 */
	public Double getImporteDescuento() {
		return importeDescuento;
	}

	/**
	 * @param importeDescuento the importeDescuento to set
	 */
	public void setImporteDescuento(Double importeDescuento) {
		this.importeDescuento = importeDescuento;
	}

	public ArrayList<EntradaSalidaEstadoQuickView> getPveList() {
		return pveList;
	}

	public void setPveList(ArrayList<EntradaSalidaEstadoQuickView> pveList) {
		this.pveList = pveList;
	}

	/**
	 * @return the estadoActualUsuarioEmail
	 */
	public String getEstadoActualUsuarioEmail() {
		return estadoActualUsuarioEmail;
	}

	/**
	 * @param estadoActualUsuarioEmail the estadoActualUsuarioEmail to set
	 */
	public void setEstadoActualUsuarioEmail(String estadoActualUsuarioEmail) {
		this.estadoActualUsuarioEmail = estadoActualUsuarioEmail;
	}

	/**
	 * @return the estadoActualUsuarioNombreCompleto
	 */
	public String getEstadoActualUsuarioNombreCompleto() {
		return estadoActualUsuarioNombreCompleto;
	}

	/**
	 * @param estadoActualUsuarioNombreCompleto the estadoActualUsuarioNombreCompleto to set
	 */
	public void setEstadoActualUsuarioNombreCompleto(String estadoActualUsuarioNombreCompleto) {
		this.estadoActualUsuarioNombreCompleto = estadoActualUsuarioNombreCompleto;
	}

	/**
	 * @return the estadoActualFecha
	 */
	public java.sql.Timestamp getEstadoActualFecha() {
		return estadoActualFecha;
	}

	/**
	 * @param estadoActualFecha the estadoActualFecha to set
	 */
	public void setEstadoActualFecha(java.sql.Timestamp estadoActualFecha) {
		this.estadoActualFecha = estadoActualFecha;
	}

	/**
	 * @return the traspasoSucOriNombre
	 */
	public String getTraspasoSucOriNombre() {
		return traspasoSucOriNombre;
	}

	/**
	 * @param traspasoSucOriNombre the traspasoSucOriNombre to set
	 */
	public void setTraspasoSucOriNombre(String traspasoSucOriNombre) {
		this.traspasoSucOriNombre = traspasoSucOriNombre;
	}

	/**
	 * @return the traspasoSucOriClave
	 */
	public String getTraspasoSucOriClave() {
		return traspasoSucOriClave;
	}

	/**
	 * @param traspasoSucOriClave the traspasoSucOriClave to set
	 */
	public void setTraspasoSucOriClave(String traspasoSucOriClave) {
		this.traspasoSucOriClave = traspasoSucOriClave;
	}

	/**
	 * @return the traspasoSucDesNombre
	 */
	public String getTraspasoSucDesNombre() {
		return traspasoSucDesNombre;
	}

	/**
	 * @param traspasoSucDesNombre the traspasoSucDesNombre to set
	 */
	public void setTraspasoSucDesNombre(String traspasoSucDesNombre) {
		this.traspasoSucDesNombre = traspasoSucDesNombre;
	}

	/**
	 * @return the traspasoSucDesClave
	 */
	public String getTraspasoSucDesClave() {
		return traspasoSucDesClave;
	}

	/**
	 * @param traspasoSucDesClave the traspasoSucDesClave to set
	 */
	public void setTraspasoSucDesClave(String traspasoSucDesClave) {
		this.traspasoSucDesClave = traspasoSucDesClave;
	}

	/**
	 * @return the usuarioClave
	 */
	public Integer getUsuarioClave() {
		return usuarioClave;
	}

	/**
	 * @param usuarioClave the usuarioClave to set
	 */
	public void setUsuarioClave(Integer usuarioClave) {
		this.usuarioClave = usuarioClave;
	}

	/**
	 * @return the sucursalDireccion
	 */
	public String getSucursalDireccion() {
		return sucursalDireccion;
	}

	/**
	 * @param sucursalDireccion the sucursalDireccion to set
	 */
	public void setSucursalDireccion(String sucursalDireccion) {
		this.sucursalDireccion = sucursalDireccion;
	}

	/**
	 * @return the clienteDirFacturacion
	 */
	public String getClienteDirFacturacion() {
		return clienteDirFacturacion;
	}

	/**
	 * @param clienteDirFacturacion the clienteDirFacturacion to set
	 */
	public void setClienteDirFacturacion(String clienteDirFacturacion) {
		this.clienteDirFacturacion = clienteDirFacturacion;
	}
    
    public String getCFDContenidoXML() {
        return CFDContenidoXML;
    }

    public void setCFDContenidoXML(String CFDContenidoXML) {
        this.CFDContenidoXML = CFDContenidoXML;
    }
    	
}

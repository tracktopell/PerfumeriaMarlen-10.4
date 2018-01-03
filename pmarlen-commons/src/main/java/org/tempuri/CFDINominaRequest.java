
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CFDINominaRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CFDINominaRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contrasena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Datosnomina" type="{http://tempuri.org/}DatosNominaCFDI" minOccurs="0"/>
 *         &lt;element name="empleado" type="{http://tempuri.org/}EmpleadoCFDI" minOccurs="0"/>
 *         &lt;element name="concepto" type="{http://tempuri.org/}ConceptoNomina" minOccurs="0"/>
 *         &lt;element name="Percepciones" type="{http://tempuri.org/}Percepciones" minOccurs="0"/>
 *         &lt;element name="Deducciones" type="{http://tempuri.org/}Deducciones" minOccurs="0"/>
 *         &lt;element name="OtroPago" type="{http://tempuri.org/}OtrosPagos" minOccurs="0"/>
 *         &lt;element name="Incapacidades" type="{http://tempuri.org/}Incapacidades" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CFDINominaRequest", propOrder = {
    "usuario",
    "contrasena",
    "datosnomina",
    "empleado",
    "concepto",
    "percepciones",
    "deducciones",
    "otroPago",
    "incapacidades"
})
public class CFDINominaRequest {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "Datosnomina")
    protected DatosNominaCFDI datosnomina;
    protected EmpleadoCFDI empleado;
    protected ConceptoNomina concepto;
    @XmlElement(name = "Percepciones")
    protected Percepciones percepciones;
    @XmlElement(name = "Deducciones")
    protected Deducciones deducciones;
    @XmlElement(name = "OtroPago")
    protected OtrosPagos otroPago;
    @XmlElement(name = "Incapacidades")
    protected Incapacidades incapacidades;

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad contrasena.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Define el valor de la propiedad contrasena.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasena(String value) {
        this.contrasena = value;
    }

    /**
     * Obtiene el valor de la propiedad datosnomina.
     * 
     * @return
     *     possible object is
     *     {@link DatosNominaCFDI }
     *     
     */
    public DatosNominaCFDI getDatosnomina() {
        return datosnomina;
    }

    /**
     * Define el valor de la propiedad datosnomina.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosNominaCFDI }
     *     
     */
    public void setDatosnomina(DatosNominaCFDI value) {
        this.datosnomina = value;
    }

    /**
     * Obtiene el valor de la propiedad empleado.
     * 
     * @return
     *     possible object is
     *     {@link EmpleadoCFDI }
     *     
     */
    public EmpleadoCFDI getEmpleado() {
        return empleado;
    }

    /**
     * Define el valor de la propiedad empleado.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpleadoCFDI }
     *     
     */
    public void setEmpleado(EmpleadoCFDI value) {
        this.empleado = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link ConceptoNomina }
     *     
     */
    public ConceptoNomina getConcepto() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptoNomina }
     *     
     */
    public void setConcepto(ConceptoNomina value) {
        this.concepto = value;
    }

    /**
     * Obtiene el valor de la propiedad percepciones.
     * 
     * @return
     *     possible object is
     *     {@link Percepciones }
     *     
     */
    public Percepciones getPercepciones() {
        return percepciones;
    }

    /**
     * Define el valor de la propiedad percepciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Percepciones }
     *     
     */
    public void setPercepciones(Percepciones value) {
        this.percepciones = value;
    }

    /**
     * Obtiene el valor de la propiedad deducciones.
     * 
     * @return
     *     possible object is
     *     {@link Deducciones }
     *     
     */
    public Deducciones getDeducciones() {
        return deducciones;
    }

    /**
     * Define el valor de la propiedad deducciones.
     * 
     * @param value
     *     allowed object is
     *     {@link Deducciones }
     *     
     */
    public void setDeducciones(Deducciones value) {
        this.deducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad otroPago.
     * 
     * @return
     *     possible object is
     *     {@link OtrosPagos }
     *     
     */
    public OtrosPagos getOtroPago() {
        return otroPago;
    }

    /**
     * Define el valor de la propiedad otroPago.
     * 
     * @param value
     *     allowed object is
     *     {@link OtrosPagos }
     *     
     */
    public void setOtroPago(OtrosPagos value) {
        this.otroPago = value;
    }

    /**
     * Obtiene el valor de la propiedad incapacidades.
     * 
     * @return
     *     possible object is
     *     {@link Incapacidades }
     *     
     */
    public Incapacidades getIncapacidades() {
        return incapacidades;
    }

    /**
     * Define el valor de la propiedad incapacidades.
     * 
     * @param value
     *     allowed object is
     *     {@link Incapacidades }
     *     
     */
    public void setIncapacidades(Incapacidades value) {
        this.incapacidades = value;
    }

}

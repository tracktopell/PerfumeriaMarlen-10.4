
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contrasena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosCFD" type="{https://cfd.sicofi.com.mx}DatosNomina" minOccurs="0"/>
 *         &lt;element name="Receptor" type="{https://cfd.sicofi.com.mx}Empleado" minOccurs="0"/>
 *         &lt;element name="Conceptos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Percepciones" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Deducciones" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Incapacidades" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="HorasExtras" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="XMLAddenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuario",
    "contrasena",
    "datosCFD",
    "receptor",
    "conceptos",
    "impuestos",
    "percepciones",
    "deducciones",
    "incapacidades",
    "horasExtras",
    "xmlAddenda"
})
@XmlRootElement(name = "GeneraCFDINomina")
public class GeneraCFDINomina {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "DatosCFD")
    protected DatosNomina datosCFD;
    @XmlElement(name = "Receptor")
    protected Empleado receptor;
    @XmlElement(name = "Conceptos")
    protected ArrayOfAnyType conceptos;
    @XmlElement(name = "Impuestos")
    protected ArrayOfAnyType impuestos;
    @XmlElement(name = "Percepciones")
    protected ArrayOfAnyType percepciones;
    @XmlElement(name = "Deducciones")
    protected ArrayOfAnyType deducciones;
    @XmlElement(name = "Incapacidades")
    protected ArrayOfAnyType incapacidades;
    @XmlElement(name = "HorasExtras")
    protected ArrayOfAnyType horasExtras;
    @XmlElement(name = "XMLAddenda")
    protected String xmlAddenda;

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
     * Obtiene el valor de la propiedad datosCFD.
     * 
     * @return
     *     possible object is
     *     {@link DatosNomina }
     *     
     */
    public DatosNomina getDatosCFD() {
        return datosCFD;
    }

    /**
     * Define el valor de la propiedad datosCFD.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosNomina }
     *     
     */
    public void setDatosCFD(DatosNomina value) {
        this.datosCFD = value;
    }

    /**
     * Obtiene el valor de la propiedad receptor.
     * 
     * @return
     *     possible object is
     *     {@link Empleado }
     *     
     */
    public Empleado getReceptor() {
        return receptor;
    }

    /**
     * Define el valor de la propiedad receptor.
     * 
     * @param value
     *     allowed object is
     *     {@link Empleado }
     *     
     */
    public void setReceptor(Empleado value) {
        this.receptor = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getConceptos() {
        return conceptos;
    }

    /**
     * Define el valor de la propiedad conceptos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setConceptos(ArrayOfAnyType value) {
        this.conceptos = value;
    }

    /**
     * Obtiene el valor de la propiedad impuestos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getImpuestos() {
        return impuestos;
    }

    /**
     * Define el valor de la propiedad impuestos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setImpuestos(ArrayOfAnyType value) {
        this.impuestos = value;
    }

    /**
     * Obtiene el valor de la propiedad percepciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getPercepciones() {
        return percepciones;
    }

    /**
     * Define el valor de la propiedad percepciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setPercepciones(ArrayOfAnyType value) {
        this.percepciones = value;
    }

    /**
     * Obtiene el valor de la propiedad deducciones.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getDeducciones() {
        return deducciones;
    }

    /**
     * Define el valor de la propiedad deducciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setDeducciones(ArrayOfAnyType value) {
        this.deducciones = value;
    }

    /**
     * Obtiene el valor de la propiedad incapacidades.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getIncapacidades() {
        return incapacidades;
    }

    /**
     * Define el valor de la propiedad incapacidades.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setIncapacidades(ArrayOfAnyType value) {
        this.incapacidades = value;
    }

    /**
     * Obtiene el valor de la propiedad horasExtras.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getHorasExtras() {
        return horasExtras;
    }

    /**
     * Define el valor de la propiedad horasExtras.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setHorasExtras(ArrayOfAnyType value) {
        this.horasExtras = value;
    }

    /**
     * Obtiene el valor de la propiedad xmlAddenda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLAddenda() {
        return xmlAddenda;
    }

    /**
     * Define el valor de la propiedad xmlAddenda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLAddenda(String value) {
        this.xmlAddenda = value;
    }

}

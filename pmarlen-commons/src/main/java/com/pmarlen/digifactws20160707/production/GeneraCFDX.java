
package com.pmarlen.digifactws20160707.production;

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
 *         &lt;element name="DatosCFD" type="{https://cfd.sicofi.com.mx}ObjetoX" minOccurs="0"/>
 *         &lt;element name="Receptor" type="{https://cfd.sicofi.com.mx}ObjetoX" minOccurs="0"/>
 *         &lt;element name="Conceptos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
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
    "xmlAddenda"
})
@XmlRootElement(name = "GeneraCFDX")
public class GeneraCFDX {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "DatosCFD")
    protected ObjetoX datosCFD;
    @XmlElement(name = "Receptor")
    protected ObjetoX receptor;
    @XmlElement(name = "Conceptos")
    protected ArrayOfAnyType conceptos;
    @XmlElement(name = "Impuestos")
    protected ArrayOfAnyType impuestos;
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
     *     {@link ObjetoX }
     *     
     */
    public ObjetoX getDatosCFD() {
        return datosCFD;
    }

    /**
     * Define el valor de la propiedad datosCFD.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjetoX }
     *     
     */
    public void setDatosCFD(ObjetoX value) {
        this.datosCFD = value;
    }

    /**
     * Obtiene el valor de la propiedad receptor.
     * 
     * @return
     *     possible object is
     *     {@link ObjetoX }
     *     
     */
    public ObjetoX getReceptor() {
        return receptor;
    }

    /**
     * Define el valor de la propiedad receptor.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjetoX }
     *     
     */
    public void setReceptor(ObjetoX value) {
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

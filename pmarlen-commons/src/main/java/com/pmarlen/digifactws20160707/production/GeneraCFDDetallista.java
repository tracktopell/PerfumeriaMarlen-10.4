
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
 *         &lt;element name="DatosCFD" type="{https://cfd.sicofi.com.mx}DatosCFD" minOccurs="0"/>
 *         &lt;element name="Receptor" type="{https://cfd.sicofi.com.mx}Receptor" minOccurs="0"/>
 *         &lt;element name="Conceptos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="XMLAddenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Complemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COComplemento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "xmlAddenda",
    "complemento",
    "coComplemento"
})
@XmlRootElement(name = "GeneraCFDDetallista")
public class GeneraCFDDetallista {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "DatosCFD")
    protected DatosCFD datosCFD;
    @XmlElement(name = "Receptor")
    protected Receptor receptor;
    @XmlElement(name = "Conceptos")
    protected ArrayOfAnyType conceptos;
    @XmlElement(name = "Impuestos")
    protected ArrayOfAnyType impuestos;
    @XmlElement(name = "XMLAddenda")
    protected String xmlAddenda;
    @XmlElement(name = "Complemento")
    protected String complemento;
    @XmlElement(name = "COComplemento")
    protected String coComplemento;

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
     *     {@link DatosCFD }
     *     
     */
    public DatosCFD getDatosCFD() {
        return datosCFD;
    }

    /**
     * Define el valor de la propiedad datosCFD.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosCFD }
     *     
     */
    public void setDatosCFD(DatosCFD value) {
        this.datosCFD = value;
    }

    /**
     * Obtiene el valor de la propiedad receptor.
     * 
     * @return
     *     possible object is
     *     {@link Receptor }
     *     
     */
    public Receptor getReceptor() {
        return receptor;
    }

    /**
     * Define el valor de la propiedad receptor.
     * 
     * @param value
     *     allowed object is
     *     {@link Receptor }
     *     
     */
    public void setReceptor(Receptor value) {
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

    /**
     * Obtiene el valor de la propiedad complemento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Define el valor de la propiedad complemento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

    /**
     * Obtiene el valor de la propiedad coComplemento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOComplemento() {
        return coComplemento;
    }

    /**
     * Define el valor de la propiedad coComplemento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOComplemento(String value) {
        this.coComplemento = value;
    }

}


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
 *         &lt;element name="Datos" type="{https://cfd.sicofi.com.mx}DatosConstanciaRetencion" minOccurs="0"/>
 *         &lt;element name="Receptor" type="{https://cfd.sicofi.com.mx}ReceptorConstanciaRetencion" minOccurs="0"/>
 *         &lt;element name="impuestosRetencion" type="{https://cfd.sicofi.com.mx}ArrayOfImpuestoRetencion" minOccurs="0"/>
 *         &lt;element name="complementosRetencion" type="{https://cfd.sicofi.com.mx}ComplementosRetenciones" minOccurs="0"/>
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
    "datos",
    "receptor",
    "impuestosRetencion",
    "complementosRetencion",
    "xmlAddenda"
})
@XmlRootElement(name = "GeneraRetencion")
public class GeneraRetencion {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "Datos")
    protected DatosConstanciaRetencion datos;
    @XmlElement(name = "Receptor")
    protected ReceptorConstanciaRetencion receptor;
    protected ArrayOfImpuestoRetencion impuestosRetencion;
    protected ComplementosRetenciones complementosRetencion;
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
     * Obtiene el valor de la propiedad datos.
     * 
     * @return
     *     possible object is
     *     {@link DatosConstanciaRetencion }
     *     
     */
    public DatosConstanciaRetencion getDatos() {
        return datos;
    }

    /**
     * Define el valor de la propiedad datos.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosConstanciaRetencion }
     *     
     */
    public void setDatos(DatosConstanciaRetencion value) {
        this.datos = value;
    }

    /**
     * Obtiene el valor de la propiedad receptor.
     * 
     * @return
     *     possible object is
     *     {@link ReceptorConstanciaRetencion }
     *     
     */
    public ReceptorConstanciaRetencion getReceptor() {
        return receptor;
    }

    /**
     * Define el valor de la propiedad receptor.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceptorConstanciaRetencion }
     *     
     */
    public void setReceptor(ReceptorConstanciaRetencion value) {
        this.receptor = value;
    }

    /**
     * Obtiene el valor de la propiedad impuestosRetencion.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfImpuestoRetencion }
     *     
     */
    public ArrayOfImpuestoRetencion getImpuestosRetencion() {
        return impuestosRetencion;
    }

    /**
     * Define el valor de la propiedad impuestosRetencion.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfImpuestoRetencion }
     *     
     */
    public void setImpuestosRetencion(ArrayOfImpuestoRetencion value) {
        this.impuestosRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad complementosRetencion.
     * 
     * @return
     *     possible object is
     *     {@link ComplementosRetenciones }
     *     
     */
    public ComplementosRetenciones getComplementosRetencion() {
        return complementosRetencion;
    }

    /**
     * Define el valor de la propiedad complementosRetencion.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplementosRetenciones }
     *     
     */
    public void setComplementosRetencion(ComplementosRetenciones value) {
        this.complementosRetencion = value;
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

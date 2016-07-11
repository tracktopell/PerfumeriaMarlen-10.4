
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
 *         &lt;element name="datosRetencion" type="{https://cfd.sicofi.com.mx}DatosRetencion" minOccurs="0"/>
 *         &lt;element name="emisorRetencion" type="{https://cfd.sicofi.com.mx}EmisorRetencion" minOccurs="0"/>
 *         &lt;element name="receptorRetencion" type="{https://cfd.sicofi.com.mx}ReceptorRetencion" minOccurs="0"/>
 *         &lt;element name="impuestosRetencion" type="{https://cfd.sicofi.com.mx}ArrayOfImpuestoRetencion" minOccurs="0"/>
 *         &lt;element name="complementosRetencion" type="{https://cfd.sicofi.com.mx}ComplementosRetenciones" minOccurs="0"/>
 *         &lt;element name="Addenda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "datosRetencion",
    "emisorRetencion",
    "receptorRetencion",
    "impuestosRetencion",
    "complementosRetencion",
    "addenda"
})
@XmlRootElement(name = "TimbraRetencion")
public class TimbraRetencion {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    protected DatosRetencion datosRetencion;
    protected EmisorRetencion emisorRetencion;
    protected ReceptorRetencion receptorRetencion;
    protected ArrayOfImpuestoRetencion impuestosRetencion;
    protected ComplementosRetenciones complementosRetencion;
    @XmlElement(name = "Addenda")
    protected String addenda;

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
     * Obtiene el valor de la propiedad datosRetencion.
     * 
     * @return
     *     possible object is
     *     {@link DatosRetencion }
     *     
     */
    public DatosRetencion getDatosRetencion() {
        return datosRetencion;
    }

    /**
     * Define el valor de la propiedad datosRetencion.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosRetencion }
     *     
     */
    public void setDatosRetencion(DatosRetencion value) {
        this.datosRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad emisorRetencion.
     * 
     * @return
     *     possible object is
     *     {@link EmisorRetencion }
     *     
     */
    public EmisorRetencion getEmisorRetencion() {
        return emisorRetencion;
    }

    /**
     * Define el valor de la propiedad emisorRetencion.
     * 
     * @param value
     *     allowed object is
     *     {@link EmisorRetencion }
     *     
     */
    public void setEmisorRetencion(EmisorRetencion value) {
        this.emisorRetencion = value;
    }

    /**
     * Obtiene el valor de la propiedad receptorRetencion.
     * 
     * @return
     *     possible object is
     *     {@link ReceptorRetencion }
     *     
     */
    public ReceptorRetencion getReceptorRetencion() {
        return receptorRetencion;
    }

    /**
     * Define el valor de la propiedad receptorRetencion.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceptorRetencion }
     *     
     */
    public void setReceptorRetencion(ReceptorRetencion value) {
        this.receptorRetencion = value;
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
     * Obtiene el valor de la propiedad addenda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddenda() {
        return addenda;
    }

    /**
     * Define el valor de la propiedad addenda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddenda(String value) {
        this.addenda = value;
    }

}

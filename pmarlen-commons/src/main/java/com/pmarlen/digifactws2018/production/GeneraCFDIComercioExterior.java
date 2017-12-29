
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
 *         &lt;element name="DatosCFD" type="{https://cfd.sicofi.com.mx}DatosCFD" minOccurs="0"/>
 *         &lt;element name="Receptor" type="{https://cfd.sicofi.com.mx}Receptor" minOccurs="0"/>
 *         &lt;element name="Conceptos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
 *         &lt;element name="DatosComercio" type="{https://cfd.sicofi.com.mx}DatosComercioExterior" minOccurs="0"/>
 *         &lt;element name="Emcomercio" type="{https://cfd.sicofi.com.mx}EmisorComercioExterior" minOccurs="0"/>
 *         &lt;element name="ReceptorComercio" type="{https://cfd.sicofi.com.mx}ReceptorComercioExterior" minOccurs="0"/>
 *         &lt;element name="DestinatarioComercio" type="{https://cfd.sicofi.com.mx}DestinatarioComercioExterior" minOccurs="0"/>
 *         &lt;element name="Mercancias" type="{https://cfd.sicofi.com.mx}ArrayOfAnyType" minOccurs="0"/>
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
    "datosComercio",
    "emcomercio",
    "receptorComercio",
    "destinatarioComercio",
    "mercancias",
    "xmlAddenda"
})
@XmlRootElement(name = "GeneraCFDIComercioExterior")
public class GeneraCFDIComercioExterior {

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
    @XmlElement(name = "DatosComercio")
    protected DatosComercioExterior datosComercio;
    @XmlElement(name = "Emcomercio")
    protected EmisorComercioExterior emcomercio;
    @XmlElement(name = "ReceptorComercio")
    protected ReceptorComercioExterior receptorComercio;
    @XmlElement(name = "DestinatarioComercio")
    protected DestinatarioComercioExterior destinatarioComercio;
    @XmlElement(name = "Mercancias")
    protected ArrayOfAnyType mercancias;
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
     * Obtiene el valor de la propiedad datosComercio.
     * 
     * @return
     *     possible object is
     *     {@link DatosComercioExterior }
     *     
     */
    public DatosComercioExterior getDatosComercio() {
        return datosComercio;
    }

    /**
     * Define el valor de la propiedad datosComercio.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosComercioExterior }
     *     
     */
    public void setDatosComercio(DatosComercioExterior value) {
        this.datosComercio = value;
    }

    /**
     * Obtiene el valor de la propiedad emcomercio.
     * 
     * @return
     *     possible object is
     *     {@link EmisorComercioExterior }
     *     
     */
    public EmisorComercioExterior getEmcomercio() {
        return emcomercio;
    }

    /**
     * Define el valor de la propiedad emcomercio.
     * 
     * @param value
     *     allowed object is
     *     {@link EmisorComercioExterior }
     *     
     */
    public void setEmcomercio(EmisorComercioExterior value) {
        this.emcomercio = value;
    }

    /**
     * Obtiene el valor de la propiedad receptorComercio.
     * 
     * @return
     *     possible object is
     *     {@link ReceptorComercioExterior }
     *     
     */
    public ReceptorComercioExterior getReceptorComercio() {
        return receptorComercio;
    }

    /**
     * Define el valor de la propiedad receptorComercio.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceptorComercioExterior }
     *     
     */
    public void setReceptorComercio(ReceptorComercioExterior value) {
        this.receptorComercio = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatarioComercio.
     * 
     * @return
     *     possible object is
     *     {@link DestinatarioComercioExterior }
     *     
     */
    public DestinatarioComercioExterior getDestinatarioComercio() {
        return destinatarioComercio;
    }

    /**
     * Define el valor de la propiedad destinatarioComercio.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatarioComercioExterior }
     *     
     */
    public void setDestinatarioComercio(DestinatarioComercioExterior value) {
        this.destinatarioComercio = value;
    }

    /**
     * Obtiene el valor de la propiedad mercancias.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public ArrayOfAnyType getMercancias() {
        return mercancias;
    }

    /**
     * Define el valor de la propiedad mercancias.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAnyType }
     *     
     */
    public void setMercancias(ArrayOfAnyType value) {
        this.mercancias = value;
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

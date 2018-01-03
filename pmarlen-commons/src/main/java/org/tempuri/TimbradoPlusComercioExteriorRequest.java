
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para TimbradoPlusComercioExteriorRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TimbradoPlusComercioExteriorRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contrasena" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DatosComercioExterior" type="{http://tempuri.org/}DatosComercioExteriorTimbrado" minOccurs="0"/>
 *         &lt;element name="EmisorComercioExterior" type="{http://tempuri.org/}EmisorComercioExteriorTimbrado" minOccurs="0"/>
 *         &lt;element name="ReceptorComercioExterior" type="{http://tempuri.org/}ReceptorComercioExteriorTimbrado" minOccurs="0"/>
 *         &lt;element name="Conceptos" type="{http://tempuri.org/}ConceptosComercioTimbrado" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{http://tempuri.org/}ImpuestosComercio" minOccurs="0"/>
 *         &lt;element name="propietario" type="{http://tempuri.org/}PropietarioComercioExterior" minOccurs="0"/>
 *         &lt;element name="Destinatario" type="{http://tempuri.org/}DestinatariosComercioExterior" minOccurs="0"/>
 *         &lt;element name="Mercancias" type="{http://tempuri.org/}MercanciasComercioExterior" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimbradoPlusComercioExteriorRequest", propOrder = {
    "usuario",
    "contrasena",
    "datosComercioExterior",
    "emisorComercioExterior",
    "receptorComercioExterior",
    "conceptos",
    "impuestos",
    "propietario",
    "destinatario",
    "mercancias"
})
public class TimbradoPlusComercioExteriorRequest {

    @XmlElement(name = "Usuario")
    protected String usuario;
    @XmlElement(name = "Contrasena")
    protected String contrasena;
    @XmlElement(name = "DatosComercioExterior")
    protected DatosComercioExteriorTimbrado datosComercioExterior;
    @XmlElement(name = "EmisorComercioExterior")
    protected EmisorComercioExteriorTimbrado emisorComercioExterior;
    @XmlElement(name = "ReceptorComercioExterior")
    protected ReceptorComercioExteriorTimbrado receptorComercioExterior;
    @XmlElement(name = "Conceptos")
    protected ConceptosComercioTimbrado conceptos;
    @XmlElement(name = "Impuestos")
    protected ImpuestosComercio impuestos;
    protected PropietarioComercioExterior propietario;
    @XmlElement(name = "Destinatario")
    protected DestinatariosComercioExterior destinatario;
    @XmlElement(name = "Mercancias")
    protected MercanciasComercioExterior mercancias;

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
     * Obtiene el valor de la propiedad datosComercioExterior.
     * 
     * @return
     *     possible object is
     *     {@link DatosComercioExteriorTimbrado }
     *     
     */
    public DatosComercioExteriorTimbrado getDatosComercioExterior() {
        return datosComercioExterior;
    }

    /**
     * Define el valor de la propiedad datosComercioExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosComercioExteriorTimbrado }
     *     
     */
    public void setDatosComercioExterior(DatosComercioExteriorTimbrado value) {
        this.datosComercioExterior = value;
    }

    /**
     * Obtiene el valor de la propiedad emisorComercioExterior.
     * 
     * @return
     *     possible object is
     *     {@link EmisorComercioExteriorTimbrado }
     *     
     */
    public EmisorComercioExteriorTimbrado getEmisorComercioExterior() {
        return emisorComercioExterior;
    }

    /**
     * Define el valor de la propiedad emisorComercioExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link EmisorComercioExteriorTimbrado }
     *     
     */
    public void setEmisorComercioExterior(EmisorComercioExteriorTimbrado value) {
        this.emisorComercioExterior = value;
    }

    /**
     * Obtiene el valor de la propiedad receptorComercioExterior.
     * 
     * @return
     *     possible object is
     *     {@link ReceptorComercioExteriorTimbrado }
     *     
     */
    public ReceptorComercioExteriorTimbrado getReceptorComercioExterior() {
        return receptorComercioExterior;
    }

    /**
     * Define el valor de la propiedad receptorComercioExterior.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceptorComercioExteriorTimbrado }
     *     
     */
    public void setReceptorComercioExterior(ReceptorComercioExteriorTimbrado value) {
        this.receptorComercioExterior = value;
    }

    /**
     * Obtiene el valor de la propiedad conceptos.
     * 
     * @return
     *     possible object is
     *     {@link ConceptosComercioTimbrado }
     *     
     */
    public ConceptosComercioTimbrado getConceptos() {
        return conceptos;
    }

    /**
     * Define el valor de la propiedad conceptos.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptosComercioTimbrado }
     *     
     */
    public void setConceptos(ConceptosComercioTimbrado value) {
        this.conceptos = value;
    }

    /**
     * Obtiene el valor de la propiedad impuestos.
     * 
     * @return
     *     possible object is
     *     {@link ImpuestosComercio }
     *     
     */
    public ImpuestosComercio getImpuestos() {
        return impuestos;
    }

    /**
     * Define el valor de la propiedad impuestos.
     * 
     * @param value
     *     allowed object is
     *     {@link ImpuestosComercio }
     *     
     */
    public void setImpuestos(ImpuestosComercio value) {
        this.impuestos = value;
    }

    /**
     * Obtiene el valor de la propiedad propietario.
     * 
     * @return
     *     possible object is
     *     {@link PropietarioComercioExterior }
     *     
     */
    public PropietarioComercioExterior getPropietario() {
        return propietario;
    }

    /**
     * Define el valor de la propiedad propietario.
     * 
     * @param value
     *     allowed object is
     *     {@link PropietarioComercioExterior }
     *     
     */
    public void setPropietario(PropietarioComercioExterior value) {
        this.propietario = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatario.
     * 
     * @return
     *     possible object is
     *     {@link DestinatariosComercioExterior }
     *     
     */
    public DestinatariosComercioExterior getDestinatario() {
        return destinatario;
    }

    /**
     * Define el valor de la propiedad destinatario.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinatariosComercioExterior }
     *     
     */
    public void setDestinatario(DestinatariosComercioExterior value) {
        this.destinatario = value;
    }

    /**
     * Obtiene el valor de la propiedad mercancias.
     * 
     * @return
     *     possible object is
     *     {@link MercanciasComercioExterior }
     *     
     */
    public MercanciasComercioExterior getMercancias() {
        return mercancias;
    }

    /**
     * Define el valor de la propiedad mercancias.
     * 
     * @param value
     *     allowed object is
     *     {@link MercanciasComercioExterior }
     *     
     */
    public void setMercancias(MercanciasComercioExterior value) {
        this.mercancias = value;
    }

}

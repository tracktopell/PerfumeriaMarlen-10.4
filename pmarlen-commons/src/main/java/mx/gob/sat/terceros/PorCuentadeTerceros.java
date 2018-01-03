
package mx.gob.sat.terceros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PorCuentadeTerceros complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PorCuentadeTerceros">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InformacionFiscalTercero" type="{http://www.sat.gob.mx/terceros}InformacionFiscalTercero" minOccurs="0"/>
 *         &lt;element name="InformacionAduanera" type="{http://www.sat.gob.mx/terceros}InformacionAduanera" minOccurs="0"/>
 *         &lt;element name="Impuestos" type="{http://www.sat.gob.mx/terceros}Impuestos" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rfc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PorCuentadeTerceros", propOrder = {
    "informacionFiscalTercero",
    "informacionAduanera",
    "impuestos"
})
public class PorCuentadeTerceros {

    @XmlElement(name = "InformacionFiscalTercero")
    protected InformacionFiscalTercero informacionFiscalTercero;
    @XmlElement(name = "InformacionAduanera")
    protected InformacionAduanera informacionAduanera;
    @XmlElement(name = "Impuestos")
    protected Impuestos impuestos;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "rfc")
    protected String rfc;
    @XmlAttribute(name = "nombre")
    protected String nombre;

    /**
     * Obtiene el valor de la propiedad informacionFiscalTercero.
     * 
     * @return
     *     possible object is
     *     {@link InformacionFiscalTercero }
     *     
     */
    public InformacionFiscalTercero getInformacionFiscalTercero() {
        return informacionFiscalTercero;
    }

    /**
     * Define el valor de la propiedad informacionFiscalTercero.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionFiscalTercero }
     *     
     */
    public void setInformacionFiscalTercero(InformacionFiscalTercero value) {
        this.informacionFiscalTercero = value;
    }

    /**
     * Obtiene el valor de la propiedad informacionAduanera.
     * 
     * @return
     *     possible object is
     *     {@link InformacionAduanera }
     *     
     */
    public InformacionAduanera getInformacionAduanera() {
        return informacionAduanera;
    }

    /**
     * Define el valor de la propiedad informacionAduanera.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionAduanera }
     *     
     */
    public void setInformacionAduanera(InformacionAduanera value) {
        this.informacionAduanera = value;
    }

    /**
     * Obtiene el valor de la propiedad impuestos.
     * 
     * @return
     *     possible object is
     *     {@link Impuestos }
     *     
     */
    public Impuestos getImpuestos() {
        return impuestos;
    }

    /**
     * Define el valor de la propiedad impuestos.
     * 
     * @param value
     *     allowed object is
     *     {@link Impuestos }
     *     
     */
    public void setImpuestos(Impuestos value) {
        this.impuestos = value;
    }

    /**
     * Obtiene el valor de la propiedad version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Define el valor de la propiedad version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtiene el valor de la propiedad rfc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Define el valor de la propiedad rfc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfc(String value) {
        this.rfc = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

}


package mx.gob.sat.notariospublicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para NotariosPublicos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NotariosPublicos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DescInmuebles" type="{http://www.sat.gob.mx/notariospublicos}DescInmuebles" minOccurs="0"/>
 *         &lt;element name="DatosOperacion" type="{http://www.sat.gob.mx/notariospublicos}DatosOperacion" minOccurs="0"/>
 *         &lt;element name="DatosNotario" type="{http://www.sat.gob.mx/notariospublicos}DatosNotario" minOccurs="0"/>
 *         &lt;element name="DatosEnajenante" type="{http://www.sat.gob.mx/notariospublicos}DatosEnajenante" minOccurs="0"/>
 *         &lt;element name="DatosAdquiriente" type="{http://www.sat.gob.mx/notariospublicos}DatosAdquiriente" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotariosPublicos", propOrder = {
    "descInmuebles",
    "datosOperacion",
    "datosNotario",
    "datosEnajenante",
    "datosAdquiriente"
})
public class NotariosPublicos {

    @XmlElement(name = "DescInmuebles")
    protected DescInmuebles descInmuebles;
    @XmlElement(name = "DatosOperacion")
    protected DatosOperacion datosOperacion;
    @XmlElement(name = "DatosNotario")
    protected DatosNotario datosNotario;
    @XmlElement(name = "DatosEnajenante")
    protected DatosEnajenante datosEnajenante;
    @XmlElement(name = "DatosAdquiriente")
    protected DatosAdquiriente datosAdquiriente;
    @XmlAttribute(name = "Version")
    protected String version;

    /**
     * Obtiene el valor de la propiedad descInmuebles.
     * 
     * @return
     *     possible object is
     *     {@link DescInmuebles }
     *     
     */
    public DescInmuebles getDescInmuebles() {
        return descInmuebles;
    }

    /**
     * Define el valor de la propiedad descInmuebles.
     * 
     * @param value
     *     allowed object is
     *     {@link DescInmuebles }
     *     
     */
    public void setDescInmuebles(DescInmuebles value) {
        this.descInmuebles = value;
    }

    /**
     * Obtiene el valor de la propiedad datosOperacion.
     * 
     * @return
     *     possible object is
     *     {@link DatosOperacion }
     *     
     */
    public DatosOperacion getDatosOperacion() {
        return datosOperacion;
    }

    /**
     * Define el valor de la propiedad datosOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosOperacion }
     *     
     */
    public void setDatosOperacion(DatosOperacion value) {
        this.datosOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad datosNotario.
     * 
     * @return
     *     possible object is
     *     {@link DatosNotario }
     *     
     */
    public DatosNotario getDatosNotario() {
        return datosNotario;
    }

    /**
     * Define el valor de la propiedad datosNotario.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosNotario }
     *     
     */
    public void setDatosNotario(DatosNotario value) {
        this.datosNotario = value;
    }

    /**
     * Obtiene el valor de la propiedad datosEnajenante.
     * 
     * @return
     *     possible object is
     *     {@link DatosEnajenante }
     *     
     */
    public DatosEnajenante getDatosEnajenante() {
        return datosEnajenante;
    }

    /**
     * Define el valor de la propiedad datosEnajenante.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosEnajenante }
     *     
     */
    public void setDatosEnajenante(DatosEnajenante value) {
        this.datosEnajenante = value;
    }

    /**
     * Obtiene el valor de la propiedad datosAdquiriente.
     * 
     * @return
     *     possible object is
     *     {@link DatosAdquiriente }
     *     
     */
    public DatosAdquiriente getDatosAdquiriente() {
        return datosAdquiriente;
    }

    /**
     * Define el valor de la propiedad datosAdquiriente.
     * 
     * @param value
     *     allowed object is
     *     {@link DatosAdquiriente }
     *     
     */
    public void setDatosAdquiriente(DatosAdquiriente value) {
        this.datosAdquiriente = value;
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

}

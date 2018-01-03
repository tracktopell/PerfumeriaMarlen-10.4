
package mx.gob.sat.aerolineas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Aerolineas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Aerolineas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OtrosCargos" type="{http://www.sat.gob.mx/aerolineas}OtrosCargos" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TUA" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aerolineas", propOrder = {
    "otrosCargos"
})
public class Aerolineas {

    @XmlElement(name = "OtrosCargos")
    protected OtrosCargos otrosCargos;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "TUA", required = true)
    protected double tua;

    /**
     * Obtiene el valor de la propiedad otrosCargos.
     * 
     * @return
     *     possible object is
     *     {@link OtrosCargos }
     *     
     */
    public OtrosCargos getOtrosCargos() {
        return otrosCargos;
    }

    /**
     * Define el valor de la propiedad otrosCargos.
     * 
     * @param value
     *     allowed object is
     *     {@link OtrosCargos }
     *     
     */
    public void setOtrosCargos(OtrosCargos value) {
        this.otrosCargos = value;
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
     * Obtiene el valor de la propiedad tua.
     * 
     */
    public double getTUA() {
        return tua;
    }

    /**
     * Define el valor de la propiedad tua.
     * 
     */
    public void setTUA(double value) {
        this.tua = value;
    }

}


package mx.gob.sat.iedu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para InstEducativas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="InstEducativas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nombreAlumno" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CURP" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nivelEducativo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="autRVOE" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rfcPago" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstEducativas")
public class InstEducativas {

    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "nombreAlumno")
    protected String nombreAlumno;
    @XmlAttribute(name = "CURP")
    protected String curp;
    @XmlAttribute(name = "nivelEducativo")
    protected String nivelEducativo;
    @XmlAttribute(name = "autRVOE")
    protected String autRVOE;
    @XmlAttribute(name = "rfcPago")
    protected String rfcPago;

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
     * Obtiene el valor de la propiedad nombreAlumno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAlumno() {
        return nombreAlumno;
    }

    /**
     * Define el valor de la propiedad nombreAlumno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAlumno(String value) {
        this.nombreAlumno = value;
    }

    /**
     * Obtiene el valor de la propiedad curp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURP() {
        return curp;
    }

    /**
     * Define el valor de la propiedad curp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURP(String value) {
        this.curp = value;
    }

    /**
     * Obtiene el valor de la propiedad nivelEducativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNivelEducativo() {
        return nivelEducativo;
    }

    /**
     * Define el valor de la propiedad nivelEducativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNivelEducativo(String value) {
        this.nivelEducativo = value;
    }

    /**
     * Obtiene el valor de la propiedad autRVOE.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutRVOE() {
        return autRVOE;
    }

    /**
     * Define el valor de la propiedad autRVOE.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutRVOE(String value) {
        this.autRVOE = value;
    }

    /**
     * Obtiene el valor de la propiedad rfcPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfcPago() {
        return rfcPago;
    }

    /**
     * Define el valor de la propiedad rfcPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfcPago(String value) {
        this.rfcPago = value;
    }

}

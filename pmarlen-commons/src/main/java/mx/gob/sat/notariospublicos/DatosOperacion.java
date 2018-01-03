
package mx.gob.sat.notariospublicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosOperacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosOperacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="NumInstrumentoNotarial" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FechaInstNotarial" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MontoOperacion" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="Subtotal" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="IVA" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosOperacion")
public class DatosOperacion {

    @XmlAttribute(name = "NumInstrumentoNotarial")
    protected String numInstrumentoNotarial;
    @XmlAttribute(name = "FechaInstNotarial")
    protected String fechaInstNotarial;
    @XmlAttribute(name = "MontoOperacion", required = true)
    protected double montoOperacion;
    @XmlAttribute(name = "Subtotal", required = true)
    protected double subtotal;
    @XmlAttribute(name = "IVA", required = true)
    protected double iva;

    /**
     * Obtiene el valor de la propiedad numInstrumentoNotarial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumInstrumentoNotarial() {
        return numInstrumentoNotarial;
    }

    /**
     * Define el valor de la propiedad numInstrumentoNotarial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumInstrumentoNotarial(String value) {
        this.numInstrumentoNotarial = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInstNotarial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInstNotarial() {
        return fechaInstNotarial;
    }

    /**
     * Define el valor de la propiedad fechaInstNotarial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInstNotarial(String value) {
        this.fechaInstNotarial = value;
    }

    /**
     * Obtiene el valor de la propiedad montoOperacion.
     * 
     */
    public double getMontoOperacion() {
        return montoOperacion;
    }

    /**
     * Define el valor de la propiedad montoOperacion.
     * 
     */
    public void setMontoOperacion(double value) {
        this.montoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad subtotal.
     * 
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Define el valor de la propiedad subtotal.
     * 
     */
    public void setSubtotal(double value) {
        this.subtotal = value;
    }

    /**
     * Obtiene el valor de la propiedad iva.
     * 
     */
    public double getIVA() {
        return iva;
    }

    /**
     * Define el valor de la propiedad iva.
     * 
     */
    public void setIVA(double value) {
        this.iva = value;
    }

}

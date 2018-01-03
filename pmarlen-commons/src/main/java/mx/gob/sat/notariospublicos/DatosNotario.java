
package mx.gob.sat.notariospublicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DatosNotario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DatosNotario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="CURP" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NumNotaria" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EntidadFederativa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Adscripcion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatosNotario")
public class DatosNotario {

    @XmlAttribute(name = "CURP")
    protected String curp;
    @XmlAttribute(name = "NumNotaria")
    protected String numNotaria;
    @XmlAttribute(name = "EntidadFederativa")
    protected String entidadFederativa;
    @XmlAttribute(name = "Adscripcion")
    protected String adscripcion;

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
     * Obtiene el valor de la propiedad numNotaria.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumNotaria() {
        return numNotaria;
    }

    /**
     * Define el valor de la propiedad numNotaria.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumNotaria(String value) {
        this.numNotaria = value;
    }

    /**
     * Obtiene el valor de la propiedad entidadFederativa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    /**
     * Define el valor de la propiedad entidadFederativa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidadFederativa(String value) {
        this.entidadFederativa = value;
    }

    /**
     * Obtiene el valor de la propiedad adscripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdscripcion() {
        return adscripcion;
    }

    /**
     * Define el valor de la propiedad adscripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdscripcion(String value) {
        this.adscripcion = value;
    }

}

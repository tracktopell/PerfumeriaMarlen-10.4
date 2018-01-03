
package mx.gob.sat.ine;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Entidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Entidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Contabilidad" type="{http://www.sat.gob.mx/ine}Contabilidad" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ClaveEntidad" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Ambito" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entidad", propOrder = {
    "contabilidad"
})
public class Entidad {

    @XmlElement(name = "Contabilidad")
    protected List<Contabilidad> contabilidad;
    @XmlAttribute(name = "ClaveEntidad")
    protected String claveEntidad;
    @XmlAttribute(name = "Ambito")
    protected String ambito;

    /**
     * Gets the value of the contabilidad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contabilidad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContabilidad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contabilidad }
     * 
     * 
     */
    public List<Contabilidad> getContabilidad() {
        if (contabilidad == null) {
            contabilidad = new ArrayList<Contabilidad>();
        }
        return this.contabilidad;
    }

    /**
     * Obtiene el valor de la propiedad claveEntidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveEntidad() {
        return claveEntidad;
    }

    /**
     * Define el valor de la propiedad claveEntidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveEntidad(String value) {
        this.claveEntidad = value;
    }

    /**
     * Obtiene el valor de la propiedad ambito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmbito() {
        return ambito;
    }

    /**
     * Define el valor de la propiedad ambito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmbito(String value) {
        this.ambito = value;
    }

}

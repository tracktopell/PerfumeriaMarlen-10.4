
package mx.gob.sat.ventavehiculos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para VentaVehiculos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="VentaVehiculos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InformacionAduanera" type="{http://www.sat.gob.mx/ventavehiculos}InformacionAduanera" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ClaveVehicular" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Niv" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VentaVehiculos", propOrder = {
    "informacionAduanera"
})
public class VentaVehiculos {

    @XmlElement(name = "InformacionAduanera")
    protected List<InformacionAduanera> informacionAduanera;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "ClaveVehicular")
    protected String claveVehicular;
    @XmlAttribute(name = "Niv")
    protected String niv;

    /**
     * Gets the value of the informacionAduanera property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the informacionAduanera property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformacionAduanera().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InformacionAduanera }
     * 
     * 
     */
    public List<InformacionAduanera> getInformacionAduanera() {
        if (informacionAduanera == null) {
            informacionAduanera = new ArrayList<InformacionAduanera>();
        }
        return this.informacionAduanera;
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
     * Obtiene el valor de la propiedad claveVehicular.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveVehicular() {
        return claveVehicular;
    }

    /**
     * Define el valor de la propiedad claveVehicular.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveVehicular(String value) {
        this.claveVehicular = value;
    }

    /**
     * Obtiene el valor de la propiedad niv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNiv() {
        return niv;
    }

    /**
     * Define el valor de la propiedad niv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNiv(String value) {
        this.niv = value;
    }

}

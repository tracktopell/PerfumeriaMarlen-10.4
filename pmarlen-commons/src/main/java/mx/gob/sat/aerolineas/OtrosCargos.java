
package mx.gob.sat.aerolineas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OtrosCargos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OtrosCargos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Cargo" type="{http://www.sat.gob.mx/aerolineas}Cargo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TotalCargos" use="required" type="{http://www.w3.org/2001/XMLSchema}double" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtrosCargos", propOrder = {
    "cargo"
})
public class OtrosCargos {

    @XmlElement(name = "Cargo")
    protected List<Cargo> cargo;
    @XmlAttribute(name = "TotalCargos", required = true)
    protected double totalCargos;

    /**
     * Gets the value of the cargo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cargo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCargo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cargo }
     * 
     * 
     */
    public List<Cargo> getCargo() {
        if (cargo == null) {
            cargo = new ArrayList<Cargo>();
        }
        return this.cargo;
    }

    /**
     * Obtiene el valor de la propiedad totalCargos.
     * 
     */
    public double getTotalCargos() {
        return totalCargos;
    }

    /**
     * Define el valor de la propiedad totalCargos.
     * 
     */
    public void setTotalCargos(double value) {
        this.totalCargos = value;
    }

}

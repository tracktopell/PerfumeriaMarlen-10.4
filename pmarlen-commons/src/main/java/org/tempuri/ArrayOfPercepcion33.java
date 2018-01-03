
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfPercepcion33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPercepcion33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Percepcion33" type="{http://tempuri.org/}Percepcion33" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPercepcion33", propOrder = {
    "percepcion33"
})
public class ArrayOfPercepcion33 {

    @XmlElement(name = "Percepcion33", nillable = true)
    protected List<Percepcion33> percepcion33;

    /**
     * Gets the value of the percepcion33 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the percepcion33 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPercepcion33().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Percepcion33 }
     * 
     * 
     */
    public List<Percepcion33> getPercepcion33() {
        if (percepcion33 == null) {
            percepcion33 = new ArrayList<Percepcion33>();
        }
        return this.percepcion33;
    }

}

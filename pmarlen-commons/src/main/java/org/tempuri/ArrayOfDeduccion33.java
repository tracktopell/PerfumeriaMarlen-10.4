
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfDeduccion33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDeduccion33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Deduccion33" type="{http://tempuri.org/}Deduccion33" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDeduccion33", propOrder = {
    "deduccion33"
})
public class ArrayOfDeduccion33 {

    @XmlElement(name = "Deduccion33", nillable = true)
    protected List<Deduccion33> deduccion33;

    /**
     * Gets the value of the deduccion33 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deduccion33 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeduccion33().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Deduccion33 }
     * 
     * 
     */
    public List<Deduccion33> getDeduccion33() {
        if (deduccion33 == null) {
            deduccion33 = new ArrayList<Deduccion33>();
        }
        return this.deduccion33;
    }

}

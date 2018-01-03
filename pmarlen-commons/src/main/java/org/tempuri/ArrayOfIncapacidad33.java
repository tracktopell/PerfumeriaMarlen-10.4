
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfIncapacidad33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfIncapacidad33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Incapacidad33" type="{http://tempuri.org/}Incapacidad33" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfIncapacidad33", propOrder = {
    "incapacidad33"
})
public class ArrayOfIncapacidad33 {

    @XmlElement(name = "Incapacidad33", nillable = true)
    protected List<Incapacidad33> incapacidad33;

    /**
     * Gets the value of the incapacidad33 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the incapacidad33 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncapacidad33().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Incapacidad33 }
     * 
     * 
     */
    public List<Incapacidad33> getIncapacidad33() {
        if (incapacidad33 == null) {
            incapacidad33 = new ArrayList<Incapacidad33>();
        }
        return this.incapacidad33;
    }

}


package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfOtroPago33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOtroPago33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OtroPago33" type="{http://tempuri.org/}OtroPago33" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOtroPago33", propOrder = {
    "otroPago33"
})
public class ArrayOfOtroPago33 {

    @XmlElement(name = "OtroPago33", nillable = true)
    protected List<OtroPago33> otroPago33;

    /**
     * Gets the value of the otroPago33 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otroPago33 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtroPago33().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OtroPago33 }
     * 
     * 
     */
    public List<OtroPago33> getOtroPago33() {
        if (otroPago33 == null) {
            otroPago33 = new ArrayList<OtroPago33>();
        }
        return this.otroPago33;
    }

}

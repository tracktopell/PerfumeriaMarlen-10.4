
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfImpuestoTrasladadoTicket complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfImpuestoTrasladadoTicket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ImpuestoTrasladadoTicket" type="{http://tempuri.org/}ImpuestoTrasladadoTicket" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfImpuestoTrasladadoTicket", propOrder = {
    "impuestoTrasladadoTicket"
})
public class ArrayOfImpuestoTrasladadoTicket {

    @XmlElement(name = "ImpuestoTrasladadoTicket", nillable = true)
    protected List<ImpuestoTrasladadoTicket> impuestoTrasladadoTicket;

    /**
     * Gets the value of the impuestoTrasladadoTicket property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the impuestoTrasladadoTicket property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImpuestoTrasladadoTicket().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImpuestoTrasladadoTicket }
     * 
     * 
     */
    public List<ImpuestoTrasladadoTicket> getImpuestoTrasladadoTicket() {
        if (impuestoTrasladadoTicket == null) {
            impuestoTrasladadoTicket = new ArrayList<ImpuestoTrasladadoTicket>();
        }
        return this.impuestoTrasladadoTicket;
    }

}

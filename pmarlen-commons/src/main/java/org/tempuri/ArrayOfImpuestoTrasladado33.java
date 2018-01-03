
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfImpuestoTrasladado33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfImpuestoTrasladado33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ImpuestoTrasladado33" type="{http://tempuri.org/}ImpuestoTrasladado33" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfImpuestoTrasladado33", propOrder = {
    "impuestoTrasladado33"
})
public class ArrayOfImpuestoTrasladado33 {

    @XmlElement(name = "ImpuestoTrasladado33", nillable = true)
    protected List<ImpuestoTrasladado33> impuestoTrasladado33;

    /**
     * Gets the value of the impuestoTrasladado33 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the impuestoTrasladado33 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImpuestoTrasladado33().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImpuestoTrasladado33 }
     * 
     * 
     */
    public List<ImpuestoTrasladado33> getImpuestoTrasladado33() {
        if (impuestoTrasladado33 == null) {
            impuestoTrasladado33 = new ArrayList<ImpuestoTrasladado33>();
        }
        return this.impuestoTrasladado33;
    }

}

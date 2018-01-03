
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfConceptoComercioCFDI33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfConceptoComercioCFDI33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConceptoComercioCFDI33" type="{http://tempuri.org/}ConceptoComercioCFDI33" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfConceptoComercioCFDI33", propOrder = {
    "conceptoComercioCFDI33"
})
public class ArrayOfConceptoComercioCFDI33 {

    @XmlElement(name = "ConceptoComercioCFDI33", nillable = true)
    protected List<ConceptoComercioCFDI33> conceptoComercioCFDI33;

    /**
     * Gets the value of the conceptoComercioCFDI33 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conceptoComercioCFDI33 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConceptoComercioCFDI33().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConceptoComercioCFDI33 }
     * 
     * 
     */
    public List<ConceptoComercioCFDI33> getConceptoComercioCFDI33() {
        if (conceptoComercioCFDI33 == null) {
            conceptoComercioCFDI33 = new ArrayList<ConceptoComercioCFDI33>();
        }
        return this.conceptoComercioCFDI33;
    }

}


package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.leyendasfiscales.Leyenda;


/**
 * <p>Clase Java para ArrayOfLeyenda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfLeyenda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Leyenda" type="{http://www.sat.gob.mx/leyendasFiscales}Leyenda" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLeyenda", propOrder = {
    "leyenda"
})
public class ArrayOfLeyenda {

    @XmlElement(name = "Leyenda", nillable = true)
    protected List<Leyenda> leyenda;

    /**
     * Gets the value of the leyenda property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the leyenda property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeyenda().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Leyenda }
     * 
     * 
     */
    public List<Leyenda> getLeyenda() {
        if (leyenda == null) {
            leyenda = new ArrayList<Leyenda>();
        }
        return this.leyenda;
    }

}

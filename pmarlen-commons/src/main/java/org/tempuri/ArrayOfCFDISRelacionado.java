
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfCFDISRelacionado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCFDISRelacionado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFDISRelacionado" type="{http://tempuri.org/}CFDISRelacionado" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCFDISRelacionado", propOrder = {
    "cfdisRelacionado"
})
public class ArrayOfCFDISRelacionado {

    @XmlElement(name = "CFDISRelacionado", nillable = true)
    protected List<CFDISRelacionado> cfdisRelacionado;

    /**
     * Gets the value of the cfdisRelacionado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cfdisRelacionado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCFDISRelacionado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CFDISRelacionado }
     * 
     * 
     */
    public List<CFDISRelacionado> getCFDISRelacionado() {
        if (cfdisRelacionado == null) {
            cfdisRelacionado = new ArrayList<CFDISRelacionado>();
        }
        return this.cfdisRelacionado;
    }

}

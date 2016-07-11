
package com.pmarlen.digifactws20160707.production;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfImpuestoRetencion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfImpuestoRetencion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ImpuestoRetencion" type="{https://cfd.sicofi.com.mx}ImpuestoRetencion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfImpuestoRetencion", propOrder = {
    "impuestoRetencion"
})
public class ArrayOfImpuestoRetencion {

    @XmlElement(name = "ImpuestoRetencion", nillable = true)
    protected List<ImpuestoRetencion> impuestoRetencion;

    /**
     * Gets the value of the impuestoRetencion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the impuestoRetencion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImpuestoRetencion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImpuestoRetencion }
     * 
     * 
     */
    public List<ImpuestoRetencion> getImpuestoRetencion() {
        if (impuestoRetencion == null) {
            impuestoRetencion = new ArrayList<ImpuestoRetencion>();
        }
        return this.impuestoRetencion;
    }

}

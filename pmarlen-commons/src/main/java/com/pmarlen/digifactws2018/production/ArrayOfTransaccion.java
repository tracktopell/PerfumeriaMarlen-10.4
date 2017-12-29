
package com.pmarlen.digifactws2018.production;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfTransaccion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTransaccion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Transaccion" type="{https://cfd.sicofi.com.mx}Transaccion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTransaccion", propOrder = {
    "transaccion"
})
public class ArrayOfTransaccion {

    @XmlElement(name = "Transaccion", nillable = true)
    protected List<Transaccion> transaccion;

    /**
     * Gets the value of the transaccion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transaccion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransaccion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transaccion }
     * 
     * 
     */
    public List<Transaccion> getTransaccion() {
        if (transaccion == null) {
            transaccion = new ArrayList<Transaccion>();
        }
        return this.transaccion;
    }

}


package com.pmarlen.digifactws2018.production;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfTransferencia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTransferencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Transferencia" type="{https://cfd.sicofi.com.mx}Transferencia" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTransferencia", propOrder = {
    "transferencia"
})
public class ArrayOfTransferencia {

    @XmlElement(name = "Transferencia", nillable = true)
    protected List<Transferencia> transferencia;

    /**
     * Gets the value of the transferencia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transferencia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransferencia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transferencia }
     * 
     * 
     */
    public List<Transferencia> getTransferencia() {
        if (transferencia == null) {
            transferencia = new ArrayList<Transferencia>();
        }
        return this.transferencia;
    }

}

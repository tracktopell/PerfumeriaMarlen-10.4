
package org.tempuri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfDomicilioDestinatario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDomicilioDestinatario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DomicilioDestinatario" type="{http://tempuri.org/}DomicilioDestinatario" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDomicilioDestinatario", propOrder = {
    "domicilioDestinatario"
})
public class ArrayOfDomicilioDestinatario {

    @XmlElement(name = "DomicilioDestinatario", nillable = true)
    protected List<DomicilioDestinatario> domicilioDestinatario;

    /**
     * Gets the value of the domicilioDestinatario property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domicilioDestinatario property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomicilioDestinatario().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomicilioDestinatario }
     * 
     * 
     */
    public List<DomicilioDestinatario> getDomicilioDestinatario() {
        if (domicilioDestinatario == null) {
            domicilioDestinatario = new ArrayList<DomicilioDestinatario>();
        }
        return this.domicilioDestinatario;
    }

}

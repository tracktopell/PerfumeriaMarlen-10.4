
package com.pmarlen.digifactws2018.production;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfComprobanteExtranjero complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComprobanteExtranjero">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ComprobanteExtranjero" type="{https://cfd.sicofi.com.mx}ComprobanteExtranjero" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComprobanteExtranjero", propOrder = {
    "comprobanteExtranjero"
})
public class ArrayOfComprobanteExtranjero {

    @XmlElement(name = "ComprobanteExtranjero", nillable = true)
    protected List<ComprobanteExtranjero> comprobanteExtranjero;

    /**
     * Gets the value of the comprobanteExtranjero property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comprobanteExtranjero property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComprobanteExtranjero().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComprobanteExtranjero }
     * 
     * 
     */
    public List<ComprobanteExtranjero> getComprobanteExtranjero() {
        if (comprobanteExtranjero == null) {
            comprobanteExtranjero = new ArrayList<ComprobanteExtranjero>();
        }
        return this.comprobanteExtranjero;
    }

}

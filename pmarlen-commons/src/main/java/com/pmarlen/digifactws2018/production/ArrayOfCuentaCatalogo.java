
package com.pmarlen.digifactws2018.production;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfCuentaCatalogo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCuentaCatalogo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CuentaCatalogo" type="{https://cfd.sicofi.com.mx}CuentaCatalogo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCuentaCatalogo", propOrder = {
    "cuentaCatalogo"
})
public class ArrayOfCuentaCatalogo {

    @XmlElement(name = "CuentaCatalogo", nillable = true)
    protected List<CuentaCatalogo> cuentaCatalogo;

    /**
     * Gets the value of the cuentaCatalogo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuentaCatalogo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuentaCatalogo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CuentaCatalogo }
     * 
     * 
     */
    public List<CuentaCatalogo> getCuentaCatalogo() {
        if (cuentaCatalogo == null) {
            cuentaCatalogo = new ArrayList<CuentaCatalogo>();
        }
        return this.cuentaCatalogo;
    }

}

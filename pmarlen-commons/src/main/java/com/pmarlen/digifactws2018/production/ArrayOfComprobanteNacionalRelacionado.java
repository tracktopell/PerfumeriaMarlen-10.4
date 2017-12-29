
package com.pmarlen.digifactws2018.production;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfComprobanteNacionalRelacionado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComprobanteNacionalRelacionado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ComprobanteNacionalRelacionado" type="{https://cfd.sicofi.com.mx}ComprobanteNacionalRelacionado" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComprobanteNacionalRelacionado", propOrder = {
    "comprobanteNacionalRelacionado"
})
public class ArrayOfComprobanteNacionalRelacionado {

    @XmlElement(name = "ComprobanteNacionalRelacionado", nillable = true)
    protected List<ComprobanteNacionalRelacionado> comprobanteNacionalRelacionado;

    /**
     * Gets the value of the comprobanteNacionalRelacionado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comprobanteNacionalRelacionado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComprobanteNacionalRelacionado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComprobanteNacionalRelacionado }
     * 
     * 
     */
    public List<ComprobanteNacionalRelacionado> getComprobanteNacionalRelacionado() {
        if (comprobanteNacionalRelacionado == null) {
            comprobanteNacionalRelacionado = new ArrayList<ComprobanteNacionalRelacionado>();
        }
        return this.comprobanteNacionalRelacionado;
    }

}

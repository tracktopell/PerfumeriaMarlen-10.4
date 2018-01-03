
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EstatusFactura complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EstatusFactura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pagada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstatusFactura", propOrder = {
    "pagada"
})
public class EstatusFactura {

    @XmlElement(name = "Pagada")
    protected boolean pagada;

    /**
     * Obtiene el valor de la propiedad pagada.
     * 
     */
    public boolean isPagada() {
        return pagada;
    }

    /**
     * Define el valor de la propiedad pagada.
     * 
     */
    public void setPagada(boolean value) {
        this.pagada = value;
    }

}

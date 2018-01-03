
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AccionesOTitulos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AccionesOTitulos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ValorMercado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="PrecioAlOtorgarse" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccionesOTitulos", propOrder = {
    "valorMercado",
    "precioAlOtorgarse"
})
public class AccionesOTitulos {

    @XmlElement(name = "ValorMercado")
    protected double valorMercado;
    @XmlElement(name = "PrecioAlOtorgarse")
    protected double precioAlOtorgarse;

    /**
     * Obtiene el valor de la propiedad valorMercado.
     * 
     */
    public double getValorMercado() {
        return valorMercado;
    }

    /**
     * Define el valor de la propiedad valorMercado.
     * 
     */
    public void setValorMercado(double value) {
        this.valorMercado = value;
    }

    /**
     * Obtiene el valor de la propiedad precioAlOtorgarse.
     * 
     */
    public double getPrecioAlOtorgarse() {
        return precioAlOtorgarse;
    }

    /**
     * Define el valor de la propiedad precioAlOtorgarse.
     * 
     */
    public void setPrecioAlOtorgarse(double value) {
        this.precioAlOtorgarse = value;
    }

}

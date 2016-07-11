
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OperacionesConDerivados complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OperacionesConDerivados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MontoGanancia" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MontoPerdida" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperacionesConDerivados", propOrder = {
    "incluirComplemento",
    "montoGanancia",
    "montoPerdida"
})
public class OperacionesConDerivados {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "MontoGanancia")
    protected double montoGanancia;
    @XmlElement(name = "MontoPerdida")
    protected double montoPerdida;

    /**
     * Obtiene el valor de la propiedad incluirComplemento.
     * 
     */
    public boolean isIncluirComplemento() {
        return incluirComplemento;
    }

    /**
     * Define el valor de la propiedad incluirComplemento.
     * 
     */
    public void setIncluirComplemento(boolean value) {
        this.incluirComplemento = value;
    }

    /**
     * Obtiene el valor de la propiedad montoGanancia.
     * 
     */
    public double getMontoGanancia() {
        return montoGanancia;
    }

    /**
     * Define el valor de la propiedad montoGanancia.
     * 
     */
    public void setMontoGanancia(double value) {
        this.montoGanancia = value;
    }

    /**
     * Obtiene el valor de la propiedad montoPerdida.
     * 
     */
    public double getMontoPerdida() {
        return montoPerdida;
    }

    /**
     * Define el valor de la propiedad montoPerdida.
     * 
     */
    public void setMontoPerdida(double value) {
        this.montoPerdida = value;
    }

}

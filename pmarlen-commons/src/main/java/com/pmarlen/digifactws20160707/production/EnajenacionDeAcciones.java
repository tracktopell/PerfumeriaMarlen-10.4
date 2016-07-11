
package com.pmarlen.digifactws20160707.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnajenacionDeAcciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EnajenacionDeAcciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IncluirComplemento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ContratoIntermediacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ganancia" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Perdida" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnajenacionDeAcciones", propOrder = {
    "incluirComplemento",
    "contratoIntermediacion",
    "ganancia",
    "perdida"
})
public class EnajenacionDeAcciones {

    @XmlElement(name = "IncluirComplemento")
    protected boolean incluirComplemento;
    @XmlElement(name = "ContratoIntermediacion")
    protected String contratoIntermediacion;
    @XmlElement(name = "Ganancia")
    protected double ganancia;
    @XmlElement(name = "Perdida")
    protected double perdida;

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
     * Obtiene el valor de la propiedad contratoIntermediacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoIntermediacion() {
        return contratoIntermediacion;
    }

    /**
     * Define el valor de la propiedad contratoIntermediacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoIntermediacion(String value) {
        this.contratoIntermediacion = value;
    }

    /**
     * Obtiene el valor de la propiedad ganancia.
     * 
     */
    public double getGanancia() {
        return ganancia;
    }

    /**
     * Define el valor de la propiedad ganancia.
     * 
     */
    public void setGanancia(double value) {
        this.ganancia = value;
    }

    /**
     * Obtiene el valor de la propiedad perdida.
     * 
     */
    public double getPerdida() {
        return perdida;
    }

    /**
     * Define el valor de la propiedad perdida.
     * 
     */
    public void setPerdida(double value) {
        this.perdida = value;
    }

}

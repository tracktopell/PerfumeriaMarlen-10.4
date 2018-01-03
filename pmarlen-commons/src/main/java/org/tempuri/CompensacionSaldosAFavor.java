
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CompensacionSaldosAFavor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CompensacionSaldosAFavor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SaldoAFavor" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RemanenteSalFav" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompensacionSaldosAFavor", propOrder = {
    "saldoAFavor",
    "anio",
    "remanenteSalFav"
})
public class CompensacionSaldosAFavor {

    @XmlElement(name = "SaldoAFavor")
    protected double saldoAFavor;
    protected int anio;
    @XmlElement(name = "RemanenteSalFav")
    protected double remanenteSalFav;

    /**
     * Obtiene el valor de la propiedad saldoAFavor.
     * 
     */
    public double getSaldoAFavor() {
        return saldoAFavor;
    }

    /**
     * Define el valor de la propiedad saldoAFavor.
     * 
     */
    public void setSaldoAFavor(double value) {
        this.saldoAFavor = value;
    }

    /**
     * Obtiene el valor de la propiedad anio.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Define el valor de la propiedad anio.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Obtiene el valor de la propiedad remanenteSalFav.
     * 
     */
    public double getRemanenteSalFav() {
        return remanenteSalFav;
    }

    /**
     * Define el valor de la propiedad remanenteSalFav.
     * 
     */
    public void setRemanenteSalFav(double value) {
        this.remanenteSalFav = value;
    }

}

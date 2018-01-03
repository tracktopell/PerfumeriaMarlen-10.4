
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OtroPago33 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OtroPago33">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoOtroPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Clave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Concepto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Importe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="SubsidioAlEmpleo" type="{http://tempuri.org/}SubsidioAlEmpleo33" minOccurs="0"/>
 *         &lt;element name="CompensacionSaldosAFavor" type="{http://tempuri.org/}CompensacionSaldosAFavor33" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtroPago33", propOrder = {
    "tipoOtroPago",
    "clave",
    "concepto",
    "importe",
    "subsidioAlEmpleo",
    "compensacionSaldosAFavor"
})
public class OtroPago33 {

    @XmlElement(name = "TipoOtroPago")
    protected String tipoOtroPago;
    @XmlElement(name = "Clave")
    protected String clave;
    @XmlElement(name = "Concepto")
    protected String concepto;
    @XmlElement(name = "Importe")
    protected double importe;
    @XmlElement(name = "SubsidioAlEmpleo")
    protected SubsidioAlEmpleo33 subsidioAlEmpleo;
    @XmlElement(name = "CompensacionSaldosAFavor")
    protected CompensacionSaldosAFavor33 compensacionSaldosAFavor;

    /**
     * Obtiene el valor de la propiedad tipoOtroPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOtroPago() {
        return tipoOtroPago;
    }

    /**
     * Define el valor de la propiedad tipoOtroPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOtroPago(String value) {
        this.tipoOtroPago = value;
    }

    /**
     * Obtiene el valor de la propiedad clave.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClave() {
        return clave;
    }

    /**
     * Define el valor de la propiedad clave.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClave(String value) {
        this.clave = value;
    }

    /**
     * Obtiene el valor de la propiedad concepto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Define el valor de la propiedad concepto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConcepto(String value) {
        this.concepto = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     */
    public void setImporte(double value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad subsidioAlEmpleo.
     * 
     * @return
     *     possible object is
     *     {@link SubsidioAlEmpleo33 }
     *     
     */
    public SubsidioAlEmpleo33 getSubsidioAlEmpleo() {
        return subsidioAlEmpleo;
    }

    /**
     * Define el valor de la propiedad subsidioAlEmpleo.
     * 
     * @param value
     *     allowed object is
     *     {@link SubsidioAlEmpleo33 }
     *     
     */
    public void setSubsidioAlEmpleo(SubsidioAlEmpleo33 value) {
        this.subsidioAlEmpleo = value;
    }

    /**
     * Obtiene el valor de la propiedad compensacionSaldosAFavor.
     * 
     * @return
     *     possible object is
     *     {@link CompensacionSaldosAFavor33 }
     *     
     */
    public CompensacionSaldosAFavor33 getCompensacionSaldosAFavor() {
        return compensacionSaldosAFavor;
    }

    /**
     * Define el valor de la propiedad compensacionSaldosAFavor.
     * 
     * @param value
     *     allowed object is
     *     {@link CompensacionSaldosAFavor33 }
     *     
     */
    public void setCompensacionSaldosAFavor(CompensacionSaldosAFavor33 value) {
        this.compensacionSaldosAFavor = value;
    }

}

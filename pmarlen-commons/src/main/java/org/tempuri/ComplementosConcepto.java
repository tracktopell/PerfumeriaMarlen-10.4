
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.iedu.InstEducativas;
import mx.gob.sat.terceros.PorCuentadeTerceros;
import mx.gob.sat.ventavehiculos.VentaVehiculos;


/**
 * <p>Clase Java para ComplementosConcepto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComplementosConcepto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Terceros" type="{http://www.sat.gob.mx/terceros}PorCuentadeTerceros" minOccurs="0"/>
 *         &lt;element name="iedu" type="{http://www.sat.gob.mx/iedu}InstEducativas" minOccurs="0"/>
 *         &lt;element name="VentadeVehiculos" type="{http://www.sat.gob.mx/ventavehiculos}VentaVehiculos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplementosConcepto", propOrder = {
    "terceros",
    "iedu",
    "ventadeVehiculos"
})
public class ComplementosConcepto {

    @XmlElement(name = "Terceros")
    protected PorCuentadeTerceros terceros;
    protected InstEducativas iedu;
    @XmlElement(name = "VentadeVehiculos")
    protected VentaVehiculos ventadeVehiculos;

    /**
     * Obtiene el valor de la propiedad terceros.
     * 
     * @return
     *     possible object is
     *     {@link PorCuentadeTerceros }
     *     
     */
    public PorCuentadeTerceros getTerceros() {
        return terceros;
    }

    /**
     * Define el valor de la propiedad terceros.
     * 
     * @param value
     *     allowed object is
     *     {@link PorCuentadeTerceros }
     *     
     */
    public void setTerceros(PorCuentadeTerceros value) {
        this.terceros = value;
    }

    /**
     * Obtiene el valor de la propiedad iedu.
     * 
     * @return
     *     possible object is
     *     {@link InstEducativas }
     *     
     */
    public InstEducativas getIedu() {
        return iedu;
    }

    /**
     * Define el valor de la propiedad iedu.
     * 
     * @param value
     *     allowed object is
     *     {@link InstEducativas }
     *     
     */
    public void setIedu(InstEducativas value) {
        this.iedu = value;
    }

    /**
     * Obtiene el valor de la propiedad ventadeVehiculos.
     * 
     * @return
     *     possible object is
     *     {@link VentaVehiculos }
     *     
     */
    public VentaVehiculos getVentadeVehiculos() {
        return ventadeVehiculos;
    }

    /**
     * Define el valor de la propiedad ventadeVehiculos.
     * 
     * @param value
     *     allowed object is
     *     {@link VentaVehiculos }
     *     
     */
    public void setVentadeVehiculos(VentaVehiculos value) {
        this.ventadeVehiculos = value;
    }

}

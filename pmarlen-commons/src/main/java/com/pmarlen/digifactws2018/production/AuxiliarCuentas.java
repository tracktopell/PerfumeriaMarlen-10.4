
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AuxiliarCuentas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AuxiliarCuentas">
 *   &lt;complexContent>
 *     &lt;extension base="{https://cfd.sicofi.com.mx}GeneralesAuxiliares">
 *       &lt;sequence>
 *         &lt;element name="Cuentas" type="{https://cfd.sicofi.com.mx}ArrayOfCuentasAux" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuxiliarCuentas", propOrder = {
    "cuentas"
})
public class AuxiliarCuentas
    extends GeneralesAuxiliares
{

    @XmlElement(name = "Cuentas")
    protected ArrayOfCuentasAux cuentas;

    /**
     * Obtiene el valor de la propiedad cuentas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCuentasAux }
     *     
     */
    public ArrayOfCuentasAux getCuentas() {
        return cuentas;
    }

    /**
     * Define el valor de la propiedad cuentas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCuentasAux }
     *     
     */
    public void setCuentas(ArrayOfCuentasAux value) {
        this.cuentas = value;
    }

}

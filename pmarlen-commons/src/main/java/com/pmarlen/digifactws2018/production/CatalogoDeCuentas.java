
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CatalogoDeCuentas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CatalogoDeCuentas">
 *   &lt;complexContent>
 *     &lt;extension base="{https://cfd.sicofi.com.mx}GeneralesConta">
 *       &lt;sequence>
 *         &lt;element name="Cuentas" type="{https://cfd.sicofi.com.mx}ArrayOfCuentaCatalogo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogoDeCuentas", propOrder = {
    "cuentas"
})
public class CatalogoDeCuentas
    extends GeneralesConta
{

    @XmlElement(name = "Cuentas")
    protected ArrayOfCuentaCatalogo cuentas;

    /**
     * Obtiene el valor de la propiedad cuentas.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCuentaCatalogo }
     *     
     */
    public ArrayOfCuentaCatalogo getCuentas() {
        return cuentas;
    }

    /**
     * Define el valor de la propiedad cuentas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCuentaCatalogo }
     *     
     */
    public void setCuentas(ArrayOfCuentaCatalogo value) {
        this.cuentas = value;
    }

}

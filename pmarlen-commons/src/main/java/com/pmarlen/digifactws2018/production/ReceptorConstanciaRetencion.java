
package com.pmarlen.digifactws2018.production;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ReceptorConstanciaRetencion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ReceptorConstanciaRetencion">
 *   &lt;complexContent>
 *     &lt;extension base="{https://cfd.sicofi.com.mx}ReceptorRetencion">
 *       &lt;sequence>
 *         &lt;element name="Email1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceptorConstanciaRetencion", propOrder = {
    "email1",
    "email2",
    "email3"
})
public class ReceptorConstanciaRetencion
    extends ReceptorRetencion
{

    @XmlElement(name = "Email1")
    protected String email1;
    @XmlElement(name = "Email2")
    protected String email2;
    @XmlElement(name = "Email3")
    protected String email3;

    /**
     * Obtiene el valor de la propiedad email1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail1() {
        return email1;
    }

    /**
     * Define el valor de la propiedad email1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail1(String value) {
        this.email1 = value;
    }

    /**
     * Obtiene el valor de la propiedad email2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * Define el valor de la propiedad email2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail2(String value) {
        this.email2 = value;
    }

    /**
     * Obtiene el valor de la propiedad email3.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail3() {
        return email3;
    }

    /**
     * Define el valor de la propiedad email3.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail3(String value) {
        this.email3 = value;
    }

}

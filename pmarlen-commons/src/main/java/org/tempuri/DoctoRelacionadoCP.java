
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DoctoRelacionadoCP complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DoctoRelacionadoCP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Serie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Folio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonedaDR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoCambioDR" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="MetodoDePagoDR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumParcialidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ImpSaldoAnt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImpPagado" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ImpSaldoInsoluto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoctoRelacionadoCP", propOrder = {
    "idDocumento",
    "serie",
    "folio",
    "monedaDR",
    "tipoCambioDR",
    "metodoDePagoDR",
    "numParcialidad",
    "impSaldoAnt",
    "impPagado",
    "impSaldoInsoluto"
})
public class DoctoRelacionadoCP {

    @XmlElement(name = "IdDocumento")
    protected String idDocumento;
    @XmlElement(name = "Serie")
    protected String serie;
    @XmlElement(name = "Folio")
    protected String folio;
    @XmlElement(name = "MonedaDR")
    protected String monedaDR;
    @XmlElement(name = "TipoCambioDR")
    protected double tipoCambioDR;
    @XmlElement(name = "MetodoDePagoDR")
    protected String metodoDePagoDR;
    @XmlElement(name = "NumParcialidad")
    protected int numParcialidad;
    @XmlElement(name = "ImpSaldoAnt")
    protected double impSaldoAnt;
    @XmlElement(name = "ImpPagado")
    protected double impPagado;
    @XmlElement(name = "ImpSaldoInsoluto")
    protected double impSaldoInsoluto;

    /**
     * Obtiene el valor de la propiedad idDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocumento() {
        return idDocumento;
    }

    /**
     * Define el valor de la propiedad idDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumento(String value) {
        this.idDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad serie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Define el valor de la propiedad serie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerie(String value) {
        this.serie = value;
    }

    /**
     * Obtiene el valor de la propiedad folio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaDR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaDR() {
        return monedaDR;
    }

    /**
     * Define el valor de la propiedad monedaDR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaDR(String value) {
        this.monedaDR = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoCambioDR.
     * 
     */
    public double getTipoCambioDR() {
        return tipoCambioDR;
    }

    /**
     * Define el valor de la propiedad tipoCambioDR.
     * 
     */
    public void setTipoCambioDR(double value) {
        this.tipoCambioDR = value;
    }

    /**
     * Obtiene el valor de la propiedad metodoDePagoDR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetodoDePagoDR() {
        return metodoDePagoDR;
    }

    /**
     * Define el valor de la propiedad metodoDePagoDR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetodoDePagoDR(String value) {
        this.metodoDePagoDR = value;
    }

    /**
     * Obtiene el valor de la propiedad numParcialidad.
     * 
     */
    public int getNumParcialidad() {
        return numParcialidad;
    }

    /**
     * Define el valor de la propiedad numParcialidad.
     * 
     */
    public void setNumParcialidad(int value) {
        this.numParcialidad = value;
    }

    /**
     * Obtiene el valor de la propiedad impSaldoAnt.
     * 
     */
    public double getImpSaldoAnt() {
        return impSaldoAnt;
    }

    /**
     * Define el valor de la propiedad impSaldoAnt.
     * 
     */
    public void setImpSaldoAnt(double value) {
        this.impSaldoAnt = value;
    }

    /**
     * Obtiene el valor de la propiedad impPagado.
     * 
     */
    public double getImpPagado() {
        return impPagado;
    }

    /**
     * Define el valor de la propiedad impPagado.
     * 
     */
    public void setImpPagado(double value) {
        this.impPagado = value;
    }

    /**
     * Obtiene el valor de la propiedad impSaldoInsoluto.
     * 
     */
    public double getImpSaldoInsoluto() {
        return impSaldoInsoluto;
    }

    /**
     * Define el valor de la propiedad impSaldoInsoluto.
     * 
     */
    public void setImpSaldoInsoluto(double value) {
        this.impSaldoInsoluto = value;
    }

}

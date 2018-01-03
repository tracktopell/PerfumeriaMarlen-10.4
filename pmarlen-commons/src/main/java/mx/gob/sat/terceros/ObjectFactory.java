
package mx.gob.sat.terceros;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.sat.terceros package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.sat.terceros
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Traslados }
     * 
     */
    public Traslados createTraslados() {
        return new Traslados();
    }

    /**
     * Create an instance of {@link Retenciones }
     * 
     */
    public Retenciones createRetenciones() {
        return new Retenciones();
    }

    /**
     * Create an instance of {@link Retencion }
     * 
     */
    public Retencion createRetencion() {
        return new Retencion();
    }

    /**
     * Create an instance of {@link Traslado }
     * 
     */
    public Traslado createTraslado() {
        return new Traslado();
    }

    /**
     * Create an instance of {@link Impuestos }
     * 
     */
    public Impuestos createImpuestos() {
        return new Impuestos();
    }

    /**
     * Create an instance of {@link InformacionFiscalTercero }
     * 
     */
    public InformacionFiscalTercero createInformacionFiscalTercero() {
        return new InformacionFiscalTercero();
    }

    /**
     * Create an instance of {@link InformacionAduanera }
     * 
     */
    public InformacionAduanera createInformacionAduanera() {
        return new InformacionAduanera();
    }

    /**
     * Create an instance of {@link PorCuentadeTerceros }
     * 
     */
    public PorCuentadeTerceros createPorCuentadeTerceros() {
        return new PorCuentadeTerceros();
    }

}

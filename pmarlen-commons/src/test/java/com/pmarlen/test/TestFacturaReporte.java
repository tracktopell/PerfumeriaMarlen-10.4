package com.pmarlen.test;

import com.pmarlen.backend.model.Cfd;
import com.pmarlen.backend.model.Cliente;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaFooter;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.reports.GeneradorImpresionPedidoVenta;
import com.pmarlen.model.Constants;
import com.sun.media.jfxmedia.logging.Logger;
import java.io.FileOutputStream;
import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;

/**
 *
 * @author Alfredo Estrada
 */
public class TestFacturaReporte {
    private static final String contenidoOriginalXML=
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<cfdi:Comprobante 	Version=\"3.3\" \n" +
"					Serie=\"PMM\" \n" +
"					Folio=\"5524\" \n" +
"					Fecha=\"2018-01-15T20:36:59\" \n" +
"					Sello=\"Ub0GBvNacNmC3ytw/QClomZuVZ39Xs1nFpD4OwY9jMkep80CE49L8CgR4xYzZyG1EnVgOQd2eTE654NS4/TxO8VplDw6VPzkcfEYSFPVQJbFTD+IX/jA+PwNAz/10fxt5EwH/g7DAJudPw827HPCWWGh1s+hVf1tdUe1Ogh+Sf80Onj5iC+bbjVRS6BAIDKxeynNvB01NFKgpGbAnOYp67J1CrDd5DIts5eKIu/eKzMvlTZgOXE33HKEezq1V7cW/xh6dlT0LbrphRk249T01933IKX968CCm2QxG1HoiZoPSwH7fWrD6q0C/RWljOrYQcoF8lIohid3F5Xu4+GNqg==\" \n" +
"					FormaPago=\"03\" \n" +
"					NoCertificado=\"00001000000404444142\" \n" +
"					Certificado=\"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\" \n" +
"					SubTotal=\"131.84\" \n" +
"					Moneda=\"MXN\" \n" +
"					Total=\"152.94\" \n" +
"					TipoDeComprobante=\"I\" \n" +
"					MetodoPago=\"PUE\" \n" +
"					LugarExpedicion=\"55740\"\n" +
"	xmlns:cfdi=\"http://www.sat.gob.mx/cfd/3\"\n" +
"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd\">\n" +
"	<cfdi:Emisor 	Rfc=\"PMA1208028U7\" Nombre=\"PERFUMERIA MARLEN, S.A. DE C.V.\" RegimenFiscal=\"601\"/>\n" +
"	<cfdi:Receptor Rfc=\"ASJ020301KK6\" Nombre=\"ABARROTES SAN JUAN HEHE, S.A. DE C.V.\" UsoCFDI=\"G01\"/>\n" +
"	<cfdi:Conceptos>\n" +
"		<cfdi:Concepto ClaveProdServ=\"53131613\" NoIdentificacion=\"7503020722011\" Cantidad=\"12.000000\" ClaveUnidad=\"H87\" Unidad=\"PZ\" Descripcion=\"CREMINA CREMA HUMECTANTE/ALMENDRAS(95 G)\" ValorUnitario=\"5.146552\" Importe=\"61.758621\">\n" +
"			<cfdi:Impuestos>\n" +
"				<cfdi:Traslados>\n" +
"					<cfdi:Traslado Base=\"61.758621\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"9.881379\"/>\n" +
"				</cfdi:Traslados>\n" +
"			</cfdi:Impuestos>\n" +
"		</cfdi:Concepto>\n" +
"		<cfdi:Concepto ClaveProdServ=\"53131608\" NoIdentificacion=\"7509546056357\" Cantidad=\"12.000000\" ClaveUnidad=\"H87\" Unidad=\"PZ\" Descripcion=\"PALMOLIVE NATURALS/INTENSA RENOVACI??N(110.0 G)\" ValorUnitario=\"5.836207\" Importe=\"70.034483\">\n" +
"			<cfdi:Impuestos>\n" +
"				<cfdi:Traslados>\n" +
"					<cfdi:Traslado Base=\"70.034483\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"11.205517\"/>\n" +
"				</cfdi:Traslados>\n" +
"			</cfdi:Impuestos>\n" +
"		</cfdi:Concepto>\n" +
"		<cfdi:Concepto ClaveProdServ=\"53131608\" NoIdentificacion=\"7509546056357\" Cantidad=\"6.000000\" ClaveUnidad=\"H87\" Unidad=\"PZ\" Descripcion=\"PALMOLIVE NATURALS/INTENSA RENOVACI??N(110.0 G)\" ValorUnitario=\"0.008621\" Importe=\"0.051724\">\n" +
"			<cfdi:Impuestos>\n" +
"				<cfdi:Traslados>\n" +
"					<cfdi:Traslado Base=\"0.051724\" Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"0.008276\"/>\n" +
"				</cfdi:Traslados>\n" +
"			</cfdi:Impuestos>\n" +
"		</cfdi:Concepto>\n" +
"	</cfdi:Conceptos>\n" +
"	<cfdi:Impuestos TotalImpuestosTrasladados=\"21.10\">\n" +
"		<cfdi:Traslados>\n" +
"			<cfdi:Traslado Impuesto=\"002\" TipoFactor=\"Tasa\" TasaOCuota=\"0.160000\" Importe=\"21.095171\"/>\n" +
"		</cfdi:Traslados>\n" +
"	</cfdi:Impuestos>\n" +
"	<cfdi:Complemento>\n" +
"		<tfd:TimbreFiscalDigital\n" +
"			xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\"\n" +
"			xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd\" \n" +
"			Version=\"1.1\" \n" +
"			UUID=\"24BE7820-FA66-11E7-BC8D-00155D014009\" \n" +
"			FechaTimbrado=\"2018-01-15T20:37:06\" \n" +
"			RfcProvCertif=\"TBN040609RKA\" \n" +
"			SelloCFD=\"Ub0GBvNacNmC3ytw/QClomZuVZ39Xs1nFpD4OwY9jMkep80CE49L8CgR4xYzZyG1EnVgOQd2eTE654NS4/TxO8VplDw6VPzkcfEYSFPVQJbFTD+IX/jA+PwNAz/10fxt5EwH/g7DAJudPw827HPCWWGh1s+hVf1tdUe1Ogh+Sf80Onj5iC+bbjVRS6BAIDKxeynNvB01NFKgpGbAnOYp67J1CrDd5DIts5eKIu/eKzMvlTZgOXE33HKEezq1V7cW/xh6dlT0LbrphRk249T01933IKX968CCm2QxG1HoiZoPSwH7fWrD6q0C/RWljOrYQcoF8lIohid3F5Xu4+GNqg==\" \n" +
"			NoCertificadoSAT=\"00001000000403557578\" \n" +
"			SelloSAT=\"UGs5NtwzGxo1eaG56OJ6B4NtnVPL7QgqKIQ6JDUnmk74p7tEAC61f6GJyt5vm6HG4uEeisBxUoX9B8D4Czl9dUezLXCt0z6HbkT4fwKhhC7yRta4+fn415tAGbvxVbm8PgjgU4zQOVfFT4hpBQCBAEGj0V5jKnIFDMVthNv7lXpphYupa0RUQ/qFc8th2N0WJq51B8xbauW/1dx7YKwUuY9uZzq/0HRdOZ1FaOrWhQpD0U5W5Nach21QlL4M0ZYupJPkUtAhEnDLwbIZlrSkvGqnp3xDjDdfNtS0RQsJWtjXy7joQ5VII8YqVlNq8apY6S3oyKos5atwZpvlwxVGhA==\"/>\n" +
"		</cfdi:Complemento>\n" +
"	</cfdi:Comprobante>";
    private EntradaSalidaQuickView pedidoVenta;
    private Cfd cfdFactura;
    private ArrayList<EntradaSalidaDetalleQuickView> esdList;
    private Cliente clienteVenta;
    private boolean fullPrint;
    private String usuarioImr;
    
    private Object[][] facturaData;
    
    public TestFacturaReporte() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Logger.setLevel(Logger.DEBUG);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        facturaData=new Object[][]{
            new Object[]{1,72,"7503007859099","53131626", 29.85},
            new Object[]{1,48,"7502244764845","47131824", 15.21},
            new Object[]{1,24,"7502244767143","47131830", 15.21},
            new Object[]{1,36,"6371"         ,"47131810", 13.9 },
            new Object[]{1,24,"7502244767389","47131801", 24.97},
            new Object[]{1,24,"7502244767396","47131801", 24.97},
            new Object[]{1,12,"7502244767112","53131608",119.5 },
            new Object[]{1,12,"7502244766900","53131608",119.5 },
            new Object[]{1,72,"7502244764487","53131608", 29.85},
            new Object[]{1,96,"7509546056357","53131608",  6.77},
            new Object[]{1,24,"7502245720079","53131602" ,29.93},
            new Object[]{1,12,"7509546056357","53131608",  6.77}
        };
        
        pedidoVenta = new EntradaSalidaQuickView();        
        pedidoVenta.setId(12345);
        pedidoVenta.setAutorizaDescuento(1);
        pedidoVenta.setFactorIva(Constants.IVA);
        pedidoVenta.setPorcentajeDescuentoExtra(0);
        pedidoVenta.setFormaDePagoDescripcion ("PPD|PAGO EN PARCIALIDADES O DIFERIDO");
        pedidoVenta.setMetodoDePagoDescripcion("99|POR DEFINIR");
        
        cfdFactura = new Cfd();
        cfdFactura.setNumCfd("TST12345");
        cfdFactura.setContenidoOriginalXml(contenidoOriginalXML.getBytes());
        esdList = new ArrayList<EntradaSalidaDetalleQuickView>();
        for(Object[] row:facturaData){
            EntradaSalidaDetalleQuickView esd = new EntradaSalidaDetalleQuickView();
            
            esd.setApTipoAlmacen       ((Integer)row[0]);
            esd.setCantidad            ((Integer)row[1]);
            esd.setUnidad              ("H87");
            esd.setProductoCodigoBarras((String)row[2]);
            esd.setNoIdentificacion    ((String)row[3]);
            esd.setProductoNombre("NOM");
            esd.setProductoPresentacion("PRES");
            esd.setProductoContenido("00.0");
            esd.setProductoUnidadMedida("G");
            esd.setPrecioVenta  ((Double) row[4]);
            
            esdList.add(esd);
        }
        
        
        clienteVenta = new Cliente();
        clienteVenta.setRazonSocial("RAZON SOCIAL");
        clienteVenta.setRfc("EAGA8012254X2");
        
        fullPrint = true;
        usuarioImr = "----";
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCalculaTotalesFactura() {
        EntradaSalidaFooter esf=new EntradaSalidaFooter();
        esf.calculaParaFacturaTotalesDesde(pedidoVenta, esdList);
    }
    
    @Ignore
    @Test
    public void testGeneraPDF() throws Exception{
        FileOutputStream fos=null;
        byte[] generaPdfPfacturaPedidoVenta = null;
        try{
            generaPdfPfacturaPedidoVenta = GeneradorImpresionPedidoVenta.generaPdfPfacturaPedidoVenta(pedidoVenta, cfdFactura, esdList, clienteVenta, true, usuarioImr);
            fos = new FileOutputStream("./target/PDF_FACTURA.pdf");
            fos.write(generaPdfPfacturaPedidoVenta);
            fos.flush();
            fos.close();
        }catch(Exception ex){
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
}

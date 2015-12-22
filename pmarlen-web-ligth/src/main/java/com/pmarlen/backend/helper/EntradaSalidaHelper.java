package com.pmarlen.backend.helper;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.quickviews.EntradaSalidaDetalleQuickView;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.businesslogic.LogicaFinaciera;
import com.pmarlen.businesslogic.TotalesCalculados;
import com.pmarlen.model.Constants;
import com.tracktopell.jdbc.LocalDataSourceFacade;
import java.util.ArrayList;

/**
 * com.pmarlen.backend.helper.EntradaSalidaHelper
 * @author alfredo
 */
public class EntradaSalidaHelper {
	
	public static void main(String[] args) {
		
		int esId = Integer.parseInt(args[0]);
		
		LocalDataSourceFacade.registerStrategy();
		EntradaSalidaQuickView es = null;
		ArrayList<EntradaSalidaDetalleQuickView> esdList = null;
		try {
			es = EntradaSalidaDAO.getInstance().findBy(new EntradaSalida(esId));
			esdList = EntradaSalidaDAO.getInstance().findAllESDByEntradaSalida(esId);
			
			for(EntradaSalidaDetalleQuickView esd:esdList){
				double importe   = esd.getCantidad()*esd.getPrecioVenta();
				
				System.out.println("\t"+esd.getCantidad()+" * "+esd.getProductoCodigoBarras()+"["+esd.getAlmacenId()+":"+
						Constants.getDescripcionTipoAlmacen(esd.getApTipoAlmacen())+"] "+Constants.dfMonedaLong.format(esd.getPrecioVenta())+" = "+Constants.dfMonedaLong.format(importe));
			}
			System.out.println("\t-----------------");
			TotalesCalculados ct = LogicaFinaciera.calculaTotales(es, esdList, false, 0.0, es.getSucursalId()==1?0:1);
			System.out.println("FACTURA:");
			System.out.println(ct.toString(true));
			System.out.println(" TICKET:");
			System.out.println(ct.toString(false));
		}catch(DAOException de){
			de.printStackTrace(System.err);
		}
	}
	
}

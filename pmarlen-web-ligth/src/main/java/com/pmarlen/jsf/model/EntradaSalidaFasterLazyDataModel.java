package com.pmarlen.jsf.model;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.EntradaSalidaDAO;
import com.pmarlen.backend.model.quickviews.EntradaSalidaQuickView;
import com.pmarlen.backend.model.quickviews.PagerInfo;
import com.pmarlen.model.Constants;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author alfredo
 */
public class EntradaSalidaFasterLazyDataModel extends LazyDataModel<EntradaSalidaQuickView>{
	List<EntradaSalidaQuickView> pageData;
	PagerInfo pagerInfo;
	
	private int tipoMov;
	private int sucursalId;
	boolean active;
	private Timestamp fechaInicial;
	private Timestamp fechaFinal;
	//private Double totalVenta;
	private EntradaSalidaDTOPageHelper esdtoH;
	private int caja;
	public static final int defaultPowsPerPage=25;
	
	//public EntradaSalidaLazyDataModel    (int tipoMov, int sucursalId, int caja, boolean active,Timestamp fechaInicial,Timestamp fechaFinal) {
	
	public EntradaSalidaFasterLazyDataModel(int tipoMov, int sucursalId, int caja, boolean active,Timestamp fechaInicial,Timestamp fechaFinal, int rowsPerPage) {
		this.tipoMov = tipoMov;
		this.sucursalId = sucursalId;
		this.caja = caja;
		this.active = active;
		this.pagerInfo = new PagerInfo(0, rowsPerPage, "ES.ID", PagerInfo.DESCENDING, null, 0);
		this.fechaInicial = fechaInicial;
		this.fechaFinal   = fechaFinal;
		
		try {
			this.esdtoH = new EntradaSalidaDTOPageHelper(tipoMov, sucursalId, this.caja, active, pagerInfo, fechaInicial, fechaFinal, 0.0);			
			this.pageData = EntradaSalidaDAO.getInstance().findFastAllActiveByPage(this.esdtoH);			
			super.setRowCount(pagerInfo.getTotalRowCount());
		}catch(DAOException de){
			this.pageData = null;
		}
    }
	
	public EntradaSalidaFasterLazyDataModel(int tipoMov, int sucursalId, boolean active,Timestamp fechaInicial,Timestamp fechaFinal, int rowsPerPage) {
		this(tipoMov, sucursalId, 0,active,fechaInicial,fechaFinal,rowsPerPage);
	}
	
	public EntradaSalidaFasterLazyDataModel(int tipoMov, int sucursalId, boolean active, int rowsPerPage) {
		this(tipoMov, sucursalId, active,null,null,rowsPerPage);
    }
	
	public EntradaSalidaFasterLazyDataModel(int tipoMov, int sucursalId, boolean active,Timestamp fechaInicial,Timestamp fechaFinal) {
		this(tipoMov, sucursalId, 0,active,fechaInicial,fechaFinal,defaultPowsPerPage);
	}

	public EntradaSalidaFasterLazyDataModel(int tipoMov, int sucursalId, boolean active) {
		this(tipoMov, sucursalId, active,null,null);
	}
     
    @Override
    public EntradaSalidaQuickView getRowData(String rowKey) {
        for(EntradaSalidaQuickView es : pageData) {
            if(es.getId().equals(rowKey)) {
                return es;
			}
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(EntradaSalidaQuickView es) {
        return es.getId();
    }
		
 
    @Override
    public List<EntradaSalidaQuickView> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        try {
			this.pagerInfo.setFilters(filters);
			this.pagerInfo.setFirst(first);
			this.pagerInfo.setPageSize(pageSize);
			if(sortField!=null){
				this.pagerInfo.setSortField(sortField);
				this.pagerInfo.setSortOrder(sortOrder.equals(SortOrder.ASCENDING )?PagerInfo.ASCENDIG:
											sortOrder.equals(SortOrder.DESCENDING)?PagerInfo.DESCENDING:
																			0);
			} else {
				this.pagerInfo.setSortField("ES.ID");
				this.pagerInfo.setSortOrder(PagerInfo.DESCENDING);
			}
			super.setPageSize(pageSize);
			this.esdtoH = new EntradaSalidaDTOPageHelper(tipoMov, sucursalId, this.caja, active, pagerInfo, fechaInicial, fechaFinal, 0.0);						
			this.pageData = EntradaSalidaDAO.getInstance().findFastAllActiveByPage(this.esdtoH);			
			super.setRowCount(pagerInfo.getTotalRowCount());
		}catch(DAOException de){
			this.pageData = null;
		}
		return this.pageData;
	}

	/**
	 * @return the fechaInicial
	 */
	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * @return the totalVenta
	 */
	public Double getTotalVenta() {
		return this.esdtoH.getImporteTotal();
	}

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}
	
}

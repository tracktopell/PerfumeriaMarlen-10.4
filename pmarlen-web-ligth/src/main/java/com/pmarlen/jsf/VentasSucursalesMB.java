package com.pmarlen.jsf;

import com.pmarlen.backend.dao.DAOException;
import com.pmarlen.backend.dao.SucursalDAO;
import com.pmarlen.backend.model.Sucursal;
import com.pmarlen.jsf.model.EntradaSalidaLazyDataModel;
import com.pmarlen.model.Constants;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

@ManagedBean(name="ventasSucursalesMB")
@ViewScoped

public class VentasSucursalesMB implements Serializable {
	private transient static Logger logger = Logger.getLogger(VentasSucursalesMB.class.getSimpleName());
	private int		sucursalId = -1;
	private int		caja       = 0;
	private ArrayList<SelectItem> sucursalesList;
	private ArrayList<SelectItem> cajasList;

	class IntervaloFecha implements Serializable{
		Date fechaInicial;
		Date fechaFinal;
		IntervaloFecha(Date fi,Date ff){
			fechaInicial =  fi;
			fechaFinal   =  ff;
		}
	}
	
	
	static final String IF_SOLO_YEAR = "ESTE AÑO";
	static final String IF_SPEC = "ESPECIFICO";
	static final String IF_SOLO_MES = "ESTE MES";
	static final String IF_SOLO_SEMANA = "ESTA SEMANA";
	static final String IF_SOLO_HOY = "SOLO HOY";

	private String intFechaSelec=IF_SOLO_HOY;	
	
	private ArrayList<SelectItem> intervalosFechas;
	private LinkedHashMap<String,IntervaloFecha> intervalosFechasList;
	private Date fechaInicial;
	private Date fechaFinal;
	private Double totalVenta;
	private static final long day  = 86400000;
	private static final long week = day * 7;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	@ManagedProperty(value = "#{editarPedidoVentaMB}")
	private EditarPedidoVentaMB editarPedidoVentaMB;	
	
	private EntradaSalidaLazyDataModel lazyModel;	
	private int viewRows;
	
	@PostConstruct
	public void init(){		
		logger.debug("init:");
		intFechaSelec = IF_SOLO_HOY;
		especificarFecha  = false;
		totalVenta = 0.0;
		getIntervalosFechas();
		onIntervaloFechaChange();
		getSucursalesList();		
		viewRows = 25;
	}

	public void refrescar(){
		fechaFinal = new Timestamp(fechaFinal.getTime()+day-1);
		logger.debug("refrescar: fechas ["+fechaInicial+","+fechaFinal+"]");
		lazyModel = null;
	}

	public void onSucursalChange() {
		logger.debug("onSucursalChange:sucursalId="+sucursalId);
		lazyModel = null;
	}
	
	public void onCajaChange() {
		logger.debug("onCajaChange:caja="+caja);
		lazyModel = null;
	}

	public void setEditarPedidoVentaMB(EditarPedidoVentaMB editarPedidoVentaMB) {
		this.editarPedidoVentaMB = editarPedidoVentaMB;
	}
	
	public void editar(int id){
		logger.trace("editar:id="+id);
		editarPedidoVentaMB.editar(id);
	}
	public String editarPedidoAction(int pedidoVentaId){
		logger.trace("editarPedidoAction:pedidoVentaId="+pedidoVentaId);
		return editarPedidoVentaMB.editar(pedidoVentaId);
	}
		
	public int getSizeList(){
		logger.trace("getSizeList()");
		return lazyModel.getRowCount();
	}

	public int getViewRows() {
		logger.trace("getViewRows()");
		return viewRows;
	}

	public void setViewRows(int viewRows) {
		logger.trace("setViewRows("+viewRows+")");
		this.viewRows = viewRows;
	}

	public String getImporteMoneda(double f){
		return Constants.getImporteMoneda(f);
	}

	public EntradaSalidaLazyDataModel getLazyModel() {
		if(lazyModel == null) {
			logger.info("getLazyModel:sucursalId="+sucursalId+", caja="+caja);
			lazyModel = new EntradaSalidaLazyDataModel(Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA,sucursalId,caja, true,
					new Timestamp(fechaInicial.getTime()),new Timestamp(fechaFinal.getTime()));
		}
		return lazyModel;
	}
	
	public double getTotalVenta(){
		return lazyModel.getTotalVenta();
	}
	
	public void setSucursalId(int sucursalId) {
		this.sucursalId = sucursalId;
	}

	public int getSucursalId() {
		return sucursalId;
	}
	
	public ArrayList<SelectItem> getIntervalosFechas() {
		if(intervalosFechas == null){
			intervalosFechas = new ArrayList<SelectItem>();
			intervalosFechasList = new LinkedHashMap<String,IntervaloFecha> ();
			Date today = new Date();
			Date xd = null;
			try{
				xd = sdf.parse(sdf.format(today));
			}catch(ParseException e){
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(xd);
			//	D L M M J V S
			//	1 2 3 4 5 6 7		
			int dow = cal.get(Calendar.DAY_OF_WEEK);
			int dom = cal.get(Calendar.DAY_OF_MONTH) - 2 ;
			int doy = cal.get(Calendar.DAY_OF_YEAR)  - 2;
			
			int dbw = 0;
			if(dow == 1) {
				dbw = 7;
			} else {
				dbw = dow - 1;
			}
			Date fechaInicialInt = null;
			Date fechaFinalInt   = null;
			IntervaloFecha intFec = null;
			
			
			fechaInicialInt = new Timestamp(xd.getTime()          );
			fechaFinalInt   = new Timestamp(xd.getTime() + day - 1);			
			intFec = new IntervaloFecha(fechaInicialInt,fechaFinalInt);
			intervalosFechasList.put(IF_SOLO_HOY,intFec);
			intervalosFechas.add(new SelectItem(IF_SOLO_HOY, IF_SOLO_HOY));
			
			fechaInicialInt = new Timestamp(xd.getTime() - dbw * day);
			fechaFinalInt   = new Timestamp(xd.getTime() + day - 1  );						
			intFec = new IntervaloFecha(fechaInicialInt,fechaFinalInt);			
			intervalosFechasList.put(IF_SOLO_SEMANA,intFec);
			intervalosFechas.add(new SelectItem(IF_SOLO_SEMANA, IF_SOLO_SEMANA));
			
			fechaInicialInt = new Timestamp(xd.getTime() - dom * day);
			fechaFinalInt   = new Timestamp(xd.getTime() + day - 1  );			
			intFec = new IntervaloFecha(fechaInicialInt,fechaFinalInt);			
			intervalosFechasList.put(IF_SOLO_MES,intFec);
			intervalosFechas.add(new SelectItem(IF_SOLO_MES, IF_SOLO_MES));
			
			fechaInicialInt = new Timestamp(xd.getTime() - doy * day);
			fechaFinalInt   = new Timestamp(xd.getTime() + day - 1  );			
			intFec = new IntervaloFecha(fechaInicialInt,fechaFinalInt);			
			intervalosFechasList.put(IF_SOLO_YEAR,intFec);
			intervalosFechas.add(new SelectItem(IF_SOLO_YEAR, IF_SOLO_YEAR));
			
			fechaInicialInt = new Timestamp(xd.getTime()          );
			fechaFinalInt   = new Timestamp(xd.getTime() + day - 1);			
			intFec = new IntervaloFecha(fechaInicialInt,fechaFinalInt);			
			intervalosFechasList.put(IF_SPEC,intFec);			
			intervalosFechas.add(new SelectItem(IF_SPEC, IF_SPEC));
		}
		return intervalosFechas;
	}
	
	private boolean especificarFecha;
	
	public void onIntervaloFechaChange() {		
		logger.debug("onIntervaloFechaChange:intFechaSelec="+intFechaSelec);
		if(intFechaSelec.endsWith(IF_SPEC)) {
			especificarFecha  = true;
			IntervaloFecha ifs = intervalosFechasList.get(intFechaSelec);
			fechaInicial = ifs.fechaInicial;
			fechaFinal   = ifs.fechaFinal;
			logger.debug("\t-->onIntervaloFechaChange: SECIFICAR: fechaInicial="+fechaInicial+", fechaFinal="+fechaFinal);
		} else {
			especificarFecha  = false;
			IntervaloFecha ifs = intervalosFechasList.get(intFechaSelec);
			fechaInicial = ifs.fechaInicial;
			fechaFinal   = ifs.fechaFinal;
			logger.debug("\t-->onIntervaloFechaChange:INTERVALO:fechaInicial="+fechaInicial+", fechaFinal="+fechaFinal);
			lazyModel = null;
		}
	}
	
	public ArrayList<SelectItem> getSucursalesList() {
		if(sucursalesList==null){
			sucursalesList = new ArrayList<SelectItem>();
			List<Sucursal> sucursales=null;
			try {
				sucursales=(List<Sucursal>) SucursalDAO.getInstance().findAll();
			}catch(DAOException de){
				logger.error(de.getMessage());			
			}
			
			if(sucursales != null){				
				for(Sucursal s:sucursales){
					if(s.getIdPadre() != null){						
						sucursalesList.add(new SelectItem(s.getId(),s.getNombre()));			
					}
				}
				sucursalId = (Integer)sucursalesList.get(0).getValue();
			}
		}
		return sucursalesList;
	}

	public ArrayList<SelectItem> getCajasList() {
		if(cajasList==null){
			cajasList = new ArrayList<SelectItem>();
				
			cajasList.add(new SelectItem(0,"[TODAS LAS CAJAS ]"));
			cajasList.add(new SelectItem(1 ,"[---- CAJA 1 ----]"));
			cajasList.add(new SelectItem(2 ,"[---- CAJA 2 ----]"));
			cajasList.add(new SelectItem(3 ,"[---- CAJA 3 ----]"));
			cajasList.add(new SelectItem(4 ,"[---- CAJA 4 ----]"));
		}
		return cajasList;
	}
	
	/**
	 * @return the fechaInicial
	 */
	public Date getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	/**
	 * @return the intFechaSelec
	 */
	public String getIntFechaSelec() {
		return intFechaSelec;
	}

	/**
	 * @param intFechaSelec the intFechaSelec to set
	 */
	public void setIntFechaSelec(String intFechaSelec) {
		this.intFechaSelec = intFechaSelec;
	}

	/**
	 * @return the especificarFecha
	 */
	public boolean isEspecificarFecha() {
		return especificarFecha;
	}

	/**
	 * @param especificarFecha the especificarFecha to set
	 */
	public void setEspecificarFecha(boolean especificarFecha) {
		this.especificarFecha = especificarFecha;
	}
	
	public boolean isIntervaloEspecifico() {
		return intFechaSelec.equals(IF_SPEC);
	}

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}
	
}

package com.pmarlen.caja.control;

import com.google.zxing.FormatException;
import com.pmarlen.caja.dao.ESFileSystemJsonDAO;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.view.CierreCajaJFrame;
import com.pmarlen.caja.view.TokenFrame;
import com.pmarlen.model.Constants;
import com.pmarlen.rest.dto.CorteCajaDTO;
import com.pmarlen.rest.dto.ES;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.rest.dto.U;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;
import org.apache.log4j.Logger;

/**
 *
 * @author tracktopell
 */
public class CierreCajaControl implements ActionListener , FocusListener, ValidadorDeCampos{
	private static Logger logger = Logger.getLogger(CierreCajaControl.class.getName());
	private CierreCajaJFrame cierreCajaDialog;	
	private double saldoInicial;
	private double saldoFinal;
	private double saldoEstimado;
	private double saldoNeto;
	private double diferencia;
	private String usuarioAutorizo;
	private String observaciones;
	private boolean cierreCorrecto;
	private boolean hayInconsistencia=false;
    private boolean autorizadoPorToken=false;
	public static final int OBSERVACIONES_MAX_LENGTH = 255;
	
	public CierreCajaControl(CierreCajaJFrame dialogLogin) {
		this.cierreCajaDialog = dialogLogin;
		this.cierreCajaDialog.getSaldoFinal().addActionListener(this);
		this.cierreCajaDialog.getAceptar() .addActionListener(this);
		this.cierreCajaDialog.getCancelar().addActionListener(this);		
		this.cierreCajaDialog.getLimpiar().addActionListener(this);
		this.cierreCajaDialog.getValidar().addActionListener(this);
        this.cierreCajaDialog.getSolicAut().addActionListener(this);
	}

	public CierreCajaJFrame getCierreCajaJFrame() {
		return cierreCajaDialog;
	}
	
	public void estadoInicial(){
        autorizadoPorToken=false;
		Double valueCCSaldoInicial = ApplicationLogic.getInstance().getCorteCajaDTO().getSaldoInicial();
		if(valueCCSaldoInicial != null){
			saldoInicial  = valueCCSaldoInicial.doubleValue();
		} else {
			saldoInicial  = 0.0;
		}
		//saldoEstimado = saldoInicial + ApplicationLogic.getInstance().getRemoteSaldoFinalEstimado();
		saldoEstimado = saldoInicial;
		buscarSaldoFinalEstimado();
		saldoNeto     = saldoEstimado - saldoInicial;
		logger.debug("estadoInicial:saldoInicial="+saldoInicial+", saldoEstimado="+saldoEstimado+", saldoNeto="+saldoNeto);
		observaciones = null;
		cierreCorrecto = false;
		cierreCajaDialog.getSaldoInicial().setText(Constants.df2Decimal.format(saldoInicial));
		cierreCajaDialog.getEstimado().setText(Constants.df2Decimal.format(saldoEstimado));
		cierreCajaDialog.getNeto().setText(Constants.df2Decimal.format(saldoNeto));
		cierreCajaDialog.getAceptar().setEnabled(false);
		
		saldoEstimado = saldoInicial;
		//buscarSaldoFinalEstimado();
		saldoNeto     = saldoEstimado - saldoInicial;
		cierreCajaDialog.getEstimado().setText(Constants.df2Decimal.format(saldoEstimado));
		cierreCajaDialog.getNeto().setText(Constants.df2Decimal.format(saldoNeto));
		
		
		
		setEnableAnormalControls(false);
		cierreCajaDialog.getCierreAnormalPanel().setVisible(false);
		
		cierreCajaDialog.getUsuarioAutorizo().setModel(getAdministradores());
		enfocarSaldoFinal();
        
        cierreCajaDialog.getAutorizado().setText("No se ha solicitado Autorización");
        cierreCajaDialog.getAutorizado().setForeground(Color.GRAY);
        
		cierreCajaDialog.setVisible(true);
        
	}
	
	private void enfocarSaldoFinal() {
		new Thread() {
			public void run() {
				try {
					logger.debug("enfocarSaldoFinal:enfocarSaldoFinal");
					Thread.sleep(1000);
					cierreCajaDialog.getSaldoFinal().requestFocus();					
				} catch (InterruptedException ie) {
				}
			}
		}.start();
	}
	

	private void setEnableAnormalControls(boolean d) {				
		cierreCajaDialog.getDiferencia().setEnabled(d);
		cierreCajaDialog.getJustificacion().setEnabled(d);
		cierreCajaDialog.getUsuarioAutorizo().setEnabled(d);
	}
	private double  totalVentas       = 0.0;
	private double  totalDevoluciones = 0.0;
    
    private static  boolean serachLocal = true;
    
	private void buscarSaldoFinalEstimado(){
		logger.debug("buscarSaldoFinalEstimado: -->> new Thread().start()");
		new Thread("RemoteSaldoEstimado:(serachLocal?"+serachLocal+")"){
			public void run(){
				try {
					final CorteCajaDTO aperturaCajaDTO = MemoryDAO.readLastSavedCorteCajaDTOApertura();
					logger.debug("buscarSaldoFinalEstimado: ("+serachLocal+")corteCajaDTO="+aperturaCajaDTO);
					SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
					Date beginingDay = null;
					try{
						beginingDay = sdfDate.parse(sdfDate.format(new Date()));
					}catch(ParseException ex){
						beginingDay = new Date();
					}
					logger.debug("buscarSaldoFinalEstimado: beginingDay="+Constants.sdfLogTS.format(beginingDay));
					
					if(aperturaCajaDTO != null && serachLocal){
						logger.debug("buscarSaldoFinalEstimado: [LOCAL] searching : aperturaCajaDTO="+aperturaCajaDTO);
						final ArrayList<ES_ESD> esList = ESFileSystemJsonDAO.getEsList();
                        logger.debug("buscarSaldoFinalEstimado: [LOCAL] esList.size()="+esList.size());
						double ttX = 0.0;
						totalVentas       = 0.0;
						totalDevoluciones = 0.0;
	
						for(ES_ESD esesd: esList){
                            logger.debug("buscarSaldoFinalEstimado: [LOCAL] \t"+esesd.getEs().toShrotString());
							if(     esesd.getEs().getFc() >= aperturaCajaDTO.getFecha() && 
                                    esesd.getEs().getFc() >= beginingDay.getTime()){
								final ES es = esesd.getEs(); 
								final int tm=es.getTm();
								int add = 0;
								double rxTx = 0.0;
								if(	tm == Constants.TIPO_MOV_SALIDA_ALMACEN_VENTA ){
									add = +1;
									rxTx = (es.getTot())*add;
									totalVentas       += rxTx;
								} else if(	tm ==Constants.TIPO_MOV_ENTRADA_ALMACEN_DEVOLUCION){
									add = -1;
									rxTx = (es.getTot())*add;
									totalDevoluciones += rxTx;
								}
								
								ttX += rxTx;
								logger.debug("\tbuscarSaldoFinalEstimado: [LOCAL] \t\t+ "+rxTx);
							}
						}
						logger.debug("buscarSaldoFinalEstimado:  [LOCAL] Suma:"+ttX);
						
						saldoEstimado = ttX;
						saldoNeto      = saldoEstimado + saldoInicial;
						saldoFinal     = saldoEstimado;
						
						logger.debug("buscarSaldoFinalEstimado[LOCAL]:saldoInicial="+saldoInicial+", saldoEstimado="+saldoEstimado+", saldoNeto="+saldoNeto);
					} else {					
						saldoEstimado = ApplicationLogic.getInstance().getRemoteSaldoFinalEstimado();
						saldoNeto      = saldoEstimado + saldoInicial;
						saldoFinal     = saldoEstimado;
						logger.debug("buscarSaldoFinalEstimado[RemoteSaldoEstimado]:saldoInicial="+saldoInicial+", saldoEstimado="+saldoEstimado+", saldoNeto="+saldoNeto);
					}
					
					if(totalVentas != 0 && totalDevoluciones!= 0){
						cierreCajaDialog.getEstimado().setText(Constants.df2Decimal.format(totalVentas)+" - "+Constants.df2Decimal.format(totalDevoluciones)+" = "+Constants.df2Decimal.format(saldoEstimado));
					} else {
						cierreCajaDialog.getEstimado().setText(Constants.df2Decimal.format(saldoEstimado));
					}
					
					cierreCajaDialog.getNeto().setText(Constants.df2Decimal.format(saldoNeto));
					cierreCajaDialog.getEstimado().setText(Constants.df2Decimal.format(saldoEstimado));					
					cierreCajaDialog.getSaldoFinal().setText(Constants.df2Decimal.format(saldoFinal));
					
				}catch(IOException ioe){
					logger.error("buscarSaldoFinalEstimado[RemoteSaldoEstimado]:: error al consultar el saldo.", ioe);
					JOptionPane.showMessageDialog(cierreCajaDialog, "NO SE PUEDE OBTENER EL SALDO FINAL ESTIMADO", "CIERRE CAJA", JOptionPane.ERROR_MESSAGE);
				}
			}
		}.start();
	}
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cierreCajaDialog.getSaldoFinal()) {
			saldoFinal_focusLost();
		} else if (e.getSource() == cierreCajaDialog.getAceptar()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == cierreCajaDialog.getValidar()) {
			validar_ActionPerformed();
		} else if (e.getSource() == cierreCajaDialog.getJustificacion()) {
			aceptar_ActionPerformed();
		} else if (e.getSource() == cierreCajaDialog.getCancelar()) {
			cancelar_ActionPerformed();
		} else if (e.getSource() == cierreCajaDialog.getLimpiar()) {
			limpiar_ActionPerformed();
		} else if(e.getSource() == cierreCajaDialog.getSolicAut()){
            solicAut_ActionPerformed();
        }
	}
	
	private void aceptar_ActionPerformed(){
		logger.info("[USER]->aceptar_ActionPerformed()");
		
		if(validateAll()) {
			cierreCajaDialog.getAceptar().setEnabled(true);
		} else {
			cierreCajaDialog.getAceptar().setEnabled(false);
		}
		
		registraCirreCaja();
	}
	
	private boolean validateAll() {
		JComponent componentWithError;
		try {
			validateNormal();
            if(hayInconsistencia){
                validateInconsistencias();
                if(autorizadoPorToken){
                    throw new ValidacionCamposException("Debe Solicitar Autorización por TOKEN",cierreCajaDialog.getSolicAut());
                }
            }
		} catch (ValidacionCamposException ve) {
			componentWithError = ve.getSource();
			if (componentWithError != null) {
				javax.swing.UIManager.put("OptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24)));
				javax.swing.UIManager.put("JOptionPane.font", new FontUIResource(new java.awt.Font("Tahoma", 0, 24)));
				JOptionPane.showMessageDialog(cierreCajaDialog, ve.getMessage(), "ACEPTAR", JOptionPane.ERROR_MESSAGE);
				if(componentWithError instanceof JTextField) {
					((JTextField)componentWithError).setText("");
				}
				componentWithError.requestFocus();
				return false;
			}
		}
		return true;
	}
	
	private void cancelar_ActionPerformed(){
		logger.info("[USER]->cancelar_ActionPerformed()");
		cierreCajaDialog.dispose();
		cierreCajaDialog = null;
	}
	
	private void registraCirreCaja(){
		
		ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioEmail(ApplicationLogic.getInstance().getLogged().getE());
		ApplicationLogic.getInstance().getCorteCajaDTO().setSucursalId(MemoryDAO.getSucursalId());
		ApplicationLogic.getInstance().getCorteCajaDTO().setCaja(MemoryDAO.getNumCaja());
		ApplicationLogic.getInstance().getCorteCajaDTO().setFecha(System.currentTimeMillis());
		ApplicationLogic.getInstance().getCorteCajaDTO().setTipoEvento(Constants.TIPO_EVENTO_CIERRE);
		ApplicationLogic.getInstance().getCorteCajaDTO().setSaldoInicial(saldoInicial);
		ApplicationLogic.getInstance().getCorteCajaDTO().setSaldoFinal(saldoFinal);		
		ApplicationLogic.getInstance().getCorteCajaDTO().setComentarios(observaciones);
		ApplicationLogic.getInstance().getCorteCajaDTO().setUsuarioAutorizo(usuarioAutorizo);
		
		MemoryDAO.saveCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
		MemoryDAO.backupCorteCajaDTO(ApplicationLogic.getInstance().getCorteCajaDTO());
		MemoryDAO.iniciaEnvioCierreCaja();
		
		cierreCorrecto=true;
		cierreCajaDialog.dispose();
		cierreCajaDialog = null;		
	}
	
	boolean seguirEsperando=true;
	
	public void esperaACErrarPorEnvioCaja(){
		logger.debug("esperaACErrarPorEnvioCaja:");
		seguirEsperando=true;
		try {
			int numEspera=0;
			//cierreCajaDialog
			FramePrincipalControl.getInstance().getFramePrincipal().getCerrando().setText("....AHORA SI CERRANDO");
			while(seguirEsperando){
				logger.debug("\tesperaACErrarPorEnvioCaja:-------->>["+numEspera+"]");
				FramePrincipalControl.getInstance().getFramePrincipal().getCerrando().setText("...ENVIANDO T=["+numEspera+"] :(  ...ESPERA");
				if(MemoryDAO.isEnviandoCierreCaja()){
					seguirEsperando = true;
				}else{
					seguirEsperando = false;
				}
				Thread.sleep(1000);
			}
			if(MemoryDAO.isEnviandoCierreCorrectmente()){
				logger.debug("SE ENVIO CORRCTAMENTE!");
			}
		}catch(InterruptedException ie){
			logger.error("SE INTERRUMPIO ENVIO:", ie);
		}
		
		cierreCajaDialog.dispose();
		cierreCajaDialog = null;
	}

	@Override
	public void focusGained(FocusEvent fe) {
		
	}

	@Override
	public void focusLost(FocusEvent fe) {
		if (fe.getSource() == cierreCajaDialog.getSaldoInicial()) {
			saldoFinal_focusLost();
		}
	}
	
	public void setFontBigest() {
		cierreCajaDialog.setFont(new java.awt.Font("Tahoma", 0, 24));
	}

	public void validateNormal() throws ValidacionCamposException {
		String saldoFinalValue = cierreCajaDialog.getSaldoFinal().getText().replace("$", "").replace(",", "").trim();		
		try {
			saldoFinal = Double.parseDouble(saldoFinalValue);		
		} catch(NumberFormatException nfe){
			throw new ValidacionCamposException("EL SALDO DEBE SER UN IMPORTE DE MONEDA", cierreCajaDialog.getSaldoInicial());
		}
		
		if(saldoFinal< 0 || saldoFinal > 100000 ) {
			throw new ValidacionCamposException("EL SALDO NO PARECE SER UN IMPORTE RAZONABLE: DEBE SER $0.00 A $99,999.99", cierreCajaDialog.getSaldoInicial());
		}
		double saldoEstimadoRedondeado = saldoEstimado;
		try {
			saldoEstimadoRedondeado = Constants.df2Decimal.parse(cierreCajaDialog.getEstimado().getText()).doubleValue();
		}catch(ParseException pe){
		}
		double difReal     = saldoFinal - saldoEstimadoRedondeado;
		double factorPerdida = 0.0;
		
		cierreCajaDialog.getDiferencia().setText(Constants.df2Decimal.format(difReal));
		boolean grave=false;
		if(difReal < 0 ) {
			factorPerdida = (difReal*-1)/saldoNeto;
			logger.info("validate:factorPerdida="+factorPerdida);
			
			cierreCajaDialog.getPanelsParametros().setSelectedComponent(cierreCajaDialog.getCierreAnormalPanel());
			
			if(saldoFinal < saldoInicial || factorPerdida<=0.1 || (difReal*-1) > 100.0){
				cierreCajaDialog.getDiferencia().setBackground(Color.RED);
				grave =true;
			} else {
				cierreCajaDialog.getDiferencia().setBackground(cierreCajaDialog.getSaldoInicial().getBackground());
				grave =false;
			}
			
			setEnableAnormalControls(true);
            
			hayInconsistencia=true;
		} else {
            hayInconsistencia=false;
			setEnableAnormalControls(false);
		}
	}
    
    public void validateInconsistencias() throws ValidacionCamposException {
		String saldoFinalValue = cierreCajaDialog.getSaldoFinal().getText().replace("$", "").replace(",", "").trim();		
			
        if( cierreCajaDialog.getJustificacion().getText().trim().length() < 10) {
            cierreCajaDialog.getJustificacion().setEnabled(true);
            throw new ValidacionCamposException("DEBE ESCRIBIR UNA RAZÓN COHERENTE DE LA DIFERENCIA Y PEDIR AUTORIZACIÓN", cierreCajaDialog.getJustificacion());
        } else {				
            observaciones = cierreCajaDialog.getJustificacion().getText().trim();

            if(observaciones.length() > 0 ){
                if(observaciones.length() > OBSERVACIONES_MAX_LENGTH) {
                    observaciones = observaciones.substring(0, OBSERVACIONES_MAX_LENGTH-3)+"...";
                } else if(observaciones.length() <= 10) {
                    cierreCajaDialog.getJustificacion().setEnabled(true);
                    throw new ValidacionCamposException("DEBE ESCRIBIR UNA RAZÓN CON COHERENCIA Y EXPLICITA, NO PUEDE SER UNA(S) SIMPLE(S) PALABRA(S)", cierreCajaDialog.getJustificacion());	
                    
                }
            } else {
                cierreCajaDialog.getJustificacion().setEnabled(true);
                throw new ValidacionCamposException("DEBE ESCRIBIR LA RAZÓN DE LA DIFERENCIA, NO PUEDE QUEDAR EN BLANCO ", cierreCajaDialog.getJustificacion());	
            }
        }
        
        if(cierreCajaDialog.getUsuarioAutorizo().getSelectedIndex()==0){
            throw new ValidacionCamposException("DEBE ELEGIR QUIÉN AUTORIZARÁ", cierreCajaDialog.getUsuarioAutorizo());	
        }
	}

	private void 	saldoFinal_focusLost() {
		logger.info("[USER]->saldoFinal_focusLost():saldoFinal="+cierreCajaDialog.getSaldoFinal().getText());
		if(validateAll()) {
			cierreCajaDialog.getAceptar().setEnabled(true);
		} else {
			cierreCajaDialog.getAceptar().setEnabled(false);
		}
	}

	public boolean isCierreCorrecto() {
		return cierreCorrecto;
	}

	private ComboBoxModel getAdministradores() {
		List<U> usuarioList = MemoryDAO.getPaqueteSinc().getUsuarioList();	
		logger.debug("getAdministradores:"+usuarioList);
		Vector<U> v= new Vector<U>();
		U seleccioneUsuario = new U();
		seleccioneUsuario.setE(null);
		seleccioneUsuario.setN("-- SELECCIONE --");
		v.add(seleccioneUsuario);
		for(U u:usuarioList){
			logger.trace("getAdministradores:\t->U:"+u+", ADMIN?"+u.getPlaysAsAdmin()+", Perfiles:"+u.getPerfiles());
			if(u.getPlaysAsAdmin()) {
				v.add(u);
			} else {
				logger.trace("getAdministradores:\t-> FUCKING FIX ?");
				final List<String> perfiles = u.getPerfiles();
				for(String p: perfiles){
					if(u.getA()!=0  &&  (p.equalsIgnoreCase(Constants.PERFIL_ADMIN)||p.equalsIgnoreCase(Constants.PERFIL_ROOT)) ){
						logger.trace("getAdministradores:\t\t->OK, LET's FUCKING FIX : ADD: "+u);
						v.add(u);
						break;
					}
				}
			}
		}
		logger.debug("getAdministradores:v="+v);
		ComboBoxModel m =  new DefaultComboBoxModel(v);
		
		return m;
	}

	private void validar_ActionPerformed() {
		if(validateAll()) {
			cierreCajaDialog.getAceptar().setEnabled(true);
		} else {
			cierreCajaDialog.getAceptar().setEnabled(false);
		}
	}

	private void limpiar_ActionPerformed() {
		estadoInicial();
	}

    private void solicAut_ActionPerformed() {
        autorizadoPorToken=false;
   		TokenFrame tf = new TokenFrame(FramePrincipalControl.getInstance().getFramePrincipal());
		
		tf.setVisible(true);
		
		if(tf.isAccepted()){
            autorizadoPorToken = true;
			logger.debug("terminar_ActionPerformed: Se autorizo.");
            cierreCajaDialog.getAutorizado().setText("OK, AUTORIZADO POR TOKEN");
            cierreCajaDialog.getAutorizado().setForeground(Color.GREEN);
            cierreCajaDialog.getSolicAut().setEnabled(false);
            cierreCajaDialog.getAceptar().setEnabled(true);
            cierreCajaDialog.getJustificacion().setEnabled(false);
		} else {
            cierreCajaDialog.getAutorizado().setText("AUTORIZACIÓN INCORRECTA, REINTENTE");
            cierreCajaDialog.getAutorizado().setForeground(Color.RED);
        }
    }
	
}

package com.pmarlen.caja.control;

import com.pmarlen.businesslogic.reports.TextReporter;
import com.pmarlen.caja.Main;
import com.pmarlen.caja.dao.ESFileSystemJsonDAO;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.VentaSesion;
import com.pmarlen.caja.model.VentaTableModel;
import com.pmarlen.caja.view.PanelVentas;
import com.pmarlen.model.Constants;
import com.pmarlen.model.JarpeReportsInfoDTO;
import com.pmarlen.model.OSValidator;
import com.pmarlen.rest.dto.ES_ESD;
import com.pmarlen.ticket.systemprinter.SendFileToSystemPrinter;
import com.pmarlen.ticket.systemprinter.UnixSendToLP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class PanelVentasControl implements ActionListener,TableModelListener,MouseListener{
	private static Logger logger = Logger.getLogger(PanelVentasControl.class.getName());
	private PanelVentas panelVentas;
	private DecimalFormat df;
	private VentaTableModel ventasTM;
	//private ImporteCellRender importeCellRender;
	//private FechaCellRender fechaCellRender;
	public PanelVentasControl(PanelVentas panelVentas) {
		this.panelVentas = panelVentas;
		ventasTM = new VentaTableModel();
		this.panelVentas.getVentasJTable().setModel(ventasTM);
		ventasTM.setVentaList(ESFileSystemJsonDAO.getEsList());
		ventasTM.addTableModelListener( this);
		
		panelVentas.getReimprimir().addActionListener(this);
		panelVentas.getVentasJTable().addMouseListener(this);
		panelVentas.getVentasJTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelVentas.getVentasJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean valueIsAdjusting = e.getValueIsAdjusting();
				if (!valueIsAdjusting) {
					updateSelectedRow();
				}
			}
		});
	
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelVentas.getVentasJTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		//panelVentas.getVentasJTable().getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		//panelVentas.getVentasJTable().getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		panelVentas.getVentasJTable().getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		logger.debug("->>table columns="+panelVentas.getVentasJTable().getColumnCount());
	}
	
	public void refrescar() {		
		final int tw = panelVentas.getVentasJTable().getWidth();
		int[] cws = new int[]{5,15,5,5,10,30,10,10,10};
		int cw;
		logger.debug("->panelVentas.getVentasJTable().getSize()="+tw);
		int i=0;
		for(int cwi : cws) {
			cw = (tw * cwi)/100;
			logger.trace("->\tpanelVentas.getVentasJTable().column["+i+"] PreferredWidth:"+cw);
		
			panelVentas.getVentasJTable().getColumnModel().getColumn(i++).setPreferredWidth(cw);
		}		
	}
	
	public void estadoInicial(){
		refrescar();
		panelVentas.getReimprimir().setEnabled(false);
		panelVentas.getVentasJTable().updateUI();		
		panelVentas.getVentasJTable().getSelectionModel().clearSelection();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelVentas.getReimprimir()){
			reimprimir_actionPerformed();
		}
	}
	private ES_ESD es_esd ;
	private void reimprimir_actionPerformed(){
		int idx = panelVentas.getVentasJTable().getSelectedRow();
		if(idx>=0){
			logger.debug("[USER]reimprimir_actionPerformed:idx="+idx);
			es_esd = ventasTM.getES_ESDAt(idx);
			if (ApplicationLogic.getInstance().isPrintingEnabled()) {
				new Thread() {
				@Override
				public void run() {
					logger.debug("terminar_ActionPerformed(): despues de cerrar dialogo: TICKET:"+es_esd.getEs().getNt());
					imprimirTicket();
				}
			}.start();
		}

		}
	}
	
	void imprimirTicket() {
		
		boolean printed = false;
		try {
			if(Main.dinamicDebug){
				TextReporter.DEBUG = true;
			} else {
				TextReporter.DEBUG = false;
			}
			
			TextReporter.setColumns(MemoryDAO.getTextSystemPrinterColumns());
			
			if(Main.dinamicTrace){
				TextReporter.DEBUG =true;
			} else {
				TextReporter.DEBUG =false;
			}
			
			JarpeReportsInfoDTO infoDTOTicket = VentaSesion.generaJarpeReportsInfoDTOTicket(es_esd);
			logger.debug("imprimirTicket:fileTicket="+infoDTOTicket);			
			byte[] generateTicketTXTBytes = TextReporter.generateTicketTXTBytes(infoDTOTicket);
			logger.debug("imprimirTicket:fileTicket:{\n"+new String(generateTicketTXTBytes)+"\n}");
			Date today = new Date();
			
			String fileTicket     = "TICKET_"+infoDTOTicket.getParameters().get("venta.ticket")+"_" + Constants.sdfThinDate.format(today) + ".txt";
			String dirPrintTicket = "./";
			FileOutputStream fos = null;
			String realTicketFileName = null;
			try{
				realTicketFileName = dirPrintTicket+fileTicket;
				fos = new FileOutputStream(realTicketFileName);
				
				logger.debug("imprimirTicket:ticketFileName:"+realTicketFileName);
				
				fos.write(generateTicketTXTBytes);
				fos.flush();
				fos.close();
				
				logger.debug("imprimirTicket:OSValidator.isUnix()?:"+OSValidator.isUnix());
				logger.debug("imprimirTicket:OSValidator.isMac():"+OSValidator.isMac());
				
				if(OSValidator.isUnix() || OSValidator.isMac()){
					logger.debug("imprimirTicket: calling UnixSendToLP.printFile");
					UnixSendToLP.printFile((String)fileTicket);
				}else{
					logger.debug("imprimirTicket: calling SendFileToSystemPrinter.printFile");
					SendFileToSystemPrinter.printFile((String)fileTicket);
				}
				printed = true;
			}catch(Exception e){
				printed = false;
				throw new Exception(e.getMessage());
			}
		} catch (Exception ioe) {
			logger.error("imprimiedo",ioe);
			//JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Error al imprimir Ticket", "Imprimir Ticket", JOptionPane.ERROR_MESSAGE);
		} finally {
			logger.debug("DESPUES DE IMPRIMIR TICKET: printed?"+printed);
			if (!printed) {
				logger.debug("ERROR AL IMPRIMIR");
				//t.printStackTrace(System.err);
				JOptionPane.showMessageDialog(FramePrincipalControl.getInstance().getFramePrincipal(), "Error grave al imprimir Ticket", "Imprimir Ticket", JOptionPane.ERROR_MESSAGE);
			}			
		}
	}
	


	@Override
	public void tableChanged(TableModelEvent e) {
		panelVentas.getVentasJTable().updateUI();		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}	
	
	private void updateSelectedRow() {
		int idx = panelVentas.getVentasJTable().getSelectedRow();
		logger.debug("=>updateSelectedRow:Selected:sr=" + idx);
		if(idx != -1){
			panelVentas.getReimprimir().setEnabled(true);
		} else {
			panelVentas.getReimprimir().setEnabled(false);
		}
	}

}

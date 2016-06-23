/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.view;

import java.awt.Font;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class PanelDevolucion extends javax.swing.JPanel {
	private static Logger logger = Logger.getLogger(PanelDevolucion.class.getName());

	private static DecimalFormat df = new DecimalFormat("$ ###,###,##0.00");
	private static String imagesDir = null;

	/**
	 * @return the df
	 */
	public static DecimalFormat getDf() {
		return df;
	}

	/**
	 * @return the imagesDir
	 */
	public static String getImagesDir() {
		return imagesDir;
	}
	/**
	 * Creates new form PanelVenta
	 */
	public PanelDevolucion() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoAlmacen = new javax.swing.ButtonGroup();
        jPanel17 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detalleVentaJTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        labelTotal1 = new javax.swing.JLabel();
        numArt = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        labelTotal = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        labelDescuento = new javax.swing.JLabel();
        descuento = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        labelTotal3 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        devolver = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        detalleVentaJTableDev = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        labelTotal2 = new javax.swing.JLabel();
        numArtDev = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        labelTotal5 = new javax.swing.JLabel();
        totalDev = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        labelTotal7 = new javax.swing.JLabel();
        motivoDev = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        terminar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        labelTotal8 = new javax.swing.JLabel();
        codigoBuscar = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        labelTotal6 = new javax.swing.JLabel();
        ticket = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        labelTotal9 = new javax.swing.JLabel();
        sucursal = new javax.swing.JTextField();
        labelTotal10 = new javax.swing.JLabel();
        caja = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        labelTotal4 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        labelTotal11 = new javax.swing.JLabel();
        atendio = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel17.setLayout(new javax.swing.BoxLayout(jPanel17, javax.swing.BoxLayout.X_AXIS));

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ELEMENTOS QUE FUERON VENDIDOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel13.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 200));

        detalleVentaJTable.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        detalleVentaJTable.setRowHeight(28);
        jScrollPane1.setViewportView(detalleVentaJTable);

        jPanel13.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(3, 1));

        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        labelTotal1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal1.setText("# ART. :");
        labelTotal1.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel12.add(labelTotal1);

        numArt.setEditable(false);
        numArt.setColumns(3);
        numArt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        numArt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel12.add(numArt);

        jPanel14.add(jPanel12);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        labelTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal.setText("Subtotal :");
        jPanel6.add(labelTotal);

        subtotal.setEditable(false);
        subtotal.setColumns(6);
        subtotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        subtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel6.add(subtotal);

        jPanel14.add(jPanel6);

        jPanel1.add(jPanel14);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        labelDescuento.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelDescuento.setText("-Descuento :");
        jPanel15.add(labelDescuento);

        descuento.setEditable(false);
        descuento.setColumns(6);
        descuento.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        descuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel15.add(descuento);

        jPanel1.add(jPanel15);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        labelTotal3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal3.setText("Total :");
        jPanel16.add(labelTotal3);

        total.setEditable(false);
        total.setColumns(6);
        total.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel16.add(total);

        jPanel1.add(jPanel16);

        jPanel13.add(jPanel1, java.awt.BorderLayout.SOUTH);

        jPanel17.add(jPanel13);

        jPanel9.setPreferredSize(new java.awt.Dimension(30, 40));
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        devolver.setText(">");
        devolver.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jPanel9.add(devolver);

        jPanel17.add(jPanel9);

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ELEMENTOS A DEVOLVER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel19.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 200));

        detalleVentaJTableDev.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        detalleVentaJTableDev.setRowHeight(28);
        jScrollPane2.setViewportView(detalleVentaJTableDev);

        jPanel19.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.GridLayout(3, 1));

        jPanel20.setLayout(new javax.swing.BoxLayout(jPanel20, javax.swing.BoxLayout.LINE_AXIS));

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        labelTotal2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal2.setText("# ART. :");
        labelTotal2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel21.add(labelTotal2);

        numArtDev.setEditable(false);
        numArtDev.setColumns(3);
        numArtDev.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        numArtDev.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel21.add(numArtDev);

        jPanel20.add(jPanel21);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        labelTotal5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal5.setText("Total :");
        labelTotal5.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel23.add(labelTotal5);

        totalDev.setEditable(false);
        totalDev.setColumns(6);
        totalDev.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        totalDev.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel23.add(totalDev);

        jPanel20.add(jPanel23);

        jPanel5.add(jPanel20);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        labelTotal7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal7.setText("Motivo :");
        labelTotal7.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel18.add(labelTotal7);

        motivoDev.setColumns(15);
        motivoDev.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        motivoDev.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jPanel18.add(motivoDev);

        jPanel5.add(jPanel18);

        jPanel19.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jPanel17.add(jPanel19);

        add(jPanel17, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(1, 1));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        terminar.setText("TERMINAR DEVOLUCIÓN [ F7 ]");
        jPanel4.add(terminar);

        cancelar.setText("CANCELAR [ F11 ]");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel4.add(cancelar);

        jPanel3.add(jPanel4);

        add(jPanel3, java.awt.BorderLayout.SOUTH);

        jPanel22.setLayout(new java.awt.BorderLayout());

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelTotal8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal8.setText("PRODUCTO :");
        labelTotal8.setPreferredSize(new java.awt.Dimension(160, 30));
        jPanel10.add(labelTotal8);

        codigoBuscar.setColumns(13);
        codigoBuscar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel10.add(codigoBuscar);

        jPanel22.add(jPanel10, java.awt.BorderLayout.SOUTH);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VENTA ORIGEN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 18))); // NOI18N
        jPanel7.setLayout(new java.awt.GridLayout(3, 1));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelTotal6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal6.setText("# TICKET :");
        labelTotal6.setPreferredSize(new java.awt.Dimension(160, 30));
        jPanel8.add(labelTotal6);

        ticket.setColumns(13);
        ticket.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel8.add(ticket);

        buscar.setText("BUSCAR");
        jPanel8.add(buscar);

        jPanel7.add(jPanel8);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelTotal9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal9.setText("SUCURSAL :");
        labelTotal9.setPreferredSize(new java.awt.Dimension(160, 30));
        jPanel11.add(labelTotal9);

        sucursal.setEditable(false);
        sucursal.setColumns(16);
        sucursal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        sucursal.setEnabled(false);
        jPanel11.add(sucursal);

        labelTotal10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal10.setText("CAJA #:");
        labelTotal10.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(labelTotal10);

        caja.setEditable(false);
        caja.setColumns(2);
        caja.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        caja.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        caja.setEnabled(false);
        jPanel11.add(caja);

        jPanel7.add(jPanel11);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        labelTotal4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal4.setText("FECHA :");
        labelTotal4.setPreferredSize(new java.awt.Dimension(160, 30));
        jPanel2.add(labelTotal4);

        fecha.setEditable(false);
        fecha.setColumns(10);
        fecha.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fecha.setEnabled(false);
        jPanel2.add(fecha);

        labelTotal11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal11.setText("ATENDIÓ :");
        labelTotal11.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel2.add(labelTotal11);

        atendio.setEditable(false);
        atendio.setColumns(20);
        atendio.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        atendio.setEnabled(false);
        jPanel2.add(atendio);

        jPanel7.add(jPanel2);

        jPanel22.add(jPanel7, java.awt.BorderLayout.NORTH);

        add(jPanel22, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField atendio;
    private javax.swing.JButton buscar;
    private javax.swing.JTextField caja;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField codigoBuscar;
    private javax.swing.JTextField descuento;
    private javax.swing.JTable detalleVentaJTable;
    private javax.swing.JTable detalleVentaJTableDev;
    private javax.swing.JButton devolver;
    private javax.swing.JTextField fecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDescuento;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotal1;
    private javax.swing.JLabel labelTotal10;
    private javax.swing.JLabel labelTotal11;
    private javax.swing.JLabel labelTotal2;
    private javax.swing.JLabel labelTotal3;
    private javax.swing.JLabel labelTotal4;
    private javax.swing.JLabel labelTotal5;
    private javax.swing.JLabel labelTotal6;
    private javax.swing.JLabel labelTotal7;
    private javax.swing.JLabel labelTotal8;
    private javax.swing.JLabel labelTotal9;
    private javax.swing.JTextField motivoDev;
    private javax.swing.JTextField numArt;
    private javax.swing.JTextField numArtDev;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTextField sucursal;
    private javax.swing.JButton terminar;
    private javax.swing.JTextField ticket;
    private javax.swing.ButtonGroup tipoAlmacen;
    private javax.swing.JTextField total;
    private javax.swing.JTextField totalDev;
    // End of variables declaration//GEN-END:variables


	/**
	 * @return the cancelar
	 */
	public javax.swing.JButton getCancelar() {
		return cancelar;
	}

	/**
	 * @return the codigoBuscar
	 */
	public javax.swing.JTextField getCodigoBuscar() {
		return codigoBuscar;
	}

	/**
	 * @return the detalleVentaJTable
	 */
	public javax.swing.JTable getDetalleVentaJTable() {
		return detalleVentaJTable;
	}

	/**
	 * @return the terminar
	 */
	public javax.swing.JButton getTerminar() {
		return terminar;
	}

	/**
	 * @return the total
	 */
	public javax.swing.JTextField getTotal() {
		return total;
	}

	public JTextField getSubtotal() {
		return subtotal;
	}

	public JTextField getDescuento() {
		return descuento;
	}

	public JLabel getLabelDescuento() {
		return labelDescuento;
	}

	public ButtonGroup getTipoAlmacen() {
		return tipoAlmacen;
	}
	
	public JTextField getNumArt() {
		return numArt;
	}

	@Override
	public void setFont(Font font) {
		super.setFont(font); //To change body of generated methods, choose Tools | Templates.
		if(fecha!=null) {
			fecha.setFont(font);
			labelTotal.setFont(font);
			total.setFont(font);
			terminar.setFont(font);
			cancelar.setFont(font);
			detalleVentaJTable.setFont(font);
			detalleVentaJTable.setRowHeight(font.getSize()*2);
		}
	}

	/**
	 * @return the buscar
	 */
	public javax.swing.JButton getBuscar() {
		return buscar;
	}

	/**
	 * @return the detalleVentaJTableDev
	 */
	public javax.swing.JTable getDetalleVentaJTableDev() {
		return detalleVentaJTableDev;
	}

	/**
	 * @return the motivoDev
	 */
	public javax.swing.JTextField getMotivoDev() {
		return motivoDev;
	}

	/**
	 * @return the numArtDev
	 */
	public javax.swing.JTextField getNumArtDev() {
		return numArtDev;
	}

	/**
	 * @return the ticket
	 */
	public javax.swing.JTextField getTicket() {
		return ticket;
	}

	/**
	 * @return the totalDev
	 */
	public javax.swing.JTextField getTotalDev() {
		return totalDev;
	}

	public JButton getDevolver() {
		return devolver;
	}

	public JTextField getSucursal() {
		return sucursal;
	}

	public JTextField getCaja() {
		return caja;
	}

	public JTextField getFecha() {
		return fecha;
	}

	public JTextField getAtendio() {
		return atendio;
	}
	
}

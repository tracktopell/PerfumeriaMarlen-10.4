/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.caja.view;

import com.pmarlen.backend.model.Producto;
import com.pmarlen.backend.model.quickviews.InventarioSucursalQuickView;
import com.pmarlen.caja.dao.MemoryDAO;
import com.pmarlen.caja.model.PedidoVentaDetalleTableModel;
import com.pmarlen.model.Constants;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class PanelVenta extends javax.swing.JPanel {
	private static Logger logger = Logger.getLogger(PanelVenta.class.getName());

	private static DecimalFormat df = new DecimalFormat("$ ###,###,##0.00");
	private static String imagesDir = null;
	/**
	 * Creates new form PanelVenta
	 */
	public PanelVenta() {
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codigoBuscar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        labelTotal1 = new javax.swing.JLabel();
        numArt = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        labelTotal = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        terminar = new javax.swing.JButton();
        chechar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        splitPanel = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        detalleVentaJTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        imgProducto = new javax.swing.JLabel();
        infoColsPanel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        labelCodigoBarras = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        labelPresentacion = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        labelContenido = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        labelPrecio = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setIcon(getBarCodeImageIcon());
        jPanel2.add(jLabel1);

        codigoBuscar.setColumns(10);
        codigoBuscar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel2.add(codigoBuscar);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        labelTotal1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal1.setText("# ART. :");
        jPanel12.add(labelTotal1);

        numArt.setEditable(false);
        numArt.setColumns(4);
        numArt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        numArt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel12.add(numArt);

        jPanel1.add(jPanel12);

        labelTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTotal.setText("Total :");
        jPanel6.add(labelTotal);

        total.setEditable(false);
        total.setColumns(8);
        total.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel6.add(total);

        jPanel1.add(jPanel6);

        jPanel3.add(jPanel1);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        terminar.setText("TERMINAR VENTA [ F2 ]");
        jPanel4.add(terminar);

        chechar.setText("CHECAR [ F3 ]");
        jPanel4.add(chechar);

        cancelar.setText("CANCELAR [ F12 ]");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel4.add(cancelar);

        jPanel3.add(jPanel4);

        add(jPanel3, java.awt.BorderLayout.SOUTH);

        splitPanel.setDividerLocation(500);
        splitPanel.setOneTouchExpandable(true);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 200));

        detalleVentaJTable.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        detalleVentaJTable.setModel(new com.pmarlen.caja.model.PedidoVentaDetalleTableModel());
        detalleVentaJTable.setRowHeight(28);
        jScrollPane1.setViewportView(detalleVentaJTable);

        splitPanel.setLeftComponent(jScrollPane1);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(180, 10));
        jPanel5.setLayout(new java.awt.BorderLayout());

        imgProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgProducto.setIcon(getDefaultProductoImageIcon());
        jPanel5.add(imgProducto, java.awt.BorderLayout.CENTER);

        infoColsPanel.setBackground(new java.awt.Color(255, 255, 255));
        infoColsPanel.setLayout(new javax.swing.BoxLayout(infoColsPanel, javax.swing.BoxLayout.Y_AXIS));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        labelCodigoBarras.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        labelCodigoBarras.setText("||CODIGO BARRAS ||");
        jPanel11.add(labelCodigoBarras);

        infoColsPanel.add(jPanel11);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        labelNombre.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        labelNombre.setText("NOMBRE PRODUCTO");
        jPanel7.add(labelNombre);

        infoColsPanel.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        labelPresentacion.setFont(new java.awt.Font("Lucida Grande", 2, 13)); // NOI18N
        labelPresentacion.setText("PRESENTACIÓN");
        jPanel8.add(labelPresentacion);

        infoColsPanel.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        labelContenido.setText("CONTENIDO");
        jPanel9.add(labelContenido);

        infoColsPanel.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        labelPrecio.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        labelPrecio.setText("$ PRECIO");
        jPanel10.add(labelPrecio);

        infoColsPanel.add(jPanel10);

        jPanel5.add(infoColsPanel, java.awt.BorderLayout.NORTH);

        splitPanel.setRightComponent(jPanel5);

        add(splitPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton chechar;
    private javax.swing.JTextField codigoBuscar;
    private javax.swing.JTable detalleVentaJTable;
    private javax.swing.JLabel imgProducto;
    private javax.swing.JPanel infoColsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCodigoBarras;
    private javax.swing.JLabel labelContenido;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPrecio;
    private javax.swing.JLabel labelPresentacion;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotal1;
    private javax.swing.JTextField numArt;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JButton terminar;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables

	/**
	 * @return the chechar
	 */
	public JButton getChechar() {
		return chechar;
	}

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

	public JTextField getNumArt() {
		return numArt;
	}
	
	public JLabel getImgProducto() {
		return imgProducto;
	}
	
	private javax.swing.ImageIcon getBarCodeImageIcon(){
		javax.swing.ImageIcon ii = null;
		try{
			ii = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/barcode_icon_52x24.png")));
		} catch(Exception ioe){
		}
		return ii;
	}

	public JSplitPane getSplitPanel() {
		return splitPanel;
	}
	
	@Override
	public void setFont(Font font) {
		super.setFont(font); //To change body of generated methods, choose Tools | Templates.
		if(codigoBuscar!=null) {
			codigoBuscar.setFont(font);
			labelTotal.setFont(font);
			total.setFont(font);
			terminar.setFont(font);
			chechar.setFont(font);
			cancelar.setFont(font);
			detalleVentaJTable.setFont(font);
			detalleVentaJTable.setRowHeight(font.getSize()*2);
		}
	}
	ImageIcon defaultProductoImageIcon = null;

	public static String getImagesDir() {
		if(imagesDir == null){
			imagesDir = MemoryDAO.getProperty("dropboxdir");
		}
		return imagesDir;
	}
	
	private ImageIcon getDefaultProductoImageIcon(){
		if(defaultProductoImageIcon==null){
			try{
				//defaultProductoImageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/cardboard-box-icon-320x320_1.jpg")));
				defaultProductoImageIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/cardboard-box-icon-256x256_1.png")));
			} catch(Exception e){
				e.printStackTrace(System.err);
			}
		}
		return defaultProductoImageIcon;	
	}
	
	String imagePath = "jpg/";
	
	private ImageIcon getProductoImageIcon(Producto p){
		logger.debug("getProductoImageIcon(" + p +")");
		ImageIcon productoImageIcon = null;
		File fileImage=null;
		try{			
			String fileNaeImage = getImagesDir()+imagePath+"/MIN_"+p.getCodigoBarras()+"_01.jpg";
			fileImage = new File(fileNaeImage);
			logger.debug("getProductoImageIcon(" + p +"): fileNaeImage="+fileNaeImage);
			if(fileImage.exists() && fileImage.canRead()) {
				logger.trace("=>getProductoImageIcon: fileImage:" + fileImage+", size:"+fileImage.length()+" bytes");
				productoImageIcon = new ImageIcon(ImageIO.read(new FileInputStream(fileImage)));
			} else {
				throw new FileNotFoundException("Image not found for :"+p.getCodigoBarras()+", fileImage:"+fileImage);
			}
		} catch(FileNotFoundException fnfe){
			logger.debug("getProductoImageIcon(" + p +"): Not fount:"+fnfe.getMessage()+" =>> DEFAULT !");
			productoImageIcon = getDefaultProductoImageIcon();
		} catch(Exception e){
			logger.error("getProductoImageIcon("+p.getCodigoBarras()+"):Reading Image File:",e);
			productoImageIcon = getDefaultProductoImageIcon();
		}
		return productoImageIcon;	
	}
	
	public void resetInfoForProducto(final InventarioSucursalQuickView p,int tipoAlmacen){
		logger.trace("p=" + p);
		if(p!=null){
			labelCodigoBarras.setText(p.getCodigoBarras());
			labelNombre.setText(p.getNombre());		
			labelPresentacion.setText(p.getPresentacion());
			labelContenido.setText(p.getContenido()+" "+p.getUnidadMedida());
			//infoColsPanel.setToolTipText("A1["+p.getA1c()+"] AO["+p.getaOc()+"] AR["+p.getaRc()+"]");
			infoColsPanel.setToolTipText("#["+p.getA1c()+"]");
			if(tipoAlmacen == Constants.ALMACEN_PRINCIPAL)
				labelPrecio.setText(df.format(p.getA1p()));
			else if(tipoAlmacen == Constants.ALMACEN_OPORTUNIDAD)
				labelPrecio.setText(df.format(p.getaOp()));
			else if(tipoAlmacen == Constants.ALMACEN_REGALIAS)
				labelPrecio.setText(df.format(p.getaRp()));
			
			new Thread(){
				@Override
				public void run() {
					imgProducto.setIcon(getProductoImageIcon(p));
				}
			}.start();
		} else {
			infoColsPanel.setToolTipText(null);
			labelCodigoBarras.setText("|| CODIGO BARRAS ||");
			labelNombre.setText("NOMBRE PRODUCTO");		
			labelPresentacion.setText("PRESENTACIÓN");
			labelContenido.setText("CONTENIDO");
			labelPrecio.setText("$ PRECIO");
			imgProducto.setIcon(getDefaultProductoImageIcon());
		}
	}

}

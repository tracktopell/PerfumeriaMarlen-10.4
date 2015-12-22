package com.pmarlen.businesslogic;

import com.pmarlen.model.Constants;

/**
 *
 * @author alfredo
 */
public class TotalesCalculados {
	double subTotal1ra;
	double subTotalOpo;
	double subTotalReg;		
	
	double subTotal1raNG;
	double subTotalOpoNG;
	double subTotalRegNG;
	
	double subTotal;
	double subTotalNG;

	double iva;

	double factorDescCalculado;
	double descuentoRedondeo;
	double descuento;
	int    porcentajeCalculado;
	int    porcentajeExtra;
	double total;
	double totalFinal;
	double totalCobrar;
	
	double recibidoEfectivo;
	double recibidoTarjeta;
	double cambio;

	public TotalesCalculados() {
	}
	

	/**
	 * @return the subTotal1ra
	 */
	public double getSubTotal1ra() {
		return subTotal1ra;
	}

	/**
	 * @param subTotal1ra the subTotal1ra to set
	 */
	public void setSubTotal1ra(double subTotal1ra) {
		this.subTotal1ra = subTotal1ra;
	}

	/**
	 * @return the subTotalOpo
	 */
	public double getSubTotalOpo() {
		return subTotalOpo;
	}

	/**
	 * @param subTotalOpo the subTotalOpo to set
	 */
	public void setSubTotalOpo(double subTotalOpo) {
		this.subTotalOpo = subTotalOpo;
	}

	/**
	 * @return the subTotalReg
	 */
	public double getSubTotalReg() {
		return subTotalReg;
	}

	/**
	 * @param subTotalReg the subTotalReg to set
	 */
	public void setSubTotalReg(double subTotalReg) {
		this.subTotalReg = subTotalReg;
	}

	/**
	 * @return the subTotal1raNG
	 */
	public double getSubTotal1raNG() {
		return subTotal1raNG;
	}

	/**
	 * @param subTotal1raNG the subTotal1raNG to set
	 */
	public void setSubTotal1raNG(double subTotal1raNG) {
		this.subTotal1raNG = subTotal1raNG;
	}

	/**
	 * @return the subTotalOpoNG
	 */
	public double getSubTotalOpoNG() {
		return subTotalOpoNG;
	}

	/**
	 * @param subTotalOpoNG the subTotalOpoNG to set
	 */
	public void setSubTotalOpoNG(double subTotalOpoNG) {
		this.subTotalOpoNG = subTotalOpoNG;
	}

	/**
	 * @return the subTotalRegNG
	 */
	public double getSubTotalRegNG() {
		return subTotalRegNG;
	}

	/**
	 * @param subTotalRegNG the subTotalRegNG to set
	 */
	public void setSubTotalRegNG(double subTotalRegNG) {
		this.subTotalRegNG = subTotalRegNG;
	}

	/**
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the factorDescCalculado
	 */
	public double getFactorDescCalculado() {
		return factorDescCalculado;
	}

	/**
	 * @param factorDescCalculado the factorDescCalculado to set
	 */
	public void setFactorDescCalculado(double factorDescCalculado) {
		this.factorDescCalculado = factorDescCalculado;
	}

	/**
	 * @return the descuentoRedondeo
	 */
	public double getDescuentoRedondeo() {
		return descuentoRedondeo;
	}

	/**
	 * @param descuentoRedondeo the descuentoRedondeo to set
	 */
	public void setDescuentoRedondeo(double descuentoRedondeo) {
		this.descuentoRedondeo = descuentoRedondeo;
	}

	/**
	 * @return the descuento
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	/**
	 * @return the porcentajeCalculado
	 */
	public int getPorcentajeCalculado() {
		return porcentajeCalculado;
	}

	/**
	 * @param porcentajeCalculado the porcentajeCalculado to set
	 */
	public void setPorcentajeCalculado(int porcentajeCalculado) {
		this.porcentajeCalculado = porcentajeCalculado;
	}

	/**
	 * @return the porcentajeExtra
	 */
	public int getPorcentajeExtra() {
		return porcentajeExtra;
	}

	/**
	 * @param porcentajeExtra the porcentajeExtra to set
	 */
	public void setPorcentajeExtra(int porcentajeExtra) {
		this.porcentajeExtra = porcentajeExtra;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the totalCobrar
	 */
	public double getTotalCobrar() {
		return totalCobrar;
	}

	/**
	 * @param totalCobrar the totalCobrar to set
	 */
	public void setTotalCobrar(double totalCobrar) {
		this.totalCobrar = totalCobrar;
	}

	/**
	 * @return the cambio
	 */
	public double getCambio() {
		return cambio;
	}

	/**
	 * @param cambio the cambio to set
	 */
	public void setCambio(double cambio) {
		this.cambio = cambio;
	}

	/**
	 * @return the recibidoEfectivo
	 */
	public double getRecibidoEfectivo() {
		return recibidoEfectivo;
	}

	/**
	 * @param recibidoEfectivo the recibidoEfectivo to set
	 */
	public void setRecibidoEfectivo(double recibidoEfectivo) {
		this.recibidoEfectivo = recibidoEfectivo;
	}

	/**
	 * @return the recibidoTarjeta
	 */
	public double getRecibidoTarjeta() {
		return recibidoTarjeta;
	}

	/**
	 * @param recibidoTarjeta the recibidoTarjeta to set
	 */
	public void setRecibidoTarjeta(double recibidoTarjeta) {
		this.recibidoTarjeta = recibidoTarjeta;
	}
	
	
	/**
	 * @return the subTotalNG
	 */
	public double getSubTotalNG() {
		return subTotalNG;
	}

	/**
	 * @param subTotalNG the subTotalNG to set
	 */
	public void setSubTotalNG(double subTotalNG) {
		this.subTotalNG = subTotalNG;
	}
	
	
	/**
	 * @return the totalFinal
	 */
	public double getTotalFinal() {
		return totalFinal;
	}

	/**
	 * @param totalFinal the totalFinal to set
	 */
	public void setTotalFinal(double totalFinal) {
		this.totalFinal = totalFinal;
	}

	/**
	 * @return the iva
	 */
	public double getIva() {
		return iva;
	}

	/**
	 * @param iva the iva to set
	 */
	public void setIva(double iva) {
		this.iva = iva;
	}

	public String toString(boolean vistaFactura) {
		StringBuilder sb= new StringBuilder();
		sb.append("===========================================================\n");
		
		sb.append("S.T. 1RA   :").append("+\t").append(String.format("%15s",Constants.dfCurrency.format(subTotal1ra))).append("\n");
		sb.append("S.T. OPO   :").append("+\t").append(String.format("%15s",Constants.dfCurrency.format(subTotalOpo))).append("\n");
		sb.append("S.T. REG   :").append("+\t").append(String.format("%15s",Constants.dfCurrency.format(subTotalReg))).append("\n");
		sb.append("                 -------------------\n");
		sb.append("   SUBTOTAL:").append("=\t").append(String.format("%15s",Constants.dfCurrency.format(subTotal   ))).append("\n");

		sb.append("S.T. 1RA NG:").append("+\t").append(String.format("%15s",Constants.dfCurrency.format(subTotal1raNG))).append("\n");
		sb.append("S.T. OPO NG:").append("+\t").append(String.format("%15s",Constants.dfCurrency.format(subTotalOpoNG))).append("\n");
		sb.append("S.T. REG NG:").append("+\t").append(String.format("%15s",Constants.dfCurrency.format(subTotalRegNG))).append("\n");
		sb.append("                 -------------------\n");
		sb.append("SUBTOTAL NG:").append("=\t").append(String.format("%15s",Constants.dfCurrency.format(subTotalNG   ))).append("\n");
		
		sb.append(" DESC.     :").append("-\t").append(String.format("%15s",Constants.dfCurrency.format(descuento    )));
		sb.append(" = (").append(porcentajeCalculado).append("% + ").append(porcentajeExtra).append("% )\n");
		if(descuentoRedondeo>0.0){
			sb.append("DESC REDOND:").append("-\t").append(String.format("%15s",Constants.dfCurrency.format(descuentoRedondeo))).append("\n");
		}
		if(vistaFactura){
			sb.append("     I.V.A.:").append("+\t").append(String.format("%15s",Constants.dfCurrency.format(iva))).append("\n");;
		}		
		sb.append("   SUBTOTAL:").append("\t").append(String.format("%15s",Constants.dfCurrency.format(total))).append("\n");
		sb.append("------------------------------------\n");
		sb.append("  T O T A L:").append("\t").append(String.format("%15s",Constants.dfCurrency.format(totalFinal))).append("\n");
		if(recibidoTarjeta > 0.0){
			sb.append("CARGO TARJ:").append("\t").append(String.format("%15s",Constants.dfCurrency.format(recibidoTarjeta ))).append("\n");			
		}
		if(recibidoEfectivo > 0.0){
			sb.append("  RECIBIDO:").append("\t").append(String.format("%15s",Constants.dfCurrency.format(recibidoEfectivo))).append("\n");			
			sb.append("    CAMBIO:").append("\t").append(String.format("%15s",Constants.dfCurrency.format(cambio          ))).append("\n");
		}
		
		
		return sb.toString(); 
	}

	@Override
	public String toString() {
		return toString(true);
	}

	
}

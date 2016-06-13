package com.pmarlen.businesslogic;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class GeneradorNumTicket{
	/**
	 * long bigLong = 999999999999999999;
	 *	              yyMMddhhmmss000000
	 *  1505211355120101
	 */
    private static final SimpleDateFormat sdf     = new SimpleDateFormat("yyMMddHHmmss");	
    private static final DecimalFormat    df2Id   = new DecimalFormat("00");
	private static final DecimalFormat    df3Id   = new DecimalFormat("000");
	private static final DecimalFormat    dfT2    = new DecimalFormat("0000000000000000");
	private static final DecimalFormat    df2Tot  = new DecimalFormat("000000.00");	
	//MAXLONG                           9223372036854775807L;
    private static final long bigLong  =  999999999999999999L;
	private static final long bigLongT =  99999999L;
	private static final long bigLongT2 = 9999999999999999L;
	private static final long bigLongT3 = 999999999999999999L;
	
	/**
	 * EJEMPLO:  091984087839388979
	 * @param d
	 * @param sucId
	 * @param caja
	 * @return 
	 */
	
	public static String getNumTicket(Date d,int sucId,int caja){
		StringBuilder sb= new StringBuilder();		
		
		sb.append(df2Id.format(sucId));
		sb.append(df2Id.format(caja));
		sb.append(sdf.format(d));
		sb.append(df2Id.format(Math.random()*100.0));
		
		//System.err.println("sb:->"+sb+"<-");
		long   nt= Long.parseLong(sb.toString());
		//System.err.println("nt:->"+nt+"<-");
		String x2 = dfT2.format(bigLongT3 - nt);
		//System.err.println("x2:->"+x2+"<-");
		StringBuilder ticketSB = new StringBuilder(x2.toString()).reverse();		
		return ticketSB.toString();
    }

    public static void main(String args[]){
		System.out.println("TIKECT     V3 ="+getNumTicket(new Date(),3,3));
		System.out.println("TIKECT     V3 ="+getNumTicket(new Date(),3,3));
		System.out.println("TIKECT     V3 ="+getNumTicket(new Date(),3,3));
    }
}

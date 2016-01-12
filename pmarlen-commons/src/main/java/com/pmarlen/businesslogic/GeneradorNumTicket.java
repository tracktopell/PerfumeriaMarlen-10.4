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
	/*
    @Deprecated
	public static String getNumTicket(int sucId,int caja,int clienteId,double total){
		StringBuilder sb= new StringBuilder();		
		sb.append(sdf.format(new Date()));
		sb.append(df2Id.format(sucId));
		sb.append(df2Id.format(caja));
		sb.append(df2Id.format(Math.random()*100.0));
		
		long l1=Long.parseLong(sb.toString());
		long l2=Long.parseLong(df2Tot.format(total).replace(".", ""));
		
        //return String.valueOf(bigLong - l1) + df3Id.format(clienteId) + String.valueOf(bigLongT - l2);
		return String.valueOf(bigLong - l1) + df3Id.format(clienteId) + df2Tot.format(total).replace(".", "");
    }
	
	@Deprecated
    public static String getNumTicket(Date d,int sucId,int caja,int clienteId,double total){
		StringBuilder sb= new StringBuilder();		
		sb.append(sdf.format(d));
		sb.append(df2Id.format(sucId));
		sb.append(df2Id.format(caja));
		sb.append(df2Id.format(Math.random()*100.0));
		
		long l1=Long.parseLong(sb.toString());
		long l2=Long.parseLong(df2Tot.format(total).replace(".", ""));
		
        //return String.valueOf(bigLong - l1) + df3Id.format(clienteId) + String.valueOf(bigLongT - l2);
		return String.valueOf(bigLong - l1) + df3Id.format(clienteId) + df2Tot.format(total).replace(".", "");
    }
	*/
	
	public static String getNumTicket(Date d,int sucId,int caja){
		StringBuilder sb= new StringBuilder();		
		
		sb.append(df2Id.format(sucId));
		sb.append(df2Id.format(caja));
		sb.append(sdf.format(d));		
		long   nt= Long.parseLong(sb.toString());
		String x2 = dfT2.format(bigLongT2 - nt);
		StringBuilder ticketSB = new StringBuilder(x2.toString()).reverse();		
		return ticketSB.toString();
    }

    public static void main(String args[]){
        String numTicket = getNumTicket(new Date(),3,3);
		System.out.println("TIKECT      ="+numTicket);	
    }
}

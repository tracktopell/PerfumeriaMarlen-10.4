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
	private static final DecimalFormat    df2Tot  = new DecimalFormat("000000.00");	
	//MAXLONG                           9223372036854775807L;
    private static final long bigLong  =  999999999999999999L;
	private static final long bigLongT =  99999999L;
	
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

    public static void main(String args[]){
        long t1= System.currentTimeMillis();
		
		System.out.println("T1="+t1);
		long t2 = t1/1000;
		System.out.println("T1="+t2);
    }
}

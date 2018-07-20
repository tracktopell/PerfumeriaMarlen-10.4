package com.pmarlen.businesslogic;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneradorNumTicket{
	/**
	 * long bigLong = 999999999999999999;
	 *	              yyMMddhhmmss000000
	 *  1505211355120101
	 */
	private static final SimpleDateFormat sdf     = new SimpleDateFormat("yyMMddHHmmss");	
    private static final SimpleDateFormat sdfIn     = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");	
    private static final DecimalFormat    df2Id   = new DecimalFormat("00");
	private static final DecimalFormat    df3Id   = new DecimalFormat("000");
	private static final DecimalFormat    dfT2    = new DecimalFormat("0000000000000000");
	private static final DecimalFormat    dfT1    = new DecimalFormat("000000000000000000");
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
		
		System.err.println("sb:->"+sb+"<-");
		long   nt= Long.parseLong(sb.toString());
		System.err.println("nt:->"+nt+"<-");
		String x2 = dfT2.format(bigLongT3 - nt);
		System.err.println("x2:->"+x2+"<-");
		StringBuilder ticketSB = new StringBuilder(x2.toString()).reverse();
		System.err.println("ti:->"+ticketSB+"<-");		
		return ticketSB.toString();
    }
	
	public static String dissambleNumTicket(String nt){
		StringBuilder ticketSB = new StringBuilder(nt.toString()).reverse();		
		
		System.err.println("ds:---------------------------");		
		System.err.println("ds:->"+nt+"<-");		
		System.err.println("rt:->"+ticketSB+"<-");		
		long   ntr1 = Long.parseLong(ticketSB.toString());
		System.err.println("lo:->"+bigLongT3+" - "+ntr1+"<-");												
		long   ntr2 =  bigLongT3 - ntr1;
		System.err.println("on:->"+ntr2+"<-");				
		final String cdisnt = dfT1.format(ntr2);
		System.err.println("cd:->"+cdisnt+"<-");				
		return cdisnt;
	}
	

    public static void main(String args[]){
		try {
			final Date dx1 = sdfIn.parse("2017-07-09 21:26:24.616");
			//DATE    =2017-07-09 21:26:24.616
			//     090604189729288969
			//     000000000000000000
			//sb:->050117070921262403<-
			//nt:->50117070921262403<-
			//x2:->949882929078737596<-
			//ti:->695737870929288949<-
			//ds:---------------------------
			//ds:->695737870929288949<-
			//rt:->949882929078737596<-
			//lo:->999999999999999999 - 949882929078737596<-
			//on:->50117070921262403<-
			//cd:->50117070921262403<-
			//TIKECT[0] =695737870929288949, 50117070921262403
			System.out.println("DATE    ="+sdfIn.format(dx1));
			for(int i=0;i<10;i++){
				final String nt = getNumTicket(dx1,5,1);
				final String tn = dissambleNumTicket(nt);
				System.out.println("TIKECT["+i+"] ="+nt+", "+tn);
			}
		} catch (ParseException ex) {
			ex.printStackTrace(System.err);
		}
    }
}

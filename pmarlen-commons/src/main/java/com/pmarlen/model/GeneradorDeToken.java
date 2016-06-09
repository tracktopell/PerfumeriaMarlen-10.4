package com.pmarlen.model;

/**
 *
 * @author alfredo
 */
public class GeneradorDeToken {

	private static final long SEED = 7919L;
	
	private static final String FILLER = "000000";

	private static final int STRENGTH = 6;
	
	private static final long EXPIRATION_SECS = 60 * 15;
	
	public GeneradorDeToken() {
		
	}
	
	public final String getFrase2() {		
		long   ut = System.currentTimeMillis() / 1000;

		long   p1 = ut / 1000000L;
		long   p2 = ut % 1000000L;
		
		long   f3 = p1 + (p2 *  SEED);

		String s1 = String.valueOf(f3);		
		String s3 = null;
		
		s3 = s1.substring(s1.length()-STRENGTH, s1.length());
		
		return reverse(s3);
	}
	

	private static String reverse(String orig) {
		char[] s = orig.toCharArray();
		int n = s.length;
		int halfLength = n / 2;
		for (int i = 0; i < halfLength; i++) {
			char temp = s[i];
			s[i] = s[n - 1 - i];
			s[n - 1 - i] = temp;
		}
		return new String(s);
	}

	public final String getToken(String frase) {
		String s1 = String.valueOf(Long.parseLong(frase) * SEED);
		String s2 = null;
		s2 = s1.substring(s1.length()-STRENGTH, s1.length());
		return s2;
	}
	
	public final boolean isValid2(String frase,String token) {
		long   ut = System.currentTimeMillis() / 1000;
		
		long   m2 = 0;
		long   m1 = 0;
		
		
		m2        = ut + 1;
		m1        = ut - (EXPIRATION_SECS);
		
//		System.out.println("->t1 = "+t1);
//		System.out.println("->m1 = "+m1);
//		System.out.println("->m2 = "+m2);

		final String token1 = getToken(frase);				
		boolean isToken = token1.equals(token);
		
		if(!isToken){
			System.out.println("\tTEST1:"+token+" != "+token1);
			return false;
		}
		
		boolean someValid=false;
		String lastFrase =  null;
		for(long mI0=m1;mI0<=m2;mI0+=1){

			long   p1 = mI0 / 1000000L;
			long   p2 = mI0 % 1000000L;

			long   f3 = p1 + (p2 *  SEED);

			String s1 = String.valueOf(f3);		
			String s3 = null;

			s3 = s1.substring(s1.length()-STRENGTH, s1.length());
		
			String fi3 = reverse(s3);

			final String tI0  = getToken(fi3);
			boolean      tI0e = tI0.equals(token);
			if(tI0e){
				//System.out.println("\t->mTx = "+mTx+"\tfI0 = ("+fI0+" == "+frase+")\tTOKEN: ("+tI0+" == "+token+")\t: VALID?"+tI0e);
				someValid =  true;
				break;
			}
		}
		
		if(!someValid){
			System.out.println("\tt1=["+ut+"]->m1 = "+m2+", m2="+m2+"\tlastFrase="+lastFrase);
		}
		
		return someValid;
	}

	public final boolean _isValid(String frase,String token) {
		return getToken(frase).equals(token);
	}

	public static void main(String[] args) {
		GeneradorDeToken gt = new GeneradorDeToken();
		int ci=0;
		System.out.println("\t->GeneradorDeToken: INVALIDS?");
		for (int i=0;i< 10;i++){
			//String frase  = gt.getFrase();
			String frase3 = gt.getFrase2();
			
			final String token_f3 = gt.getToken(frase3);
			
			final boolean valid2 = gt.isValid2(frase3, token_f3);
			if(!valid2){
				ci++;				
			} 
			System.out.println("\tTEST["+i+"]\tFRASE2:"+frase3+"\tTOKEN:"+token_f3+"\tVALID:"+valid2);
			try{
				//System.out.println("=======================");
				Thread.sleep(1100L);
			}catch(Exception e){

			}

		}
		System.out.println("TERMINADO: INVALIDOS="+ci);
		
//		try{
//			System.out.println("=======================");
//			Thread.sleep(3000L);
//		}catch(Exception e){
//			
//		}
//		final String frase2 = gt.getFrase();
//		System.out.println("FRASE 2:"+frase2);
//		final String token2 = gt.getToken(frase2);
//		System.out.println("TOKEN 2:"+token2);
//		
//		System.out.println("VALID:"+gt.isValid2(frase2, token2));
//		System.out.println("=======================");
		
	}
}

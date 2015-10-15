package com.pmarlen.model;

/**
 *
 * @author alfredo
 */
public class GeneradorDeToken {

	private static final long SEED = 997L;
	
	private static final String FILLER = "000000";

	private static final int STRENGTH = 6;
	
	public GeneradorDeToken() {
		
	}

	public final String getFrase() {		
		long t1 = System.currentTimeMillis();
		long x1 = (t1 % 1000) * SEED;
		final String s1 = String.valueOf(x1);				
		return reverse(FILLER.substring(0, STRENGTH- s1.length()) + s1);
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
		final String s1 = ""+frase.hashCode() * SEED;
		//System.out.println("->s1="+s1);
		final String s2 = s1.substring(s1.length()-STRENGTH, s1.length());
		return reverse(s2);
	}
	

	public final boolean isValid(String frase,String token) {
		return getToken(frase).equals(token);
	}

	public static void main(String[] args) {
		GeneradorDeToken gt = new GeneradorDeToken();
		final String frase = gt.getFrase();
		System.out.println("FRASE:"+frase);
		final String token = gt.getToken(frase);
		System.out.println("TOKEN:"+token);
		
		System.out.println("VALID:"+gt.isValid(frase, token));
		
	}
}

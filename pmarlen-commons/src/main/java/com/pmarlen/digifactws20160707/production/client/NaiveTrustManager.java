/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.digifactws20160707.production.client;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author alfredo
 */
class NaiveTrustManager implements X509TrustManager {
	
	@Override
	public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];
	}
	
}

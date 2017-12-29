package com.pmarlen.digifactws2018.production.client;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author alfredo
 */
public class NaiveSSLHelper {

	public static void makeWebServiceClientTrustEveryone(Object webServicePort) {
		if (webServicePort instanceof BindingProvider) {
			BindingProvider bp = (BindingProvider) webServicePort;
			Map requestContext = bp.getRequestContext();
			requestContext.put(JAXWS_SSL_SOCKET_FACTORY,
					getTrustingSSLSocketFactory());
			requestContext.put(JAXWS_HOSTNAME_VERIFIER,
					new NaiveHostnameVerifier());
		} else {
			throw new IllegalArgumentException(
					"Web service port "
					+ webServicePort.getClass().getName()
					+ " does not implement "
					+ BindingProvider.class.getName());
		}
	}

	public static SSLSocketFactory getTrustingSSLSocketFactory() {
		return SSLSocketFactoryHolder.INSTANCE;
	}

	private static SSLSocketFactory createSSLSocketFactory() {
		TrustManager[] trustManagers = new TrustManager[]{
			new NaiveTrustManager()
		};
		SSLContext sslContext;
		try {
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(new KeyManager[0], trustManagers,
					new SecureRandom());
			return sslContext.getSocketFactory();
		} catch (GeneralSecurityException e) {
			return null;
		}
	}

	private static interface SSLSocketFactoryHolder {

		public static final SSLSocketFactory INSTANCE = createSSLSocketFactory();
	}

	private static class NaiveHostnameVerifier implements
			HostnameVerifier {

		@Override
		public boolean verify(String hostName,
				SSLSession session) {
			return true;
		}
	}


	private static final java.lang.String JAXWS_HOSTNAME_VERIFIER
			= "com.sun.xml.internal.ws.transport.https.client.hostname.verifier";
	private static final java.lang.String JAXWS_SSL_SOCKET_FACTORY
			= "com.sun.xml.internal.ws.transport.https.client.SSLSocketFactory";
}

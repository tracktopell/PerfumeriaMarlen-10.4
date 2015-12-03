/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmwebsiteacess;

import com.google.gson.Gson;
import com.pmarlen.suempresa.realmmodel.Datum;
import com.pmarlen.suempresa.realmmodel.EmailModelDTO;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author alfredo
 */
public class PMWebSiteAcess {

	/**
	 * @param args the command line arguments
	 */
	static Logger logger = Logger.getLogger(PMWebSiteAcess.class.getName());
	
	public static void avoidSSLCErtificates(){
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			}
			};

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					logger.debug("->HostnameVerifier.verify:hostname=" + hostname);
					return true;
				}
			});
	
		} catch (Exception ioe) {
			logger.error("Errror:", ioe);
		}
	}
	
	public static String login(String user,String password){
		String PHPSessionID = null;
		
		URL urlEmailList = null;
		HttpURLConnection httpConn = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			urlEmailList = new URL(URL_LOGIN);
			logger.debug("->main:urlEmailList=" + urlEmailList);
//			Authenticator.setDefault(new Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("perfumeriamarlen.com.mx", "A9cD0H6NKEuM".toCharArray());
//				}
//			});

			httpConn = (HttpURLConnection) urlEmailList.openConnection();
			logger.debug("->main:httpConn=" + httpConn);

			httpConn.setDoOutput(true);
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestMethod("POST");

			String urlParameters
					= "login_name=" + URLEncoder.encode(user, "UTF-8")
					+ "&passwd=" + URLEncoder.encode(password, "UTF-8");
			httpConn.setRequestProperty("Content-Length", ""
					+ Integer.toString(urlParameters.getBytes().length));
			
			httpConn.setUseCaches(false);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			logger.debug("--------------------->main:send output");
			//Send request
			DataOutputStream wr = new DataOutputStream(
					httpConn.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			is = httpConn.getInputStream();
			
			logger.debug("--------------------->main:httpConn.getResponseCode()=" + httpConn.getResponseCode());			
			
			Map<String, List<String>> headerFields = httpConn.getHeaderFields();
			Iterator<String> itX = headerFields.keySet().iterator();
			//Información: ->main:HEADERS[Set-Cookie]=[[no_frames=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/, locale=es-ES; expires=Sun, 13-Dec-2015 02:17:43 GMT; path=/, PHPSESSID=4590a7354c4e34c63a8daac15ca74d28; path=/; secure; httponly]]
			
			while (itX.hasNext()) {
				String header = itX.next();
				List<String> headerValues = headerFields.get(header);
				logger.debug("->main:HEADERS[" + header + "]=[" + headerValues + "]");
				if(header!=null && header.equalsIgnoreCase("Set-Cookie")){
					logger.debug("->main:===>>headerValues.size="+headerValues.size());
					for(String cookieValue:headerValues){
						if(cookieValue.contains("PHPSESSID")){
							PHPSessionID = cookieValue.substring(cookieValue.indexOf("PHPSESSID="), cookieValue.indexOf("path=")).trim();
							logger.debug("->main:===>>PHPSessionID="+PHPSessionID);
						}
					}
					
					String cookies=headerValues.get(2);
					
				}
			}

			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			for (int nl = 1; ((line = br.readLine()) != null); nl++) {
				//logger.debug("->main:Stream:Line[" + nl + "]->" + line + "<-");
			}
			is.close();
			logger.debug("-------------------------------->");
		} catch (MalformedURLException me) {
			logger.error("la URL esta mal:", me);
		} catch (Exception ioe) {
			logger.error("Errror:", ioe);
		}		
		
		return PHPSessionID;
	}

	public static String getEmailList(String phpSessionId,String url){
		URL urlEmailList = null;
		HttpURLConnection httpConn = null;
		InputStream is = null;
		BufferedReader br = null;
		String result=null;
		try {
			urlEmailList = new URL(url);
			logger.debug("->main:urlEmailList=" + urlEmailList);
//			Authenticator.setDefault(new Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("perfumeriamarlen.com.mx", "A9cD0H6NKEuM".toCharArray());
//				}
//			});

			httpConn = (HttpURLConnection) urlEmailList.openConnection();
			logger.debug("->main:httpConn=" + httpConn);

			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("Cookie", phpSessionId+"; locale=es-ES");
			httpConn.setDoInput(true);
			is = httpConn.getInputStream();
			
			logger.debug("--------------------->main:httpConn.getResponseCode()=" + httpConn.getResponseCode());			
			
			Map<String, List<String>> headerFields = httpConn.getHeaderFields();
			Iterator<String> itX = headerFields.keySet().iterator();
			//Información: ->main:HEADERS[Set-Cookie]=[[no_frames=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/, locale=es-ES; expires=Sun, 13-Dec-2015 02:17:43 GMT; path=/, PHPSESSID=4590a7354c4e34c63a8daac15ca74d28; path=/; secure; httponly]]
			String PHPSessionID=null;
			while (itX.hasNext()) {
				String header = itX.next();
				List<String> headerValues = headerFields.get(header);
				logger.debug("->main:HEADERS[" + header + "]=[" + headerValues + "]");
				if(header!=null && header.equalsIgnoreCase("Set-Cookie")){
					logger.debug("->main:===>>headerValues.size="+headerValues.size());
					for(String cookieValue:headerValues){
						if(cookieValue.contains("PHPSESSID")){
							PHPSessionID = cookieValue.substring(cookieValue.indexOf("PHPSESSID="), cookieValue.indexOf("path=")).trim();
							logger.debug("->main:===>>PHPSessionID="+PHPSessionID);
						}
					}										
				}
			}

			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			for (int nl = 1; ((line = br.readLine()) != null); nl++) {
				//logger.debug("->main:Stream:Line[" + nl + "]->" + line + "<-");				
				result = line;
			}
			
			is.close();
			logger.debug("-------------------------------->");
		} catch (MalformedURLException me) {
			logger.error("la URL esta mal:", me);
		} catch (Exception ioe) {
			logger.error("Errror:", ioe);
		}		
		
		return result;
	}
	
	public static String getEditForgeryToken(String phpSessionId,String id,String url){
		URL urlEmailList = null;
		HttpURLConnection httpConn = null;
		InputStream is = null;
		BufferedReader br = null;
		String result=null;
		try {
			urlEmailList = new URL(url);
			logger.debug("->main:urlEmailList=" + urlEmailList);
//			Authenticator.setDefault(new Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("perfumeriamarlen.com.mx", "A9cD0H6NKEuM".toCharArray());
//				}
//			});

			httpConn = (HttpURLConnection) urlEmailList.openConnection();
			logger.debug("->main:httpConn=" + httpConn);

			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("Cookie", phpSessionId+"; locale=es-ES");
			httpConn.setDoInput(true);
			is = httpConn.getInputStream();
			
			logger.debug("--------------------->main:httpConn.getResponseCode()=" + httpConn.getResponseCode());			
			
			Map<String, List<String>> headerFields = httpConn.getHeaderFields();
			Iterator<String> itX = headerFields.keySet().iterator();
			//Información: ->main:HEADERS[Set-Cookie]=[[no_frames=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/, locale=es-ES; expires=Sun, 13-Dec-2015 02:17:43 GMT; path=/, PHPSESSID=4590a7354c4e34c63a8daac15ca74d28; path=/; secure; httponly]]
			String PHPSessionID=null;
			while (itX.hasNext()) {
				String header = itX.next();
				List<String> headerValues = headerFields.get(header);
				logger.debug("->main:HEADERS[" + header + "]=[" + headerValues + "]");
				if(header!=null && header.equalsIgnoreCase("Set-Cookie")){
					logger.debug("->main:===>>headerValues.size="+headerValues.size());
					for(String cookieValue:headerValues){
						if(cookieValue.contains("PHPSESSID")){
							PHPSessionID = cookieValue.substring(cookieValue.indexOf("PHPSESSID="), cookieValue.indexOf("path=")).trim();
							logger.debug("->main:===>>PHPSessionID="+PHPSessionID);
						}
					}										
				}
			}

			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			for (int nl = 1; ((line = br.readLine()) != null); nl++) {
				//logger.debug("->main:Stream:Line[" + nl + "]->" + line + "<-");
				if(line.contains("name=\"forgery_protection_token\"")){					
					int ftBi = line.indexOf("content=")+9;
					int ftEi = line.indexOf("\"",ftBi+2);
					if(ftBi > 0 && ftEi >0) {
						result = line.substring(ftBi,ftEi);
						logger.debug("->forgery_protection_token:->" + result + "<-");
					}
				}
			}
			
			is.close();
			logger.debug("-------------------------------->");
		} catch (MalformedURLException me) {
			logger.error("la URL esta mal:", me);
		} catch (Exception ioe) {
			logger.error("Errror:", ioe);
		}		
		
		return result;
	}
	
	public static int saveEdit(String phpSessionId,String id,String name,String password,String forgeryProtectionToken,String url){
		URL urlEdit = null;
		HttpURLConnection httpConn = null;
		InputStream is = null;
		BufferedReader br = null;
		String result=null;
		int status=-1;
		try {
			urlEdit = new URL(url);
			logger.debug("->saveEdit:urlEdit=" + urlEdit);
//			Authenticator.setDefault(new Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("perfumeriamarlen.com.mx", "A9cD0H6NKEuM".toCharArray());
//				}
//			})

			httpConn = (HttpURLConnection) urlEdit.openConnection();
			logger.debug("->saveEdit:httpConn=" + httpConn);

			httpConn.setDoOutput(true);			
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpConn.setRequestProperty("Cookie", phpSessionId+"; locale=es-ES");
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Cookie", phpSessionId+"; locale=es-ES");
			
			String urlParameters
					=   "general%5BgeneralSection%5D%5Bname%5D="+name+"&" +
						"general%5BgeneralSection%5D%5Bpostbox%5D=0&" +
						"general%5BgeneralSection%5D%5Bpostbox%5D=1&" +
						"general%5BgeneralSection%5D%5BmboxQuotaValue%5D=-1&" +
						"general-generalSection-mboxQuotaValue-selector=unlimited&" +
						"general%5BgeneralSection%5D%5Bpassword%5D="+password+"&" +
						"general%5BgeneralSection%5D%5BpasswordConfirmation%5D="+password+"&" +
						"redirect%5BredirectSection%5D%5Benabled%5D=0&" +
						"aliases%5BaliasesSection%5D%5BaliasesForm%5D%5Bc210124%5D%5BaliasName%5D=&" +
						"aliases%5BaliasesSection%5D%5BaliasesForm%5D%5BdynamicSubFormTemplate%5D%5BaliasName%5D=&" +
						"autoResponder%5BautoResponderSection%5D%5Benabled%5D=0&" +
						"autoResponder%5BautoResponderSection%5D%5Battachments%5D%5BdynamicSubFormTemplate%5D%5Bfile%5D=&" +
						"hidden=&" +
						"forgery_protection_token="+forgeryProtectionToken;
/*
general%5BgeneralSection%5D%5Bname%5D=test1&general%5BgeneralSection%5D%5Bpostbox%5D=0&general%5BgeneralSection
%5D%5Bpostbox%5D=1&general%5BgeneralSection%5D%5BmboxQuotaValue%5D=-1&general-generalSection-mboxQuotaValue-selector
=unlimited&general%5BgeneralSection%5D%5Bpassword%5D=sdf%24234Tgh67&general%5BgeneralSection%5D%5BpasswordConfirmation
%5D=sdf%24234Tgh67&redirect%5BredirectSection%5D%5Benabled%5D=0&aliases%5BaliasesSection%5D%5BaliasesForm
%5D%5Bc151550%5D%5BaliasName%5D=&aliases%5BaliasesSection%5D%5BaliasesForm%5D%5BdynamicSubFormTemplate
%5D%5BaliasName%5D=&autoResponder%5BautoResponderSection%5D%5Benabled%5D=0&autoResponder%5BautoResponderSection
%5D%5Battachments%5D%5BdynamicSubFormTemplate%5D%5Bfile%5D=&hidden=&forgery_protection_token=4f7c900
23dfb3712b787cfbd93b29c2c
*/
			//urlParameters = URLEncoder.encode(urlParameters,"UTF-8");
			
			logger.debug("--------------------->params: ->"+urlParameters+"<-("+urlParameters.length()+")");
			
			httpConn.setRequestProperty("Content-Length", ""
					+ Integer.toString(urlParameters.getBytes().length));
			
			httpConn.setUseCaches(false);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			logger.debug("--------------------->saveEdit:send output");
			//Send request
			DataOutputStream wr = new DataOutputStream(
					httpConn.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();			
			is = httpConn.getInputStream();
			status = httpConn.getResponseCode();
			logger.debug("--------------------->saveEdit:httpConn.getResponseCode()=" + status);
			
			Map<String, List<String>> headerFields = httpConn.getHeaderFields();
			Iterator<String> itX = headerFields.keySet().iterator();
			//Información: ->main:HEADERS[Set-Cookie]=[[no_frames=deleted; expires=Thu, 01-Jan-1970 00:00:01 GMT; path=/, locale=es-ES; expires=Sun, 13-Dec-2015 02:17:43 GMT; path=/, PHPSESSID=4590a7354c4e34c63a8daac15ca74d28; path=/; secure; httponly]]
			String PHPSessionID=null;
			while (itX.hasNext()) {
				String header = itX.next();
				List<String> headerValues = headerFields.get(header);
				logger.trace("->saveEdit:HEADERS[" + header + "]=[" + headerValues + "]");
				if(header!=null && header.equalsIgnoreCase("Set-Cookie")){
					logger.trace("->saveEdit:===>>headerValues.size="+headerValues.size());
					for(String cookieValue:headerValues){
						if(cookieValue.contains("PHPSESSID")){
							PHPSessionID = cookieValue.substring(cookieValue.indexOf("PHPSESSID="), cookieValue.indexOf("path=")).trim();
							logger.trace("->saveEdit:===>>PHPSessionID="+PHPSessionID);
						}
					}										
				}
			}

			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			for (int nl = 1; ((line = br.readLine()) != null); nl++) {
				logger.debug("->saveEdit:Stream:Line[" + nl + "]->" + line + "<-");				
			}
			
			is.close();
			logger.debug("-------------------------------->");
		} catch (MalformedURLException me) {
			logger.error("la URL esta mal:", me);
		} catch (Exception ioe) {
			logger.error("Errror:", ioe);
		}
		
		return status;
	}

	
	public static void main(String[] args) {
		try {
			avoidSSLCErtificates();
			
			String PHPSessionID = login(USER, PASSWORD);
			
			logger.debug("->PHPSessionID:"+PHPSessionID);
			
			String json = getEmailList(PHPSessionID,URL_EMAIL_LIST);
			
			Gson gson=new Gson();
			EmailModelDTO x = gson.fromJson(json,EmailModelDTO.class);
			
			logger.debug("->x="+x);
			String id=null;
			String name="test1";
			String domain = "";
			if(x != null) {
				List<Datum> dl = x.getData();
				for(Datum di: dl){					
					if(di.getName().equals(name)){
						logger.debug("\t->[X]["+di.getId()+"] "+di.getName()+"@"+di.getDomainName()+"\tDomainName:"+di.getDomainName()+",DomainId:"+di.getDomainId());
						id=di.getId();
						domain=di.getDomainName();
					}else {
						logger.debug("\t->   ["+di.getId()+"] "+di.getName()+"@"+di.getDomainName());
					}
				}
			}
			
			String forgeryPT = getEditForgeryToken(PHPSessionID,id,URL_EMAIL_EDIT);
			
			String password = "pwd$" + Long.toHexString(System.currentTimeMillis()).hashCode();
			password = "qwerty654321";
			logger.debug("\t->["+id+"] "+name+"@"+domain+" new Password:->"+password+"<-forgeryPT ->"+forgeryPT+"<-");
			
			saveEdit(PHPSessionID, id, name, password, forgeryPT, URL_EMAIL_EDIT+id);
			
		} catch (Exception ioe) {
			logger.error("Errror:", ioe);
		}
	}
	private static final String PASSWORD = "A9cD0H6NKEuM";
	private static final String USER = "perfumeriamarlen.com.mx";
	private static final String URL_LOGIN = "https://perfumeriamarlen.com.mx:8443/login_up.php3";
	private static final String URL_EMAIL_LIST1 = "https://perfumeriamarlen.com.mx:8443/smb/email-address/list";
	private static final String URL_EMAIL_LIST = "https://perfumeriamarlen.com.mx:8443/smb/email-address/list-data/items-per-page/100000";
	private static final String URL_EMAIL_EDIT = "https://perfumeriamarlen.com.mx:8443/smb/email-address/edit/id/";

}

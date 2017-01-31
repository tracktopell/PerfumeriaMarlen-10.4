package com.pmarlen.rest.client;


import com.google.gson.*;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.rest.dto.IAmAliveDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import com.pmarlen.rest.dto.UserAgent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

@Deprecated
public class IAmAliveServiceClient {
	
	private static String hostService = "http://localhost:8070";
	
	private static String uriServiceIAmAlive =                       "/l30/rest/iamaliveservice/hello";
	
	private static Logger logger = Logger.getLogger(IAmAliveServiceClient.class.getName());
	
	private static IAmAliveDTOPackage getHello(String hostPort){
		IAmAliveDTOPackage iAmAliveDTOPackage=null;
		Gson gson=new Gson();

		try {
			long t0=System.currentTimeMillis();
			Client client = Client.create();
			System.out.println("...creating WebResource for ZIP");
			WebResource webResource = client.resource(hostPort+uriServiceIAmAlive);			
			
			String jsonInput = null;
			IAmAliveDTORequest iAmAliveDTORequest = new IAmAliveDTORequest();
			
			iAmAliveDTORequest.setCajaId(1);
			iAmAliveDTORequest.setLoggedIn("tracktopell");
			iAmAliveDTORequest.setSessionId("-");
			iAmAliveDTORequest.setSucursalId(1);
			iAmAliveDTORequest.setUserAgent(new UserAgent("", 
					"Mac", 
					"Java-7", 
					"alfredo", 
					"./"));			
			System.out.println("-->> building: IAmAliveDTORequest="+iAmAliveDTORequest+", before send.");
			
			long t1=System.currentTimeMillis();
			
			jsonInput = gson.toJson(iAmAliveDTORequest);
			
			System.out.println("...invoking with:IAmAliveDTORequest="+jsonInput);
			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonInput);
			System.out.println("...get response");
			long t2=System.currentTimeMillis();
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed HTTP error code :"
						+ response.getStatus());
			}
			System.out.println("OK, not error, trying get JSON response");
			byte byteArr[]=new byte[0];
			byte[] output = response.getEntity(byteArr.getClass());
			long t3=System.currentTimeMillis();
			System.out.println("Output from Server:output.length="+output.length);
			String content = new String(output);			
			String jsonContent=new String(content);					
			System.out.println("jsonContent:"+jsonContent);
			if(jsonContent != null) {
				iAmAliveDTOPackage = gson.fromJson(jsonContent, IAmAliveDTOPackage.class);			
				long t4=System.currentTimeMillis();

				long t5=System.currentTimeMillis();
				System.out.println("  T = "+(t5-t0));
				System.out.println("+T1 = "+(t1-t0));
				System.out.println("+T2 = "+(t2-t1));
				System.out.println("+T3 = "+(t3-t2));					
				System.out.println("+T4 = "+(t4-t3));
				System.out.println("+T5 = "+(t5-t4));
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			return iAmAliveDTOPackage;
		}
	}
	
	public static void _main(String[] args) {
		IAmAliveDTOPackage paqueteSinc = IAmAliveServiceClient.getHello(args.length==0?"http://localhost:8070":args[0]);
		System.out.println("-->> IAmAliveDTOPackage: StatusCode="+paqueteSinc.getStatusCode());
	}
/*
...creating WebResource for ZIP
...get response
OK, not error, trying get JSON response
Output from Server:output.length=91491
>> Reading from:data.json, -1 bytes
>> OK read.
>> content.length=828782
>> jsonContent.size=827677
>> After read zip
  T = 1218
+T1 = 601
+T2 = 304
+T3 = 2
+T4 = 311
+T5 = 0
-->> paqueteSinc{inventarioSucursalQVList.length=2496,usuarioList.length=10,clienteList=296,metodoDePagoList.length=6,formaDePagoList=3,sucursal=PERFUMERIA MARLEN S.A. DE C.V. CENTRO DE DISTRIBUCIÓN}
*/
}

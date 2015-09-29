package com.pmarlen.rest.client;


import com.google.gson.*;
import com.pmarlen.rest.dto.IAmAliveDTORequest;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.pmarlen.rest.dto.SyncDTORequest;
import com.pmarlen.rest.dto.UserAgent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

public class ZipSyncDTOPackageClient {
	
	private static String hostService = "http://localhost:8070";
	
	private static String uriServiceZipSync =                       "/l30/rest/syncservice/sync";
	
	private static Logger logger = Logger.getLogger(ZipSyncDTOPackageClient.class.getName());
	
	private static SyncDTOPackage getAllOfSucursal(String hostPort){
		SyncDTOPackage paqueteSinc=null;
		Gson gson=new Gson();

		try {
			long t0=System.currentTimeMillis();
			Client client = Client.create();
			System.out.println("...creating WebResource for ZIP");
			WebResource webResource = client.resource(hostPort+uriServiceZipSync);
			
			
			String jsonInput = null;
			SyncDTORequest syncDTORequest = new SyncDTORequest();
			IAmAliveDTORequest iAmAliveDTORequest = new IAmAliveDTORequest();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			iAmAliveDTORequest.setCajaId(1);
			iAmAliveDTORequest.setLoggedIn("tracktopell");
			iAmAliveDTORequest.setSessionId("S-"+sdf.format(new Date()));
			iAmAliveDTORequest.setSucursalId(1);
			iAmAliveDTORequest.setUserAgent(new UserAgent("", 
					"Mac", 
					"Java-7", 
					"alfredo", 
					"./"));			

			syncDTORequest.setiAmAliveDTORequest(iAmAliveDTORequest);
			
			System.out.println("-->> building: SyncDTORequest="+syncDTORequest+", before send.");
			
			long t1=System.currentTimeMillis();
			
			jsonInput = gson.toJson(syncDTORequest);
			
			System.out.println("...invoking with:syncDTORequest="+jsonInput);
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
			
			String jsonContent=null;
			ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(output));
			ZipEntry ze = null;
			while ((ze = zis.getNextEntry()) != null) {
				
				byte content[]=null;
				byte buffer[] =new byte[1024];
				ByteArrayOutputStream baos= new ByteArrayOutputStream();
				if(ze.getName().endsWith(".json")){
					System.out.println(">> Reading from:"+ze.getName()+", "+ze.getSize()+" bytes");					
					int r=0;
					while((r=zis.read(buffer))!=-1){
						baos.write(buffer, 0, r);
						baos.flush();
					}
					baos.close();
					zis.closeEntry();
					System.out.println(">> OK read.");
					
					content = baos.toByteArray();
					System.out.println(">> content.length="+content.length);
					
					jsonContent=new String(content);					
					
					System.out.println(">> jsonContent.size="+jsonContent.length());
					
				}
			}
			zis.close();
			System.out.println(">> After read zip");
			if(jsonContent != null) {			
				paqueteSinc = gson.fromJson(jsonContent, SyncDTOPackage.class);			
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
			return paqueteSinc;
		}
	}
	
	public static void main(String[] args) {
		SyncDTOPackage paqueteSinc = ZipSyncDTOPackageClient.getAllOfSucursal(args.length==0?"http://localhost:8070":args[0]);
		System.out.println("-->> paqueteSinc{"+paqueteSinc+"}");
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

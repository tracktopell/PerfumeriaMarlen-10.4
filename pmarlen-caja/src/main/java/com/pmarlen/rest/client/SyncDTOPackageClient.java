package com.pmarlen.rest.client;


import com.google.gson.*;
import com.pmarlen.rest.dto.SyncDTOPackage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.List;

public class SyncDTOPackageClient {
	
	private static String urlService = "http://localhost:8070/l30/rest/syncservice/syncdtopackage/1";
	
	private static String uriService = "/l30/rest/syncservice/syncdtopackage/1";

	private static SyncDTOPackage getAllOfSucursal(String hostPort){
		SyncDTOPackage paqueteSinc=null;
		Gson gson=new Gson();

		try {
			long t0=System.currentTimeMillis();
			Client client = Client.create();
			System.out.println("...creating WebResource");
			WebResource webResource = client.resource(hostPort+uriService);
			long t1=System.currentTimeMillis();
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			System.out.println("...get response");
			long t2=System.currentTimeMillis();
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed HTTP error code :"
						+ response.getStatus());
			}
			System.out.println("OK, not error, trying get JSON response");

			String output = response.getEntity(String.class);
			long t3=System.currentTimeMillis();
			System.out.println("Output from Server:output.length="+output.length());
			
			paqueteSinc = gson.fromJson(output, SyncDTOPackage.class);
			long t4=System.currentTimeMillis();
			
			long t5=System.currentTimeMillis();
			System.out.println("  T = "+(t5-t0));
			System.out.println("+T1 = "+(t1-t0));
			System.out.println("+T2 = "+(t2-t1));
			System.out.println("+T3 = "+(t3-t2));
			System.out.println("+T4 = "+(t4-t3));
			System.out.println("+T5 = "+(t5-t4));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			return paqueteSinc;
		}
	}
	
	public static void main(String[] args) {
		SyncDTOPackage paqueteSinc = SyncDTOPackageClient.getAllOfSucursal(args.length==0?"http://localhost:8070":args[0]);
		System.out.println("-->> paqueteSinc{"+paqueteSinc+"}");
	}
/*
...creating WebResource
...get response
OK, not error, trying get JSON response
Output from Server:output.length=827677
  T = 1336
+T1 = 647
+T2 = 288
+T3 = 97
+T4 = 304
+T5 = 0
-->> paqueteSinc{inventarioSucursalQVList.length=2496,usuarioList.length=10,clienteList=296,metodoDePagoList.length=6,formaDePagoList=3,sucursal=PERFUMERIA MARLEN S.A. DE C.V. CENTRO DE DISTRIBUCIÓN}
	
*/
}

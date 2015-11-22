/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmarlen.ticket.systemprinter;

import com.pmarlen.backend.model.EntradaSalida;
import com.pmarlen.backend.model.EntradaSalidaDetalle;
import com.pmarlen.backend.model.Producto;
import com.pmarlen.caja.control.ApplicationLogic;
import com.pmarlen.model.OSValidator;
import com.pmarlen.ticket.NumeroCastellano;
import com.pmarlen.ticket.TicketPrinteService;
import com.pmarlen.ticket.bluetooth.SendBytesToDevice;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;

/**
 *
 * @author alfredo
 */
public class TicketSystemPrinter implements TicketPrinteService {
    private static final String SPACES = "                                ";
    public  static final String DEFAULT_BT_PRINTER = "00037A66B839";
    private static final String TICKET_TEST        = "/ticket_layout/TICKET_TEST.txt";    
    private static final String TICKET_LAYOUT_FILE = "/ticket_layout/TICKET_DEFINITION.txt";
    private static SimpleDateFormat sdf_fecha_full = new SimpleDateFormat("yyyyMMdd_hhmmss");    
    private static SimpleDateFormat sdf_fecha = new SimpleDateFormat("yyyy/MM/dd");
    private static SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:mm");
    private static final int MAX_CHARS_PER_COLUMN = 32;
	public static final String BT_PRINTER_MODE = "Bluetooth";	
    private String btAdress;
	private ApplicationLogic applicationLogic;
	private static TicketSystemPrinter instance;
	private static Logger logger = Logger.getLogger(TicketSystemPrinter.class.getSimpleName());
	
	private TicketSystemPrinter(){
	
	}
	
	/**
	 * @return the instance
	 */
	public static TicketSystemPrinter getInstance() {
		if(instance == null){
			instance = new TicketSystemPrinter();
		}
		return instance;
	}

	
	public String getBtAdress(){
		if(applicationLogic!=null ){
			btAdress = applicationLogic.getBTImpresora();
		} 
		return btAdress;
	}
	
	
	@Override
    public Object generateTicket(EntradaSalida pv,ArrayList<EntradaSalidaDetalle> pvdList,HashMap<String,String> extraInformation) throws IOException {
        String tiketPrinted = null;
        PrintStream psPrintTicket = null;
        
        InputStream is = TicketSystemPrinter.class.getResourceAsStream(TICKET_LAYOUT_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            String line = null;
            Date fecha = new Date();
            tiketPrinted = "TICKET_"+pv.getNumeroTicket()+"_"+sdf_fecha_full.format(fecha)+".TXT";
            psPrintTicket = new PrintStream(new File(tiketPrinted),"ISO-8859-1");
			
            HashMap<String, String> staticVars = new HashMap();
            staticVars.put("${FECHA}", sdf_fecha.format(fecha));
            staticVars.put("${HORA}", sdf_hora.format(fecha));
            staticVars.put("${CLIENTE}", "?");

			String nombreEmpresa = "?";

            List<String> nombreEmpresaList = justifyText(nombreEmpresa, MAX_CHARS_PER_COLUMN - 2 );

            staticVars.put("${NOMBRE_EMRPESA_0}", alignTextCenter(nombreEmpresaList.get(0),MAX_CHARS_PER_COLUMN - 2));
            if (nombreEmpresaList.size() > 1) {
                staticVars.put("?{NOMBRE_EMRPESA_1}", alignTextCenter(nombreEmpresaList.get(1),MAX_CHARS_PER_COLUMN - 2));
            }
            
            String direccion = "?";

            List<String> direccionList = justifyText(direccion, MAX_CHARS_PER_COLUMN - 2 );

            staticVars.put("${DIRECCION_0}", alignTextCenter(direccionList.get(0),MAX_CHARS_PER_COLUMN - 2));
            if (direccionList.size() > 1) {
                staticVars.put("?{DIRECCION_1}", alignTextCenter(direccionList.get(1),MAX_CHARS_PER_COLUMN - 2));
            }
            if (direccionList.size() > 2) {
                staticVars.put("?{DIRECCION_2}", alignTextCenter(direccionList.get(2),MAX_CHARS_PER_COLUMN - 2));
            }
            if (direccionList.size() > 3) {
                staticVars.put("?{DIRECCION_3}", alignTextCenter(direccionList.get(3),MAX_CHARS_PER_COLUMN - 2));
            }
			staticVars.put("${TELEFONO_0}", alignTextCenter("?",MAX_CHARS_PER_COLUMN - 2));

            boolean skipLine = false;
            boolean detailStart = false;
            boolean expandDetail = false;
            int numIter = 1;
            List<String> iterationLines = new ArrayList<String>();
            
            while ((line = br.readLine()) != null) {
                if (line.contains("#{")) {
                    if (line.contains("#{DETAIL_START}")) {
                        detailStart = true;
                        iterationLines.clear();
                        continue;
                    } else if (line.contains("#{DETAIL_END}")) {
                        detailStart = false;
                        expandDetail = true;
                    }
                } else if (!detailStart) {
                    iterationLines.clear();
                    iterationLines.add(line);
                    //System.err.print("#_>>"+iterationLines.size()+"\t");
                }

                if (detailStart) {
                    iterationLines.add(line);
                    //logger.info("#=>>"+line);
                    continue;
                }

                if (expandDetail) {
                    numIter = pvdList.size();
                } else {
                    numIter = 1;
                }
                DecimalFormat df_6 = new DecimalFormat("#####0");
                DecimalFormat dfs4_2 = new DecimalFormat("###0.00");


                double sum_importe = 0.0;
                double importe = 0.0;
                double sum_desc = 0.0;
                double desc = 0.0;

                for (int i = 0; i < numIter; i++) {

                    if (expandDetail) {
						
						//${CODIGO } ${PRODUCTO}
						//   ${CANT} *  ${PRECIO}  ${IMP}
						staticVars.put("${CANT}"    , alignTextRigth(df_6.format(pvdList.get(i).getCantidad()),3));
						String nombre = pvdList.get(i).getProductoCodigoBarras();
                        //staticVars.put("${PRODUCTO}", alignTextLeft(prod.getNombre(),25));
						if(nombre.length()>28){
							nombre = nombre.substring(28);
						}
						staticVars.put("${PRODUCTO}", nombre);
                        
						staticVars.put("${CODIGO}", alignTextRigth(pvdList.get(i).getProductoCodigoBarras(),14));                        
						
                        importe  = pvdList.get(i).getCantidad() * pvdList.get(i).getPrecioVenta();
                        sum_importe += importe;
                        sum_desc += desc;

                        staticVars.put("${PRECIO}" , alignTextRigth(dfs4_2.format(pvdList.get(i).getPrecioVenta()),7));
                        
                        staticVars.put("${IMP}", alignTextRigth(dfs4_2.format(importe),7));
                    }

                    for (String innerLine : iterationLines) {

                        skipLine = false;
                        Iterator<String> ik = staticVars.keySet().iterator();
                        while (ik.hasNext()) {
                            String k = ik.next();
                            if (innerLine.contains(k)) {
                                innerLine = innerLine.replace(k, staticVars.get(k));
                            }
                            //logger.info("\t\t===>>> replace "+k+" ->"+staticVars.get(k));
                        }
                        if (innerLine.indexOf("?{") >= 0) {
                            String optionalField = innerLine.substring(innerLine.indexOf("?{"), innerLine.indexOf("}"));
                            if (!staticVars.containsKey(optionalField)) {
                                skipLine = true;
                            }
                        }
                        if (!skipLine) {
                            //logger.info("=>>" + innerLine);
                            psPrintTicket.print(innerLine+"\r");
                        } else {
                            //logger.info("X=>>" + innerLine);
                        }

                    }
                }

                if (expandDetail) {
                    //logger.info("#=>>______________");
                    expandDetail = false;
					final double subTotal = sum_importe;
                    staticVars.put("${SBTOT}" , alignTextRigth(dfs4_2.format(subTotal),9));
                    //staticVars.put("${DESCUENTO}", alignTextRigth(df_6_2.format(sum_desc),12));
                    double total = sum_importe - sum_desc;
                    String strTotal = dfs4_2.format(total);
                    staticVars.put("${TOTAL}", alignTextRigth(strTotal,9));
					String recibimosOriginal = extraInformation.get("recibimos").toString();
					String recibimos = recibimosOriginal;
					staticVars.put("${RECIB}", alignTextRigth(recibimos,9));
					String suCambio = extraInformation.get("cambio").toString();
					if(suCambio == null || suCambio.trim().length()==0){
						suCambio = dfs4_2.format(Double.parseDouble(recibimosOriginal) - total);
					}
					staticVars.put("${CAMBI}", alignTextRigth(suCambio,9));
					
                    String enterosLetra  = NumeroCastellano.numeroACastellano(Long.parseLong(strTotal.substring(0, strTotal.indexOf(".")))).toUpperCase().trim();
                    String centavosLetra = strTotal.substring(strTotal.indexOf(".") + 1);

                    List<String> totalLetraList = justifyText("SON [" + enterosLetra + " " + centavosLetra + "/100 M.N.]", MAX_CHARS_PER_COLUMN-2);

                    staticVars.put("${TOTAL_LETRA_0}", totalLetraList.get(0));
                    if (totalLetraList.size() > 1) {
                        staticVars.put("?{TOTAL_LETRA_1}", totalLetraList.get(1));
                    }
                }
            }
        } catch (IOException ex) {
            //ex.printStackTrace(System.err);
            throw new IllegalStateException("No sepuede generar el Ticket:",ex);
        } finally {
            if(psPrintTicket!= null){
                psPrintTicket.close();
            }
        }

        return tiketPrinted;
    }

	private static String alignTextCenter(String text, int maxPerColumn) {
        try{
			int length  = text.trim().length(); 
			int lengthL = (maxPerColumn - length)/2;
			int lengthR = (maxPerColumn - length) - lengthL;
			
            if(length<maxPerColumn){                
                return SPACES.substring(0,lengthL)+
                        text +
						SPACES.substring(0,lengthR);
            }else {
                return text.trim().substring(0, maxPerColumn);
            }
        }catch(Exception ex){
            logger.info("\t==>>> alignTextRigth: ->"+text.trim()+"<-["+text.trim().length()+"],"+maxPerColumn+":"+ex.getMessage());
            return text.trim();
        }
    }

    private static String alignTextRigth(String text, int maxPerColumn) {
        try{
            if(text.trim().length()<maxPerColumn){
                
                return SPACES.substring(0,maxPerColumn-text.trim().length())+
                        text;
            }else {
                return text.trim().substring(0, maxPerColumn);
            }
        }catch(Exception ex){
            logger.info("\t==>>> alignTextRigth: ->"+text.trim()+"<-["+text.trim().length()+"],"+maxPerColumn+":"+ex.getMessage());
            return text.trim();
        }
    }
    
    private static String alignTextLeft(String text, int maxPerColumn) {
        try{
            if(text.trim().length()<maxPerColumn){                
                return text+SPACES.substring(0,maxPerColumn-text.trim().length());
            }else {
                return text.trim().substring(0, maxPerColumn);
            }
        }catch(Exception ex){
            logger.info("\t==>>> alignTextLeft: ->"+text.trim()+"<-["+text.trim().length()+"],"+maxPerColumn+":"+ex.getMessage());
            return text.trim();
        }
    }

    private static List<String> justifyText(String text, int maxPerColumn) {
        List<String> result = new ArrayList<String>();

        String[] words = text.split("\\s");
        String currentLine = "";

        for (String w : words) {
            if (w.trim().length() == 0) {
                continue;
            }
            if (currentLine.length() + 1 + w.length() <= maxPerColumn) {
                if (currentLine.length() == 0) {
                    currentLine = w;
                } else {
                    currentLine = currentLine + " " + w;
                }

            } else {
                result.add(currentLine);
                currentLine = w;
            }
        }

        if (currentLine.length() > 0) {
            result.add(currentLine);
            currentLine = "";
        }

        return result;
    }
    
	
	@Override
    public void testDefaultPrinter() throws IOException {
        InputStream is = TicketSystemPrinter.class.getResourceAsStream(TICKET_TEST);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
		Date fecha = new Date();
		
		String fileNameTest = "./TICKET_TEST_"+sdf_fecha_full.format(fecha)+".txt";
            
		FileOutputStream fos = new FileOutputStream(fileNameTest);
		PrintStream psTestPrint    = new PrintStream(fos,true,"ISO-8859-1");

        try {
            String line = null;
            
            HashMap<String, String> staticVars = new HashMap();
            //${FECHA},${HORA},${BTADDERSS}
            staticVars.put("${FECHA}", sdf_fecha.format(fecha));
            staticVars.put("${HORA}", sdf_hora.format(fecha));
			staticVars.put("${BTADDERSS}", getBtAdress());
			
			Set<String> keySet = staticVars.keySet();
			for(String key: keySet){
				logger.info("\t-->>key:"+key+"="+staticVars.get(key));				
			}
			while((line=br.readLine())!=null){
				//final String key = "${FECHA}";
				for(String key: keySet){
					if(line.contains(key)){
						line = line.replace(key, staticVars.get(key));
					}
				}
				
				psTestPrint.print(line+"\r");
			}
			br.close();
			psTestPrint.close();
			fos.close();
			
			sendToPrinter(fileNameTest);
			
        } catch (IOException ex) {
            //ex.printStackTrace(System.err);
            throw new IllegalStateException("No se puede generar la Prueba");
        } finally {
            
        }
    }

	@Override
	public void sendToPrinter(Object ticketFileName) throws IOException {
		logger.info("sendToPrinter:ticketFileName:"+ticketFileName);
		
		logger.info("sendToPrinter:OSValidator.isUnix()?:"+OSValidator.isUnix());
		logger.info("sendToPrinter:OSValidator.isMac():"+OSValidator.isMac());
		
		if(OSValidator.isUnix() || OSValidator.isMac()){
			logger.info("sendToPrinter: calling UnixSendToLP.printFile");
			UnixSendToLP.printFile((String)ticketFileName);
		}else{
			logger.info("sendToPrinter: calling SendFileToSystemPrinter.printFile");
			SendFileToSystemPrinter.printFile((String)ticketFileName);
		}
	}
	
	/**
	 * @param al the al to set
	 */
	public void setApplicationLogic(ApplicationLogic al) {
		this.applicationLogic = al;
	}
	
}
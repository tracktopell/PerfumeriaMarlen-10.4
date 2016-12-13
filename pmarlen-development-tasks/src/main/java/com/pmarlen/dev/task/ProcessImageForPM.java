package com.pmarlen.dev.task;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


/**
 * com.pmarlen.dev.task.ProcessImageForPM
 * @author alfredo
 */
public class ProcessImageForPM {
	private static final boolean FORCED = false;
	private static final float defImageQuality = 0.9f;

	private static final int wmImageWidth = 700;
	private static final int wmImageHeight = 700;

	private static final int target_defImageWidth = 760;
	private static final int target_defImageHeight = 760;
	private static final int target_medImageWidth = 320;
	private static final int target_medImageHeight = 320;
	private static final int target_minImageWidth = 128;
	private static final int target_minImageHeight = 128;
	private static final int target_icoImageWidth = 16;
	private static final int target_icoImageHeight = 16;
	
	private static final String regProdExp      = ".*\\p{Digit}{2,18}(_\\p{Digit}+)?.+";
	private static final String regExactFileExp = "\\p{Digit}{2,18}_\\p{Digit}+\\.png";
	private static String outputPMImagesDir = null;		
	private static String waterMarkImgFile = null;
	private static final String version ="10.4.1-B156";	
	public static void main(String[] args) {
		System.out.print("======================[com.pmarlen.dev.task.ProcessImageForPM]====================[V "+version+"]");
		
		String inputFile = null;		
		File dirOriginalImages = null;
		String fromFile= null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
		SimpleDateFormat sdfTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date today = new Date();
		if (args.length == 4) {
			dirOriginalImages = new File(args[0]);
			inputFile = args[1];
			outputPMImagesDir = args[2];
			waterMarkImgFile = args[3];
		} else {
			dirOriginalImages = new File("/usr/local/pmarlen_imagenes_productos_originales");
			inputFile         =          "/usr/local/pmarlen_imagenes_productos_originales/*.png";
			outputPMImagesDir =          "/usr/local/pmarlen_multimedio/imgs_productos"; 			
			waterMarkImgFile  =          "/usr/local/pmarlen_multimedio/imgs_productos/imagenes_marcaagua/PM_marcaagua_700x200.png";
		}
		List<File> listaFinal= new ArrayList<File>();
		String fileNameMD5     = "./PM_IMG_PROCESADOS_INDEX.txt"; 
		String fileNmaeMD5Hist = "./PM_IMG_PROCESADOS_INDEX_"+sdfTimeStamp.format(today)+".txt";
		
		File fileMD5Proc=new File(fileNameMD5);
		File fileMD5ProcHist=new File(fileNmaeMD5Hist);
		HashMap<String,String> filesProcWithMD5 = null;
		if(fileMD5Proc.exists() && fileMD5Proc.canRead() && fileMD5Proc.isFile()){
			filesProcWithMD5 = getFileProcsHash(fileMD5Proc);
		} else {
			System.err.println("==>>FILE: "+fileNameMD5+", Not exist!");
			filesProcWithMD5 = new LinkedHashMap<String,String>();
		}
				
		File files[]=dirOriginalImages.listFiles();
		
		HashMap<String,List<File>> organizedFilesByCB = new HashMap<String,List<File>> ();
		String mD5Checksum = null;
		List<File> archivosMalFormados=new ArrayList<File>();
		List<File> archivosBorrar     =new ArrayList<File>();
		for(File f:files){
			//System.out.print("--->>"+f.getName());
			if(!f.getName().toLowerCase().matches(regExactFileExp)){
				if(!f.getName().startsWith(".")){
					if( f.getName().toLowerCase().matches(regProdExp)){
						//System.out.print("\t :S ");
						archivosMalFormados.add(f);
					} else {
						//System.out.print("\t XP ");
						archivosBorrar.add(f);
					}
				}
				//System.out.println("");
				continue;
			}else {
				//System.out.print("\t :D ");
			}
			
//			System.out.print(f.isDirectory()?"d":"-");
//			System.out.print(f.canRead()?"r":"-");
//			System.out.print(f.canWrite()?"w":"-");
//			System.out.print(f.canExecute()?"x":"-");
//			System.out.print(" ");			
//			System.out.print(f.getName());
//			System.out.print("\t");
//			System.out.print(f.length());
//			System.out.print("\t");
//			System.out.print(sdf.format(new Date(f.lastModified())));
//			System.out.print("\t");
			
			String cb=f.getName().substring(0, f.getName().indexOf('_'));
			
			List<File> sameCBFilesList = organizedFilesByCB.get(cb);
			if(sameCBFilesList != null){
				sameCBFilesList.add(f);
			} else {
				sameCBFilesList = new ArrayList<File>();
				sameCBFilesList.add(f);
				organizedFilesByCB.put(cb, sameCBFilesList);
			}
		}
		System.out.println("==================================================");
		
		DecimalFormat df=new DecimalFormat("00");
		List<String> rename=new ArrayList<String>();
		for(String cb:organizedFilesByCB.keySet()){
			List<File> sameCBFilesList = organizedFilesByCB.get(cb);
			//System.out.println(cb+"\t("+sameCBFilesList.size()+") Files.");
			int c=1;
			for(File fx: sameCBFilesList){
				//System.out.print("\t"+fx.getName());
				String numberFile = fx.getName().substring(fx.getName().indexOf('_')+1, fx.getName().indexOf('.'));
				String correctNumberFile = df.format(c);
				
				if(!numberFile.equals(correctNumberFile)){
					
					String correctName=cb+"_"+correctNumberFile+".png";
					//System.out.println("\t("+numberFile+" !="+correctNumberFile+")\t=> "+correctName);
					rename.add("mv "+fx.getName()+" "+correctName);
					//rename.add("RENOMBRAR\t"+f.getName()+"\tA\t"+correctName+"");
				} else {
					try {
						//System.out.println("-->BUSCANDO:"+fx.getName()+" ?\t" + filesProcWithMD5.containsKey(fx.getName()));
						String md5Proc = filesProcWithMD5.get(fx.getName());
						if(md5Proc != null){
							mD5Checksum = getMD5Checksum(fx);
							if(!md5Proc.equalsIgnoreCase(mD5Checksum)){
								// AGREGAR LISTA FINAL
								// System.out.println("\t [CAMBIO]--> "+fx.getName()+"\t"+md5Proc+" != "+mD5Checksum);
								filesProcWithMD5.put(fx.getName(), mD5Checksum);
								listaFinal.add(fx);
							} else {
								// System.out.println("\t [MISMO ]--> "+fx.getName()+"\t"+md5Proc+" == "+mD5Checksum);
							}
						} else {
							// AGREGAR LISTA FINAL
							mD5Checksum = getMD5Checksum(fx);
							// System.out.println("\t [NUEVO ]--> "+ fx.getName()+"\t"+mD5Checksum);
							filesProcWithMD5.put(fx.getName(), mD5Checksum);
							listaFinal.add(fx);
						}
						//System.out.println(mD5Checksum);
					}catch(Exception e){
						System.out.println("\tERROR WHILE GET MD5:"+e.getMessage());
					}
				}
				c++;
			}
		}
		System.out.println("=====================================================================================");
		System.out.println("----------------- ERRORES DE INCONCISTENCIA ARITMETICA DE CONSECUTIVO:");
		for(String rn: rename){
			System.out.println(rn);
		}
		System.out.println("----------------- ERRORES EN FORMATO DE NOMBRE Y FORMATO DE ARCHIVO (ESTRICTAMENTE SOLO .png con minusculas y 3 letras)");
		for(File f: archivosMalFormados){
			//System.out.println("CORREGIR\t"+f.getName());
			System.out.println("open  \""+f.getAbsolutePath()+"\"");
		}
		System.out.println("----------------- ARCHIVOS QUE DEBEN SER BORRADOS");
		for(File f: archivosBorrar){
			//System.out.println("BORRAR\t"+f.getName());
			System.out.println("rm   \""+f.getAbsolutePath()+"\"");
		}
		
		
		for(File fx: listaFinal){			
			//System.out.println("\t->>CALL: processPMImage("+fx.getAbsolutePath()+", "+outputPMImagesDir+", waterMarkImgFile);");
			//processPMImage(f.getAbsolutePath(), outputPMImagesDir, waterMarkImgFile);
		}
		int totArcivos = listaFinal.size();
		int i;
		int d;
		int numDif=10;
		d = totArcivos/numDif;
		int iInicial=0;
		int iFinal  =0;
		int nombreHilo=0;
		List<ProcessPMImageThread> theadList= new ArrayList<ProcessPMImageThread>();
		for(i=0;i<totArcivos;i+=d){
			iInicial=i;
			if(i+d>=totArcivos){
				iFinal  =totArcivos-1;
			} else {
				iFinal  =i+d-1;
			}
			System.out.println("------->>> PROCESANDO["+iInicial+","+iFinal+"]");
			String nt = "HILO ["+nombreHilo+"]";
			System.out.println(nt);
			List<File> listaProcesar = listaFinal.subList(iInicial, iFinal);
			nombreHilo++;
			new ProcessPMImageThread(nt,listaProcesar).start();
		}
		try {
			for(ProcessPMImageThread t: theadList){
				t.join();
			}
			Thread.sleep(2000);
			System.out.println("");
			
			//fileMD5Proc
			if(fileMD5Proc.exists()){
				Files.copy(fileMD5Proc.toPath(), fileMD5ProcHist.toPath());
			}
			updateFileProcHash(filesProcWithMD5,fileMD5Proc);
			System.out.println("==>Saving PROCs File");
			System.out.println("========================= END PROCCESS =======================");
		}catch(Exception ie){
			ie.printStackTrace(System.err);
		}
		
	}

	private static void updateFileProcHash(HashMap<String, String> filesProcWithMD5, File fileMD5Proc) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileMD5Proc);
			PrintWriter pw =  new PrintWriter(fos);
			Set<String> keySet = filesProcWithMD5.keySet();
			for(String a: keySet){
				String md5 = filesProcWithMD5.get(a);
				pw.print(a);
				pw.print("|");
				pw.println(md5);
			}
		}catch(IOException ioe){
			ioe.printStackTrace(System.err);
		}
	}
	
	private static class ProcessPMImageThread extends Thread{
		List<File> listaProcesar;
		public ProcessPMImageThread(String name,List<File> lp) {
			super(name);
			listaProcesar = lp;
		}

		@Override
		public void run() {
			int x,t,a;
			x=0;
			t=listaProcesar.size();
			System.out.println("======================["+getName()+"]====================RUN:");
			String xs = "";
			for(File f: listaProcesar){				

				x++;				
				a=(x*100)/t;
				
				xs = "\t->"+getName()+"["+x+"/"+t+"]="+a+" %";
				
				if(f.length() <= 10 ){
					System.err.println("");
					System.err.print("\t->"+getName()+" ERROR IN SIZE="+f.length()+" FOR FILE: ("+f.getAbsolutePath()+")");
					continue;
				}
				if(!f.isFile()){
					System.err.println("");
					System.err.print("\t->"+getName()+" ERROR IS NOT FILE FOR PATH: ("+f.getAbsolutePath()+")");
					continue;
				}
				
				try {
					processPMImage(f.getAbsolutePath(), outputPMImagesDir, waterMarkImgFile);
					//Thread.sleep(100);
					//System.out.print(xs+"\r");
					System.out.println(xs);
				}catch(Throwable e){
					System.err.println("");
					System.err.println("\t\tException in ->"+getName()+" processing ("+f.getAbsolutePath()+"):"+e);
				}
			}
			System.out.println("");
			System.out.println("\t->"+getName()+" <==== TERMINADO");
		}
	}

	public static void processPMImage(String inputFile,	String outputPMImagesDir,String waterMarkImgFile) {
		String cb_n = "";
		
		String  regExp="\\p{Digit}+_\\p{Digit}+";
		Pattern pattern = Pattern.compile(regExp);

		Matcher matcher = pattern.matcher(inputFile);
		boolean found = false;
		int beginIndex=0;
		int endIndex=0;
		while (matcher.find()) {
			beginIndex = matcher.start();			
			endIndex=matcher.end();
			cb_n = inputFile.substring(beginIndex, endIndex);			
		}
		
		//System.out.println("====>PROCESSING IMAGES FOR PRODUCTO CODIGO_BARRAS:" + cb_n +", inputFile:"+inputFile);
		//---------------
		Iterator<ImageWriter> jpegWriters = ImageIO.getImageWritersBySuffix("jpeg");
		if (!jpegWriters.hasNext()) {
			throw new IllegalStateException("No JPEG writers found");
		}
		ImageWriter jpegImageWriter = (ImageWriter) jpegWriters.next();

		ImageWriteParam jpegIWParam = jpegImageWriter.getDefaultWriteParam();
		jpegIWParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegIWParam.setCompressionQuality(defImageQuality);

		//---------------
		Iterator<ImageWriter> pngWriters = ImageIO.getImageWritersBySuffix("png");
		if (!pngWriters.hasNext()) {
			throw new IllegalStateException("No PNG writers found");
		}
		ImageWriter pngImageWriter = (ImageWriter) pngWriters.next();
		ImageWriteParam pngIWParam = pngImageWriter.getDefaultWriteParam();
		//pngIWParam.setCompressionMode(ImageWriteParam.MODE_DEFAULT);
		//pngIWParam.setCompressionQuality(bestImageQuality);
		//---------------
		Iterator<ImageWriter> gifWriters = ImageIO.getImageWritersBySuffix("gif");
		if (!gifWriters.hasNext()) {
			throw new IllegalStateException("No GIF writers found");
		}
		ImageWriter gifImageWriter = (ImageWriter) gifWriters.next();
		ImageWriteParam gifIWParam = gifImageWriter.getDefaultWriteParam();
		gifIWParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		gifIWParam.setCompressionType("LZW");
		gifIWParam.setCompressionQuality(defImageQuality);

		//======================================================================
		File f = new File(inputFile);
		if (!f.exists() || !f.canRead()) {
			System.err.println("Can't read file: " + f);
			return;
		}
		BufferedImage imageTransformed = null;
		BufferedImage imageWMTransformed = null;
		BufferedImage originalImage = null;
		BufferedImage watterMarkImg = null;
		Image imageScalled = null;
		Image imageWMScalled = null;
		Graphics2D g2d = null;
		try {
			originalImage = ImageIO.read(f);
			if(originalImage == null){
				throw new IllegalStateException("File "+f.getName()+" can't be read as Image");
			}
			watterMarkImg = ImageIO.read(new File(waterMarkImgFile));
		} catch (Exception ex) {
			System.err.println("====>ERROR READ: from path (" + f+ ") size:"+f.length());
			return;
		}
		
		int w = originalImage.getWidth();
		int h = originalImage.getHeight();

		int newImageW = 0;
		int newImageH = 0;

		//System.out.println("\t====>TRANSFORMING Image: size:" + w + "x" + h + " => Base Size: " + target_defImageWidth + "x" + target_defImageHeight);
		if (h != target_defImageHeight && h > w) {
			newImageH = target_defImageHeight;
			newImageW = ((target_defImageHeight * w) / h);
			//System.err.println("\t\t resize =>> " + newImageW + "x" + newImageH);
		} else {
			newImageH = ((target_defImageHeight * h) / w);
			newImageW = target_defImageWidth;
			//System.err.println("\t\t resize =>> " + newImageW + "x" + newImageH);						
		}

		imageScalled = originalImage.getScaledInstance(newImageW, newImageH, Image.SCALE_SMOOTH);

		int coordImgX = 0;
		int coordImgY = 0;
		int coordWMImgX = 0;
		int coordWMImgY = 0;

		imageTransformed = new BufferedImage(target_defImageWidth, target_defImageHeight, BufferedImage.TYPE_INT_ARGB);
		imageWMTransformed = new BufferedImage(target_defImageWidth, target_defImageHeight, BufferedImage.TYPE_INT_ARGB);

		//----------------------------------------------------------------------
		g2d = (Graphics2D) imageTransformed.getGraphics();
		coordImgX = (target_defImageWidth - newImageW) / 2;
		coordImgY = (target_defImageHeight - newImageH) / 2;
		coordWMImgX = (target_defImageWidth - wmImageWidth) / 2;
		coordWMImgY = (target_defImageHeight - wmImageHeight) / 2;
		g2d.drawImage(imageScalled, coordImgX, coordImgY, null);
		//----------------------------------------------------------------------
		g2d = (Graphics2D) imageWMTransformed.getGraphics();
		coordImgX = (target_defImageWidth - newImageW) / 2;
		coordImgY = (target_defImageHeight - newImageH) / 2;
		coordWMImgX = (target_defImageWidth - wmImageWidth) / 2;
		coordWMImgY = (target_defImageHeight - wmImageHeight) / 2;
		g2d.drawImage(imageScalled, coordImgX, coordImgY, null);
		for (int k = 0; k < 3; k++) {
			coordWMImgY = (target_defImageHeight * k / 3) + (target_defImageHeight - wmImageHeight) / 2;
			g2d.drawImage(watterMarkImg, coordWMImgX, coordWMImgY, null);
		}	
		// /usr/local/pmarlen_multimedio/imgs_productos/
		// /usr/local/pmarlen_multimedio/imgs_productos/def/def_gif/
		// /usr/local/pmarlen_multimedio/imgs_productos/def/def_jpg/
		// /usr/local/pmarlen_multimedio/imgs_productos/def/def_png/
		// /usr/local/pmarlen_multimedio/imgs_productos/ico/ico_gif/
		// /usr/local/pmarlen_multimedio/imgs_productos/ico/ico_jpg/
		// /usr/local/pmarlen_multimedio/imgs_productos/ico/ico_png/
		// /usr/local/pmarlen_multimedio/imgs_productos/med/med_gif/
		// /usr/local/pmarlen_multimedio/imgs_productos/med/med_jpg/
		// /usr/local/pmarlen_multimedio/imgs_productos/med/med_png/
		// /usr/local/pmarlen_multimedio/imgs_productos/min/min_gif/
		// /usr/local/pmarlen_multimedio/imgs_productos/min/min_jpg/
		// /usr/local/pmarlen_multimedio/imgs_productos/min/min_png/
/*
		// FOR TEST LOCAL 
		mkdir /Users/alfredo/imgs_productos_forDropbox/def
		mkdir /Users/alfredo/imgs_productos_forDropbox/ico
		mkdir /Users/alfredo/imgs_productos_forDropbox/med
		mkdir /Users/alfredo/imgs_productos_forDropbox/min	
		mkdir /Users/alfredo/imgs_productos_forDropbox/def/def_gif/
		mkdir /Users/alfredo/imgs_productos_forDropbox/def/def_jpg/
		mkdir /Users/alfredo/imgs_productos_forDropbox/def/def_png/
		mkdir /Users/alfredo/imgs_productos_forDropbox/ico/ico_gif/
		mkdir /Users/alfredo/imgs_productos_forDropbox/ico/ico_jpg/
		mkdir /Users/alfredo/imgs_productos_forDropbox/ico/ico_png/
		mkdir /Users/alfredo/imgs_productos_forDropbox/med/med_gif/
		mkdir /Users/alfredo/imgs_productos_forDropbox/med/med_jpg/
		mkdir /Users/alfredo/imgs_productos_forDropbox/med/med_png/
		mkdir /Users/alfredo/imgs_productos_forDropbox/min/min_gif/
		mkdir /Users/alfredo/imgs_productos_forDropbox/min/min_jpg/
		mkdir /Users/alfredo/imgs_productos_forDropbox/min/min_png/
*/
		//System.out.println("====>SAVING:");
		String nombreArchivoFinal =  cb_n;		
		try {
			//System.out.println("\t====>WRITING DEF");
			nombreArchivoFinal =  cb_n;
			
			saveScaledImage   (imageWMTransformed, target_defImageWidth, target_defImageHeight, outputPMImagesDir + "/def_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageWMTransformed, target_defImageWidth, target_defImageHeight, outputPMImagesDir + "/def_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageWMTransformed, target_defImageWidth, target_defImageHeight, outputPMImagesDir + "/def_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);
			nombreArchivoFinal = "NWM_" + cb_n;
			saveScaledImage   (imageWMTransformed, target_defImageWidth, target_defImageHeight, outputPMImagesDir + "/def_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageWMTransformed, target_defImageWidth, target_defImageHeight, outputPMImagesDir + "/def_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageWMTransformed, target_defImageWidth, target_defImageHeight, outputPMImagesDir + "/def_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);
			
			//System.out.println("\t====>WRITING ICO");
			nombreArchivoFinal =  cb_n;
			
			saveScaledImage   (imageWMTransformed, target_icoImageWidth, target_icoImageHeight, outputPMImagesDir + "/ico_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageWMTransformed, target_icoImageWidth, target_icoImageHeight, outputPMImagesDir + "/ico_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageWMTransformed, target_icoImageWidth, target_icoImageHeight, outputPMImagesDir + "/ico_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);
			nombreArchivoFinal = "NWM_" + cb_n;
			saveScaledImage   (imageTransformed, target_icoImageWidth, target_icoImageHeight, outputPMImagesDir + "/ico_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageTransformed, target_icoImageWidth, target_icoImageHeight, outputPMImagesDir + "/ico_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageTransformed, target_icoImageWidth, target_icoImageHeight, outputPMImagesDir + "/ico_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);
			
			//System.out.println("\t====>WRITING MED");
			nombreArchivoFinal =  cb_n;
			
			saveScaledImage   (imageWMTransformed, target_medImageWidth, target_medImageHeight, outputPMImagesDir + "/med_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageWMTransformed, target_medImageWidth, target_medImageHeight, outputPMImagesDir + "/med_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageWMTransformed, target_medImageWidth, target_medImageHeight, outputPMImagesDir + "/med_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);
			nombreArchivoFinal = "NWM_" + cb_n;
			saveScaledImage   (imageTransformed, target_medImageWidth, target_medImageHeight, outputPMImagesDir + "/med_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageTransformed, target_medImageWidth, target_medImageHeight, outputPMImagesDir + "/med_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageTransformed, target_medImageWidth, target_medImageHeight, outputPMImagesDir + "/med_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);

			//System.out.println("\t====>WRITING MIN");
			nombreArchivoFinal =  cb_n;
			
			saveScaledImage   (imageWMTransformed, target_minImageWidth, target_minImageHeight, outputPMImagesDir + "/min_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageWMTransformed, target_minImageWidth, target_minImageHeight, outputPMImagesDir + "/min_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageWMTransformed, target_minImageWidth, target_minImageHeight, outputPMImagesDir + "/min_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);
			nombreArchivoFinal = "NWM_" + cb_n;
			saveScaledImage   (imageTransformed, target_minImageWidth, target_minImageHeight, outputPMImagesDir + "/min_gif/" +  nombreArchivoFinal + ".gif", gifImageWriter, gifIWParam, false);
			saveScaledJPGImage(imageTransformed, target_minImageWidth, target_minImageHeight, outputPMImagesDir + "/min_jpg/" +  nombreArchivoFinal + ".jpg", jpegImageWriter, jpegIWParam);
			saveScaledImage   (imageTransformed, target_minImageWidth, target_minImageHeight, outputPMImagesDir + "/min_png/" +  nombreArchivoFinal + ".png", pngImageWriter, pngIWParam, false);

		} catch (Exception ex) {
			System.err.println("("+Thread.currentThread().getName()+")ERROR WRITE:"+ex.getMessage());			
			return;
		}
	}
	
	private static void saveScaledImage(BufferedImage src, int w, int h, String destFile, ImageWriter imageWriter, ImageWriteParam iwp, boolean whiteBackGround) throws IOException {
		File fileDestFile = new File(destFile);
		if(!FORCED && fileDestFile.exists()){
			return;
		}
		Image productoImgeMedRes = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2ds = (Graphics2D) outImg.getGraphics();
		if (whiteBackGround) {
			g2ds.setColor(Color.WHITE);
			g2ds.fillRect(0, 0, outImg.getWidth(), outImg.getHeight());
		}
		g2ds.drawImage(productoImgeMedRes, 0, 0, null);
		ImageOutputStream ios = ImageIO.createImageOutputStream(new FileOutputStream(fileDestFile));
		imageWriter.setOutput(ios);
		imageWriter.write(null, new IIOImage(outImg, null, null), iwp);
	}

	private static void saveScaledJPGImage(BufferedImage src, int w, int h, String destFile, ImageWriter imageWriter, ImageWriteParam iwp) throws IOException {
		File fileDestFile = new File(destFile);
		if(!FORCED && fileDestFile.exists()){
			return;
		}
		Image productoImgeMedRes = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2ds = (Graphics2D) outImg.getGraphics();
		g2ds.setColor(Color.WHITE);
		g2ds.fillRect(0, 0, outImg.getWidth(), outImg.getHeight());

		g2ds.drawImage(productoImgeMedRes, 0, 0, null);
		ImageOutputStream ios = ImageIO.createImageOutputStream(new FileOutputStream(fileDestFile));
		imageWriter.setOutput(ios);
		imageWriter.write(null, new IIOImage(outImg, null, null), iwp);
	}
	
	private static HashMap<String,String> getFileProcsHash(File f){
		HashMap<String,String> hm = new LinkedHashMap<String,String>();
		BufferedReader br = null;
		FileInputStream is = null;
		String line=null;
		try {
			is = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(is));
			int nl=1;
			while((line = br.readLine()) != null) {
				if(line.trim().length()>3){
					String[] fileHash = line.split("\\|");
					if(fileHash != null && fileHash.length == 2){
						//System.out.println("getFileProcsHash:line["+nl+"]: fileHash:"+Arrays.asList(fileHash));
						hm.put(fileHash[0], fileHash[1]);
					}
				}
				nl++;
			}
			is.close();
			br.close();
		}catch(IOException ioe){
			ioe.printStackTrace(System.err);
		}
		
		return hm;
	}
	
   public static byte[] createChecksum(String filename) throws Exception {
       InputStream fis =  new FileInputStream(filename);

       byte[] buffer = new byte[1024];
       MessageDigest complete = MessageDigest.getInstance("MD5");
       int numRead;

       do {
           numRead = fis.read(buffer);
           if (numRead > 0) {
               complete.update(buffer, 0, numRead);
           }
       } while (numRead != -1);

       fis.close();
       return complete.digest();
   }
   
   // see this How-to for a faster way to convert
   // a byte array to a HEX string
   public static String _getMD5Checksum(String filename) throws Exception {
       byte[] b = createChecksum(filename);
       StringBuilder result = new StringBuilder();

       for (int i=0; i < b.length; i++) {
           result.append(Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 ));
       }
       return result.toString();
   }
   
   public static String getMD5Checksum(File f) throws Exception {
       StringBuilder result = new StringBuilder();
	   if(f.exists() && f.isFile()){
		   result.append(f.lastModified());
	   }
       return result.toString();
   }
}

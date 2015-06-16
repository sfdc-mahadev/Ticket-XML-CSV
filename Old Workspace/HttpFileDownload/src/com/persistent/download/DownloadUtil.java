package com.persistent.download;

import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

/**
 * A utility that downloads a file from a URL.
 * @author mahadev_wagalgave
 *
 */
public class DownloadUtil {
  
	/**
	 * 
	 */
	public DownloadUtil() {
		// TODO Auto-generated constructor stub
	}
 
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			String originalAttachCSV = "";
			String newAttachCSV = "";
			String localFolderPath = "";
			
			System.out.println("Inputs received as : ");
			if(args.length == 3)
			{
				for (int i = 0; i < args.length; i++)
				{
		            System.out.println(args[i]);
				}
				
				originalAttachCSV = args[0];
				newAttachCSV = args[1];
				localFolderPath = args[2];
				
				doTrustToCertificates();
				
				ReadCSV.readCsvFile(originalAttachCSV, newAttachCSV, localFolderPath);
				
				System.out.println("**** Created New CSV File with Local Path **** ");
				
				//ReadnDownloadCSV obj = new ReadnDownloadCSV();
				
				//obj.readCsvnDownloadFile(newAttachCSV, localFolderPath);
				
				ReadUsingCBR cbr = new ReadUsingCBR();
				System.out.println("Got File for Downloading Attachments :: " + newAttachCSV);
				
				//cbr.readCSVFile(newAttachCSV, localFolderPath);
				cbr.readCSVFile(newAttachCSV, localFolderPath);
				//readWithCsvListReader
				
			    //System.out.println("XML To CSV Conversion has been completed.");
			}
			else if(args.length == 2)
			{
				doTrustToCertificates();
				newAttachCSV = args[0];
				localFolderPath = args[1];
				
				ReadUsingCBR cbr = new ReadUsingCBR();
				System.out.println(newAttachCSV);
				
				//cbr.readCSVFile(newAttachCSV, localFolderPath);
				cbr.readCSVFile(newAttachCSV, localFolderPath);
				//readWithCsvListReader
			}
			else
			{
				System.out.println("The parameters you have passed are incorrect, please check the parameters");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// trusting all certificate 
	 public static void doTrustToCertificates() throws Exception 
	 {
		 
	        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        TrustManager[] trustAllCerts = new TrustManager[]{
	                new X509TrustManager() {
	                    public X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                    }

	                   	@Override
						public void checkClientTrusted(X509Certificate[] arg0,
								String arg1) throws CertificateException {
							// TODO Auto-generated method stub
						}

						@Override
						public void checkServerTrusted(X509Certificate[] arg0,
								String arg1) throws CertificateException {
							// TODO Auto-generated method stub
						}
	                }
	        };

	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        HostnameVerifier hv = new HostnameVerifier() {
	            public boolean verify(String urlHostName, SSLSession session) {
	                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
	                    System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
	                }
	                return true;
	            }
	        };
	        HttpsURLConnection.setDefaultHostnameVerifier(hv);
	    }
	
}

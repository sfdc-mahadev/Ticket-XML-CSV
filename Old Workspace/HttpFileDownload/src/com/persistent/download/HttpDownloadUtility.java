package com.persistent.download;

import java.io.*;
import java.net.*;



public class HttpDownloadUtility {

	
	public String DownloadTargetDir;
	public String CsvFileName;
	public String DownloadLinkFieldName;
	
	
	public HttpDownloadUtility() {
		// TODO Auto-generated constructor stub
	}
	
	 
    /**
     * Method downloads file from URL to a given directory.
     * @param fileURL   -   file URL to download
     * @param destinationDirectory  - directory to download file to
     * @param fileName - File Name of the downloading file
     * @throws IOException
     */
    public static void download(String fileURL, String destinationDirectory, String fileName) throws IOException {
        
    	try
    	{
    		String downloadedFileName = fileName;
	    	// File name that is being downloaded
	    	
	        //String downloadedFileName = fileURL.substring(fileURL.lastIndexOf("/")+1);
	         
	        // Open connection to the file
	        URL url = new URL(fileURL);
	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.connect();
	        
	        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
	        {
		        InputStream is = conn.getInputStream();
		        // Stream to the destination file
		        FileOutputStream fos = new FileOutputStream(destinationDirectory + "/" + downloadedFileName);
		  
		        // Read bytes from URL to the local file
		        byte[] buffer = new byte[4096];
		        int bytesRead = 0;
		        
		        System.out.println("============================================================================================");
		        System.out.println("Downloading " + downloadedFileName);
		        //System.out.println("*-*-*-*-*-*-*-* " + is.available());
		        while ((bytesRead = is.read(buffer)) != -1) {
		            System.out.print("-");  // Progress bar :)
		            fos.write(buffer,0,bytesRead);
		        }
		        System.out.println();
		        System.out.println("Downloading done for file : " + downloadedFileName);
		        System.out.println("============================================================================================");
		        // Close destination stream
		        fos.close();
		        // Close URL stream
		        is.close();
	        }
	        else
	        {
	        	System.out.println("Downloading for file : " + downloadedFileName + " Aborted HTTP STATUS CODE " + conn.getResponseCode()) ;
	        }
	        
	        conn.disconnect();
    	}
    	catch(Exception e)
    	{
    		throw e;
    	}
    }  
}

package com.persistent.download;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;

import com.persistent.model.Attachment;

public class ReadUsingCBR {

	public ReadUsingCBR() {
		// TODO Auto-generated constructor stub
	}
	
	public void readCSVFile(String csvFileName, String dirPath) {
		ICsvBeanReader beanReader = null;
		

		try {
			
			beanReader = new CsvBeanReader(new FileReader(csvFileName),
					CsvPreference.STANDARD_PREFERENCE);
			String[] header = getAttachmentHeader();
			
			//String[] nameMapping = Arrays.copyOf(new String[]{"ticketId","commentId","contenttype","createdat","filename","id","ispublic","size","token","url","localUrl"}, header.length);

				// processors are optional, but you can populate these if you want
			//CellProcessor[] processors = new CellProcessor[header.length];
			
			CellProcessor[] processors = getAttachmentCellProcessor();
			
			Attachment attachBean = null;
			System.out.println(header.length + " --  " + processors.length);
			int counter = 0;
			while ((attachBean = beanReader.read(Attachment.class, header, processors)) != null) {
				counter++;
				if(counter == 1)
					continue;
				String []tempFileName = attachBean.localUrl.split("\\\\");
            	System.out.println(tempFileName[tempFileName.length - 1]);
            	
            	GetActualUrlnDownload(dirPath, tempFileName[tempFileName.length - 1], attachBean.url);
			}
			
			System.out.println("Total Attachments Processed ** " + counter);
		} catch (FileNotFoundException ex) {
			System.err.println("Could not find the CSV file: " + ex);
		} catch (IOException ex) {
			System.err.println("Error reading the CSV file: " + ex);
		} 
		catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception Occured, please change the run parameters since there are some problems whiling reading newly created csv, Please open the csv in Excel and expand the last column to get it working." + e);
		}
		finally {
			if (beanReader != null) {
				try {
					beanReader.close();
				} catch (IOException ex) {
					System.err.println("Error closing the reader: " + ex);
				}
			}
		}
	}
	
	public String[] getAttachmentHeader() {
		String[] header = {"ticketId","commentId","contenttype","createdat","filename","id","ispublic","size","token","url","localUrl"};
		return header;
	} 
	
	public CellProcessor[] getAttachmentCellProcessor() {
		CellProcessor[] processors = new CellProcessor[11];
		return processors;
	}
	
	private void GetActualUrlnDownload(String dir, String fileName, String url) throws MalformedURLException, IOException {
        
        //URL furl = new URL(url);
		//String query = furl.getQuery();
		//System.out.println(query);
		String originalFileName = fileName;
        
        HttpURLConnection con = (HttpURLConnection)new URL( url ).openConnection();
        HttpURLConnection.setFollowRedirects(false);
        con.setInstanceFollowRedirects(false);
        
        con.connect();
        
        if(con.getResponseCode() == HttpURLConnection.HTTP_OK || con.getResponseCode() == 301 || con.getResponseCode() == 302)
        {
        	System.out.println( "orignal url: " + con.getURL() );
	        System.out.println( "connected url: " + con.getURL() );
	        InputStream is = con.getInputStream();
	        String fileUrl = con.getHeaderField( "Location" );
	        System.out.println( "Location Detected == " + con.getHeaderField( "Location" ));
	        
	        HttpDownloadUtility.download(fileUrl, dir, originalFileName );
	        
	        is.close();
        }
        else
        {
        	System.out.println("Downloading for file : " + originalFileName + " Aborted HTTP STATUS CODE " + con.getResponseCode()) ;;
        }
        
    }
	
}

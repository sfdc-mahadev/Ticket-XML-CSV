package com.persistent.download;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.*;



import com.persistent.model.Attachment;


public class ReadnDownloadCSV {

	public ReadnDownloadCSV() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String COMMA_DELIMITER = ",";
	
	public void readCsvnDownloadFile(String fileName, String dir) {

		BufferedReader fileReader = null;
		//Delimiter used in CSV file
     
        try {
        	
        	//Create a new list of student to be filled by CSV file data 
        	//ArrayList<Attachment> attachments = new ArrayList<Attachment>();
        	
            String line = "";
            
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            
            //Read the CSV file header to skip it
            fileReader.readLine();
            int attachCount = 0;
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
            	//System.out.println(line);
                String[] tokens = line.split(COMMA_DELIMITER);
                //System.out.println(tokens.length);
                if (tokens.length > 0) {
                	//Create a new student object and fill his  data
					//Student student = new Student(Long.parseLong(tokens[STUDENT_ID_IDX]), tokens[STUDENT_FNAME_IDX], tokens[STUDENT_LNAME_IDX], tokens[STUDENT_GENDER], Integer.parseInt(tokens[STUDENT_AGE]));
					//students.add(student);
                	attachCount++;
                	//	ticketId, commentId, contenttype,createdat,filename,id,ispublic,size,token,url
                	//System.out.println(dir);
                	Attachment rec1 = new Attachment();
                	rec1.commentId = tokens[1] != null ? tokens[1] : "";
                	rec1.contenttype = tokens[2]!= null ? tokens[2] : "";
                	rec1.createdat = tokens[3]!= null ? tokens[3] : "";
                	rec1.filename = tokens[4]!= null ? tokens[4] : "";
                	rec1.ispublic = tokens[6]!= null ? tokens[6] : "";
                	rec1.id = tokens[5]!= null ? tokens[5] : "";
                	rec1.localUrl = dir.concat("\\".concat(tokens[4] != null ? tokens[4] : ""));
                	rec1.size = tokens[7]!= null ? tokens[7] : "";
                	rec1.ticketId = tokens[0]!= null ? tokens[0] : "";
                	rec1.token = tokens[8]!= null ? tokens[8] : "";
                	rec1.url = tokens[9]!= null ? tokens[9] : "";
                	
                	//attachments.add(rec);
                	
                	//System.out.println(tokens[9] + " *** " + tokens[4]);
                	//System.out.println(rec.localUrl.split("\\"));
                	String []tempFileName = rec1.localUrl.split("\\\\");
                	System.out.println(tempFileName[tempFileName.length - 1]);
                	GetActualUrlnDownload(dir, tempFileName[tempFileName.length - 1], tokens[9]);
				}
                System.out.println("Total Attachments Downloaded *** " + attachCount);
            }
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

	}

	
	private void GetActualUrlnDownload(String dir, String fileName, String url) throws MalformedURLException, IOException {
        
        //URL furl = new URL(url);
		//String query = furl.getQuery();
		//System.out.println(query);
		String originalFileName = fileName;
        
        HttpURLConnection con = (HttpURLConnection)new URL( url ).openConnection();
        HttpURLConnection.setFollowRedirects(false);
        con.setInstanceFollowRedirects(false);
        
        System.out.println( "orignal url: " + con.getURL() );
        con.connect();
        System.out.println( "connected url: " + con.getURL() );
        InputStream is = con.getInputStream();
        String fileUrl = con.getHeaderField( "Location" );
        System.out.println( "Location Detected == " + con.getHeaderField( "Location" ));
        
        HttpDownloadUtility.download(fileUrl, dir, originalFileName );
        
        is.close();
    }

}

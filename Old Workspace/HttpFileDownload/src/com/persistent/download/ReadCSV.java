package com.persistent.download;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.persistent.model.Attachment;


public class ReadCSV {

	public ReadCSV() {
		// TODO Auto-generated constructor stub
	}
	
	private static final String COMMA_DELIMITER = ",";
	
	//CSV file header
	//private static final String READ_FILE_HEADER = "ticketId,commentId,contenttype,createdat,filename,id,ispublic,size,token,url";
	//private static final String WRITE_FILE_HEADER = "ticketId,commentId,contenttype,createdat,filename,id,ispublic,size,token,url,localUrl";
	
	public static void readCsvFile(String fileName, String newFileName, String dirPath) {

		BufferedReader fileReader = null;
		//Delimiter used in CSV file
		long fileSuffix = 1;
        try {
        	
        	//Create a new list of student to be filled by CSV file data 
        	ArrayList<Attachment> attachments = new ArrayList<Attachment>();
        	
            String line = "";
            
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            
            //Read the CSV file header to skip it
            fileReader.readLine();
            
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
            	System.out.println(line);
                String[] tokens = line.split(COMMA_DELIMITER);
                
                if (tokens.length == 10) {
                	//Create a new student object and fill his  data
					//Student student = new Student(Long.parseLong(tokens[STUDENT_ID_IDX]), tokens[STUDENT_FNAME_IDX], tokens[STUDENT_LNAME_IDX], tokens[STUDENT_GENDER], Integer.parseInt(tokens[STUDENT_AGE]));
					//students.add(student);
                	
                	//	ticketId, commentId, contenttype,createdat,filename,id,ispublic,size,token,url
                	//System.out.println(dirPath);
                	
                	String[] fntokens = tokens[4].split("\\.(?=[^\\.]+$)");
                	String tempFileName = "";
                	if(fntokens.length == 2)
                	{
                		tempFileName =   fntokens[0] + fileSuffix++ + "." + fntokens[1];
                	}
                	else
                	{
                		tempFileName = tokens[4] + fileSuffix++;
                	}
                	
                	Attachment rec = new Attachment();
                	System.out.println(tokens.length);
                	rec.commentId = tokens[1] != null ? tokens[1] : "";
                	rec.contenttype = tokens[2]!= null ? tokens[2] : "";
                	rec.createdat = tokens[3]!= null ? tokens[3] : "";
                	rec.filename = tokens[4]!= null ? tokens[4] : "";
                	rec.ispublic = tokens[6]!= null ? tokens[6] : "";
                	rec.id = tokens[5]!= null ? tokens[5] : "";
                	rec.localUrl = dirPath.concat("\\".concat(tempFileName));
                	rec.size = tokens[7]!= null ? tokens[7] : "";
                	rec.ticketId = tokens[0]!= null ? tokens[0] : "";
                	rec.token = tokens[8]!= null ? tokens[8] : "";
                	rec.url = tokens[9]!= null ? tokens[9] : "";
                	
                	attachments.add(rec);
                	
				}
            }
            
            //Print the new student list
           /* for (Student student : students) {
				System.out.println(student.toString());
			}*/
           
            System.out.println("Read All Records ---- ");
            
            System.out.println("Writing to new File  All Records ---- ");
            
            WriteCSV.writeCsvFile(newFileName, attachments);
            
            
            System.out.println("Downloading Files ----- ");
            
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

}

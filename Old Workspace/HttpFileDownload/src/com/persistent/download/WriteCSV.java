package com.persistent.download;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.persistent.model.*;

public class WriteCSV {

	public WriteCSV() {
		// TODO Auto-generated constructor stub
	}

	
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		private static final String FILE_HEADER = "ticketId,commentId,authorid,contenttype,createdat,filename,id,ispublic,size,token,url,localUrl";

		public static void writeCsvFile(String fileName, ArrayList<Attachment> lstAttachments) {
			
			
			//Create a new list of student objects
			//List attachments = new ArrayList();
						
			FileWriter fileWriter = null;
					
			try {
				fileWriter = new FileWriter(fileName);

				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				//Write a new student object list to the CSV file
				for (Attachment attach : lstAttachments) {
					
					fileWriter.append(String.valueOf(attach.getTicketId()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getCommentId()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getAuthorId()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getContenttype()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getCreatedat()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getFilename()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getId()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getIspublic()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getSize()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getToken()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getUrl()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(String.valueOf(attach.getLocalUrl()));
					fileWriter.append(COMMA_DELIMITER);
					
					fileWriter.append(NEW_LINE_SEPARATOR);
					fileWriter.flush();
				}

				System.out.println("CSV file was created successfully !!!");
				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				
				try {
					//fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
				
			}
		}
}

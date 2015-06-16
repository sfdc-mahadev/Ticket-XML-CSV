package com.persistent.xmlcsv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.persistent.handler.*;

/**
 * @author mahadev_wagalgave
 * 
 * This utility will be used to convert the Ticket.xml file from zendesk to csv files
 * this will generate 3 files from one single ticket.xml file
 * this utility must have 4 parameters 
 * 1. xml file / path to xml file
 * 2. ticket.csv output file path 
 * 3. comment.csv
 * 4. attachment.csv
 */
public class XML2CSV {

	/**
	 * 
	 */
    
	public XML2CSV() {
		// TODO Auto-generated constructor stub
		//tickets = new ArrayList();
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		
		String xmlFileName = "";
		String ticketCSV = "";
		String commentsCSV = "";
		String attachmentsCSV = "";
		String createdAt = "";
		
		try
		{
			
			System.out.println("Inputs received as : ");
			if(args.length == 4 || args.length == 5 )
			{
				for (int i = 0; i < args.length; i++)
				{
		            System.out.println(args[i]);
				}
				
				xmlFileName = args[0];
				ticketCSV = args[1];
				commentsCSV = args[2];
				attachmentsCSV = args[3];
				
				if(args.length == 5)
					createdAt = args[4];//"2014-12-31T21:52:33";//args[4] != null ? args[4] : null;
				
				/*DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeNoMillis();
				String jtdate = "2014-12-31T21:52:33+05:30";
				System.out.println(parser2.parseDateTime(jtdate));
				
				LocalDateTime ldt = parser2.parseLocalDateTime(createdAt);
				DateTimeFormatter dtf = DateTimeFormat.forPattern("2014-12-31T21:52:33+05:30");*/
				
				
				/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			    Date matchDateTime = sdf.parse("2014-12-31T21:52:33+05:30");
			    try {
			    	matchDateTime = sdf.parse("2014-12-31T21:52:33+05:30");
			    	System.out.println(matchDateTime);
				} catch (ParseException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}
				    // get the current date
				Date currenthDateTime = null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				Date dt = dateFormat.parse(createdAt);
				String currentDateTimeString = dateFormat.format(dt);
				try {                   
				        currenthDateTime = sdf.parse(currentDateTimeString);
				        System.out.println(currenthDateTime.compareTo(matchDateTime));
				} catch (ParseException e) {
				    // TODO Auto-generated catch block 
				    e.printStackTrace();
				}*/
				
				Date createdOrUpdatedDate = null;
				
				if(createdAt != null && createdAt != "")
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					Date dt = dateFormat.parse(createdAt);
					String currentDateTimeString = dateFormat.format(dt);
					createdOrUpdatedDate = sdf.parse(currentDateTimeString);
				}
				
				UtilityHelper obj = new UtilityHelper(ticketCSV,commentsCSV,attachmentsCSV);	
				
				//System.out.println(obj.getFormatedDate("2014-12-31T22:52:33+05:30").compareTo(obj.getFormatedDate("2014-12-31T21:52:33+05:30")));
			    obj.StAXParser(xmlFileName, createdOrUpdatedDate);
			    System.out.println("XML To CSV Conversion has been completed.");
			}
			else
			{
				System.out.println("The parameters you have passed are incorrect, please check the parameters");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Oops, something went wrong. " + ex.getMessage());
			ex.printStackTrace();
		} 

	}
	
	

}

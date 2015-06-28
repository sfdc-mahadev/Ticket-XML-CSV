package com.persistent.xmlcsv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

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
		
		
		try {
			//EntriesCSV(args);
			//OrgAndUserCSV(args);
			PostsCSV po = new PostsCSV();
			String xPost = "C:\\Data\\Projects\\15JuneXMLDumps\\posts.xml";
			String cPost = "C:\\Data\\Projects\\15JuneXMLDumps\\posts.csv";
			
			if(args.length == 2)
			{
				 xPost = args[0];
				 cPost = args[1];
			}
			po.StAXParsePosts(xPost, cPost);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*try
		{
			
			System.out.println("Inputs received as : ");
			if(args.length == 4)
			{
				for (int i = 0; i < args.length; i++)
				{
		            System.out.println(args[i]);
				}
				
				xmlFileName = args[0];
				ticketCSV = args[1];
				commentsCSV = args[2];
				attachmentsCSV = args[3];
				
				UtilityHelper obj = new UtilityHelper(ticketCSV,commentsCSV,attachmentsCSV);				
			    obj.parseXML(xmlFileName);
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
		} */
	}
	
	public static void OrgAndUserCSV(String[] args) {
		
			
		String xmlOrg = "C:\\Users\\mahadev_wagalgave\\Documents\\Vaishali Thite\\XMLtoCSV\\organizations.xml";
		String csvOrg = "C:\\Users\\mahadev_wagalgave\\Documents\\Vaishali Thite\\XMLtoCSV\\organizations.csv";
		String xmlUser = "C:\\Users\\mahadev_wagalgave\\Documents\\Vaishali Thite\\XMLtoCSV\\users.xml";
		String csvUser = "C:\\Users\\mahadev_wagalgave\\Documents\\Vaishali Thite\\XMLtoCSV\\ExistingUsers.csv";
		String updatedDate = "";
		
		try
		{
			
			
			System.out.println("Inputs received : " + args.length);
			if(args.length == 4 || args.length == 5)
			{
				for (int i = 0; i < args.length; i++)
				{
		            System.out.println(args[i]);
				}
				
				xmlOrg = args[0];
				csvOrg = args[1];
				xmlUser = args[2];
				csvUser = args[3];
				updatedDate = args[4];
				
				Date createdOrUpdatedDate = null;
				
				if(updatedDate != null && updatedDate != "")
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					Date dt = dateFormat.parse(updatedDate);
					String currentDateTimeString = dateFormat.format(dt);
					createdOrUpdatedDate = sdf.parse(currentDateTimeString);
				}
				
				
				 OrgUserCSVUtility obj = new OrgUserCSVUtility();
				 obj.ConvertOrg2CSV(xmlOrg, csvOrg, createdOrUpdatedDate);
				 obj.ConvertUser2CSV(xmlUser, csvUser, createdOrUpdatedDate);
			    System.out.println("User and Org XML To CSV Conversion has been completed.");
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
	
	
	public static void EntriesCSV( String[] args) throws Exception 
	{
		
		try
		{
			String xmlEntry = "C:\\Data\\Projects\\15JuneXMLDumps\\entries-27.xml";
			String newXML = "C:\\Data\\Projects\\15JuneXMLDumps\\newentries.xml";
			String csvEntry = "C:\\Data\\Projects\\15JuneXMLDumps\\entries.csv";
			String csvEntryAttach = "C:\\Data\\Projects\\15JuneXMLDumps\\entriesAttach.csv";
			
			//ReadNWrite(xmlEntry, newXML);
			
			OrgEntriesCSV ent = new OrgEntriesCSV();
			//ent.ParseEntries(xmlEntry, csvEntry);
			ent.StAXParser(xmlEntry, csvEntry, csvEntryAttach);
		}
		catch(Exception e)
		{
			throw e;
		}
		
	}
	
	
	public static void ReadNWrite(String input, String output) throws XMLStreamException, IOException
	{
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader streamReader = factory.createXMLStreamReader(
			    new FileReader(input));
		
		XMLOutputFactory ofactory = XMLOutputFactory.newInstance();

		XMLStreamWriter writer = ofactory.createXMLStreamWriter(new FileOutputStream(output),"UTF-8");
		writer.writeStartDocument();
		try
		{
			while(streamReader.hasNext()){
			    streamReader.next();
			    //XMLEvent xmlEvent = xmlEventReader.nextEvent();
			    
			    if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT){
			        System.out.println(streamReader.getLocalName());
			        writer.writeStartElement(streamReader.getLocalName());
			    }
			    
			    if(streamReader.getEventType() == XMLStreamReader.CHARACTERS){
			        System.out.println(streamReader.getText());
			        writer.writeCharacters(streamReader.getText());
			    }
			    
			    if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT)
			    {
			    	System.out.println(streamReader.getLocalName());
			        writer.writeEndElement();
			        
			       
			    }
			}	
			writer.writeEndDocument();
			writer.flush();
		    writer.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
	}
	

}

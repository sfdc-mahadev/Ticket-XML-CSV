package com.persistent.ticket;

import java.io.*;

import javax.xml.stream.*;

/**
 * @author mahadev_wagalgave
 *
 */
public class AddIdsToXML {

	/**
	 * 
	 */
	public AddIdsToXML() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			String ticketXML = "";
			String newTicketXML = "";
			
		
			System.out.println("Inputs received as : ");
			if(args.length == 2)
			{
				for (int i = 0; i < args.length; i++)
				{
		            System.out.println(args[i]);
				}
				
				ticketXML = args[0];
				newTicketXML = args[1];
				
				ReadNWrite(ticketXML, newTicketXML);
			}
			else
			{
				System.out.println("The parameters you have passed are incorrect, please check the parameters");
			}
			
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String ticketUUId  = null;
	public static String commentUUId = null; 
	public static long ticketCount  = 0;
	public static long commentCount = 0; 
	
	private static boolean niceIdFlag = false; 
	    
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
			        
			        if(streamReader.getLocalName().equalsIgnoreCase("ticket"))
			        {
			        	/*writer.writeStartElement("ticketUUId");
			        	ticketCount++;
			        	ticketUUId = new String("ticket-" + ticketCount);
			        	System.out.println(" **** " +ticketUUId);
			        	writer.writeCharacters(ticketUUId);
			        	writer.writeEndElement();*/
			        }
			        else if(streamReader.getLocalName().equalsIgnoreCase("nice-id"))
			        {
			        	niceIdFlag = true;
			        }
			        else if(streamReader.getLocalName().equalsIgnoreCase("comment"))
			        {
			        	writer.writeStartElement("ticketUUId");
			        	System.out.println("--- " + ticketUUId);
			        	writer.writeCharacters(ticketUUId);
			        	writer.writeEndElement();
			        	
			        	writer.writeStartElement("commentUUId");
			        	commentCount++;
			        	commentUUId = new String("comment-" + commentCount);			        	
			        	writer.writeCharacters(commentUUId);
			        	writer.writeEndElement();
			        	
			        }
			        else if(streamReader.getLocalName().equalsIgnoreCase("attachment"))
			        {
			        	writer.writeStartElement("ticketUUId");
			        	writer.writeCharacters(ticketUUId);
			        	writer.writeEndElement();
			        	writer.writeStartElement("commentUUId");
			        	writer.writeCharacters(commentUUId);
			        	writer.writeEndElement();
			        }
			    }
			    
			    if(streamReader.getEventType() == XMLStreamReader.CHARACTERS){
			    	
			    	if(niceIdFlag == true)
			    	{
			    		ticketUUId = streamReader.getText();
			    		niceIdFlag = false;
			    	}
			    	
			    	System.out.println(streamReader.getText());
			        writer.writeCharacters(streamReader.getText());
			    }
			    
			    if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT)
			    {
			    	System.out.println(streamReader.getLocalName());
			        writer.writeEndElement();
			        
			        /*if(streamReader.getLocalName().equals("ticket"))
			        {
			        	writer.writeEndElement();
			        	ticketUUId = null;
			        	commentUUId = null;
			        	break;
			        }*/
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

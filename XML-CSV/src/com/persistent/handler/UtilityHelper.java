package com.persistent.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.persistent.models.*;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanWriter;

public class UtilityHelper {

	
	Properties prop = new Properties();
	
    CSVBeanWriterManager beanwritermgr = new CSVBeanWriterManager();
    String csvFileName;
    ICsvBeanWriter beanWriter = null;
    
    CSVBeanWriterManager beanwritermgrComment = new CSVBeanWriterManager();
    String csvFileNameComment;
    ICsvBeanWriter beanWriterComment = null;
    
    CSVBeanWriterManager beanwritermgrAttachment = new CSVBeanWriterManager();
    String csvFileNameAttachment;
    ICsvBeanWriter beanWriterAttachment = null;
    
    String[] header = null;
    CellProcessor[] processors = null;
    
    String[] headerComments = null;
    CellProcessor[] processorsComments = null;
    
    String[] headerAttachments = null;
    CellProcessor[] processorsAttachments = null;
    
    Ticket ticket = null;
    List<Ticket> tickets = new ArrayList<Ticket>();
    
    Comment comment = null;
    List<Comment> comments = new ArrayList<Comment>();
    
    Attachment attachment = null;
    List<Attachment> attachments = new ArrayList<Attachment>();
    
    
    public  UtilityHelper() {
		
	}
    
	public UtilityHelper(String ticketcsv, String commentcsv, String attachcsv) {
		// TODO Auto-generated constructor stub
		//csvFileName="C:\\Users\\mahadev_wagalgave\\Documents\\Vaishali Thite\\XMLtoCSV\\XMLtoCSV\\ResultTickets.csv";
		csvFileName = ticketcsv;
       	beanWriter = beanwritermgr.getTicketCSVWriter(csvFileName,"ticket");
       	
       	header = getTicketHeader();
       	processors=getTicketCellProcessor();
       	
       	//csvFileNameComment = "C:\\Users\\mahadev_wagalgave\\Documents\\Vaishali Thite\\XMLtoCSV\\XMLtoCSV\\ResultComments.csv";
       	csvFileNameComment = commentcsv;
       	beanWriterComment = beanwritermgr.getTicketCSVWriter(csvFileNameComment,"comment");
       	
       	headerComments = getCommentHeader();
       	processorsComments = getCommentCellProcessor();
       	
       	//csvFileNameAttachment = "C:\\Users\\mahadev_wagalgave\\Documents\\Vaishali Thite\\XMLtoCSV\\XMLtoCSV\\ResultAttachments.csv";
       	csvFileNameAttachment = attachcsv;
       	beanWriterAttachment = beanwritermgr.getTicketCSVWriter(csvFileNameAttachment,"attachment");
       	
       	headerAttachments = getAttachmentHeader();
       	processorsAttachments = getAttachmentCellProcessor();
       	
	}
	
	
	
	/*public Object parseXML(String fileName) {
        
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            
            xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES , false);
        	xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);   
        	XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                
               if (xmlEvent.isStartElement()){
                   StartElement startElement = xmlEvent.asStartElement();
                   System.out.println(startElement);
                   
                   if(startElement.getName().getLocalPart().equals("ticket")){
                        ticket = new Ticket();
                   }
                   //set the other varibles from xml elements
                   else if(startElement.getName().getLocalPart().equals("assigned-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.assignedate = xmlEvent.asCharacters().getData().toString();
                   }else if(startElement.getName().getLocalPart().equals("assignee-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.assigneeid = xmlEvent.asCharacters().getData().toString();
                   }else if(startElement.getName().getLocalPart().equals("base-score")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.basescore = xmlEvent.asCharacters().getData().toString();
                   }else if(startElement.getName().getLocalPart().equals("brand-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.brandid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("created-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.createdat = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("current-collaborators")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.currentcollaborators = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("current-tags")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.currenttags = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("description")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.description = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("due-date")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.duedate = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("entry-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.entryid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("external-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.externalid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("group-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.groupid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("initially-assigned-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.initiallyassignedat = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("latest-recipients")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.latestrecipients = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("nice-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.niceid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("organization-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.organizationid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("original-recipient-address")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.originalrecipientaddress = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("priority-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.priorityid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("recipient")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.recipient = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("requester-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.requesterid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("resolution-time")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.resolutiontime = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("solved-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.solvedat = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("status-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.statusid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("status-updated-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.statusupdatedat = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("subject")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.subject = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("submitter-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.submitterid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("ticket-form-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.ticketformid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("ticket-type-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.tickettypeid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("updated-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.updatedat = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("updated-by-type-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.updatedbytypeid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("via-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.viaid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("score")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.score = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("problem-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.problemid = xmlEvent.asCharacters().getData().toString();
                   }
                   else if(startElement.getName().getLocalPart().equals("has-incidents")){
                       xmlEvent = xmlEventReader.nextEvent();
                       ticket.hasincidents = xmlEvent.asCharacters().getData().toString();
                   }
                   
               }
               //if Employee end element is reached, add employee object to list
               if(xmlEvent.isEndElement()){
                   EndElement endElement = xmlEvent.asEndElement();
                   if(endElement.getName().getLocalPart().equals("ticket")){
                       //empList.add(emp);
                        try{
                           
                           beanWriter.write(ticket, header, processors);
                           ticket = null;
                           
                           }catch (IOException e){
                                  System.err.println("Exception while writting ticket ");
                                  e.printStackTrace();
                           }
                           
               }
            }
            }
            
            //System.out.println("end element   *-*-*-*-*-*-  : " + qName);
              
            if (beanWriter != null){
                           try {
                                  beanWriter.close();
                           } catch (IOException e) {
                                  // TODO Auto-generated catch block
                                  e.printStackTrace();
                           }
           }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

	
	private boolean ticket1 = false;
	private boolean ticket2 = false;
	private boolean ticket3 = false;
	private boolean ticket4 = false;
	private boolean ticket5 = false;
	private boolean ticket6 = false;
	private boolean ticket7 = false;
	private boolean ticket8 = false;
	private boolean ticket9 = false;
	private boolean ticket10 = false;
	private boolean ticket11 = false;
	private boolean ticket12 = false;
	private boolean ticket13 = false;
	private boolean ticket14 = false;
	private boolean ticket15 = false;
	private boolean ticket16 = false;
	private boolean ticket17 = false;
	private boolean ticket18 = false;
	private boolean ticket19 = false;
	private boolean ticket20 = false;
	private boolean ticket21 = false;
	private boolean ticket22 = false;
	private boolean ticket23 = false;
	private boolean ticket24 = false;
	private boolean ticket25 = false;
	private boolean ticket26 = false;
	private boolean ticket27 = false;
	private boolean ticket28 = false;
	private boolean ticket29 = false;
	private boolean ticket30 = false;
	private boolean ticket31 = false;
	private boolean ticket32 = false;
	private boolean ticket33 = false;
	private boolean ticket34 = false;
	private boolean ticket35 = false;
	
	private boolean comment1 = false;
	private boolean comment2 = false;
	private boolean comment3 = false;
	private boolean comment4 = false;
	private boolean comment5 = false;
	private boolean comment6 = false;
	private boolean comment7 = false;
	private boolean comment8 = false;
	//private boolean comment9 = false;
		
	private boolean attach1 = false;
	private boolean attach2 = false;
	private boolean attach3 = false;
	private boolean attach4 = false;
	private boolean attach5 = false;
	private boolean attach6 = false;
	private boolean attach7 = false;
	private boolean attach8 = false;
	private boolean attach9 = false;
	private boolean attach10 = false;
	
	public Object StAXParser(String fileName, Date createdOrUpdatedDate)
	{
		int countTickets = 0;
		int countComments = 0;
		int countAttachments = 0;
		
		int countTicketsUpdated = 0;
		int countCommentsUpdated = 0;
		int countAttachmentsUpdated = 0;
       
		String ticketUId = "";
		String commentId = "";
		
		System.out.println( "Total Tickets Found *** "  + countTickets);
        System.out.println( "Total Comments Found *** "  + countComments);
        System.out.println( "Total Attachemnts Found *** "  + countAttachments);
        
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader streamReader = factory.createXMLStreamReader(
				    new FileReader(fileName));
			
			while(streamReader.hasNext())
			{
			    streamReader.next();
			    
			    if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT)
			    {
			    	/* Ticket Attributes */
			    	if(streamReader.getLocalName().equalsIgnoreCase("ticket"))
			    	{
			    		ticket = new Ticket();countTickets++;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("ticketUUId"))
			    	{
			    		ticket1 = true;
			    		if(comment != null)
			    			comment1 = true;
			    		if(attachment != null)
			    			attach1 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("assigned-at"))
			    	{
			    		ticket2 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("assignee-id"))
			    	{
			    		ticket3 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("base-score"))
			    	{
			    		ticket4 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("brand-id"))
			    	{
			    		ticket5 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("created-at"))
			    	{
			    		ticket6 = true;
			    		if(comment != null)
			    			comment4 = true;
			    		if(attachment != null)
			    			attach4 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("current-collaborators"))
			    	{
			    		ticket7 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("current-tags"))
			    	{
			    		ticket8 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("description"))
			    	{
			    		ticket9 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("due-date"))
			    	{
			    		ticket10 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("entry-id"))
			    	{
			    		ticket11 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("external-id"))
			    	{
			    		ticket12 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("group-id"))
			    	{
			    		ticket13 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("initially-assigned-at"))
			    	{
			    		ticket14 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("latest-recipients"))
			    	{
			    		ticket15 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("nice-id"))
			    	{
			    		ticket16 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("organization-id"))
			    	{
			    		ticket17 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("original-recipient-address"))
			    	{
			    		ticket18 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("priority-id"))
			    	{
			    		ticket19 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("recipient"))
			    	{
			    		ticket20 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("requester-id"))
			    	{
			    		ticket21 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("resolution-time"))
			    	{
			    		ticket22 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("solved-at"))
			    	{
			    		ticket23 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("status-id"))
			    	{
			    		ticket24 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("status-updated-at"))
			    	{
			    		ticket25 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("subject"))
			    	{
			    		ticket26 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("submitter-id"))
			    	{
			    		ticket27 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("ticket-form-id"))
			    	{
			    		ticket28 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("ticket-type-id"))
			    	{
			    		ticket29 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("updated-at"))
			    	{
			    		ticket30 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("updated-by-type-id"))
			    	{
			    		ticket31 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("via-id"))
			    	{
			    		ticket32 = true;
			    		if(comment != null)
			    			comment8 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("score"))
			    	{
			    		ticket33 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("problem-id"))
			    	{
			    		ticket34 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("has-incidents"))
			    	{
			    		ticket35 = true;
			    	}
			    	
			    	
			    	/* Comment Attributes */
			    	
			    	if(streamReader.getLocalName().equalsIgnoreCase("comment"))
			    	{
			    		comment = new Comment();
			    		commentId = "Comment-"+ ++countComments;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("commentUUId"))
			    	{
			    		comment2 = true;
			    		if(attachment != null)
			    			attach2 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("author-id"))
			    	{
			    		comment3 = true;
			    	}
			    	/*else if(streamReader.getLocalName().equalsIgnoreCase("created-at"))
			    	{
			    		comment4 = true;
			    	}*/
			    	else if(streamReader.getLocalName().equalsIgnoreCase("is-public"))
			    	{
			    		comment5 = true;
			    		if(attachment != null)
			    			attach7 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("type"))
			    	{
			    		comment6 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("value"))
			    	{
			    		if(comment != null)
			    			comment7 = true;
			    	}
			    	/*else if(streamReader.getLocalName().equalsIgnoreCase("via-id"))
			    	{
			    		comment8 = true;
			    	}*/
			    	
			    	/*else if(streamReader.getLocalName().equalsIgnoreCase("has-incidents"))
			    	{
			    		comment9 = true;
			    	}
			    	 *
			    	 * Extra as Value is also present in Ticket tag
			    	 * else if(streamReader.getLocalName().equalsIgnoreCase("has-incidents"))
			    	{
			    		ticket35 = true;
			    	}*/
			    	
			    	/* Attachment Attributes */
			    	
			    	if(streamReader.getLocalName().equalsIgnoreCase("attachment"))
			    	{
			    		attachment = new Attachment();countAttachments++;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("content-type"))
			    	{
			    		attach3 = true;
			    	}
			    	
			    	else if(streamReader.getLocalName().equalsIgnoreCase("filename"))
			    	{
			    		attach5 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("id"))
			    	{
			    		attach6 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("size"))
			    	{
			    		attach8 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("token"))
			    	{
			    		attach9 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("url"))
			    	{
			    		attach10 = true;
			    	}
			    }
			    
		    	if(streamReader.getEventType() == XMLStreamReader.CHARACTERS)
		    	{
			    	if(ticket1 == true)
			    	{
			    		ticket.ticketId = streamReader.getText();
			    		ticket1 = false;
			    	}
			    	if(ticket2 == true)
			    	{
			    		ticket.assignedate = streamReader.getText();
			    		ticket2 = false;
			    	}
			    	if(ticket3 == true)
			    	{
			    		ticket.assigneeid = streamReader.getText();
			    		ticket3 = false;
			    	}
			    	if(ticket4 == true)
			    	{
			    		ticket.basescore = streamReader.getText();
			    		ticket4 = false;
			    	}
			    	if(ticket5 == true)
			    	{
			    		ticket.brandid = streamReader.getText();
			    		ticket5 = false;
			    	}
			    	if(ticket6 == true)
			    	{
			    		ticket.createdat = streamReader.getText();
			    		ticket6 = false;
			    	}
			    	if(ticket7 == true)
			    	{
			    		ticket.currentcollaborators = streamReader.getText();
			    		ticket7 = false;
			    	}
			    	if(ticket8 == true)
			    	{
			    		ticket.currenttags = streamReader.getText();
			    		ticket8 = false;
			    	}
			    	if(ticket9 == true)
			    	{
			    		ticket.description = streamReader.getText();
			    		ticket9 = false;
			    	}
			    	if(ticket10 == true)
			    	{
			    		ticket.duedate = streamReader.getText();
			    		ticket10 = false;
			    	}
			    	if(ticket11 == true)
			    	{
			    		ticket.entryid = streamReader.getText();
			    		ticket11 = false;
			    	}
			    	if(ticket12 == true)
			    	{
			    		ticket.externalid = streamReader.getText();
			    		ticket12 = false;
			    	}
			    	if(ticket13 == true)
			    	{
			    		ticket.groupid = streamReader.getText();
			    		ticket13 = false;
			    	}
			    	if(ticket14 == true)
			    	{
			    		ticket.initiallyassignedat = streamReader.getText();
			    		ticket14 = false;
			    	}
			    	if(ticket15 == true)
			    	{
			    		ticket.latestrecipients = streamReader.getText();
			    		ticket15 = false;
			    	}
			    	if(ticket16 == true)
			    	{
			    		ticketUId = ticket.niceid = streamReader.getText();
			    		ticket16 = false;
			    	}
			    	if(ticket17 == true)
			    	{
			    		ticket.organizationid = streamReader.getText();
			    		ticket17 = false;
			    	}
			    	if(ticket18 == true)
			    	{
			    		ticket.originalrecipientaddress = streamReader.getText();
			    		ticket18 = false;
			    	}
			    	if(ticket19 == true)
			    	{
			    		ticket.priorityid = streamReader.getText();
			    		ticket19 = false;
			    	}
			    	if(ticket20 == true)
			    	{
			    		ticket.recipient = streamReader.getText();
			    		ticket20 = false;
			    	}
			    	if(ticket21 == true)
			    	{
			    		ticket.requesterid = streamReader.getText();
			    		ticket21 = false;
			    	}
			    	if(ticket22 == true)
			    	{
			    		ticket.resolutiontime = streamReader.getText();
			    		ticket22 = false;
			    	}
			    	if(ticket23 == true)
			    	{
			    		ticket.solvedat = streamReader.getText();
			    		ticket23 = false;
			    	}
			    	if(ticket24 == true)
			    	{
			    		ticket.statusid = streamReader.getText();
			    		ticket24 = false;
			    	}
			    	if(ticket25 == true)
			    	{
			    		ticket.statusupdatedat = streamReader.getText();
			    		ticket25 = false;
			    	}
			    	if(ticket26 == true)
			    	{
			    		ticket.subject = streamReader.getText();
			    		ticket26 = false;
			    	}
			    	if(ticket27 == true)
			    	{
			    		ticket.submitterid = streamReader.getText();
			    		ticket27 = false;
			    	}
			    	if(ticket28 == true)
			    	{
			    		ticket.ticketformid = streamReader.getText();
			    		ticket28 = false;
			    	}
			    	if(ticket29 == true)
			    	{
			    		ticket.tickettypeid = streamReader.getText();
			    		ticket29 = false;
			    	}
			    	if(ticket30 == true)
			    	{
			    		ticket.updatedat = streamReader.getText();
			    		ticket30 = false;
			    	}
			    	if(ticket31 == true)
			    	{
			    		ticket.updatedbytypeid = streamReader.getText();
			    		ticket31 = false;
			    	}
			    	if(ticket32 == true)
			    	{
			    		ticket.viaid = streamReader.getText();
			    		ticket32 = false;
			    	}
			    	if(ticket33 == true)
			    	{
			    		ticket.score = streamReader.getText();
			    		ticket33 = false;
			    	}
			    	if(ticket34 == true)
			    	{
			    		ticket.problemid = streamReader.getText();
			    		ticket34 = false;
			    	}
			    	if(ticket35 == true)
			    	{
			    		ticket.hasincidents = streamReader.getText();
			    		ticket35 = false;
			    	}

			    	// Comment Assignments
			    	
			    	if(comment1 == true)
			    	{
			    		//comment.ticketId = streamReader.getText();
			    		comment.ticketId = ticketUId;
			    		comment1 = false;
			    	}
			    	if(comment2 == true)
			    	{
			    		comment.commentId = commentId;
			    		comment2 = false;
			    	}
			    	if(comment3 == true)
			    	{
			    		comment.authorid = streamReader.getText();
			    		comment3 = false;
			    	}
			    	if(comment4 == true)
			    	{
			    		comment.createdat = streamReader.getText();
			    		comment4 = false;
			    	}
			    	if(comment5 == true)
			    	{
			    		comment.ispublic = streamReader.getText();
			    		comment5 = false;
			    	}
			    	if(comment6 == true)
			    	{
			    		comment.type = streamReader.getText();
			    		comment6 = false;
			    	}
			    	if(comment7 == true)
			    	{
			    		
			    		System.out.println(comment);
			    		comment.value = streamReader.getText() != null && streamReader.getText() != "" ? streamReader.getText() : "";
			    		comment7 = false;
			    	}
			    	if(comment8 == true)
			    	{
			    		comment.viaid = streamReader.getText();
			    		comment8 = false;
			    	}
			    				    	
			    	// Attachment Assignments
			    	
			    	if(attach1 == true)
			    	{
			    		attachment.ticketId = ticketUId;
			    		attach1 = false;
			    	}
			    	if(attach2 == true)
			    	{
			    		attachment.commentId = commentId;
			    		attach2 = false;
			    	}
			    	if(attach3 == true)
			    	{
			    		attachment.contenttype = streamReader.getText();
			    		attach3 = false;
			    	}
			    	if(attach4 == true)
			    	{
			    		attachment.createdat = streamReader.getText();
			    		attach4 = false;
			    	}
			    	if(attach5 == true)
			    	{
			    		attachment.filename = streamReader.getText();
			    		attach5 = false;
			    	}
			    	if(attach6 == true)
			    	{
			    		attachment.id = streamReader.getText();
			    		attach6 = false;
			    	}
			    	if(attach7 == true)
			    	{
			    		attachment.ispublic= streamReader.getText();
			    		attach7 = false;
			    	}
			    	if(attach8 == true)
			    	{
			    		attachment.size = streamReader.getText();
			    		attach8 = false;
			    	}
			    	if(attach9 == true)
			    	{
			    		attachment.token = streamReader.getText();
			    		attach9 = false;
			    	}
			    	if(attach10 == true)
			    	{
			    		attachment.url = streamReader.getText();
			    		attach10 = false;
			    	}
			    	
			    }
			    
			    if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT)
			    {
			    	System.out.println(streamReader.getLocalName());
			    	if(streamReader.getLocalName().equalsIgnoreCase("ticket"))
			    	{
			    		if(ticket != null)
			    		{
			    			
			    			if(createdOrUpdatedDate == null)
			    			{
			    				beanWriter.write(ticket, header, processors);
			    			}
			    			else
			    			{
			    				System.out.println(getFormatedDate(ticket.createdat) + " --- Is Equal or greater ---  " + createdOrUpdatedDate + " **** " + getFormatedDate(ticket.createdat).compareTo(createdOrUpdatedDate));
			    				if( (getFormatedDate(ticket.createdat).compareTo(createdOrUpdatedDate) >= 0) || (getFormatedDate(ticket.updatedat).compareTo(createdOrUpdatedDate) >=0))
			    				{
			    					countTicketsUpdated++;
			    					beanWriter.write(ticket, header, processors);
			    				}
			    			}
                		   
			    		}
			    		ticket = null;
			    	}
			    	if(streamReader.getLocalName().equalsIgnoreCase("comment"))
			    	{
			    		 if(comment != null)
			    		 {
			    			 
			    			 if(createdOrUpdatedDate == null)
			    			 {
			    				 beanWriterComment.write(comment, headerComments, processorsComments);
			    			 }
			    			 else
			    			 {
			    				 System.out.println(getFormatedDate(comment.createdat) + " --- Is Equal or greater ---  " + createdOrUpdatedDate + " **** " + getFormatedDate(comment.createdat).compareTo(createdOrUpdatedDate));
		    				 	if( getFormatedDate(comment.createdat).compareTo(createdOrUpdatedDate) >= 0 )
			    				{
		    				 		countCommentsUpdated++;
		    				 		beanWriterComment.write(comment, headerComments, processorsComments);
			    				}
			    			 }
			    		 }
			    		 comment = null;
			    	}
			    	if(streamReader.getLocalName().equalsIgnoreCase("attachment"))
			    	{
			    		 if(attachment != null)
			    		 {
			    			 
			    			 if(createdOrUpdatedDate == null)
			    			 {
			    				 beanWriterAttachment.write(attachment, headerAttachments, processorsAttachments);
			    			 }
			    			 else
			    			 {
			    				 System.out.println(getFormatedDate(attachment.createdat) + " --- Is Equal or greater ---  " + createdOrUpdatedDate + " **** " + getFormatedDate(attachment.createdat).compareTo(createdOrUpdatedDate));
		    				 	if( getFormatedDate(attachment.createdat).compareTo(createdOrUpdatedDate) >= 0 )
			    				{
		    				 		countAttachmentsUpdated++;
		    				 		beanWriterAttachment.write(attachment, headerAttachments, processorsAttachments);
			    				}
			    			 }
			    			 
			    		 }
	    		 		 attachment = null;
			    	}
			    }
			}
			
		 	System.out.println( "Total Tickets Found *** "  + countTickets);
            System.out.println( "Total Comments Found *** "  + countComments);
            System.out.println( "Total Attachemnts Found *** "  + countAttachments);
            
			System.out.println( "Total Tickets Written *** "  + countTicketsUpdated);
            System.out.println( "Total Comments Written *** "  + countCommentsUpdated);
            System.out.println( "Total Attachemnts Written *** "  + countAttachmentsUpdated);
            /*if(tickets != null && tickets.size() > 0)
            	beanWriter.write(tickets, header, processors);
            */
            if (beanWriterAttachment != null){
       			try {
       				beanWriterAttachment.close();
       			} catch (IOException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
            }
            if (beanWriterComment != null){
       			try {
       				beanWriterComment.close();
       			} catch (IOException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
            }
            if (beanWriter != null){
       			try {
       				beanWriter.close();
       			} catch (IOException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
            }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Date getFormatedDate(String strDate) throws ParseException
	{
		Date createdOrUpdatedDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date dt = dateFormat.parse(strDate);
		String currentDateTimeString = dateFormat.format(dt);
		createdOrUpdatedDate = sdf.parse(currentDateTimeString);
		
		return createdOrUpdatedDate;
	}
	
	
	public Object parseXML(String fileName) {
		
		int countTickets = 0;
		int countComments = 0;
		int countAttachments = 0;
       
		System.out.println( "Total Tickets Found *** "  + countTickets);
        System.out.println( "Total Comments Found *** "  + countComments);
        System.out.println( "Total Attachemnts Found *** "  + countAttachments);
	      
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try 
        {
        	xmlInputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES , false);
        	xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);   
        	
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                
               if (xmlEvent.isStartElement()){
                   StartElement startElement = xmlEvent.asStartElement();
                   
                   System.out.println("---------" +startElement);
                   
                   if(startElement.getName().getLocalPart().equals("ticket")){
                	   ticket = new Ticket();
                	   countTickets++;
                   }
                   else if(startElement.getName().getLocalPart().equals("comment")){
                	   comment = new Comment();
                	   countComments++;
                   }
                   
                   else if(startElement.getName().getLocalPart().equals("attachment")){
                	   attachment = new Attachment();
                	   countAttachments++;
                   }
                   
                   // Ticket attributes ---- 
                   //set the other variables from xml elements
                   else if(startElement.getName().getLocalPart().equals("ticketUUId")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.assignedate = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.ticketId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                       
                      /* if(comment != null)
                       {
                    	   comment.ticketId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                       }
                       if(attachment != null)
                       {
                    	   attachment.ticketId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                    	    
                       }*/
                   }
                   else if(startElement.getName().getLocalPart().equals("commentUUId")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.assignedate = getCharacterData(xmlEvent, xmlEventReader);
                       comment.commentId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                      /* if(attachment != null)
                       {
                    	   attachment.commentId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                       }*/
                   }
                   else if(startElement.getName().getLocalPart().equals("assigned-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.assignedate = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.assignedate = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }else if(startElement.getName().getLocalPart().equals("assignee-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.assigneeid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.assigneeid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }else if(startElement.getName().getLocalPart().equals("base-score")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.basescore = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.basescore = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }else if(startElement.getName().getLocalPart().equals("brand-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.brandid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.brandid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("created-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.createdat = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.createdat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("current-collaborators")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.currentcollaborators = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.currentcollaborators = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("current-tags")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.currenttags = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.currenttags = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("description")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.description = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.description = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("due-date")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.duedate = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.duedate = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("entry-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.entryid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.entryid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("external-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.externalid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.externalid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("group-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.groupid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.groupid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("initially-assigned-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.initiallyassignedat = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.initiallyassignedat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("latest-recipients")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.latestrecipients = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.latestrecipients = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("nice-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.niceid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.niceid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("organization-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.organizationid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.organizationid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("original-recipient-address")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.originalrecipientaddress = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.originalrecipientaddress = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("priority-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.priorityid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.priorityid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("recipient")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.recipient = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.recipient = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("requester-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.requesterid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.requesterid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("resolution-time")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.resolutiontime = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.resolutiontime = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("solved-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.solvedat = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.solvedat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("status-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.statusid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.statusid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("status-updated-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.statusupdatedat = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.statusupdatedat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("subject")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.subject = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.subject =xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("submitter-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.submitterid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.submitterid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("ticket-form-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.ticketformid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.ticketformid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("ticket-type-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.tickettypeid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.tickettypeid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("updated-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.updatedat = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.updatedat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("updated-by-type-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.updatedbytypeid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.updatedbytypeid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("via-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.viaid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.viaid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("score")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.score = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.score = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("problem-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.problemid = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.problemid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("has-incidents")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       ticket.hasincidents = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   
                   // Comment Attributes 
                   
                   // Comented on 1 june 2015 for testing purpose 
                  
                   else if(startElement.getName().getLocalPart().equals("author-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       comment.authorid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("created-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       comment.createdat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("is-public")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       comment.ispublic = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("type")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       comment.type = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("value")){
                       xmlEvent = xmlEventReader.nextEvent();
                       if(comment != null)
                    	   comment.value = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("via-id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       comment.viaid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   
                   // Attachments Attribute --- 
                   
                   else if(startElement.getName().getLocalPart().equals("ticketUUId")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.assignedate = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.ticketId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("commentUUId")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.assignedate = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.commentId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("content-type")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.contenttype = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("created-at")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.createdat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("filename")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.filename = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("id")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.id = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("is-public")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.ispublic = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("size")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.size = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   
                   else if(startElement.getName().getLocalPart().equals("token")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.token = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                   else if(startElement.getName().getLocalPart().equals("url")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.hasincidents = getCharacterData(xmlEvent, xmlEventReader);
                       attachment.url = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                   }
                    
                    
                  // comment eND	 
                   
               }
               //if Employee end element is reached, add employee object to list
               if(xmlEvent.isEndElement()){
                   EndElement endElement = xmlEvent.asEndElement();
                   if(endElement.getName().getLocalPart().equals("ticket")){
                	   if(ticket != null)
                		   beanWriter.write(ticket, header, processors);
                	   ticket = null;
                   }
                   
                   else if(endElement.getName().getLocalPart().equals("comment")){
                	   if(comment != null)
                		   beanWriterComment.write(comment, headerComments, processorsComments);
                	   comment = null;
                   }
                   
                   else if(endElement.getName().getLocalPart().equals("attachment")){
                	   if(attachment != null)
                		   beanWriterAttachment.write(attachment, headerAttachments, processorsAttachments);
                	   attachment = null;
                   }
               }
            }
            
            System.out.println( "Total Tickets Found *** "  + countTickets);
            System.out.println( "Total Comments Found *** "  + countComments);
            System.out.println( "Total Attachemnts Found *** "  + countAttachments);
            /*if(tickets != null && tickets.size() > 0)
            	beanWriter.write(tickets, header, processors);
            */
            if (beanWriterAttachment != null){
       			try {
       				beanWriterAttachment.close();
       			} catch (IOException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
           }
            if (beanWriterComment != null){
       			try {
       				beanWriterComment.close();
       			} catch (IOException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
           }
            if (beanWriter != null){
       			try {
       				beanWriter.close();
       			} catch (IOException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       			}
           }
             
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
	
	public String[] getTicketHeader() {
		String[] header = {"ticketId","assignedate","assigneeid", "basescore","brandid","createdat","currentcollaborators","currenttags","description","duedate","entryid","externalid","groupid","initiallyassignedat","latestrecipients","niceid","organizationid","originalrecipientaddress","priorityid","recipient","requesterid","resolutiontime","solvedat","statusid","statusupdatedat","subject","submitterid","ticketformid","tickettypeid","updatedat","updatedbytypeid","viaid","score","problemid","hasincidents"};
		return header;
	} 
	
	public CellProcessor[] getTicketCellProcessor() {
		CellProcessor[] processors = new CellProcessor[35] ;
		return processors;
	}
	
	
	public String[] getCommentHeader() {
		String[] header = {"ticketId","commentId","authorId","createdat","ispublic","type","value","viaid"};
		return header;
	} 
	
	public CellProcessor[] getCommentCellProcessor() {
		CellProcessor[] processors = new CellProcessor[8] ;
		return processors;
	}
	
	public String[] getAttachmentHeader() {
		String[] header = {"ticketId","commentId","contenttype","createdat","filename","id","ispublic","size","token","url"};
		return header;
	} 
	
	public CellProcessor[] getAttachmentCellProcessor() {
		CellProcessor[] processors = new CellProcessor[10];
		return processors;
	}
}

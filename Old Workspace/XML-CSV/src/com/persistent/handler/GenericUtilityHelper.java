package com.persistent.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.persistent.models.*;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanWriter;

public class GenericUtilityHelper {

	
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
    
    
    public  GenericUtilityHelper() {
		
	}
    
	public GenericUtilityHelper(String ticketcsv, String commentcsv, String attachcsv) {
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
                       
                       if(comment != null)
                       {
                    	   comment.ticketId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                       }
                       if(attachment != null)
                       {
                    	   attachment.ticketId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                    	    
                       }
                   }
                   else if(startElement.getName().getLocalPart().equals("commentUUId")){
                       xmlEvent = xmlEventReader.nextEvent();
                       //ticket.assignedate = getCharacterData(xmlEvent, xmlEventReader);
                       comment.commentId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                       if(attachment != null)
                       {
                    	   attachment.commentId = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
                       }
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
               }
               //if Employee end element is reached, add employee object to list
               if(xmlEvent.isEndElement()){
                   EndElement endElement = xmlEvent.asEndElement();
                   if(endElement.getName().getLocalPart().equals("ticket")){
                       //empList.add(emp);
                		   //tickets.add(ticket);
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

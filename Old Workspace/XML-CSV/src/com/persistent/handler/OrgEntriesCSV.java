package com.persistent.handler;

import java.awt.image.TileObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.lang3.StringEscapeUtils;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.encoder.CsvEncoder;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import com.persistent.models.Attachment;
import com.persistent.models.Entry;

public class OrgEntriesCSV {

	public OrgEntriesCSV() {
		// TODO Auto-generated constructor stub
	}
	
	Properties prop = new Properties();

	CSVBeanWriterManager beanwritermgrEntries = new CSVBeanWriterManager();
	String csvFileNameEntries;
	ICsvBeanWriter beanWriterEntries = null;
	
	CSVBeanWriterManager beanWriterEntryAttachments = new CSVBeanWriterManager();
	String csvFileNameEntryAttach;
	ICsvBeanWriter beanWriterEntryAttach = null;
	
	String[] header1 = null;
	CellProcessor[] processors1 = null;
	

	String[] header = null;
	CellProcessor[] processors = null;

	Entry entry = null;
	
	
	public boolean	body;
	public boolean	createdat;
	public boolean	currenttags;
	public boolean	flagtypeid;
	public boolean	forumid;
	public boolean	hits;
	public boolean	id;
	public boolean	ishighlighted;
	public boolean	islocked;
	public boolean	ispinned;
	public boolean	ispublic;
	public boolean	organizationid;
	public boolean	position;
	public boolean	postscount;
	public boolean	submitterid;
	public boolean	title;
	public boolean	updatedat;
	public boolean	votescount;
	
	public void ParseEntries(String xmlFile, String csvFile) {
		
		// csvFileNameOrg = cOrg;
		
		
		beanWriterEntries = beanwritermgrEntries.getTicketCSVWriter(csvFile,
				"entry");
		
		

		header = getEntryHeader();
		processors = getEntryCellProcessor();
		
		

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			xmlInputFactory.setProperty(
					XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
			xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);

			XMLEventReader xmlEventReader = xmlInputFactory
					.createXMLEventReader(new FileInputStream(xmlFile));

			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();

					System.out.println("---------" + startElement);

					if (startElement.getName().getLocalPart()
							.equals("entry")) {
						entry = new Entry();
					} else if (startElement.getName().getLocalPart()
							.equals("created-at")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.createdat = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
					else if (startElement.getName().getLocalPart()
							.equals("body")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.body = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
								System.out.println(entry.body);
								//break;
					}
					else if (startElement.getName().getLocalPart()
							.equals("is-locked")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.islocked = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("is-pinned")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.ispinned = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("is-public")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.ispublic = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("organization-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.organizationid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("id")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.id = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("position")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.position = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("posts-count")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.postscount = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("submitter-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.submitterid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("title")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.title = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("votes-count")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.votescount = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("updated-at")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.updatedat = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("current-tags")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.currenttags = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
					else if (startElement.getName().getLocalPart()
							.equals("is-highlighted")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.ishighlighted = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
					else if (startElement.getName().getLocalPart()
							.equals("hits")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.hits = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
					else if (startElement.getName().getLocalPart()
							.equals("forum-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.forumid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
					else if (startElement.getName().getLocalPart()
							.equals("flag-type-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						entry.flagtypeid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
				}

				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					if (endElement.getName().getLocalPart()
							.equals("entry")) {
						// empList.add(emp);
						// tickets.add(ticket);
						if (entry != null)
							beanWriterEntries.write(entry, header, processors);
						entry = null;
					}

				}
			}
			if (beanWriterEntries != null) {
				try {
					beanWriterEntries.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
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
	
	 Attachment attachment = null;
	 
	 
	 
	 
	
	//***** 
	
	public Object StAXParser(String xmlFile, String EntryCSVFile, String EntryAttachCSV) throws IOException
	{
		
		
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES , false);
		factory.setProperty(XMLInputFactory.IS_COALESCING, true);   
    	
		List<Entry> lstEntries = new ArrayList<Entry>();
		
		
		try {
			
			beanWriterEntries = beanwritermgrEntries.getTicketCSVWriter(EntryCSVFile,
					"entry");

			header = getEntryHeader();
			processors = getEntryCellProcessor();
			
			beanWriterEntryAttach = beanWriterEntryAttachments.getTicketCSVWriter(EntryAttachCSV, "entry-attach");
			
			header1 = getEntryAttachmentHeader();
			processors1 = getEntryAttachmentCellProcessor();
			
			
			
			XMLStreamReader streamReader = factory.createXMLStreamReader(
				    new FileReader(xmlFile));
			
			String entryId = "";
			
			while(streamReader.hasNext())
			{
			    streamReader.next();
			    
			    if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT)
			    {
			    	/* Ticket Attributes */
			    	if(streamReader.getLocalName().equalsIgnoreCase("entry"))
			    	{
			    		entry = new Entry();
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("body"))
			    	{
			    		//entry.body = streamReader.getElementText();	
			    		//System.out.println("----"+entry.body);
			    		body = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("created-at"))
			    	{
			    		createdat = true;
			    		if(attachment != null)
			    			attach2 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("current-tags"))
			    	{
			    		currenttags = true;
			    	}
			    	
			    	else if(streamReader.getLocalName().equalsIgnoreCase("flag-type-id"))
			    	{
			    		flagtypeid = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("forum-id"))
			    	{
			    		forumid = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("hits"))
			    	{hits = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("id"))
			    	{
			    		id = true;
			    		if(attachment != null)
			    		{	
			    				attach4 = true;
			    		}
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("is-highlighted"))
			    	{ishighlighted = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("is-locked"))
			    	{islocked = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("is-pinned"))
			    	{ispinned = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("is-public"))
			    	{ispublic = true;
			    	if(attachment != null)
		    			attach5 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("organization-id"))
			    	{organizationid = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("position"))
			    	{position = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("posts-count"))
			    	{postscount = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("submitter-id"))
			    	{submitterid = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("title"))
			    	{title = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("updated-at"))
			    	{updatedat = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("votes-count"))
			    	{votescount = true;
			    	}
			    	
			    	
		    		/* Attachment Attributes */
			    	
			    	if(streamReader.getLocalName().equalsIgnoreCase("attachment"))
			    	{
			    		attachment = new Attachment();
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("content-type"))
			    	{
			    		attach1 = true;
			    	}
			    	
			    	else if(streamReader.getLocalName().equalsIgnoreCase("filename"))
			    	{
			    		attach3 = true;
			    	}
			    	
			    	else if(streamReader.getLocalName().equalsIgnoreCase("size"))
			    	{
			    		attach6 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("token"))
			    	{
			    		attach7 = true;
			    	}
			    	else if(streamReader.getLocalName().equalsIgnoreCase("url"))
			    	{
			    		attach8 = true;
			    	}
			    }
			    
		    	if(streamReader.getEventType() == XMLStreamReader.CHARACTERS)
		    	{
			    	if(body)
			    	{
			    		
			    		entry.body = streamReader.getText();
			    		body = false;
			    	}
			    	if(createdat)
			    	{
			    		entry.createdat = streamReader.getText();
			    		createdat = false;
			    	}
			    	if(currenttags)
			    	{
			    		entry.currenttags = streamReader.getText();
			    		currenttags = false;
			    	}
			    	if(flagtypeid)
			    	{
			    		entry.flagtypeid = streamReader.getText();
			    		flagtypeid = false;
			    	}
			    	if(forumid)
			    	{
			    		entry.forumid = streamReader.getText();
			    		forumid = false;
			    	}
			    	if(hits)
			    	{
			    		entry.hits = streamReader.getText();
			    		hits = false;
			    	}
			    	if(id)
			    	{
			    		if(entry.id == null)
			    			entry.id = streamReader.getText();
			    		id = false;
			    	}
			    	if(ishighlighted)
			    	{
			    		entry.ishighlighted = streamReader.getText();
			    		ishighlighted = false;
			    	}
			    	if(islocked)
			    	{
			    		entry.islocked = streamReader.getText();
			    		islocked = false;
			    	}
			    	if(ispinned)
			    	{
			    		entry.ispinned = streamReader.getText();
			    		ispinned = false;
			    	}
			    	if(ispublic)
			    	{
			    		entry.ispublic = streamReader.getText();
			    		ispublic = false;
			    	}
			    	if(organizationid)
			    	{
			    		entry.organizationid = streamReader.getText();
			    		organizationid = false;
			    	}
			    	if(position)
			    	{
			    		entry.position = streamReader.getText();
			    		position = false;
			    	}
			    	if(postscount)
			    	{
			    		entry.postscount = streamReader.getText();
			    		postscount = false;
			    	}
			    	if(submitterid)
			    	{
			    		entry.submitterid = streamReader.getText();
			    		submitterid = false;
			    	}
			    	if(title)
			    	{
			    		entry.title = streamReader.getText();
			    		title = false;
			    	}
			    	if(updatedat)
			    	{
			    		entry.updatedat = streamReader.getText();
			    		updatedat = false;
			    	}
			    	if(votescount)
			    	{
			    		entry.votescount = streamReader.getText();
			    		votescount = false;
			    	}
			    	
			    	if(attach1 == true)
			    	{
			    		attachment.contenttype = streamReader.getText();
			    		attach1 = false;
			    	}
			    	if(attach2 == true)
			    	{
			    		attachment.createdat = streamReader.getText();
			    		attach2 = false;
			    	}
			    	if(attach3 == true)
			    	{
			    		attachment.filename = streamReader.getText();
			    		attach3 = false;
			    	}
			    	if(attach4 == true)
			    	{
			    		//System.out.println(entry.id);
			    		if(attachment.entryId == null)
			    			attachment.entryId =  entry.id;
			    		attachment.id = streamReader.getText();
			    		attach4 = false;
			    	}
			    	if(attach5 == true)
			    	{
			    		attachment.ispublic= streamReader.getText();
			    		attach5 = false;
			    	}
			    	if(attach6 == true)
			    	{
			    		attachment.size = streamReader.getText();
			    		attach6 = false;
			    	}
			    	if(attach7 == true)
			    	{
			    		attachment.token = streamReader.getText();
			    		attach7 = false;
			    	}
			    	if(attach8 == true)
			    	{
			    		attachment.url = streamReader.getText();
			    		attach8 = false;
			    	}
			    }
			    
			    if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT)
			    {
			    	//System.out.println(streamReader.getLocalName());
			    	if(streamReader.getLocalName().equalsIgnoreCase("entry"))
			    	{
			    		if(entry != null)
			    		{
			    			beanWriterEntries.write(entry, header, processors);
			    		}
			    		entry = null;
			    	}
			    	
			    	if(streamReader.getLocalName().equalsIgnoreCase("attachment"))
			    	{
			    		if(attachment != null)
			    			beanWriterEntryAttach.write(attachment, header1, processors1);
			    		attachment = null;
			    	}
			    }
			}
			
			/*if(lstEntries.size() > 0)
			{
				
				  ICsvListWriter listWriter = null;
			        try {
			                listWriter = new CsvListWriter(new FileWriter(EntryCSVFile),
			                        CsvPreference.STANDARD_PREFERENCE);
			                
			                // assign a default value for married (if null), and write numberOfKids as an empty column if null
			               
			                // write the header
			                listWriter.writeHeader(header);
			                
			                // write the customer Lists
			                //listWriter.write(lstEntries, processors);
			                Integer counter = 0;
			               for (Iterator<Entry> iterator = lstEntries.iterator(); iterator
								.hasNext();) {
			            	   
			            	   System.out.println("Counter : " + ++counter);
							Entry entry = (Entry) iterator.next();
								
							List<Object> test = Arrays.asList(new Object[] {entry.body, entry.createdat, entry.currenttags, entry.flagtypeid, entry.forumid, entry.hits, entry.id, entry.ishighlighted, entry.islocked, entry.ispinned, entry.ispublic, entry.organizationid, entry.position, entry.postscount, entry.submitterid, entry.title, entry.updatedat, entry.votescount});
							listWriter.write(test);
						}		
			                
			                
			                
			        }
			        finally {
			                if( listWriter != null ) {
			                        listWriter.close();
			                }
			        }
				
				
			}*/
			
			if (beanWriterEntries != null) {
				try {
					beanWriterEntries.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (beanWriterEntryAttach != null) {
				try {
					beanWriterEntryAttach.close();
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
	
	//*****
	
	
	
	public String[] getEntryHeader() {
		String[] header = { "body", "createdat", "currenttags", "flagtypeid",
				"forumid", "hits", "id", "ishighlighted", "islocked",
				"ispinned", "ispublic", "organizationid", "position", "postscount", "submitterid", "title",  "updatedat", "votescount"};
		return header;
	}

	public CellProcessor[] getEntryCellProcessor() {
		CellProcessor[] processors = new CellProcessor[18];
		return processors;
	}
	
	
	public String[] getEntryAttachmentHeader() {
		String[] header = {"entryId","contenttype","createdat","filename","id","ispublic","size","token","url"};
		return header;
	} 
	
	public CellProcessor[] getEntryAttachmentCellProcessor() {
		CellProcessor[] processors = new CellProcessor[9];
		return processors;
	}

}

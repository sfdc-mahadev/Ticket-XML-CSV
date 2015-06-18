package com.persistent.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanWriter;

import com.persistent.models.Post;

public class PostsCSV {

	public PostsCSV() {
		// TODO Auto-generated constructor stub
	}
	
	Properties prop = new Properties();

	CSVBeanWriterManager beanwritermgrPosts = new CSVBeanWriterManager();
	String csvFileNamePosts;
	ICsvBeanWriter beanWriterPosts = null;
	
	String[] header = null;
	CellProcessor[] processors = null;
	
	Post post = null;
	
	public String[] getPostHeader() {
		String[] header = {"accountid","body","createdat","entryid","forumid","id","isinformative","updatedat","userid"};
		return header;
	} 
	
	public CellProcessor[] getPostCellProcessor() {
		CellProcessor[] processors = new CellProcessor[9];
		return processors;
	}
	
	public void ConvertPost2CSV(String xPost, String cPost) {

		String csvFileNamePost = cPost;
		beanWriterPosts = beanwritermgrPosts.getTicketCSVWriter(csvFileNamePost,
				"Post");

		header = getPostHeader();
		processors = getPostCellProcessor();

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			xmlInputFactory.setProperty(
					XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
			xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);

			XMLEventReader xmlEventReader = xmlInputFactory
					.createXMLEventReader(new FileInputStream(xPost));

			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();

					System.out.println("---------" + startElement);

					if (startElement.getName().getLocalPart().equals("post")) {
						post = new Post();
					} else if (startElement.getName().getLocalPart()
							.equals("created-at")) {
						xmlEvent = xmlEventReader.nextEvent();
						post.createdat = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("account-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						post.accountid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("body")) {
						xmlEvent = xmlEventReader.nextEvent();
						post.body = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("id")) {
						xmlEvent = xmlEventReader.nextEvent();
						post.id = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("entry-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						post.entryid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("forum-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						post.forumid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("is-informative")) {
						xmlEvent = xmlEventReader.nextEvent();
						post.isinformative = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("updated-at")) {
						post.updatedat = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
						
						//post.updatedat = xmlEvent.asCharacters().getData() ;
					} 
					else if (startElement.getName().getLocalPart()
							.equals("user-id")) {
						post.userid = xmlEvent.isCharacters() ? xmlEvent.asCharacters().getData() : "";
								//
						//post.userid = xmlEvent.asCharacters().getData() ;
					} 
				}

				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					if (endElement.getName().getLocalPart().equals("post")) {
						// empList.add(emp);
						// tickets.add(ticket);
						if (post != null)
						{
							beanWriterPosts.write(post, header, processors);
						}
						post = null;
					}

				}
			}
			if (beanWriterPosts != null) {
				try {
					beanWriterPosts.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean flag1;
	public boolean flag2;
	public boolean flag3;
	public boolean flag4;
	public boolean flag5;
	public boolean flag6;
	public boolean flag7;
	public boolean flag8;
	public boolean flag9;
	
	
	ArrayList<Integer> arrlist = new ArrayList<Integer>(30);
	
	
	public void StAXParsePosts(String xPost, String cPost) throws XMLStreamException, IOException
	{
		
		arrlist.add(21595564);
		arrlist.add(22924065);
		arrlist.add(23094950);
		arrlist.add(23263679);
		arrlist.add(23330584);
		arrlist.add(28099600);
		arrlist.add(30777360);
		arrlist.add(37300964);
		arrlist.add(42042084);
		arrlist.add(43840124);
		arrlist.add(44356100);
		arrlist.add(47135354);
		arrlist.add(49642844);
		arrlist.add(58606584);
		arrlist.add(59341660);
		arrlist.add(60741230);
		arrlist.add(60870354);
		arrlist.add(61230160);
		arrlist.add(64911490);
		arrlist.add(66460624);
		arrlist.add(67084854);
		arrlist.add(21679469);
		arrlist.add(21653049);
		arrlist.add(22563880);
		arrlist.add(24140455);
		arrlist.add(21570724);
		arrlist.add(22422505);
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES , false);
		factory.setProperty(XMLInputFactory.IS_COALESCING, true);  
		
		String csvFileNamePost = cPost;
		
		beanWriterPosts = beanwritermgrPosts.getTicketCSVWriter(csvFileNamePost,
				"Post");

		header = getPostHeader();
		processors = getPostCellProcessor();
		
	
		XMLStreamReader streamReader = factory.createXMLStreamReader(
			    new FileReader(xPost));
		
		while(streamReader.hasNext())
		{
		    streamReader.next();
		    
		    if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT)
		    {
		    	if(streamReader.getLocalName().equalsIgnoreCase("post"))
		    	{
		    		post = new Post();
		    	}
		    	else if(streamReader.getLocalName().equalsIgnoreCase("account-id"))
		    	{
		    		flag1 = true;
		    	}
		    	
		    	else if(streamReader.getLocalName().equalsIgnoreCase("body"))
		    	{
		    		flag2 = true;
		    	}
		    	
		    	else if(streamReader.getLocalName().equalsIgnoreCase("created-at"))
		    	{
		    		flag3 = true;
		    	}
		    	else if(streamReader.getLocalName().equalsIgnoreCase("entry-id"))
		    	{
		    		flag4 = true;
		    	}
		    	else if(streamReader.getLocalName().equalsIgnoreCase("forum-id"))
		    	{
		    		flag5 = true;
		    	}
		    	else if(streamReader.getLocalName().equalsIgnoreCase("id"))
		    	{
		    		flag6 = true;
		    	}
		    	else if(streamReader.getLocalName().equalsIgnoreCase("is-informative"))
		    	{
		    		flag7 = true;
		    	}
		    	else if(streamReader.getLocalName().equalsIgnoreCase("updated-at"))
		    	{
		    		flag8 = true;
		    	}
		    	else if(streamReader.getLocalName().equalsIgnoreCase("user-id"))
		    	{
		    		flag9 = true;
		    	}
		    	
		    }
		    
		    if(streamReader.getEventType() == XMLStreamReader.CHARACTERS)
	    	{
		    	if(flag1)
		    	{
		    		post.accountid = streamReader.getText();
		    		flag1 = false;
		    	}
		    	if(flag2)
		    	{
		    		post.body = streamReader.getText();
		    		flag2 = false;
		    	}
		    	if(flag3)
		    	{
		    		post.createdat = streamReader.getText();
		    		flag3 = false;
		    	}
		    	if(flag4)
		    	{
		    		post.entryid = streamReader.getText();
		    		flag4 = false;
		    	}
		    	if(flag5)
		    	{
		    		post.forumid = streamReader.getText();
		    		flag5 = false;
		    	}
		    	if(flag6)
		    	{
		    		post.id = streamReader.getText();
		    		flag6 = false;
		    	}
		    	if(flag7)
		    	{
	    			post.isinformative = streamReader.getText();
		    		flag7 = false;
		    	}
		    	if(flag8)
		    	{
		    		post.updatedat = streamReader.getText();
		    		flag8 = false;
		    	}
		    	if(flag9)
		    	{
		    		post.userid = streamReader.getText();
		    		flag9 = false;
		    	}
	    	}
		    
		    if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT)
		    {
		    	//System.out.println(streamReader.getLocalName());
		    	if(streamReader.getLocalName().equalsIgnoreCase("post"))
		    	{
		    		if(post != null)
		    		{
		    			if(arrlist.contains(Integer.parseInt(post.entryid)))
		    			{
		    				System.out.println(count++);
		    				beanWriterPosts.write(post, header, processors);
		    			}
		    		}
		    		post = null;
		    	}
		    }
		}
		
		if (beanWriterPosts != null) {
			try {
				beanWriterPosts.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static int count = 1;
}

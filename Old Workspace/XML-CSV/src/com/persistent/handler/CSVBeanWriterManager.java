package com.persistent.handler;

import java.io.*;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * @author vaibhav_langade
 * Class to return CSVBeanWriter object. this class also useful to return cell processors and headers for given table. 
 */
public class CSVBeanWriterManager {
	//private static Logger logger = Logger.getLogger("logger");

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
	
	public String[] getEntryAttachmentHeader() {
		String[] header = {"entryId","contenttype","createdat","filename","id","ispublic","size","token","url"};
		return header;
	} 
	
	public CellProcessor[] getEntryAttachmentCellProcessor() {
		CellProcessor[] processors = new CellProcessor[9];
		return processors;
	}
	
	
	public String[] getOrgHeader() {
		String[] header = {"createdat","defaultVal","details","externalid","groupid","id","isshared","issharedcomments","name","notes","suspended","updatedat","currenttags"};
		return header;
	} 
	
	public CellProcessor[] getOrgCellProcessor() {
		CellProcessor[] processors = new CellProcessor[13];
		return processors;
	}
	
	public String[] getUserHeader() {
		String[] header = {"createdat" ,"details" ,"externalid" ,"id" ,"isactive" ,"lastlogin" ,"localeid" ,"name","notes" ,"openidurl" ,"organizationid" ,"phone" ,"restrictionid" ,"roles" ,"timezone","updatedat" ,"email","isverified" ,"customroleid" ,"photourl","uses12hourclock"};
		return header;
	} 
	
	public CellProcessor[] getUserCellProcessor() {
		CellProcessor[] processors = new CellProcessor[21];
		return processors;
	}
	
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
	
	
	public String[] getPostHeader() {
		String[] header = {"accountid","body","createdat","entryid","forumid","id","isinformative","updatedat","userid"};
		return header;
	} 
	
	public CellProcessor[] getPostCellProcessor() {
		CellProcessor[] processors = new CellProcessor[9];
		return processors;
	}
	
	
	/**
	 * @param csvFileName - CSV file Name
	 * @param type -  table name or type of object to be generated as CSV 
	 * @return - ICsvBeanWriter object
	 * this method returns ICsvBeanWriter object based on table name or type
	 */
	public ICsvBeanWriter getTicketCSVWriter(String csvFileName, String type) {
	    ICsvBeanWriter beanWriter = null;
	 
	    try {
	    	
	        beanWriter = new CsvBeanWriter(new FileWriter(csvFileName),
	                CsvPreference.STANDARD_PREFERENCE);
	        
	       if("ticket".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getTicketHeader());
	       if("comment".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getCommentHeader());
	       if("attachment".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getAttachmentHeader());
	       if("organization".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getOrgHeader());
	       if("user".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getUserHeader());
	       if("entry".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getEntryHeader());
	       if("entry-attach".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getEntryAttachmentHeader());
	       if("post".equalsIgnoreCase(type))
		        beanWriter.writeHeader(getPostHeader());
	       
	       
	    } catch (IOException ex) {
	        System.err.println("Error writing the CSV file: " + ex);
	    } catch(Exception e){
	    	 System.err.println("Error writing the CSV file: " + e);
	    }
	    
	    return beanWriter;
	}
	
	
	/*public Properties getProperties(){
		Properties prop = new Properties();
		try {
			
			File file;
			InputStream input = null;
			
			file = new File("src/navisextracter/config.properties");
			input = new FileInputStream(file);
			prop.load(input);
		}catch (Exception e) {
			//logger.error("Exception thrown in main method ", e);
			
		}
		
		return prop;
	}
*/

}

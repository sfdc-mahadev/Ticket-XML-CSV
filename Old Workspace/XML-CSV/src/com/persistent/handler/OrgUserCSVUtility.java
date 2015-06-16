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

public class OrgUserCSVUtility {

	Properties prop = new Properties();

	CSVBeanWriterManager beanwritermgrOrg = new CSVBeanWriterManager();
	String csvFileNameOrg;
	ICsvBeanWriter beanWriterOrg = null;

	CSVBeanWriterManager beanwritermgrUser = new CSVBeanWriterManager();
	String csvFileNameUser;
	ICsvBeanWriter beanWriterUser = null;

	String[] header = null;
	CellProcessor[] processors = null;

	String[] header1 = null;
	CellProcessor[] processors1 = null;

	Organization org = null;
	User user = null;

	public void ConvertOrg2CSV(String xOrg, String cOrg) {

		csvFileNameOrg = cOrg;
		beanWriterOrg = beanwritermgrOrg.getTicketCSVWriter(csvFileNameOrg,
				"organization");

		header = getOrgHeader();
		processors = getOrgCellProcessor();

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			xmlInputFactory.setProperty(
					XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
			xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);

			XMLEventReader xmlEventReader = xmlInputFactory
					.createXMLEventReader(new FileInputStream(xOrg));

			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();

					System.out.println("---------" + startElement);

					if (startElement.getName().getLocalPart()
							.equals("organization")) {
						org = new Organization();
					} else if (startElement.getName().getLocalPart()
							.equals("created-at")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.createdat = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}

					else if (startElement.getName().getLocalPart()
							.equals("default")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.defaultVal = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("details")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.details = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("external-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.externalid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("group-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.groupid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("id")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.id = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("is-shared")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.isshared = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("is-shared-comments")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.issharedcomments = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("name")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.name = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("notes")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.notes = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("suspended")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.suspended = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("updated-at")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.updatedat = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("current-tags")) {
						xmlEvent = xmlEventReader.nextEvent();
						org.currenttags = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
				}

				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					if (endElement.getName().getLocalPart()
							.equals("organization")) {
						// empList.add(emp);
						// tickets.add(ticket);
						if (org != null)
							beanWriterOrg.write(org, header, processors);
						org = null;
					}

				}
			}
			if (beanWriterOrg != null) {
				try {
					beanWriterOrg.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void ConvertUser2CSV(String xUser, String cUser) {

		csvFileNameUser = cUser;
		beanWriterUser = beanwritermgrUser.getTicketCSVWriter(csvFileNameUser,
				"user");

		header1 = getUserHeader();
		processors1 = getUserCellProcessor();

		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			xmlInputFactory.setProperty(
					XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
			xmlInputFactory.setProperty(XMLInputFactory.IS_COALESCING, true);

			XMLEventReader xmlEventReader = xmlInputFactory
					.createXMLEventReader(new FileInputStream(xUser));

			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();

					System.out.println("---------" + startElement);

					if (startElement.getName().getLocalPart().equals("user")) {
						user = new User();
					} else if (startElement.getName().getLocalPart()
							.equals("created-at")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.createdat = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("details")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.details = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("external-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.externalid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("id")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.id = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("last-login")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.lastlogin = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("locale-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.localeid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("name")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.name = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("notes")) {
						user.notes = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("openid-url")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.openidurl = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("organization-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.organizationid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("phone")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.phone = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("restriction-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.restrictionid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("roles")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.roles = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("time-zone")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.timezone = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("updated-at")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.updatedat = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("current-tags")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.currenttags = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("email")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.email = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("is-verified")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.isverified = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("custom-role-id")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.customroleid = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("groups")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.groups = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("photo-url")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.photourl = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					} else if (startElement.getName().getLocalPart()
							.equals("uses-12-hour-clock")) {
						xmlEvent = xmlEventReader.nextEvent();
						user.uses12hourclock = xmlEvent.isCharacters() ? xmlEvent
								.asCharacters().getData() : "";
					}
				}

				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();
					if (endElement.getName().getLocalPart().equals("user")) {
						// empList.add(emp);
						// tickets.add(ticket);
						if (user != null)
							beanWriterUser.write(user, header1, processors1);
						user = null;
					}

				}
			}
			if (beanWriterUser != null) {
				try {
					beanWriterUser.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String[] getOrgHeader() {
		String[] header = { "createdat", "defaultVal", "details", "externalid",
				"groupid", "id", "isshared", "issharedcomments", "name",
				"notes", "suspended", "updatedat", "currenttags" };
		return header;
	}

	public CellProcessor[] getOrgCellProcessor() {
		CellProcessor[] processors = new CellProcessor[13];
		return processors;
	}

	public String[] getUserHeader() {
		String[] header = { "createdat", "details", "externalid", "id",
				"isactive", "lastlogin", "localeid", "name", "notes",
				"openidurl", "organizationid", "phone", "restrictionid",
				"roles", "timezone", "updatedat", "email", "isverified",
				"customroleid", "photourl", "uses12hourclock" };
		return header;
	}

	public CellProcessor[] getUserCellProcessor() {
		CellProcessor[] processors = new CellProcessor[21];
		return processors;
	}

}

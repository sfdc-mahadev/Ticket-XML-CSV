package com.persistent.models;

public class Comment {

	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public String authorid;
	public String createdat;
	public String ispublic;
	public String type;
	public String value;
	public String viaid;
	public String ticketId;
	/**
	 * @return the ticketId
	 */
	public String getTicketId() {
		return ticketId;
	}
	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * @return the commentId
	 */
	public String getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String commentId;
	
	/**
	 * @return the authorid
	 */
	public String getAuthorid() {
		return authorid;
	}
	/**
	 * @param authorid the authorid to set
	 */
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	/**
	 * @return the createdat
	 */
	public String getCreatedat() {
		return createdat;
	}
	/**
	 * @param createdat the createdat to set
	 */
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	/**
	 * @return the ispublic
	 */
	public String getIspublic() {
		return ispublic;
	}
	/**
	 * @param ispublic the ispublic to set
	 */
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the viaid
	 */
	public String getViaid() {
		return viaid;
	}
	/**
	 * @param viaid the viaid to set
	 */
	public void setViaid(String viaid) {
		this.viaid = viaid;
	}

}

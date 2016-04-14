package com.support.objects;


import java.io.Serializable;

public class PendingCases implements Serializable {
	/**
	 *
	 */
	private int PendingCommentID;
	private String Username;
	private String CommentTitle;
	private String Comments;
	private String Severity;
	private String CaseSeverityDesc;
	private int CaseReasonID;
	private String ReasonDesc;
	private String DateCreated;
	private boolean HasAttachment;

	public PendingCases(int pendingCommentID, String username, String commentTitle, String comments, String severity, String caseSeverityDesc, int caseReasonID, String reasonDesc, String dateCreated, boolean hasAttachment) {
		PendingCommentID = pendingCommentID;
		Username = username;
		CommentTitle = commentTitle;
		Comments = comments;
		Severity = severity;
		CaseSeverityDesc = caseSeverityDesc;
		CaseReasonID = caseReasonID;
		ReasonDesc = reasonDesc;
		DateCreated = dateCreated;
		HasAttachment = hasAttachment;
	}

	public int getPendingCommentID() {
		return PendingCommentID;
	}

	public void setPendingCommentID(int pendingCommentID) {
		PendingCommentID = pendingCommentID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getCommentTitle() {
		return CommentTitle;
	}

	public void setCommentTitle(String commentTitle) {
		CommentTitle = commentTitle;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getSeverity() {
		return Severity;
	}

	public void setSeverity(String severity) {
		Severity = severity;
	}

	public String getCaseSeverityDesc() {
		return CaseSeverityDesc;
	}

	public void setCaseSeverityDesc(String caseSeverityDesc) {
		CaseSeverityDesc = caseSeverityDesc;
	}

	public int getCaseReasonID() {
		return CaseReasonID;
	}

	public void setCaseReasonID(int caseReasonID) {
		CaseReasonID = caseReasonID;
	}

	public String getReasonDesc() {
		return ReasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		ReasonDesc = reasonDesc;
	}

	public String getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}

	public boolean isHasAttachment() {
		return HasAttachment;
	}

	public void setHasAttachment(boolean hasAttachment) {
		HasAttachment = hasAttachment;
	}
}


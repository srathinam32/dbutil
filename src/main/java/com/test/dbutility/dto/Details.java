/**
 * 
 */
package com.test.dbutility.dto;

/**
 * @author Kesavalu
 *
 */
public class Details {

	private String project;
	private String projectLead;
	private String emailAddress;
	private int issueCount;
	
	
	/**
	 * @param project
	 * @param projectLead
	 * @param emailAddress
	 * @param issueCount
	 */
	public Details(String project, String projectLead, String emailAddress, int issueCount) {
		super();
		this.project = project;
		this.projectLead = projectLead;
		this.emailAddress = emailAddress;
		this.issueCount = issueCount;
	}
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * @return the projectLead
	 */
	public String getProjectLead() {
		return projectLead;
	}
	/**
	 * @param projectLead the projectLead to set
	 */
	public void setProjectLead(String projectLead) {
		this.projectLead = projectLead;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the issueCount
	 */
	public int getIssueCount() {
		return issueCount;
	}
	/**
	 * @param issueCount the issueCount to set
	 */
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
	
	
	
}

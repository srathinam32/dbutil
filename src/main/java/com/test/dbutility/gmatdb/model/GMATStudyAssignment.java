package com.test.dbutility.gmatdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GMAT_STUDY_ASSIGNMENTS",schema = "db2")
public class GMATStudyAssignment {
	@Id
	@Column(name = "GMAT_MASTER_SEQ")
	private int gmatMasterSeq;
	
	@Column(name = "STUDY_NUMBER")
	private String studyNumber;
	
	@Column(name = "Study_Status_Actual")
	private String studyStatusActual;
	
	
}
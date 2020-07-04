package com.test.dbutility.gmatdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GMAT_STUDY_MASTER",schema = "gmatdb")
public class GMATStudyMaster {
	@Id
	@Column(name = "GMAT_MASTER_SEQ")
	private int gmatMasterSeq;
	
	@Column(name = "STUDY_NUMBER")
	private String studyNumber;
	
	@Column(name = "STUDY")
	private String study;
}
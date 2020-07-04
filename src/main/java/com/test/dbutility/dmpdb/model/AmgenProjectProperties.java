package com.test.dbutility.dmpdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AMGEN_PROJECT_PROPERTIES",schema = "db1")
public class AmgenProjectProperties {
	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "LOCKED")
	private String locked;
	
	@Column(name = "STUDY")
	private String study;
}
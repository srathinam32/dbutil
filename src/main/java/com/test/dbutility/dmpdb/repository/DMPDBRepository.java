package com.test.dbutility.dmpdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.dbutility.dmpdb.model.AmgenProjectProperties;
import com.test.dbutility.dto.Details;

 
public interface DMPDBRepository extends CrudRepository<AmgenProjectProperties, Long>{
	
	@Query(value = "Select study from AMGEN_PROJECT_PROPERTIES where locked is null", nativeQuery = true)
	List<String> getDMPStudy();
	
	@Query(value = "SELECT NEW com.test.dbutility.dto.Details(project, projectLead , emailAddress, issueCount) from (select b.pname as project,b.lead as projectLead,c.email_Address as emailAddress,count(a.id) as issueCount from JIRAISSUE a,project b,Cwd_User c where a.id=b.id and b.lead=c.user_name and b.pname in :pnames group by b.pname, b.lead, c.email_Address)", nativeQuery = true)
	List<Details> getDMPDetails(@Param(value = "pnames") List<String> pnames);
	
}

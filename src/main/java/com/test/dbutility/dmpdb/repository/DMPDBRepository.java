package com.test.dbutility.dmpdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.dbutility.dmpdb.model.AmgenProjectProperties;

 
public interface DMPDBRepository extends CrudRepository<AmgenProjectProperties, Long>{
	
	@Query(value = "Select study from AMGEN_PROJECT_PROPERTIES where locked is null", nativeQuery = true)
	List<String> getDMPStudy();
	
	@Query(value = "SELECT 	b.pname,b.lead,c.email_Address,count(a.id) FROM JIRAISSUE a,project b,Cwd_User c WHERE a.id=b.id and b.lead=c.user_name and b.pname in :pnames GROUP BY b.pname, b.lead, c.email_Address", nativeQuery = true)
	List<Object[]> getDMPDetails(@Param(value = "pnames") List<String> pnames);
	
}

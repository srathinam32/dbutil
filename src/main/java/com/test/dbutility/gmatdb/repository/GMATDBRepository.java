package com.test.dbutility.gmatdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.dbutility.gmatdb.model.GMATStudyMaster;
 
public interface GMATDBRepository extends CrudRepository<GMATStudyMaster, Long>{
	
	@Query( value = "Select study from GMAT_STUDY_MASTER where study_number in (Select study_number from GMAT_STUDY_ASSIGNMENTS where Study_Status_Actual='LOCKED') and study in (:study)", nativeQuery = true)
	List<String> getGMATStudy(@Param(value = "study") List<String> study);
}

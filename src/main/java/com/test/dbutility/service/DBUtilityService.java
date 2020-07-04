/**
 * 
 */
package com.test.dbutility.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dbutility.dmpdb.repository.DMPDBRepository;
import com.test.dbutility.dto.Details;
import com.test.dbutility.gmatdb.repository.GMATDBRepository;
import com.test.dbutility.util.ExcelGenerator;
import com.test.dbutility.util.MailUtil;

/**
 * @author Kesavalu
 *
 */
@Service
public class DBUtilityService {

	@Autowired
	DMPDBRepository dmpdbRepo;
	
	@Autowired
	GMATDBRepository gmatdbRepo;
	
	@Autowired
	MailUtil mailUtil;
	
	public List<String> getDMPStudy(){
		
		return dmpdbRepo.getDMPStudy();
	}
	
	public List<String> getGMATStudy(List<String> study){
		return gmatdbRepo.getGMATStudy(study);
	}
	public List<Details> getDetails(List<String> pnames){
		List<Details> details = new ArrayList<>();
		List<Object[]> lists = dmpdbRepo.getDMPDetails(pnames);
		for(Object[] s :lists) {
			Details det = new Details(String.valueOf(s[0]), String.valueOf(s[1]), String.valueOf(s[2]), Integer.parseInt(String.valueOf(s[3])));
			details.add(det);
		}
		return details;
	}
	public List<Details> getFullDetails(){
		return getDetails(getGMATStudy(getDMPStudy()));
	}
	public void sendMail(String email,String msg) throws IOException {
		List<String> dmpStudies = getDMPStudy();
		List<String> gmatStudyPnames = getGMATStudy(dmpStudies);
		List<Details> details = getDetails(gmatStudyPnames);
		ByteArrayInputStream in = null;
		File targetFile = null;
		OutputStream out = null;
		try {
			in = ExcelGenerator.customersToExcel(details);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);

			targetFile = new File("temp.xls");
			out = new FileOutputStream(targetFile);
			out.write(buffer);
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}

		try {
			mailUtil.sendEmailWithAttachment("dummy.fmsproject@gmail.com",new String[] {email}, "Test Report "+msg, "test mail for report sending....", targetFile, "TestDBUtilityReport");
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}

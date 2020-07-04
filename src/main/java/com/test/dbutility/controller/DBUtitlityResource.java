package com.test.dbutility.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.dbutility.dto.Details;
import com.test.dbutility.service.DBUtilityService;
import com.test.dbutility.util.ExcelGenerator;

@RestController
@RequestMapping("/api/reports")
public class DBUtitlityResource {
	
@Autowired
DBUtilityService service;
	
	@GetMapping(value = "/download")
	public ResponseEntity<InputStreamResource> downloadReport() throws IOException {
		List<Details> details = service.getFullDetails();
		
		ByteArrayInputStream in = ExcelGenerator.customersToExcel(details);
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping(value = "/sendMail")
	public ResponseEntity<String> mail(@RequestParam String mail) throws IOException {
		System.out.println("email value=" + mail);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
		service.sendMail(mail,"");
			return ResponseEntity.ok().headers(headers).body("Email sent to " + mail + " successfully");
	}
}

package com.ye.restController;

import java.io.IOException;
import java.net.URI;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sleepData.model.SleepingData;
import com.sleepData.service.SleepingDataService;


@RestController
@RequestMapping("/")
public class DownloadRestController {
	
	
	/*
	 * Provides REST service that downloads zip files to user's local machine
	 * 
	 * @return ResponseEntity
	 * 
	 * */
	@RequestMapping(value = "/download", method = RequestMethod.GET, produces = "application/zip")
	public ResponseEntity <InputStreamResource> downloadPDFFile()
	        throws IOException, InterruptedException {
		
		/*
		 * TODO
		 * get the S3 path and download file
		 * */
		
		String path = "/downloadedFiles";
		String fileName = "test.zip";
		
	    ClassPathResource zipFile = new ClassPathResource(path+"/"+fileName);
	    
	    //prevent caching
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    headers.add("Pragma", "no-cache");
	    headers.add("Expires", "0");
	    
	    return ResponseEntity
	            .ok()
	            .headers(headers)
	            .contentLength(zipFile.contentLength())
	            .contentType(
	                    MediaType.parseMediaType("application/octet-stream"))
	            .body(new InputStreamResource(zipFile.getInputStream()));
		
		
	}
	
	@RequestMapping(value = "/notfound", method = RequestMethod.GET)
	public ResponseEntity fileNotFound(){		
		ResponseEntity<String> responseEntity = new ResponseEntity<>("Your data is not uploaded. ", HttpStatus.OK);
		return responseEntity;
	}
	
	
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String checkData(){
		
		String fileInTheS3 = "false";
		
		//TODO 
		// get profileid and check if the data is in the db
		// then set fileInTheS3 to 'true' or 'false' accordingly
		
		return fileInTheS3;
	}
	
	/*
	 * A helper method to indicate whether rest service is running
	 * */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		String result="Hello! REST service is running!";  
		return result;
	}
}
package gr.athenstech.dissertation.decisionsupportsystem.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.athenstech.dissertation.decisionsupportsystem.dto.SoilInformationDTO;
import gr.athenstech.dissertation.decisionsupportsystem.dto.SoilResultsResponse;
import gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl.SoilDataServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/soildata")
public class SoilDataController {	
	private static final Logger logger = LoggerFactory.getLogger(SoilDataController.class);
	
	@Autowired
	private SoilDataServiceImpl soilDataServiceImpl; 
	
	@PostMapping
	public ResponseEntity<SoilResultsResponse> getSoilData(@RequestBody SoilInformationDTO soilInformation) {
		 logger.info("SoilInformation received: {}", soilInformation.toString());
		 SoilResultsResponse soilResults = null;
		 
		 try {			 
			 String carbonResult = soilDataServiceImpl.calculateCarbonResult(soilInformation.getCarbon(), soilInformation.getSilt(), soilInformation.getClay());
			 String phResult = soilDataServiceImpl.calculatePhResult(soilInformation.getPh());
			 String cationResult = soilDataServiceImpl.calculateCationResult(soilInformation.getCation());
			 String bulkResult = soilDataServiceImpl.calculateBulkResult(soilInformation.getBulk());
			 logger.info("carbonResult received: " + carbonResult);
			 logger.info("phResult received: " + phResult);
			 logger.info("cationResult received: " + cationResult);
			 logger.info("bulkResult received: " + bulkResult);
			 
			 soilResults = new SoilResultsResponse();
			 soilResults.setCarbonResult(carbonResult);
			 soilResults.setPhResult(phResult);
			 soilResults.setCationResult(cationResult);
			 soilResults.setCalculateBulkResult(bulkResult);

//			 soilResults = soilDataServiceImpl.();
//			 soilResults.setSoilTextureClass("asd");			 
			 
			return new ResponseEntity<SoilResultsResponse>(soilResults, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SoilResultsResponse>(soilResults, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	} 

}

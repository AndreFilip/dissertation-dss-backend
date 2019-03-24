package gr.athenstech.dissertation.decisionsupportsystem.controllers;


import java.util.List;

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
import gr.athenstech.dissertation.decisionsupportsystem.model.SoilData;
import gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl.SoilDataServiceImpl;
import gr.athenstech.dissertation.decisionsupportsystem.utils.MyConstants;

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
			 List<String> textureClassList = soilDataServiceImpl.calculateTextureClass(soilInformation.getSand(), soilInformation.getSilt() ,soilInformation.getClay());
			 String typeOfSoil = "No type of soil matched.";

			 if(textureClassList.indexOf(MyConstants.Soil_Texture_Class_Not_Found) == -1) {
				 SoilData sd = soilDataServiceImpl.findByTextureClass(textureClassList.get(0));
				 typeOfSoil = sd.getTypeOfSoil();
			 }
//			 logger.info("typeOfSoil received: " + typeOfSoil);
			 
			 soilResults = new SoilResultsResponse();
			 soilResults.setCarbonResult(carbonResult);
			 soilResults.setPhResult(phResult);
			 soilResults.setCationResult(cationResult);
			 soilResults.setCalculateBulkResult(bulkResult);
			 soilResults.setSoilTextureClassList(textureClassList);	 
			 soilResults.setTypeOfSoil(typeOfSoil);	 

			return new ResponseEntity<SoilResultsResponse>(soilResults, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SoilResultsResponse>(soilResults, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	} 

}

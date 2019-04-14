package gr.athenstech.dissertation.decisionsupportsystem.controllers;

import gr.athenstech.dissertation.decisionsupportsystem.services.GraphicsService;
import gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl.FireIndexServiceImpl;
import gr.athenstech.dissertation.decisionsupportsystem.dto.FireDataDTO;
import gr.athenstech.dissertation.decisionsupportsystem.model.Graphic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/firedata")
public class FireController {
	private static final Logger logger = LoggerFactory.getLogger(FireController.class);

	@Autowired
    private FireIndexServiceImpl fireIndexServiceImpl;
	
	 @PostMapping
	 public ResponseEntity<Double> getFireIndex(@RequestBody FireDataDTO fd) {	
		logger.info("List<Graphic> graphics received: {}", fd.toString());
		double temp = fd.getTemp();
		double rhum = fd.getRhum();
		double wind = fd.getWind();
		double prcp = fd.getPrcp();
		int mth = fd.getMth();
		try {
			double result = fireIndexServiceImpl.FWIcalculator(temp, rhum, wind, prcp, mth);
			return new ResponseEntity<Double>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Double>((double) -1, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	 }

}


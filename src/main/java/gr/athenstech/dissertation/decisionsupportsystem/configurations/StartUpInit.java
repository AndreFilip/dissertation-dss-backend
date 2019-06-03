package gr.athenstech.dissertation.decisionsupportsystem.configurations;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gr.athenstech.dissertation.decisionsupportsystem.controllers.FileController;
import gr.athenstech.dissertation.decisionsupportsystem.model.SoilData;
import gr.athenstech.dissertation.decisionsupportsystem.repositories.SoilDataRepository;

@Component
public class StartUpInit {
 private static final Logger logger = LoggerFactory.getLogger(StartUpInit.class);

	@Autowired
	SoilDataRepository soilDataRepository;
	
	@PostConstruct
	private void init(){
		initializeSoilDataTable();
	 }
	
	private void initializeSoilDataTable() {
//		soilDataRepository.save(new SoilData("Sand","Sand","Coarse texture","Sand soil is best for root based vegetables like turnips, parsnips, and carrots.  Sandy soils are more prone to high bulk density."));
//		soilDataRepository.save(new SoilData("Sand","Loamy sand","Coarse texture","Sand soil is best for root based vegetables like turnips, parsnips, and carrots.  Sandy soils are more prone to high bulk density."));
//		soilDataRepository.save(new SoilData("Loam ","Sandy loam","Moderately coarse texture", "Loam type of soil helps grow the best possible crops because it provides the necessary elements."));				
//		soilDataRepository.save(new SoilData("Loam","Loam","Medium texture", "Loam type of soil helps grow the best possible crops because it provides the necessary elements."));
//		soilDataRepository.save(new SoilData("Loam","Silty loam","Medium texture","Loam type of soil helps grow the best possible crops because it provides the necessary elements. Silty soil has also nutrients for good development. Lettuce, cabbage, carrots, turnips, and many other vegetables flourish in silt."));
//		soilDataRepository.save(new SoilData("Loam","Silt","Medium texture","Loam type of soil helps grow the best possible crops because it provides the necessary elements. Silty soil has also nutrients for good development. Lettuce, cabbage, carrots, turnips, and many other vegetables flourish in silt."));		
//		soilDataRepository.save(new SoilData("Loam","Clay loam","Moderately fine texture","Loam type of soil helps grow the best possible crops because it provides the necessary elements."));
//		soilDataRepository.save(new SoilData("Loam","Sandy clay loam","Moderately fine texture","Loam type of soil helps grow the best possible crops because it provides the necessary elements."));
//		soilDataRepository.save(new SoilData("Loam","Silty clay loam","Moderately fine texture","Loam type of soil helps grow the best possible crops because it provides the necessary elements. Silty soil has also nutrients for good development. Lettuce, cabbage, carrots, turnips, and many other vegetables flourish in silt."));		
//		soilDataRepository.save(new SoilData("Clay","Sandy clay","Fine texture","Clay type of soil is best for growing cabbage and broccoli and is not considered good for root vegetables. Because of its dense texture it creates problems for roots to expand. Soils with a higher clay fraction tend to have a higher CEC."));
//		soilDataRepository.save(new SoilData("Clay","Silty clay","Fine texture","Clay type of soil is best for growing cabbage and broccoli and is not considered good for root vegetables. Because of its dense texture it creates problems for roots to expand. Soils with a higher clay fraction tend to have a higher CEC. Silty soil has also nutrients for good development. Lettuce, cabbage, carrots, turnips, and many other vegetables flourish in silt."));
//		soilDataRepository.save(new SoilData("Clay","Clay","Fine texture","Clay type of soil is best for growing cabbage and broccoli and is not considered good for root vegetables. Because of its dense texture it creates problems for roots to expand. Soils with a higher clay fraction tend to have a higher CEC."));
	}

}

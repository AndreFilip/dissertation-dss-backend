package gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.athenstech.dissertation.decisionsupportsystem.services.SoilDataService;
import gr.athenstech.dissertation.decisionsupportsystem.utils.MyConstants;
import gr.athenstech.dissertation.decisionsupportsystem.model.SoilData;
import gr.athenstech.dissertation.decisionsupportsystem.repositories.SoilDataRepository;

@Service
public class SoilDataServiceImpl implements SoilDataService {
	
	@Autowired
	private SoilDataRepository soilDataRepository; 

	@Override
	@Transactional
	public SoilData findByTextureClass(String textureClass) {		
		return soilDataRepository.findByTextureClass(textureClass);
	}
	
	@Override
	public List<String> calculateTextureClass(double sand, double silt, double clay) {
		List<String> results = new ArrayList<>();
		
		if(sand >= 86 && (silt >=0 && silt <= 14) && (clay >= 0 && clay <= 10)) {
			results.add(MyConstants.Soil_Sand);
		} else if (( sand >= 70 && sand < 86) && (silt >=0 && silt <= 30) && (clay >= 0 && clay <= 15)) {
			results.add(MyConstants.Soil_Loamy_sand);
		} else if (( sand >= 50 && sand <= 70) && (silt >=0 && silt <= 50) && (clay >= 0 && clay <= 20)) {
			results.add(MyConstants.Soil_Sandy_loam);
		} else if (( sand >= 23 && sand <= 52) && (silt >= 28 && silt <= 50) && (clay >= 7 && clay <= 27)) {
			results.add(MyConstants.Soil_Loam);
		} else if (( sand >= 20 && sand < 50) && (silt >= 74 && silt <= 88) && (clay >= 0 && clay <= 27)) {
			results.add(MyConstants.Soil_Silty_loam);
		} else if (( sand >= 0 && sand < 20) && (silt >= 88 && silt <= 100) && (clay >= 0 && clay <= 12)) {
			results.add(MyConstants.Soil_Silt);
		} else if (( sand >= 20 && sand < 45) && (silt >= 15 && silt <= 52) && (clay >= 27 && clay <= 40)) {
			results.add(MyConstants.Soil_Clay_loam);
		} else if (( sand >= 48 && sand <= 80) && (silt >= 0 && silt <= 28) && (clay >= 20 && clay <= 35)) {
			results.add(MyConstants.Soil_Sand_clay_loam);
		} else if (( sand >= 0 && sand < 20) && (silt >= 40 && silt <= 73) && (clay >= 27 && clay <= 40)) {
			results.add(MyConstants.Soil_Silty_clay_loam);
		} else if (( sand >= 45 && sand < 65) && (silt >= 0 && silt <= 20) && (clay >= 35 && clay <= 55)) {
			results.add(MyConstants.Soil_Sandy_clay);
		} else if (( sand >= 0 && sand < 20) && (silt >= 40 && silt <= 60) && (clay >= 40 && clay <= 60)) {
			results.add(MyConstants.Soil_Silty_clay);
		} else if (( sand >= 0 && sand < 45) && (silt >= 0 && silt <= 40) && (clay >= 40 && clay <= 100)) {
			results.add(MyConstants.Soil_Clay);
		} 
		
		if(results.isEmpty()) {
			results.add(MyConstants.Soil_Texture_Class_Not_Found);
		}
		
		return results;
	}

	@Override
	public String calculateBulkResult(double bulk) {
		String result = null;
		if(bulk >= 1.6) {
			result = MyConstants.Bulk_Result_above_1_6;
		} else if (bulk >= 1) {
			result = MyConstants.Bulk_Result_between_1_1_6;
		} else if (bulk >= 0.02) {
			result = MyConstants.Bulk_Result_below_1;
		} else {
			result = MyConstants.Bulk_Result_below_0_02;
		}
		
		return result;		
	}
	
	@Override
	public String calculateCationResult (double cation) {		
		String result = null;
		if(cation >= 30) {
			result = MyConstants.Cation_Result_above_30;
		} else if (cation >= 10) {
			result = MyConstants.Cation_Result_above_10;
		} else {
			result = MyConstants.Cation_Result_below_10;
		}		
		return result;		
	}
	
	@Override
	public String calculatePhResult(double ph) {
		String result = null;
		if(ph > 7.5 || ph < 5.5) {
			result = MyConstants.PH_Result_bad;
		} else if (ph >= 6.2 && ph <= 6.8) {
			result = MyConstants.PH_Result_optimal;
		} else {
			result = MyConstants.PH_Result_good;
		}		
		return result;			
	}
	
	@Override
	public String calculateCarbonResult(double carbon, double silt, double clay) {
		String result = null;
		double threshold = calculateCarbonThreshold(silt, clay);
		if(carbon >= threshold) {
			result = MyConstants.SOC_Result_above_threshold + " Your threshold is " + Math.floor(threshold * 100) / 100 + ".";
		} else {
			result = MyConstants.SOC_Result_below_threshold + " Your threshold is " + Math.floor(threshold * 100) / 100 + ".";
		}
		return result;			
	}
	
	private double calculateCarbonThreshold(double silt, double clay) {
		return 0.87 + 0.32*(silt + clay);		
	}
	

}

package gr.athenstech.dissertation.decisionsupportsystem.services.servicesImpl;

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
	public SoilData findBySubTypeOfSoil(String subTypeOfSoil) {		
		return soilDataRepository.findBySubTypeOfSoil(subTypeOfSoil);
	}
	
//	TODO:calculateSoilTextureClass

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
		} else if (ph >= 6.2 || ph <= 6.8) {
			result = MyConstants.PH_Result_optimal;
		} else {
			result = MyConstants.BPH_Result_good;
		}		
		return result;			
	}
	
	@Override
	public String calculateCarbonResult(double carbon, double silk, double clay) {
		String result = null;
		if(carbon >= calculateCarbonThreshold(silk, clay)) {
			result = MyConstants.SOC_Result_above_threshold;
		} else {
			result = MyConstants.SOC_Result_below_threshold;
		}
		return result;			
	}
	
	private double calculateCarbonThreshold(double silk, double clay) {
		return 0.87 + 0.32*(silk + clay);		
	}
	

}

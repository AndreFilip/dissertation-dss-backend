package gr.athenstech.dissertation.decisionsupportsystem.services;


import java.util.List;

import gr.athenstech.dissertation.decisionsupportsystem.model.SoilData;

public interface SoilDataService {
	public SoilData findByTextureClass(String textureClass);
	public String calculateBulkResult(double bulk);	
	public String calculateCationResult(double cation);
	public String calculatePhResult(double ph);	
	public String calculateCarbonResult(double carbon, double silt, double clay);
	public List<String> calculateTextureClass(double sand, double silt, double clay);	
}

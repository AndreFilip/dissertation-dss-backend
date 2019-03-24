package gr.athenstech.dissertation.decisionsupportsystem.services;


import gr.athenstech.dissertation.decisionsupportsystem.model.SoilData;

public interface SoilDataService {
	public SoilData findBySubTypeOfSoil(String subTypeOfSoil);
	public String calculateBulkResult(double bulk);	
	public String calculateCationResult(double cation);
	public String calculatePhResult(double ph);	
	public String calculateCarbonResult(double carbon, double silk, double clay);
}

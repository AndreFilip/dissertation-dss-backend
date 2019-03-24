package gr.athenstech.dissertation.decisionsupportsystem.dto;

import java.util.List;

public class SoilResultsResponse {
	private String carbonResult;
	private String phResult;
	private String cationResult;
	private String calculateBulkResult;
	private List<String> soilTextureClassList;
	private String typeOfSoil;
	
	public SoilResultsResponse() {}

	public SoilResultsResponse(String carbonResult, String phResult, String cationResult, String calculateBulkResult,
			List<String> soilTextureClassList,String typeOfSoil) {
		super();
		this.carbonResult = carbonResult;
		this.phResult = phResult;
		this.cationResult = cationResult;
		this.calculateBulkResult = calculateBulkResult;
		this.soilTextureClassList = soilTextureClassList;
		this.typeOfSoil = typeOfSoil;
	}	

	public String getTypeOfSoil() {
		return typeOfSoil;
	}

	public void setTypeOfSoil(String typeOfSoil) {
		this.typeOfSoil = typeOfSoil;
	}

	public String getCarbonResult() {
		return carbonResult;
	}

	public void setCarbonResult(String carbonResult) {
		this.carbonResult = carbonResult;
	}

	public String getPhResult() {
		return phResult;
	}

	public void setPhResult(String phResult) {
		this.phResult = phResult;
	}

	public String getCationResult() {
		return cationResult;
	}

	public void setCationResult(String cationResult) {
		this.cationResult = cationResult;
	}

	public String getCalculateBulkResult() {
		return calculateBulkResult;
	}

	public void setCalculateBulkResult(String calculateBulkResult) {
		this.calculateBulkResult = calculateBulkResult;
	}

	public List<String> getSoilTextureClassList() {
		return soilTextureClassList;
	}

	public void setSoilTextureClassList(List<String> soilTextureClassList) {
		this.soilTextureClassList = soilTextureClassList;
	}
	
	
	
	
	
}

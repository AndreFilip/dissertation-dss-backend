package gr.athenstech.dissertation.decisionsupportsystem.dto;

public class SoilResultsResponse {
	private String carbonResult;
	private String phResult;
	private String cationResult;
	private String calculateBulkResult;
	private String soilTextureClass;
	
	public SoilResultsResponse() {}
	
	public SoilResultsResponse(String carbonResult, String phResult, String cationResult, String calculateBulkResult,
			String soilTextureClass) {
		this.carbonResult = carbonResult;
		this.phResult = phResult;
		this.cationResult = cationResult;
		this.calculateBulkResult = calculateBulkResult;
		this.soilTextureClass = soilTextureClass;
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

	public String getSoilTextureClass() {
		return soilTextureClass;
	}

	public void setSoilTextureClass(String soilTextureClass) {
		this.soilTextureClass = soilTextureClass;
	}
	
	
	
}

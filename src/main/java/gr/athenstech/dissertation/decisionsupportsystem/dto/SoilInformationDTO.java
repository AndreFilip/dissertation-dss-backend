package gr.athenstech.dissertation.decisionsupportsystem.dto;

public class SoilInformationDTO {	
	private double bulk;
	private double carbon;
	private double cation;
	private double clay;
	private double ph;
	private double sand;
    private double silt;   
        
    public SoilInformationDTO() {}
    
	public SoilInformationDTO(double carbon, double bulk, double ph, double cation, double silt, double clay,
			double sand) {	}
	
	public double getCarbon() {
		return carbon;
	}
	public void setCarbon(double carbon) {
		this.carbon = carbon;
	}
	public double getBulk() {
		return bulk;
	}
	public void setBulk(double bulk) {
		this.bulk = bulk;
	}
	public double getPh() {
		return ph;
	}
	public void setPh(double ph) {
		this.ph = ph;
	}
	public double getCation() {
		return cation;
	}
	public void setCation(double cation) {
		this.cation = cation;
	}
	public double getSilt() {
		return silt;
	}
	public void setSilt(double silt) {
		this.silt = silt;
	}
	public double getClay() {
		return clay;
	}
	public void setClay(double clay) {
		this.clay = clay;
	}
	public double getSand() {
		return sand;
	}
	public void setSand(double sand) {
		this.sand = sand;
	}
	
	@Override
	public String toString() {
		return "SoilInformationDTO [carbon=" + carbon + ",\n bulk=" + bulk + ",\n ph=" + ph + ",\n cation=" + cation
				+ ",\n silt=" + silt + ",\n clay=" + clay + ",\n sand=" + sand + "]";
	}
    
    
}

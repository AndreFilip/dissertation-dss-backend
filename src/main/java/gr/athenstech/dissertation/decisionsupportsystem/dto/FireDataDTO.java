package gr.athenstech.dissertation.decisionsupportsystem.dto;

public class FireDataDTO {
	
	private double temp;
	private double rhum;
	private double wind;
	private double prcp;
	private int mth;	
		
	public FireDataDTO() {
	}
	
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getRhum() {
		return rhum;
	}
	public void setRhum(double rhum) {
		this.rhum = rhum;
	}
	public double getWind() {
		return wind;
	}
	public void setWind(double wind) {
		this.wind = wind;
	}
	public double getPrcp() {
		return prcp;
	}
	public void setPrcp(double prcp) {
		this.prcp = prcp;
	}
	public int getMth() {
		return mth;
	}
	public void setMth(int mth) {
		this.mth = mth;
	}
	
	
	@Override
	public String toString() {
		return "FireDataDTO [temp=" + temp + ", rhum=" + rhum + ", wind=" + wind + ", prcp=" + prcp + ", mth=" + mth
				+ "]";
	}

}

package gr.athenstech.dissertation.decisionsupportsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class SoilData {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	 private String typeOfSoil;
	 private String subTypeOfSoil;
	 private String texture;
	 @Lob
	 private String informationSoil;
	 
	 public SoilData() {
		 
	 }
	 
	public SoilData(String typeOfSoil, String subTypeOfSoil, String texture, String informationSoil) {
		super();
		this.typeOfSoil = typeOfSoil;
		this.subTypeOfSoil = subTypeOfSoil;
		this.texture = texture;
		this.informationSoil = informationSoil;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeOfSoil() {
		return typeOfSoil;
	}
	public void setTypeOfSoil(String typeOfSoil) {
		this.typeOfSoil = typeOfSoil;
	}
	public String getSubTypeOfSoil() {
		return subTypeOfSoil;
	}
	public void setSubTypeOfSoil(String subTypeOfSoil) {
		this.subTypeOfSoil = subTypeOfSoil;
	}
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public String getInformationSoil() {
		return informationSoil;
	}
	public void setInformationSoil(String informationSoil) {
		this.informationSoil = informationSoil;
	}
	
	@Override
	public String toString() {
		return "SoilData [id=" + id + ", typeOfSoil=" + typeOfSoil + ", subTypeOfSoil=" + subTypeOfSoil + ", texture="
				+ texture + ", informationSoil=" + informationSoil + "]";
	}
	 
	 
}

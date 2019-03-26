package gr.athenstech.dissertation.decisionsupportsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Color {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;	 
	 private int r;
	 private int b;
	 private int g;
	 
	 public Color() { }
	 
	 public Color(Long id, int r, int b, int g) {
		this.id = id;
		this.r = r;
		this.b = b;
		this.g = g;
	 }
	 
	 public Color(int r, int b, int g) {
			this.r = r;
			this.b = b;
			this.g = g;
		 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	@Override
	public String toString() {
		return "Color [id=" + id + ", r=" + r + ", b=" + b + ", g=" + g + "]";
	}	

}

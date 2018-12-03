package gr.athenstech.dissertation.decisionsupportsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Color {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 public Long id;	 
	 public int r;
	 public int b;
	 public int g;
	 
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + b;
//		result = prime * result + g;
//		result = prime * result + r;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Color other = (Color) obj;
//		if (b != other.b)
//			return false;
//		if (g != other.g)
//			return false;
//		if (r != other.r)
//			return false;
//		return true;
//	}

	 

}

//package gr.athenstech.dissertation.decisionsupportsystem.model;
//
//import gr.athenstech.dissertation.decisionsupportsystem.model.Color;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
//import javax.persistence.OneToOne;
//
//@Entity
//public class Graphic {
//	
//	 @Id
//	 @GeneratedValue(strategy=GenerationType.AUTO)
//	 public Long id;	 
//	 public String title;
//	 public String comments;
//	 public double latitude;
//	 public double longitude;
//	 @Lob
//	 public String paths;
//	 public String type;
//	 public String idReturned;
//
//
//	 @OneToOne(cascade = CascadeType.ALL)
//	 @JoinColumn(name = "color_id")
//	 public Color color;
//	 
//	 public Graphic() {}
//	 
//	public Graphic(Long id, String title, String comments, double latitude, double longitude
//			, Color color, String paths, String type,  String idReturned
//			) {
//		this.id = id;
//		this.title = title;
//		this.comments = comments;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.color = color;
//		this.paths = paths;
//		this.type = type;
//		this.idReturned = idReturned;
//
//	}
//	
//	public Graphic(String title, String comments, double latitude, double longitude
//			, Color color, String paths, String type,  String idReturned
//			) {
//		this.title = title;
//		this.comments = comments;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.color = color;
//		this.paths = paths;
//		this.type = type;
//		this.idReturned = idReturned;
//
//	}
//	
//	
//	
//	public String getIdReturned() {
//		return idReturned;
//	}
//
//	public void setIdReturned(String idReturned) {
//		this.idReturned = idReturned;
//	}
//
//	public String getPaths() {
//		return paths;
//	}
//
//	public void setPaths(String paths) {
//		this.paths = paths;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getComments() {
//		return comments;
//	}
//
//	public void setComments(String comments) {
//		this.comments = comments;
//	}
//
//	public double getLatitude() {
//		return latitude;
//	}
//
//	public void setLatitude(double latitude) {
//		this.latitude = latitude;
//	}
//
//	public double getLongitude() {
//		return longitude;
//	}
//
//	public void setLongitude(double longitude) {
//		this.longitude = longitude;
//	}
//
//	public Color getColor() {
//		return color;
//	}
//
//	public void setColor(Color color) {
//		this.color = color;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
//		Graphic other = (Graphic) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//	
//	
//	
//
////	@Override
////	public int hashCode() {
////		final int prime = 31;
////		int result = 1;
////		long temp;
////		if (this.getType().equals("point")) {
////			temp = Double.doubleToLongBits(latitude);
////			result = prime * result + (int) (temp ^ (temp >>> 32));
////			temp = Double.doubleToLongBits(longitude);
////			result = prime * result + (int) (temp ^ (temp >>> 32));
////		} else  {
////			result = prime * result + ((paths == null) ? 0 : paths.hashCode());
////			result = prime * result + ((type == null) ? 0 : type.hashCode());
////		}		
////		return result;
////	}
////
////	@Override
////	public boolean equals(Object obj) {
////		if (this == obj)
////			return true;
////		if (obj == null)
////			return false;
////		if (getClass() != obj.getClass())
////			return false;
////		
////		Graphic other = (Graphic) obj;
////		if (this.getType().equals("point") && other.getType().equals("point")) {
////			if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
////				return false;
////			if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
////				return false;
////			return true;
////		}
////		
////		if (paths == null) {
////			if (other.paths != null)
////				return false;
////		} else if (!paths.equals(other.paths))
////			return false;
////		
////		if (type == null) {
////			if (other.type != null)
////				return false;
////		} else if (!type.equals(other.type))
////			return false;
////		
////		
////		return true;
////	}
//
//	
//	 
//	
//	
//
//}

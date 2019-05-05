package gr.athenstech.dissertation.decisionsupportsystem.model;

import gr.athenstech.dissertation.decisionsupportsystem.model.Color;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="graphics")
public class Graphic {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;	 
	 private String title;
	 private String comments;
	 @Column(nullable = true)
	 private double latitude;
	 @Column(nullable = true)
	 private double longitude;
	 @Lob
	 @Column(nullable = true)
	 private String paths;
	 private String type;
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(nullable = false)
	 private Color color;
	 @ManyToOne
	 @JoinColumn(name="user_id", nullable = true)
	 private User user;
	 
	 public Graphic() {}
	 
	public Graphic(Long id, String title, String comments, double latitude, double longitude
			, Color color, String paths, String type,  String idReturned
			) {
		this.id = id;
		this.title = title;
		this.comments = comments;
		this.latitude = latitude;
		this.longitude = longitude;
		this.color = color;
		this.paths = paths;
		this.type = type;
	}	
	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Graphic [id=" + id + ", title=" + title + ", comments=" + comments + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", paths=" + paths + ", type=" + type + ", color=" + color + "]";
	}
	
	
}

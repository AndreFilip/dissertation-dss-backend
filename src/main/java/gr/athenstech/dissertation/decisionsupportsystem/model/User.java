package gr.athenstech.dissertation.decisionsupportsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(min=3, max = 25)
	@Column(nullable=false, unique=true)
    private String username;
	
	@NotBlank	
	@Column(nullable=false)
    private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval=true,  fetch = FetchType.LAZY)
	private List<Graphic> graphics;
	
	
	public User() {	}

	public User(@NotBlank String username, @NotBlank String password) {
		this.username = username;
		this.password = password;
	}	

	public List<Graphic> getGraphics() {
		return graphics;
	}

	public void setGraphics(List<Graphic> graphics) {
		this.graphics = graphics;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}	
	
}

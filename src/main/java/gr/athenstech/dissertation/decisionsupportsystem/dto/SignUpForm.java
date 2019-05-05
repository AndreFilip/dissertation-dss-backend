package gr.athenstech.dissertation.decisionsupportsystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpForm {
	@NotBlank
	@Size(min=3, max = 25)
    private String username;
    
    @NotBlank
    @Size(min=3, max = 25)
    private String password;

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
		return "SignUpForm [username=" + username + ", password=" + password + "]";
	}
    
    
}

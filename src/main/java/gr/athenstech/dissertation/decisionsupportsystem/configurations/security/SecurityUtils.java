package gr.athenstech.dissertation.decisionsupportsystem.configurations.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import gr.athenstech.dissertation.decisionsupportsystem.model.User;
import gr.athenstech.dissertation.decisionsupportsystem.repositories.UserRepository;

@Component
public class SecurityUtils {
	@Autowired
	private UserRepository userRepository;
	
	public User getCurrentUser() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		if(username != null) {
			Optional<User> userOp = userRepository.findOptionalByUsername(username);
			user = userOp.orElse(null);
		}
		return user;				
	}

}

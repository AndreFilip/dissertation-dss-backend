package gr.athenstech.dissertation.decisionsupportsystem.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.athenstech.dissertation.decisionsupportsystem.configurations.security.JwtProvider;
import gr.athenstech.dissertation.decisionsupportsystem.dto.JwtResponse;
import gr.athenstech.dissertation.decisionsupportsystem.dto.LoginForm;
import gr.athenstech.dissertation.decisionsupportsystem.dto.SignUpForm;
import gr.athenstech.dissertation.decisionsupportsystem.model.User;
import gr.athenstech.dissertation.decisionsupportsystem.repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtProvider jwtProvider;	 	
	
	  @PostMapping("/login")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		  logger.info("LoginForm received: {}", loginRequest.toString());

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	 
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	 
	    String jwt = jwtProvider.generateJwtToken(authentication);
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	 
	    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
	  }
	 
	  @PostMapping("/register")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		 logger.info("SignUpForm received: " + signUpRequest.toString());
		 Map <String,Object> response = new HashMap<>();

	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	    	response.put("result", "Username is already taken!");
	      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    } 
	    // Creating user's account
	    User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
	    userRepository.save(user);
	    
	    Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
		 
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	 
	    String jwt = jwtProvider.generateJwtToken(authentication);
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		    
	    response.put("result", "Username successfully registered!");
	    response.put("credentials", new JwtResponse(jwt, userDetails.getUsername())); 
	    return ResponseEntity.ok(response);
	  }
}

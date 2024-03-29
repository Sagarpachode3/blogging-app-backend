package com.codewithtechsagar.blog.controllers;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithtechsagar.blog.entities.User;
import com.codewithtechsagar.blog.exceptions.ApiException;
import com.codewithtechsagar.blog.payloads.ApiResponse;
import com.codewithtechsagar.blog.payloads.JwtRequest;
import com.codewithtechsagar.blog.payloads.JwtResponse;
import com.codewithtechsagar.blog.payloads.UserDto;
import com.codewithtechsagar.blog.security.JwtTokenHelper;
import com.codewithtechsagar.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        

        JwtResponse response = new JwtResponse();
        response.setToken(token);
        response.setUser(this.mapper.map((User) userDetails, UserDto.class));
        return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
    }

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}

	}
	
	//register new use API
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerNewUser(@RequestBody @Valid UserDto userDto) {
	   /* if (bindingResult.hasErrors()) {
	        // Log or print validation errors
	        System.out.println("Validation errors: " + bindingResult.getAllErrors());
	        // Return a meaningful response for validation errors
	        ApiResponse apiError = new ApiResponse("Validation error", false);
	        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	    }*/

	    try {
	        UserDto registeredUser = this.userService.registerNewUser(userDto);
	        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	    } catch (DataIntegrityViolationException e) {
	        String errorMessage = "Email address '" + userDto.getEmail() + "' is already in use.";
	        ApiResponse apiError = new ApiResponse(errorMessage, false);
	        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	    }
	}

}

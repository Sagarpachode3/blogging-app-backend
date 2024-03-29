package com.codewithtechsagar.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.codewithtechsagar.blog.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	//@NotNull
	//@NotEmpty
	@Size(min = 4, message = "User Name must be minimum of 4 characters !!!")
	private String name;

	@NotEmpty(message = "Email address is required")
	@Email(message = "Email address is not valid !!!")
	private String email;

	//@NotNull
	
	//@Size(min = 8, max = 14, message = "Password lenghth : 8 -14 & Password Must contain - 2 letters in Upper Case)
	//@NotEmpty()	
	@Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,14}$", message = "Password lenghth : 8 -14 & Password Must contain - 2 letters in Upper Case, 1  Special Character (!@#$&*), 2 numerals (0-9), 3 letters in Lower Case\"")
	private String password;

	//@NotNull
	@NotEmpty(message = "About is required")
	private String about;
	
	private Set<RoleDto> roles = new HashSet<RoleDto>();
	
//	@JsonIgnore
//	public String getPassword() {
//		return this.password;
//	}
//	
//	@JsonProperty
//	public void setPassword(String password) {
//		this.password = password;
//	}
}

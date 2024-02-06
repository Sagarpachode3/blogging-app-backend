package com.codewithtechsagar.blog.payloads;

import lombok.Data;

@Data
public class JwtRequest {
	
	private String username;
	
	private String password;

}

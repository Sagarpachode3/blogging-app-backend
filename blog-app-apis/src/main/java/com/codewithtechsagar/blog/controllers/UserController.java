package com.codewithtechsagar.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewithtechsagar.blog.payloads.ApiResponse;
import com.codewithtechsagar.blog.payloads.UserDto;
import com.codewithtechsagar.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST - Create User

	@PostMapping("/")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto) {

		try {
			UserDto createUserDto = this.userService.createUser(userDto);
			return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {

			String errorMessage = "Email address '" + userDto.getEmail() + "' is already in use.";
	        ApiResponse apiError = new ApiResponse(errorMessage, false);
	        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
		}

	}

	// PUT - Update User

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer uid) {

		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}

	// DELETE - delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		this.userService.getUserById(uid);
		this.userService.deleteUser(uid);
		// return new ResponseEntity(Map.of("message", "User deleted Successfully"),
		// HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
	}

	// GET - Get User

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uid) {
		return ResponseEntity.ok(this.userService.getUserById(uid));

	}

	// GET - Get All Users

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}

}

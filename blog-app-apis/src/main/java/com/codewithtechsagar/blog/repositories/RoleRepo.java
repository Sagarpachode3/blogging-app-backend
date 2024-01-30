package com.codewithtechsagar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtechsagar.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	
}

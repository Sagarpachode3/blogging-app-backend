package com.codewithtechsagar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtechsagar.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}

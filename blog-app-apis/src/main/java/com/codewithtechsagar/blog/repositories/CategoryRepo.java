package com.codewithtechsagar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtechsagar.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

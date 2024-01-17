package com.codewithtechsagar.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtechsagar.blog.entities.Category;
import com.codewithtechsagar.blog.entities.Post;
import com.codewithtechsagar.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
}

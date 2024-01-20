package com.codewithtechsagar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithtechsagar.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}

package com.codewithtechsagar.blog.services;

import java.util.List;

import com.codewithtechsagar.blog.entities.Post;
import com.codewithtechsagar.blog.payloads.PostDto;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

	// update
	Post updatePost(PostDto postDto, Integer postId);
 
	// delete
	void deletePost(Integer postId);

	// getSinglePost
	Post getPostById(Integer postId);

	// getAllPosts
	List<Post> getAllPosts();

	// get post by user
	List<PostDto> getPostsByUser(Integer userId);

	// get post by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// search post by keyword
	List<Post> searchPosts(String keyword);

}

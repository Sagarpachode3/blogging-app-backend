package com.codewithtechsagar.blog.services;

import java.util.List;

import com.codewithtechsagar.blog.entities.Post;
import com.codewithtechsagar.blog.payloads.PostDto;
import com.codewithtechsagar.blog.payloads.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);
 
	// delete
	void deletePost(Integer postId);

	// getSinglePost
	PostDto getPostById(Integer postId);

	// getAllPosts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	// get post by user
	List<PostDto> getPostsByUser(Integer userId);

	// get post by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// search post by keyword
	List<PostDto> searchPosts(String keyword);

}

package com.codewithtechsagar.blog.services;

import com.codewithtechsagar.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	
	void deleteComment(Integer commentId);
	
}

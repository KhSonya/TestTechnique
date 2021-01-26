package com.commnunication.blog.rest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	/**
	 * CommentDto id
	 */
    private Integer commentId;
	
	/**
	 * CommentDto content
	 */
	private String content;
	
	/**
	 * CommentDto datePublication
	 */
	private String datePublication;
	
	/**
	 * CommentDto user email
	 */
	private String email;
	
	/**
	 * CommentDto user username
	 */
	private String username;
	/**
	 * CommentDto posteID
	 */
	private Integer posteId;
}

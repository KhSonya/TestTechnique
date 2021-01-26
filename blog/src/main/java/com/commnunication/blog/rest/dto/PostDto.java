package com.commnunication.blog.rest.dto;

import java.util.Date;
import java.util.List;

import com.commnunication.blog.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	
	/**
	 * PostDto id
	 */
    private Integer posteId;
	
	/**
	 * PostDto content
	 */
	private String statut;
	
	/**
	 * PostDto datePublication
	 */
	private String datePublication;
	
	/**
	 * Postdto user email
	 */
	private String email;
	
	/**
	 * Postdto user username
	 */
	private String username;
	
	
	/**
	 * PostDto Comments 
	 */
	private List<CommentDto> comments;
}

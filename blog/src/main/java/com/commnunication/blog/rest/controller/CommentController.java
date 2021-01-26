package com.commnunication.blog.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commnunication.blog.rest.dto.CommentDto;
import com.commnunication.blog.service.CommentService;

@RestController
@RequestMapping("/comments")

public class CommentController {

	@Autowired
	CommentService commentservice;

	@PostMapping()
	public Object createComment(@RequestBody CommentDto commentDto) {
		commentservice.saveComment(commentDto);
		return ResponseEntity.status(HttpStatus.OK).body(commentDto);
	}
	
	@DeleteMapping("/{commentId}")
	public Object deleteComment(@PathVariable Integer commentId, @RequestParam String email) {
		commentservice.deleteComment(commentId, email);
		return ResponseEntity.status(HttpStatus.OK).body(null);

	}
}

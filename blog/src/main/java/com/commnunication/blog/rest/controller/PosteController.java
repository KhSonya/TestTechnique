package com.commnunication.blog.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commnunication.blog.rest.dto.PostDto;
import com.commnunication.blog.service.PostService;

@RestController
@RequestMapping("/posts")
public class PosteController {

	@Autowired
	PostService postService;

	@PostMapping()
	public Object createPost(@RequestBody PostDto post) {
		postService.savePost(post);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
	
	@GetMapping()
	public Object getPosts() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());

	}
	
	@DeleteMapping("/{postId}")
	public Object deletePost(@PathVariable Integer postId, @RequestParam String email) {
		postService.deletePost(postId, email);
		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

}

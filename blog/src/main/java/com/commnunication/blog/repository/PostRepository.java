package com.commnunication.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commnunication.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	/**
	 * 
	 * @return
	 */
	 List<Post> findAllByOrderByDatePublicationDesc();

}

package com.commnunication.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.commnunication.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}

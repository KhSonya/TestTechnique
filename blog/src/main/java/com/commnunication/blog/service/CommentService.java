package com.commnunication.blog.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commnunication.blog.exception.BadRequestException;
import com.commnunication.blog.exception.DataNotFoundException;
import com.commnunication.blog.exception.ForbiddenException;
import com.commnunication.blog.model.Comment;
import com.commnunication.blog.model.Post;
import com.commnunication.blog.model.User;
import com.commnunication.blog.repository.CommentRepository;
import com.commnunication.blog.repository.PostRepository;
import com.commnunication.blog.repository.UserRepository;
import com.commnunication.blog.rest.dto.CommentDto;

@Service
@Transactional
public class CommentService {
	/**
	 * 
	 */
	@Autowired
	CommentRepository commentRepository;

	/**
	 * 
	 */
	@Autowired
	PostRepository postRepository;

	/**
	 * 
	 */
	@Autowired
	UserRepository userRepository;

	public void saveComment(CommentDto commentDto) {
		if (commentDto.getContent() == null || commentDto.getContent().isEmpty()) {
			throw new BadRequestException("MISSING_REQUIRED_DATA", "Ce champ est obligatoire");
		}

		if (commentDto.getEmail() == null || commentDto.getEmail().isEmpty()) {
			throw new BadRequestException("MISSING_REQUIRED_DATA", "Merci de préciser l'utilisateur");
		}
		if (commentDto.getPosteId() == null) {
			throw new BadRequestException("MISSING_REQUIRED_DATA",
					"Merci de préciser le poste auquel appartient ce commentaire");
		}
		Optional<Post> post = postRepository.findById(commentDto.getPosteId());
		if (!post.isPresent()) {
			throw new DataNotFoundException("POST_NOT_FOUND", "Aucun post trouvé avec cet id");

		}
		User user = userRepository.findByEmail(commentDto.getEmail());
		if (user == null) {
			throw new DataNotFoundException("USER_NOT_FOUND", "Aucun utilisateur trouvé avec ce username");
		}

		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDto, comment);
		comment.setDatePublication(new Date());

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String strDate = formatter.format(comment.getDatePublication());
		commentDto.setDatePublication(strDate);

		comment.setUser(user);
		commentDto.setUsername(user.getUsername());
		comment.setPost(post.get());
		
		commentRepository.save(comment);
		commentDto.setCommentId(comment.getCommentId());

	}

	/**
	 * 
	 * @param postId
	 * @param userEmail
	 */
	public void deleteComment(Integer commentId, String userEmail) {

		Optional<Comment> comment = commentRepository.findById(commentId);
		if (!comment.isPresent()) {
			throw new DataNotFoundException("COMMENT_NOT_FOUND", "Comment not found with this id " + commentId);
		}
		Comment commentToDelete = comment.get();
		if (!commentToDelete.getUser().getEmail().equals(userEmail))
			throw new ForbiddenException("USER_UNAUTHORIZED", "User not allowed to delete this comment! " + commentId);
		commentRepository.delete(commentToDelete);
	}
}

package com.commnunication.blog.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.commnunication.blog.repository.PostRepository;
import com.commnunication.blog.repository.UserRepository;
import com.commnunication.blog.rest.dto.CommentDto;
import com.commnunication.blog.rest.dto.PostDto;

@Service
@Transactional
public class PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * Create post 
	 * @param postDto
	 */
	public void savePost(PostDto postDto) {
		if (postDto.getStatut() == null || postDto.getStatut().isEmpty()) {
			throw new BadRequestException("MISSING_REQUIRED_DATA", "Ce champ est obligatoire");
		}

		if (postDto.getEmail() == null || postDto.getEmail().isEmpty()) {
			throw new BadRequestException("MISSING_REQUIRED_DATA", "Merci de préciser l'utilisateur");
		}
		User user = userRepository.findByEmail(postDto.getEmail());
		if (user == null) {
			throw new DataNotFoundException("USER_NOT_FOUND", "Aucun utilisateur trouvé avec ce username");
		}
		Post post = new Post();
		BeanUtils.copyProperties(postDto, post);
		postDto.setUsername(user.getUsername());

		post.setUser(user);
		post.setDatePublication(new Date());
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    String strDate = formatter.format(post.getDatePublication()); 
			postDto.setDatePublication(strDate);

		postRepository.save(post);
		postDto.setPosteId(post.getPosteId());
	}
	
	/**
	 * 
	 * @return
	 */
	public List<PostDto> getPosts(){
		List<Post> posts = postRepository.findAllByOrderByDatePublicationDesc();
		List<PostDto> postsToAffich = new ArrayList<>();
		if(!posts.isEmpty()) {
			for (Post post:posts){
				PostDto postToAffich =new PostDto();
				BeanUtils.copyProperties(post, postToAffich);
				postToAffich.setEmail(post.getUser().getEmail());
				postToAffich.setUsername(post.getUser().getUsername());
				 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				    String strDate = formatter.format(post.getDatePublication()); 
				    postToAffich.setDatePublication(strDate);

				if(post.getComments()!=null && !post.getComments().isEmpty()) {
					List<CommentDto> commentsDto = new ArrayList<>();
					for(Comment comment:post.getComments()) {
						CommentDto commentDto = new CommentDto();
						BeanUtils.copyProperties(comment, commentDto,"post");
						commentDto.setEmail(comment.getUser().getEmail());
						commentDto.setUsername(comment.getUser().getUsername());
						     strDate = formatter.format(comment.getDatePublication()); 
						    commentDto.setDatePublication(strDate);
						commentsDto.add(commentDto);
					}
					postToAffich.setComments(commentsDto);
				}
				postsToAffich.add(postToAffich);
				
			}
		}
		return postsToAffich;

	}
	
	/**
	 * Delete post with its comments
	 * @param postId
	 * @param userEmail
	 */
	public void deletePost(Integer postId, String userEmail) {
		
		Optional<Post> post = postRepository.findById(postId);
		if(!post.isPresent()) {
			throw new DataNotFoundException("POST_NOT_FOUND", "Post not found with this id "+postId);
		}
		Post postToDelete = post.get();
		if(!postToDelete.getUser().getEmail().equals(userEmail))
			throw new ForbiddenException ("USER_UNAUTHORIZED", "User not allowed to delete this post! "+postId);
		postRepository.delete(postToDelete);
	}

}

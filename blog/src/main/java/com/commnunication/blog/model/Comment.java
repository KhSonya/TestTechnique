package com.commnunication.blog.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMENT")
public class Comment {

	/**
	 * Comment id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
	
	/**
	 * Comment content
	 */
	@Column(name="comment")
	private String content;
	
	/**
	 * Comment password
	 */
	@Column(name="DATE_PUBLICATION")
	private Date datePublication;
	
	/**
	 * 
	 */
	@ManyToOne()
	private User user;
	
	/**
	 * 
	 */
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
}


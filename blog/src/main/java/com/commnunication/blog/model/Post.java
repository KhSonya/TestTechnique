package com.commnunication.blog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "POST")
public class Post {
	
	/**
	 * Poste id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer posteId;
	
	/**
	 * Poste content
	 */
	@Column(name="statut")
	private String statut;
	
	/**
	 * User password
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
	@OneToMany(mappedBy = "post",cascade =  CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<Comment> comments;
	

}

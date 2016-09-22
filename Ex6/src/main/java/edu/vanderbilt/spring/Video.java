package edu.vanderbilt.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private long size;
	private String genre;
	private String url;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getSize() {
		return size;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setSize(long size) {
		this.size = size;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}

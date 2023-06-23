package com.example.btlweb.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String name;
	//private List<Book> bookbuy;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	List<Order> orders;
	
	@OneToMany(mappedBy = "user",fetch =FetchType.EAGER )
	List<Review> reviews;
	
	public User() {
		super();
	}

	public User(int id,String username, String password, String name) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.name = name;
		//this.bookbuy = bookbuy;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Book> getBookbuy() {
//		return bookbuy;
//	}
//
//	public void setBookbuy(List<Book> bookbuy) {
//		this.bookbuy = bookbuy;
//	}

}

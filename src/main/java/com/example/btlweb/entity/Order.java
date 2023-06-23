package com.example.btlweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblorder")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="id_book")
	private Book book;
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	private int so_luong;
	public Order() {
		super();
	}
	public Order(int id, int so_luong) {
		super();
		this.id = id;
		this.so_luong = so_luong;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSo_luong() {
		return so_luong;
	}
	public void setSo_luong(int so_luong) {
		this.so_luong = so_luong;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

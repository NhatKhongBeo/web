package com.example.btlweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;

    private Integer danh_gia;
    private String binh_luan;

    public Review() {
    }

    public Review(Integer id, User user, Book book, Integer danh_gia, String binh_luan) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.danh_gia = danh_gia;
        this.binh_luan = binh_luan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getDanh_gia() {
        return danh_gia;
    }

    public void setDanh_gia(Integer danh_gia) {
        this.danh_gia = danh_gia;
    }

    public String getBinh_luan() {
        return binh_luan;
    }

    public void setBinh_luan(String binh_luan) {
        this.binh_luan = binh_luan;
    }

}
package com.example.btlweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btlweb.entity.Review;
import com.example.btlweb.repository.ReviewRepository;

@Service
public class ReviewService {
	ReviewRepository repository;
	
    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }
	public List<Review> findall(){
		return repository.findAll();
		
	}
	
	public List<Review> findByBookId(Integer id_book){
		return repository.findByBookId(id_book);
	}
	
	public void saveReview(Review review) {
		repository.save(review);
	}
}

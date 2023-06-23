package com.example.btlweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.btlweb.entity.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	@Query("select r from Review r where r.book.id= :bookId")
	List<Review> findByBookId(Integer bookId);
}

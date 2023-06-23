package com.example.btlweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.btlweb.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findByUserId( Integer userId);
	
	void deleteById(Integer id);
}

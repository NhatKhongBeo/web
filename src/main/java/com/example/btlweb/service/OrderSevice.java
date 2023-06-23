package com.example.btlweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.btlweb.entity.Order;
import com.example.btlweb.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderSevice {
	@Autowired
	private OrderRepository orderRepository;
	
	public void taodon(Order order) {
		orderRepository.save(order);
	}
	
	public List<Order> findOrderByUserId(Integer id_user){
		return orderRepository.findByUserId(id_user);
	}
	@Transactional
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }
}

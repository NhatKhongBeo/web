package com.example.btlweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.btlweb.entity.User;
import com.example.btlweb.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(Integer id) {
		return userRepository.findById(id);
	}
	
	public User findByUserName(@Param("username") String username) {
		return userRepository.findByUsername(username);
	}
	public boolean checkLogin(String username, String password) {
		User user= findByUserName(username);
		
		if(user!= null&& user.getPassword() != null && user.getPassword().equals(password))
			return true;
		else 
			return false;
	}
	public boolean checkExist(String username) {
		User user =findByUserName(username);
		
		if(user!=null) 
			return false;
		return true;
	}
	public void save(User user) {
		userRepository.save(user);
	}
	
	
}

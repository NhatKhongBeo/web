package com.example.btlweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.btlweb.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByUsername(@Param("username") String username);
}
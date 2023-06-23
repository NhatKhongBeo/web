package com.example.btlweb.repository;

import com.example.btlweb.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query("select b from Book b where lower(b.tieu_de)=lower(:Tieu_de) and lower(b.tac_gia)=lower(:Tac_gia)")
	Book findByTieu_de(String Tieu_de,String Tac_gia);
}

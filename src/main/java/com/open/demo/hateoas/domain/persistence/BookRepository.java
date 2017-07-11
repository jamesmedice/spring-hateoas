package com.open.demo.hateoas.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.open.demo.hateoas.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

   Book findOneByIsbn(String isbn);
 
 }

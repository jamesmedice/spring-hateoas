package com.open.demo.hateoas.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.open.demo.hateoas.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

   Author findOneByHandle(String lastName);
}

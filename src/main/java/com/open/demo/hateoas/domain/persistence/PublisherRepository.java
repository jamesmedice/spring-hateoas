package com.open.demo.hateoas.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.open.demo.hateoas.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
   
   
}

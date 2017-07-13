package com.opencredo.demo.hateoas.api;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.open.demo.hateoas.Application;
import com.open.demo.hateoas.domain.persistence.AuthorRepository;
import com.open.demo.hateoas.domain.persistence.BookRepository;
import com.open.demo.hateoas.domain.persistence.PublisherRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=8880")
@DirtiesContext
public abstract class AbstractControllerTest {

    @Value("${local.server.port}")
    int port;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Before
    public void setUp() {
	RestAssured.port = port;
    }

}

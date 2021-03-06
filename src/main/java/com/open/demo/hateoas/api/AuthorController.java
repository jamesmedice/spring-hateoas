package com.open.demo.hateoas.api;

import static com.open.demo.hateoas.api.ResourceHandlingUtils.entityOrNotFoundException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.open.demo.hateoas.api.resources.AuthorResource;
import com.open.demo.hateoas.api.resources.NewAuthor;
import com.open.demo.hateoas.domain.Author;
import com.open.demo.hateoas.domain.persistence.AuthorRepository;

/**
 * 
 * @author TOSS
 *
 */
@RestController
@ExposesResourceFor(AuthorResource.class)
@RequestMapping(value = "/authors", produces = { MediaType.APPLICATION_JSON_VALUE })
@Transactional
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorResourceAssembler authorResourceAssembler;

    @Autowired
    public AuthorController(final AuthorRepository authorRepository, final AuthorResourceAssembler authorResourceAssembler) {
	this.authorRepository = authorRepository;
	this.authorResourceAssembler = authorResourceAssembler;
    }

    @RequestMapping(value = "/{author_handle}", method = RequestMethod.GET)
    public ResponseEntity<AuthorResource> showAuthor(@PathVariable("author_handle") final String authorHandle) {
	final Author author = entityOrNotFoundException(authorRepository.findOneByHandle(authorHandle));
	final AuthorResource resource = authorResourceAssembler.toResource(author);
	return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Resources<AuthorResource>> listAllAuthors() {
	final Iterable<Author> authors = authorRepository.findAll();
	final Resources<AuthorResource> wrapped = authorResourceAssembler.toEmbeddedList(authors);
	return ResponseEntity.ok(wrapped);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> newAuthor(@RequestBody NewAuthor newAuthor) {

	final Author savedAuthor = authorRepository.save(new Author(newAuthor.getFirstName(), newAuthor.getLastName()));
	final HttpHeaders headers = new HttpHeaders();
	headers.add("Location", authorResourceAssembler.linkToSingleResource(savedAuthor).getHref());
	return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}

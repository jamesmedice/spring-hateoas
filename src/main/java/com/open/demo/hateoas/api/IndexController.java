package com.open.demo.hateoas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.open.demo.hateoas.api.resources.IndexResource;

@RestController
@RequestMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class IndexController {

    private final IndexResourceAssembler indexResourceAssembler;

    @Autowired
    public IndexController(IndexResourceAssembler indexResourceAssembler) {
	this.indexResourceAssembler = indexResourceAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<IndexResource> index() {
	return ResponseEntity.ok(indexResourceAssembler.buildIndex());
    }
}

package com.open.demo.hateoas.api.resources;

import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Relation(value = "publisher", collectionRelation = "publishers")
public class PublisherResource extends ResourceWithEmbeddeds {

    private final String name;

    @JsonCreator
    public PublisherResource(@JsonProperty("name") String name) {
	super();
	this.name = name;
    }

    public String getName() {
	return name;
    }
}

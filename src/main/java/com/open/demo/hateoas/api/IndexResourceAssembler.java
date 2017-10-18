package com.open.demo.hateoas.api;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.stereotype.Service;

import com.open.demo.hateoas.api.resources.AuthorResource;
import com.open.demo.hateoas.api.resources.BookResource;
import com.open.demo.hateoas.api.resources.IndexResource;
import com.open.demo.hateoas.api.resources.PublisherResource;

@Service
public class IndexResourceAssembler {

    private final RelProvider relProvider;
    private final EntityLinks entityLinks;

    @Autowired
    public IndexResourceAssembler(RelProvider relProvider, EntityLinks entityLinks) {
	this.relProvider = relProvider;
	this.entityLinks = entityLinks;
    }

    public IndexResource buildIndex() {
	final List<Link> links = asList(entityLinks.linkToCollectionResource(BookResource.class).withRel(relProvider.getCollectionResourceRelFor(BookResource.class)), entityLinks
		.linkToCollectionResource(AuthorResource.class).withRel(relProvider.getCollectionResourceRelFor(AuthorResource.class)),
		entityLinks.linkToCollectionResource(PublisherResource.class).withRel(relProvider.getCollectionResourceRelFor(PublisherResource.class)));
	final IndexResource resource = new IndexResource("sample_hateoas", "A sample HATEOAS API");
	resource.add(links);
	return resource;
    }
}

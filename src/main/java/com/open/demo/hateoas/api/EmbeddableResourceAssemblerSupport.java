package com.open.demo.hateoas.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public abstract class EmbeddableResourceAssemblerSupport<T, D extends ResourceSupport, C> extends ResourceAssemblerSupport<T, D> {

    protected final RelProvider relProvider;
    protected final EntityLinks entityLinks;
    protected final Class<C> controllerClass;

    public EmbeddableResourceAssemblerSupport(final EntityLinks entityLinks, final RelProvider relProvider, Class<C> controllerClass, Class<D> resourceType) {
	super(controllerClass, resourceType);
	this.entityLinks = entityLinks;
	this.relProvider = relProvider;
	this.controllerClass = controllerClass;
    }

    public List<EmbeddedWrapper> toEmbeddable(Iterable<T> entities) {
	final EmbeddedWrappers wrapper = new EmbeddedWrappers(true);
	final List<D> resources = toResources(entities);
	return resources.stream().map(a -> wrapper.wrap(a)).collect(Collectors.toList());
    }

    public EmbeddedWrapper toEmbeddable(T entity) {
	final EmbeddedWrappers wrapper = new EmbeddedWrappers(false);
	final D resource = toResource(entity);
	return wrapper.wrap(resource);
    }

    public Resources<D> toEmbeddedList(Iterable<T> entities) {
	final List<D> resources = toResources(entities);
	return new Resources<D>(resources, linkTo(controllerClass).withSelfRel());
    }

    public abstract Link linkToSingleResource(T entity);
}

/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.rest.common.resource;

import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class AbstractLinksSupportedResource {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Set<Link> links = new LinkedHashSet<>();

    public Set<Link> getLinks() {
        return links;
    }

    public AbstractLinksSupportedResource addLink(String rel, URI href) {
        links.add(new Link(rel, href.toString()));
        return this;
    }

    public AbstractLinksSupportedResource addSelf(URI href) {
        return addLink("self", href);
    }

    public AbstractLinksSupportedResource addParent(URI href) {
        return addLink("parent", href);
    }
}

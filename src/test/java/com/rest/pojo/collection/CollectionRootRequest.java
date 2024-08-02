package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRootRequest extends CollectionRootBase {
    private CollectionRequest collection;

    public CollectionRootRequest() {
        // Default constructor
    }

    public CollectionRootRequest(CollectionRequest collection) {
        this.collection = collection;
    }

    public CollectionRequest getCollection() {
        return collection;
    }

    public void setCollection(CollectionRequest collection) {
        this.collection = collection;
    }
}

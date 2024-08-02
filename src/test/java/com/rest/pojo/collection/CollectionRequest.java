package com.rest.pojo.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRequest extends CollectionBase {
    private List<FolderRequest> item;

    public CollectionRequest() {
        // Default constructor
    }

    public CollectionRequest(Info info, List<FolderRequest> item) {
        super(info);  // Call to superclass constructor with info parameter
        this.item = item;
    }

    public List<FolderRequest> getItem() {
        return item;
    }

    public void setItem(List<FolderRequest> item) {
        this.item = item;
    }
}

package khemlall.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CollectionRootBase {

    public CollectionRootBase(){
    }
}

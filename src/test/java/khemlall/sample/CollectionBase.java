package khemlall.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.pojo.collection.Info;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CollectionBase {
    Info info;

    public CollectionBase(){

    }

    public CollectionBase(Info info){
        this.info = info;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

package khemlall.sample;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRequest extends RequestBase{
    private String url;

    public RequestRequest(){

    }
/*
    public RequestRequest(String url, String method, List<Header> header, com.rest.pojo.collection.Body body, String description) {
        super(method, header, body, description);
        this.url = url;
    }
*/
    public void setUrl(String url) {
        this.url = url;
    }
}

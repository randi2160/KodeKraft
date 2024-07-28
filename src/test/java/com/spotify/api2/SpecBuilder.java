package com.spotify.api2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI"))
                //.setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder()
                //.setBaseUri("https://accounts.spotify.com")
                .setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL)
                .build();
    }


    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                //.expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

}

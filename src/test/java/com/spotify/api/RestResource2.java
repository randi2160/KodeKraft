package com.spotify.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.api.Route.API;
import static com.spotify.api.Route.TOKEN;
import static com.spotify.api2.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource2 {

    public static Response post(String path, String token,Object requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization","Bearer " + token)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract().response();

    }

    public static Response postAccount(HashMap<String,String> formParams){
       return
                given(getAccountRequestSpec()).
                formParams(formParams).
                when().post(API+ TOKEN)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }
    public static Response get(String path, String token){
    return given(getRequestSpec())
            .header("Authorization","Bearer " + token)
            .when().get(path)
            .then().spec(getResponseSpec()).
            extract().response();
    }

    public static Response update(String path, String token,Object requestPlaylist){
        return given(getRequestSpec())
                .body(requestPlaylist)
                .header("Authorization","Bearer " + token)
                .body(requestPlaylist)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract().response();
    }
}

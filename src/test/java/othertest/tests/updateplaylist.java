package othertest.tests;

import com.spotify.pojo.Playlist;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class updateplaylist {
    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    String access_token = "BQDr2EL8RIjkMaI05EqFoPbtKolkM7GKAm5_drIhJhTqVa2SnD3QZWAbacXfO38xYnwkz5-XPvKI_Rjd2x11-B8hkClxFxHlbVash3v5-5Wezu6PSvQIEMDrG_k3vYuFc9C2XsJtZvl4WTErqMSK0qa_HISIvkYgFCHmZEdvau8gXlZq6CUup7fKNgeo1mrrILDGqtE33NkDbxDTH_DIUCSC6gzqPPUiAgwPi4FTK544Vd1vXNQvxVwkOheMhZY2WN54-sKs6uYsAEYUGSOOCQ";
    String refresh_token="";

    @BeforeClass
    public void beforeClass(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .addHeader("Authorization", "Bearer " + access_token)
                .setBasePath("/v1")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                //.expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void ShouldBeAbleToCreateAPlayList(){
/*
        Playlist requestPlaylist = new Playlist();
       requestPlaylist.setName("New Playlist");
        requestPlaylist.setDescription("New playlist description");
        requestPlaylist.setPublic("false");

 */
        String payload ="{\n" +
                "    \"name\": \"Updated Playlist Name\",\n" +
                "    \"description\": \"Updated playlist description\",\n" +
                "    \"public\": false\n" +
                "}";

        given()
                .spec(requestSpecification)
                .body(payload)
                .when().post("/users/31blole434cqahr3jzhoygkatahi/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(201)
                .extract().response().as(Playlist.class);

        //assertThat(requestPlaylist.getName(),equalTo((requestPlaylist.getName())));
        // assertThat(requestPlaylist.getDescription(),equalTo((requestPlaylist.getDescription())));
        //assertThat(requestPlaylist.get_public(),equalTo((requestPlaylist.get_public())));
    }

    @Test
    public void ShouldBeAbleToGetAPlayList(){

        given()
                .spec(requestSpecification)
                .when().get("/playlists/6yBp1uW6DD45tzIOFHnVck")
                .then().spec(responseSpecification)
                .assertThat().statusCode(200)
                .body("name", equalTo("Updated Playlist Name"),
                        "description", equalTo("Updated playlist description"),
                        "public", equalTo(true));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlayList(){
        String payload ="{\n" +
                "    \"name\": \"Updated Playlist Name\",\n" +
                "    \"description\": \"Updated playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .body(payload)
                .when().put("/playlists/6yBp1uW6DD45tzIOFHnVck")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(200);
    }

    /*Negative Scenario
    1. create a playlist without name 404
    2. Create a Playlist with Expired Token  401 Unauthorized.
     */


    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithoutName(){
        String payload ="{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given()
                .spec(requestSpecification)
                .body(payload)
                .when().post("/users/31blole434cqahr3jzhoygkatahi/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(400)
                .body("error.status", equalTo(400),
                        "error.message", equalTo("Missing required field: name"));
    }


    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken(){
        String payload ="{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"New playlist description\",\n" +
                "    \"public\": false\n" +
                "}";
        given().
                baseUri("https://api.spotify.com")
                .header("Authorization", "Bearer " + "dummyvalue")
                .basePath("/v1")
                .contentType(ContentType.JSON)
                .log().all()
                .body(payload)
                .when().post("/users/31blole434cqahr3jzhoygkatahi/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(401)
                .body("error.status", equalTo(401),
                        "error.message", equalTo("Invalid access token"));
    }

}

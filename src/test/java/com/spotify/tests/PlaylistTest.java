package com.spotify.tests;

import com.spotify.api.StatusCode;
import com.spotify.api.applicationApi2.PlaylistApi;
import com.spotify.pojo2.Error;
import com.spotify.pojo2.Playlist;
import com.spotify.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.utils.FakerUtils.generateDescription;
import static com.spotify.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest {



    @Story("Create a playlist story")
    @Link("https://api.spotify.com")
    @Link(name = "allure", type = "mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Description("this is the description")
    @Test(description = "should be able to create a playlist")
    public void ShouldBeAbleToCreateAPlayList(){
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(),false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201.code);
        assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);
    }

    @Test
    public void ShouldBeAbleToGetAPlayList(){
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(),true);

        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.code);
        assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);

    }

    @Test
    public void ShouldBeAbleToUpdateAPlayList(){
        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name","Updated playlist description",false);
        Response response = PlaylistApi.update(requestPlaylist,DataLoader.getInstance().getUpdatePlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.code);

    }

    /*Negative Scenario
    1. create a playlist without name 404
    2. Create a Playlist with Expired Token  401 Unauthorized.
     */


    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithoutName(){
        Playlist requestPlaylist = playlistBuilder("",generateDescription(),false);
        Response response = PlaylistApi.post(requestPlaylist);
        //assertThat(response.statusCode(),equalTo(400));
        assertStatusCode(response.statusCode(),StatusCode.CODE_400.code);
        assertPlaylistErrorEqual(response.as(Error.class),StatusCode.CODE_400.code,"Missing required field: name");


    }

    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken(){
        String invalid_Token ="12451";
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(),false);
        Response response = PlaylistApi.post(invalid_Token,requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_401.code);
        assertPlaylistErrorEqual(response.as(Error.class),StatusCode.CODE_401.code,"Invalid access token");
    }
    public Playlist playlistBuilder(String name, String Description, boolean _public){
        return Playlist.builder().name(name).description(Description)._public(_public).build();
    }

    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(requestPlaylist.getName(),equalTo((requestPlaylist.getName())));
        assertThat(requestPlaylist.getDescription(),equalTo((requestPlaylist.getDescription())));
        assertThat(requestPlaylist.getPublic(),equalTo((requestPlaylist.getPublic())));
    }

    //Validate Error
    public void assertPlaylistErrorEqual(Error responseError, int expectedStatusCode, String ExpectedMessage){
        assertThat(responseError.getError().getStatus(),equalTo((expectedStatusCode)));
        assertThat(responseError.getError().getMessage(),equalTo((ExpectedMessage)));
    }
    //Assert status Code
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode){
        assertThat(actualStatusCode,equalTo((expectedStatusCode)));
    }
}

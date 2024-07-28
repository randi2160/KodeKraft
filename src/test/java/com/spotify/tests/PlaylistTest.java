package com.spotify.tests;

import com.spotify.api.applicationApi2.PlaylistApi;
import com.spotify.pojo2.Error;
import com.spotify.pojo2.Playlist;
import com.spotify.utils.DataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTest {
    //ResponseSpecification responseSpecification;
    //RequestSpecification requestSpecification;
    //String refresh_token="";

    @Test
    public void ShouldBeAbleToCreateAPlayList(){

        Playlist requestPlaylist = new Playlist()
                .setName("New Playlist")
                .setDescription("New playlist description")
                .setPublic(false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(),equalTo(201));
        //let desearlize the json
        Playlist ResponsePlaylist = response.as(Playlist.class);
        assertThat(requestPlaylist.getName(),equalTo((requestPlaylist.getName())));
        assertThat(requestPlaylist.getDescription(),equalTo((requestPlaylist.getDescription())));
        assertThat(requestPlaylist.getPublic(),equalTo((requestPlaylist.getPublic())));
    }

    @Test
    public void ShouldBeAbleToGetAPlayList(){
        Playlist requestPlaylist = new Playlist()
                .setName("Updated Playlist Name")
                .setDescription("Updated playlist description")
                .setPublic(true);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertThat(response.statusCode(),equalTo(200));
        Playlist ResponsePlaylist = response.as(Playlist.class);

        assertThat(requestPlaylist.getName(),equalTo((requestPlaylist.getName())));
        assertThat(requestPlaylist.getDescription(),equalTo((requestPlaylist.getDescription())));
        assertThat(requestPlaylist.getPublic(),equalTo((requestPlaylist.getPublic())));
    }

    @Test
    public void ShouldBeAbleToUpdateAPlayList(){
        Playlist requestPlaylist = new Playlist()
                .setName("Updated Playlist Name")
                .setDescription("Updated playlist description")
                .setPublic(false);
        Response response = PlaylistApi.update(requestPlaylist,DataLoader.getInstance().getUpdatePlaylistId());
        assertThat(response.statusCode(),equalTo(200));
    }

    /*Negative Scenario
    1. create a playlist without name 404
    2. Create a Playlist with Expired Token  401 Unauthorized.
     */


    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithoutName(){
        Playlist requestPlaylist = new Playlist()
                .setName("")
                .setDescription("New playlist description")
                .setPublic(false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertThat(response.statusCode(),equalTo(400));
        Error  error =response.as(Error.class);
        assertThat(error.getError().getStatus(),equalTo((400)));
        assertThat(error.getError().getMessage(),equalTo(("Missing required field: name")));

    }

    @Test
    public void ShouldNotBeAbleToCreateAPlayListWithExpiredToken(){
        String invalid_Token ="12451";
        Playlist requestPlaylist = new Playlist()
                .setName("New playlist")
                .setDescription("New playlist description")
                .setPublic(false);

        Response response = PlaylistApi.post(invalid_Token,requestPlaylist);
        assertThat(response.statusCode(),equalTo(401));
        Error  error = response.as(Error.class);
        assertThat(error.getError().getStatus(),equalTo((401)));
        assertThat(error.getError().getMessage(),equalTo(("Invalid access token")));



    }

}

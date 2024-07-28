package com.spotify.api.applicationApi2;

import com.spotify.api.RestResource2;
import com.spotify.pojo2.Playlist;
import com.spotify.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.api.Route.PLAYLISTS;
import static com.spotify.api.Route.USERS;
import static com.spotify.api.TokenManager2.getToken;


public class PlaylistApi {
   // static String access_token = "BQDHb_JmLDjf55guWR4IKfMNC_joDoOFVRb_Ln0A6ZYL8nY_S7p4uoYliyj6FxWVLGkeETG6g-16KszjfabOoCdN6BZrCWdZEot018raZ_CBRbA01qpGOnNVQyEQnf0je9YhQFlCKN8sSr8aOzYSCkllE-7qymsZtvXt-vYklJq2J4HxSyDNQccbWMN3wVrEizPptDg7Ydx-3GFDkzfUW6xHnw4MMri5E_pwiory5v2HGN5Yji8xdZq-5rRNanwb1aghaHH2OOf2adFZWpwjjQ";

    public static Response post(Playlist requestPlaylist){
        return RestResource2.post(USERS +"/" + ConfigLoader.getInstance().getUser() + PLAYLISTS,getToken(),requestPlaylist);
    }


        public static Response post(String token,Playlist requestPlaylist){
            return RestResource2.post(USERS +"/"+ ConfigLoader.getInstance().getUser()+ PLAYLISTS,token,requestPlaylist);
        }

    public static Response get(String playlistId){
        return RestResource2.get(PLAYLISTS + "/"+ playlistId,getToken());

    }

    public static Response update(Playlist requestPlaylist, String playlistId){
        return RestResource2.update(PLAYLISTS + "/"+ playlistId ,getToken(),requestPlaylist);
    }
}

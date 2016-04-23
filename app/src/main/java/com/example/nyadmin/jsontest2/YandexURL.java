package com.example.nyadmin.jsontest2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nyadmin on 22.04.2016.
 */
public interface YandexURL {

    String URL_BASE="download.cdn.yandex.net/";
    String URL_ADD="mobilization-2016/artists.json";

    int CONNECT_TIMEOUT = 15;
    int WRITE_TIMEOUT = 60;
    int TIMEOUT = 60;

    @GET(URL_ADD)
    Call<List<Artist>> getArtistsData();

}

package com.yandex.mobilization.ymapp.db;

import java.util.ArrayList;


public class Artist {
    private long id;
    private String name;
    private ArrayList<String> genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private Cover cover;

    private static class Cover {
        String small;
        String big;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Artist no ").append(id)
                .append(", Name: ").append(name)
                .append(", Genres: ").append(genres)
                .append(", Tracks: ").append(tracks)
                .append(", Albums: ").append(albums)
                .append(", Link: ").append(link)
                .append(", Description: ").append(description)
                .append(", Small cover: ").append(cover.small)
                .append(", Big cover: ").append(cover.big)
                .toString();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public int getTracks() {
        return tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverBig() {
        return cover.big;
    }

    public String getCoverSmall() {
        return cover.small;
    }

}

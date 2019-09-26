package com.example.movitest;

import java.io.Serializable;

public class Movie implements Serializable {
    private String id;
    private String title;
    private String poset_path;
    private String overview;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoset_path() {
        return poset_path;
    }

    public void setPoset_path(String poset_path) {
        this.poset_path = poset_path;
    }
}

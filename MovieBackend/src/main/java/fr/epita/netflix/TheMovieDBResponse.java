package fr.epita.netflix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TheMovieDBResponse {
    private Integer page;
    private ArrayList<PopularMovieDBResult> results;

    public TheMovieDBResponse(Integer page, ArrayList<PopularMovieDBResult> results) {
        this.page = page;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<PopularMovieDBResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<PopularMovieDBResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "TheMovieDBResponse{" +
                "page=" + page +
                ", results=" + results +
                '}';
    }
}


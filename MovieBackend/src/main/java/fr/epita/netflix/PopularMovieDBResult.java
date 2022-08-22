package fr.epita.netflix;

import java.util.ArrayList;
import java.util.Date;

public class PopularMovieDBResult {
    private String title;

    private Integer id;
    private Date release_date;

    private ArrayList<Integer> genre_ids;

    private String overview;

    private Float vote_average;

    public void setTitle(String original_title) {
        this.title = original_title;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    private String backdrop_path;

    public PopularMovieDBResult(String title, Date release_date) {
        this.title = title;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getVote_average() {
        return vote_average;
    }

    public void setVote_average(Float vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public String toString() {
        return "TheMovieDBResult{" +
                "title='" + title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                '}';
    }

}

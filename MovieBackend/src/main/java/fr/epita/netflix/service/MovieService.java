package fr.epita.netflix.service;

import fr.epita.netflix.datamodel.Movie;

import java.util.List;

public interface MovieService {
    public Movie findById(Long id);
    public List<Movie> findAll();
    public Movie save(Movie movie);

    public List<Movie> getTopRated();

    public List<Movie> getPopular();

    public List<Movie> getUpcoming();

    public List<Movie> getByGenre(String genre);
}

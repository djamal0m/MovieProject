package fr.epita.netflix.service;

import fr.epita.netflix.dao.MovieDao;
import fr.epita.netflix.datamodel.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieDao movieDao;

    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    public Movie findById(Long id) {
        return movieDao.findById(id).get();
    }

    public Movie save(Movie movie) {
        return movieDao.saveAndFlush(movie);
    }

    public List<Movie> getTopRated() {
        Page<Movie> page = movieDao.findAll(
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "rating")));
        return page.getContent();
    }

    public List<Movie> getPopular() {
        Page<Movie> page = movieDao.findAll(
                PageRequest.of(2, 10));
        return page.getContent();
    }

    public List<Movie> getUpcoming() {
        java.util.Date date = new java.util.Date();
        Page<Movie> page = movieDao.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "releaseDate")));
        return page.getContent();
    }

    public List<Movie> getByGenre(String genre) {
        List<Movie> movies = movieDao.findMoviesByGenre(genre);
        return movies;
    }
}

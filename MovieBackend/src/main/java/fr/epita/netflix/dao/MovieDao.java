package fr.epita.netflix.dao;

import fr.epita.netflix.datamodel.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MovieDao extends JpaRepository<Movie, Long> {
    List<Movie> findMoviesByReleaseDateAfter(Date releaseDate);
    List<Movie> findMoviesByGenre(String genre);
}

package fr.epita.netflix;

import fr.epita.netflix.datamodel.Movie;

import fr.epita.netflix.datamodel.User;
import fr.epita.netflix.service.MovieService;
import fr.epita.netflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/netflix")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.findAll();

    }

    @GetMapping("movies/liked/user/{idUser}")
    public List<Movie> getLikedMoviesByUser(
            @PathVariable("idUser") Long idUser
    ) {
        User user = userService.findById(idUser);
        List<Movie> result = new ArrayList<>();
        if (user != null) {
            result.addAll(user.getLikedMovies());
        }
        return result;
    }



    @GetMapping("/movies/top-rated")
    public List<Movie> getTopRated() {
        return movieService.getTopRated();
    }

    @GetMapping("/movies/popular")
    public List<Movie> getPopular() {
        return movieService.getPopular();
    }

    @GetMapping("/movies/upcoming")
    public List<Movie> getUpcoming() {
        return movieService.getUpcoming();
    }

    @GetMapping("/movies/{genre}")
    public List<Movie> getByGenre(
            @PathVariable("genre") String genre
    ) {
        return movieService.getByGenre(genre);
    }
}


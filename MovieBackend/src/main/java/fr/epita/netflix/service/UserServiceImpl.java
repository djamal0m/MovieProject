package fr.epita.netflix.service;


import com.google.gson.Gson;
import fr.epita.netflix.HttpRequest;
import fr.epita.netflix.TheMovieDBResponse;
import fr.epita.netflix.PopularMovieDBResult;
import fr.epita.netflix.dao.*;
import fr.epita.netflix.datamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private ContactDao contactDao;

    @Autowired
    private UserHasLikedMovieDao userHasLikedMovieDao;

    @Autowired
    private UserHasSeenMovieDao userHasSeenMovieDao;

    @Autowired
    private MovieDao movieDao;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    public User save(User user) {
        return userDao.saveAndFlush(user);
    }

    public User signup(User user) {
        Contact contact = user.getContact();

        if (contact != null) {
            System.out.println(contact);
            Contact createdContact = contactDao.saveAndFlush(contact);
            if (contact.getAddresses() != null) {
                Set<Address> addresses = contact.getAddresses();
                for(Address address : contact.getAddresses()) {
                    address.setIdContact(createdContact.getId());
                }
                addressDao.saveAllAndFlush(contact.getAddresses());
            }
            user.setIdContact(createdContact.getId());
        }
        return userDao.saveAndFlush(user);
    }
    public Contact findContactByEmail(String email) {
        return contactDao.findByEmail(email);
    }

    public void userHasSeenMovie(Long idUser, Long idMovie) {
        UserHasSeenMovie userHasSeenMovie = new UserHasSeenMovie();
        userHasSeenMovie.setIdUser(idUser);
        userHasSeenMovie.setIdMovie(idMovie);
        userHasSeenMovieDao.saveAndFlush(userHasSeenMovie);
    }

    public void userHasLikedMovie(Long idUser, Long idMovie) {
        UserHasLikedMovie userHasLikedMovie = new UserHasLikedMovie();
        userHasLikedMovie.setIdUser(idUser);
        userHasLikedMovie.setIdMovie(idMovie);
        userHasLikedMovieDao.saveAndFlush(userHasLikedMovie);
    }

    @Transactional
    public void userHasDislikedMovie(Long idUser, Long idMovie) {
        userHasLikedMovieDao.deleteUserHasLikedMovieByIdUserAndIdMovie(idUser, idMovie);
    }

    @Transactional
    public void seedMovies() {
        String popularMoviesResult = HttpRequest.executeGet("https://api.themoviedb.org/3/movie/popular?api_key=53716044cdb424128844cfdd43616d0e&language=en-US&page=1", "");
        ArrayList<PopularMovieDBResult>  popularMovies = new Gson().fromJson(popularMoviesResult, TheMovieDBResponse.class).getResults();
        String topRatedMoviesResult = HttpRequest.executeGet("https://api.themoviedb.org/3/movie/top_rated?api_key=53716044cdb424128844cfdd43616d0e&language=en-US&page=1", "");
        ArrayList<PopularMovieDBResult>  topRatedMovies = new Gson().fromJson(topRatedMoviesResult, TheMovieDBResponse.class).getResults();
        String upcomingMoviesResult = HttpRequest.executeGet("https://api.themoviedb.org/3/movie/upcoming?api_key=53716044cdb424128844cfdd43616d0e&language=en-US&page=1", "");
        ArrayList<PopularMovieDBResult>  upcomingMovies = new Gson().fromJson(upcomingMoviesResult, TheMovieDBResponse.class).getResults();
        ArrayList<PopularMovieDBResult> movies = new ArrayList<>(topRatedMovies);
        movies.addAll(popularMovies);
        movies.addAll(upcomingMovies);
        HashMap<Integer, String> idGenreToName = new HashMap<>();
        idGenreToName.put(28, "Action");
        idGenreToName.put(12, "Adventure");
        idGenreToName.put(16, "Animation");
        idGenreToName.put(35, "Comedy");
        idGenreToName.put(80, "Crime");
        idGenreToName.put(99, "Documentary");
        idGenreToName.put(18, "Drama");
        idGenreToName.put(10751, "Family");
        idGenreToName.put(14, "Fantasy");
        idGenreToName.put(36, "History");
        idGenreToName.put(27, "Family");
        idGenreToName.put(10402, "Music");
        idGenreToName.put(9648, "Mystery");
        idGenreToName.put(10749, "Romance");
        idGenreToName.put(878, "Science Fiction");
        idGenreToName.put(10770, "TV Movie");
        idGenreToName.put(53, "Thriller");
        idGenreToName.put(10752, "War");
        idGenreToName.put(37, "Western");

        ArrayList<Movie> moviesToInsert = new ArrayList<>();
        for(PopularMovieDBResult movie : movies) {
            Movie movieToInsert = new Movie();
            movieToInsert.setTitle(movie.getTitle());
            movieToInsert.setImgUrl("https://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path());
            movieToInsert.setReleaseDate(movie.getRelease_date());
            movieToInsert.setGenre(idGenreToName.get(movie.getGenre_ids().get(0)));
            movieToInsert.setOverview(movie.getOverview());
            movieToInsert.setRating(movie.getVote_average());
            moviesToInsert.add(movieToInsert);
        }
        movieDao.saveAllAndFlush(moviesToInsert);
    }
}

package fr.epita.netflix.service;

import fr.epita.netflix.datamodel.Contact;
import fr.epita.netflix.datamodel.User;

import java.util.List;

public interface UserService {
    public User findById(Long id);
    public List<User> findAll();
    public User save(User user);

    public User signup(User user);
    public Contact findContactByEmail(String email);

    public void userHasSeenMovie(Long idUser, Long idMovie);

    public void userHasLikedMovie(Long idUser, Long idMovie);

    public void userHasDislikedMovie(Long idUser, Long idMovie);

    public void seedMovies();

}

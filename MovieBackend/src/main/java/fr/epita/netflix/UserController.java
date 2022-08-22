package fr.epita.netflix;

import fr.epita.netflix.datamodel.Contact;
import fr.epita.netflix.datamodel.User;
import fr.epita.netflix.exceptions.BadRequestAlertException;
import fr.epita.netflix.exceptions.ResourceNotFoundException;
import fr.epita.netflix.service.UserService;
import fr.epita.netflix.service.UserServiceImpl;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/netflix")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users =
                userService.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginBody loginBody) throws URISyntaxException {

        Contact foundContact = userService.findContactByEmail(loginBody.getEmail());


        if (foundContact != null &&  foundContact.getUser() != null && loginBody.getPassword().equals(foundContact.getUser().getPassword())) {
            return new ResponseEntity<>(foundContact.getUser(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        if (user.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID");
            // Lowercase the user login before comparing with database
        } else {
            return new ResponseEntity<User>(userService.signup(user), HttpStatus.OK);
        }
    }

    @PostMapping("/users/{idUser}/seen/movie/{idMovie}")
    public ResponseEntity<User> userHasSeenMovie(
            @PathVariable("idUser") Long idUser,
            @PathVariable("idMovie") Long idMovie
            ) throws URISyntaxException {
        userService.userHasSeenMovie(idUser, idMovie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/users/{idUser}/liked/movie/{idMovie}")
    public ResponseEntity<User> userHasLikedMovie(
            @PathVariable("idUser") Long idUser,
            @PathVariable("idMovie") Long idMovie
        ) throws URISyntaxException {
        userService.userHasLikedMovie(idUser, idMovie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping ("/users/{idUser}/liked/movie/{idMovie}")
    public ResponseEntity<User> userHasDislikedMovie(
            @PathVariable("idUser") Long idUser,
            @PathVariable("idMovie") Long idMovie
            ) throws URISyntaxException {
        userService.userHasDislikedMovie(idUser, idMovie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/users/seed/movies")
    public ResponseEntity<HttpStatus> seedMovies() {
        userService.seedMovies();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

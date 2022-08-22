package fr.epita.netflix.dao;

import fr.epita.netflix.datamodel.UserHasSeenMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHasSeenMovieDao extends JpaRepository<UserHasSeenMovie, Long> { }

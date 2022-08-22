package fr.epita.netflix.dao;

import fr.epita.netflix.datamodel.UserHasLikedMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHasLikedMovieDao extends JpaRepository<UserHasLikedMovie, Long> {
    public void deleteUserHasLikedMovieByIdUserAndIdMovie(Long idUser,Long idMovie);
}

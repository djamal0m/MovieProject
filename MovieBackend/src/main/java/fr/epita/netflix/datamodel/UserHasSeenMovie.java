package fr.epita.netflix.datamodel;

import javax.persistence.*;

@Entity
@Table(name = "user_has_seen_movie", uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "id_movie"}))
public class UserHasSeenMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_movie")
    private Long idMovie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }
}

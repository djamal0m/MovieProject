package fr.epita.netflix.dao;

import fr.epita.netflix.datamodel.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDao extends JpaRepository<Contact, Integer> {
    Contact findByEmail(String email);
}


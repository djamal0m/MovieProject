package fr.epita.netflix.service;

import fr.epita.netflix.dao.RoleDao;
import fr.epita.netflix.datamodel.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public Role findById(Long id) {
        return roleDao.findById(id).get();
    }

    public Role save(Role movie) {
        return roleDao.saveAndFlush(movie);
    }
}

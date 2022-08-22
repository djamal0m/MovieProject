package fr.epita.netflix.service;

import fr.epita.netflix.datamodel.Role;

import java.util.List;

public interface RoleService {
    public Role findById(Long id);
    public List<Role> findAll();
    public Role save(Role role);
}

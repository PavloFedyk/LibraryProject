package com.library.service.Implementation;

import com.library.DAO.RoleDAO;
import com.library.entity.Role;

import java.util.List;

public class RoleServiceImpl implements RoleDAO {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findById(Long id) {
        return roleDAO.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }
}

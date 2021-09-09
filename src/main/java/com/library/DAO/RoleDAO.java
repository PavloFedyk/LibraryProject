package com.library.DAO;

import com.library.entity.Role;

import java.util.List;

public interface RoleDAO {

    Role findById(Long id);

    List<Role> findAll();

}

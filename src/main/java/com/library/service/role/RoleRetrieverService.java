package com.library.service.role;

import com.library.entity.Role;

import java.util.List;

public interface RoleRetrieverService {
    Role findById(Long id);

    List<Role> findAll();
}

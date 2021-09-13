package com.library.service.role;

import com.library.dao.RoleDAO;
import com.library.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRetrieverServiceImpl implements RoleRetrieverService {
    private final RoleDAO roleDAO;

    @Override
    public Role findById(Long id) {
        return roleDAO.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }
}

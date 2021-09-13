package com.library.service.user;

import com.library.dao.UserDAO;
import com.library.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleterServiceImpl implements UserDeleterService{
    private final UserDAO userDAO;

    @Override
    public User remove(Long id) {
        return userDAO.remove(id);
    }
}

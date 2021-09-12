package com.library.service.impl;

import com.library.dao.UserDAO;
import com.library.entity.RentStatus;
import com.library.entity.User;
import com.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByIdFetchRentInfo(Long id) {
        return userDAO.findByIdFetchRentInfo(id);
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User remove(Long id) {
        return userDAO.remove(id);
    }

    @Override
    public Integer daysOurClient(Long id) {
        return userDAO.daysOurClient(id);
    }

    @Override
    public Integer amountReadBooks(Long id) {
        return userDAO.amountReadBooks(id);
    }

    @Override
    public Optional<Double> readingTimeOfBooks(RentStatus rentStatus, Long id) {
        return userDAO.readingTimeOfBooks(rentStatus, id);
    }

    @Override
    public Integer averageAge() {
        return userDAO.averageAge();
    }

    @Override
    public Integer averageTimeWorkingWithLibrary() {
        return userDAO.averageTimeWorkingWithLibrary();
    }

    @Override
    public List<User> findAllWithExpiredStatus() {
        return userDAO.findAllWithExpiredStatus();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return userDAO.getUserByUsername(s);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("User not found!");
        }

    }
}

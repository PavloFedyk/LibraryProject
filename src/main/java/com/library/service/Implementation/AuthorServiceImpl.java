package com.library.service.Implementation;

import com.library.DAO.AuthorDAO;
import com.library.entity.Author;
import com.library.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDAO authorDAO;

    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public Author save(Author author) {
        return authorDAO.save(author);
    }

    @Override
    public Author findById(Long id) {
        return authorDAO.findById(id);
    }

    @Override
    public Author findByFetchBooks(Long id) {
        return authorDAO.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDAO.findAll();
    }

    @Override
    public Author remove(Long id) {
        return authorDAO.remove(id);
    }
}

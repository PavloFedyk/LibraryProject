package com.library.service.author;

import com.library.dao.AuthorDAO;
import com.library.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorRetrieverServiceImpl implements AuthorRetrieverService {

    private final AuthorDAO authorDAO;

    @Override
    public Author findById(final Long id) {
        return authorDAO.findById(id);
    }

    @Override
    public Author findByFetchBooks(final Long id) {
        return authorDAO.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDAO.findAll();
    }
}

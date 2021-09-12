package com.library.service.author;

import com.library.entity.Author;

import java.util.List;

public interface AuthorRetrieverService {

    Author findById(Long id);

    Author findByFetchBooks(Long id);

    List<Author> findAll();
}

package com.library.service;

import com.library.entity.Book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Book findById(Long id);

    Book findByIdFetchCoAuthors(Long id);

    List<Book> findAll();

    Book remove(Long id);

    List<Book> findBooksByAuthorAndTitle(String str);

    List<Book> findMostPopularBook(LocalDateTime from, LocalDateTime to);

    List<Book> findMostUnpopularBook(LocalDateTime from, LocalDateTime to);

    Optional<Double> averageTimeOfReadingBook(String title);

}

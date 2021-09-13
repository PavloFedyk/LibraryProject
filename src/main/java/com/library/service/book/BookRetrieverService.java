package com.library.service.book;

import com.library.entity.Book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookRetrieverService {

    Book findById(Long id);

    Book findByIdFetchCoAuthors(Long id);

    List<Book> findAll();

    List<Book> findBooksByAuthorAndTitle(String str);

    List<Book> findMostPopularBook(LocalDateTime from, LocalDateTime to);

    List<Book> findMostUnpopularBook(LocalDateTime from, LocalDateTime to);

    Optional<Double> averageTimeOfReadingBook(String title);
}

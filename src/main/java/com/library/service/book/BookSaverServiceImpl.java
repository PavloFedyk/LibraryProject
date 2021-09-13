package com.library.service.book;

import com.library.dao.BookDAO;
import com.library.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookSaverServiceImpl implements BookSaverService {
    private final BookDAO bookDAO;

    @Override
    public Book save(Book book) {
        return bookDAO.save(book);
    }
}

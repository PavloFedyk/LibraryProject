package com.library.service.book;

import com.library.dao.BookDAO;
import com.library.entity.Author;
import com.library.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookDeleterServiceImpl implements BookDeleterService {
    private final BookDAO bookDAO;

    @Override
    public Book remove(final Long id) {
        return bookDAO.remove(id);
    }
}


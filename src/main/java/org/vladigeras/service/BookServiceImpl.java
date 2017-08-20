package org.vladigeras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vladigeras.dao.BookDAO;
import org.vladigeras.model.BookEntity;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean delete(Long id) {
        return bookDAO.delete(id);
    }

//    @Override
//    public boolean save(BookEntity book) {
//        return bookDAO.save(book);
//    }


}

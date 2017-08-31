package org.vladigeras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vladigeras.dao.BookDAO;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return bookDAO.delete(id);
    }

    @Override
    @Transactional
    public List search(String title, String author, String genre) {
        return bookDAO.search(title, author, genre);
    }

    @Override
    @Transactional
    public Object getBookById(Long id) {
        return bookDAO.getBookById(id);
    }

    @Override
    @Transactional
    public byte[] getContentById(Long id) {
        return bookDAO.getContentById(id);
    }
//    @Override
//    @Transactional
//    public boolean save(BookEntity book) {
//        return bookDAO.save(book);
//    }


}

package org.vladigeras.service;

import org.vladigeras.model.BookEntity;

import java.util.List;

public interface BookService {
    public boolean delete(Long id);
    public List search(String title, String author, String genre);
    public BookEntity getBookById(Long id);
//    public boolean save(BookEntity book);
}

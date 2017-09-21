package org.vladigeras.dao;

import org.vladigeras.model.BookEntity;

import java.util.List;

public interface BookDAO {
    public boolean delete(Long id);
    public List search(String title, String author, String genre);
    public Object getBookById(Long id);
    public BookEntity getBookEntityById(Long id);
    public byte[] getContentById(Long id);
//    public boolean save(BookEntity book);
}

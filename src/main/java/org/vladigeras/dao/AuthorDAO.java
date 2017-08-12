package org.vladigeras.dao;

import org.vladigeras.model.AuthorEntity;

import java.util.List;

public interface AuthorDAO {
    public List<AuthorEntity> getAllAuthors();
    public boolean delete(Long id);
    public boolean save(AuthorEntity author);
    public boolean update(AuthorEntity author);
}

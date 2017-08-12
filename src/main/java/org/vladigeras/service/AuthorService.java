package org.vladigeras.service;

import org.vladigeras.model.AuthorEntity;

import java.util.List;

public interface AuthorService {
    public List<AuthorEntity> getAllAuthors();
    public boolean delete(Long id);
    public boolean save(AuthorEntity author);
    public boolean update(AuthorEntity author);
}

package org.vladigeras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vladigeras.dao.AuthorDAO;
import org.vladigeras.model.AuthorEntity;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    public AuthorServiceImpl() {
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        List<AuthorEntity> result;
        result = authorDAO.getAllAuthors();
        return result;
    }

    @Override
    public boolean delete(Long id) {
        return authorDAO.delete(id);
    }

    @Override
    public boolean save(AuthorEntity author) {
        return authorDAO.save(author);
    }

    @Override
    public boolean update(AuthorEntity author) {
        return authorDAO.update(author);
    }
}

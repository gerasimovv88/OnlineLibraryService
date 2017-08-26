package org.vladigeras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vladigeras.dao.AuthorDAO;
import org.vladigeras.model.AuthorEntity;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDAO authorDAO;

    @Autowired
    public AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    @Transactional
    public List<AuthorEntity> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return authorDAO.delete(id);
    }

    @Override
    @Transactional
    public boolean save(AuthorEntity author) {
        return authorDAO.save(author);
    }

    @Override
    @Transactional
    public boolean update(AuthorEntity author) {
        return authorDAO.update(author);
    }
}

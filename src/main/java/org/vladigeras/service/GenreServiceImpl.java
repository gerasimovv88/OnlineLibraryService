package org.vladigeras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vladigeras.dao.GenreDAO;
import org.vladigeras.model.GenreEntity;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDAO genreDAO;

    @Autowired
    public GenreServiceImpl(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    @Transactional
    public List<GenreEntity> getAllGenres() {
        return genreDAO.getAllGenres();
    }

    @Override
    @Transactional
    public boolean save(String title) {
        return genreDAO.save(title);
    }

    @Override
    @Transactional
    public boolean delete(String title) {
        return genreDAO.delete(title);
    }

    @Override
    @Transactional
    public boolean update(String oldTitle, String newTitle) {
        return genreDAO.update(oldTitle, newTitle);
    }
}

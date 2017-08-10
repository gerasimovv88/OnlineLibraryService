package org.vladigeras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vladigeras.dao.GenreDAO;
import org.vladigeras.model.GenreEntity;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDAO genreDAO;

    public GenreServiceImpl() {
    }

    @Override
    public List<GenreEntity> getAllGenres() {
        List<GenreEntity> result;
        result = genreDAO.getAllGenres();
        return result;
    }

    @Override
    public boolean save(String title) {
        return genreDAO.save(title);
    }

    @Override
    public boolean delete(String title) {
        return genreDAO.delete(title);
    }

    @Override
    public boolean update(String oldTitle, String newTitle) {
        return genreDAO.update(oldTitle, newTitle);
    }
}

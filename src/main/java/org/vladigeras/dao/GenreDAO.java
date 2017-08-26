package org.vladigeras.dao;

import org.vladigeras.model.GenreEntity;

import java.util.List;

public interface GenreDAO {
    public List<GenreEntity> getAllGenres();
    public boolean save(String title);
    public boolean delete(String title);
    public boolean update(String oldTitle, String newTitle);
}

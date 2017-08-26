package org.vladigeras.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vladigeras.model.GenreEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public GenreDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<GenreEntity> getAllGenres() {
        List<GenreEntity> result = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<GenreEntity> criteria = builder.createQuery(GenreEntity.class);
            Root<GenreEntity> root = criteria.from(GenreEntity.class);
            criteria.select(root);
            result = session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private GenreEntity findByTitle(String title, Session session) {
        GenreEntity result = null;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> criteria = builder.createQuery(GenreEntity.class);
        Root<GenreEntity> root = criteria.from(GenreEntity.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("title"), title));
        List<GenreEntity> genres = session.createQuery(criteria).getResultList();

        if(genres != null) {
            if (genres.size() == 1) {
                result = genres.get(0);
            }
        }
        return result;
    }

    @Override
    public boolean save(String title) {     /* If not find entity with this title, then save new entity */
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            GenreEntity genre = findByTitle(title, session);
            if (genre == null) { //not find - save new
                genre = new GenreEntity(title);
                session.save(genre);
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successful;
    }

    @Override
    public boolean delete(String title) {   /* If find entity with this title, then delete her */
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            GenreEntity genre = findByTitle(title, session);
            if (genre != null) { //find - delete
                session.delete(genre);
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successful;
    }

    @Override
    public boolean update(String oldTitle, String newTitle) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            GenreEntity genre = findByTitle(oldTitle, session); /* If find entity with this title, then replace it */
            if (genre != null) { //find - replace
                genre.setTitle(newTitle);
                session.update(genre);
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successful;
    }
}

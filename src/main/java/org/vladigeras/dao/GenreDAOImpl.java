package org.vladigeras.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vladigeras.model.GenreEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class GenreDAOImpl implements GenreDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public GenreDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<GenreEntity> getAllGenres() {
        List<GenreEntity> result = null;

        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<GenreEntity> criteria = builder.createQuery(GenreEntity.class);
            Root<GenreEntity> root = criteria.from(GenreEntity.class);
            criteria.select(root);
            result = session.createQuery(criteria).getResultList();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
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
    @Transactional
    public boolean save(String title) {     /* If not find entity with this title, then save new entity */
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            Transaction tx = session.beginTransaction();
            GenreEntity genre = findByTitle(title, session);
            if (genre == null) { //not find - save new
                genre = new GenreEntity(title);
                session.save(genre);
                tx.commit();
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return successful;
    }

    @Override
    @Transactional
    public boolean delete(String title) {   /* If find entity with this title, then delete her */
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            Transaction tx = session.beginTransaction();
            GenreEntity genre = findByTitle(title, session);
            if (genre != null) { //find - delete
                session.delete(genre);
                tx.commit();
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return successful;
    }

    @Override
    @Transactional
    public boolean update(String oldTitle, String newTitle) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            Transaction tx = session.beginTransaction();
            GenreEntity genre = findByTitle(oldTitle, session); /* If find entity with this title, then replace it */
            if (genre != null) { //find - replace
                genre.setTitle(newTitle);
                session.update(genre);
                tx.commit();
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return successful;
    }
}

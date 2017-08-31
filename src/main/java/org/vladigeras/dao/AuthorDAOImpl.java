package org.vladigeras.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vladigeras.model.AuthorEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public AuthorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        List<AuthorEntity> result = null;

        Session session = sessionFactory.getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AuthorEntity> criteria = builder.createQuery(AuthorEntity.class);
            Root<AuthorEntity> root = criteria.from(AuthorEntity.class);
            criteria.select(root);
            result = session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            AuthorEntity author = session.get(AuthorEntity.class, id);
            if (author != null) {
                session.delete(author);
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successful;
    }

    @Override
    public boolean save(AuthorEntity author) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AuthorEntity> criteria = builder.createQuery(AuthorEntity.class);
            Root<AuthorEntity> root = criteria.from(AuthorEntity.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("firstname"), author.getFirstname())).
                    where(builder.equal(root.get("middlename"), author.getMiddlename())).
                    where(builder.equal(root.get("lastname"), author.getLastname()));
            List<AuthorEntity> authors = session.createQuery(criteria).getResultList();
                                        //if not find, then save new entity
            if ((authors != null) && (authors.isEmpty())) {
                session.save(author);
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successful;
    }

    @Override
    public boolean update(AuthorEntity author) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            if (session.get(AuthorEntity.class, author.getId()) != null) {   // if find, then merge fields
                session.merge(author);
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successful;
    }
}

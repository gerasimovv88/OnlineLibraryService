package org.vladigeras.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vladigeras.model.AuthorEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthorDAOImpl implements AuthorDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public AuthorDAOImpl() {
    }

    @Override
    @Transactional
    public List<AuthorEntity> getAllAuthors() {
        List<AuthorEntity> result = null;

        Session session = sessionFactory.getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AuthorEntity> criteria = builder.createQuery(AuthorEntity.class);
            Root<AuthorEntity> root = criteria.from(AuthorEntity.class);
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

    @Override
    @Transactional
    public boolean delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            Transaction tx = session.beginTransaction();
            AuthorEntity author = session.get(AuthorEntity.class, id);
            if (author != null) {
                session.delete(author);
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
    public boolean save(AuthorEntity author) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            Transaction tx = session.beginTransaction();

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
    public boolean update(AuthorEntity author) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            Transaction tx = session.beginTransaction();
            if (session.get(AuthorEntity.class, author.getId()) != null) {   // if find, then merge fields
                session.merge(author);
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

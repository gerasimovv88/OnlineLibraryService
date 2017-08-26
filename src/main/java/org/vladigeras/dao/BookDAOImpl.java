package org.vladigeras.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vladigeras.model.BookEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            BookEntity book = session.get(BookEntity.class, id);
            if (book != null) { //if find - then delete
                session.delete(book);
                successful = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successful;
    }

    @Override
    public List search(String title, String author, String genre) {
        List result = new ArrayList();

        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("SELECT BookEntity.id, BookEntity.title, BookEntity.image, BookEntity.");
            List results = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public BookEntity getBookById(Long id) {
        BookEntity result = null;

        Session session = sessionFactory.getCurrentSession();
        try {
            result = session.get(BookEntity.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}

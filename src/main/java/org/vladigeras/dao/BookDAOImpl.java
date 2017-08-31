package org.vladigeras.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vladigeras.model.BookEntity;

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
        List result = null;

        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("SELECT DISTINCT book.id, book.title, book.image, book.averageRating, ac.fio, gc.title " +
                "FROM BookEntity AS book " +
                "INNER JOIN book.authorEntityCollection AS ac " +
                "INNER JOIN book.genreEntityCollection AS gc " +
                "WHERE ((LOWER(book.title) LIKE :bookTitle) OR (LOWER(ac.fio) LIKE :author) OR (LOWER(gc.title) LIKE :genre)) " +
                    "GROUP BY book.id ORDER BY book.title ASC");
                query.setParameter("bookTitle", "%" + title.toLowerCase() + "%");
                query.setParameter("author", "%" + author.toLowerCase() + "%");
                query.setParameter("genre", "%" + genre.toLowerCase() + "%");
            result = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Object getBookById(Long id) {
        Object result = null;

        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("SELECT DISTINCT book, ac.fio " +
                    "FROM BookEntity AS book " +
                    "INNER JOIN book.authorEntityCollection AS ac " +
                    "INNER JOIN book.genreEntityCollection AS gc " +
                    "WHERE book.id = :bookId");
            query.setParameter("bookId", id);
            List results = query.list();
            if (results != null) {
                if (results.size() == 1) {
                    result = results.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public byte[] getContentById(Long id) {
        byte[] content = null;
        Session session = sessionFactory.getCurrentSession();
        try {
            Query query = session.createQuery("SELECT book.content FROM BookEntity AS book WHERE book.id = :id");
            query.setParameter("id", id);
            List result = query.list(); //we must get only one record
            if (result != null) {
                if (result.size() == 1) {
                    content = (byte[]) result.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }

}

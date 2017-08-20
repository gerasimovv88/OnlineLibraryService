package org.vladigeras.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vladigeras.model.AuthorEntity;
import org.vladigeras.model.BookEntity;

import javax.transaction.Transactional;

@Repository
public class BookDAOImpl implements BookDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        boolean successful = false;
        try {
            Transaction tx = session.beginTransaction();
            BookEntity book = session.get(BookEntity.class, id);
            if (book != null) { //if find - then delete
                session.delete(book);
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

//    @Override
//    @Transactional
//    public boolean save(BookEntity book) {
//        Session session = sessionFactory.getCurrentSession();
//        boolean successful = false;
//        try {
//            Transaction tx = session.beginTransaction();
//            BookEntity bookEntity = findByAuthorAndTitle(book.getBookAuthorsById(), book.getTitle());
//            if (bookEntity == null) { //if not find - then save new
//                session.save(book);
//                tx.commit();
//                successful = true;
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return successful;
//    }
//
//    @Transactional
//    private BookEntity findByAuthorIdAndTitle (Long authorId, String string) {
//
//    }
}

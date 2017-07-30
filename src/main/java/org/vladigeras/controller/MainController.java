package org.vladigeras.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vladigeras.controller.dao.util.HibernateUtil;
import org.vladigeras.entity.GenreEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
public class MainController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void hello() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(new GenreEntity("Приключения"));

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> criteria = builder.createQuery(GenreEntity.class);
        Root<GenreEntity> employeeRoot=criteria.from(GenreEntity.class);
        criteria.select(employeeRoot);

        List<GenreEntity> employeeList=session.createQuery(criteria).getResultList();
        for (GenreEntity genreEntity : employeeList) {
            System.out.println(genreEntity.getTitle());
        }

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }
}

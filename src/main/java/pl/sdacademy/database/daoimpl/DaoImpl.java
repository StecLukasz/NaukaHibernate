package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements RunDao {
    @Override
    public void save(Run run) {
        SessionFactory sessionFactory = HibernateUtils
                .getInstance().getSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(run);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public Run findById(Long id) {
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Run run = null;
        try {
            run = (Run) session
                    .createQuery("from Run where id =:id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
        }

        session.getTransaction().commit();
        session.close();


        return run;
    }

    @Override
    public List<Run> findAll() {
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Run> runList = session
                .createQuery("from Run", Run.class)
                .list();
        session.getTransaction().commit();
        session.close();


        return runList;
    }


    @Override
    public void deleteById(Long id) {
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Run run = null;
        run = session.createQuery("from Run where id=:id", Run.class)
                .setParameter("id", id)
                .getSingleResult();


        session.delete(run);
        session.getTransaction().commit();
        session.close();

    }
}

package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sdacademy.database.dao.RunMemberDao;
import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.List;

public class RunMemberDaoImpl implements RunMemberDao {
    @Override
    public void save(RunMember runMember) {
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();

        Session session = factory.getCurrentSession();
        session.saveOrUpdate(runMember);
        session.beginTransaction().commit();
        session.close();

    }

    @Override
    public RunMember findById(Long id) {
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();
        Session session=factory.getCurrentSession();
        session.beginTransaction();
        RunMember runMember =null;
        try {
            runMember = (RunMember) session
                    .createQuery("from RunMember where id-:id")
                    .setParameter("id", id)
                    .getSingleResult();

        }catch (Exception e){}

        session.getTransaction().commit();
        session.close();

        return runMember;
    }

    @Override
    public List<RunMember> findAll() {
        SessionFactory factory =HibernateUtils
                .getInstance().getSessionFactory();
        Session session =factory.getCurrentSession();
        session.beginTransaction();
        List<RunMember> runMemberList = session.createQuery("from Run", RunMember.class)
                .list();
        session.getTransaction().commit();
        session.close();
        return runMemberList;
    }

    @Override
    public void deleteById(Long id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();

        Session session =factory.getCurrentSession();
        session.beginTransaction();
        RunMember runMember =null;
        runMember = session.createQuery("from RunMember where id=:id", RunMember.class)
                .setParameter("id", id)
                .getSingleResult();
        session.delete(runMember);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<RunMember> findByNameFragment(String fragment) {
        SessionFactory factory =HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<RunMember> runMemberList = session.createQuery("from RunMember where name like '%" + fragment + "%'", RunMember.class)
                .list();
        session.getTransaction().commit();
        session.close();
        return runMemberList;
    }
}

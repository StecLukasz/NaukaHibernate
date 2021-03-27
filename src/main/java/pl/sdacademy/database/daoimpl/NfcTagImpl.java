package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sdacademy.database.dao.NfcTagDao;
import pl.sdacademy.database.entity.NfcTag;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.List;

public class NfcTagImpl implements NfcTagDao {

    @Override
    public void save(NfcTag nfcTag) {
            SessionFactory factory = HibernateUtils
                    .getInstance().getSessionFactory();

            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(nfcTag);
            session.getTransaction().commit();
            session.close();
    }

    @Override
    public NfcTag findById(Long id) {
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();
        Session session=factory.getCurrentSession();
        session.beginTransaction();
        NfcTag nfcTag  =null;
        try {
            nfcTag = (NfcTag) session
                    .createQuery("from NfcTag where id=:id")
                    .setParameter("id", id)
                    .getSingleResult();

        }catch (Exception e){}

        session.getTransaction().commit();
        session.close();

        return nfcTag;
    }

    @Override
    public List<NfcTag> findAll() {
        SessionFactory factory =HibernateUtils
                .getInstance().getSessionFactory();
        Session session =factory.getCurrentSession();
        session.beginTransaction();
        List<NfcTag> nfcTagList = session.createQuery("from NfcTag", NfcTag.class)
                .list();
        session.getTransaction().commit();
        session.close();
        return nfcTagList;
    }

    @Override
    public void deleteById(Long id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();

        Session session =factory.getCurrentSession();
        session.beginTransaction();
        NfcTag nfcTag =null;
        nfcTag = session.createQuery("from NfcTag where id=:id", NfcTag.class)
                .setParameter("id", id)
                .getSingleResult();
        session.delete(nfcTag);
        session.getTransaction().commit();
        session.close();

    }
}

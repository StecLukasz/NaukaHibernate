package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.dao.NfcTagDao;
import pl.sdacademy.database.entity.NfcTag;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import static org.junit.jupiter.api.Assertions.*;

class NfcTagImplTest {
    private NfcTagDao nfcTagDao = new NfcTagImpl();
    @BeforeEach
    private void deleteAll() {
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("delete NfcTag")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


    @Test
    void save() {

        NfcTag tag = new NfcTag();
        tag.setSerialNumber("serial serial serial");
        nfcTagDao.save(tag);
        NfcTag saved = nfcTagDao.findById(tag.getId());
        assertNotNull(saved);
    }
}

//    @Test
//    void save() {
//        NfcTag tag = new NfcTag();
//        try {
//            NfcTagDao.save(tag);
//            Run saved = NfcTagDao.findById(tag.getId());
//
//            assertNotNull(saved);
//            assertEquals(tag.getId(), saved.getId());
//            assertEquals(tag.getSerialNumber(), saved);
//        } catch (Exception e) {
//            fail(e);
//        }
//    }
//}
package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunMemberDaoImplTest {
    private RunMemberDaoImpl runMemberDao = new RunMemberDaoImpl();


    @BeforeEach
    private void deleteAll() {
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("delete RunMember")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        RunMember runMember = new RunMember(null, "Testowy Bieg", 99);
        try {
            runMemberDao.save(runMember);
            RunMember saved = runMemberDao.findById(runMember.getId());

            assertNotNull(saved);
            assertEquals(runMember.getId(), saved.getId());
            assertEquals(runMember.getName(), saved.getName());
            assertEquals(runMember.getStart_number(), saved.getStart_number());
        } catch (Exception e) {
            fail(e);
        }
    }


    @Test
    void findById() {
    }

    @Test
    void findAll() {
        try {
            RunMember runMember1 = new RunMember(null, "Bieg numer 100", 99);
            RunMember runMember2 = new RunMember(null, "inny bieg", 20);

            runMemberDao.save(runMember1);
            runMemberDao.save(runMember2);

            List<RunMember> runList = runMemberDao.findAll();
            assertNotNull(runList);
            assertEquals(2, runList.size());

            RunMember testRun1 = null;
            if (runList.get(0).getId() == runMember1.getId()) {
                testRun1 = runList.get(0);
            } else if (runList.get(1).getId() == runMember1.getId()) {
                testRun1 = runList.get(1);
            }

            assertNotNull(testRun1);
            assertEquals(runMember1.getId(), testRun1.getId());
            assertEquals(runMember1.getName(), testRun1.getName());
            assertEquals(runMember1.getStart_number(), testRun1.getStart_number());

        } catch (Exception e) {
            fail(e);
        }
    }


    @Test
    void deleteById() {
    }

    @Test
    void findByNameFragment() {
    }
}
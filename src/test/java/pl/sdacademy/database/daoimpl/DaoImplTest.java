package pl.sdacademy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DaoImplTest {
    private DaoImpl runDao = new DaoImpl();


    @BeforeEach
    private void deleteAll() {
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("delete Run")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        Run run = new Run(null, "Testowy Bieg", 99, 20);
        try {
            runDao.save(run);
            Run saved = runDao.findById(run.getId());

            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getName(), saved.getName());
            assertEquals(run.getMembersLimit(), saved.getMembersLimit());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void findAll() {
        try {
            Run run1 = new Run(null, "Bieg numer 100", 99, 20);
            Run run2 = new Run(null, "inny bieg", 20, 20);

            runDao.save(run1);
            runDao.save(run2);

            List<Run> runList = runDao.findAll();
            assertNotNull(runList);
            assertEquals(2, runList.size());

            Run testRun1 = null;
            if (runList.get(0).getId() == run1.getId()) {
                testRun1 = runList.get(0);
            } else if (runList.get(1).getId() == run1.getId()) {
                testRun1 = runList.get(1);
            }

            assertNotNull(testRun1);
            assertEquals(run1.getId(), testRun1.getId());
            assertEquals(run1.getName(), testRun1.getName());
            assertEquals(run1.getMembersLimit(), testRun1.getMembersLimit());

        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void findById() {
        try {
            Run run1 = new Run(null, "Testowy", 99, 5);
            Run run2 = new Run(null, "Testowy", 20, 10);
            runDao.save(run1);
            runDao.save(run2);
            List<Run> runList = runDao.findAll();
            Run testRun1 = null;
            for (Run run : runList) {
                if (run.getId() == run1.getId()) {
                    testRun1 = run1;
                }
            }
            assertNotNull(runList);
            assertEquals(2, runList.size());
            assertEquals(run1.getId(), testRun1.getId());
        } catch (Exception e) {
            fail(e);
        }
    }


//    @Test
//    void update() {
//        Run run = new Run(1l, "Bieg testowy przed zmiana", 50, 20);
//        try {
//            runDao.save(run);
//            run.setMembersLimit(20);
//            run.setName("Inna nazwa");
//
//            runDao.update(run);
//
//            Run updated = runDao.findById(run.getId());
//
//            assertNotNull(updated);
//            assertEquals(run.getMembersLimit(), updated.getMembersLimit());
//            assertEquals(run.getName(), updated.getName());
//
//        } catch (Exception e) {
//            fail(e);
//        }
//    }

    @Test
    void deleteById() {
        Run run = new Run(null, "Bieg do usunicia", 100, 20);
        try {
            runDao.save(run);
            runDao.deleteById(run.getId());
            Run deleted = runDao.findById(run.getId());


            assertNull(deleted);


        } catch (Exception e) {
            fail(e);
        }
    }
    @Test
    void findMembersByLimitRange(){
        try {
            Run run1 = new Run(null, "Bieg Janusza", 50,20);
            Run run2 = new Run(null, "Bieg Grażyny", 100,20);
            Run run3 = new Run(null, "Bieg Dżesiki", 150,20);

            runDao.save(run1);
            runDao.save(run2);
            runDao.save(run3);

            List<Run> found1 = runDao.findMembersByLimitRange(40,60);
            List<Run> found2 = runDao.findMembersByLimitRange(40,200);
            List<Run> found3 = runDao.findMembersByLimitRange(40,45);

            assertNotNull(found1);
            assertEquals(1, found1.size());
            assertEquals(run1.getId(), found1.get(0).getId());
            assertEquals(run1.getName(), found1.get(0).getName());
            assertEquals(run1.getMembersLimit(), found1.get(0).getMembersLimit());

            assertEquals(3,found2.size());
            assertEquals(0,found3.size());


        }catch (Exception e){
            fail(e);
        }

    }
}
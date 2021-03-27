package pl.sdacademy.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sdacademy.database.dao.NfcTagDao;
import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.dao.RunMemberDao;
import pl.sdacademy.database.daoimpl.DaoImpl;
import pl.sdacademy.database.daoimpl.NfcTagImpl;
import pl.sdacademy.database.daoimpl.RunMemberDaoImpl;
import pl.sdacademy.database.entity.NfcTag;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.entity.RunMember;
import pl.sdacademy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.util.List;

public class Main {
    public static void main(String[] args) {


//        insertOneRun();
//        selectOneRun();
//        printAllRuns();

//        oneToManySaveTest();
//        oneToManySelectTest();
//        manyToManySaveTest();
        nfcTangReadTest();

        HibernateUtils.getInstance()
                .getSessionFactory()
                .close();
    }

    private static void nfcTangReadTest() {
        NfcTagDao nfcTagDao = new NfcTagImpl();
        NfcTag tag = nfcTagDao.findById(76l);

        System.out.println("Tag: " + tag.getSerialNumber());
        for (RunMember runMember : tag.getMembers()) {
            System.out.println("Uczestnik: " + runMember.getName());
            if (runMember.getRun() != null) {
                System.out.println("Uczestnik biegu: " + runMember.getRun().getName());
            }
        }
    }

    private static void manyToManySaveTest() {
        RunMemberDao runMemberDao = new RunMemberDaoImpl();
        NfcTagDao nfcTagDao = new NfcTagImpl();

        RunMember member1 = new RunMember();
        member1.setName("Adam");
        RunMember member2 = new RunMember();
        member2.setName("Wojciech");

        runMemberDao.save(member1);
        runMemberDao.save(member2);

        NfcTag tag1 = new NfcTag();
        tag1.setSerialNumber("tag numer 1");
        tag1.getMembers().add(member1);
        tag1.getMembers().add(member2);
        nfcTagDao.save(tag1);

        NfcTag tag2 = new NfcTag();
        tag2.setSerialNumber("tag numer 2");
        tag2.getMembers().add(member1);
        tag2.getMembers().add(member2);
        nfcTagDao.save(tag2);

    }


    private static void oneToManySaveTest() {
        RunDao runDao = new DaoImpl();
        RunMemberDao runMemberDao = new RunMemberDaoImpl();

        Run run = new Run();
        run.setName("Bieg na 10");
        runDao.save(run);
        System.out.println("Numer id: " + run.getId());

        for (int i = 0; i < 10; i++) {
            RunMember member = new RunMember();
            member.setName("Biegacz nr " + i);
            runMemberDao.save(member);

            member.setRun(run);
            runMemberDao.save(member);
        }
    }

    static void oneToManySelectTest() {
        RunDao runDao = new DaoImpl();


        Run run = runDao.findById(41l);
        System.out.println(run.getId());
        System.out.println("Bieg: " + run.getName());
        System.out.println("Ilość uczestników: " + run.getMembers().size());

    }


    private static void insertOneRun() {
        Run run = new Run();
        run.setName("Rzeszowska piątka");
        run.setMembersLimit(1000);

        SessionFactory sessionFactory = HibernateUtils.getInstance()
                .getSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(run);
        session.getTransaction().commit();
        session.close();


    }

    private static void selectOneRun() {
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();
        Run run = null;
        try {
            run = (Run) session
                    .createQuery("from Run where id=:id")
                    .setParameter("id", 5l)
                    .getSingleResult();
        } catch (NoResultException e) {
        }

        session.getTransaction().commit();
        session.close();
        if (run != null) {

            System.out.printf("Bieg: " + run.getName());
        } else {
            System.out.println("Brak takiego biegu");
        }
    }

    private static void printAllRuns() {
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Run> list = session
                .createQuery("from Run", Run.class)
                .list();

        session.getTransaction().commit();
        session.close();
        for (Run run : list) {
            System.out.printf("id=%d name =%s, limit=%d\n", run.getId(), run.getName(), run.getMembersLimit());
        }
    }
}

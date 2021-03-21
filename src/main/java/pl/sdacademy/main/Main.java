package pl.sdacademy.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.util.List;

public class Main {
    public static void main(String[] args) {


//        insertOneRun();
//        selectOneRun();
        printAllRuns();


        HibernateUtils.getInstance()
                .getSessionFactory()
                .close();

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
        Run run =null;
        try {
            run = (Run) session
                    .createQuery("from Run where id=:id")
                    .setParameter("id", 5l)
                    .getSingleResult();
        } catch (NoResultException e){        }

        session.getTransaction().commit();
        session.close();
        if (run!=null) {

            System.out.printf("Bieg: " + run.getName());
        }else {
            System.out.println("Brak takiego biegu");
        }
    }

    private static void printAllRuns(){
        SessionFactory factory = HibernateUtils
                .getInstance().getSessionFactory();

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Run> list = session
                .createQuery("from Run", Run.class)
                .list();

        session.getTransaction().commit();
        session.close();
        for (Run run:list){
            System.out.printf("id=%d name =%s, limit=%d\n", run.getId(),run.getName(),run.getMembersLimit());
        }
    }
}

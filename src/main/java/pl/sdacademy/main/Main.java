package pl.sdacademy.main;

import pl.sdacademy.database.utils.HibernateUtils;

public class Main {
    public static void main(String[] args) {

        HibernateUtils.getInstance()
                .getSessionFactory()
                .close();

    }
}

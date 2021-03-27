package pl.sdacademy.main;

import pl.sdacademy.database.dao.RunDao;
import pl.sdacademy.database.dao.RunMemberDao;
import pl.sdacademy.database.daoimpl.DaoImpl;
import pl.sdacademy.database.daoimpl.RunMemberDaoImpl;
import pl.sdacademy.database.entity.Run;
import pl.sdacademy.database.entity.RunMember;

import java.util.List;
import java.util.Scanner;

public class RunCenter {

    public static void main(String[] args) {
        int selected;
        Scanner scanner = new Scanner(System.in);
        do {
            log("[1] Dopisz nowy bieg");
            log("[2] Wyświetl biegi");
            log("[3] Usuń bieg");
            log("[4] Wyświetl listę uczestników dla danego biegu");
            log("[5] Dopisz uczestnika do biegu");
            log("[6] Usuń uczestnika");
            log("[7] Wyszkaj uczestnika po numerze startowym");


            log("[0] Wyjście");

            selected = scanner.nextInt();

            switch (selected) {
                case 1:
                    handleAddNewRun();
                    break;
                case 2:
                    handleShowAllRuns();
                    break;
                case 3:
                    handleDeleteRun();
                    break;
                case 4:
                    handleShowListRunnersByOneRun();
                    break;
                case 5:
                    handleAddMemberToRun();
                    break;
            }
        } while (selected != 0);
    }

    private static void log(String text) {
    }

    private static void handleAddMemberToRun() {
        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new DaoImpl();
        RunMemberDao memberDao = new RunMemberDaoImpl();

        log("Podaj którego użytkownika chcesz dodać: ");
        long idRun = scanner.nextLong();
        Run run = runDao.findById(idRun);

        if (run != null) {
            RunMember member = new RunMember();
            log("Podaj nazwę uczestnika");
            scanner.next();

            log("Podaj numer startowy");
            member.setStart_number(scanner.nextInt());

            member.setRun(run);
            memberDao.save(member);
        } else {
            log("Nie ma takiego biegu");
        }
    }

    private static void handleShowListRunnersByOneRun() {
        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new DaoImpl();
        log("Podaj id biegu");
        long runId = scanner.nextLong();
        Run run = runDao.findById(runId);
        for (RunMember member : run.getMembers()) {
            log(member.getId() + " " + member.getName() + " " + member.getStart_number());
        }
    }


    private static void handleShowAllRuns() {
        RunDao runDao = new DaoImpl();
        List<Run> list = runDao.findAll();

        System.out.println("Lista biegów");
        System.out.println("==================");
        for (Run run : list) {
            System.out.println(
                    run.getId() + " " +
                            run.getName() + " " +
                            run.getMembersLimit()
            );
        }
        System.out.println("-------------------");
    }

    private static void handleAddNewRun() {
        Scanner input = new Scanner(System.in);
        RunDao runDao = new DaoImpl();
        Run run = new Run();
        System.out.println("Podaj nazwę nowgo biegu: ");
        run.setName(input.next());
        System.out.println("Podaj limit uczestników: ");
        run.setMembersLimit(input.nextInt());
        System.out.println("Podaj dystans biegu: ");
        run.setDistance(input.nextInt());
        runDao.save(run);

    }

    private static void handleDeleteRun() {
        Scanner input = new Scanner(System.in);
        RunDao runDao = new DaoImpl();
        System.out.println("Podaj id biegu: ");
        runDao.deleteById(input.nextLong());

    }

}

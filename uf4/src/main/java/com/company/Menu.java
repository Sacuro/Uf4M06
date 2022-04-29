package com.company;

import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    public static int dbMenu() {
        while(true) {
            System.out.println("DATABASE SELECTOR:");
            System.out.println("\t(1) MySql");
            System.out.println("\t(2) MongoDB");
            System.out.println("\t----------------");
            System.out.println("\t(q) Exit");
            String dbSelector = scanner.next();

            if (dbSelector.equals("q")) {
                return 0;
            } else if (dbSelector.equals("1")) {
                System.out.println("MySql");
                return 1;
            } else if (dbSelector.equals("2")) {
                System.out.println("MongoDB");
                return 2;
            } else {
                System.out.println("ERROR! Choose a valid option.");
            }
        }
    }

    public static int actionMenu() {
        while(true) {
            System.out.println("SELECT ACTION:");
            System.out.println("\t(1) Insert Film");
            System.out.println("\t(2) Show Film");
            System.out.println("\t(3) Show All Films");
            System.out.println("\t(4) Delete Film");
            System.out.println("\t(5) Update Film");
            System.out.println("\t");
            System.out.println("\t(6) Insert Actor");
            System.out.println("\t(7) Show Actor");
            System.out.println("\t(8) Show All Actors");
            System.out.println("\t(9) Delete Actor");
            System.out.println("\t(10) Update Actor");
            System.out.println("\t");
            System.out.println("\t(11) Insert Relation");
            System.out.println("\t(12) Delete Relation");
            System.out.println("\t(13) Query Relation");
            System.out.println("\t(14) Query All Relation");
            System.out.println("\t(q) Exit");

            String actionSelector = scanner.next();

            switch (actionSelector) {
                case "q":
                    return 0;
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
                case "8":
                    return 8;
                case "9":
                    return 9;
                case "10":
                    return 10;
                case "11":
                    return 11;
                case "12":
                    return 12;
                case "13":
                    return 13;
                case "14":
                    return 14;

                default:
                    System.out.println("ERROR! Choose a valid option.");
                    break;
            }
        }
    }
}

package library.service.impl;

import library.dao.InterfaceUser;
import library.service.CredentialsPersistenceStorable;
import library.service.UserPersistenceStorable;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginServiceImpl {

    private static String readUserLogin(){

        Scanner reader = new Scanner(System.in);
        String userLogin = "";

        while (userLogin.length() <= 0 || userLogin.length() >= 13) {
            System.out.print("Введите логин: ");
            userLogin = reader.nextLine();

            if (userLogin.length() <= 0 || userLogin.length() >= 13) {
                System.out.println("Неверный логин. Нажмите enter для продолжения...");
                try {
                    System.in.read();
                } catch (Exception e) {
                }
            }
        }
        return userLogin;
    }

    private static String getUserPassword() {

        Scanner reader = new Scanner(System.in);
        String userPassword = "";
        while (userPassword.length() <= 0 || userPassword.length() >= 8) {

            System.out.print("Введите пароль: ");
            userPassword = reader.nextLine();

            if (userPassword.length() <= 0 || userPassword.length() >= 8) {
                System.out.println("Неверный пароль. Нажмите enter для продолжения...");
                try {
                    System.in.read();
                } catch (Exception e) {
                }
            }
        }
        return userPassword;
    }

    private static String getUserIdByCreds(String login, String pass) {

        CredentialsPersistenceStorable cps = new DataPersistenceServiceImpl();
        ArrayList<ArrayList<String>> availableCredentials = cps.loadCredentials();
        String desiredUserID = "-1";

        for (ArrayList<String> triple : availableCredentials) {
            if (triple.get(0).equals(login) && triple.get(1).equals(pass)) {
                desiredUserID = triple.get(2);
            }
        }

        return desiredUserID;
    }

    private static InterfaceUser getUserById(String id) {

        UserPersistenceStorable ups = new DataPersistenceServiceImpl();
        ArrayList<InterfaceUser> availableUsers = ups.loadUsers();
        InterfaceUser authUser = null;

        for (InterfaceUser user : availableUsers) {
            if (user.getId() == Integer.parseInt(id)) {
                authUser = user;
            }
        }

        return authUser;
    }

    public static InterfaceUser authenticateUser() {

        String userId = "";
        InterfaceUser user = null;

        while (userId.isEmpty() || user == null) {
            System.out.println("Здравствуйте! Введите логин и пароль.");
            String login = readUserLogin();
            String password = getUserPassword();
            userId = getUserIdByCreds(login, password);
            user = getUserById(userId);

            if (userId.isEmpty() || user == null) {
                System.out.println("Пользователя с такими данными не существует. Нажмите enter для продолжения...");
                try {
                    System.in.read();
                } catch (Exception e) {
                }
            }
        }

        return user;
    }
}

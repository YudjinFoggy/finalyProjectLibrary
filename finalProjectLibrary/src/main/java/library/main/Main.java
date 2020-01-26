package library.main;

import library.dao.impl.BookDAO;
import library.dao.impl.UserDAO;
import library.service.BookPersistenceStorable;
import library.service.CredentialsPersistenceStorable;
import library.dao.InterfaceUser;
import library.service.UserPersistenceStorable;
import library.dao.impl.BookStorageDAO;
import library.service.impl.DataPersistenceServiceImpl;
import library.service.impl.LoginServiceImpl;
import library.controller.MenuController;

import java.util.ArrayList;

public class Main {

    public static void generateUsers() {

        UserDAO admin = new UserDAO();
        admin.setRole("admin");
        admin.setFio("Adminivich Admin Admin");
        admin.setId(100);

        UserDAO userDAO1 = new UserDAO();
        userDAO1.setRole("user");
        userDAO1.setFio("Userovich1 User1 User1");
        userDAO1.setId(101);

        UserDAO userDAO2 = new UserDAO();
        userDAO2.setRole("user");
        userDAO2.setFio("Userovich2 User2 User2");
        userDAO2.setId(102);

        ArrayList<InterfaceUser> users = new ArrayList<InterfaceUser>();
        users.add(admin);
        users.add(userDAO1);
        users.add(userDAO2);

        UserPersistenceStorable ups = new DataPersistenceServiceImpl();
        ups.saveUsers(users);
    }

    public static void generateCredentials() {

        ArrayList<String> adminCreds = new ArrayList<String>();
        adminCreds.add("admin");
        adminCreds.add("admin");
        adminCreds.add("100");

        ArrayList<String> user1Creds = new ArrayList<String>();
        user1Creds.add("user1");
        user1Creds.add("user1");
        user1Creds.add("101");

        ArrayList<String> user2Creds = new ArrayList<String>();
        user2Creds.add("user2");
        user2Creds.add("user2");
        user2Creds.add("102");

        ArrayList<ArrayList<String>> userCreds = new ArrayList<ArrayList<String>>();
        userCreds.add(adminCreds);
        userCreds.add(user1Creds);
        userCreds.add(user2Creds);

        CredentialsPersistenceStorable cps = new DataPersistenceServiceImpl();
        cps.saveCredentials(userCreds);
    }

    public static void generateLibrary() {

        BookDAO bookDAO1 = new BookDAO();
        bookDAO1.setAutor("King");
        bookDAO1.setTitle("Ghost");
        bookDAO1.setPublishingHouse("WhiteHouse");
        bookDAO1.setDescription("library.dao.impl.BookDAO about ghosts");
        bookDAO1.setTypeBook("supernatural");

        BookDAO bookDAO2 = new BookDAO();
        bookDAO2.setAutor("Lee");
        bookDAO2.setTitle("World");
        bookDAO2.setPublishingHouse("WhiteHouse");
        bookDAO2.setDescription("library.dao.impl.BookDAO about world");
        bookDAO2.setTypeBook("science");

        BookDAO bookDAO3 = new BookDAO();
        bookDAO3.setAutor("Jordan Wok");
        bookDAO3.setTitle("People");
        bookDAO3.setPublishingHouse("BlackHouse");
        bookDAO3.setDescription("library.dao.impl.BookDAO about people");
        bookDAO3.setTypeBook("science");

        BookStorageDAO booksStorage = new BookStorageDAO();
        booksStorage.getBooks().add(bookDAO1);
        booksStorage.getBooks().add(bookDAO2);
        booksStorage.getBooks().add(bookDAO3);

        BookPersistenceStorable bps = new DataPersistenceServiceImpl();
        bps.saveBooks(booksStorage);
    }

    public static void generateRandomData() {

        generateCredentials();
        generateUsers();
        generateLibrary();
    }

    public static void main(String[] args) {
       generateRandomData();

        InterfaceUser loggedUser = null;
        while (loggedUser == null) {
            loggedUser = LoginServiceImpl.authenticateUser();
            MenuController.showMenuForUser(loggedUser);
            //loggedUser = null;
        }
    }
}



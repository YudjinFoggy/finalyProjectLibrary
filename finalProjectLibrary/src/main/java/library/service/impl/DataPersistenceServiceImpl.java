package library.service.impl;

import library.dao.impl.BookStorageDAO;
import library.dao.InterfaceUser;
import library.service.BookPersistenceStorable;
import library.service.CredentialsPersistenceStorable;
import library.service.UserPersistenceStorable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataPersistenceServiceImpl implements CredentialsPersistenceStorable, UserPersistenceStorable, BookPersistenceStorable {

    public ArrayList<ArrayList<String>> loadCredentials() {

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        String filename = "credentials.dat";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            result = ((ArrayList<ArrayList<String>>) ois.readObject());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void saveCredentials(ArrayList<ArrayList<String>> creds) {

        String filename = "credentials.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(creds);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<InterfaceUser> loadUsers() {

        ArrayList<InterfaceUser> result = new ArrayList<InterfaceUser>();
        String filename = "users.dat";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            result = ((ArrayList<InterfaceUser>) ois.readObject());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public void saveUsers(ArrayList<InterfaceUser> users) {

        String filename = "users.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public BookStorageDAO loadBooks() {

        BookStorageDAO result = new BookStorageDAO();
        String filename = "books.dat";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            result = (BookStorageDAO) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void saveBooks(BookStorageDAO books) {

        String filename = "books.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

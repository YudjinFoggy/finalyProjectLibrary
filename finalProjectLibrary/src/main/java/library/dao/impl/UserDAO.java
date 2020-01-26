package library.dao.impl;

import library.dao.InterfaceBook;
import library.dao.InterfaceUser;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDAO implements Serializable, InterfaceUser {

    private String fio;
    private int id;
    private String role;
    private ArrayList<String> email;
    private ArrayList<InterfaceBook> books;

    public UserDAO() {

        this.setFio("");
        this.setId(0);
        this.setRole("");
        this.setEmail(new ArrayList<String>());
        this.setBooks(new ArrayList<InterfaceBook>());
    }

    public UserDAO(String fio, int id, String role, ArrayList<String> email, ArrayList<InterfaceBook> books) {

        this.setFio(fio);
        this.setId(id);
        this.setRole(role);
        this.setEmail(email);
        this.setBooks(books);
    }

    public String getFio() { return fio; }

    public void setFio(String fio) { this.fio = fio; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public ArrayList<String> getEmail() { return email; }

    public void setEmail(ArrayList<String> email) { this.email = email; }

    public ArrayList<InterfaceBook> getBooks() { return books; }

    public void setBooks(ArrayList<InterfaceBook> books) { this.books = books; }

    @Override
    public String toString() {
        return super.toString();
    }
}

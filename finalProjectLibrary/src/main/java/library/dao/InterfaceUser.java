package library.dao;

import java.util.ArrayList;

public interface InterfaceUser {

    public String getFio();

    public void setFio(String fio);

    public int getId();

    public void setId(int id);

    public String getRole();

    public void setRole(String role);

    public ArrayList<String> getEmail();

    public void setEmail(ArrayList<String> email);

    public ArrayList<InterfaceBook> getBooks();

    public void setBooks(ArrayList<InterfaceBook> books);

    public String toString();
}

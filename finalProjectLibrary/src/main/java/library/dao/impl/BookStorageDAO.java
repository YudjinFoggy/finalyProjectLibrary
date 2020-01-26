package library.dao.impl;

import library.dao.InterfaceBook;

import java.io.Serializable;
import java.util.ArrayList;

public class BookStorageDAO implements Serializable {

    private ArrayList<InterfaceBook> books;

    public BookStorageDAO() {
        setBooks(new ArrayList<InterfaceBook>());
    }

    public ArrayList<InterfaceBook> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<InterfaceBook> books) {
        this.books = books;
    }
}

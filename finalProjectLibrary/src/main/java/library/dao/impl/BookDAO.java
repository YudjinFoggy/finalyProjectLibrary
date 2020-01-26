package library.dao.impl;

import library.dao.InterfaceBook;

import java.io.Serializable;

public class BookDAO implements Serializable, InterfaceBook {

    private String autor;
    private String title;
    private String publishingHouse;
    private String typeBook;
    private String description;

    public BookDAO() {

        this.setAutor("");
        this.setTitle("");
        this.setPublishingHouse("");
        this.setTypeBook("");
        this.setDescription("");
    }

    public BookDAO(String autor, String title, String publishingHouse, String typeBook, String description) {

        this.setAutor(autor);
        this.setTitle(title);
        this.setPublishingHouse(publishingHouse);
        this.setTypeBook(typeBook);
        this.setDescription(description);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getTypeBook() {
        return typeBook;
    }

    public void setTypeBook(String typeBook) {
        this.typeBook = typeBook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Автор: " + getAutor() + "\nНазвание книги: " + getTitle() + "\nИздательство: " + getPublishingHouse() +
                "\nЖанр книги: " + getTypeBook() + "\nОписание: " + getDescription();
    }
}

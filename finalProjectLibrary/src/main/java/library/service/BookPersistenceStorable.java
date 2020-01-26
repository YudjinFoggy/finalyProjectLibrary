package library.service;

import library.dao.impl.BookStorageDAO;

public interface BookPersistenceStorable {

    public BookStorageDAO loadBooks();
    public void saveBooks(BookStorageDAO books);
}

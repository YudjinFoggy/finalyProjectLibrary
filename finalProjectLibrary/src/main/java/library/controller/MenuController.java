package library.controller;

import library.dao.impl.BookStorageDAO;
import library.dao.impl.BookDAO;
import library.service.BookPersistenceStorable;
import library.dao.InterfaceBook;
import library.dao.InterfaceUser;
import library.service.UserPersistenceStorable;
import library.service.impl.DataPersistenceServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    public static void showMenuForUser(InterfaceUser user) {

        if (user.getRole().equals("user")) {
            showMenuForOrdinaryUser(user);
        } else if (user.getRole().equals("admin")) {
            showMenuForAdmin(user);
        }
    }

    private static void showMenuForOrdinaryUser(InterfaceUser user) {

        System.out.println();
        System.out.println("\nПривет, " + user.getFio());
        Scanner scanner = new Scanner(System.in);
        int userVariant = -1;

        while (true) {
            System.out.println("1. Посмотреть каталог книг");
            System.out.println("2. Посмотреть взятые книги");
            System.out.println("3. Заглянуть в почтовый ящик");
            System.out.println("4. Предложить админу книжку");
            System.out.println("5. Взять книгу");
            System.out.println("6. Вернуть книгу");
            System.out.println("7. Выйти из учетной записи");
            System.out.print("\nВыберите желаемое действие:");
            userVariant = scanner.nextInt();

            switch (userVariant) {
                case 1:
                    showBooksCatalog();
                    break;
                case 2:
                    showTakenBooks(user);
                    break;
                case 3:
                    showEmails(user);
                    break;
                case 4:
                    promoteNewBookForLibrary();
                    break;
                case 5:
                    takeBook(user);
                    break;
                case 6:
                    returnBook(user);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Упс! Чет не то");
                    break;
            }
        }
    }

    private static void showMenuForAdmin(InterfaceUser user) {

        System.out.println("\nПривет, администратор, " + user.getFio());
        Scanner scanner = new Scanner(System.in);
        int userVariant = -1;

        while (true) {
            System.out.println("1. Посмотреть каталог книг");
            System.out.println("2. Посмотреть взятые книги");
            System.out.println("3. Заглянуть в почтовый ящик");
            System.out.println("4. Редактировать библиотеку");
            System.out.println("5. Взять книгу");
            System.out.println("6. Вернуть книгу");
            System.out.println("7. Выйти из учетной записи");
            System.out.print("\nВыберите желаемое действие:");
            userVariant = scanner.nextInt();

            switch (userVariant) {
                case 1:
                    showBooksCatalog();
                    break;
                case 2:
                    showTakenBooks(user);
                    break;
                case 3:
                    showEmails(user);
                    break;
                case 4: editLibrary();
                    break;
                case 5:
                    takeBook(user);
                    break;
                case 6:
                    returnBook(user);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Упс! Чет не то");
                    break;
            }
        }
    }

    private static void showBooksCatalog() {

        BookPersistenceStorable bps = new DataPersistenceServiceImpl();
        ArrayList<InterfaceBook> books = bps.loadBooks().getBooks();
        for (InterfaceBook book : books) {
            System.out.println(book.toString());
            System.out.println();
        }
        if(books.isEmpty()){
            System.out.println("В каталоге нет книг");
        }
    }

    private static void showTakenBooks(InterfaceUser user) {

        System.out.println("Взятые книги:");
        for (InterfaceBook book : user.getBooks()) {
            System.out.println(book);
            System.out.println();
        }
    }

    private static void showEmails(InterfaceUser user) {

        System.out.println("Почтовый ящик:");
        for (String email : user.getEmail()) {
            System.out.println(email);
            System.out.println();
        }
    }

    private static void promoteNewBookForLibrary() {

        System.out.println("Введите описание книги, которую бы хотели посоветовать:");
        Scanner scanner = new Scanner(System.in);
        String bookDescription = scanner.nextLine();

        UserPersistenceStorable ups = new DataPersistenceServiceImpl();
        ArrayList<InterfaceUser> users = ups.loadUsers();
        for (InterfaceUser user : users) {
            if (user.getRole().equals("admin")) {
                ArrayList<String> emails = user.getEmail();
                emails.add(bookDescription);
                user.setEmail(emails);
            }
        }
        ups.saveUsers(users);
    }

    private static void takeBook(InterfaceUser user) {

        BookPersistenceStorable bps = new DataPersistenceServiceImpl();
        BookStorageDAO bs = bps.loadBooks();
        ArrayList<InterfaceBook> b = bs.getBooks();

        if (b.isEmpty()) {
            System.out.println("Все книги разобрали, извините.");
            return;
        }

        for (int i = 0; i < b.size(); i++) {
            System.out.println((i + 1) + ") " + b.get(i).getAutor() + " - " + b.get(i).getTitle() + ".");
        }

        Scanner scanner = new Scanner(System.in);
        int variant = -1;

        while (variant <= 0 || variant > b.size()) {
            System.out.print("Введите номер желаемой книги: ");
            variant = scanner.nextInt();
            if (variant <= 0 || variant > b.size()) {
                System.out.print("Неверный ввод. Попробуйте еще раз.");
            }
        }

        user.getBooks().add(b.get(variant - 1));
        b.remove(variant - 1);

        bps.saveBooks(bs);

        UserPersistenceStorable ups = new DataPersistenceServiceImpl();
        ArrayList<InterfaceUser> users = ups.loadUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.remove(i);
                users.add(i, user);
                break;
            }
        }
        ups.saveUsers(users);
    }

    private static void returnBook(InterfaceUser user) {

        ArrayList<InterfaceBook> b = user.getBooks();
        if (b.isEmpty()) {
            System.out.println("У вас нет взятых книг, извините.");
            return;
        }

        for (int i = 0; i < b.size(); i++) {
            System.out.println((i + 1) + ") " + b.get(i).getAutor() + " - " + b.get(i).getTitle() + ".");
        }

        Scanner scanner = new Scanner(System.in);
        int variant = -1;

        while (variant <= 0 || variant > b.size()) {
            System.out.print("Введите номер книги: ");
            variant = scanner.nextInt();
            if (variant <= 0 || variant > b.size()) {
                System.out.print("Неверный ввод. Попробуйте еще раз.");
            }
        }

        BookPersistenceStorable bps = new DataPersistenceServiceImpl();
        BookStorageDAO bs = bps.loadBooks();
        bs.getBooks().add(user.getBooks().get(variant - 1));
        user.getBooks().remove(variant - 1);

        bps.saveBooks(bs);

        UserPersistenceStorable ups = new DataPersistenceServiceImpl();
        ArrayList<InterfaceUser> users = ups.loadUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.remove(i);
                users.add(i, user);
                break;
            }
        }
        ups.saveUsers(users);
    }

    private static void editLibrary() {

        Scanner scanner = new Scanner(System.in);
        int variant = -1;

        while (variant < 1 || variant > 2) {
            System.out.println("\nВведите номер желаемой операции: ");
            System.out.println ("1) Добавить книгу 2) Удалить книгу");
            variant = scanner.nextInt();
            if (variant < 1 || variant > 2) {
                System.out.print("Неверный ввод. Попробуйте еще раз.");
            }
        }

        BookPersistenceStorable bps = new DataPersistenceServiceImpl();
        if (variant == 1) {
            BookDAO bookDAO = createNewBook();

            BookStorageDAO bs = bps.loadBooks();
            bs.getBooks().add(bookDAO);
            bps.saveBooks(bs);
            notifyAllUsersWithEmail("Added", bookDAO);
        }
        if (variant == 2) {
            BookStorageDAO bs = bps.loadBooks();
            ArrayList<InterfaceBook> b = bs.getBooks();
            if (b.isEmpty()) {
                System.out.println("У нет книг, извините.");
                return;
            }

            for (int i = 0; i < b.size(); i++) {
                System.out.println((i + 1) + ") " + b.get(i).getAutor() + " - " + b.get(i).getTitle() + ".");
            }

            int variant1 = -1;
            while (variant1 <= 0 || variant1 > b.size()) {
                System.out.print("Введите номер книги на удаление: ");
                variant1 = scanner.nextInt();
                if (variant1 <= 0 || variant1 > b.size()) {
                    System.out.print("Неверный ввод. Попробуйте еще раз.");
                }
            }
            notifyAllUsersWithEmail("Deleted",  bs.getBooks().get(variant1));
            bs.getBooks().remove(variant1);
            bps.saveBooks(bs);
        }
    }

    private static BookDAO createNewBook() {

        System.out.println("Добавление новой книги в каталог.");
        System.out.print("Введите автора книги: ");
        Scanner reader = new Scanner(System.in);
        String newAuthBook = reader.nextLine();

        System.out.print("Введите название книги: ");
        String newTitleBook = reader.nextLine();

        System.out.print("Введите название издательства: ");
        String newPublishingHouse = reader.nextLine();

        System.out.print("Введите жанр книги: ");
        String newTypeBook = reader.nextLine();

        System.out.print("Введите краткое описание книги: ");
        String newDescriptionBook = reader.nextLine();

        BookDAO newBookDAO = new BookDAO();
        newBookDAO.setAutor(newAuthBook);
        newBookDAO.setTitle(newTitleBook);
        newBookDAO.setPublishingHouse(newPublishingHouse);
        newBookDAO.setTypeBook(newTypeBook);
        newBookDAO.setDescription(newDescriptionBook);

        BookStorageDAO addNewBook = new BookStorageDAO();
        addNewBook.getBooks().add(newBookDAO);

        return newBookDAO;
    }

    private static void notifyAllUsersWithEmail(String title, InterfaceBook book) {

        UserPersistenceStorable usp = new DataPersistenceServiceImpl();
        ArrayList<InterfaceUser> users = usp.loadUsers();
        for (InterfaceUser user : users) {
            user.getEmail().add(title + ": " +  book.getAutor() + " - " + book.getTitle() + ".");
        }
        usp.saveUsers(users);
    }
}

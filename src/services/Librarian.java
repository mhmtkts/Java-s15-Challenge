package services;

import entities.Book;
import entities.member_Record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Librarian {
    private String name;
    private String password;
    private List<Book> books;
    private Map<Integer, member_Record> members;
    private Map<Integer, Integer> borrowedBooks;
    private Map<Integer, Book> bookMap;

    public Librarian(String name, String password, List<Book> books, Map<Integer, member_Record> members) {
        this.name = name;
        this.password = password;
        this.books = books;
        this.members = members;
        this.borrowedBooks = new HashMap<>();
        this.bookMap = new HashMap<>();
        for (Book book : books) {
            bookMap.put(book.getBook_ID(), book); // Kitapları map'e ekle
        }
    }

}
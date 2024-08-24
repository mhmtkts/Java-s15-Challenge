package entities;

import java.util.LinkedList;
import java.util.List;


public class Reader extends Person{
    private List<Book> borrowedBooks;

    public Reader(String name) {
        super(name);
        borrowedBooks = new LinkedList<>();
    }

    public void purchase_book(Book book) {
        borrowedBooks.add(book);
    }

    public void borrow_book(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void showBooks() {
        for (Book book : borrowedBooks) {
            book.display();
        }
    }
    public boolean canBorrow() {
        return borrowedBooks.size() < 5;
    }

    @Override
    public String whoyouare() {
        return "I am an reader. " + getName();
    }


}

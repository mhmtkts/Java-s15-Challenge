package entities;

import java.util.ArrayList;
import java.util.List;


public class Author extends Person {
    private List<Book> books;


    public Author(String name) {
        super(name);
       this.books = new ArrayList<>();
    }

    public void new_book(Book book) {
        books.add(book);
    }

    public void show_book() {
        for (Book book : books) {
            book.display();
        }
    }
    @Override
    public String whoyouare() {
        return "Author" + getName();
    }
}

package entities;

import java.time.LocalDate;

public class Magazines extends Book {

    public Magazines(int book_ID, String author, String name, double price, boolean status, int edition, LocalDate date_of_purchase) {
        super(book_ID, author, name, price, status, edition, date_of_purchase, "Magazine");
    }

    @Override
    public void display() {
        System.out.println("Magazine - Title: " + get_title() + ", Author: " + get_author());
    }
}

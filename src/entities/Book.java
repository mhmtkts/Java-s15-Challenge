package entities;


import java.time.LocalDate;

public abstract class Book {
    private int book_ID;
    private String author;
    private String name;
    private double price;
    private boolean status;
    private int edition;
    private LocalDate date_of_purchase;
    private String category;
    private String owner;

    public Book(int book_ID, String author, String name, double price, boolean status, int edition, LocalDate date_of_purchase, String category) {
        this.book_ID = book_ID;
        this.author = author;
        this.name = name;
        this.price = price;
        this.status = status;
        this.edition = edition;
        this.date_of_purchase = date_of_purchase;
        this.category = category;


    }

    public abstract void display();

    public boolean update_status(boolean newStatus) {
        this.status = newStatus;
        return status;
    }

    public void change_owner(String newOwner) {
        this.owner = newOwner;
    }

    public String get_owner() {
        return this.owner;
    }

    public int getBook_ID() {
        return book_ID;
    }

    public String get_title() {
        return name;
    }

    public String get_author() {
        return author;
    }


    public void setBook_ID(int book_ID) {
        this.book_ID = book_ID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public LocalDate getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(LocalDate date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

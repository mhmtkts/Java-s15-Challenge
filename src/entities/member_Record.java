package entities;

import interfaces.Billable;
import interfaces.Lendable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class member_Record implements Billable, Lendable {
    private int member_id;
    private String name;
    private String address;
    private String phone_no;
    private int no_books_issued;
    private LocalDate date_of_membership;
    private String type;
    private List<Book> borrowedBooks = new ArrayList<>();
    public static final int max_book_limit = 5;

    public member_Record(int member_id, String name, String address, String phone_no, int no_books_issued, LocalDate date_of_membership, String type) {
        this.member_id = member_id;
        this.name = name;
        this.address = address;
        this.phone_no = phone_no;
        this.no_books_issued = no_books_issued;
        this.date_of_membership = date_of_membership;
        this.type = type;
    }

    public int getNo_books_issued() {
        return no_books_issued;
    }

    public int get_member() {
        return member_id;
    }

    public void incBookIssued() {
        if (this.no_books_issued < max_book_limit) {
            this.no_books_issued++;
        } else {
            System.out.println("Max kitap limitine ulaşıldı.");
        }
    }

    public void decBookIssued() {
        if (this.no_books_issued > 0) {
            this.no_books_issued--;
        }
    }

    @Override
    public boolean canLend() {
        return this.borrowedBooks.size() < max_book_limit;
    }

    public boolean borrowBook(Book book) {
        borrowedBooks.add(book);
        incBookIssued();  // Kitap sayısını artır
        return true;
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            decBookIssued();  // Kitap sayısını azalt
            return true;
        }
        return false;  // Kitap listede yoksa, iade etmeyi reddet
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setNo_books_issued(int no_books_issued) {
        this.no_books_issued = no_books_issued;
    }

    @Override
    public void payBill(double amount) {
        System.out.println(name + " üyesine " + amount + " TL fatura kesildi.");
        // Üyenin borçlandırılmasını işlemek için buraya ek kod ekleyebilirsiniz
    }

    @Override
    public void refund(double amount) {
        System.out.println(name + " üyesine " + amount + " TL iade edildi.");
        // Üyenin borcunun silinmesini işlemek için buraya ek kod ekleyebilirsiniz
    }
}


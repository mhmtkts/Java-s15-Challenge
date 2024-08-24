package services;

import entities.Book;
import entities.Student;
import entities.Faculty;
import entities.member_Record;
import interfaces.Lendable;

import java.util.*;
import java.util.stream.Collectors;

import static entities.member_Record.max_book_limit;

public class Library implements Lendable {
    private List<Book> books;
    private Set<member_Record> activeMembers;
    private Map<Integer, Book> bookMap;
    private Map<Integer, Integer> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        activeMembers = new HashSet<>();
        bookMap = new HashMap<>();
        borrowedBooks = new HashMap<>();
    }


    @Override
    public boolean canLend() {
        return borrowedBooks.size() < max_book_limit;
    }

    @Override
    public void lend_book(int bookID, member_Record member) {
        Book book = bookMap.get(bookID);

        if (book != null) {
            if (borrowedBooks.containsKey(bookID)) {
                int currentMemberId = borrowedBooks.get(bookID);
                System.out.println("Bu kitap zaten ödünç verilmiş. Şu anda Üye ID: " + currentMemberId + " tarafından ödünç alınmıştır.");
                return;
            }

            if (!member.canLend()) {
                System.out.println("Üye daha fazla kitap ödünç alamaz.");
                return;
            }

            if (book.isStatus()) {
                book.setStatus(false); // Kitap ödünç alındı, mevcut değil
                member.borrowBook(book); // Üye kitabı ödünç aldı
                borrowedBooks.put(bookID, member.get_member()); // Kitabın ödünç alındığı üye ID'sini güncelle
                member.payBill(book.getPrice());
                System.out.println("Kitap başarıyla ödünç verildi.");
            } else {
                System.out.println("Kitap mevcut değil.");
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }




    @Override
    public void take_back_book(int bookID, member_Record member) {
        Book book = bookMap.get(bookID);

        if (book != null && member.returnBook(book)) {
            borrowedBooks.remove(bookID);  // Kitap iade edildiğinde borrowedBooks'tan kaldır
            book.setStatus(true);  // Kitabı tekrar mevcut duruma getir

            if (member instanceof Student || member instanceof Faculty) {
                if (member.getBorrowedBooks().size() == 0) {
                    activeMembers.remove(member);  // Üyenin ödünç aldığı kitap kalmadıysa, aktif üyeler listesinden çıkar
                }
            }
            member.refund(book.getPrice());
            System.out.println("Kitap başarıyla iade edildi.");
        } else {
            System.out.println("Kitap tanınmadı veya bu üye tarafından ödünç alınmamış.");
        }
    }


    public void showBooks() {
        for (Book book : books) {
            book.display();
        }
    }

    public void new_book(Book book) {
        books.add(book);
        bookMap.put(book.getBook_ID(), book);
    }

    public void deleteBook(int bookID) {
        Book book = bookMap.remove(bookID);
        if (book != null) {
            books.remove(book);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public Book searchBook(int bookID) {
        // bookMap'ten kitap ID'sine göre kitabı al
        Book book = bookMap.get(bookID);

        if (book != null) {
            return book;  // Eğer kitap bulunursa geri döndür
        } else {
            return null;  // Eğer kitap bulunamazsa null döndür
        }
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.stream().filter(book -> book.getName().equalsIgnoreCase(title)).collect(Collectors.toList());
    }

    public List<Book> searchBooksByCategory(String category) {
        return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthor(String author) {
        return books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }


    public Book getBookById(int id) {
        return bookMap.get(id);
    }

    public int getBorrowedMemberId(int bookID) {
        return borrowedBooks.getOrDefault(bookID, -1); // -1, kitabın ödünç alınmadığını belirtir
    }

}

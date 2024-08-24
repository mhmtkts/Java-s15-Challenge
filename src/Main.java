import entities.*;
import services.Library;
import services.Librarian;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();
    private static final Map<Integer, member_Record> members = new HashMap<>();
    private static final Librarian librarian = new Librarian("admin", "password", new ArrayList<>(), members);

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            System.out.println("Kütüphane Sistemi Menü:");
            System.out.println("1. Yeni Kitap Ekle");
            System.out.println("2. Kitap Güncelle");
            System.out.println("3. Kitap Sil");
            System.out.println("4. Kitap Ödünç Ver");
            System.out.println("5. Tüm Kitapları Listele");
            System.out.println("6. Kategoriye Göre Kitapları Listele");
            System.out.println("7. Yazara Göre Kitapları Listele");
            System.out.println("8. Yeni Üye Ekle");
            System.out.println("9. Kitap İade Et");
            System.out.println("10. Çıkış");
            System.out.print("Bir seçenek girin: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonunu temizle

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    listAllBooks();
                    break;
                case 6:
                    listBooksByCategory();
                    break;
                case 7:
                    listBooksByAuthor();
                    break;
                case 8:
                    addNewMember();
                    break;
                case 9:
                    returnBook();
                    break;
                case 10:
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }

    private static void initializeSampleData() {
        library.new_book(new Magazines(1, "Ahmet Yılmaz", "Güncel Teknoloji", 15.0, true, 1, LocalDate.now()));
        library.new_book(new Journals(2, "Mehmet Demir", "Bilim ve Araştırma", 25.0, true, 2, LocalDate.now()));
        library.new_book(new StudyBooks(3, "Ayşe Kaya", "Matematik Temelleri", 35.0, true, 3, LocalDate.now()));
        library.new_book(new Magazines(4, "Fatma Çelik", "Spor ve Sağlık", 20.0, true, 4, LocalDate.now()));
        library.new_book(new Journals(5, "Ali Öztürk", "Ekonomi ve İş Dünyası", 30.0, true, 5, LocalDate.now()));
        library.new_book(new StudyBooks(6, "Elif Arslan", "Kimya Bilgileri", 40.0, true, 6, LocalDate.now()));
        library.new_book(new Magazines(7, "Hakan Karaca", "Seyahat Rehberi", 18.0, true, 7, LocalDate.now()));
        library.new_book(new Journals(8, "Seda Yalçın", "Tarih ve Kültür", 28.0, true, 8, LocalDate.now()));
        library.new_book(new StudyBooks(9, "Oğuz Yurt", "Fizik Prensipleri", 38.0, true, 9, LocalDate.now()));
        library.new_book(new Magazines(10, "Büşra Yılmaz", "Sanat ve Tasarım", 22.0, true, 10, LocalDate.now()));
        library.new_book(new Journals(11, "Emre Kalkan", "Psikoloji ve İnsan Davranışları", 32.0, true, 11, LocalDate.now()));
        library.new_book(new StudyBooks(12, "Gözde Demirtaş", "Bilgisayar Bilimleri", 45.0, true, 12, LocalDate.now()));
        library.new_book(new Magazines(13, "Burak Ekinci", "Güncel Moda", 25.0, true, 13, LocalDate.now()));
        library.new_book(new Journals(14, "Cemre Aydın", "Sosyal Bilimler", 35.0, true, 14, LocalDate.now()));
        library.new_book(new StudyBooks(15, "Merve Yavuz", "Ekoloji ve Çevre Bilimleri", 50.0, true, 15, LocalDate.now()));
        library.new_book(new Magazines(16, "Okan Işık", "Yemek Tarifleri", 20.0, true, 16, LocalDate.now()));
        library.new_book(new Journals(17, "Zeynep Özer", "Hukuk ve Adalet", 30.0, true, 17, LocalDate.now()));
        library.new_book(new StudyBooks(18, "Ege Korkmaz", "Biyoloji Temelleri", 55.0, true, 18, LocalDate.now()));
        library.new_book(new Magazines(19, "Bahar Acar", "Teknoloji İncelemeleri", 27.0, true, 19, LocalDate.now()));
        library.new_book(new Journals(20, "Kaan Aslan", "Mühendislik ve İnovasyon", 40.0, true, 20, LocalDate.now()));


        members.put(1, new Student(1, "Ahmet Can", "Örnek Mah. 1. Cad. No:1, İstanbul", "0550-123-4567", 0, LocalDate.now(), "Öğrenci"));
        members.put(2, new Faculty(2, "Elif Kaya", "Deneme Sok. 2, Ankara", "0541-234-5678", 0, LocalDate.now(), "Öğretim Üyesi"));
        members.put(3, new Student(3, "Mehmet Yalçın", "Küçükçekmece Mah. No:3, İstanbul", "0532-345-6789", 0, LocalDate.now(), "Öğrenci"));
        members.put(4, new Faculty(4, "Zeynep Arslan", "Yenidoğan Mah. 4, İzmir", "0521-456-7890", 0, LocalDate.now(), "Öğretim Üyesi"));
        members.put(5, new Student(5, "Ayşe Demir", "Bahçelievler Mah. No:5, Bursa", "0512-567-8901", 0, LocalDate.now(), "Öğrenci"));
        members.put(6, new Faculty(6, "Ali Çelik", "Gölcük Cad. 6, Antalya", "0501-678-9012", 0, LocalDate.now(), "Öğretim Üyesi"));
        members.put(7, new Student(7, "Fatma Yılmaz", "Cevizli Mah. 7, Eskişehir", "0490-789-0123", 0, LocalDate.now(), "Öğrenci"));
        members.put(8, new Faculty(8, "Caner Aydın", "Gazi Mah. 8, Konya", "0489-890-1234", 0, LocalDate.now(), "Öğretim Üyesi"));
        members.put(9, new Student(9, "Burcu Karaca", "Esenyurt Mah. 9, Tekirdağ", "0478-901-2345", 0, LocalDate.now(), "Öğrenci"));
        members.put(10, new Faculty(10, "Mehmet Korkmaz", "Merkez Mah. 10, Kocaeli", "0467-012-3456", 0, LocalDate.now(), "Öğretim Üyesi"));

    }

    private static void addNewBook() {
        System.out.print("Kitap ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (library.getBookById(id) != null) {
            System.out.println("Bu ID'ye sahip bir kitap zaten mevcut.");
            return;
        }
        System.out.print("Yazar Adı: ");
        String author = scanner.nextLine();
        System.out.print("Kitap Adı: ");
        String name = scanner.nextLine();
        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        System.out.print("Baskı: ");
        int edition = scanner.nextInt();
        System.out.print("Mevcut mu (true/false): ");
        boolean status = scanner.nextBoolean();
        scanner.nextLine(); // Satır sonunu temizle
        System.out.print("Satın Alma Tarihi (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Kitap türü (Magazine/Journal/StudyBook): ");
        String type = scanner.nextLine();

        Book book;
        if (type.equalsIgnoreCase("Magazine")) {
            book = new Magazines(id, author, name, price, status, edition, date);
        } else if (type.equalsIgnoreCase("Journal")) {
            book = new Journals(id, author, name, price, status, edition, date);
        } else if (type.equalsIgnoreCase("StudyBook")) {
            book = new StudyBooks(id, author, name, price, status, edition, date);
        } else {
            System.out.println("Geçersiz kitap türü.");
            return;
        }
        library.new_book(book);
        System.out.println("Kitap başarıyla eklendi.");
    }

    private static void updateBook() {
        System.out.print("Güncellemek istediğiniz Kitap ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Satır sonunu temizle

        Book book = library.searchBook(id);
        if (book != null) {
            System.out.print("Yeni Yazar: ");
            String author = scanner.nextLine();
            System.out.print("Yeni Kitap Adı: ");
            String name = scanner.nextLine();
            System.out.print("Yeni Fiyat: ");
            double price = scanner.nextDouble();
            System.out.print("Yeni Baskı: ");
            int edition = scanner.nextInt();
            System.out.print("Mevcut mu (true/false): ");
            boolean status = scanner.nextBoolean();
            scanner.nextLine(); // Satır sonunu temizle
            System.out.print("Yeni Satın Alma Tarihi (yyyy-MM-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            book.setAuthor(author);
            book.setName(name);
            book.setPrice(price);
            book.setEdition(edition);
            book.setStatus(status);
            book.setDate_of_purchase(date);

            System.out.println("Kitap başarıyla güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void deleteBook() {
        System.out.print("Silmek istediğiniz Kitap ID'sini girin: ");
        int id = scanner.nextInt();
        library.deleteBook(id);
        System.out.println("Kitap başarıyla silindi.");
    }

    private static void issueBook() {
        System.out.print("Kitap ID'sini girin (veya atlamak için 0): ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Satır sonunu temizle

        Book book = null;
        if (bookId != 0) {
            book = library.searchBook(bookId);
        }

        if (book == null) {
            System.out.print("Kitap Başlığı girin (veya atlamak için boş bırakın): ");
            String title = scanner.nextLine();

            if (!title.isEmpty()) {
                List<Book> booksByTitle = library.searchBooksByTitle(title);
                if (!booksByTitle.isEmpty()) {
                    book = booksByTitle.get(0); // İlk eşleşen kitabı alıyoruz.
                }
            }
        }

        if (book == null) {
            System.out.print("Yazar Adı girin: ");
            String author = scanner.nextLine();

            List<Book> booksByAuthor = library.searchBooksByAuthor(author);
            if (!booksByAuthor.isEmpty()) {
                book = booksByAuthor.get(0); // İlk eşleşen kitabı alıyoruz.
            }
        }

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        // Kitabın mevcut olup olmadığını kontrol et
        if (!book.isStatus()) {
            System.out.println("Kitap şu anda mevcut değil.");
            int currentMemberId = library.getBorrowedMemberId(bookId);
            if (currentMemberId != -1) {
                System.out.println("Kitap şu anda Üye ID: " + currentMemberId + " tarafından ödünç alınmıştır.");
            }
            return;
        }

        System.out.print("Üye ID'sini girin: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // Satır sonunu temizle
        member_Record member = members.get(memberId);

        if (member == null) {
            System.out.println("Üye bulunamadı.");
            return;
        }

        // Kullanıcının kitap limiti kontrol ediliyor
        if (member.getBorrowedBooks().size() >= 5) {
            System.out.println("Üye, 5 kitap limitine ulaştı.");
            return;
        }

        // Kitabı ödünç ver ve bilgileri güncelle
        library.lend_book(bookId, member);
    }


    private static void listAllBooks() {
        System.out.println("Kütüphanedeki Kitaplar:");
        library.showBooks();
    }

    private static void listBooksByCategory() {
        System.out.print("Kategori girin(Magazine,Journals,StudyBooks) : ");
        String category = scanner.nextLine();
        List<Book> categoryBooks = library.searchBooksByCategory(category);
        if (!categoryBooks.isEmpty()) {
            System.out.println("'" + category + "' kategorisindeki kitaplar:");
            categoryBooks.forEach(Book::display);
        } else {
            System.out.println("Bu kategoride kitap bulunamadı.");
        }
    }

    private static void listBooksByAuthor() {
        System.out.print("Yazar adı girin: ");
        String author = scanner.nextLine();
        List<Book> authorBooks = library.searchBooksByAuthor(author);
        if (!authorBooks.isEmpty()) {
            System.out.println("'" + author + "' tarafından yazılmış kitaplar:");
            authorBooks.forEach(Book::display);
        } else {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        }
    }

    private static void addNewMember() {
        System.out.print("Üye ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Satır sonunu temizle
        System.out.print("Üye Adı: ");
        String name = scanner.nextLine();
        System.out.print("Üye Adresi: ");
        String address = scanner.nextLine();
        System.out.print("Üye Telefonu: ");
        String phone = scanner.nextLine();
        System.out.print("Üye Limitini girin: ");
        int limit = scanner.nextInt();
        scanner.nextLine(); // Satır sonunu temizle
        System.out.print("Üye Üyelik Tarihi (yyyy-MM-dd): ");
        LocalDate membershipDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Üye Türünü girin (Student/Faculty): ");
        String type = scanner.nextLine();

        member_Record member;
        if (type.equalsIgnoreCase("Student")) {
            member = new Student(id, name, address, phone, 0, membershipDate, "Öğrenci");
        } else if (type.equalsIgnoreCase("Faculty")) {
            member = new Faculty(id, name, address, phone, 0, membershipDate, "Öğretim Üyesi");
        } else {
            System.out.println("Geçersiz üye türü.");
            return;
        }
        members.put(id, member);
        System.out.println("Üye başarıyla eklendi.");
    }

    private static void returnBook() {
        System.out.print("İade edilecek Kitap ID'sini girin: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Üye ID'sini girin: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();  // Satır sonunu temizle
        member_Record member = members.get(memberId);

        if (member == null) {
            System.out.println("Üye bulunamadı.");
            return;
        }

        Book book = library.searchBook(bookId);
        if (book == null || !member.getBorrowedBooks().contains(book)) {
            System.out.println("Kitap bulunamadı veya bu üye tarafından ödünç alınmamış.");
            return;
        }

        // Kitap iade ediliyor ve library sınıfındaki metod çağrılıyor
        library.take_back_book(bookId, member);
    }

}

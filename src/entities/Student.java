package entities;

import java.time.LocalDate;

public class Student extends member_Record{
    public Student(int member_id, String name, String address, String phone_no, int no_books_issued, LocalDate date_of_membership, String type) {
        super(member_id, name, address, phone_no, no_books_issued, date_of_membership, type);
    }


    public void lend_book(int bookID, member_Record member) {
        if (canLend()) {
            incBookIssued();
            System.out.println("Book with ID " + bookID + " lent to member " + member.get_member());
        } else {
            System.out.println("Cannot lend more books.");
        }
    }

    @Override
    public void take_back_book(int bookID, member_Record member) {
        decBookIssued();
        System.out.println("Book with ID " + bookID + " returned by member " + member.get_member());
    }
}

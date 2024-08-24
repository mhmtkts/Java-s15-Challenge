package interfaces;


import entities.member_Record;

public interface Lendable {
    boolean canLend();
    void lend_book(int bookID, member_Record member);
    void take_back_book(int bookID, member_Record member);
}

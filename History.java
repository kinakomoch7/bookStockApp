import java.util.Date;

public class History {
    Date lendDate;
    Date returnDate;
    Book book;
    User user;

    void print() {
        System.out.printf("%s, %s, %s, %d ", book.title, book.authors, book.publisher, book.publishYear);

        System.out.printf("%s(%s, %d) ", user.name, user.gender, user.age);

        System.out.printf("%s ~ %s\n", String.valueOf(lendDate), String.valueOf(returnDate));
    }
}
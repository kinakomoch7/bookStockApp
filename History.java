import java.util.Date;

public class History {
    Date lendDate;
    Date returnDate;
    Book book;
    User user;

    void print() {
        System.out.printf("%s, %s, %s, %d ", book.title, book.authors, book.publisher, book.publishYear);

        String str;
        if (!isLent()) {
            str = "(貸し出し中)";
        } else {
            str = "(配架中)";
        }
        System.out.print(str);

        System.out.printf("%s(%s, %d) ", user.name, user.gender, user.age);

        System.out.printf("%s ~ %s\n", String.valueOf(lendDate), String.valueOf(returnDate));
    }

    boolean isLent() {
        if (returnDate == null) {
            return false;
        }
        return true;
    }
}
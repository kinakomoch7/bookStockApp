import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {
    List<Book> shelf = new ArrayList<Book>();

    void run() {
        this.printWelcomeMessage();

        this.addBooks(shelf);

        List<Book> findedBook = this.findAnd("羅生門", "芥川龍之介", "青空文庫", 1997);

        for (Book book : findedBook) {
            this.remove(book);
        }
        for (Book book : shelf) {
            printBook(book);
        }

    }

    void remove(Book book) {
        int index = shelf.indexOf(book);
        shelf.remove(index);
    }

    void remove2(String title, String author, String publisher, Integer publishYear) {
        Book book = createBook(title, author, publisher, publishYear);
        shelf.remove(book);
    }

    Book find(String title) {
        for (Book book : shelf) {
            if (Objects.equals(title, book.title)) {
                return book;
            }
        }
        return null;
    }

    List<Book> findAnd(String title, String authors, String publisher, Integer publishYear) {
        List<Book> result = new ArrayList<Book>();

        for (Book book : shelf) {
            if (this.andIsMatch(book, title, authors, publisher, publishYear)) {
                result.add(book);
            }
        }
        return result;
    }

    List<Book> findOr(String title, String authors, String publisher, Integer publishYear) {
        List<Book> result = new ArrayList<Book>();

        for (Book book : shelf) {
            if (this.orIsMatch(book, title, authors, publisher, publishYear)) {
                result.add(book);
            }
        }
        return result;
    }

    Boolean andIsMatch(Book book, String title, String authors,
            String publisher, Integer publishYear) {
        if (book == null) {
            return false;
        }
        if (Objects.equals(title, book.title) && Objects.equals(authors, book.authors)
                && Objects.equals(publisher, book.publisher) && Objects.equals(publishYear, book.publishYear)) {
            return true;
        }
        return false;
    }

    Boolean orIsMatch(Book book, String title, String authors,
            String publisher, Integer publishYear) {
        if (book == null) {
            return false;
        }
        if (Objects.equals(title, book.title) && Objects.equals(authors, book.authors)
                && Objects.equals(publisher, book.publisher) && Objects.equals(publishYear, book.publishYear)) {
            return true;
        }
        return false;
    }

    void printBook(Book book) {
        System.out.printf("%s (%s) %s, %d%n",
                book.title, book.authors, book.publisher, book.publishYear);
    }

    void addBooks(List<Book> shelf) {
        Book book1 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);
        shelf.add(book1);
        Book book2 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);
        shelf.add(book2);
        Book book3 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);
        shelf.add(book3);
        Book book4 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);
        shelf.add(book4);
    }

    void addBooksCsv(List<Book> shelf) {
        LibraryUtil util = new LibraryUtil();
        List<Book> books = util.readFromFile("books.csv");
        for (Integer i = 0; i < books.size(); i++) {
            shelf.add(books.get(i));
        }
    }

    Book createBook(String title, String authors, String publisher, Integer publishYear) {
        Book book = new Book();
        book.title = title;
        book.authors = authors;
        book.publisher = publisher;
        book.publishYear = publishYear;

        return book;
    }

    void printWelcomeMessage() {
        System.out.println("図書館システムへようこそ．");
    }

    public static void main(String[] args) {
        Library lib = new Library();
        lib.run();
    }
}

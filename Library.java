import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Library {
    List<Book> shelf = new ArrayList<Book>();
    List<User> manager = new ArrayList<User>();
    Map<Book, List<History>> historyMap = new HashMap<Book, List<History>>();

    void run() {
        this.printWelcomeMessage();

        this.addBooks();
        this.registerUsers();

        Iterator<User> iterator = manager.iterator();
        while (iterator.hasNext()) {
            User aUser = iterator.next();
            System.out.printf("%s %s, %d%n", aUser.name, aUser.gender, aUser.age);
        }

        for (Book book : shelf) {
            printBook(book);
        }

        runLend();

        runFindHistory(findHistory(null, null));
    }

    void runFindHistory(List<History> histories) {
        for (History hist : histories) {
            System.out.println(hist);
        }
    }

    List<History> findHistory(Book book, User user) {
        if (book != null) {
            for (Map.Entry<Book, List<History>> entry : historyMap.entrySet()) {
                if (Objects.equals(book, entry.getKey())) {
                    return entry.getValue();
                }
            }
        } else {
            for (List<History> list : historyMap.values()) {
                System.out.print(list);
            }
        }
        return null;
    }

    void runLend() {
        // User user1 = this.createUser("小出朋希", "男", 21);
        // User user2 = this.createUser("小出朋希", "男", 21);
        // Book book1 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);
        // Book book2 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);
        // Book book3 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);
        // Book book4 = this.createBook("羅生門", "芥川龍之介", "青空文庫", 1997);

        History history1 = this.createHistory(manager.get(1), shelf.get(1));
        History history2 = this.createHistory(manager.get(0), shelf.get(2));
        History history3 = this.createHistory(manager.get(1), shelf.get(3));
    }

    History lend(User user, Book book) {
        History createdHistory = createHistory(user, book);
        if (registerHistory(createdHistory)) {
            return createdHistory;
        } else {
            return null;
        }
    }

    boolean registerHistory(History history) {
        List<History> histories = historyMap.get(history.book);
        if (histories == null) {
            histories = new ArrayList<History>();
            historyMap.put(history.book, histories);
        }
        if (this.canLend(history, histories)) {
            histories.add(history);
            return true;
        }
        return false;
    }

    Boolean canLend(History history,
            List<History> histories) {
        if (!shelf.contains(history.book)) {
            return false;
        }
        if (histories.size() > 0) {
            History lastHistory = histories.get(histories.size() - 1);
            if (lastHistory.isLent()) {
                return false;
            }
        }
        return true;
    }

    History createHistory(User user, Book book) {
        HistoryCreator createHistory = new HistoryCreator();
        History history = createHistory.createHistory(book, user);
        history.lendDate = new Date();
        return history;
    }

    void registerUsers() {
        User user1 = this.createUser("小出朋希", "男", 21);
        manager.add(user1);
        User user2 = this.createUser("小出朋希", "男", 21);
        manager.add(user2);
    }

    User createUser(String name, String gender, Integer age) {
        User user = new User();
        user.name = name;
        user.gender = gender;
        user.age = age;

        return user;
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
        System.out.printf("%s (%s) %s, %d%n", book.title, book.authors, book.publisher, book.publishYear);
    }

    void addBooks() {
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

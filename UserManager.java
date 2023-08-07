import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class UserManager {
    List<User> myself = new ArrayList<User>();

    void run() {
        User user = new User();
        user.name = "石川翼";
        user.gender = "男";
        user.age = 20;
        for (Integer i = 0; i < 5; i++) {
            this.add(user);
        }

        List<User> foundUsers = find("石川翼", "男", 20);
        for (User fUser : foundUsers) {
            print(fUser);
        }
        List<User> noFoundUsers = find("石川翼", "男", 30);
        for (User nofUser : noFoundUsers) {
            print(nofUser);
        }

        for (User allUser : myself) {
            print(allUser);
        }

        Iterator<User> iterator = this.iterator();
        while (iterator.hasNext()) {
            User aUser = iterator.next();
            print(aUser);
        }

    }

    public Iterator<User> iterator() {
        return myself.iterator();
    }

    User create(String name, String gender, Integer age) {
        User user = new User();
        user.name = name;
        user.gender = gender;
        user.age = age;

        return user;
    }

    void print(User user) {
        System.out.printf("%s %s, %d%n",
                user.name, user.gender, user.age);
    }

    Integer size() {
        return myself.size();
    }

    void add(User user) {
        User addUser = this.create(user.name, user.gender, user.age);
        myself.add(addUser);
    }

    List<User> find(String name, String gender, Integer age) {
        List<User> result = new ArrayList<User>();

        for (User user : myself) {
            if (this.orIsMatch(user, name, gender, age)) {
                result.add(user);
            }
        }
        return result;
    }

    Boolean orIsMatch(User user, String name, String gender, Integer age) {
        if (user == null) {
            return false;
        }
        if (Objects.equals(name, user.name) || Objects.equals(gender, user.gender) || Objects.equals(age, user.age)) {
            return true;
        }
        return false;
    }

    Boolean delete(User user) {
        int index = -1;
        index = myself.indexOf(user);
        if (index != -1) {
            myself.remove(index);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        UserManager us = new UserManager();
        us.run();
    }

}
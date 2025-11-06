package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Galina", "Fokina", (byte) 28);
        userService.saveUser("Vyacheslav", "Smirnov", (byte) 48);
        userService.saveUser("Vyachelina", "Yacht",  (byte) 47);
        userService.saveUser("Tuzik", "Tuzikov",  (byte) 5);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

       userService.cleanUsersTable();

       userService.dropUsersTable();
    }
}
package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age TINYINT, " +
                "PRIMARY KEY (id))";
        try (Connection connection = Util.getConnection();
            Statement statement = connection != null ? connection.createStatement() : null) {
            if (statement != null) {
                statement.execute(sql);
            } else {
                System.out.println("createUsersTable: Connection is null");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы users");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
            Statement statement = connection != null ? connection.createStatement() : null) {
            if (statement != null) {
                statement.execute(sql);
            } else {
                System.out.println("dropUsersTable: Connection is null");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы users");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
            PreparedStatement prStmt = connection != null ? connection.prepareStatement(sql) : null) {
            if (prStmt != null) {
                prStmt.setString(1, name);
                prStmt.setString(2, lastName);
                prStmt.setByte(3, age);
                prStmt.executeUpdate();
                System.out.println("User с именем - " + name + " добавлен в базу данных");
            } else  {
                System.out.println("saveUser: Connection is null");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
            PreparedStatement prStmt = connection != null ? connection.prepareStatement(sql) : null) {
            if (prStmt != null) {
                prStmt.setLong(1, id);
                prStmt.executeUpdate();
            } else {
                System.out.println("removeUserById: Connection is null");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя по id");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT id, name, lastName, age FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
            Statement statement = connection != null ? connection.createStatement() : null;
            ResultSet resultSet = statement != null ? statement.executeQuery(sql) : null) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }
            } else {
                System.out.println("getAllUsers: Connection or ResultSet is null");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех пользователей");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();
        Statement statement = connection != null ? connection.createStatement() : null) {
            if (statement != null) {
                statement.execute(sql);
            } else {
                System.out.println("cleanUsersTable: Connection is null");
            }
        }  catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы users");
            e.printStackTrace();
        }
    }
}
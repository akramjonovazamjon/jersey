package org.example.repository;

import org.example.dto.CreateUser;
import org.example.dto.UpdateUser;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements BaseRepository {

    public User create(CreateUser dto) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection.prepareStatement("INSERT INTO users(first_name, last_name, phone_number, age) VALUES (?,?,?,?)");
                PreparedStatement preparedStatement1
                        = connection.prepareStatement("SELECT * FROM users WHERE phone_number = ?")
        ) {
            preparedStatement.setString(1, dto.firstName());
            preparedStatement.setString(2, dto.lastName());
            preparedStatement.setString(3, dto.phoneNumber());
            preparedStatement.setInt(4, dto.age());
            preparedStatement.execute();


            preparedStatement1.setString(1, dto.phoneNumber());
            ResultSet resultSet = preparedStatement1.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User(resultSet);
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAll() {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection.prepareStatement("SELECT * FROM users")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(resultSet));
            }
            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public User getById(Long id) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection.prepareStatement("SELECT * FROM users WHERE id = ?")
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = null;
            while (resultSet.next()) {
                user = new User(resultSet);
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(Long id) {

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection.prepareStatement("DELETE FROM users WHERE id = ?")
        ) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Long id, UpdateUser dto) {

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection.prepareStatement("UPDATE users SET first_name = ? , last_name = ?, phone_number = ?, age = ? WHERE id = ?")
        ) {

            preparedStatement.setString(1, dto.firstName());
            preparedStatement.setString(2, dto.lastName());
            preparedStatement.setString(3, dto.phoneNumber());
            preparedStatement.setInt(4, dto.age());
            preparedStatement.setLong(5, id);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

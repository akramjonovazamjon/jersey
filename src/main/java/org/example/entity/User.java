package org.example.entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer age;

    public User(ResultSet resultSet) {
        try {
            this.id = resultSet.getLong("id");
            this.firstName = resultSet.getString("first_name");
            this.lastName = resultSet.getString("last_name");
            this.phoneNumber = resultSet.getString("phone_number");
            this.age = resultSet.getInt("age");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

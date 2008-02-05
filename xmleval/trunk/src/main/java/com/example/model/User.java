package com.example.model;

import org.joda.time.LocalDate;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class User {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthday;

    public User(Long id, String firstName, String middleName, String lastName, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id: [").append(id).append("], ")
                .append("firstName: [").append(firstName).append("], ")
                .append("middleName: [").append(middleName).append("], ")
                .append("lastName: [").append(lastName).append("], ")
                .append("birthday: [").append(birthday).append("]")
                .toString();
    }
}

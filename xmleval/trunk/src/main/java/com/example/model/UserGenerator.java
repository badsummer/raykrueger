package com.example.model;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * DOCUMENT ME!
 *
 * @author Ray Krueger
 */
public class UserGenerator {

    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<User>();
        for (int i = 1; i < count + 1; i++) {
            users.add(createUser(i));
        }
        return users;
    }

    private static User createUser(int i) {
        return new User((long) i, randomString(), randomString(), randomString(), new LocalDate(i * 10000099999L));
    }

    private static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws Exception {
        List<User> list = generateUsers(100);
        for (User user : list) {
            System.out.println("user: [" + user + "]");
        }
    }


}

package com.springproject.artifact;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public List<User> getUsers() {

        return List.of(
                new User("John", "1234"),
                new User("Fred", "4321")

        );

    }

}

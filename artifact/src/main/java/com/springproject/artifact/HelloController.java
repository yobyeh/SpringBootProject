package com.springproject.artifact;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public List<User> hello() {

        return List.of(
                new User("John", "1234"),
                new User("Fred", "4321")

        );

    }

}

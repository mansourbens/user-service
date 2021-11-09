package com.mansourbens.user.controller;

import com.mansourbens.user.UserNotFoundException;
import com.mansourbens.user.VO.ResponseTemplateVO;
import com.mansourbens.user.entity.User;
import com.mansourbens.user.exceptions.WebApplicationException;
import com.mansourbens.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Saving user " + user.getFirstName());
        return userService.saveUser(user);
    }

    @GetMapping("{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) throws WebApplicationException {
        log.info("Getting user " + userId);
        try {
            return userService.getUserWithDepartment(userId);
        } catch (NoSuchElementException e) {
            throw new WebApplicationException(100, "Technical error", "500");
        }
    }
}

package com.alirf.finance.controllers;

import com.alirf.finance.models.User;
import com.alirf.finance.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(required = false) String name,
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10) Pageable page
    ) {
        Page<User> users = this.userService.find(name, page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Integer id
    ) {
        User user = this.userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody User user
    ) {
        User createdUser = this.userService.insert(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody User user
    ) {
        User updatedUser = this.userService.update(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

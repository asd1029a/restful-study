package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * UserController.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.20
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User findUser = userDaoService.findOne(id);
        if (findUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return findUser;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser =userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updateUser = userDaoService.update(id, user);

        if (updateUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        boolean isDeleted = userDaoService.deleteUser(id);

        if (!isDeleted) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}

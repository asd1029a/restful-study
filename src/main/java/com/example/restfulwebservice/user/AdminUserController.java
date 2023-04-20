package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
@RequestMapping("/admin")
public class AdminUserController {

    private final UserDaoService userDaoService;

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {

        List<User> users = userDaoService.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "password");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filterProvider);
        return mapping;
    }

//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=1")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User findUser = userDaoService.findOne(id);
        if (findUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(findUser);
        mapping.setFilters(filterProvider);
        return mapping;
    }

//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=2")
//@GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User findUser = userDaoService.findOne(id);
        if (findUser == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(findUser, userV2);
        userV2.setGrade("VIP");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}

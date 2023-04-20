package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * User.java
 * Class 설명을 작성하세요.
 *
 * @author kjm
 * @since 2023.04.20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
public class User {

    private Integer id;

    @Size(min = 2, max = 13, message = "2에서 13사이로만 입력 가능합니다.")
    private String name;

    @Past
    private Date joinDate;

//    @JsonIgnore
    private String password;

//    @JsonIgnore
    private String ssn;
}

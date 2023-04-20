package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
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
@JsonFilter("UserInfoV2")
public class UserV2 extends User {

    private String grade;

}

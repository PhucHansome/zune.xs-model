package com.cg.model.dto;

import com.cg.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO  {

    private Long id;

    @NotBlank(message = "The email is required")
    @Email(message = "The email address is invalid")
    @Size(max = 50, message = "The length of email must be between 5 and 50 characters")
    private String username;

    @NotBlank(message = "The password is required")
    @Size(max = 30, message = "Maximum password length 30 characters")
    private String password;

    @Valid
    private RoleDTO role;

    private Date createdAt;

    private Date updatedAt;


    @NotBlank(message = "Trạng thái không được để trống")
    private String status;

//    @NotBlank(message = "Url Ảnh không được để trống")
    private String image;

    public UserDTO(Long id, String username, String status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

    public UserDTO(Long id, String username, Role role, String status, String image) {
        this.id = id;
        this.username = username;
//        this.password = password;
        this.role = role.toRoleDTO();
        this.status = status;
        this.image = image;
    }

    public UserDTO(Long id, String username,String password, Role role, String status, String image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role.toRoleDTO();
        this.status = status;
        this.image = image;
    }


    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRole(role.toRole())
                .setStatus(status)
                .setImage(image)
                ;

    }

}

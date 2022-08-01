package com.cg.controller.rest;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.jwt.JwtService;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AppUtil appUtils;


    @GetMapping
    public ResponseEntity<?> showListUser(){
        List<UserDTO> userDTOList = userService.findAllUserDTOByDeletedIsFailse();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()){
            throw  new ResourceNotFoundException("Invalid User Id");
        }
        return new ResponseEntity<>(userOptional.get().toUserDTO(),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?>updatePassword(@Validated @RequestBody UserDTO userDTO,BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        boolean existId = userService.existsById(userDTO.getId());
        if (!existId) {
            throw new ResourceNotFoundException("Customer ID invalid");
        }
        User user = userService.save(userDTO.toUser());
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/name/{username}")
    public ResponseEntity<?> getUsernameByName(@PathVariable String username){
        Optional<User> userOptional = userService.findByUsername(username);
        if(!userOptional.isPresent()){
            throw  new ResourceNotFoundException("Invalid UserName");
        }
        return new ResponseEntity<>(userOptional.get().toUserDTO(),HttpStatus.OK);
    }
}
package com.validation.service;


import com.validation.dto.UserRequest;
import com.validation.entity.User;
import com.validation.exception.UserNotFoundException;
import com.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User saveUser(UserRequest request) {
        User user = User
                .build(0, request.getName(), request.getEmail(), request.getMobile(), request.getGender()
                        , request.getAge(), request.getNationality());
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUser(int id) throws UserNotFoundException {
        User user =  repo.findByUserId(id);
        if(user!= null){
            return user;
        }else{
            throw new UserNotFoundException("User not found with the Id: "+id);
        }
    }


}

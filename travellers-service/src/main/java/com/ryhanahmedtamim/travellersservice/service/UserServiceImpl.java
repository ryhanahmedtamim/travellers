package com.ryhanahmedtamim.travellersservice.service;

import com.ryhanahmedtamim.travellersservice.entity.UserEntity;
import com.ryhanahmedtamim.travellersservice.model.User;
import com.ryhanahmedtamim.travellersservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public String registerUser(User user) {

        if(emailExists(user.getEmail()))
        {
            return "This email already exist";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setRole("user");
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.saveAndFlush(userEntity);

        return "Successfully Register user";
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}

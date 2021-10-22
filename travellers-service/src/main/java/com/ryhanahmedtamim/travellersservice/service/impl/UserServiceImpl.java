package com.ryhanahmedtamim.travellersservice.service.impl;

import com.ryhanahmedtamim.travellersservice.entity.UserEntity;
import com.ryhanahmedtamim.travellersservice.model.User;
import com.ryhanahmedtamim.travellersservice.repository.UserRepository;
import com.ryhanahmedtamim.travellersservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Action;

@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(User user) {

        if(emailExists(user.getEmail()))
        {
            log.error("This email already exist");
            return "This email already exist";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setRole("user");
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.saveAndFlush(userEntity);
        log.info("Successfully Register user");
        return "Successfully Register user";
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}

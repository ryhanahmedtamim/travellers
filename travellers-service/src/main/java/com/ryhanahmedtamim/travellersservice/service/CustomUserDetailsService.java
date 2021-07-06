package com.ryhanahmedtamim.travellersservice.service;

import com.ryhanahmedtamim.travellersservice.entity.UserEntity;
import com.ryhanahmedtamim.travellersservice.model.CustomUserDetails;
import com.ryhanahmedtamim.travellersservice.model.User;
import com.ryhanahmedtamim.travellersservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(userEntity);
    }
}

package com.ryhanahmedtamim.travellersservice.service.impl;

import com.ryhanahmedtamim.travellersservice.entity.StatusEntity;
import com.ryhanahmedtamim.travellersservice.entity.UserEntity;
import com.ryhanahmedtamim.travellersservice.model.CustomUserDetails;
import com.ryhanahmedtamim.travellersservice.model.Status;
import com.ryhanahmedtamim.travellersservice.repository.StatusRepository;
import com.ryhanahmedtamim.travellersservice.repository.UserRepository;
import com.ryhanahmedtamim.travellersservice.service.StatusPosterService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StatusPosterServiceImpl implements StatusPosterService {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public String postStatus(Status status) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();
        int userId = customUser.getId();
        log.debug("status {} ", status);
        log.debug("status by :{}", customUser.getFullName());
        log.debug("user Id : {}" , userId);


        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setLocation(status.getLocation());
        statusEntity.setMessage(status.getMessage());
        statusEntity.setPrivacy(status.getPrivacy());
        statusEntity.setCreatedUserId(userId);
        statusEntity.setCreatedDate(new Date());
        statusEntity.setUpdatedDate(new Date());

        statusRepository.saveAndFlush(statusEntity);

        return "Successfully Added";
    }

    @Override
    public List<Status> getAllPublicStatus() {

        List<StatusEntity> statusEntities = statusRepository.findAllByPrivacy(1);

        List<Status> statusList = new ArrayList<>();

        statusEntities.stream().forEach(statusEntity -> {
            Status status = new Status();
            status.setLocation(statusEntity.getLocation());
            status.setMessage(statusEntity.getMessage());
            Optional<UserEntity> userEntity = userRepository.findById(statusEntity.getCreatedUserId());
            status.setCreatedBy(userEntity.get().getName());
            statusList.add(status);
        });


        return statusList;
    }

    @Override
    public List<Status> getAllStatusByUserId(Integer id) {

        List<StatusEntity> statusEntities = statusRepository.findAllByCreatedUserIdOrderByUpdatedDate(id);

        List<Status> statusList = new ArrayList<>();

        statusEntities.stream().forEach(statusEntity -> {
            Status status = new Status();
            status.setLocation(statusEntity.getLocation());
            status.setMessage(statusEntity.getMessage());
            Optional<UserEntity> userEntity = userRepository.findById(statusEntity.getCreatedUserId());
            status.setCreatedBy(userEntity.get().getName());
            statusList.add(status);
        });
        return statusList;
    }

    @Override
    public String editStatus(Status status) {
        return null;
    }
}

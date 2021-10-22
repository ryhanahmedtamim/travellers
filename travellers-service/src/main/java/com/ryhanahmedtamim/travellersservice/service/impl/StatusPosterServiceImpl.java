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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StatusPosterServiceImpl implements StatusPosterService {

    StatusRepository statusRepository;
    UserRepository userRepository;

    public StatusPosterServiceImpl(StatusRepository statusRepository, UserRepository userRepository){
       this.statusRepository = statusRepository;
       this.userRepository = userRepository;
    }

    @Override
    public String postStatus(Status status) {

        log.debug("status {} ", status);
        if(status.getLocation() == null || status.getPrivacy() == null){
            log.error("Status id or location or privacy is null");
            return "Status id or location or privacy cannot be null";
        }

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

        List<StatusEntity> statusEntities = statusRepository.findAllByPrivacyOrderByUpdatedDateDesc(1);

        List<Status> statusList = new ArrayList<>();

        statusEntities.stream().forEach(statusEntity -> {
            Status status = new Status();
            status.setLocation(statusEntity.getLocation());
            status.setMessage(statusEntity.getMessage());
            Optional<UserEntity> userEntity = userRepository.findById(statusEntity.getCreatedUserId());
            status.setCreatedBy(userEntity.get().getName());
            status.setUpdatedDate(statusEntity.getUpdatedDate());
            statusList.add(status);
        });


        return statusList;
    }

    @Override
    public List<Status> getAllStatusByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();
        int userId = customUser.getId();
        log.debug("status by :{}", customUser.getFullName());
        log.debug("user Id : {}" , userId);

        List<StatusEntity> statusEntities = statusRepository.findAllByCreatedUserIdOrderByUpdatedDateDesc(userId);


        List<Status> statusList = new ArrayList<>();

        statusEntities.stream().forEach(statusEntity -> {
            Status status = new Status();
            status.setId(statusEntity.getId());
            status.setLocation(statusEntity.getLocation());
            status.setMessage(statusEntity.getMessage());
            status.setPrivacy(statusEntity.getPrivacy());
            Optional<UserEntity> userEntity = userRepository.findById(statusEntity.getCreatedUserId());
            status.setCreatedBy(userEntity.get().getName());
            status.setUpdatedDate(statusEntity.getUpdatedDate());
            statusList.add(status);
        });
        return statusList;
    }

    @Override
    public String editStatus(Status status) {

        log.debug("status {} ", status);
        if(status.getId() == null || status.getLocation() == null || status.getPrivacy() == null){
            log.error("Status id or location or privacy is null");
            return "Status id or location or privacy cannot be null";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUser = (CustomUserDetails)authentication.getPrincipal();
        int userId = customUser.getId();


        log.debug("status by :{}", customUser.getFullName());
        log.debug("user Id : {}" , userId);

        StatusEntity statusEntity = statusRepository.findByIdAndAndCreatedUserId(status.getId(), userId);

        if(statusEntity == null){
            log.error("This status is postedBy This user {}", userId);
            return "This status is postedBy This user";
        }

        statusEntity.setLocation(status.getLocation());
        statusEntity.setMessage(status.getMessage());
        statusEntity.setPrivacy(status.getPrivacy());
        statusEntity.setUpdatedDate(new Date());

        statusRepository.saveAndFlush(statusEntity);

        return "Successfully Updated";
    }
}

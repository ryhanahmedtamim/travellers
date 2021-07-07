package com.ryhanahmedtamim.travellersservice.service;

import com.ryhanahmedtamim.travellersservice.model.Status;

import java.util.List;

public interface StatusPosterService {
    public String postStatus(Status status);
    public List<Status> getAllPublicStatus();
    public List<Status> getAllStatusByUserId();
    public String editStatus(Status status);
}

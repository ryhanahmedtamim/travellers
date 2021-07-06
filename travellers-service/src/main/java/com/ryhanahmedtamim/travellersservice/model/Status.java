package com.ryhanahmedtamim.travellersservice.model;

import lombok.Data;

@Data
public class Status {

    private String message;
    //0 private, 1 public
    private Integer privacy;
    private String Location;
    private String createdBy;
}

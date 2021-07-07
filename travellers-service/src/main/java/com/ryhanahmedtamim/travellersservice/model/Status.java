package com.ryhanahmedtamim.travellersservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Status {
    private Integer id;
    private String message;
    //0 private, 1 public
    private Integer privacy;
    private String location;
    private String createdBy;
    private Date updatedDate;
}

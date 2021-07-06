package com.ryhanahmedtamim.travellersservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "stastus_message", length = 2000)
    private String message;

    @Column(name = "created_by")
    private Integer createdUserId;

    //0 private, 1 public
    @Column(name = "privacy")
    private Integer privacy;

    @Column(name = "location")
    private String Location;

    @Column(name = "created_data")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

}

package com.ryhanahmedtamim.travellersservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "stastus", length = 2000)
    private String status;

    //0 private, 1 public
    @Column(name = "privacy")
    private Integer privacy;

    @Column(name = "location")
    private String Location;

}

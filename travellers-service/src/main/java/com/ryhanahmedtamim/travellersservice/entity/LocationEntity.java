package com.ryhanahmedtamim.travellersservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Data
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "location_name")
    private String locationName;
}

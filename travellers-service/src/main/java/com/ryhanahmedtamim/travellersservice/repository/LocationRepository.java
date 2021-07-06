package com.ryhanahmedtamim.travellersservice.repository;

import com.ryhanahmedtamim.travellersservice.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
}

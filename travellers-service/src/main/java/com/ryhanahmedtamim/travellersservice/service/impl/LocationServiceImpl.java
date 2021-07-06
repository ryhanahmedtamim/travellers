package com.ryhanahmedtamim.travellersservice.service.impl;

import com.ryhanahmedtamim.travellersservice.entity.LocationEntity;
import com.ryhanahmedtamim.travellersservice.model.Location;
import com.ryhanahmedtamim.travellersservice.repository.LocationRepository;
import com.ryhanahmedtamim.travellersservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        List<LocationEntity> locationEntities = locationRepository.findAll();

        List<Location> locations = new ArrayList<>();

        for(LocationEntity locationEntity : locationEntities){
            Location location = new Location();
            location.setLocationName(locationEntity.getLocationName());
            location.setId(locationEntity.getId());
            locations.add(location);
        }
        return locations;
    }
}

package com.ryhanahmedtamim.travellersservice.service.impl;

import com.ryhanahmedtamim.travellersservice.entity.LocationEntity;
import com.ryhanahmedtamim.travellersservice.model.Location;
import com.ryhanahmedtamim.travellersservice.repository.LocationRepository;
import com.ryhanahmedtamim.travellersservice.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

    LocationRepository locationRepository;
    public LocationServiceImpl(LocationRepository locationRepository){
      this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        List<LocationEntity> locationEntities = locationRepository.findAll();

        List<Location> locations = new ArrayList<>();

        if(locationEntities.size() == 0){
            locationEntities = new ArrayList<LocationEntity>();

            LocationEntity locationKhulna= new LocationEntity();
            locationKhulna.setLocationName("Khulna");
            LocationEntity locationDhaka= new LocationEntity();
            locationDhaka.setLocationName("Dhaka");
            LocationEntity locationSyleth= new LocationEntity();
            locationSyleth.setLocationName("Syleth");
            LocationEntity locationGopalganj= new LocationEntity();
            locationGopalganj.setLocationName("Gopalganj");

            locationEntities.add(locationKhulna);
            locationEntities.add(locationDhaka);
            locationEntities.add(locationSyleth);
            locationEntities.add(locationGopalganj);
            try {
                locationRepository.saveAll(locationEntities);
            }
            catch (Exception e) {
                log.error("Data update failed");
               log.error(e.getMessage());
            }
        }

        locationEntities.stream().forEach(locationEntity->{
            Location location = new Location();
            location.setLocationName(locationEntity.getLocationName());
            location.setId(locationEntity.getId());
            locations.add(location);
        });
        return locations;
    }
}

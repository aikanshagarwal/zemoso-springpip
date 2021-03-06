package com.example.springassignment2.service;

import com.example.springassignment2.dao.LocationRepository;
import com.example.springassignment2.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository theLocationRepository)
    {
        locationRepository = theLocationRepository;
    }

    @Override
    public List<Location> findAll()
    {
            return locationRepository.findAll();
    }

    @Override
    public Location save(Location theLocation) {
        return locationRepository.save(theLocation);
    }


    @Override
    public List<Location> findLocationsByJobsId(int theId) {
        return locationRepository.findLocationsByJobsId(theId);
    }
}

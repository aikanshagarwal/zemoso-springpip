package com.example.springassignment2.SpringAssignment2.service;

import com.example.springassignment2.SpringAssignment2.entity.Location;
import com.example.springassignment2.SpringAssignment2.entity.Skill;

import java.util.List;

public interface LocationService
{
    public List<Location> findAll();

    public Location save(Location theLocation);

    public List<Location> findLocationsByJobsId(int theId);
}

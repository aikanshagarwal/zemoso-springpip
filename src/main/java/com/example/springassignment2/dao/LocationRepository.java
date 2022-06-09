package com.example.springassignment2.dao;

import com.example.springassignment2.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer>
{
    //finding locations given a job id
    public List<Location> findLocationsByJobsId(int theId);
}

package com.example.springassignment2.SpringAssignment2.dao;

import com.example.springassignment2.SpringAssignment2.entity.Location;
import com.example.springassignment2.SpringAssignment2.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer>
{
    public List<Location> findLocationsByJobsId(int theId);
}

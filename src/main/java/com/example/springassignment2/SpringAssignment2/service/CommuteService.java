package com.example.springassignment2.SpringAssignment2.service;

import com.example.springassignment2.SpringAssignment2.entity.Commute;
import com.example.springassignment2.SpringAssignment2.entity.Location;

import java.util.List;

public interface CommuteService
{
    public Commute findById(int theId);

    public Commute save(Commute theCommute);

    public void deleteById(int theId);

    public List<Commute> findByJobId(int theId);
}

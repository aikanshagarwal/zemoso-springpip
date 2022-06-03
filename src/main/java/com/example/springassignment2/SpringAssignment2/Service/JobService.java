package com.example.springassignment2.SpringAssignment2.Service;

import com.example.springassignment2.SpringAssignment2.Entity.Job;
import com.example.springassignment2.SpringAssignment2.Entity.User;

import java.util.List;

public interface JobService
{
    public List<Job> findAll();

    public Job findById(int theId);

    public Job save(Job theJob);

    public void deleteById(int theId);

    public List<Job> findJobsBySkillsId(int[] theIds);
}

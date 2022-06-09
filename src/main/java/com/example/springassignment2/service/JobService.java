package com.example.springassignment2.service;

import com.example.springassignment2.entity.Job;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobService
{
    public List<Job> findAll();

    public Job findById(int theId);

    public Job save(Job theJob);

    public void deleteById(int theId);

    public List<Job> findByLocation(String location);

    List<Job> findBySkill(String skill);

    List<Job> findBySkillAndLocation(String skill2,String location2);

    List<Job> showSavedJobs(String userName);

    void saveAJob(@Param("jobId") int jobId, @Param("userName") String userName);

    void unsaveAJob(@Param("jobId") int jobId, @Param("userName") String userName);
}

package com.example.springassignment2.service;

import com.example.springassignment2.dao.JobRepository;
import com.example.springassignment2.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService
{

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository theJobRepository)
    {
        jobRepository = theJobRepository;
    }

    @Override
    public List<Job> findAll()
    {
        return jobRepository.findAll();
    }

    @Override
    public Job findById(int theId)
    {
        Optional<Job> savedJob = jobRepository.findById(theId);
        Job theJob = null;

        if (savedJob.isPresent())
        {
            theJob = savedJob.get();
        }
        else
        {
            // we didn't find the job
            throw new RuntimeException("Did not find job with id - " + theId);
        }

        return theJob;
    }

    @Override
    public Job save(Job theJob)
    {
        return jobRepository.save(theJob);
    }

    @Override
    public void deleteById(int theId)
    {
        jobRepository.deleteById(theId);
    }


    @Override
    public List<Job> findByLocation(String location) {
        return jobRepository.findByLocation(location);
    }

    @Override
    public List<Job> findBySkill(String skill) {
        return jobRepository.findBySkill(skill);
    }

    @Override
    public List<Job> findBySkillAndLocation(String skill2, String location2) {
        return jobRepository.findBySkillAndLocation(skill2,location2);
    }

    @Override
    public List<Job> showSavedJobs(String userName) {
        return jobRepository.showSavedJobs(userName);
    }

    @Override
    public void saveAJob(int jobId, String userName) {
        jobRepository.saveAJob(jobId,userName);

    }

    @Override
    public void unsaveAJob(int jobId, String userName) {
        jobRepository.unsaveAJob(jobId,userName);
    }


}

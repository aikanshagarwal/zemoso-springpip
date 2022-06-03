package com.example.springassignment2.SpringAssignment2.Service;

import com.example.springassignment2.SpringAssignment2.Dao.JobRepository;
import com.example.springassignment2.SpringAssignment2.Dao.UserRepository;
import com.example.springassignment2.SpringAssignment2.Entity.Job;
import com.example.springassignment2.SpringAssignment2.Entity.User;
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
    public List<Job> findJobsBySkillsId(int[] theIds) {
        return jobRepository.findJobsBySkillsId(theIds);
    }
}

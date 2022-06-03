package com.example.springassignment2.SpringAssignment2.Dao;

import com.example.springassignment2.SpringAssignment2.Entity.Job;
import com.example.springassignment2.SpringAssignment2.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer>
{
    public List<Job> findJobsBySkillsId(int[] theIds);
}

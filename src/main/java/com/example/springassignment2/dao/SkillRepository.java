package com.example.springassignment2.dao;

import com.example.springassignment2.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer>
{
    public List<Skill> findSkillsByJobsId(int theId);
}

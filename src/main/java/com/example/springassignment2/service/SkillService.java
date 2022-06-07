package com.example.springassignment2.service;

import com.example.springassignment2.entity.Skill;

import java.util.List;

public interface SkillService
{
    public List<Skill> findAll();

    public Skill save(Skill theSkill);

    public List<Skill> findSkillsByJobsId(int theId);
}

package com.example.springassignment2.SpringAssignment2.Service;

import com.example.springassignment2.SpringAssignment2.Entity.Skill;

import java.util.List;

public interface SkillService
{
    public List<Skill> findAll();

    public Skill findById(int theId);

    public Skill save(Skill theSkill);

    public void deleteById(int theId);

    public List<Skill> findSkillsByJobsId(int theId);
}

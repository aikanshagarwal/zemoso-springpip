package com.example.springassignment2.service;

import com.example.springassignment2.dao.SkillRepository;
import com.example.springassignment2.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService{

    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository theSkillRepository)
    {
        skillRepository = theSkillRepository;
    }

    @Override
    public List<Skill> findAll()
    {
            return skillRepository.findAll();
    }

    @Override
    public Skill save(Skill theSkill) {
        return skillRepository.save(theSkill);
    }

    @Override
    public List<Skill> findSkillsByJobsId(int theId) {
        return skillRepository.findSkillsByJobsId(theId);
    }
}

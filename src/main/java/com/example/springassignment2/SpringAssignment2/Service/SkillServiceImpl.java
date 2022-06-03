package com.example.springassignment2.SpringAssignment2.Service;

import com.example.springassignment2.SpringAssignment2.Dao.SkillRepository;
import com.example.springassignment2.SpringAssignment2.Entity.Skill;
import com.example.springassignment2.SpringAssignment2.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService{

    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository theSkillRepository)
    {
        skillRepository = theSkillRepository;
    }

    @Override
    public List<Skill> findAll() {
            return skillRepository.findAll();

    }

    @Override
    public Skill findById(int theId) {
        Optional<Skill> savedSkill = skillRepository.findById(theId);
        Skill theSkill = null;

        if (savedSkill.isPresent())
        {
            theSkill = savedSkill.get();
        }
        else
        {
            // we didn't find the user
            throw new RuntimeException("Did not find skill with id - " + theId);
        }

        return theSkill;
    }

    @Override
    public Skill save(Skill theSkill) {
        return skillRepository.save(theSkill);
    }

    @Override
    public void deleteById(int theId) {
        skillRepository.deleteById(theId);
    }

    @Override
    public List<Skill> findSkillsByJobsId(int theId) {
        return skillRepository.findSkillsByJobsId(theId);
    }
}

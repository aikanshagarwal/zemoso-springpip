package com.example.springassignment2.SpringAssignment2.service;

import com.example.springassignment2.SpringAssignment2.dao.CommuteRepository;

import com.example.springassignment2.SpringAssignment2.entity.Commute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommuteServiceImpl implements CommuteService
{

    private CommuteRepository commuteRepository;

    @Autowired
    public CommuteServiceImpl(CommuteRepository theCommuteRepository)
    {
        commuteRepository = theCommuteRepository;
    }

    @Override
    public Commute findById(int theId) {
        Optional<Commute> savedCommute = commuteRepository.findById(theId);
        Commute theCommute = null;

        if (savedCommute.isPresent())
        {
            theCommute = savedCommute.get();
        }
        else
        {
            throw new RuntimeException("Did not find commute with id - " + theId);
        }
        return theCommute;
    }

    @Override
    public Commute save(Commute theCommute) {
        return commuteRepository.save(theCommute);
    }

    @Override
    public void deleteById(int theId) {
        commuteRepository.deleteById(theId);
    }

    @Override
    public List<Commute> findByJobId(int theId) {
        return commuteRepository.findByJobId(theId);
    }
}

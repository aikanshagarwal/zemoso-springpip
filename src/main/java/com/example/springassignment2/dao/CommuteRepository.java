package com.example.springassignment2.dao;


import com.example.springassignment2.entity.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommuteRepository extends JpaRepository<Commute, Integer>
{
    //finding commute options given the job id
    List<Commute> findByJobId(int theId);
}

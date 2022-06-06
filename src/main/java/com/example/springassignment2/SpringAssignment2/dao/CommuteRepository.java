package com.example.springassignment2.SpringAssignment2.dao;


import com.example.springassignment2.SpringAssignment2.entity.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommuteRepository extends JpaRepository<Commute, Integer>
{
    List<Commute> findByJobId(int theId);
}

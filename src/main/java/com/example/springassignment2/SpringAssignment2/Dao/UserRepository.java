package com.example.springassignment2.SpringAssignment2.Dao;

import com.example.springassignment2.SpringAssignment2.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{

}

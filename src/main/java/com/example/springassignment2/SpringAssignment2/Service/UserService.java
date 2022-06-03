package com.example.springassignment2.SpringAssignment2.Service;

import com.example.springassignment2.SpringAssignment2.Entity.User;

import java.util.List;

public interface UserService
{
    public List<User> findAll();

    public User findById(int theId);

    public User save(User theUser);

    public void deleteById(int theId);
}

package com.example.springassignment2.SpringAssignment2.Service;

import com.example.springassignment2.SpringAssignment2.Dao.UserRepository;
import com.example.springassignment2.SpringAssignment2.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository)
    {
        userRepository = theUserRepository;
    }

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId)
    {
        Optional<User> savedUser = userRepository.findById(theId);
        User theUser = null;

        if (savedUser.isPresent())
        {
            theUser = savedUser.get();
        }
        else
        {
            // we didn't find the user
            throw new RuntimeException("Did not find user with id - " + theId);
        }

        return theUser;
    }

    @Override
    public User save(User theUser)
    {
        return userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId)
    {
        userRepository.deleteById(theId);
    }
}

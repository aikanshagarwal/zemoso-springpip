package com.example.springassignment2.exception;

public class JobNotFoundException extends RuntimeException
{
    public JobNotFoundException(String message)
    {
        super(message);
    }

}

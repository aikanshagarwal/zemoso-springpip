package com.example.springassignment2;

import com.example.springassignment2.dao.JobRepository;
import com.example.springassignment2.dao.LocationRepository;
import com.example.springassignment2.entity.Job;
import com.example.springassignment2.entity.Location;
import com.example.springassignment2.service.JobServiceImpl;
import com.example.springassignment2.service.LocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class LocationServiceTests
{
    @Mock
    private LocationRepository locationRepository;

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @InjectMocks
    private LocationServiceImpl locationService;

    private Location location;
    private Job job;

    @BeforeEach
    void setupLocation(){
        location = location.builder()
                .id(1)
                .name("Kolkata")
                .build();
    }

    @BeforeEach
    void setupJob(){
        job = job.builder()
                .id(1)
                .title("Backend Developer")
                .pay(20000)
                .build();
    }

    @DisplayName("JUnit test for saveLocation method")
    @Test
    void givenLocationObject_whenSaveLocation_thenReturnLocationObject(){
        // given - precondition or setup

        given(locationRepository.save(location)).willReturn(location);

        System.out.println(locationRepository);
        System.out.println(locationService);

        // when -  action or the behaviour that we are going test
        Location savedLocation = locationService.save(location);

        System.out.println(savedLocation);
        // then - verify the output
        assertThat(savedLocation).isNotNull();
    }

    @DisplayName("JUnit test for getAllLocations method")
    @Test
    void givenLocationsList_whenGetAllLocations_thenReturnLocationsList(){
        // given - precondition or setup

        Location location1 =  location.builder()
                .id(1)
                .name("Kolkata")
                .build();

        given(locationRepository.findAll()).willReturn(List.of(location,location1));

        // when -  action or the behaviour that we are going test
        List<Location> locationList = locationService.findAll();

        // then - verify the output
        assertThat(locationList).isNotNull().hasSize(2);
    }

    @DisplayName("JUnit test for getLocationsByJobId method")
    @Test
    void givenJobId_whenGetLocationsbyJobId_thenReturnLocationObject(){

        // when
        List<Location> savedLocation = locationService.findLocationsByJobsId(1);

        // then
        assertThat(savedLocation).isNotNull();
    }

}

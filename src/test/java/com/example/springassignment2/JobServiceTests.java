package com.example.springassignment2;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springassignment2.dao.JobRepository;
import com.example.springassignment2.dao.LocationRepository;
import com.example.springassignment2.dao.SkillRepository;
import com.example.springassignment2.entity.Job;
import com.example.springassignment2.entity.Location;
import com.example.springassignment2.entity.Skill;
import com.example.springassignment2.service.JobServiceImpl;
import com.example.springassignment2.service.LocationServiceImpl;
import com.example.springassignment2.service.SkillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class JobServiceTests
{

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    private Job job;
    private Location location;
    private Skill skill;

    @BeforeEach
    void setup(){
        job = job.builder()
                .id(1)
                .title("Backend Developer")
                .pay(20000)
                .build();
    }

    @DisplayName("JUnit test for saveJob method")
    @Test
    void givenJobObject_whenSaveJob_thenReturnJobObject(){

        // given - precondition or setup
        given(jobRepository.save(job)).willReturn(job);
        System.out.println(jobRepository);
        System.out.println(jobService);

        // when -  action or the behaviour that we are going test
        Job savedJob = jobService.save(job);
        System.out.println(savedJob);

        // then - verify the output
        assertThat(savedJob).isNotNull();
    }

    @DisplayName("JUnit test for getAllJobs method")
    @Test
    void givenJobsList_whenGetAllJobs_thenReturnJobsList(){

        // given - precondition or setup
        Job job1 = job.builder()
                .id(1)
                .title("Frontend Developer")
                .pay(20000)
                .build();

        given(jobRepository.findAll()).willReturn(List.of(job,job1));

        // when -  action or the behaviour that we are going test
        List<Job> jobList = jobService.findAll();

        // then - verify the output
        assertThat(jobList).isNotNull().hasSize(2);
    }

    @DisplayName("JUnit test for getJobById method")
    @Test
    void givenJobId_whenGetJobById_thenReturnJobObject(){

        // given
        given(jobRepository.findById(1)).willReturn(Optional.of(job));

        // when
        Job savedJob = jobService.findById(job.getId());

        // then
        assertThat(savedJob).isNotNull();
    }

    @DisplayName("JUnit test for deleteJob method")
    @Test
    void givenJobId_whenDeleteJob_thenNothing(){

        // given - precondition or setup
        int jobId = 1;
        willDoNothing().given(jobRepository).deleteById(jobId);

        // when -  action or the behaviour that we are going test
        jobService.deleteById(jobId);

        // then - verify the output
        verify(jobRepository, times(1)).deleteById(jobId);
    }

    @DisplayName("JUnit test for getJobsByLocation method")
    @Test
    void givenLocation_whenGetJobsByLocation_thenReturnJobsList(){

        // given - precondition or setup
        Location location1 = location.builder()
                .id(1)
                .name("Kolkata")
                .build();

        List<Location> list1 = new ArrayList<>();
        list1.add(location1);

        Job job1 = job.builder()
                .id(1)
                .title("Frontend Developer")
                .pay(20000)
                .locations(list1)
                .build();

        given(jobRepository.findByLocation("Kolkata")).willReturn(List.of(job,job1));

        // when -  action or the behaviour that we are going test
        List<Job> jobList = jobService.findByLocation("Kolkata");

        // then - verify the output
        assertThat(jobList).isNotNull().hasSize(2);
    }

    @DisplayName("JUnit test for getJobBySkill method")
    @Test
    void givenSkill_whenGetJobsBySkill_thenReturnJobsList(){

        // given - precondition or setup
        Skill skill1 = skill.builder()
                .id(1)
                .name("Java")
                .build();

        List<Skill> list1 = new ArrayList<>();
        list1.add(skill1);

        Job job1 = job.builder()
                .id(1)
                .title("Frontend Developer")
                .pay(20000)
                .skills(list1)
                .build();

        given(jobRepository.findBySkill("Java")).willReturn(List.of(job,job1));

        // when -  action or the behaviour that we are going test
        List<Job> jobList = jobService.findBySkill("Java");

        // then - verify the output
        assertThat(jobList).isNotNull().hasSize(2);
    }

    @DisplayName("JUnit test for getJobsBySkillandLocation method")
    @Test
    void givenSkillAndLocation_whenGetJobsBySkillAndLocation_thenReturnJobsList(){

        // given - precondition or setup
        Skill skill1 = skill.builder()
                .id(1)
                .name("Java")
                .build();

        Location location1 = location.builder()
                .id(1)
                .name("Kolkata")
                .build();

        List<Skill> list1 = new ArrayList<>();
        list1.add(skill1);

        List<Location> list2 = new ArrayList<>();
        list2.add(location1);

        Job job1 = job.builder()
                .id(1)
                .title("Frontend Developer")
                .pay(20000)
                .skills(list1)
                .locations(list2)
                .build();

        given(jobRepository.findBySkillAndLocation("Java","Kolkata")).willReturn(List.of(job,job1));

        // when -  action or the behaviour that we are going test
        List<Job> jobList = jobService.findBySkillAndLocation("Java","Kolkata");

        // then - verify the output
        assertThat(jobList).isNotNull().hasSize(2);
    }
}

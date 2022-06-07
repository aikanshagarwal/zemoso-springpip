package com.example.springassignment2;

import com.example.springassignment2.dao.JobRepository;
import com.example.springassignment2.dao.SkillRepository;
import com.example.springassignment2.entity.Job;
import com.example.springassignment2.entity.Skill;
import com.example.springassignment2.service.JobServiceImpl;
import com.example.springassignment2.service.SkillServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SkillServiceTests
{
    @Mock
    private SkillRepository skillRepository;

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @InjectMocks
    private SkillServiceImpl skillService;

    private Skill skill;
    private Job job;

    @BeforeEach
    void setupSkill(){
        skill = skill.builder()
                .id(1)
                .name("Java")
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

    @DisplayName("JUnit test for saveSkill method")
    @Test
    void givenSkillObject_whenSaveSkill_thenReturnSkillObject(){
        // given - precondition or setup

        given(skillRepository.save(skill)).willReturn(skill);

        System.out.println(skillRepository);
        System.out.println(skillService);

        // when -  action or the behaviour that we are going test
        Skill savedSkill = skillService.save(skill);

        System.out.println(savedSkill);
        // then - verify the output
        assertThat(savedSkill).isNotNull();
    }

    @DisplayName("JUnit test for getAllSkills method")
    @Test
    void givenSkillsList_whenGetAllSkills_thenReturnSkillsList(){
        // given - precondition or setup

        Skill skill1 =  skill.builder()
                .id(1)
                .name("Java")
                .build();

        given(skillRepository.findAll()).willReturn(List.of(skill,skill1));

        // when -  action or the behaviour that we are going test
        List<Skill> skillList = skillService.findAll();

        // then - verify the output
        assertThat(skillList).isNotNull().hasSize(2);
    }

    @DisplayName("JUnit test for getSkillsByJobId method")
    @Test
    void givenJobId_whenGetSkillsbyJobId_thenReturnSkillObject(){

        // when
        List<Skill> savedSkill = skillService.findSkillsByJobsId(1);

        // then
        assertThat(savedSkill).isNotNull();
    }

}


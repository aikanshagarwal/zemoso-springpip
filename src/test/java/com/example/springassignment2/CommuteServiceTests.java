package com.example.springassignment2;

import com.example.springassignment2.dao.CommuteRepository;
import com.example.springassignment2.dao.JobRepository;
import com.example.springassignment2.entity.Commute;
import com.example.springassignment2.entity.Job;
import com.example.springassignment2.service.CommuteServiceImpl;
import com.example.springassignment2.service.JobServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommuteServiceTests
{
    @Mock
    private CommuteRepository commuteRepository;

    @InjectMocks
    private CommuteServiceImpl commuteService;

    private Commute commute;
    private Job job;

    @BeforeEach
    void setupCommute(){
        commute = commute.builder()
                .id(1)
                .option("Take a bus from Central Bus Stand")
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

    @DisplayName("JUnit test for saveCommute method")
    @Test
    void givenCommuteObject_whenSaveCommute_thenReturnCommuteObject(){

        // given - precondition or setup
        given(commuteRepository.save(commute)).willReturn(commute);

        System.out.println(commuteRepository);
        System.out.println(commuteService);

        // when -  action or the behaviour that we are going test
        Commute savedCommute = commuteService.save(commute);
        System.out.println(savedCommute);

        // then - verify the output
        assertThat(savedCommute).isNotNull();
    }


    @DisplayName("JUnit test for getCommuteById method")
    @Test
    void givenCommuteId_whenGetCommuteById_thenReturnCommuteObject(){

        // given
        given(commuteRepository.findById(1)).willReturn(Optional.of(commute));

        // when
        Commute savedCommute = commuteService.findById(commute.getId());

        // then
        assertThat(savedCommute).isNotNull();
    }

    @DisplayName("JUnit test for deleteCommute method")
    @Test
    void givenCommuteId_whenDeleteCommute_thenNothing(){

        // given - precondition or setup
        int commuteId = 1;
        willDoNothing().given(commuteRepository).deleteById(commuteId);

        // when -  action or the behaviour that we are going test
        commuteService.deleteById(commuteId);

        // then - verify the output
        verify(commuteRepository, times(1)).deleteById(commuteId);
    }

    @DisplayName("JUnit test for getCommuteByJobId method")
    @Test
    void whenGetCommutesByJobId_thenReturnCommuteObject(){

        // when
        List<Commute> savedCommute = commuteService.findByJobId(1);

        // then
        assertThat(savedCommute).isNotNull();
    }

}

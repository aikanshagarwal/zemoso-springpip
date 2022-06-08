package com.example.springassignment2.dao;

import com.example.springassignment2.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    @Query(value = "SELECT * FROM job j INNER JOIN job_location jl ON (j.id = jl.job_id) INNER JOIN location l on (jl.location_id = l.id) where l.name = :location ", nativeQuery = true)
    List<Job> findByLocation(@Param("location") String location);

    @Query(value = "SELECT * FROM job j INNER JOIN job_skill js ON (j.id = js.job_id) INNER JOIN skill s on (js.skill_id = s.id) where s.name = :skill ", nativeQuery = true)
    List<Job> findBySkill(@Param("skill") String skill);

    @Query(value = "SELECT * FROM job j INNER JOIN job_skill js ON (j.id = js.job_id) INNER JOIN skill s on (js.skill_id = s.id) INNER JOIN job_location jl ON (j.id = jl.job_id) INNER JOIN location l on (jl.location_id = l.id) where s.name = :skill2 and l.name = :location2 ", nativeQuery = true)
    List<Job> findBySkillAndLocation(@Param("skill2") String skill2,@Param("location2") String location2);

    @Modifying
    @Query(value = "SELECT * FROM job j INNER JOIN job_user ju ON (j.id = ju.job_id) INNER JOIN user u on (ju.username = u.username) WHERE u.username = :userName", nativeQuery = true)
    @Transactional
    List<Job> showSavedJobs(@Param("userName") String userName);

    @Modifying
    @Query(value = "INSERT IGNORE into job_user (job_id,username) VALUES (:jobId,:userName)", nativeQuery = true)
    @Transactional
    void saveAJob(@Param("jobId") int jobId, @Param("userName") String userName);

    @Modifying
    @Query(value = "DELETE from job_user WHERE job_id = :jobId AND username = :userName", nativeQuery = true)
    @Transactional
    void unsaveAJob(@Param("jobId") int jobId, @Param("userName") String userName);



}
package com.example.springassignment2.SpringAssignment2.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="jobs")
public class Job
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotEmpty(message="must not be empty")
    @Column(name="title")
    private String title;

    @NotEmpty(message="must not be empty")
    @Column(name="location")
    private String location;

    @Min(value=1, message="must be equal to or greater than 1")
    @Column(name="pay")
    private long pay;

    @ManyToMany
    @JoinTable(name = "job_user",
            joinColumns = @JoinColumn(name="job_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "job_skill",
            joinColumns = @JoinColumn(name="job_id"),
            inverseJoinColumns = @JoinColumn(name="skill_id"))
    private List<Skill> skills;

}

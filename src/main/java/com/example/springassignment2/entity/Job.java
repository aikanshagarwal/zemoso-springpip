package com.example.springassignment2.entity;

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
@Table(name="job")
public class Job
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotEmpty(message="must not be empty")
    @Column(name="title")
    private String title;

    @Min(value=1, message="must be equal to or greater than 1")
    @Column(name="pay")
    private long pay;

    @ManyToMany
    @JoinTable(name = "job_skill",
            joinColumns = @JoinColumn(name="job_id"),
            inverseJoinColumns = @JoinColumn(name="skill_id"))
    private List<Skill> skills;

    @ManyToMany
    @JoinTable(name = "job_location",
            joinColumns = @JoinColumn(name="job_id"),
            inverseJoinColumns = @JoinColumn(name="location_id"))
    private List<Location> locations;

    @OneToMany(mappedBy = "job",cascade = CascadeType.ALL)
    private List<Commute> commutes;

}

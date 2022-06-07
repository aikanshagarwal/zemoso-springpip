package com.example.springassignment2.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="location")
public class Location
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "locations")
    private List<Job> jobs;
}


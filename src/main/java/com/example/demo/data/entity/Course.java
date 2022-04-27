package com.example.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Table(name = "courses")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String topic;
    @Column
    private Boolean isActive;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private User teacher;
    @ManyToMany
    @JoinColumn(name = "student_id", nullable = false)
    private Set<User> users;



}

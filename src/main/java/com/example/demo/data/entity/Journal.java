package com.example.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "journal")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer mark;
    @Column
    private Date createdDate;
    @Column
    private Integer semester;
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name="student_id")
    private User student;


}

package com.dailycodebuffer.spring.data.jpa.tutorial.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    // course material will have an extra column with course id
    // which column will the primary key apply

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;

    // cascading means to pass the properties or permission to your child element
    // cascade: whatever the operations are there in the testing method, everything will happen in the database

    // fetch type means how to want to fetch the data
    // eager fetching - fetch course data when you fetch course material
    // lazy fetching  - it will not fetch course data until you specifically ask to fetch the course data
}

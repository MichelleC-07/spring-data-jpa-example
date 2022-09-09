package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest  {
    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * result:
     * insert into teacher
     * insert into course
     * insert into course
     * update course set teacher_id=? where course_id=?
     * update course set teacher_id=? where course_id=?
     * note: (optional relationship) no course material is created when a list of course is created for teacher record
     * to make mandatory relationship where creating course must also create course material (so data is consistent),
     * set optional = false in @OneToOne() in CourseMaterial.java, by default is true for optional
     */
    @Test
    public void saveTeacher() {

        /*
         * ideally you have to create method that create course
         * and add the course into the list of course for a particular teacher
         */
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();

        Course courseJava = Course.builder()
                .title("Java")
                .credit(6)
                .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Qutub")
                        .lastName("Khan")
//                        .courses(List.of(courseDBA,courseJava))
                        .build();

        teacherRepository.save(teacher);
    }

}
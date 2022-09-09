package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void SaveCourseMaterial() {

        /*
        * error: object references an unsaved transient
        * reason: trying to save coursematerial record without course record exist
        * solution: cascade.persistence will persistence the data of course; but we use cascade.all
        * result: first; insert into course second; insert into course_material
         */
        Course course =
                Course.builder()
                        .title("DSA")
                        .credit(6)
                        .build();

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("www.google.com")
                        .course(course)
                        .build();

        repository.save(courseMaterial);
    }

    /**
     * error: org.hibernate.LazyInitializationException: could not initialize proxy
     * reason: used fetchtype.lazy and toString method is trying to call course data
     * solution: either remove the course data from toString or change to fetchtype.eager in entity class
     * result: courseMaterials = [CourseMaterial(courseMaterialId=2, url=www.dailycodebuffer.com)]
     *
     * scenario with fetch eager output: [CourseMaterial(courseMaterialId=2, url=www.dailycodebuffer.com, course=Course(courseId=1, title=.net, credit=6))]
     */
    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials =
                repository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);
    }

}

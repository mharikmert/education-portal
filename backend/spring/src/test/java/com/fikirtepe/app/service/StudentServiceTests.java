package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Role;
import com.fikirtepe.app.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class StudentServiceTests {

    @Autowired
    private StudentService studentService;

    @Test @Rollback
    public void testCreateStudent(){
        Student student = new Student();
        student.setPassword("123");
        studentService.createStudent(student);
        Student student1 = studentService.getStudentById(student.getId());
        Assertions.assertEquals(student.getPassword(),student1.getPassword());
        Assertions.assertEquals(student.getId(),student1.getId());
        Assertions.assertTrue(student.getRoles().contains(Role.ROLE_STUDENT));
        Assertions.assertEquals("Öğrenci", student.getType());
    }

    @Test
    public void testSaveStudent(){
        Student student = new Student();
        studentService.save(student);
        Assertions.assertNotNull(studentService.getStudentById(student.getId()));
    }

    @Test
    public void testGetStudents(){
        Student student = new Student();
        studentService.save(student);

        List<Student> students = studentService.getStudents();
        Assertions.assertNotNull(students);
        Assertions.assertTrue(students.size()>0);
    }
    @Test
    public void testGetStudentById(){
        Student student = new Student();
        studentService.save(student);
        Assertions.assertNotNull(studentService.getStudentById(student.getId()));

    }

    @Test
    public void testDeleteStudent(){
        Student student = new Student();
        student.setId(1L);
        studentService.save(student);
        studentService.deleteStudentById(student.getId());
        Student student1 = studentService.getStudentById(student.getId());
        Assertions.assertNull(student1);
    }



}


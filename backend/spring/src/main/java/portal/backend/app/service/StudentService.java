package portal.backend.app.service;

import portal.backend.app.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student save(Student student);

    void deleteStudentById(Long id);

    Student getStudentById(Long id);

    List<Student> getStudents();
}

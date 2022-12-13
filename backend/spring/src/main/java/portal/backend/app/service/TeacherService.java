package portal.backend.app.service;

import portal.backend.app.model.Classroom;
import portal.backend.app.model.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);

    Teacher save(Teacher teacher);

    Teacher getTeacherById(Long id);

    List<Teacher> getTeachers();
}

package portal.backend.app.service.teacherServiceImplementation;

import portal.backend.app.model.Role;
import portal.backend.app.model.Teacher;
import portal.backend.app.repository.TeacherRepository;
import portal.backend.app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImplementation(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacher.setType("Öğretmen");
        teacher.setRoles(Collections.singletonList(Role.ROLE_TEACHER));
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findTeacherById(id);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }
}

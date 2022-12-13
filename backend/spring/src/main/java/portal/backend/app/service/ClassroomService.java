package portal.backend.app.service;

import portal.backend.app.model.Classroom;
import portal.backend.app.model.Student;
import portal.backend.app.model.Teacher;

import java.util.List;
import java.util.Set;

public interface ClassroomService {
    List<Classroom> getClassrooms();

    Classroom createClassroom(Classroom classroom);

    Classroom updateClassroom(Classroom classroom);

    Classroom getClassroomByName(String classroomName);

    Classroom getClassroomById(Long id);

    void deleteClassroom(Classroom classRef);

    void deleteClassroomByName(String name);
}

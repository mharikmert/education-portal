package portal.backend.app.service;

import portal.backend.app.model.Parent;
import portal.backend.app.model.Student;

import java.util.List;
import java.util.Set;

public interface ParentService {
    Parent createParent(Parent parent);

    List<Parent> getParents();

    Parent getParentById(Long id);

    void deleteParent(Long id);
}

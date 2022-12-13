package portal.backend.app.service.parentServiceImplementation;

import portal.backend.app.model.Parent;
import portal.backend.app.model.Role;
import portal.backend.app.model.Student;
import portal.backend.app.repository.ParentRepository;
import portal.backend.app.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ParentServiceImplementation implements ParentService {
    private final ParentRepository parentRepository;

    @Autowired
    public ParentServiceImplementation(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent createParent(Parent parent) {
        parent.setRoles(Collections.singletonList(Role.ROLE_PARENT));
        parent.setType("Veli");
        return parentRepository.save(parent);
    }

    @Override
    public List<Parent> getParents() {
        return parentRepository.findAll();
    }

    @Override
    public Parent getParentById(Long id) {
        return parentRepository.findParentById(id);
    }

    @Override
    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }
}

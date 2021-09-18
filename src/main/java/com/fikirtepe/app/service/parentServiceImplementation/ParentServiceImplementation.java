package com.fikirtepe.app.service.parentServiceImplementation;

import com.fikirtepe.app.model.Parent;
import com.fikirtepe.app.model.Role;
import com.fikirtepe.app.model.Student;
import com.fikirtepe.app.repository.ParentRepository;
import com.fikirtepe.app.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ParentServiceImplementation implements ParentService {
    private final ParentRepository parentRepository;
    @Autowired
    public ParentServiceImplementation(ParentRepository parentRepository){
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent createParent(Parent parent) {
        parent.setRoles(Collections.singletonList(Role.ROLE_PARENT));
        parent.setType("Veli");
        return parentRepository.save(parent);
    }

    @Override
    public List<Parent> findAllParents() {
       return parentRepository.findAll();
    }

    @Override
    public Parent findParentById(Long id) {
        return parentRepository.findParentById(id);
    }

    @Override
    public Set<Student> findStudentsByParentId(Long id) {
        return parentRepository.findParentById(id).getStudents();
    }

    @Override
    public void deleteParent(Parent parent) {
        parentRepository.delete(parent);
    }
}

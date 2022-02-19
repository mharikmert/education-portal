package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Parent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ParentServiceTests {

    @Autowired
    private ParentService parentService;

    @Test @Rollback
    public void testCreateParent() {
        Parent parent = new Parent();
        parent.setFirstName("test");
        parent.setLastName("test");
        parentService.createParent(parent);
        Parent parent2 = parentService.getParentById(parent.getId());
        Assertions.assertEquals(parent.getFirstName(), parent2.getFirstName());
    }

    @Test @Rollback
    public void testGetParents(){
        Parent parent = new Parent();
        parent.setFirstName("test");
        parent.setLastName("test");
        parentService.createParent(parent);
        Assertions.assertNotNull(parentService.getParents());
        Assertions.assertEquals(1, parentService.getParents().size());
    }

    @Test @Rollback
    public void testGetParentById(){
        Parent parent = new Parent();
        parent.setFirstName("test");
        parent.setLastName("test");
        parentService.createParent(parent);
        Assertions.assertNotNull(parentService.getParentById(parent.getId()));
        Assertions.assertEquals(parent.getFirstName(), parentService.getParentById(parent.getId()).getFirstName());
    }

    @Test @Rollback
    public void testDeleteParent(){
        Parent parent = new Parent();
        parent.setId(1L);
        parent.setFirstName("test");
        parent.setLastName("test");
        parentService.createParent(parent);
        parentService.deleteParent(parent.getId());
        Assertions.assertNull(parentService.getParentById(parent.getId()));

    }


}

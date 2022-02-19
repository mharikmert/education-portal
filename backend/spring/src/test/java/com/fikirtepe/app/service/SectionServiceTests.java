package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Section;
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
public class SectionServiceTests {
    @Autowired
    private SectionService sectionService;

    @Test @Rollback
    public void testCreateSection(){
        Section section = new Section();
        section.setDay("Wednesday");
        section.setStartingTime("10:00");
        sectionService.createSection(section);
        Assertions.assertNotNull(sectionService.createSection(section));
        Assertions.assertNotNull(sectionService.getSectionById(section.getId()));
    }

    @Test @Rollback
    public void testUpdateSection(){
        Section section = new Section();
        section.setDay("Wednesday");
        section.setStartingTime("10:00");
        sectionService.createSection(section);
        Assertions.assertNotNull(sectionService.getSectionById(section.getId()));
        section.setDay("Thursday");
        sectionService.updateSection(section);
        Assertions.assertNotNull(sectionService.updateSection(section));
        Assertions.assertEquals(section.getDay(), sectionService.getSectionById(section.getId()).getDay());
    }

    @Test @Rollback
    public void testDeleteSection(){
        Section section = new Section();
        section.setDay("Wednesday");
        section.setNumberOfHours(2);
        section.setStartingTime("10:00");
        sectionService.createSection(section);
        Assertions.assertNotNull(sectionService.getSectionById(section.getId()));
        sectionService.deleteSection(section);
        Section section1 = sectionService.getSectionById(section.getId());
        Assertions.assertNull(section1);
    }
}

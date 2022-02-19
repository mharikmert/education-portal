package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    Section createSection(Section section);
    Section getSectionById(Long id);
    List<Section> getSectionsByClassroomId(long id);
    Section getSectionByClassroomIdAndDayAndStartingTime(long id, String day, String startingTime);
    Section updateSection(Section section);
    void deleteSection(Section section);
}

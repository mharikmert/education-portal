package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Section;

import java.util.List;

public interface SectionService {
    Section createSection(Section section);
    List<Section> findSectionsByClassroomId(long id);
    Section findSectionByClassroomIdAndDayAndStartingTime(long id, String day, String startingTime);
    void deleteSection(Section section);
}

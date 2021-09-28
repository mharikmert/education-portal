package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Section;

import java.util.List;

public interface SectionService {
    Section createSection(Section section);
    List<Section> findSectionsByClassroomId(long id);
    void deleteSection(Section section);
}

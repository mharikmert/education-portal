package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Section;

public interface SectionService {
    Section createSection(Section section);
    void deleteSection(Section section);
}

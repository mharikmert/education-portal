package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Term;

public interface TermService {
    Term findTermByName(String name);
    Term createTerm(Term term);
    void deleteTerm(Term term);
}

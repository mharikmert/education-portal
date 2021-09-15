package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Term;

import java.util.List;
import java.util.Set;

public interface TermService {
    List<Term> findAllTerms();
    Term findTermByName(String name);
    Term createTerm(Term term);
    void deleteTerm(Term term);
    List<Term> updateTerms(List<Term> terms);
}

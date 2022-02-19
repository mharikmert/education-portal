package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Term;

import java.util.List;

public interface TermService {
    List<Term> getTerms();
    Term getTermByName(String name);
    Term createTerm(Term term);
    void deleteTerm(Term term);
    Term updateTerm(Term term);
    List<Term> updateTerms(List<Term> terms);
}

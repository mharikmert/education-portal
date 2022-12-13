package portal.backend.app.service;

import portal.backend.app.model.Term;

import java.util.List;

public interface TermService {
    List<Term> getTerms();

    Term getCurrentTerm();

    void activateTerm(int termId);

    Term getTermByName(String name);

    Term createTerm(Term term);

    void deleteTerm(Term term);

    Term updateTerm(Term term);

    List<Term> updateTerms(List<Term> terms);
}

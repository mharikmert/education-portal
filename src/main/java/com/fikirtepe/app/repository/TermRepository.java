package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}

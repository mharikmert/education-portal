package portal.backend.app.service;

import portal.backend.app.model.Term;
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
public class TermServiceTests {

    @Autowired
    private TermService termService;

    @Test
    @Rollback
    public void testCreateTerm() {
        Term term = new Term();
        term.setName("2019-2020");
        term.setStartDate("2019-01-01");
        term.setEndDate("2020-01-01");
        termService.createTerm(term);
        Term term2 = termService.getTermByName(term.getName());
        Assertions.assertNotNull(term2);
        Assertions.assertEquals(term.getName(), term2.getName());
    }

    @Test
    @Rollback
    public void testGetTerms() {
        Term term = new Term();
        term.setName("2019-2020");
        term.setStartDate("2019-01-01");
        term.setEndDate("2020-01-01");
        termService.createTerm(term);
        Assertions.assertNotNull(termService.getTerms());
        Assertions.assertFalse(termService.getTerms().isEmpty());
    }

    @Test
    @Rollback
    public void testGetTermByName() {
        Term term = new Term();
        term.setName("2019-2020");
        term.setStartDate("2019-01-01");
        term.setEndDate("2020-01-01");
        termService.createTerm(term);
        Assertions.assertNotNull(termService.getTermByName(term.getName()));
    }

    @Test
    @Rollback
    public void testUpdateTerm() {
        Term term = new Term();
        term.setName("2019-2020");
        term.setStartDate("2019-01-01");
        term.setEndDate("2020-01-01");
        termService.createTerm(term);
        Term term2 = termService.getTermByName(term.getName());
        term2.setName("2019-2021");
        termService.updateTerm(term2);
        Assertions.assertEquals(term2.getName(), termService.getTermByName(term2.getName()).getName());
    }

    @Test
    @Rollback
    public void testDeleteTerm() {
        Term term = new Term();
        term.setName("2019-2020");
        term.setStartDate("2019-01-01");
        term.setEndDate("2020-01-01");
        termService.createTerm(term);
        Term term2 = termService.getTermByName(term.getName());
        termService.deleteTerm(term2);
        Assertions.assertNull(termService.getTermByName(term.getName()));
    }
}

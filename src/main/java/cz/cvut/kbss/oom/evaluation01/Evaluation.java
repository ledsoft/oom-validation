package cz.cvut.kbss.oom.evaluation01;

import cz.cvut.kbss.jopa.exceptions.CardinalityConstraintViolatedException;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.oom.evaluation01.generated.Vocabulary;
import cz.cvut.kbss.oom.evaluation01.manual.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Evaluation {

    private static final Logger LOG = LoggerFactory.getLogger(Evaluation.class);

    private static final String validFile = "dl-ontology" + File.separator + "evaluation-01-sample01-valid.ttl";
    private static final String validOntologyIri = "http://onto.fel.cvut.cz/ontologies/2020/ml-dissertation/evaluation-01-sample01-valid";
    private static final String invalidFile = "dl-ontology" + File.separator + "evaluation-01-sample02-invalid.ttl";
    private static final String invalidOntologyIri = "http://onto.fel.cvut.cz/ontologies/2020/ml-dissertation/evaluation-01-sample02-invalid";

    private static final String QUERY = "SELECT ?r WHERE {?r a ?report .} ORDER BY ?r";

    public static void main(String[] args) {
        final Evaluation eval = new Evaluation();
        eval.executeValid();
        eval.executeInvalid();
    }

    private void executeValid() {
        LOG.info("Testing a valid ontology.");
        final PersistenceFactory factory = new PersistenceFactory(validFile, validOntologyIri);
        try {
            final EntityManager emManual = factory.createEntityManager();
            final List<Report> manualReports = emManual.createNativeQuery(QUERY, Report.class).setParameter("report",
                    URI.create(Vocabulary.s_c_Report)).getResultList();
            final EntityManager emGenerated = factory.createEntityManager();
            final List<cz.cvut.kbss.oom.evaluation01.generated.model.Report> generatedReports = emGenerated
                    .createNativeQuery(QUERY,
                            cz.cvut.kbss.oom.evaluation01.generated.model.Report.class).setParameter("report",
                            URI.create(Vocabulary.s_c_Report)).getResultList();
            compareManualAndGenerated(manualReports, generatedReports);
            LOG.info("Valid ontology successfully tested.");
        } finally {
            factory.close();
        }
    }

    private void compareManualAndGenerated(List<Report> manual,
                                           List<cz.cvut.kbss.oom.evaluation01.generated.model.Report> generated) {
        assertEquals(manual.size(), generated.size());
        for (int i = 0; i < manual.size(); i++) {
            final Report manualReport = manual.get(i);
            final cz.cvut.kbss.oom.evaluation01.generated.model.Report generatedReport = generated.get(i);
            assertEquals(manualReport.getId(), generatedReport.getId());
            LOG.debug("Comparing manual and generated Java instances of report <{}>.", manualReport.getId());
            assertEquals(manualReport.getAuthor().getId(), generatedReport.getAuthor().getId());
            if (manualReport.getLastEditor() != null) {
                assertEquals(manualReport.getLastEditor().getId(), generatedReport.getLastEditor().getId());
            }
            assertEquals(manualReport.getDocuments().getId(), generatedReport.getDocuments().getId());
            assertEquals(manualReport.getDocuments().getHasSeverity().getId(),
                    generatedReport.getDocuments().getHasSeverity().getId());
        }
    }

    private void executeInvalid() {
        LOG.info("Testing an invalid ontology.");
        final PersistenceFactory factory = new PersistenceFactory(invalidFile, invalidOntologyIri);
        try {
            final EntityManager emManual = factory.createEntityManager();
            final List<Report> manualReports = emManual.createNativeQuery(QUERY, Report.class).setParameter("report",
                    URI.create(Vocabulary.s_c_Report)).getResultList();
            assertThrows(CardinalityConstraintViolatedException.class, () -> manualReports.forEach(r -> {
                // Trigger lazy load
                r.getAuthor();
                r.getDocuments();
            }));
            final EntityManager emGenerated = factory.createEntityManager();
            final List<cz.cvut.kbss.oom.evaluation01.generated.model.Report> generatedReports = emGenerated
                    .createNativeQuery(QUERY,
                            cz.cvut.kbss.oom.evaluation01.generated.model.Report.class).setParameter("report",
                            URI.create(Vocabulary.s_c_Report)).getResultList();
            assertThrows(CardinalityConstraintViolatedException.class, () -> generatedReports.forEach(r -> {
                // Trigger lazy load
                r.getAuthor();
                r.getDocuments();
            }));
            LOG.info("Invalid ontology successfully tested.");
        } finally {
            factory.close();
        }
    }
}

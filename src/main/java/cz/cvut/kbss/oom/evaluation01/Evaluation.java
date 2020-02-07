package cz.cvut.kbss.oom.evaluation01;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.oom.evaluation01.generated.Vocabulary;
import cz.cvut.kbss.oom.evaluation01.manual.model.Report;

import java.io.File;
import java.net.URI;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Evaluation {

    private static final String validFile = "dl-ontology" + File.separator + "evaluation-01-sample01-valid.ttl";
    private static final String validOntologyIri = "http://onto.fel.cvut.cz/ontologies/2020/ml-dissertation/evaluation-01-sample01-valid";
    private static final String invalidFile = "dl-ontology" + File.separator + "evaluation-01-sample02-invalid.ttl";
    private static final String invalidOntologyIri = "http://onto.fel.cvut.cz/ontologies/2020/ml-dissertation/evaluation-01-sample02-invalid";

    public static void main(String[] args) {
        final Evaluation eval = new Evaluation();
        eval.executeValid();
    }

    private void executeValid() {
        final PersistenceFactory factory = new PersistenceFactory(validFile, validOntologyIri);
        try {
            final EntityManager em = factory.createEntityManager();
            final List<Report> manualReports = em.createNativeQuery("SELECT ?r WHERE {?r a ?report .}", Report.class).setParameter("report",
                    URI.create(Vocabulary.s_c_Report)).getResultList();
            final List<cz.cvut.kbss.oom.evaluation01.generated.model.Report> generatedReports = em.createNativeQuery("SELECT ?r WHERE {?r a ?report .}",
                    cz.cvut.kbss.oom.evaluation01.generated.model.Report.class).setParameter("report",
                    URI.create(Vocabulary.s_c_Report)).getResultList();
            assertEquals(manualReports.size(), generatedReports.size());
            manualReports.sort(Comparator.comparing(Report::getId));
            generatedReports.sort(Comparator.comparing(cz.cvut.kbss.oom.evaluation01.generated.model.Report::getId));
        } finally {
            factory.close();
        }
    }
}

package cz.cvut.kbss.oom.evaluation01.manual.model;

import cz.cvut.kbss.jopa.model.annotations.*;

import java.io.Serializable;
import java.net.URI;

@Namespace(prefix = "ev", namespace = "http://onto.fel.cvut.cz/ontologies/2020/ml-dissertation/evaluation-01/")
@OWLClass(iri = "ev:Occurrence")
public class Occurrence implements Serializable {

    @Id
    private URI id;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:hasSeverity")
    private Severity hasSeverity;

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public Severity getHasSeverity() {
        return hasSeverity;
    }

    public void setHasSeverity(Severity hasSeverity) {
        this.hasSeverity = hasSeverity;
    }
}

package cz.cvut.kbss.oom.evaluation01.manual.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;

import java.io.Serializable;

@OWLClass(iri = "ev:Occurrence")
public class Occurrence implements Serializable {

    @Id
    private String id;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:hasSeverity")
    private Severity hasSeverity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Severity getHasSeverity() {
        return hasSeverity;
    }

    public void setHasSeverity(Severity hasSeverity) {
        this.hasSeverity = hasSeverity;
    }
}

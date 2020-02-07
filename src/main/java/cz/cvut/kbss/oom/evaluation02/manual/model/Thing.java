package cz.cvut.kbss.oom.evaluation02.manual.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.vocabulary.OWL;

import java.net.URI;

@OWLClass(iri = OWL.THING)
public class Thing {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

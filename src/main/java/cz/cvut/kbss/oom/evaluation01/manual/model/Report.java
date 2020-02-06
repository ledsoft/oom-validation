package cz.cvut.kbss.oom.evaluation01.manual.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;

import java.io.Serializable;
import java.net.URI;

@OWLClass(iri = "ev:Report")
public class Report implements Serializable {

    @Id
    private URI id;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:documents")
    private Occurrence documents;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:author")
    private User author;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:lastEditor")
    private User lastEditor;

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public Occurrence getDocuments() {
        return documents;
    }

    public void setDocuments(Occurrence documents) {
        this.documents = documents;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(User lastEditor) {
        this.lastEditor = lastEditor;
    }
}

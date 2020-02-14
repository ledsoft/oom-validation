package cz.cvut.kbss.oom.evaluation01.manual.model;

import cz.cvut.kbss.jopa.model.annotations.*;

import java.io.Serializable;

@OWLClass(iri = "ev:Report")
public class Report implements Serializable {

    @Id
    private String id;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:documents")
    private Occurrence documents;

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:author")
    private User author;

    @OWLObjectProperty(iri = "ev:lastEditor")
    private User lastEditor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

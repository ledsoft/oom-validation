package cz.cvut.kbss.oom.evaluation01.manual.model;

import cz.cvut.kbss.jopa.model.annotations.*;

import java.io.Serializable;
import java.net.URI;

@Namespace(prefix = "ev", namespace = "http://onto.fel.cvut.cz/ontologies/2020/ml-dissertation/evaluation-01/")
@OWLClass(iri = "ev:User")
public class User implements Serializable {

    @Id
    private URI id;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = "ev:firstName")
    private String firstName;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = "ev:lastName")
    private String lastName;

    @ParticipationConstraints(nonEmpty = true)
    @OWLDataProperty(iri = "ev:username")
    private String username;

    public URI getId() {
        return id;
    }

    public void setId(URI id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package cz.cvut.kbss.oom.evaluation02.manual.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;

import java.util.Set;

@OWLClass(iri = "ev:BombThreat")
public interface BombThreat extends SecurityEvent, UnlawfulAction {

    @ParticipationConstraints(nonEmpty = true)
    @OWLObjectProperty(iri = "ev:against")
    Set<Thing> getTargets();

    void setTargets(Set<Thing> targets);
}

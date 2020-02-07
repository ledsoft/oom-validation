package cz.cvut.kbss.oom.evaluation02.manual.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.cvut.kbss.jopa.model.annotations.ParticipationConstraints;

import java.util.Set;

@OWLClass(iri = "ev:UnlawfulAction")
public interface UnlawfulAction {

//    @ParticipationConstraints(nonEmpty = true)
//    @OWLObjectProperty(iri = "ev:hasPunishment")
    Set<Punishment> getPunishments();

    void setPunishments(Set<Punishment> punishments);
}

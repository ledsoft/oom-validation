package cz.cvut.kbss.oom.evaluation02.manual.model;

import cz.cvut.kbss.jopa.model.annotations.Id;

import java.util.Set;

public class BombThreatImpl implements BombThreat {

    @Id
    private String id;

    private Set<Punishment> punishments;

    private Set<Thing> targets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Set<Punishment> getPunishments() {
        return punishments;
    }

    @Override
    public void setPunishments(Set<Punishment> punishments) {
        this.punishments = punishments;
    }

    @Override
    public Set<Thing> getTargets() {
        return targets;
    }

    @Override
    public void setTargets(Set<Thing> targets) {
        this.targets = targets;
    }
}

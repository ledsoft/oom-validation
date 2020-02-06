package cz.cvut.kbss.oom.evaluation02.manual.model;

import java.util.Set;

public class BombThreatImpl implements BombThreat {

    private Set<Punishment> punishments;

    private Set<Thing> targets;

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

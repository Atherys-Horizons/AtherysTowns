package com.atherys.towns.model;

import com.atherys.core.db.SpongeIdentifiable;
import com.atherys.towns.api.permission.ContextHolder;
import com.atherys.towns.api.permission.Contextual;
import com.atherys.towns.model.permission.NationPermissionContext;
import com.atherys.towns.persistence.converter.TextConverter;
import org.hibernate.annotations.GenericGenerator;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity
public class Nation implements SpongeIdentifiable, ContextHolder<Nation, Nation, NationPermissionContext>, Contextual {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID uuid;

    @Convert(converter = TextConverter.class)
    private Text name;

    @Convert(converter = TextConverter.class)
    private Text description;

    @OneToMany(mappedBy = "nation")
    private Set<Town> towns;

    private Resident leader;

    private Town capital;

    private NationPermissionContext context;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "nation_allies",
            joinColumns = @JoinColumn(name = "nation_id"),
            inverseJoinColumns = @JoinColumn(name = "ally_nation_id")
    )
    private Set<Nation> allies;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "nation_allies",
            joinColumns = @JoinColumn(name = "nation_id"),
            inverseJoinColumns = @JoinColumn(name = "enemy_nation_id")
    )
    private Set<Nation> enemies;

    public Nation() {
    }

    public Nation(UUID uuid) {
        this.uuid = uuid;
    }

    @Nonnull
    @Override
    public UUID getId() {
        return uuid;
    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public Text getDescription() {
        return description;
    }

    public void setDescription(Text description) {
        this.description = description;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    public Resident getLeader() {
        return leader;
    }

    public void setLeader(Resident leader) {
        this.leader = leader;
    }

    public Town getCapital() {
        return capital;
    }

    public void setCapital(Town capital) {
        this.capital = capital;
    }

    @Override
    public NationPermissionContext getContext() {
        return context;
    }

    @Override
    public Nation getParent() {
        return this;
    }

    public Set<Nation> getAllies() {
        return allies;
    }

    public void setAllies(Set<Nation> allies) {
        this.allies = allies;
    }

    public Set<Nation> getEnemies() {
        return enemies;
    }

    public void setEnemies(Set<Nation> enemies) {
        this.enemies = enemies;
    }
}

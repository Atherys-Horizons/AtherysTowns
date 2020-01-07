package com.atherys.towns.entity;

import com.atherys.core.db.SpongeIdentifiable;
import com.atherys.towns.api.permission.Actor;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Resident implements SpongeIdentifiable, Actor<UUID> {

    @Id
    private UUID id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "town_id")
    private Town town;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "resident_friends",
            joinColumns = @JoinColumn(name = "resident_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<Resident> friends = new HashSet<>();

    private Set<TownRole> townRoles;

    private Set<NationRole> nationRoles;

    private LocalDateTime registeredOn;

    private LocalDateTime lastLogin;

    private LocalDateTime lastTownSpawn;

    @Transient
    private int warmupSecondsLeft;

    @Version
    private int version;

    @Nonnull
    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Resident> getFriends() {
        return friends;
    }

    public void setFriends(Set<Resident> friends) {
        this.friends = friends;
    }

    public Set<TownRole> getTownRoles() {
        return townRoles;
    }

    public void setTownRoles(Set<TownRole> townRoles) {
        this.townRoles = townRoles;
    }

    public Set<NationRole> getNationRoles() {
        return nationRoles;
    }

    public void setNationRoles(Set<NationRole> nationRoles) {
        this.nationRoles = nationRoles;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getLastTownSpawn() {
        return lastTownSpawn;
    }

    public void setLastTownSpawn(LocalDateTime lastTownSpawn) {
        this.lastTownSpawn = lastTownSpawn;
    }

    public int getWarmupSecondsLeft() {
        return warmupSecondsLeft;
    }

    public void setWarmupSecondsLeft(int warmupSecondsLeft) {
        this.warmupSecondsLeft = warmupSecondsLeft;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return id.equals(resident.id) &&
                name.equals(resident.name) &&
                town.equals(resident.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

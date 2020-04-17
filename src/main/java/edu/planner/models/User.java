package edu.planner.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.enums.LevelDegree;
import edu.planner.enums.Profile;
import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable, IModel {

    private static final long serialVersionUID = -1296150860583031241L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "is required")
    private String name;

    @NotNull(message = "is required")
    private String levelDegree;

    @NotEmpty(message = "is required")
    @Email
    private String email;

    @JsonIgnore
    @NotEmpty(message = "is required")
    private String hashKey;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES", foreignKey = @ForeignKey(name = "FK_USER"))
    @JsonIgnore
    private Set<Short> profiles = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "REQUIRED_PROFILES", foreignKey = @ForeignKey(name = "FK_USER"))
    @JsonIgnore
    private Set<Short> requiredProfiles = new HashSet<>();

    public User(Long id, String name, String levelDegree) {
        this.id = id;
        this.name = name;
        this.levelDegree = levelDegree;
    }

    public String getLevelDegree() {
        return LevelDegree.toEnum(levelDegree).getId();
    }

    public void setLevelDegree(String levelDegree) {
        this.levelDegree = LevelDegree.toEnum(levelDegree).getId();
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getId());
    }

    public Set<Profile> getRequiredProfiles() {
        return requiredProfiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public Set<Short> getRequiredProfilesShort() {
        return requiredProfiles;
    }
}
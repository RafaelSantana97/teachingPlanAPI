package edu.planner.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.planner.enums.LevelDegree;
import edu.planner.enums.Profile;
import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @CollectionTable(name = "profiles", foreignKey = @ForeignKey(name = "PROF_FK_USER"))
    @JsonIgnore
    private Set<Short> profiles = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "required_profiles", foreignKey = @ForeignKey(name = "REQ_PROF_FK_USER"))
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
        return Profile.shortsToProfiles(profiles);
    }

    public Set<Profile> getRequiredProfiles() {
        return Profile.shortsToProfiles(requiredProfiles);
    }

    @JsonIgnore
    public Set<Short> getRequiredProfilesShort() {
        return requiredProfiles;
    }
}
package edu.planner.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.planner.enums.SubjectType;
import edu.planner.interfaces.IModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject implements Serializable, IModel {

    private static final long serialVersionUID = 7564444507135696452L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 1)
    private String type;

    @ManyToOne
    @JoinColumn(name = "responsible", nullable = false, foreignKey = @ForeignKey(name = "RESP_FK_USER"))
    private User responsible;

    @OneToMany(mappedBy = "subject")
    @JsonBackReference
    private List<Class> classes = new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public Subject(Long id, String name, String type, User responsible) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.responsible = responsible;
    }

    public String getType() {
        return SubjectType.toEnum(type).getId();
    }

    public void setType(SubjectType type) {
        this.type = type.getId();
    }
}
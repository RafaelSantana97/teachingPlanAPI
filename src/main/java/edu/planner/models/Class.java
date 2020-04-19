package edu.planner.models;

import edu.planner.enums.Period;
import edu.planner.enums.Semester;
import edu.planner.interfaces.IModel;
import edu.planner.models.validation.ModelConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Class implements Serializable, IModel {

    private static final long serialVersionUID = 5814786944901023991L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String code;

    @Column(nullable = false)
    private Short period;

    @Column(nullable = false, length = 2)
    private String semester;

    @Column(nullable = false)
    @Digits(fraction = 0, integer = 4, message = "Invalid value")
    private Short year;

    @ManyToOne
    @JoinColumn(name = "subject", nullable = false, foreignKey = @ForeignKey(name = "FK_SUBJECT"))
    @ModelConstraint
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher", nullable = false, foreignKey = @ForeignKey(name = "FK_USER"))
    private User teacher;

    public void setPeriod(Short period) {
        if (period == null)
            return;
        this.period = Period.toEnum(period).getId();
    }

    public void setSemester(String semester) {
        if (semester == null)
            return;
        this.semester = Semester.toEnum(semester).getId();
    }
}
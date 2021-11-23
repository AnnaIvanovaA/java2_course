package org.database.university.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "subjects")
@NoArgsConstructor
@ToString(exclude = "faculties")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int hours;

    @ManyToMany
    @JoinTable(
            name = "subjects_on_faculties",
            joinColumns = @JoinColumn(name = "subject_id"),                //column from linking table - ID from subjects
            inverseJoinColumns = @JoinColumn(name = "faculty_id") //column from linking table - ID from faculties
    )
    private List<Faculty> faculties;

    public Subject(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }
}

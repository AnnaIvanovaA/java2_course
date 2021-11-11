package org.database.university.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//University -> table 'university'
@Setter
@Getter
@Entity
@Table(name = "unversity")
@NoArgsConstructor
@AllArgsConstructor
public class University {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long universityId;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "foundation_year")
    private Integer foundationYear;


    public University(String name, String shortName, Integer foundationYear) {
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
    }

//    public University() {
//    }
//
//    public University(Long universityId, String name, String shortName, Integer foundationYear) {
//        this.universityId = universityId;
//        this.name = name;
//        this.shortName = shortName;
//        this.foundationYear = foundationYear;
//    }
//
//    public Long getUniversityId() {
//        return universityId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getShortName() {
//        return shortName;
//    }
//
//    public Integer getFoundationYear() {
//        return foundationYear;
//    }
}

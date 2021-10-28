package org.database.university.domain;

public class University {

    private Long universityId;
    private String name;
    private String shortName;
    private Integer foundationYear;

    public University(Long universityId, String name, String shortName, Integer foundationYear) {
        this.universityId = universityId;
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }
}

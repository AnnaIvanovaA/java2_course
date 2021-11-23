package org.database.university.repository;

import org.database.university.domain.University;

import java.util.List;

public interface UniversityRepository {

    List<University> findAll();

    University createUniversity(String name, String shortName, Integer foundationYear);

    University createUniversity(String name, String shortName, Integer foundationYear, List<String> facultyNames);

    University findByUniversityId(Long universityId);


}

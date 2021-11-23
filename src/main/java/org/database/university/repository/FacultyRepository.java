package org.database.university.repository;

import org.database.university.domain.Faculty;

public interface FacultyRepository {

    Faculty createFaculty(String name, Long universityId);
}

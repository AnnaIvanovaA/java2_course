package org.database.university.repository.hbm;

import lombok.RequiredArgsConstructor;
import org.database.university.domain.Faculty;
import org.database.university.domain.University;
import org.database.university.repository.FacultyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@RequiredArgsConstructor
public class HibernateFacultyRepository implements FacultyRepository {

    private final SessionFactory factory;

    @Override
    public Faculty createFaculty(String name, Long universityId) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Faculty faculty = new Faculty();
            faculty.setName(name);
            // get/load
            faculty.setUniversity(session.load(University.class, universityId)); //university_id -> insert
            session.persist(faculty);

            tx.commit();
            return faculty;
        }
    }
}

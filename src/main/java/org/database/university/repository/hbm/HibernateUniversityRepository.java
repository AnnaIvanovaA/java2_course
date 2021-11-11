package org.database.university.repository.hbm;

import lombok.RequiredArgsConstructor;
import org.database.university.domain.University;
import org.database.university.repository.UniversityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RequiredArgsConstructor //constructor with final fields
//public HibernateUniversityRepository(SessionFactory factory) {this.factory = factory;}
public class HibernateUniversityRepository implements UniversityRepository {

    private final SessionFactory factory;

    @Override
    public List<University> findAll() {
        return null;
    }

    @Override
    public University createUniversity(String name, String shortName, Integer foundationYear) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            University u = new University(name, shortName, foundationYear);
            session.persist(u);
            System.out.println("Newly inserted row ID: " + u.getUniversityId());

            tx.commit();  //transaction
        }

        return null;
    }
}

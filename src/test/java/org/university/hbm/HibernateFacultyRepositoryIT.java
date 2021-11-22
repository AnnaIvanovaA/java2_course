package org.university.hbm;

import org.database.university.domain.Faculty;
import org.database.university.domain.University;
import org.database.university.repository.hbm.HibernateFacultyRepository;
import org.database.university.repository.hbm.HibernateUniversityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.university.configuration.TestHibernateConfiguration;

public class HibernateFacultyRepositoryIT {

    private static HibernateUniversityRepository universityRepository;
    private static HibernateFacultyRepository facultyRepository;

    @BeforeAll
    public static void initializeRepositories(){
        universityRepository = new HibernateUniversityRepository(TestHibernateConfiguration.getFactory());
        facultyRepository = new HibernateFacultyRepository(TestHibernateConfiguration.getFactory());
    }

    @Test
    public void shouldCreateFaculty(){
        //given
        University university = universityRepository
                .createUniversity("University_1", "U1", 2888);
        String name = "Faculty_1";

        //when
        Faculty faculty = facultyRepository.createFaculty(name, university.getUniversityId());

        //then
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(name, result.getName());

    }
}

package org.university.hbm;

import org.database.university.repository.hbm.HibernateUniversityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
//Run with JUnit4
public class HibernateUniversityRepositoryTest {

    @Mock
    private SessionFactory factory;
    @Mock
    private Session session;

    @InjectMocks
    private HibernateUniversityRepository universityRepository;

//    @BeforeEach
//    public void initializeMocks(){
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void shouldReturnEmptyCollectionOfUniversities(){
        //TODO
    }
}

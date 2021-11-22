package org.university.hbm;

import org.database.university.domain.University;
import org.database.university.repository.hbm.HibernateFacultyRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;


public class HibernateFacultyRepositoryTest {

    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    private HibernateFacultyRepository facultyRepository;

    @BeforeEach
    public void initializeMocks(){
        factory = Mockito.mock(SessionFactory.class);
        session = Mockito.mock(Session.class);
        transaction = Mockito.mock(Transaction.class);

        //
        Mockito.when(factory.openSession()).thenReturn(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        facultyRepository = new HibernateFacultyRepository(factory);
    }

    @Test
    public void shouldCreateFaculty(){
        //given
        String name = "faculty_name";
        Long universityId = 1045L;

        University university = new University();
        university.setUniversityId(universityId);
        //Mockito.when(session.load(University.class, universityId)).thenReturn(university);
        Mockito.when(session.load(ArgumentMatchers.eq(University.class), ArgumentMatchers.anyLong()))
                .thenReturn(university);

        //when
        Faculty result = facultyRepository.createFaculty(name, universityId);

        //then
        Assertions.assertEquals(name, result.getName());
        Assertions.assertEquals(universityId, result.getUniversity().getUniversityId());

        //verify
        //Mockito.verify(session).persist(result);
        ArgumentCaptor<Faculty> facultyCaptor = ArgumentCaptor.forClass(Faculty.class);
        Mockito.verify(session).persist(facultyCaptor.capture());
        Faculty capturedFaculty = facultyCaptor.getValue();
        Assertions.assertEquals(name, capturedFaculty.getName());

        Mockito.verify(transaction, Mockito.times(0)).commit();
        Mockito.verify(session).close();

    }

    @Test
    public void shouldCloseSessionInCreateFacultyEvenExceptionWasThrown(){
        //given
        //method has return type
        Mockito.when(session.load(University.class, 1L))
                .thenThrow(HibernateException.class);
        //method return type is void
        Mockito.doThrow(HibernateException.class).when(session).persist(ArgumentMatchers.any(Faculty.class));

        //when
        Assertions.assertThrows(
                HibernateException.class,
                () -> facultyRepository.createFaculty("faculty_name", 1L)
        );

        //then
        Mockito.verify(session).close();



    }

}

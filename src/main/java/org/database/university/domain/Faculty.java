package org.database.university.domain;

<<<<<<< HEAD

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "faculties")
@ToString(exclude = {
        "university",
        "subjects"
}) //generated toString only for 2 fields (id, name)
public class Faculty {

    //university-faculties -- 1-M
    //id, name, university

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    //connection with University
    // bidirectional - unidirectional

    // one-to-many 1-M -> many-to-one
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToMany(mappedBy = "faculties", cascade = CascadeType.PERSIST)
    private List<Subject> subjects;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "faculty_id")
    private FacultyInfo info;

}


=======
public class Faculty {
}
>>>>>>> mockito and junit

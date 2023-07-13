package marcin.dev.advancedmapping.demo.dataacessobject;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import marcin.dev.advancedmapping.demo.entity.Instructor;
import marcin.dev.advancedmapping.demo.entity.InstructorDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Repository
public class AppDataAcessObjectImpl implements AppDataAcessObject {

    EntityManager entityManager;

    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor deleteInstructor = entityManager.find(Instructor.class,id);
        entityManager.remove(deleteInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
       return entityManager.find(InstructorDetail.class,id);


    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail detailForDeletion = entityManager.find(InstructorDetail.class,id);
        detailForDeletion.getInstructor().setInstructorDetail(null);
        entityManager.remove(detailForDeletion);
    }
}

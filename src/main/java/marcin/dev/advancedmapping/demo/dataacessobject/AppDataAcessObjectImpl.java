package marcin.dev.advancedmapping.demo.dataacessobject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import marcin.dev.advancedmapping.demo.entity.Course;
import marcin.dev.advancedmapping.demo.entity.Instructor;
import marcin.dev.advancedmapping.demo.entity.InstructorDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

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
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor tempInstructor = entityManager.find(Instructor.class, id);
        List<Course> courses = tempInstructor.getCourses();
        for (Course course : courses)
            course.setInstructor(null);
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);


    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail detailForDeletion = entityManager.find(InstructorDetail.class, id);
        detailForDeletion.getInstructor().setInstructorDetail(null);
        entityManager.remove(detailForDeletion);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", Instructor.class);
        query.setParameter("data", id);
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);

    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        return course;
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }


    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class
        );
        query.setParameter("data", id);
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);
        query.setParameter("data", id);
        Course course = query.getSingleResult();
        return course;
    }
}

package marcin.dev.advancedmapping.demo.dataacessobject;

import marcin.dev.advancedmapping.demo.entity.Course;
import marcin.dev.advancedmapping.demo.entity.Instructor;
import marcin.dev.advancedmapping.demo.entity.InstructorDetail;

import java.util.List;

public interface AppDataAcessObject {
    void saveInstructor(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course>findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void update(Instructor instructor);
    void updateCourse(Course course);
    Course findCourseById(int id);


}

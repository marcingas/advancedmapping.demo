package marcin.dev.advancedmapping.demo;

import marcin.dev.advancedmapping.demo.dataacessobject.AppDataAcessObject;
import marcin.dev.advancedmapping.demo.entity.Course;
import marcin.dev.advancedmapping.demo.entity.Instructor;
import marcin.dev.advancedmapping.demo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDataAcessObject appDAO) {
        return runner -> {
//            createInstructor(appDAO);
//            findInstructor(appDAO);
//            deleteInstructor(appDAO);
//            findInstructorDetail(appDAO);
//            deleteInstructorDetail(appDAO);
//            createInstructorWithCourses(appDAO);
//            findInstructorWithCourses(appDAO);
//            findCoursesForInstructor(appDAO);
//            findInstructorWithCoursesJoinFetch(appDAO);
//            updateInstructor(appDAO);
            updateCourse(appDAO);

        };
    }

    private void updateCourse(AppDataAcessObject appDAO) {
        int id = 10;
        Course tempCourse = appDAO.findCourseById(id);
        tempCourse.setTitle("English advanced class");

        appDAO.updateCourse(tempCourse);
        System.out.println("course updated: " + tempCourse);
    }

    private void updateInstructor(AppDataAcessObject appDAO) {
        int id = 5;
        Instructor tempInstructor = appDAO.findInstructorById(id);
        tempInstructor.setLastName("Tester");
        tempInstructor.setEmail("tester@trump.us");

        appDAO.update(tempInstructor);
        System.out.println("instructor: " + tempInstructor
        );
    }

    private void findInstructorWithCoursesJoinFetch(AppDataAcessObject appDAO) {
        int id = 5;
        System.out.println("Finding instructor with id: " + id);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(id);
        System.out.println("instructor: " + tempInstructor);
        System.out.println("courses: " + tempInstructor.getCourses());
        System.out.println("instructor details: " + tempInstructor.getInstructorDetail());
    }

    private void findCoursesForInstructor(AppDataAcessObject appDAO) {
        int id = 5;
        System.out.println("Finding instructor with id: " + id);
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("instructor: " + tempInstructor);

        System.out.println("Finding courses for instructor: " + id);

        List<Course> courses = appDAO.findCoursesByInstructorId(id);
        tempInstructor.setCourses(courses);
        System.out.println("List of courses: " + tempInstructor.getCourses());
    }

    private void findInstructorWithCourses(AppDataAcessObject appDAO) {
        int id = 5;
        System.out.println("Finding instructor with id: " + id);
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("instructor: " + tempInstructor);
        System.out.println("courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDataAcessObject appDAO) {
        Instructor instructorFirst = new Instructor("Donald", "Trump", "donald@trump.us");
        InstructorDetail instructorDetailFirst = new InstructorDetail("youtube.com/donald",
                "politics");
        instructorFirst.setInstructorDetail(instructorDetailFirst);

        Course course1 = new Course("Learn Java!");
        Course course2 = new Course("Learn Guitar!");
        Course course3 = new Course("Learn Drums!");

        instructorFirst.add(course1);
        instructorFirst.add(course2);
        instructorFirst.add(course3);

        System.out.println("Courses saved: " + instructorFirst.getCourses());
        appDAO.saveInstructor(instructorFirst);
    }

    private void deleteInstructorDetail(AppDataAcessObject appDAO) {
        int id = 3;
        appDAO.deleteInstructorDetailById(id);
        System.out.println("done");
    }

    private void findInstructorDetail(AppDataAcessObject appDAO) {
        int id = 2;
        InstructorDetail searchedDetail = appDAO.findInstructorDetailById(2);
        System.out.println("Details" + searchedDetail);
        System.out.println("Instructor:" + searchedDetail.getInstructor());
    }

    private void deleteInstructor(AppDataAcessObject appDAO) {
        int id = 1;
        appDAO.deleteInstructorById(id);
        System.out.println("Instructor deleted!");
    }

    private void findInstructor(AppDataAcessObject appDAO) {
        int id = 1;
        Instructor instructorSearched = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructorSearched);
        System.out.println("details:" + instructorSearched.getInstructorDetail());

    }

    private void createInstructor(AppDataAcessObject appDAO) {
        Instructor instructorFirst = new Instructor("John", "Dowe", "john@dowe.us");
        InstructorDetail instructorDetailFirst = new InstructorDetail("youtube.com/johndowe",
                "duck shooting");
        instructorFirst.setInstructorDetail(instructorDetailFirst);

        appDAO.saveInstructor(instructorFirst);
        System.out.println("instructor with id " + instructorFirst.getId() + " saved");
    }
}

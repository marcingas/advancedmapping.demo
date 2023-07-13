package marcin.dev.advancedmapping.demo;

import marcin.dev.advancedmapping.demo.dataacessobject.AppDataAcessObject;
import marcin.dev.advancedmapping.demo.entity.Instructor;
import marcin.dev.advancedmapping.demo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            deleteInstructorDetail(appDAO);
        };
    }

    private void deleteInstructorDetail(AppDataAcessObject appDAO) {
        int id =3;
        appDAO.deleteInstructorDetailById(id);
        System.out.println("done");
    }

    private void findInstructorDetail(AppDataAcessObject appDAO) {
        int id = 2;
       InstructorDetail searchedDetail= appDAO.findInstructorDetailById(2);
        System.out.println("Details" + searchedDetail);
        System.out.println("Instructor:" + searchedDetail.getInstructor());
    }

    private void deleteInstructor(AppDataAcessObject appDAO) {
        int id =1;
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
        Instructor instructorFirst = new Instructor("John","Dowe","john@dowe.us");
        InstructorDetail instructorDetailFirst = new InstructorDetail("youtube.com/johndowe",
                "duck shooting");
        instructorFirst.setInstructorDetail(instructorDetailFirst);

        appDAO.saveInstructor(instructorFirst);
        System.out.println("instructor with id " + instructorFirst.getId() + " saved");
    }
}

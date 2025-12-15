package org.mikmin;

public class Main {
    public static void main(String[] args) {
        Department department1 = new Department("Computer Science");
        Course course1 = new Course("Linear Algebra", 2.5, department1);
        Course course2 = new Course("Discrete Math", 3, department1);

        Student student1 = new Student("Liam", Student.Gender.MALE,  new Address(67,"bozo", "laval", Address.Province.QC, "a1b2c" +
                "3"), department1);
        Student student2 = new Student("chris", Student.Gender.OTHER,  new Address(420,"Best", "Chatauguay", Address.Province.QC, "A1B2C3"), department1);

        course1.addAssignment("test 1",50);
        course1.addAssignment("test 2", 50);

        student2.registerCourse(course1);
        student2.registerCourse(course2);
        student1.registerCourse(course1);
        student1.registerCourse(course2);

        course1.generateScores();

        System.out.println(student1);
        System.out.println(course1);

        course1.displayScores();
    }

}

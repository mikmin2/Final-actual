package org.mikmin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
public class Student {

    private String studentId;
    @Setter
    private String studentName;
    @Setter
    private Gender gender;
    @Setter
    private Address address;
    @Setter
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("%06d", nextId++);
        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    /**
     * a simplified version of toString that the Course class uses inorder to not create a infinite loop of calling
     * @return the simplified string
     */
    public String toSimplifiedString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", department=" + department +
                '}';
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", department=" + department +
                ", registeredCourses=" + registeredCourses.stream().map(Course -> Course.toSimplifiedString()).toList() +
                '}';
    }

    /**
     * registers a course into the student's registeredCourses Array list
     * @param course the course to be register
     * @return if the registration was successful
     */
    public boolean registerCourse(Course course) {
        if (course.getRegisteredStudents().contains(this)) {
            return false;
        }

        registeredCourses.add(course);

        course.getRegisteredStudents().add(this);

        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().add(null);
        }

        return true;
    }

    /**
     * removes the specified course from the course list of the student
     * @param course the course to be removed
     * @return if the removal was successful
     */
    public boolean dropCourse(Course course) {
        if (!course.getRegisteredStudents().contains(this)) {
            return false;
        }

        registeredCourses.remove(course);

        course.getRegisteredStudents().remove(this);

        return true;
    }

    public enum Gender{
        MALE,
        FEMALE,
        OTHER
    }

}

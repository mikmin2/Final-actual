package org.mikmin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
@Getter
public class Course {

    private String courseId;
    @Setter
    private String courseName;
    @Setter
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;

    private static int nextId = 0;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }

    public String toSimplifiedString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department=" + department.getDepartmentName() +
                '}';
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department=" + department +
                ", assignments=" + assignments +
                ", registeredStudents=" +  registeredStudents.stream().map(Student -> Student.toSimplifiedString()).toList() +
                '}';
    }

    public boolean registerStudent(Student student) {
        if (student.getRegisteredCourses().contains(this)) {
            return false;
        }

        registeredStudents.add(student);

        student.getRegisteredCourses().add(this);

        for (Assignment assignment : this.getAssignments()) {
            assignment.getScores().add(null);
        }

        return true;
    }
}

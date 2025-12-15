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
    @Setter
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;

    private static int nextId = 0;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }

    /**
     * a simplified version of toString that the Student class uses inorder to not create a infinite loop of calling
     * @return the simplified string
     */
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

    /**
     * registers a student into the registeredStudents Array list
     * @param student the student to be registered
     * @return if the registration was successful
     */
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

    /**
     * checks if the assignment weight of all the assignments totals to 100%
     * @return the validity of the assignment weight
     */
    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;

        for (Assignment assignment: assignments) {
            totalWeight += assignment.getWeight();
        }

        return (totalWeight == 100);
    }

    /**
     * adds an assignment to the assignments list of the course
     * @param str the name of the assignment
     * @param weight the weight of the assignment
     * @return if the addition was successful
     */
    public boolean addAssignment(String str, double weight) {
        if (str == null || str.isEmpty() || weight < 0 || weight > 100) {
            return false;
        }

        assignments.add(new Assignment(str, weight));

        return true;
    }

    /**
     * calculates the student average of every student registered in the course
     * @return an int array of the averages of the students
     */
    public int[] calcStudentAverage() {
        int[] ints = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            int total = 0;

            for (Assignment assignment : assignments) {
                total += (assignment.getScores().get(i) * assignment.getWeight()) / 100;
            }

            ints[i] = total;
        }

        return ints;
    }

    /**
     * generates random scores for every assignment
     */
    public void generateScores() {
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore();
        }
    }

    /**
     * prints the program, the students and their scores for each assignment, as well as all their final averages
     */
    public void displayScores() {
        int[] ints = calcStudentAverage();

        System.out.printf("Course: %s(%s)\n",courseName,courseId);

        System.out.print("          ");
        for (Assignment assignment : assignments) {
            System.out.printf("%15s", assignment.getAssignmentName());
        }
        System.out.println("    Final Score");

        for (int i = 0; i < registeredStudents.size(); i++) {
            System.out.printf("%10s", registeredStudents.get(i).getStudentName());
            for (Assignment assignment : assignments) {
                System.out.printf("%15d", assignment.getScores().get(i));
            }
            System.out.printf("%15d\n", ints[i]);
        }

        System.out.print("  Average   ");
        for (Assignment assignment : assignments) {
            System.out.printf("%15.1f", assignment.calcAssignmentAvg());
        }
    }

}

package org.mikmin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class Department {

    private String departmentId;
    @Setter
    private String departmentName;

    private static int nextId = 1;

    public Department(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentId = String.format("D%2d", nextId++);
            this.departmentName = Util.toTitleCase(departmentName);
        }
        this.departmentId = null;
        this.departmentName = null;
    }

    /**
     * checks if a given department name is valid (only containing letters and spaces)
     * @param departmentName input department name
     * @return validity of the department name
     */
    public static boolean isDepartmentNameValid(String departmentName) {
        for (char chr: departmentName.toCharArray()) {
            if (!Character.isLetter(chr) && chr != ' ') {
                return false;
            }
        }
        return true;
    }

}

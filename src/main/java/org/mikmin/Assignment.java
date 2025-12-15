package org.mikmin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@EqualsAndHashCode
@Getter
public class Assignment {
    private String assignmentId;
    @Setter
    private String assignmentName;
    @Setter
    private double weight;
    private ArrayList<Integer> scores;

    private static int nextId= 1;

    public Assignment(String assignmentName, double weight) {
        this.assignmentId = String.format("%d",nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }

    public double calcAssignmentAvg() {
        double total = 0;
        for (int score : scores){
            total += score;
        }

        return total / scores.toArray().length;
    }

    public void generateRandomScore() {
        Random random = new Random();
        for (int i = 0; i <= scores.size(); i++) {
            int scoreBracket = random.nextInt(0,11);

            scores.set(i,
                    switch (scoreBracket){
                        case 0 -> random.nextInt(0,60);
                        case 1, 2 -> random.nextInt(60, 70);
                        case 3, 4 -> random.nextInt(70,80);
                        case 5, 6, 7, 8 -> random.nextInt(80, 90);
                        default -> random.nextInt(90, 100);
                    }
                    );
        }
    }
}

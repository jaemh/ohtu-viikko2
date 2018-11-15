package ohtu;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Submission {
    private int week;
    private int hours;
    private String course;
    private int[] exercises;
    private int sum;


    public void setCourse(String course){
      this.course = course;
    }

    public String getCourse(){
      return course;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours){
        this.hours = hours;
    }

    public int getHours(){
        return hours;
    }

    public void setList(int[] exercises){
      this.exercises = exercises;
    }

    public int[] getExercises(){
      return exercises;
    }

    public int sumOfExercises(int[] exercises, int sum) {
     sum = exercises.length;
     return sum;
   }


    @Override
    public String toString() {
        return course + ", viikko " + week + ", tehtyjä tehtäviä yhteensä: "  + sum + " aikaa kului " + hours + "h, tehdyt tehtävät: " + Arrays.toString(exercises);
    }

}

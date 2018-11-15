package ohtu;

public class Course {

    public String name;
    public int[] exercises;
    public String fullName;

    public void setName(String name){
      this.name = name;
    }

    public String getName(){
      return name;
    }

    public void setExercises(int[] exercises){
      this.exercises = exercises;
    }

    public int[] getExercises(){
      return exercises;
    }

    public void setFullName(String fullName){
      this.fullName = fullName;
    }

    public String getFullName(){
      return fullName;
    }
}

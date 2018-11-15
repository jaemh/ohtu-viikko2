package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static Course findCourse(Course[] courses, String name) {
        for(int i=0; i< courses.length; i++){

          if(courses[i].getName().equals(name)){
            return courses[i];
          }
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url)
          .connectTimeout(1000)
          .socketTimeout(1000)
          .execute().returnContent().asString();

        String url2 = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText2 = Request.Get(url2)
          .connectTimeout(1000)
          .socketTimeout(1000)
          .execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);


        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(bodyText2, Course[].class);


        System.out.println("Oliot:");
        for (Submission submission : subs) {
          Course course = findCourse(courses, submission.getCourse());
          submission.setCourseObj(course);
          System.out.println(submission);
        }


    }
}

/*
{
  "1": {
    "students": 68,
    "hour_total": 376,
    "exercise_total": 723,
    "hours": [
      null,
      1,
      2,
      6,
      15,
      15,
      8,
      5,
      8,
      null,
      4,
      1,
      1
    ],
    "exercises": [
      null,
      2,
      null,
      null,
      null,
      null,
      6,
      null,
      null,
      null,
      null,
      715
    ]
  },
}

String courseId = "1"

class Stat {
  private int students;

  parse(JsonObject obj) {
    this.students = obj.getAsJsonObject().get('students').getAsInt();
  }
}

JsonObject courseData = parser.parse(statsResponse).getAsJsonObject();


Stat weekOneStats = new Stat();

weekOneStat.parse(.get("1"));

.getAsJsonObject().get('students').getAsInt();
*/

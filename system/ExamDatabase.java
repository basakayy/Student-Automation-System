package automation.system;

import java.util.ArrayList;
import java.util.HashMap;

public class ExamDatabase {
    private static final HashMap<String, ArrayList<Exam>> exams = new HashMap<>();

    public static void addExam(String courseCode, String date) {
        Exam newExam = new Exam(courseCode, date);
        exams.computeIfAbsent(courseCode, k -> new ArrayList<>()).add(newExam);
    }

    public static ArrayList<Exam> getExamsForCourse(String courseCode) {
        return exams.getOrDefault(courseCode, new ArrayList<>());
    }
}

class Exam {
    String courseCode;
    String date;

    public Exam(String courseCode, String date) {
        this.courseCode = courseCode;
        this.date = date;
    }

    @Override
    public String toString() {
        return courseCode + " - " + date;
    }
}

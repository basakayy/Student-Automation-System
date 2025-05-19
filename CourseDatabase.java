package automation.system;

import java.util.HashSet;
import java.util.Set;

public class CourseDatabase {
    private static final Set<String> courses = new HashSet<>();

    static {
        // Mevcut dersleri ekleyin
        courses.add("Matematik");
        courses.add("Fizik");
        courses.add("Türkçe");
        courses.add("Sosyal");
        courses.add("Beden");
        courses.add("Müzik");
        courses.add("Biyoloji");
        courses.add("Kimya");
    }

    public static boolean isCourseValid(String courseCode) {
        return courses.contains(courseCode);
    }

    public static void addCourse(String courseCode) {
        courses.add(courseCode);
    }

    public static Set<String> getCourses() {
        return courses;
    }
}

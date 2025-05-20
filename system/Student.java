package automation.system;

import java.util.HashMap;

public class Student extends User implements Authentication, CourseActions,Reportable {
    HashMap<String, String> registeredCourses = new HashMap<>();
    boolean coursesApproved = false; // Ders onayı durumu
    Instructor advisor = null; // Danışman öğretim üyesi

    public Student(String userID, String password, String name) {
        super(userID, password, name);
    }

    @Override
    public boolean login(String userID, String password) {
        return this.userID.equals(userID) && this.password.equals(password);
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Şifre başarıyla değiştirildi.");
    }

    @Override
    public void registerCourse(String courseCode) {
        registeredCourses.put(courseCode, "Henüz not girilmedi");
    }

    @Override
    public void viewCourses() {
        registeredCourses.forEach((course, grade) -> 
            System.out.println(course + ": " + grade));
    }

    @Override
    public void displayDashboard() {
        System.out.println("Öğrenci Paneli: " + name);
    }
    @Override
    public void generateReport() {
        System.out.println("Öğrenci Raporu:");
        registeredCourses.forEach((course, grade) -> 
            System.out.println("Ders: " + course + ", Not: " + grade));
    }

    @Override
    public void viewReport() {
        System.out.println("Öğrenci Not Raporu Görüntüleniyor...");
        generateReport();
    }

    // Getter ve Setter metotlar
    public Instructor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Instructor advisor) {
        this.advisor = advisor;
    }
}

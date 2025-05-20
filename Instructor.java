package automation.system;

import java.util.ArrayList;
import java.util.HashMap;

public class Instructor extends User implements Authentication,Reportable {
    public Instructor(String userID, String password, String name) {
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
    public void displayDashboard() {
        System.out.println("Öğretim Üyesi Paneli: " + name);
    }

    @Override
    public void generateReport() {
        System.out.println("Sınıf Başarı Raporu Oluşturuluyor...");
        // Sınıf ve not bilgilerini al
        HashMap<String, ArrayList<String>> courseGrades = new HashMap<>();

        // Tüm öğrencileri dolaşarak ders notlarını derle
        UserDatabase.users.values().stream()
            .filter(user -> user instanceof Student) // Sadece öğrencileri filtrele
            .map(user -> (Student) user) // Öğrenci nesnelerine dönüştür
            .forEach(student -> {
                student.registeredCourses.forEach((course, grade) -> {
                    courseGrades.computeIfAbsent(course, k -> new ArrayList<>()).add(grade);
                });
            });

        // Sınıf başarılarını hesapla ve görüntüle
        courseGrades.forEach((course, grades) -> {
            int passedCount = 0;
            int totalCount = grades.size();

            for (String grade : grades) {
                try {
                    int numericGrade = Integer.parseInt(grade);
                    if (numericGrade >= 50) { // Geçme notu 50 olarak varsayılmıştır
                        passedCount++;
                    }
                } catch (NumberFormatException e) {
                    // Not verisi eksik ya da geçersiz
                }
            }

            double successRate = (totalCount > 0) ? ((double) passedCount / totalCount) * 100 : 0;

            System.out.println("Ders: " + course);
            System.out.println("Toplam Öğrenci: " + totalCount);
            System.out.println("Başarı Oranı: " + String.format("%.2f", successRate) + "%");
            System.out.println("--------------------");
        });
    }

    @Override
    public void viewReport() {
        System.out.println("Sınıf Başarı Raporu Görüntüleniyor...");
        generateReport();
    }
}

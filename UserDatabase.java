package automation.system;

import java.util.HashMap;
import javax.swing.*;

public class UserDatabase {
    static HashMap<String, User> users = new HashMap<>();

    public static void addUser(User user) {
        users.put(user.userID, user);
    }
    //overload
    public static void addUser(String userID, String password, String name) {
        users.put(userID, new Student(userID, password, name));
    }
    
    public static User getUser(String userID) {
        return users.get(userID);
    }
    public static boolean addNewUser(String userID, String password, String name, String userType) {
        if (users.containsKey(userID)) {
            return false; // Kullanıcı zaten var
        }

        switch (userType.toLowerCase()) {
            case "student":
                users.put(userID, new Student(userID, password, name));
                break;
            case "instructor":
                users.put(userID, new Instructor(userID, password, name));
                break;
            default:
                return false; // Geçersiz kullanıcı türü
        }
        return true;
    }
    public static boolean addStudent(String userID, String password, String name) {
        if (users.containsKey(userID)) {
            return false; // Kullanıcı zaten var
        }

        users.put(userID, new Student(userID, password, name));
        return true; // Öğrenci başarıyla eklendi
    }

    public static void assignAdvisor(String studentId, Instructor advisor) {
        User user = UserDatabase.getUser(studentId);
        if (user instanceof Student student) {
            student.advisor = advisor;
            JOptionPane.showMessageDialog(null, "Danışman başarıyla atandı.");
        } else {
            JOptionPane.showMessageDialog(null, "Geçersiz Öğrenci ID.");
        }
    }
    public static User getUserByName(String name) {
        for (User user : users.values()) {
            if (user.name.equalsIgnoreCase(name)) { // İsim eşleşmesini kontrol et
                return user;
            }
        }
        return null; // Eşleşen bir kullanıcı bulunamadı
    }
    public static boolean removeUser(String userID) {
        if (users.containsKey(userID)) {
            users.remove(userID);
            return true; // Başarıyla silindi
        }
        return false; // Kullanıcı bulunamadı
    }



}

package automation.system;

public class Studentlogin extends Login {
    public void specificLoginAction() {
    /*specificLoginAction metotları 
    Login sınıfında tanımlı olmadığı için 
    Instructorlogin ve Studentlogin nesnelerine erişmek amacıyla downcasting yapılmıştır.*/
        System.out.println("Öğrenci özel giriş işlemi yapıldı.");
    }
}
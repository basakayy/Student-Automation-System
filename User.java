package automation.system;

public abstract class User implements Authentication {
    String userID;
    String password;
    String name;

    public User(String userID, String password, String name) {
        this.userID = userID;
        this.password = password;
        this.name = name;
    }

    public boolean login(String userID, String password) {
        return this.userID.equals(userID) && this.password.equals(password);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Şifre başarıyla değiştirildi.");
    }

    public abstract void displayDashboard();
}
